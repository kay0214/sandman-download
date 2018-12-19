/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.download.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.dao.mysql.download.model.auto.ResourceLog;
import com.sandman.download.dao.mysql.download.model.auto.ResourceLogExample;
import com.sandman.download.service.download.DownloadService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author sunpeikai
 * @version DownloadServiceImpl, v0.1 2018/12/12 16:22
 */
@Service
public class DownloadServiceImpl extends BaseServiceImpl implements DownloadService {
    /**
     * 根据resourceId 和 userId查询是否存在记录
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public ResourceLog getResourceLogByResourceIdAndUserId(Integer resourceId, Integer userId) {
        ResourceLogExample resourceLogExample = new ResourceLogExample();
        resourceLogExample.createCriteria().andResourceIdEqualTo(resourceId).andUserIdEqualTo(userId).andDelFlagEqualTo(0).andTypeEqualTo(2);
        List<ResourceLog> resourceLogList = resourceLogMapper.selectByExample(resourceLogExample);
        if(!CollectionUtils.isEmpty(resourceLogList)){
            return resourceLogList.get(0);
        }
        return null;
    }
}
