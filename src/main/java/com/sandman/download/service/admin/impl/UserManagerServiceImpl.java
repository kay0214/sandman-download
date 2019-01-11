/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.admin.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.dao.mysql.system.model.auto.User;
import com.sandman.download.dao.mysql.system.model.auto.UserExample;
import com.sandman.download.service.admin.UserManagerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunpeikai
 * @version UserManagerServiceImpl, v0.1 2019/1/10 17:24
 */
@Service
public class UserManagerServiceImpl extends BaseServiceImpl implements UserManagerService {

    /**
     * 获取用户信息总数
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getUserCount() {
        UserExample userExample = new UserExample();
        // 未被删除且不是管理员用户
        userExample.createCriteria().andDelFlagEqualTo(0).andRoleNotEqualTo(0);
        return userMapper.countByExample(userExample);
    }

    /**
     * 分页获取用户信息
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<User> searchList(Integer page, Integer limit) {
        computePage(page, limit);
        UserExample userExample = new UserExample();
        // 注册时间倒叙
        userExample.setOrderByClause("reg_time desc");
        userExample.setLimitStart(limitStart);
        userExample.setLimitEnd(limitEnd);
        // 未被删除且不是管理员用户
        userExample.createCriteria().andDelFlagEqualTo(0).andRoleNotEqualTo(0);
        return userMapper.selectByExample(userExample);
    }
}
