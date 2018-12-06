/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.system.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.dao.mysql.system.model.auto.UserLoginLog;
import com.sandman.download.service.system.LoginService;
import com.sandman.download.utils.ClientIpAddress;
import com.sandman.download.utils.SessionUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author sunpeikai
 * @version LoginServiceImpl, v0.1 2018/12/5 14:55
 */
@Service
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {

    /**
     * 收集登录日志
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int updateLoginLog(int userId) {
        UserLoginLog userLoginLog = getLoginLogByUserId(userId);
        int result = 0;
        Date now = new Date();
        if(userLoginLog != null){
            // 更新操作
            userLoginLog.setLoginTimes(userLoginLog.getLoginTimes()+1);
            // 这里最后一次登录IP和时间，必须放到本次登录的IP和时间之前
            userLoginLog.setLastIp(userLoginLog.getLoginIp());
            userLoginLog.setLastTime(userLoginLog.getLoginTime());
            userLoginLog.setLoginIp(ClientIpAddress.getIpAddress(SessionUtils.getHttpServletRequest()));
            userLoginLog.setLoginTime(now);
            userLoginLog.setUpdateTime(now);
            result += userLoginLogMapper.updateByPrimaryKeySelective(userLoginLog);
        }else{
            // 插入操作
            userLoginLog = new UserLoginLog();
            userLoginLog.setUserId(userId);
            userLoginLog.setLoginTimes(1);
            userLoginLog.setLoginIp(ClientIpAddress.getIpAddress(SessionUtils.getHttpServletRequest()));
            userLoginLog.setLoginTime(now);
            userLoginLog.setLastIp(ClientIpAddress.getIpAddress(SessionUtils.getHttpServletRequest()));
            userLoginLog.setLastTime(now);
            userLoginLog.setCreateTime(now);
            userLoginLog.setUpdateTime(now);
            result += userLoginLogMapper.insertSelective(userLoginLog);
        }
        return result;
    }
}
