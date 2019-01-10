/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.admin;

import com.sandman.download.base.BaseService;

import java.util.Date;

/**
 * @author sunpeikai
 * @version AdminService, v0.1 2019/1/10 11:10
 */
public interface AdminService extends BaseService {

    /**
     * 根据日期获取上传资源数量
     * @auth sunpeikai
     * @param
     * @return
     */
    int getUploadCount(Date start,Date end);

    /**
     * 根据日期获取下载资源数量
     * @auth sunpeikai
     * @param
     * @return
     */
    int getDownloadCount(Date start,Date end);

    /**
     * 根据日期获取活跃用户数量
     * @auth sunpeikai
     * @param
     * @return
     */
    int getActiveCount(Date start,Date end);

    /**
     * 根据日期获取注册用户数量
     * @auth sunpeikai
     * @param
     * @return
     */
    int getRegisterCount(Date start,Date end);
}
