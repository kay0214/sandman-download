/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.film.impl;

import com.sandman.film.base.BaseServiceImpl;
import com.sandman.film.bean.film.FindPlzBean;
import com.sandman.film.dao.mysql.film.model.auto.FindPlz;
import com.sandman.film.dao.mysql.film.model.auto.FindPlzExample;
import com.sandman.film.service.film.FindPlzService;
import com.sandman.film.utils.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author sunpeikai
 * @version FindPlzServiceImpl, v0.1 2019/1/22 10:36
 */
@Service
public class FindPlzServiceImpl extends BaseServiceImpl implements FindPlzService {

    /**
     * 插入留言表
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @CacheEvict(value = "findPlzCache",allEntries = true)
    public int insertFindPlz(FindPlzBean findPlzBean) {
        FindPlz findPlz = BeanUtils.convertBean(findPlzBean,FindPlz.class);
        findPlz.setStatus(0);
        findPlz.setCreateTime(new Date());
        findPlz.setUpdateTime(new Date());
        findPlz.setDelFlag(0);
        return findPlzMapper.insertSelective(findPlz);
    }

    /**
     * 查询count
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getFindPlzCount(FindPlzBean findPlzBean) {
        FindPlzExample findPlzExample = convertExample(findPlzBean);
        return findPlzMapper.countByExample(findPlzExample);
    }

    /**
     * 查询list
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @Cacheable(value = "findPlzCache",key = "#findPlzBean.currPage")
    public List<FindPlz> searchList(FindPlzBean findPlzBean) {
        logger.info("从mysql中获取留言");
        FindPlzExample findPlzExample = convertExample(findPlzBean);
        findPlzExample.setLimitStart(findPlzBean.getLimitStart());
        findPlzExample.setLimitEnd(findPlzBean.getLimitEnd());
        return findPlzMapper.selectByExample(findPlzExample);
    }

    private FindPlzExample convertExample(FindPlzBean findPlzBean){
        FindPlzExample findPlzExample = new FindPlzExample();
        findPlzExample.createCriteria().andDelFlagEqualTo(0);
        return findPlzExample;
    }
}
