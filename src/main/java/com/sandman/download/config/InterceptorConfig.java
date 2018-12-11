/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.config;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.dao.mysql.system.model.auto.SecureConfig;
import com.sandman.download.interceptor.LoginInterceptor;
import com.sandman.download.interceptor.RetUrlInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dongzeshan
 * @version InterceptorConfig, v0.1 2018/6/22 10:56
 */

@Configuration
public class InterceptorConfig extends BaseServiceImpl implements WebMvcConfigurer {

	//这里可以动态配置

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<SecureConfig> secureConfigList = getSecureConfigList();
		List<String> addPathPatternList = new ArrayList<>();
		for(SecureConfig secureConfig:secureConfigList){
			if(secureConfig.getSecureVisitFlag() == 1){
				addPathPatternList.add(secureConfig.getApiUrl());
			}
		}
		InterceptorRegistration addIn= registry.addInterceptor(new LoginInterceptor());
		addIn.addPathPatterns(addPathPatternList);
		//addIn.excludePathPatterns("/**");
		registry.addInterceptor(new RetUrlInterceptor()).addPathPatterns("/**").excludePathPatterns("/**/**.js","/**/**.css");
		//所有都拦截
		/*addIn.addPathPatterns("/**");*/
		//不拦截的请求
/*		addIn.excludePathPatterns(
				"/login/init",
				"/login/login",
				"/register/init",
				"/register/register"
		).excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");*/
	}
}