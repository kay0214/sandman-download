/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.controller;

import com.sandman.emmmoe.base.BaseResult;
import com.sandman.emmmoe.bean.BaseController;
import com.sandman.emmmoe.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sunpeikai
 * @version LoginController, v0.1 2018/12/5 11:11
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {

    @GetMapping(value = "/init")
    public ModelAndView init(){
        return new ModelAndView("login");
    }

    @ResponseBody
    @PostMapping(value = "/login")
    public BaseResult login(@RequestBody User user, HttpServletRequest request){
        logger.info("login -> username:[{}],password:[{}]",user.getUsername(),user.getPassword());
        if("sunpeikai".equals(user.getUsername()) && "spkzq521".equals(user.getPassword())){
            request.getSession().setAttribute("user",user);
            return new BaseResult(0,"登录成功");
        }
        return new BaseResult(999,"登录失败");
    }

}
