/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.system.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.bean.system.RegisterBean;
import com.sandman.download.config.SystemConfig;
import com.sandman.download.constant.MessageTemplateConstant;
import com.sandman.download.dao.mysql.system.model.auto.*;
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
        user.setIconUrl(SystemConfig.getSandmanIconDefault());
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
    public boolean sendActiveEmail(User user) {
        // 先删除所有跟email有关的验证码
        deleteByContact(user.getEmail());
        // 重新写入验证码表
        ValidateCode validateCode = new ValidateCode();
        validateCode.setContact(user.getEmail());
        validateCode.setCode(RandomUtils.getValidateCode());
        // 设置过期时间为48小时后
        validateCode.setDeadLine(DateUtils.getHoursAfter(new Date(),48));
        // 0:此验证码无效,1:此验证码有效
        validateCode.setValid(1);
        // 0:未发送,1:已发送
        validateCode.setSend(0);
        validateCode.setCreateTime(new Date());
        validateCode.setUpdateTime(new Date());
        validateCode.setDelFlag(0);
        validateCodeMapper.insertSelective(validateCode);
        //拼装激活链接
        String activeUrl = SystemConfig.getServerHost() + "/register/active_account?userId=" + user.getUserId() + "&validateCode=" + validateCode.getCode();
        //获取EMAIL注册模板
        Template emailTemplate = getTemplateByCode(MessageTemplateConstant.TPL_EMAIL_ACTIVE);
        Map<String,String> replace = new HashMap<>();
        replace.put("recipient",user.getUsername());
        replace.put("emailCode",validateCode.getCode());
        replace.put("activeUrl",activeUrl);
        return EmailSendUtils.sendEmail("注册",EmailSendUtils.emailContentReplace(emailTemplate.getTplContent(),replace),user.getEmail());
    }
}
