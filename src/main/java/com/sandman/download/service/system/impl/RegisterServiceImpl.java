/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.system.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.bean.system.RegisterBean;
import com.sandman.download.constant.MessageTemplateConstant;
import com.sandman.download.service.system.RegisterService;
import com.sandman.download.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunpeikai
 * @version RegisterServiceImpl, v0.1 2018/12/3 12:17
 */
@Service
public class RegisterServiceImpl extends BaseServiceImpl implements RegisterService {
    /**
     * 根据email查询user信息
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public User getUserByEmail(String email) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(email);
        List<User> userList = userMapper.selectByExample(userExample);
        if(!CollectionUtils.isEmpty(userList)){
            return userList.get(0);
        }
        return null;
    }
    /**
     * 创建一个账号
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int register(RegisterBean registerBean, HttpServletRequest request) {
        User user = BeanUtils.convertBean(registerBean,User.class);
        // 激活后置为1
        user.setAvailable(0);
        // 加密密码盐
        user.setSalt(RandomUtils.getRandomCode(6));
        user.setPassword(PasswordEncrypt.getPasswordEncrypt(user.getPassword(),user.getSalt()));
        user.setGold(0);
        // 普通用户
        user.setRole(1);
        user.setRegIp(ClientIpAddress.getIpAddress(request));
        user.setRegTime(new Date());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setDelFlag(0);
        return userMapper.insertSelective(user);
    }
    /**
     * 发送激活邮件
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public boolean sendActiveEmail(String email) {
        //获取EMAIL注册模板
        Template emailTemplate = getTemplateByCode(MessageTemplateConstant.TPL_EMAIL_REGISTER);
        Map<String,String> replace = new HashMap<>();
        replace.put("recipient","孙沛凯");
        replace.put("emailCode",RandomUtils.getValidateCode());
        return EmailSendUtils.sendEmail("注册",EmailSendUtils.emailContentReplace(emailTemplate.getTplContent(),replace),email);
    }

    /**
     * 根据邮箱删除一个账户
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int deleteUserByEmail(String email) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(email);
        return userMapper.deleteByExample(userExample);
    }
}
