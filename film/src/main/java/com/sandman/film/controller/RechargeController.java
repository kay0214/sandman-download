/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.controller;

import com.sandman.film.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sunpeikai
 * @version RechargeController, v0.1 2019/1/14 19:28
 */
@Controller
@RequestMapping(value = "/recharge")
public class RechargeController extends BaseController {

    @GetMapping(value = "/init")
    public ModelAndView init(){

        return new ModelAndView("recharge");
    }
}
