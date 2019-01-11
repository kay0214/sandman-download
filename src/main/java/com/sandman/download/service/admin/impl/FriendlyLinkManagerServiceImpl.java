/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.admin.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.dao.mysql.system.model.auto.FriendlyLink;
import com.sandman.download.dao.mysql.system.model.auto.FriendlyLinkExample;
import com.sandman.download.service.admin.FriendlyLinkManagerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunpeikai
 * @version FriendlyLinkManagerServiceImpl, v0.1 2019/1/10 17:32
 */
@Service
public class FriendlyLinkManagerServiceImpl extends BaseServiceImpl implements FriendlyLinkManagerService {

    /**
     * 友情链接count
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getFriendlyLinkCount() {
        FriendlyLinkExample friendlyLinkExample = new FriendlyLinkExample();
        friendlyLinkExample.createCriteria().andDelFlagEqualTo(0);
        return friendlyLinkMapper.countByExample(friendlyLinkExample);
    }

    /**
     * 分页获取友情链接列表
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<FriendlyLink> searchList(Integer page, Integer limit) {
        FriendlyLinkExample friendlyLinkExample = new FriendlyLinkExample();
        friendlyLinkExample.setOrderByClause("order_no asc");
        computePage(page, limit);
        friendlyLinkExample.setLimitStart(limitStart);
        friendlyLinkExample.setLimitEnd(limitEnd);
        friendlyLinkExample.createCriteria().andDelFlagEqualTo(0);
        return friendlyLinkMapper.selectByExample(friendlyLinkExample);
    }

    /**
     * 根据id获取友情链接
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public FriendlyLink getFriendlyLinkById(Integer id) {
        return friendlyLinkMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新友情链接
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int update(FriendlyLink friendlyLink) {
        return friendlyLinkMapper.updateByPrimaryKeySelective(friendlyLink);
    }
}
