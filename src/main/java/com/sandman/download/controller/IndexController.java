/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.sandman.download.base.BaseController;
import com.sandman.download.bean.download.ResourceBean;
import com.sandman.download.constant.CommonConstant;
import com.sandman.download.dao.mysql.system.model.auto.User;
import com.sandman.download.service.download.ResourceService;
import com.sandman.download.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sunpeikai
 * @version IndexController, v0.1 2018/11/23 14:47
 */
@Controller
@RequestMapping(value = "/")
public class IndexController extends BaseController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping(value = "/")
    public ModelAndView init(){
        ModelAndView modelAndView = new ModelAndView("/index");
        // 获取资源信息（type：1 -> 按照下载次数倒叙排序，else -> 按照创建时间倒叙)
        // 最新上传
        modelAndView.addObject("newResources",resourceService.getResourceByType(new ResourceBean(1, CommonConstant.DEFAULT_LIMIT,0)));
        // 最多下载
        modelAndView.addObject("hotResources",resourceService.getResourceByType(new ResourceBean(1,CommonConstant.DEFAULT_LIMIT,1)));
        // 最新上传count
        modelAndView.addObject("count",resourceService.getResourceCountByType(new ResourceBean(1,CommonConstant.DEFAULT_LIMIT,0)));
        // 最多下载count
        modelAndView.addObject("hotCount",resourceService.getResourceCountByType(new ResourceBean(1,CommonConstant.DEFAULT_LIMIT,0)));
        return modelAndView;
    }
}
