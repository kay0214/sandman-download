/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.download;

import com.sandman.download.base.BaseService;
import com.sandman.download.dao.mysql.download.model.auto.ResourceLog;

/**
 * @author sunpeikai
 * @version DownloadService, v0.1 2018/12/12 16:22
 */
public interface DownloadService extends BaseService {

    /**
     * 根据resourceId 和 userId查询是否存在记录
     * @auth sunpeikai
     * @param
     * @return
     */
    ResourceLog getResourceLogByResourceIdAndUserId(Integer resourceId,Integer userId);

}
