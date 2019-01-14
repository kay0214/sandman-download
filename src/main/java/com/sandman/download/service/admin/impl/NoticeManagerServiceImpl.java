/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.admin.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.bean.admin.NoticeManagerRequest;
import com.sandman.download.dao.mysql.system.model.auto.Notice;
import com.sandman.download.dao.mysql.system.model.auto.NoticeExample;
import com.sandman.download.service.admin.NoticeManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author sunpeikai
 * @version NoticeManagerServiceImpl, v0.1 2019/1/10 17:29
 */
@Service
public class NoticeManagerServiceImpl extends BaseServiceImpl implements NoticeManagerService {

    /**
     * 获取公告count
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getNoticeCount(NoticeManagerRequest noticeManagerRequest) {
        NoticeExample noticeExample = convertExample(noticeManagerRequest);
        return noticeMapper.countByExample(noticeExample);
    }

    /**
     * 分页获取公告列表
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<Notice> getNoticeList(NoticeManagerRequest noticeManagerRequest) {
        NoticeExample noticeExample = convertExample(noticeManagerRequest);
        noticeExample.setOrderByClause("order_no asc");
        computePage(noticeManagerRequest.getPage(), noticeManagerRequest.getLimit());
        noticeExample.setLimitStart(limitStart);
        noticeExample.setLimitEnd(limitEnd);
        return noticeMapper.selectByExample(noticeExample);
    }

    private NoticeExample convertExample(NoticeManagerRequest noticeManagerRequest){
        NoticeExample noticeExample = new NoticeExample();
        NoticeExample.Criteria criteria = noticeExample.createCriteria().andDelFlagEqualTo(0);
        if(StringUtils.isNotBlank(noticeManagerRequest.getTitle())){
            criteria.andTitleLike("%"+ noticeManagerRequest.getTitle() + "%");
        }
        if(StringUtils.isNotBlank(noticeManagerRequest.getContent())){
            criteria.andContentLike("%"+ noticeManagerRequest.getContent() + "%");
        }
        if(noticeManagerRequest.getStatus() != null){
            criteria.andStatusEqualTo(noticeManagerRequest.getStatus());
        }
        return noticeExample;
    }

    /**
     * 根据id获取公告
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public Notice getNoticeById(Integer id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新notice
     * allEntries = true 删除全部元素重新从数据库中获取
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @CacheEvict(value = "noticeCache",allEntries = true)
    public int updateNotice(Notice notice) {
        logger.info("删除公告缓存");
        return noticeMapper.updateByPrimaryKeySelective(notice);
    }

    /**
     * 插入新公告
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @CacheEvict(value = "noticeCache",allEntries = true)
    public int insertNotice(Notice notice) {
        logger.info("删除公告缓存");
        Date now = new Date();
        notice.setCreateTime(now);
        notice.setUpdateTime(now);
        notice.setDelFlag(0);
        return noticeMapper.insertSelective(notice);
    }
}
