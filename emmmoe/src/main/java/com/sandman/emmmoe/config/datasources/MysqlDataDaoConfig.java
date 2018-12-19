package com.sandman.emmmoe.config.datasources;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
// 指定dao的地址，指定sqlSessionFactory的名称
@MapperScan(basePackages = "com.sandman.emmmoe.dao.mysql.emmmoe", sqlSessionFactoryRef = "mysqlSqlSessionFactory")
public class MysqlDataDaoConfig {
    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql.emmmoe") // application.properteis中对应属性的前缀
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "mysqlSqlSessionFactory")
    public SqlSessionFactory mysqlSqlSessionFactory() throws Exception {
        /*@Qualifier("mysqlDataSource") DataSource mysqlDataSource*/
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(mysqlDataSource());
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/sandman/emmmoe/dao/mysql/emmmoe/mapper/xml/**/*.xml"));
        return factoryBean.getObject();
    }
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(mysqlSqlSessionFactory()); // 使用上面配置的Factory
        return template;
    }
}