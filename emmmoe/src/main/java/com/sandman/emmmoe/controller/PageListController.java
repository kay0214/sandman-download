/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.controller;

import com.sandman.emmmoe.bean.BaseController;
import com.sandman.emmmoe.service.EmmmoeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sunpeikai
 * @version PageListController, v0.1 2018/12/19 16:04
 */
@Controller
@RequestMapping(value = "/")
public class PageListController extends BaseController {

    @Autowired
    private EmmmoeService emmmoeService;

    @GetMapping(value = "/")
    public ModelAndView init(){
        logger.info("entry the index");
        return new ModelAndView("/index");
    }

    @GetMapping(value = "/getPageList")
    public ModelAndView getPageList(String emmmoe){
        emmmoe = (StringUtils.isNotBlank(emmmoe))?emmmoe:"https://qwq.emm.moe";
        logger.info("emmmoe:[{}]",emmmoe);
        int result = emmmoeService.getEmmmoePageList(emmmoe);
        return new ModelAndView("/index").addObject("count",result);
    }
}
