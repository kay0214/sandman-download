/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.service.download.UploadService;
import com.sandman.download.utils.FileUtils;
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

    @PostMapping(value = "/upload_resource")
    public ModelAndView uploadResource(Resource resource){
        logger.info("上传资源 -> [{}]", JSON.toJSONString(resource));
        int result = uploadService.uploadResource(resource);
        if(result > 0){
            return new ModelAndView("redirect:/my_resource/search");
        }else{
            return new ModelAndView("/upload")
                    .addObject("resourceUrl",resource.getResourceUrl())
                    .addObject("resourceType",resource.getResourceType())
                    .addObject("fileName", FileUtils.getFileNameByUrl(resource.getResourceUrl()))
                    .addObject("resourceName",resource.getResourceName())
                    .addObject("resourceGold",resource.getResourceGold())
                    .addObject("resourceDesc",resource.getResourceDesc())
                    .addObject("errorMsg","上传失败,原因:[保存数据库失败]");
        }
    }
}
