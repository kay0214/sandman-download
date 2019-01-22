/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.admin;

import com.sandman.film.base.BaseService;
import com.sandman.film.bean.film.FindPlzPageBean;
import com.sandman.film.dao.mysql.film.model.auto.FindPlz;

import java.util.List;

/**
 * @author sunpeikai
 * @version FindPlzManagerService, v0.1 2019/1/22 16:36
 */
public interface FindPlzManagerService extends BaseService {

    /**
     * 获取count
     * @auth sunpeikai
     * @param
     * @return
     */
    int getFindPlzCount(FindPlzPageBean findPlzPageBean);

    /**
     * 获取list
     * @auth sunpeikai
     * @param
     * @return
     */
    List<FindPlz> searchList(FindPlzPageBean findPlzPageBean);

    /**
     * 更新
     * @auth sunpeikai
     * @param
     * @return
     */
    int updateFindPlz(FindPlz findPlz);
}
