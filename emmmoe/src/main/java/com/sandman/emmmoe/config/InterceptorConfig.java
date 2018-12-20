/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.config;

import com.sandman.emmmoe.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dongzeshan
 * @version InterceptorConfig, v0.1 2018/6/22 10:56
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	//这里可以动态配置

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration addIn= registry.addInterceptor(new LoginInterceptor());
		addIn.addPathPatterns("/manage/**","/work/**");
	}
}