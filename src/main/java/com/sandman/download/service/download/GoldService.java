/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.download;

import com.sandman.download.base.BaseService;
import com.sandman.download.dao.mysql.download.model.auto.GoldLog;

import java.util.List;

/**
 * @author sunpeikai
 * @version GoldService, v0.1 2019/1/7 15:38
 */
public interface GoldService extends BaseService {

    /**
     * 查询当前登录用户积分记录count
     * @auth sunpeikai
     * @param
     * @return
     */
    Integer goldListCount(Integer userId);

    /**
     * 查询当前登录用户积分记录
     * @auth sunpeikai
     * @param
     * @return
     */
    List<GoldLog> search(Integer userId,Integer page,Integer limit);
}
