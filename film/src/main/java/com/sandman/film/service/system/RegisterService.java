/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.system;

import com.sandman.film.base.BaseService;
import com.sandman.film.bean.system.RegisterBean;
import com.sandman.film.dao.mysql.system.model.auto.User;

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
}
