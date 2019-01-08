/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.alibaba.fastjson.JSONObject;
import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.config.SystemConfig;
import com.sandman.download.constant.ReturnMessage;
import com.sandman.download.dao.mysql.system.model.auto.User;
import com.sandman.download.service.download.PersonalCenterService;
import com.sandman.download.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sunpeikai
 * @version PersonalCenterController, v0.1 2019/1/8 15:20
 */
@Controller
@RequestMapping(value = "/personal")
public class PersonalCenterController extends BaseController {

    @Autowired
    private PersonalCenterService personalCenterService;

    @GetMapping(value = "/init")
    public ModelAndView init(){
        logger.info("entry personal center");
        return new ModelAndView("/personal");
    }

    @GetMapping(value = "/refresh")
    public ModelAndView refresh(){
        logger.info("refresh account info");
        return new ModelAndView("redirect:/personal/init");
    }

    @ResponseBody
    @PostMapping(value = "/upload_icon")
    public BaseResult uploadIcon(@RequestParam("file") MultipartFile file){
        BaseResult baseResult = new BaseResult();
        logger.info("用户上传头像:[{}]",file.getOriginalFilename());
        boolean result = personalCenterService.uploadIcon(file);
        // 刷新session
        refreshUser();
        if(result){
            baseResult.setStatusInfo(ReturnMessage.SUCCESS_USER_UPDATE);
        }else{
            baseResult.setStatusInfo(ReturnMessage.ERR_USER_UPDATE);
        }
        return baseResult;
    }

    private void refreshUser(){
        Integer userId = SessionUtils.getUserId();
        User user = personalCenterService.getUserByUserId(userId);
        user.setIconUrl(SystemConfig.getLinePathPrefix() + user.getIconUrl());
        SessionUtils.setSessionAttribute("user",user);
    }
}
