/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.sandman.download.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sunpeikai
 * @version RegisterController, v0.1 2018/11/26 16:22
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController extends BaseController {

    @GetMapping(value = "/init")
    public ModelAndView init(){
        logger.info("entry register view");
        return new ModelAndView("/register");
    }
}
