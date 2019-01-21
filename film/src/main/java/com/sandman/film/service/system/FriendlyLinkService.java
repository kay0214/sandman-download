/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.system;

import com.sandman.film.base.BaseService;
import com.sandman.film.dao.mysql.system.model.auto.FriendlyLink;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

/**
 * @author sunpeikai
 * @version FriendlyLinkService, v0.1 2019/1/9 15:00
 */
//@CacheConfig(cacheNames = "friendly")
public interface FriendlyLinkService extends BaseService {

    /**
     * 查询友情链接
     * @auth sunpeikai
     * @param
     * @return
     */
    List<FriendlyLink> getFriendlyLinkList();
}
