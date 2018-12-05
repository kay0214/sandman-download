/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.sandman.download.base.BaseController;
import com.sandman.download.dao.mysql.system.model.auto.User;
import com.sandman.download.utils.SessionUtils;
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

    @GetMapping(value = "/")
    public ModelAndView init(){
        User user = new User();
        user.setUsername("sunpeikai");
        user.setPassword("asdasd");
        logger.info("从controller进入");
        SessionUtils.setSessionAttribute("message","this is index page");
        //SessionUtils.setSessionAttribute("user",user);
        return new ModelAndView("/index").addObject("hello","hello me");
    }
}
