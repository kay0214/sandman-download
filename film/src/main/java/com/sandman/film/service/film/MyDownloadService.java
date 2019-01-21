/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.film;

import com.sandman.film.base.BaseService;
import com.sandman.film.bean.film.MyDownloadBean;
import com.sandman.film.bean.film.MyDownloadResultBean;

import java.util.List;

/**
 * @author sunpeikai
 * @version MyDownloadService, v0.1 2018/12/18 17:23
 */
public interface MyDownloadService extends BaseService {

    /**
     * 获取我的下载记录count
     * @auth sunpeikai
     * @param
     * @return
     */
    int getAllMyDownloadCount(MyDownloadBean myDownloadBean);

    /**
     * 获取我的下载记录
     * @auth sunpeikai
     * @param
     * @return
     */
    List<MyDownloadResultBean> getAllMyDownload(MyDownloadBean myDownloadBean);
}
