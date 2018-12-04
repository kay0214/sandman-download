/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.base;

import com.sandman.download.dao.mysql.system.model.auto.Template;
import com.sandman.download.dao.mysql.system.model.auto.User;
import com.sandman.download.dao.mysql.system.model.auto.ValidateCode;

/**
 * @author sunpeikai
 * @version BaseService, v0.1 2018/12/3 11:56
 */
public interface BaseService {
    /**
     * 根据username查询user信息
     * @auth sunpeikai
     * @param
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 根据code获取信息模板
     * @auth sunpeikai
     * @param
     * @return
     */
    Template getTemplateByCode(String tplCode);

    /**
     * 根据contact查询验证码
     * @auth sunpeikai
     * @param
     * @return
     */
    ValidateCode getValidateCodeByContact(String contact);
}
