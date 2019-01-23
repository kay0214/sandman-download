/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.controller;

import com.sandman.film.base.BaseController;
import com.sandman.film.base.BaseResult;
import com.sandman.film.bean.film.MyDownloadBean;
import com.sandman.film.dao.mysql.film.model.auto.FilmLog;
import com.sandman.film.service.film.MyDownloadService;
import com.sandman.film.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author sunpeikai
 * @version MyDownloadController, v0.1 2019/1/21 20:13
 */
@Controller
@RequestMapping(value = "/my_download")
public class MyDownloadController extends BaseController {

    @Autowired
    private MyDownloadService myDownloadService;

    @GetMapping(value = "/init")
    public ModelAndView init(){
        return new ModelAndView("my_exchange");
    }

    @ResponseBody
    @GetMapping(value = "/search_list")
    public BaseResult searchList(MyDownloadBean request){
        Integer userId = SessionUtils.getUserId();
        request.setUserId(userId);
        logger.info("查询列表分页 -> page:[{}],limit:[{}]",request.getPage(),request.getLimit());
        int count = myDownloadService.getAllMyDownloadCount(request);
        List<FilmLog> filmLogList = myDownloadService.getAllMyDownload(request);
        return new BaseResult(filmLogList,count);
    }
}
