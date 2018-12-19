/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.controller;

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
public class PageListController {

    @GetMapping(value = "/")
    public ModelAndView init(){

        return new ModelAndView("");
    }
}
