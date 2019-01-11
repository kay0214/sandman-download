/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.admin;

import com.sandman.download.base.BaseService;
import com.sandman.download.dao.mysql.system.model.auto.User;

import java.util.List;

/**
 * @author sunpeikai
 * @version UserManagerService, v0.1 2019/1/10 17:23
 */
public interface UserManagerService extends BaseService {

    /**
     * 获取用户信息总数
     * @auth sunpeikai
     * @param
     * @return
     */
    int getUserCount();
    /**
     * 分页获取用户信息
     * @auth sunpeikai
     * @param
     * @return
     */
    List<User> searchList(Integer page,Integer limit);
}
