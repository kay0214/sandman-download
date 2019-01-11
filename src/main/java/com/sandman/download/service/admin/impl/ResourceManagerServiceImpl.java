/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.admin.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.bean.admin.ResourceManagerRequest;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.dao.mysql.download.model.auto.ResourceExample;
import com.sandman.download.service.admin.ResourceManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunpeikai
 * @version ResourceManagerServiceImpl, v0.1 2019/1/10 17:27
 */
@Service
public class ResourceManagerServiceImpl extends BaseServiceImpl implements ResourceManagerService {

    /**
     * 获取资源总数
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getResourceCount(ResourceManagerRequest resourceManagerRequest) {
        ResourceExample resourceExample = convertExample(resourceManagerRequest);
        return resourceMapper.countByExample(resourceExample);
    }

    /**
     * 分页获取资源列表
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<Resource> searchList(ResourceManagerRequest resourceManagerRequest) {
        ResourceExample resourceExample = convertExample(resourceManagerRequest);
        resourceExample.setOrderByClause("create_time desc");
        computePage(resourceManagerRequest.getPage(),resourceManagerRequest.getLimit());
        resourceExample.setLimitStart(limitStart);
        resourceExample.setLimitEnd(limitEnd);
        return resourceMapper.selectByExample(resourceExample);
    }

    private ResourceExample convertExample(ResourceManagerRequest resourceManagerRequest){
        ResourceExample resourceExample = new ResourceExample();
        ResourceExample.Criteria criteria = resourceExample.createCriteria().andDelFlagEqualTo(0);
        if(StringUtils.isNotBlank(resourceManagerRequest.getUsername())){
            criteria.andUsernameLike("%" + resourceManagerRequest.getUsername() + "%");
        }
        if(StringUtils.isNotBlank(resourceManagerRequest.getResourceName())){
            criteria.andResourceNameLike("%" + resourceManagerRequest.getResourceName() + "%");
        }
        if(StringUtils.isNotBlank(resourceManagerRequest.getResourceDesc())){
            criteria.andResourceDescLike("%" + resourceManagerRequest.getResourceDesc() + "%");
        }
        if(resourceManagerRequest.getStatus()!=null){
            criteria.andStatusEqualTo(resourceManagerRequest.getStatus());
        }
        return resourceExample;
    }
}
