/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.bean.system.LoginBean;
import com.sandman.download.constant.CommonConstant;
import com.sandman.download.constant.ReturnMessage;
import com.sandman.download.dao.mysql.system.model.auto.User;
import com.sandman.download.service.system.LoginService;
import com.sandman.download.utils.PasswordEncrypt;
import com.sandman.download.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sunpeikai
 * @version LoginController, v0.1 2018/12/5 11:11
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/init")
    public ModelAndView init(){
        return new ModelAndView("/login");
    }

    @ResponseBody
    @PostMapping(value = "/login")
    public BaseResult login(@RequestBody LoginBean loginBean){
        logger.info("login -> username:[{}],password:[{}]",loginBean.getUsername(),loginBean.getPassword());
        User user = loginService.getUserByUsername(loginBean.getUsername());
        if(user != null){
            // 该用户存在，验证密码正确性
            String passwordEncrypt = PasswordEncrypt.getPasswordEncrypt(loginBean.getPassword(),user.getSalt());
            if(passwordEncrypt.equals(user.getPassword())){
                logger.info("与数据库密码一致，允许登录");
                SessionUtils.setSessionAttribute("user",user);
                SessionUtils.setSessionExpireTime(CommonConstant.LOGIN_EXPIRE);
                return new BaseResult(ReturnMessage.SUCCESS_USER_LOGIN);
            }else{
                logger.info("与数据库密码不一致，不允许登录");
                return new BaseResult(ReturnMessage.ERR_PASSWORD);
            }
        }else{
            logger.info("该用户不存在");
            return new BaseResult(ReturnMessage.ERR_USER_NOT_EXIST);
        }

    }
}
