/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.alibaba.fastjson.JSON;
import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.bean.system.RegisterBean;
import com.sandman.download.constant.ReturnMessage;
import com.sandman.download.dao.mysql.system.model.auto.User;
import com.sandman.download.dao.mysql.system.model.auto.ValidateCode;
import com.sandman.download.service.system.RegisterService;
import com.sandman.download.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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

    /**
     * 查询email是否已被占用
     * @auth sunpeikai
     * @param
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/email_valid")
    public BaseResult emailValid(String email){
        User user = registerService.getUserByEmail(email);
        boolean valid = (user == null);
        return new BaseResult(valid);
    }

    /**
     * 注册
     * @auth sunpeikai
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/register")
    public BaseResult register(@RequestBody RegisterBean registerBean, HttpServletRequest request){
        int register = registerService.register(registerBean,request);
        if(register>0){
            // 创建账号成功
            User user = registerService.getUserByEmail(registerBean.getEmail());
            logger.info("用户创建成功 -> user:[{}]", JSON.toJSONString(user));
            boolean isSend = registerService.sendActiveEmail(user);
            if(isSend){
                // 更新验证码状态为已发送
                ValidateCode validateCode = registerService.getValidateCodeByContact(user.getEmail());
                validateCode.setSend(1);
                registerService.updateValidateCode(validateCode);
                return new BaseResult(ReturnMessage.SUCESS_EMAIL_SEND);
            }
            //邮件发送失败
            registerService.deleteUserByEmail(user.getEmail());
            return new BaseResult(ReturnMessage.ERR_EMAIL_SEND);
        }else{
            // 用户注册失败
            return new BaseResult(ReturnMessage.ERR_USER_REGISTER);
        }
    }

    /**
     * 激活账户
     * @auth sunpeikai
     * @param
     * @return
     */
    @GetMapping(value = "/active_account")
    public ModelAndView activeAccount(Integer userId,String validateCode){
        logger.info("激活账户 -> userId:[{}],validateCode:[{}]",userId,validateCode);
        ModelAndView modelAndView = new ModelAndView();
        User user = registerService.getUserByUserId(userId);
        // 待激活的用户存在
        if(user != null){
            ValidateCode activeCode = registerService.getValidateCodeByContact(user.getEmail());
            // 验证码有效性检查
            if(codeValid(activeCode,validateCode)){
                user.setAvailable(1);
                user.setUpdateTime(new Date());
                // 激活用户账户
                int result = registerService.updateUserByUserId(user);
                if(result>0){
                    // 激活成功
                    registerService.deleteByContact(activeCode.getContact());
                    modelAndView.addObject("message","激活成功");
                    modelAndView.setViewName("/common/success");
                    return modelAndView;
                }
            }
            // 激活链接失效
            modelAndView.addObject("message","激活链接失效");
            modelAndView.setViewName("/common/error");
            return modelAndView;
        }
        modelAndView.addObject("message","用户不存在");
        modelAndView.setViewName("/common/error");
        return modelAndView;
    }

    /**
     * 该验证码有效
     * @auth sunpeikai
     * @param
     * @return
     */
    private boolean codeValid(ValidateCode activeCode,String validateCode){
        // 验证信息存在
        if(activeCode != null){
            // 验证码在有效期
            if(activeCode.getValid()==1 && DateUtils.beforeNow(activeCode.getDeadLine())){
                //两验证码相同
                if(validateCode.equals(activeCode.getCode())){
                    return true;
                }
            }else{
                // 验证码不在有效期
                registerService.deleteByContact(activeCode.getContact());
            }
        }
        return false;
    }
}
