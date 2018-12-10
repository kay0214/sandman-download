/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.download.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.bean.download.MyResourceBean;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.dao.mysql.download.model.auto.ResourceExample;
import com.sandman.download.service.download.MyResourceService;
import com.sandman.download.utils.SessionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunpeikai
 * @version MyResourceServiceImpl, v0.1 2018/12/10 13:59
 */
@Service
public class MyResourceServiceImpl extends BaseServiceImpl implements MyResourceService {

    /**
     * 获取我的资源列表
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<Resource> getMyResourceList(MyResourceBean myResourceBean) {
        ResourceExample resourceExample = convertExample(myResourceBean);
        return resourceMapper.selectByExample(resourceExample);
    }

    /**
     * 获取我的资源列表count
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getMyResourceListCount(MyResourceBean myResourceBean) {
        ResourceExample resourceExample = convertExample(myResourceBean);
        return resourceMapper.countByExample(resourceExample);
    }

    private ResourceExample convertExample(MyResourceBean myResourceBean){
        ResourceExample resourceExample = new ResourceExample();
        ResourceExample.Criteria criteria = resourceExample.createCriteria();
        if(null != myResourceBean.getUserId()){
            criteria.andUserIdEqualTo(myResourceBean.getUserId());
        }
        if(myResourceBean.getLimitStart() >= 0){
            resourceExample.setLimitStart(myResourceBean.getLimitStart());
            resourceExample.setLimitEnd(myResourceBean.getLimitEnd());
        }
        if(null != myResourceBean.getResourceName()){
            criteria.andResourceNameLike("%" + myResourceBean.getResourceName() + "%");
        }
        if(null != myResourceBean.getStatus()){
            criteria.andStatusEqualTo(myResourceBean.getStatus());
        }
        criteria.andDelFlagEqualTo(0);
        resourceExample.setOrderByClause("create_time desc");
        return resourceExample;
    }
}
