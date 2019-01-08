/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.download.impl;

import com.alibaba.fastjson.JSONObject;
import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.config.SystemConfig;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.dao.mysql.system.model.auto.User;
import com.sandman.download.service.download.PersonalCenterService;
import com.sandman.download.utils.FileUtils;
import com.sandman.download.utils.NumberUtils;
import com.sandman.download.utils.SessionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

/**
 * @author sunpeikai
 * @version PersonalCenterServiceImpl, v0.1 2019/1/8 16:52
 */
@Service
public class PersonalCenterServiceImpl extends BaseServiceImpl implements PersonalCenterService {

    /**
     * 上传头像到服务器
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public boolean uploadIcon(MultipartFile multipartFile) {
        Integer userId = SessionUtils.getUserId();
        User user = getUserByUserId(userId);
        String filePath = SystemConfig.getPathPrefix() + SystemConfig.getIconPrefix() + "/" + userId + "/";//  /root/sandman/emmmoe/icon + / + userId + /
        String fileName = FileUtils.getFileNameByTime(multipartFile.getOriginalFilename());
        String serverUrl = SystemConfig.getIconPrefix() + "/" + userId + "/" + fileName;
        user.setIconUrl(serverUrl);
        logger.info("头像地址为:[{}]",user.getIconUrl());
        boolean update = userMapper.updateByPrimaryKeySelective(user)>0;
        //开始将文件上传到远程服务器
        File tempFile = FileUtils.getFileByMultipartFile(multipartFile);//MultiPartFile转File
        boolean uploadSuccess = FileUtils.upload(filePath,fileName,tempFile);//上传服务器
        tempFile.delete();

        return uploadSuccess && update;
    }
}
