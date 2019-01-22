/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.film;

import com.sandman.film.base.BaseService;
import com.sandman.film.bean.film.FindPlzBean;
import com.sandman.film.dao.mysql.film.model.auto.FindPlz;

import java.util.List;

/**
 * @author sunpeikai
 * @version FindPlzService, v0.1 2019/1/22 10:36
 */
public interface FindPlzService extends BaseService {

    /**
     * 插入留言表
     * @auth sunpeikai
     * @param
     * @return
     */
    int insertFindPlz(FindPlzBean findPlzBean);

    /**
     * 查询count
     * @auth sunpeikai
     * @param
     * @return
     */
    int getFindPlzCount(FindPlzBean findPlzBean);

    /**
     * 查询list
     * @auth sunpeikai
     * @param
     * @return
     */
    List<FindPlz> searchList(FindPlzBean findPlzBean);
}
