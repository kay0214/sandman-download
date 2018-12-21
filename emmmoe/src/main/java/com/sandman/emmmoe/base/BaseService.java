/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.base;

import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.NotContains;
import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.PageInfo;

import java.util.List;

/**
 * @author sunpeikai
 * @version BaseService, v0.1 2018/12/3 11:56
 */
public interface BaseService {
    /**
     * 获取emmmoe登船地址页面
     * @auth sunpeikai
     * @param
     * @return
     */
    String getRootUrl();

    /**
     * 爬取时不包含的url列表
     * @auth sunpeikai
     * @param
     * @return
     */
    List<NotContains> getNotContainsUrl();

    /**
     * 获取到未完成的pageInfo
     * @auth sunpeikai
     * @param
     * @return
     */
    List<PageInfo> getNotSuccessPage();
}
