/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.admin.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.dao.mysql.download.model.auto.ResourceLogExample;
import com.sandman.download.dao.mysql.system.model.auto.UserExample;
import com.sandman.download.dao.mysql.system.model.auto.UserLoginLogExample;
import com.sandman.download.service.admin.AdminService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author sunpeikai
 * @version AdminServiceImpl, v0.1 2019/1/10 11:11
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl implements AdminService {

    /**
     * 根据日期获取上传资源数量
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getUploadCount(Date start, Date end) {
        ResourceLogExample resourceLogExample = new ResourceLogExample();
        resourceLogExample.createCriteria().andTypeEqualTo(1).andCreateTimeBetween(start, end);
        return resourceLogMapper.countByExample(resourceLogExample);
    }

    /**
     * 根据日期获取下载资源数量
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getDownloadCount(Date start, Date end) {
        ResourceLogExample resourceLogExample = new ResourceLogExample();
        resourceLogExample.createCriteria().andTypeEqualTo(2).andCreateTimeBetween(start, end);
        return resourceLogMapper.countByExample(resourceLogExample);
    }

    /**
     * 根据日期获取活跃用户数量
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getActiveCount(Date start, Date end) {
        UserLoginLogExample userLoginLogExample = new UserLoginLogExample();
        userLoginLogExample.createCriteria().andLoginTimeBetween(start, end);
        return userLoginLogMapper.countByExample(userLoginLogExample);
    }

    /**
     * 根据日期获取注册用户数量
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getRegisterCount(Date start, Date end) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAvailableEqualTo(1).andRoleNotEqualTo(0).andRegTimeBetween(start, end);
        return userMapper.countByExample(userExample);
    }
}
