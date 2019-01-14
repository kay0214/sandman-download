/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.config;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.dao.mysql.system.model.auto.SecureConfig;
import com.sandman.download.interceptor.AdminInterceptor;
import com.sandman.download.interceptor.LoginInterceptor;
import com.sandman.download.interceptor.RetUrlInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunpeikai
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

		// 登录请求拦截
		InterceptorRegistration addIn = registry.addInterceptor(new LoginInterceptor());
		addIn.addPathPatterns(addPathPatternList);

		// 请求路径拦截
		//registry.addInterceptor(new RetUrlInterceptor()).addPathPatterns("/**").excludePathPatterns("/**/**.js","/**/**.css");

		// 管理员拦截
		InterceptorRegistration adminIn = registry.addInterceptor(new AdminInterceptor());
		adminIn.addPathPatterns("/admin/index","/friendly_link_manager/**","/link_manager/**","/notice_manager/**","/resource_manager/**","/template_manager/**","/user_manager/**");

	}
}