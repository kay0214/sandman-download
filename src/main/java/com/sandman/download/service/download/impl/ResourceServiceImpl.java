/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.download.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.bean.download.ResourceBean;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.dao.mysql.download.model.auto.ResourceExample;
import com.sandman.download.service.download.ResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunpeikai
 * @version ResourceServiceImpl, v0.1 2018/12/6 18:02
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl implements ResourceService {

    /**
     * 获取资源信息
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<Resource> getResource(ResourceBean resourceBean) {
        ResourceExample resourceExample = new ResourceExample();
        ResourceExample.Criteria criteria = resourceExample.createCriteria();
        if(resourceBean.getLimitStart()>0){
            resourceExample.setLimitStart(resourceBean.getLimitStart());
            resourceExample.setLimitEnd(resourceBean.getLimitEnd());
        }
        return resourceMapper.selectByExample(resourceExample);
    }
}
