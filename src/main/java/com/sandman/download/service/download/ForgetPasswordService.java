/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.download;

import com.sandman.download.base.BaseService;
import com.sandman.download.dao.mysql.system.model.auto.User;

/**
 * @author sunpeikai
 * @version ForgetPasswordService, v0.1 2019/1/8 10:16
 */
public interface ForgetPasswordService extends BaseService {
    /**
     * 发送验证码邮件
     * @auth sunpeikai
     * @param
     * @return
     */
    boolean sendForgetPasswordEmail(User user);
}
