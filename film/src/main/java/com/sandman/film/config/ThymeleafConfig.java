/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.config;

import com.sandman.film.config.thymeleaf.FileSizeDialect;
import com.sandman.film.config.thymeleaf.FilmNameDialect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 * @author sunpeikai
 * @version ThymeleafConfig, v0.1 2018/12/7 15:29
 */
@Configuration
public class ThymeleafConfig {
    @Bean
    @ConditionalOnMissingBean
    public FileSizeDialect fileSizeDialect(){
        return new FileSizeDialect();
    }

    @Bean
    @ConditionalOnMissingBean
    public FilmNameDialect filmNameDialect(){
        return new FilmNameDialect();
    }
}
