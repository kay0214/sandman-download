/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.system;

import com.sandman.download.base.BaseService;
import com.sandman.download.bean.system.RegisterBean;
import com.sandman.download.dao.mysql.system.model.auto.User;
import com.sandman.download.dao.mysql.system.model.auto.ValidateCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sunpeikai
 * @version RegisterService, v0.1 2018/12/3 12:17
 */
public interface RegisterService extends BaseService {
    /**
     * 根据email查询user信息
     * @auth sunpeikai
     * @param
     * @return
     */
    User getUserByEmail(String email);

    /**
     * 创建一个账号
     * @auth sunpeikai
     * @param
     * @return
     */
    int register(RegisterBean registerBean, HttpServletRequest request);

    /**
     * 发送激活邮件
     * @auth sunpeikai
     * @param
     * @return
     */
    boolean sendActiveEmail(User user);

    /**
     * 更新验证码状态为 1 已发送
     * @auth sunpeikai
     * @param
     * @return
     */
    void updateValidateCode(ValidateCode validateCode);

    /**
     * 根据邮箱删除一个账户
     * @auth sunpeikai
     * @param
     * @return
     */
    int deleteUserByEmail(String email);
}
