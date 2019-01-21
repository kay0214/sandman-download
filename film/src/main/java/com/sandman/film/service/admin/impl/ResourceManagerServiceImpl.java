/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.admin.impl;

import com.sandman.film.base.BaseServiceImpl;
import com.sandman.film.bean.admin.ResourceManagerRequest;
import com.sandman.film.dao.mysql.film.model.auto.Film;
import com.sandman.film.dao.mysql.film.model.auto.FilmExample;
import com.sandman.film.service.admin.ResourceManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunpeikai
 * @version ResourceManagerServiceImpl, v0.1 2019/1/10 17:27
 */
@Service
public class ResourceManagerServiceImpl extends BaseServiceImpl implements ResourceManagerService {

    /**
     * 获取资源总数
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getResourceCount(ResourceManagerRequest resourceManagerRequest) {
        FilmExample filmExample = convertExample(resourceManagerRequest);
        return filmMapper.countByExample(filmExample);
    }

    /**
     * 分页获取资源列表
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<Film> searchList(ResourceManagerRequest resourceManagerRequest) {
        FilmExample filmExample = convertExample(resourceManagerRequest);
        filmExample.setOrderByClause("create_time desc");
        computePage(resourceManagerRequest.getPage(),resourceManagerRequest.getLimit());
        filmExample.setLimitStart(limitStart);
        filmExample.setLimitEnd(limitEnd);
        return filmMapper.selectByExample(filmExample);
    }

    private FilmExample convertExample(ResourceManagerRequest resourceManagerRequest){
        FilmExample filmExample = new FilmExample();
        FilmExample.Criteria criteria = filmExample.createCriteria().andDelFlagEqualTo(0);
/*        if(StringUtils.isNotBlank(resourceManagerRequest.getUsername())){
            criteria.and("%" + resourceManagerRequest.getUsername() + "%");
        }*/
        if(StringUtils.isNotBlank(resourceManagerRequest.getResourceName())){
            criteria.andFilmNameLike("%" + resourceManagerRequest.getResourceName() + "%");
        }
/*        if(StringUtils.isNotBlank(resourceManagerRequest.getResourceDesc())){
            criteria.a("%" + resourceManagerRequest.getResourceDesc() + "%");
        }*/
/*        if(resourceManagerRequest.getStatus()!=null){
            criteria.andStatusEqualTo(resourceManagerRequest.getStatus());
        }*/
        return filmExample;
    }
}
