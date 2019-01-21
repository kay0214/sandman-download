package com.sandman.film.config;/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 *//*

package com.sandman.film.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

*/
/**
 * @author sunpeikai
 * @version CacheConfig, v0.1 2019/1/10 16:53
 *//*

@Configuration
@EnableCaching
public class CacheConfig {
    */
/**
     * ehcache 主要的管理器
     * @param bean
     * @return
     *//*

    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
        return new EhCacheCacheManager(bean.getObject());
    }
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factoryBean.setShared(true);
        return factoryBean;
    }
}
*/
