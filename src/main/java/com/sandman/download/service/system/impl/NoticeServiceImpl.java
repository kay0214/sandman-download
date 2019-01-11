/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.system.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.dao.mysql.system.model.auto.Notice;
import com.sandman.download.dao.mysql.system.model.auto.NoticeExample;
import com.sandman.download.service.system.NoticeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunpeikai
 * @version NoticeServiceImpl, v0.1 2019/1/9 14:08
 */
@Service
public class NoticeServiceImpl extends BaseServiceImpl implements NoticeService {

    /**
     * 获取公告列表
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<Notice> getNoticeList() {
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.setOrderByClause("order_no asc");
        noticeExample.createCriteria().andDelFlagEqualTo(0).andStatusEqualTo(1);
        return noticeMapper.selectByExample(noticeExample);
    }
}
