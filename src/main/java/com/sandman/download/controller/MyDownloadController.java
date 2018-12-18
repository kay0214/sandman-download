/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.sandman.download.base.BaseController;
import com.sandman.download.bean.download.MyDownloadBean;
import com.sandman.download.bean.download.MyDownloadResultBean;
import com.sandman.download.service.download.MyDownloadService;
import com.sandman.download.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        List<MyDownloadResultBean> myDownloadResultBeanList = myDownloadService.getAllMyDownload(myDownloadBean);
        return new ModelAndView("/my_download").addObject("myDownload",myDownloadResultBeanList);
    }
}
