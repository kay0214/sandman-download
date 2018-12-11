/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.alibaba.fastjson.JSONObject;
import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.service.download.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @author sunpeikai
 * @version UploadController, v0.1 2018/12/11 15:44
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController extends BaseController {

    @Autowired
    private UploadService uploadService;

    /**
     * 初始化
     * @auth sunpeikai
     * @param
     * @return
     */
    @GetMapping(value = "/init")
    public ModelAndView init(){

        return new ModelAndView("/upload");
    }

    /**
     * 上传文件
     * @auth sunpeikai
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/uploadFile")
    public BaseResult uploadResource(Resource resource, @RequestParam("file") MultipartFile file) throws IOException {
        logger.info("用户上传资源:[{}]",file.getOriginalFilename());
        JSONObject result = uploadService.uploadFile(file);
        return new BaseResult(result);
    }
}
