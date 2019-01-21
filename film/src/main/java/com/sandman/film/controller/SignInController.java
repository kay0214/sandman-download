/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.controller;

import com.sandman.film.base.BaseController;
import com.sandman.film.base.BaseResult;
import com.sandman.film.constant.ReturnMessage;
import com.sandman.film.service.system.SignInService;
import com.sandman.film.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author sunpeikai
 * @version SignInController, v0.1 2019/1/16 17:31
 */
@Controller
@RequestMapping(value = "/sign_in")
public class SignInController extends BaseController {

    @Autowired
    private SignInService signInService;

    //根据userId去做ehcache缓存
    //永久缓存，点击签到以后删除并重新缓存,Date转String操作在缓存service中做
    @ResponseBody
    @GetMapping(value = "/get_sign")
    public BaseResult getSign(){
        Map<String,String> result = signInService.getSignInfo(SessionUtils.getUserId());
        return new BaseResult(result);
    }

    @ResponseBody
    @GetMapping(value = "/sign")
    public BaseResult sign(){
        boolean result = signInService.signByUserId(SessionUtils.getUserId());
        SessionUtils.setSessionAttribute("sign",result);
        if(result){
            return new BaseResult(ReturnMessage.SUCCESS_USER_SIGN_IN);
        }else{
            return new BaseResult(ReturnMessage.ERR_USER_SIGN_IN);
        }
    }
}
