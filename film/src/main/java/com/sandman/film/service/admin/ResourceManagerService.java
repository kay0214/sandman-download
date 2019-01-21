/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.admin;

import com.sandman.film.base.BaseService;
import com.sandman.film.bean.admin.ResourceManagerRequest;
import com.sandman.film.dao.mysql.film.model.auto.Film;

import java.util.List;

/**
 * @author sunpeikai
 * @version ResourceManagerService, v0.1 2019/1/10 17:26
 */
public interface ResourceManagerService extends BaseService {

    /**
     * 获取资源总数
     * @auth sunpeikai
     * @param
     * @return
     */
    int getResourceCount(ResourceManagerRequest resourceManagerRequest);

    /**
     * 分页获取资源列表
     * @auth sunpeikai
     * @param
     * @return
     */
    List<Film> searchList(ResourceManagerRequest resourceManagerRequest);
}
