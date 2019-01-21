/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.controller;

import com.sandman.film.base.BaseController;
import com.sandman.film.bean.film.FilmBean;
import com.sandman.film.constant.CommonConstant;
import com.sandman.film.service.film.FilmService;
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
    private FilmService filmService;

    @GetMapping(value = "/")
    public ModelAndView init(){
        ModelAndView modelAndView = new ModelAndView("index");
        // 获取资源信息（type：1 -> 按照下载次数倒叙排序，else -> 按照创建时间倒叙)
        // 最新上传,默认排序
        modelAndView.addObject("filmList", filmService.getFilmByType(new FilmBean(1, CommonConstant.DEFAULT_LIMIT,0)));
        // 最新上传count
        modelAndView.addObject("count", filmService.getFilmCountByType(new FilmBean(1,CommonConstant.DEFAULT_LIMIT,0)));


        // 最多下载
        //modelAndView.addObject("hotResources",filmService.getResourceByType(new FilmBean(1,CommonConstant.DEFAULT_LIMIT,1)));
        // 最多下载count
        //modelAndView.addObject("hotCount",filmService.getResourceCountByType(new FilmBean(1,CommonConstant.DEFAULT_LIMIT,0)));
        return modelAndView;
    }
}
