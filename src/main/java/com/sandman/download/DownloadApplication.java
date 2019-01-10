package com.sandman.download;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

// 取消数据库的自动配置，改为手动配置（用于多数据源的配置）
@EnableScheduling
//@EnableCaching
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DownloadApplication {

    public static void main(String[] args) {
        SpringApplication.run(DownloadApplication.class, args);
    }
}
