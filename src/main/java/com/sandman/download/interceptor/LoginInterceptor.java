/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.sandman.download.constant.CommonConstant;
import com.sandman.download.dao.mysql.system.model.auto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author sunpeikai
 * @version LoginInterceptor, v0.1 2018/12/5 16:13
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("收到请求，路径为:[{}]",request.getRequestURI());
        try {
            User user = (User) request.getSession().getAttribute("user");
            // 如果session中还有值，充值超时时间
            if(user != null){
                request.getSession().setMaxInactiveInterval(CommonConstant.LOGIN_EXPIRE);
                return true;
            }else{
                // 清空值
                request.getSession().removeAttribute("user");
                return false;
            }
        }catch(Exception e) {
            request.getSession().setAttribute("retUrl",request.getRequestURI());
            response.setContentType("application/json; charset=utf-8");
            JSONObject res = new JSONObject();
            res.put("status", "EUS000010");
            res.put("statusDesc", "登录失效，请重新登陆");
            PrintWriter out = response.getWriter();
            out.append(res.toString());
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
