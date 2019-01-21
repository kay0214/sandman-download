/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.system.impl;

import com.sandman.film.base.BaseServiceImpl;
import com.sandman.film.constant.CommonConstant;
import com.sandman.film.dao.mysql.system.model.auto.SignIn;
import com.sandman.film.dao.mysql.system.model.auto.SignInExample;
import com.sandman.film.dao.mysql.system.model.auto.User;
import com.sandman.film.service.system.SignInService;
import com.sandman.film.utils.DateUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunpeikai
 * @version SignInServiceImpl, v0.1 2019/1/17 15:07
 */
@Service
public class SignInServiceImpl extends BaseServiceImpl implements SignInService {

    /**
     * 根据userId获取签到信息
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @Cacheable(value = "signInCache",key = "#userId")
    public Map<String, String> getSignInfo(Integer userId) {
        logger.info("从数据库中取得userId:[{}]的签到信息",userId);
        Map<String,String> result = new HashMap<>();
        SignInExample signInExample = new SignInExample();
        signInExample.createCriteria().andUserIdEqualTo(userId).andDelFlagEqualTo(0);
        List<SignIn> signInList = signInMapper.selectByExample(signInExample);
        for(SignIn signIn:signInList){
            result.put(signIn.getDateStr(),"已签");
        }
        return result;
    }

    /**
     * 根据userId签到
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @Caching(evict={@CacheEvict(value = "signInCache",key = "#userId")},
            put={@CachePut(value = "signInTodayCache",key = "#userId")})
    public boolean signByUserId(Integer userId) {
        logger.info("向数据库中写入签到信息userId:[{}]",userId);
        // 判断用户今日是否已经签到了
        boolean exist = getSignInToday(userId);
        if(exist){
            return true;
        }
        // 签到成功,赠送奖励积分
        // 写入积分详情
        User user = getUserByUserId(userId);
        goldOperation(userId,null, CommonConstant.USER_SIGN_IN_DESC,user.getGold(),null,user.getGold() + CommonConstant.USER_SIGN_IN_GOLD,CommonConstant.USER_SIGN_IN_DESC,2, DateUtils.getNow());
        // 写入用户信息表
        user.setGold(user.getGold() + CommonConstant.USER_SIGN_IN_GOLD);
        user.setUpdateTime(DateUtils.getNow());
        userMapper.updateByPrimaryKeySelective(user);

        //写入签到数据
        SignIn signIn = new SignIn();
        signIn.setUserId(userId);
        signIn.setDateStr(new SimpleDateFormat("yyyy-MM-dd").format(DateUtils.getNow()));
        signIn.setCreateTime(DateUtils.getNow());
        signIn.setUpdateTime(DateUtils.getNow());
        signIn.setDelFlag(0);
        return signInMapper.insert(signIn)>0;
    }
}
