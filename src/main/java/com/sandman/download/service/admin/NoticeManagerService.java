/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.admin;

import com.sandman.download.base.BaseService;
import com.sandman.download.bean.admin.NoticeManagerRequest;
import com.sandman.download.dao.mysql.system.model.auto.Notice;

import java.util.List;

/**
 * @author sunpeikai
 * @version NoticeManagerService, v0.1 2019/1/10 17:29
 */
public interface NoticeManagerService extends BaseService {

    /**
     * 获取公告count
     * @auth sunpeikai
     * @param
     * @return
     */
    int getNoticeCount(NoticeManagerRequest noticeManagerRequest);

    /**
     * 分页获取公告列表
     * @auth sunpeikai
     * @param
     * @return
     */
    List<Notice> getNoticeList(NoticeManagerRequest noticeManagerRequest);

    /**
     * 根据id获取公告
     * @auth sunpeikai
     * @param
     * @return
     */
    Notice getNoticeById(Integer id);

    /**
     * 更新notice
     * @auth sunpeikai
     * @param
     * @return
     */
    int updateNotice(Notice notice);

    /**
     * 插入新公告
     * @auth sunpeikai
     * @param
     * @return
     */
    int insertNotice(Notice notice);
}
