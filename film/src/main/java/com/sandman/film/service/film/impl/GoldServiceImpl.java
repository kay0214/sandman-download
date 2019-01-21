/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.film.impl;

import com.sandman.film.base.BaseServiceImpl;
import com.sandman.film.dao.mysql.film.model.auto.GoldLog;
import com.sandman.film.dao.mysql.film.model.auto.GoldLogExample;
import com.sandman.film.service.film.GoldService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunpeikai
 * @version GoldServiceImpl, v0.1 2019/1/7 15:39
 */
@Service
public class GoldServiceImpl extends BaseServiceImpl implements GoldService {

    /**
     * 查询当前登录用户积分记录count
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public Integer goldListCount(Integer userId) {
        if(userId != null){
            GoldLogExample goldLogExample = new GoldLogExample();
            goldLogExample.createCriteria().andUserIdEqualTo(userId).andDelFlagEqualTo(0);
            return goldLogMapper.countByExample(goldLogExample);
        }
        return 0;
    }

    /**
     * 查询当前登录用户积分记录
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<GoldLog> search(Integer userId,Integer page,Integer limit) {
        computePage(page, limit);
        if(userId != null){
            GoldLogExample goldLogExample = new GoldLogExample();
            goldLogExample.createCriteria().andUserIdEqualTo(userId).andDelFlagEqualTo(0);
            if(limitStart != -1){
                goldLogExample.setLimitStart(limitStart);
                goldLogExample.setLimitEnd(limitEnd);
            }
            goldLogExample.setOrderByClause("create_time desc");
            return goldLogMapper.selectByExample(goldLogExample);
        }
        return new ArrayList<>();
    }
}
