/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sunpeikai
 * @version RetUrlInterceptor, v0.1 2018/12/6 9:55
 */
public class RetUrlInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        logger.debug("retUrl收到请求，路径为:[{}]",uri);


/*        String uri = request.getRequestURI();
        try{
            String lastUri = request.getSession().getAttribute("uri").toString();
            request.getSession().setAttribute("uri",uri);
            if(StringUtils.isNotBlank(lastUri)){
                request.getSession().setAttribute("retUrl",lastUri);
            }
        }catch (Exception e){
            logger.error("url拦截器报错，error:[{}]",e.toString());
            request.getSession().setAttribute("uri",uri);
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
