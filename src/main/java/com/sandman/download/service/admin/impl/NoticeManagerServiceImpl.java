/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.admin.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.dao.mysql.system.model.auto.Notice;
import com.sandman.download.dao.mysql.system.model.auto.NoticeExample;
import com.sandman.download.service.admin.NoticeManagerService;
import org.springframework.stereotype.Service;

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
    public int getNoticeCount() {
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.createCriteria().andDelFlagEqualTo(0);
        return noticeMapper.countByExample(noticeExample);
    }

    /**
     * 分页获取公告列表
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<Notice> getNoticeList(Integer page, Integer limit) {
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.setOrderByClause("order_no asc");
        computePage(page, limit);
        noticeExample.setLimitStart(limitStart);
        noticeExample.setLimitEnd(limitEnd);
        noticeExample.createCriteria().andDelFlagEqualTo(0);
        return noticeMapper.selectByExample(noticeExample);
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
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int updateNotice(Notice notice) {
        return noticeMapper.updateByPrimaryKeySelective(notice);
    }
}
