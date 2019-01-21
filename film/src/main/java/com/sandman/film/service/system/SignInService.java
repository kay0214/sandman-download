/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.system;

import com.sandman.film.base.BaseService;

import java.util.Map;

/**
 * @author sunpeikai
 * @version SignInService, v0.1 2019/1/17 15:07
 */
public interface SignInService extends BaseService {

    /**
     * 根据userId获取签到信息
     * @auth sunpeikai
     * @param
     * @return
     */
    Map<String,String> getSignInfo(Integer userId);

    /**
     * 根据userId签到
     * @auth sunpeikai
     * @param
     * @return
     */
    boolean signByUserId(Integer userId);

}
