/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.system;

import com.sandman.download.base.BaseService;
import com.sandman.download.dao.mysql.system.model.auto.Notice;

import java.util.List;

/**
 * @author sunpeikai
 * @version NoticeService, v0.1 2019/1/9 14:08
 */
public interface NoticeService extends BaseService {

    /**
     * 获取公告列表
     * @auth sunpeikai
     * @param
     * @return
     */
    List<Notice> getNoticeList();
}
