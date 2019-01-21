/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.film.impl;

import com.sandman.film.base.BaseServiceImpl;
import com.sandman.film.config.SystemConfig;
import com.sandman.film.constant.MessageTemplateConstant;
import com.sandman.film.dao.mysql.system.model.auto.Template;
import com.sandman.film.dao.mysql.system.model.auto.User;
import com.sandman.film.dao.mysql.system.model.auto.ValidateCode;
import com.sandman.film.service.film.ForgetPasswordService;
import com.sandman.film.utils.DateUtils;
import com.sandman.film.utils.EmailSendUtils;
import com.sandman.film.utils.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sunpeikai
 * @version ForgetPasswordServiceImpl, v0.1 2019/1/8 10:16
 */
@Service
public class ForgetPasswordServiceImpl extends BaseServiceImpl implements ForgetPasswordService {
    /**
     * 发送激活邮件
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public boolean sendForgetPasswordEmail(User user) {
        // 先删除所有跟email有关的验证码
        deleteByContact(user.getEmail());
        // 重新写入验证码表
        ValidateCode validateCode = new ValidateCode();
        validateCode.setContact(user.getEmail());
        validateCode.setCode(RandomUtils.getValidateCode());
        // 设置过期时间为5分钟后
        validateCode.setDeadLine(DateUtils.getMinutesAfter(new Date(),5));
        // 0:此验证码无效,1:此验证码有效
        validateCode.setValid(1);
        // 0:未发送,1:已发送
        validateCode.setSend(0);
        validateCode.setCreateTime(new Date());
        validateCode.setUpdateTime(new Date());
        validateCode.setDelFlag(0);
        validateCodeMapper.insertSelective(validateCode);
        //拼装激活链接
        String code = validateCode.getCode();
        //获取EMAIL注册模板
        Template emailTemplate = getTemplateByCode(MessageTemplateConstant.TPL_EMAIL_FORGET_PASSWORD);
        Map<String,String> replace = new HashMap<>();
        replace.put("recipient",user.getUsername());
        replace.put("emailCode",validateCode.getCode());
        replace.put("code",code);
        logger.info("发送找回密码邮件,username:[{}],email:[{}],code:[{}],url:[{}]",user.getUsername(),user.getEmail(),validateCode.getCode());
        return EmailSendUtils.sendEmail("找回密码",EmailSendUtils.emailContentReplace(emailTemplate.getTplContent(),replace),user.getEmail());
    }
}
