/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.film.impl;

import com.sandman.film.base.BaseServiceImpl;
import com.sandman.film.bean.film.MyDownloadBean;
import com.sandman.film.bean.film.MyDownloadResultBean;
import com.sandman.film.dao.mysql.film.model.auto.Film;
import com.sandman.film.dao.mysql.film.model.auto.FilmLog;
import com.sandman.film.dao.mysql.film.model.auto.FilmLogExample;
import com.sandman.film.service.film.MyDownloadService;
import com.sandman.film.utils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author sunpeikai
 * @version MyDownloadServiceImpl, v0.1 2018/12/18 17:23
 */
@Service
public class MyDownloadServiceImpl extends BaseServiceImpl implements MyDownloadService {

    /**
     * 获取我的下载记录count
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getAllMyDownloadCount(MyDownloadBean myDownloadBean) {
        FilmLogExample resourceLogExample = convertExample(myDownloadBean);
        return filmLogMapper.countByExample(resourceLogExample);
    }

    /**
     * 获取我的下载记录
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<MyDownloadResultBean> getAllMyDownload(MyDownloadBean myDownloadBean) {
        FilmLogExample filmLogExample = convertExample(myDownloadBean);
        List<FilmLog> filmLogList = filmLogMapper.selectByExample(filmLogExample);
        if(!CollectionUtils.isEmpty(filmLogList)){
            // bean 转换
            List<MyDownloadResultBean> myDownloadResultBeanList = BeanUtils.convertBeanList(filmLogList,MyDownloadResultBean.class);
            for(MyDownloadResultBean myDownloadResultBean : myDownloadResultBeanList){
                Film film = getFilmById(myDownloadResultBean.getResourceId());
                myDownloadResultBean.setFilm(film);
            }
            return myDownloadResultBeanList;
        }
        return null;
    }


    private FilmLogExample convertExample(MyDownloadBean myDownloadBean){
        FilmLogExample filmLogExample = new FilmLogExample();
        FilmLogExample.Criteria criteria = filmLogExample.createCriteria();
        criteria.andUserIdEqualTo(myDownloadBean.getUserId()).andDelFlagEqualTo(0);
        if(myDownloadBean.getLimitStart() >= 0){
            filmLogExample.setLimitStart(myDownloadBean.getLimitStart());
            filmLogExample.setLimitEnd(myDownloadBean.getLimitEnd());
        }
        if(StringUtils.isNotBlank(myDownloadBean.getResourceName())){
            criteria.andFilmNameLike("%" + myDownloadBean.getResourceName() + "%");
        }
        return filmLogExample;
    }
}
