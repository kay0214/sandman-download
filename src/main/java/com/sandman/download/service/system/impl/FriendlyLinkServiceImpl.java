/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.system.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.dao.mysql.system.model.auto.FriendlyLink;
import com.sandman.download.dao.mysql.system.model.auto.FriendlyLinkExample;
import com.sandman.download.service.system.FriendlyLinkService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunpeikai
 * @version FriendlyLinkServiceImpl, v0.1 2019/1/9 15:00
 */
@Service
public class FriendlyLinkServiceImpl extends BaseServiceImpl implements FriendlyLinkService {

    /**
     * 查询友情链接
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    //@Cacheable(value = "friendlyCache")
    public List<FriendlyLink> getFriendlyLinkList() {
        FriendlyLinkExample friendlyLinkExample = new FriendlyLinkExample();
        friendlyLinkExample.setOrderByClause("order_no asc");
        friendlyLinkExample.createCriteria().andDelFlagEqualTo(0);
        return friendlyLinkMapper.selectByExample(friendlyLinkExample);
    }
}
