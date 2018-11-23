/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.sandman.download.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sunpeikai
 * @version IndexController, v0.1 2018/11/23 14:47
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController extends BaseController {

    @RequestMapping(value = "/init")
    public ModelAndView init(){
        logger.info("从controller进入");
        return new ModelAndView("/index");
    }
}
