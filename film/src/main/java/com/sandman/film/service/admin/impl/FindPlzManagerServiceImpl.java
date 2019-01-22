/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.admin.impl;

import com.sandman.film.base.BaseServiceImpl;
import com.sandman.film.bean.film.FindPlzPageBean;
import com.sandman.film.dao.mysql.film.model.auto.FindPlz;
import com.sandman.film.dao.mysql.film.model.auto.FindPlzExample;
import com.sandman.film.service.admin.FindPlzManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunpeikai
 * @version FindPlzManagerServiceImpl, v0.1 2019/1/22 16:37
 */
@Service
public class FindPlzManagerServiceImpl extends BaseServiceImpl implements FindPlzManagerService {

    /**
     * 获取count
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getFindPlzCount(FindPlzPageBean findPlzPageBean) {
        FindPlzExample findPlzExample = convertExample(findPlzPageBean);
        return findPlzMapper.countByExample(findPlzExample);
    }

    /**
     * 获取list
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<FindPlz> searchList(FindPlzPageBean findPlzPageBean) {
        FindPlzExample findPlzExample = convertExample(findPlzPageBean);
        findPlzExample.setOrderByClause("create_time asc");
        computePage(findPlzPageBean.getPage(), findPlzPageBean.getLimit());
        findPlzExample.setLimitStart(limitStart);
        findPlzExample.setLimitEnd(limitEnd);
        return findPlzMapper.selectByExample(findPlzExample);
    }

    /**
     * 更新
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int updateFindPlz(FindPlz findPlz) {
        return findPlzMapper.updateByPrimaryKeySelective(findPlz);
    }

    private FindPlzExample convertExample(FindPlzPageBean findPlzPageBean){
        FindPlzExample findPlzExample = new FindPlzExample();
        FindPlzExample.Criteria criteria = findPlzExample.createCriteria().andDelFlagEqualTo(0);
        if(StringUtils.isNotBlank(findPlzPageBean.getName())){
            criteria.andNameLike("%" + findPlzPageBean.getName() + "%");
        }
        if(StringUtils.isNotBlank(findPlzPageBean.getContact())){
            criteria.andContactLike("%" + findPlzPageBean.getContact() + "%");
        }
        if(findPlzPageBean.getStatus() != null){
            criteria.andStatusEqualTo(findPlzPageBean.getStatus());
        }
        return findPlzExample;
    }
}
