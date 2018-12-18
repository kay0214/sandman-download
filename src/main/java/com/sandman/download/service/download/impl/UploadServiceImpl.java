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
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

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

        String fileName = FileUtils.getFileNameByTime(multipartFile.getOriginalFilename());

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
            result.put("fileName",multipartFile.getOriginalFilename());
            result.put("fileType",fileType);
        }

        return result;
    }

    /**
     * 发布资源
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int uploadResource(Resource resource) {
        int result = 0;
        Integer userId = SessionUtils.getUserId();
        ResourceExample resourceExample = new ResourceExample();
        resourceExample.createCriteria().andUserIdEqualTo(userId).andResourceUrlEqualTo(resource.getResourceUrl()).andDelFlagEqualTo(1);
        List<Resource> resourceList = resourceMapper.selectByExample(resourceExample);
        Resource res = new Resource();
        if(!CollectionUtils.isEmpty(resourceList)){
            res = resourceList.get(0);
            // 赋值,赋值到resource是因为html页面要用
            resource.setId(res.getId());
            resource.setUserId(res.getUserId());
            resource.setUsername(res.getUsername());
            resource.setNickname(res.getNickname());
            resource.setResourceSize(res.getResourceSize());
            resource.setResourceType(res.getResourceType());
            resource.setDownloadCount(res.getDownloadCount());
            resource.setStatus(res.getStatus());
            resource.setStatusDesc(res.getStatusDesc());
            resource.setCreateTime(res.getCreateTime());
            resource.setUpdateTime(res.getUpdateTime());
            // 删除标记置为0
            resource.setDelFlag(0);

            result = resourceMapper.updateByPrimaryKeySelective(resource);
            //收集用户操作日志 1:上传 2:下载
            insertResourceLog(userId,res.getId(),1);
        }
        return result;
    }
}
