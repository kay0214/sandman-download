/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.controller;

import com.sandman.emmmoe.bean.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sunpeikai
 * @version IndexController, v0.1 2018/12/20 10:51
 */
@Controller
@RequestMapping(value = "/")
public class IndexController extends BaseController {

    @GetMapping(value = "/")
    public ModelAndView init(){
        logger.info("entry the index");
        return new ModelAndView("index");
    }
}
