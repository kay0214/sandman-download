/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sunpeikai
 * @version TestController, v0.1 2019/3/20 11:00
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/test")
    public ModelAndView test(){
        return new ModelAndView("admin/user_manager2");
    }
}
