/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.admin;

import com.sandman.download.base.BaseService;
import com.sandman.download.bean.admin.ResourceManagerRequest;
import com.sandman.download.dao.mysql.download.model.auto.Resource;

import java.util.List;

/**
 * @author sunpeikai
 * @version ResourceManagerService, v0.1 2019/1/10 17:26
 */
public interface ResourceManagerService extends BaseService {

    /**
     * 获取资源总数
     * @auth sunpeikai
     * @param
     * @return
     */
    int getResourceCount(ResourceManagerRequest resourceManagerRequest);

    /**
     * 分页获取资源列表
     * @auth sunpeikai
     * @param
     * @return
     */
    List<Resource> searchList(ResourceManagerRequest resourceManagerRequest);
}
