/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.admin;

import com.sandman.film.base.BaseService;
import com.sandman.film.bean.admin.TypeManagerRequest;
import com.sandman.film.dao.mysql.film.model.auto.FilmType;

import java.util.List;

/**
 * @author sunpeikai
 * @version TypeManagerService, v0.1 2019/1/22 17:25
 */
public interface TypeManagerService extends BaseService {

    /**
     * type数量
     * @auth sunpeikai
     * @param
     * @return
     */
    int getTypeCount(TypeManagerRequest typeManagerRequest);

    /**
     * 类型list
     * @auth sunpeikai
     * @param
     * @return
     */
    List<FilmType> searchList(TypeManagerRequest typeManagerRequest);

    /**
     * 根据id获取类型
     * @auth sunpeikai
     * @param
     * @return
     */
    FilmType getFilmTypeById(Integer id);

    /**
     * 更新
     * @auth sunpeikai
     * @param
     * @return
     */
    int updateFilmType(FilmType filmType);

    /**
     * 插入
     * @auth sunpeikai
     * @param
     * @return
     */
    int insertFilmType(FilmType filmType);
}
