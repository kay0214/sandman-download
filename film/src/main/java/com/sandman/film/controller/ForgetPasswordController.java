/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.controller;

import com.sandman.film.base.BaseController;
import com.sandman.film.base.BaseResult;
import com.sandman.film.constant.ReturnMessage;
import com.sandman.film.dao.mysql.system.model.auto.User;
import com.sandman.film.dao.mysql.system.model.auto.ValidateCode;
import com.sandman.film.service.film.ForgetPasswordService;
import com.sandman.film.utils.DateUtils;
import com.sandman.film.utils.PasswordEncrypt;
import com.sandman.film.utils.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @author sunpeikai
 * @version ForgetPasswordController, v0.1 2019/1/7 17:43
 */
@Controller
@RequestMapping(value = "/forget")
public class ForgetPasswordController extends BaseController {

    @Autowired
    private ForgetPasswordService forgetPasswordService;

    @GetMapping(value = "/init")
    public ModelAndView init(){

        return new ModelAndView("forget");
    }

    @ResponseBody
    @PostMapping(value = "/send_email")
    public BaseResult sendEmail(String username){
        logger.info("忘记密码 -> username:[{}]",username);
        User user = forgetPasswordService.getUserByUsername(username);
        if(user != null){
            // 用户存在就发送邮箱验证码
            boolean isSend = forgetPasswordService.sendForgetPasswordEmail(user);
            logger.info("isSend:[{}]",isSend);
            if(isSend){
                // 更新验证码状态为已发送
                ValidateCode forget = forgetPasswordService.getValidateCodeByContact(user.getEmail());
                forget.setSend(1);
                forgetPasswordService.updateValidateCode(forget);
                return new BaseResult(ReturnMessage.SUCCESS_EMAIL_SEND);
            }
            //邮件发送失败
            return new BaseResult(ReturnMessage.ERR_EMAIL_SEND);
        }else{
            // 用户不存在
            return new BaseResult(ReturnMessage.ERR_USER_NOT_EXIST);
        }
    }

    @ResponseBody
    @PostMapping(value = "/right_code")
    public BaseResult rightCode(String username,String code){
        logger.info("校验邮箱验证码正确性 -> username:[{}],code:[{}]",username,code);
        // 待验证
        User user = forgetPasswordService.getUserByUsername(username);
        if(user != null){
            ValidateCode forget = forgetPasswordService.getValidateCodeByContact(user.getEmail());
            if(forget != null){
                // 判断是否过期
                if(!DateUtils.beforeNow(forget.getDeadLine())){
                    // 截止日期在当前日前之后，未过期。检查验证码是否一致
                    if(code.equals(forget.getCode())){
                        // 返回成功,更新验证码状态为2:已验证通过
                        forget.setValid(2);
                        forgetPasswordService.updateValidateCode(forget);
                        return new BaseResult();
                    }
                    // 激活码不正确
                    return new BaseResult(ReturnMessage.ERR_VALIDATE_CODE);
                }
                // 激活码已过期
                return new BaseResult(ReturnMessage.ERR_VALIDATE_CODE_EXPIRED);
            }
            // 激活码不存在
            return new BaseResult(ReturnMessage.ERR_VALIDATE_CODE_NOT_EXIST);
        }
        // 用户不存在
        return new BaseResult(ReturnMessage.ERR_USER_NOT_EXIST);
    }

    @ResponseBody
    @PostMapping(value = "/modify")
    public BaseResult modify(String username,String password){
        logger.info("开始修改登录密码 -> username:[{}],password:[{}]",username,password);
        if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
            User user = forgetPasswordService.getUserByUsername(username);
            if(user != null){
                // 检查是否通过验证码验证
                ValidateCode validateCode = forgetPasswordService.getValidateCodeByContact(user.getEmail());
                if(validateCode != null && validateCode.getValid()==2){
                    // 验证码不为空且状态为2:已验证通过
                    user.setSalt(RandomUtils.getRandomCode(6));
                    user.setPassword(PasswordEncrypt.getPasswordEncrypt(password,user.getSalt()));
                    user.setUpdateTime(new Date());
                    int result = forgetPasswordService.updateUser(user);
                    if(result>0){
                        // 更新密码成功,删除验证码数据
                        forgetPasswordService.deleteByContact(user.getEmail());
                        return new BaseResult(ReturnMessage.SUCCESS_PASSWORD_MODIFY);
                    }else{
                        return new BaseResult(ReturnMessage.ERR_PASSWORD_MODIFY);
                    }
                }
                // 验证还未通过
                return new BaseResult(ReturnMessage.ERR_VALIDATE_CODE_NOT_PASS);
            }
            // 用户不存在
            return new BaseResult(ReturnMessage.ERR_USER_NOT_EXIST);
        }
        return new BaseResult(ReturnMessage.ERR_OBJECT_REQUIRED,"用户名或密码");
    }
}
