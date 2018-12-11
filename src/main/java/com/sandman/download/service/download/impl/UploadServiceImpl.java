/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.download.impl;

import com.alibaba.fastjson.JSONObject;
import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.config.SystemConfig;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.dao.mysql.download.model.auto.ResourceExample;
import com.sandman.download.dao.mysql.system.model.auto.User;
import com.sandman.download.service.download.UploadService;
import com.sandman.download.utils.DateUtils;
import com.sandman.download.utils.FileUtils;
import com.sandman.download.utils.NumberUtils;
import com.sandman.download.utils.SessionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

/**
 * @author sunpeikai
 * @version UploadServiceImpl, v0.1 2018/12/11 17:09
 */
@Service
public class UploadServiceImpl extends BaseServiceImpl implements UploadService {

    /**
     * 上传文件
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public JSONObject uploadFile(MultipartFile multipartFile) {
        JSONObject result = new JSONObject();
        Resource resource = new Resource();
        Integer userId = SessionUtils.getUserId();
        User user = getUserByUserId(userId);
        String fileType = FileUtils.getSuffixNameByFileName(multipartFile.getOriginalFilename());
        fileType = (fileType==null || "".equals(fileType))?"file":fileType;//如果utils给出的文件类型为null，将file赋值给fileType
        String filePath = SystemConfig.getPathPrefix() + SystemConfig.getFilePrefix() + "/" + userId + "/";//  /root/sandman/download/file + / + userId + /

        String fileName = DateUtils.getNowStryyyyMMddHHmmss() + multipartFile.getOriginalFilename();

        resource.setUserId(userId);
        resource.setUsername(user.getUsername());
        resource.setNickname(user.getNickname());
        resource.setResourceUrl(filePath + fileName);
        resource.setResourceSize(NumberUtils.getDoubleByLong(multipartFile.getSize()));
        resource.setResourceType(fileType);
        resource.setDownloadCount(0);
        resource.setStatus(0);
        resource.setCreateTime(new Date());
        resource.setUpdateTime(new Date());
        resource.setDelFlag(1);

        // 先保存到数据库
        int insert = resourceMapper.insertSelective(resource);
        if(insert == 0){
            return null;
        }

        //开始将文件上传到远程服务器
        File tempFile = FileUtils.getFileByMultipartFile(multipartFile);//MultiPartFile转File
        boolean uploadSuccess = FileUtils.upload(filePath,fileName,tempFile);//上传服务器
        tempFile.delete();
        if(uploadSuccess){//如果上传远程服务器成功
            result.put("url",resource.getResourceUrl());
        }

        return result;
    }
}
