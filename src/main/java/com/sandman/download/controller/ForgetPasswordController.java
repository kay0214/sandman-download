/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sunpeikai
 * @version ForgetPasswordController, v0.1 2019/1/7 17:43
 */
@Controller
@RequestMapping(value = "/forget")
public class ForgetPasswordController extends BaseController {

    @GetMapping(value = "/init")
    public ModelAndView init(){

        return new ModelAndView("/forget");
    }

    @ResponseBody
    @PostMapping(value = "/send_email")
    public BaseResult sendEmail(String username){
        logger.info("忘记密码 -> username:[{}]",username);
        return new BaseResult(BaseResult.FAIL,BaseResult.FAIL_DESC);
    }
}
