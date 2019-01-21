/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.film.impl;

import com.sandman.film.base.BaseServiceImpl;
import com.sandman.film.bean.film.FilmBean;
import com.sandman.film.constant.CommonConstant;
import com.sandman.film.dao.mysql.film.model.auto.Film;
import com.sandman.film.dao.mysql.film.model.auto.FilmExample;
import com.sandman.film.dao.mysql.film.model.auto.FilmLog;
import com.sandman.film.dao.mysql.film.model.auto.FilmLogExample;
import com.sandman.film.dao.mysql.system.model.auto.User;
import com.sandman.film.service.film.FilmService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author sunpeikai
 * @version FilmServiceImpl, v0.1 2019/1/21 14:12
 */
@Service
public class FilmServiceImpl extends BaseServiceImpl implements FilmService {

    /**
     * 购买影片
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int buyFilm(User user, Film film) {
        Date now = new Date();
        // 写入资源log
        FilmLog filmLog = new FilmLog();
        filmLog.setUserId(user.getUserId());
        filmLog.setFilmId(film.getId());
        filmLog.setFilmName(film.getFilmName());
        filmLog.setFilmUrl(film.getFilmUrl());
        filmLog.setFilmPassword(film.getFilmPassword());
        filmLog.setDesc(CommonConstant.USER_BUY_FILM);
        filmLog.setCreateTime(now);
        filmLog.setUpdateTime(now);
        filmLog.setDelFlag(0);
        int result = filmLogMapper.insert(filmLog);
        // 写入积分记录
        goldOperation(user.getUserId(),film.getId(),film.getFilmName(),user.getGold(),film.getFilmGold(),user.getGold()-film.getFilmGold(),CommonConstant.USER_BUY_FILM,1,now);
        user.setGold(user.getGold() - film.getFilmGold());
        updateUser(user);
        // 写入资源下载量
        film.setBuyCount(film.getBuyCount() + 1);
        updateFilm(film);
        return result;
    }

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
    @Cacheable(value = "hotFilmsCache")
    public List<Film> getHotFilm() {
        logger.info("从mysql中获取热门资源");
        return getFilmByType(new FilmBean(1, CommonConstant.HOT_FILMS_LIMIT,1));
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
            resourceExample.setOrderByClause("update_time desc");
        }
        return resourceExample;
    }
}
