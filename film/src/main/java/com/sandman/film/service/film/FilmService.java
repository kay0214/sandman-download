/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.film;

import com.sandman.film.base.BaseService;
import com.sandman.film.bean.film.FilmBean;
import com.sandman.film.dao.mysql.film.model.auto.Film;

import java.util.List;

/**
 * @author sunpeikai
 * @version FilmService, v0.1 2019/1/21 14:12
 */
public interface FilmService extends BaseService {
    /**
     * 获取资源信息（type：1 -> 按照下载次数倒叙排序，else -> 按照创建时间倒叙)
     * @auth sunpeikai
     * @param
     * @return
     */
    List<Film> getFilmByType(FilmBean filmBean);

    /**
     * 获取资源count（type：1 -> 按照下载次数倒叙排序，else -> 按照创建时间倒叙)
     * @auth sunpeikai
     * @param
     * @return
     */
    int getFilmCountByType(FilmBean filmBean);

    /**
     * 搜索资源
     * @auth sunpeikai
     * @param
     * @return
     */
    List<Film> searchFilm(FilmBean filmBean);

    /**
     * 资源分页
     * @auth sunpeikai
     * @param
     * @return
     */
    List<Film> getList(FilmBean filmBean);

    /**
     * 获取热门资源(调用其他方法，这里封装一个方法是为了方便缓存)
     * @auth sunpeikai
     * @param
     * @return
     */
    List<Film> getHotFilm();
}
