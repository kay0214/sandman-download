/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.dao.mysql.system.model.auto.User;
import com.sandman.download.service.system.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sunpeikai
 * @version RegisterController, v0.1 2018/11/26 16:22
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController extends BaseController {

    @Autowired
    private RegisterService registerService;

    /**
     * 进入注册页面
     * @auth sunpeikai
     * @param
     * @return
     */
    @GetMapping(value = "/init")
    public ModelAndView init(){
        logger.info("entry register view");
        return new ModelAndView("/register");
    }

    /**
     * 查询username是否已被占用
     * @auth sunpeikai
     * @param
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/username_valid")
    public BaseResult usernameValid(String username){
        User user = registerService.getUserByUsername(username);
        boolean valid = (user == null);
        return new BaseResult(valid);
    }
}
