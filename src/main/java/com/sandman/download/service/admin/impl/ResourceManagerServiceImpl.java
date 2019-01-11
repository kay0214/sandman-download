/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.admin.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.dao.mysql.download.model.auto.ResourceExample;
import com.sandman.download.service.admin.ResourceManagerService;
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
    public int getResourceCount() {
        ResourceExample resourceExample = new ResourceExample();
        resourceExample.createCriteria().andDelFlagEqualTo(0);
        return resourceMapper.countByExample(resourceExample);
    }

    /**
     * 分页获取资源列表
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<Resource> searchList(Integer page, Integer limit) {
        ResourceExample resourceExample = new ResourceExample();
        resourceExample.setOrderByClause("create_time desc");
        computePage(page, limit);
        resourceExample.setLimitStart(limitStart);
        resourceExample.setLimitEnd(limitEnd);
        resourceExample.createCriteria().andDelFlagEqualTo(0);
        return resourceMapper.selectByExample(resourceExample);
    }
}
