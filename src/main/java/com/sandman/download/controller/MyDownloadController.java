/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.bean.download.MyDownloadBean;
import com.sandman.download.bean.download.MyDownloadResultBean;
import com.sandman.download.bean.download.ReDownloadCheckBean;
import com.sandman.download.constant.ReturnMessage;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.dao.mysql.download.model.auto.ResourceLog;
import com.sandman.download.service.download.MyDownloadService;
import com.sandman.download.utils.FileUtils;
import com.sandman.download.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author sunpeikai
 * @version MyDownloadController, v0.1 2018/12/18 17:18
 */
@Controller
@RequestMapping(value = "/my_download")
public class MyDownloadController extends BaseController {

    @Autowired
    private MyDownloadService myDownloadService;

    @GetMapping(value = "/init")
    public ModelAndView init(MyDownloadBean myDownloadBean){
        // TODO:我的下载做成带搜索分页的
        Integer userId = SessionUtils.getUserId();
        if(null != myDownloadBean.getCurrPage()){
            myDownloadBean.setCurrPage((myDownloadBean.getCurrPage()<1)?1:myDownloadBean.getCurrPage());
            myDownloadBean.computeLimit();
        }else{
            myDownloadBean.setCurrPage(1);
            myDownloadBean.computeLimit();
        }
        myDownloadBean.setUserId(userId);
        logger.info("entry my_download view,userId:[{}]",userId);
        int count = myDownloadService.getAllMyDownloadCount(myDownloadBean);
        List<MyDownloadResultBean> myDownloadResultBeanList = myDownloadService.getAllMyDownload(myDownloadBean);
        return new ModelAndView("/my_download")
                .addObject("count",count)
                .addObject("search",myDownloadBean.getResourceName())
                .addObject("currPage",myDownloadBean.getCurrPage())
                .addObject("myDownload",myDownloadResultBeanList)
                .addObject("errorMsg",myDownloadBean.getErrorMsg());
    }

    @GetMapping(value = "/redownload")
    public ModelAndView reDownload(ReDownloadCheckBean reDownloadCheckBean, HttpServletResponse response){
        int isCheckSuccess = checkIfSuccess(reDownloadCheckBean.getResourceId());
        if(isCheckSuccess == 2){
            logger.info("下载前的检查通过,开始下载");
            Resource resource = myDownloadService.getResourceById(reDownloadCheckBean.getResourceId());
            if(resource != null){
                String fileNameWithoutType = FileUtils.getFileNameByUrl(resource.getResourceUrl());
                String fileName = ("file".equals(resource.getResourceType()))?fileNameWithoutType:(fileNameWithoutType + "." + resource.getResourceType());
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/force-emmmoe");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=\"" + FileUtils.getRightFileNameUseCode(FileUtils.getFileNameRemoveTime(fileName)) + "\"");// 设置文件名
                boolean success = FileUtils.download(FileUtils.getFilePathByUrl(resource.getResourceUrl()),fileName,response);
                // 下载成功不需要返回，因为会报[java.lang.IllegalStateException: Cannot call sendRedirect() after the response has been committed]
                if(!success){
                    return new ModelAndView("redirect:/my_download/init").addObject("errorMsg",ReturnMessage.ERR_DOWNLOAD.getMessage());
                }
            }
            return new ModelAndView("redirect:/my_download/init").addObject("errorMsg",ReturnMessage.ERR_RESOURCE_NOT_EXIST.getMessage());
        }
        return new ModelAndView("redirect:/my_download/init").addObject("errorMsg",ReturnMessage.ERR_RESOURCE_DOWNLOAD_CHECK.getMessage());
    }

    /**
     * 重新下载之前的检查
     * @auth sunpeikai
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/check_redownload")
    public BaseResult checkReDownload(@RequestBody ReDownloadCheckBean reDownloadCheckBean){
        logger.info("重新下载前的检查,resourceId:[{}],resourceLogId:[{}]",reDownloadCheckBean.getResourceId(),reDownloadCheckBean.getResourceLogId());
        ResourceLog resourceLog = myDownloadService.getResourceLogById(reDownloadCheckBean.getResourceLogId());
        Integer userId = SessionUtils.getUserId();
        if(resourceLog != null){
            if(reDownloadCheckBean.getResourceId().equals(resourceLog.getResourceId()) && resourceLog.getUserId().equals(userId)){
                checkSuccess(reDownloadCheckBean.getResourceId(),reDownloadCheckBean.getResourceLogId());
                return new BaseResult();
            }
        }
        return new BaseResult(ReturnMessage.ERR_RESOURCE_DOWNLOAD_CHECK);
    }
}
