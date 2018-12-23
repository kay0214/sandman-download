/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.interceptor;

import com.sandman.emmmoe.bean.User;
import com.sandman.emmmoe.constant.CommonConstant;
import com.sandman.emmmoe.utils.ClientIpAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sunpeikai
 * @version LoginInterceptor, v0.1 2018/12/5 16:13
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("收到请求，路径为:[{}],ip为:[{}]",request.getRequestURI(), ClientIpAddress.getIpAddress(request));
        try {
            User user = (User) request.getSession().getAttribute("user");
            // 如果session中还有值，充值超时时间
            if(user != null){
                request.getSession().setMaxInactiveInterval(CommonConstant.LOGIN_EXPIRE);
            }else{
                // 清空值
                request.getSession().removeAttribute("user");
                response.sendRedirect("/login/init");
                return false;
            }
        }catch(Exception e) {
            logger.error("拦截器中出现错误");
            response.sendRedirect("/login/init");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
