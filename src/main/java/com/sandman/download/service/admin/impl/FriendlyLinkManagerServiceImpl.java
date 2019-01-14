/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.admin.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.bean.admin.FriendlyLinkManagerRequest;
import com.sandman.download.dao.mysql.system.model.auto.FriendlyLink;
import com.sandman.download.dao.mysql.system.model.auto.FriendlyLinkExample;
import com.sandman.download.service.admin.FriendlyLinkManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public int getFriendlyLinkCount(FriendlyLinkManagerRequest friendlyLinkManagerRequest) {
        FriendlyLinkExample friendlyLinkExample = convertExample(friendlyLinkManagerRequest);
        return friendlyLinkMapper.countByExample(friendlyLinkExample);
    }

    /**
     * 分页获取友情链接列表
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<FriendlyLink> searchList(FriendlyLinkManagerRequest friendlyLinkManagerRequest) {
        FriendlyLinkExample friendlyLinkExample = convertExample(friendlyLinkManagerRequest);
        friendlyLinkExample.setOrderByClause("order_no asc");
        computePage(friendlyLinkManagerRequest.getPage(), friendlyLinkManagerRequest.getLimit());
        friendlyLinkExample.setLimitStart(limitStart);
        friendlyLinkExample.setLimitEnd(limitEnd);
        return friendlyLinkMapper.selectByExample(friendlyLinkExample);
    }

    private FriendlyLinkExample convertExample(FriendlyLinkManagerRequest friendlyLinkManagerRequest){
        FriendlyLinkExample friendlyLinkExample = new FriendlyLinkExample();
        FriendlyLinkExample.Criteria criteria = friendlyLinkExample.createCriteria().andDelFlagEqualTo(0);
        if(StringUtils.isNotBlank(friendlyLinkManagerRequest.getLinkName())){
            criteria.andLinkNameLike("%" + friendlyLinkManagerRequest.getLinkName() + "%");
        }
        if(StringUtils.isNotBlank(friendlyLinkManagerRequest.getLinkUrl())){
            criteria.andLinkUrlLike("%" + friendlyLinkManagerRequest.getLinkUrl() + "%");
        }
        if(friendlyLinkManagerRequest.getStatus()!=null){
            criteria.andStatusEqualTo(friendlyLinkManagerRequest.getStatus());
        }
        return friendlyLinkExample;
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
    @CacheEvict(value = "friendlyCache",allEntries = true)
    public int update(FriendlyLink friendlyLink) {
        logger.info("删除友情链接缓存");
        return friendlyLinkMapper.updateByPrimaryKeySelective(friendlyLink);
    }

    /**
     * 插入友情链接
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @CacheEvict(value = "friendlyCache",allEntries = true)
    public int insertFriendlyLink(FriendlyLink friendlyLink) {
        logger.info("删除友情链接缓存");
        Date now = new Date();
        friendlyLink.setCreateTime(now);
        friendlyLink.setUpdateTime(now);
        friendlyLink.setDelFlag(0);
        return friendlyLinkMapper.insertSelective(friendlyLink);
    }
}
