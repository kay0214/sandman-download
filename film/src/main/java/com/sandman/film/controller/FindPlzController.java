/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.controller;

import com.alibaba.fastjson.JSON;
import com.sandman.film.base.BaseController;
import com.sandman.film.bean.film.FindPlzBean;
import com.sandman.film.constant.CommonConstant;
import com.sandman.film.dao.mysql.film.model.auto.FindPlz;
import com.sandman.film.service.film.FindPlzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author sunpeikai
 * @version FindPlzController, v0.1 2019/1/22 10:10
 */
@Controller
@RequestMapping(value = "/find")
public class FindPlzController extends BaseController {

    @Autowired
    private FindPlzService findPlzService;

    @GetMapping(value = "/init")
    public ModelAndView init(Integer currPage){
        logger.info("求片页面 -> currPage:[{}]",currPage);
        currPage = (currPage == null || currPage == 0)?1:currPage;
        FindPlzBean findPlzBean = new FindPlzBean(currPage, CommonConstant.DEFAULT_LIMIT);
        int count = findPlzService.getFindPlzCount(findPlzBean);
        List<FindPlz> findPlzList = findPlzService.searchList(findPlzBean);
        return new ModelAndView("find_plz")
                .addObject("findPlzList",findPlzList)
                .addObject("currPage",currPage)
                .addObject("count",count);
    }

    @PostMapping(value = "/save")
    public ModelAndView save(FindPlzBean findPlzBean){
        logger.info("留言求片:" + JSON.toJSONString(findPlzBean));
        findPlzService.insertFindPlz(findPlzBean);
        return new ModelAndView("redirect:/");
    }
}
