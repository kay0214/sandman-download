/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.utils;

import com.sandman.download.dao.mysql.system.model.auto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author sunpeikai
 * @version SessionUtils, v0.1 2018/12/5 14:50
 */
public class SessionUtils {

    private static final Logger logger = LoggerFactory.getLogger(SessionUtils.class);

    public static Integer getUserId(){
        User user = getUser();
        if(user != null){
            return user.getUserId();
        }
        return null;
    }

    public static User getUser(){
        User user = null;
        try{
            user = (User)getSessionAttribute("user");
        }catch (Exception e){
            logger.error("从session中获取用户信息失败!");
        }
        return user;
    }

    /**
     * 往session里面设置值
     * @auth sunpeikai
     * @param
     * @return
     */
    public static void setSessionAttribute(String key,Object value){
        getSession().setAttribute(key, value);
    }

    /**
     * 从session中取值
     * @auth sunpeikai
     * @param
     * @return
     */
    public static Object getSessionAttribute(String key){
        Object object = null;
        try{
            object = getSession().getAttribute(key);
        }catch (Exception e){
            logger.error("从session中取值失败,key=[{}]",key);
        }
        return object;
    }

    /**
     * 设置session超时时间
     * @auth sunpeikai
     * @param
     * @return
     */
    public static void setSessionExpireTime(int second){
        getSession().setMaxInactiveInterval(second);
    }

    /**
     * 获取session
     * @auth sunpeikai
     * @param
     * @return
     */
    public static HttpSession getSession(){
        HttpServletRequest request = getHttpServletRequest();
        return request.getSession();
    }

    /**
     * 获取HttpServletRequest
     * @auth sunpeikai
     * @param
     * @return
     */
    public static HttpServletRequest getHttpServletRequest(){
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
