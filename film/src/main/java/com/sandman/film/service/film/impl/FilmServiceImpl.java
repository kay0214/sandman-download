/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.film.impl;

import com.sandman.film.base.BaseServiceImpl;
import com.sandman.film.bean.film.FilmBean;
import com.sandman.film.constant.CommonConstant;
import com.sandman.film.dao.mysql.film.model.auto.Film;
import com.sandman.film.dao.mysql.film.model.auto.FilmExample;
import com.sandman.film.service.film.FilmService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunpeikai
 * @version FilmServiceImpl, v0.1 2019/1/21 14:12
 */
@Service
public class FilmServiceImpl extends BaseServiceImpl implements FilmService {

    /**
     * 获取资源信息（type：1 -> 按照下载次数倒叙排序，else -> 按照创建时间倒叙)
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<Film> getFilmByType(FilmBean filmBean) {
        FilmExample filmExample = convertExample(filmBean);
        return filmMapper.selectByExample(filmExample);
    }

    /**
     * 获取资源count（type：1 -> 按照下载次数倒叙排序，else -> 按照创建时间倒叙)
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getFilmCountByType(FilmBean filmBean) {
        FilmExample filmExample = convertExample(filmBean);
        return filmMapper.countByExample(filmExample);
    }

    /**
     * 搜索资源
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<Film> searchFilm(FilmBean filmBean) {
        FilmExample filmExample = convertExample(filmBean);
        return filmMapper.selectByExample(filmExample);
    }

    /**
     * 获取资源列表分页显示
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<Film> getList(FilmBean filmBean) {
        FilmExample filmExample = convertExample(filmBean);
        return filmMapper.selectByExample(filmExample);
    }

    /**
     * 获取热门资源(调用其他方法，这里封装一个方法是为了方便缓存)
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @Cacheable(value = "hotResourcesCache")
    public List<Film> getHotFilm() {
        logger.info("从mysql中获取热门资源");
        return getFilmByType(new FilmBean(1, CommonConstant.HOT_RESOURCES_LIMIT,1));
    }

    private FilmExample convertExample(FilmBean filmBean){
        FilmExample resourceExample = new FilmExample();
        if(StringUtils.isNotBlank(filmBean.getSearch())){
            resourceExample.or().andStatusEqualTo(1).andDelFlagEqualTo(0).andFilmNameLike("%" + filmBean.getSearch() + "%");
            resourceExample.or().andStatusEqualTo(1).andDelFlagEqualTo(0).andFilmDescLike("%" + filmBean.getSearch() + "%");
        }else{
            resourceExample.createCriteria().andStatusEqualTo(1).andDelFlagEqualTo(0);
        }
        if(filmBean.getLimitStart()>=0){
            resourceExample.setLimitStart(filmBean.getLimitStart());
            resourceExample.setLimitEnd(filmBean.getLimitEnd());
        }
        if(filmBean.getType() == 1){
            resourceExample.setOrderByClause("buy_count desc");
        }else{
            resourceExample.setOrderByClause("create_time desc");
        }
        return resourceExample;
    }
}
