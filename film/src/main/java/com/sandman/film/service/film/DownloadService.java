/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.film;

import com.sandman.film.base.BaseService;
import com.sandman.film.dao.mysql.film.model.auto.FilmLog;

/**
 * @author sunpeikai
 * @version DownloadService, v0.1 2018/12/12 16:22
 */
public interface DownloadService extends BaseService {

    /**
     * 根据resourceId 和 userId查询是否存在记录
     * @auth sunpeikai
     * @param
     * @return
     */
    FilmLog getFilmLogByResourceIdAndUserId(Integer filmId, Integer userId);

}
