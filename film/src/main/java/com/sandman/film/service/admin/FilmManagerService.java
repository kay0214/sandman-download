/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.admin;

import com.alibaba.fastjson.JSONObject;
import com.sandman.film.base.BaseService;
import com.sandman.film.bean.admin.FilmManagerRequest;
import com.sandman.film.dao.mysql.film.model.auto.Film;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author sunpeikai
 * @version FilmManagerService, v0.1 2019/1/10 17:26
 */
public interface FilmManagerService extends BaseService {

    /**
     * 获取资源总数
     * @auth sunpeikai
     * @param
     * @return
     */
    int getFilmCount(FilmManagerRequest filmManagerRequest);

    /**
     * 分页获取资源列表
     * @auth sunpeikai
     * @param
     * @return
     */
    List<Film> searchList(FilmManagerRequest filmManagerRequest);

    /**
     * 修改图片封面
     * @auth sunpeikai
     * @param
     * @return
     */
    JSONObject modifyImage(MultipartFile file);

    /**
     * 添加影片
     * @auth sunpeikai
     * @param
     * @return
     */
    int insertFilm(Film film);
}
