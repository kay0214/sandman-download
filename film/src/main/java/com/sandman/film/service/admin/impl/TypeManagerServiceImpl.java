/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.admin.impl;

import com.sandman.film.base.BaseServiceImpl;
import com.sandman.film.bean.admin.TypeManagerRequest;
import com.sandman.film.dao.mysql.film.model.auto.FilmType;
import com.sandman.film.dao.mysql.film.model.auto.FilmTypeExample;
import com.sandman.film.service.admin.TypeManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author sunpeikai
 * @version TypeManagerServiceImpl, v0.1 2019/1/22 17:25
 */
@Service
public class TypeManagerServiceImpl extends BaseServiceImpl implements TypeManagerService {

    /**
     * type数量
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getTypeCount(TypeManagerRequest typeManagerRequest) {
        FilmTypeExample filmTypeExample = convertExample(typeManagerRequest);
        return filmTypeMapper.countByExample(filmTypeExample);
    }

    /**
     * 类型list
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @Cacheable(value = "filmTypeCache",key = "#typeManagerRequest.page+'_'+#typeManagerRequest.limit+'_'+#typeManagerRequest.name+'_'+#typeManagerRequest.status")
    public List<FilmType> searchList(TypeManagerRequest typeManagerRequest) {
        logger.info("从MySQL中取出类型list");
        FilmTypeExample filmTypeExample = convertExample(typeManagerRequest);
        filmTypeExample.setOrderByClause("create_time desc");
        computePage(typeManagerRequest.getPage(),typeManagerRequest.getLimit());
        filmTypeExample.setLimitStart(limitStart);
        filmTypeExample.setLimitEnd(limitEnd);
        return filmTypeMapper.selectByExample(filmTypeExample);
    }

    /**
     * 根据id获取类型
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public FilmType getFilmTypeById(Integer id) {
        return filmTypeMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @CacheEvict(value = "filmTypeCache",allEntries = true)
    public int updateFilmType(FilmType filmType) {
        return filmTypeMapper.updateByPrimaryKeySelective(filmType);
    }

    /**
     * 插入
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @CacheEvict(value = "filmTypeCache",allEntries = true)
    public int insertFilmType(FilmType filmType) {
        filmType.setCreateTime(new Date());
        filmType.setUpdateTime(new Date());
        filmType.setDelFlag(0);
        return filmTypeMapper.insertSelective(filmType);
    }

    private FilmTypeExample convertExample(TypeManagerRequest typeManagerRequest){
        FilmTypeExample filmTypeExample = new FilmTypeExample();
        FilmTypeExample.Criteria criteria = filmTypeExample.createCriteria().andDelFlagEqualTo(0);
        if(StringUtils.isNotBlank(typeManagerRequest.getName())){
            criteria.andNameLike("%" + typeManagerRequest.getName() + "%");
        }
        if(typeManagerRequest.getStatus()!=null){
            criteria.andStatusEqualTo(typeManagerRequest.getStatus());
        }
        return filmTypeExample;
    }
}
