/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.admin.impl;

import com.sandman.film.base.BaseServiceImpl;
import com.sandman.film.bean.admin.UserManagerRequest;
import com.sandman.film.dao.mysql.system.model.auto.User;
import com.sandman.film.dao.mysql.system.model.auto.UserExample;
import com.sandman.film.service.admin.UserManagerService;
import org.apache.commons.lang3.StringUtils;
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
    public int getUserCount(UserManagerRequest userManagerRequest) {
        UserExample userExample = convertExample(userManagerRequest);
        return userMapper.countByExample(userExample);
    }

    /**
     * 分页获取用户信息
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<User> searchList(UserManagerRequest userManagerRequest) {
        computePage(userManagerRequest.getPage(), userManagerRequest.getLimit());
        UserExample userExample = convertExample(userManagerRequest);
        // 注册时间倒叙
        userExample.setOrderByClause("reg_time desc");
        userExample.setLimitStart(limitStart);
        userExample.setLimitEnd(limitEnd);
        // 未被删除且不是管理员用户
        return userMapper.selectByExample(userExample);
    }

    private UserExample convertExample(UserManagerRequest userManagerRequest){
        UserExample userExample = new UserExample();
        // 未被删除且不是管理员用户
        UserExample.Criteria criteria = userExample.createCriteria().andDelFlagEqualTo(0).andRoleNotEqualTo(0);
        if(StringUtils.isNotBlank(userManagerRequest.getUsername())){
            criteria.andUsernameLike("%" + userManagerRequest.getUsername() + "%");
        }
        if(StringUtils.isNotBlank(userManagerRequest.getMobile())){
            criteria.andMobileLike("%" + userManagerRequest.getMobile() + "%");
        }
        if(StringUtils.isNotBlank(userManagerRequest.getEmail())){
            criteria.andEmailLike("%" + userManagerRequest.getEmail() + "%");
        }
        if(userManagerRequest.getAvailable() != null){
            criteria.andAvailableEqualTo(userManagerRequest.getAvailable());
        }
        if(userManagerRequest.getRole() != null){
            criteria.andRoleEqualTo(userManagerRequest.getRole());
        }
        return userExample;
    }
}
