/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.scheduled;

import com.sandman.film.crawler.service.DyttCrawlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author sunpeikai
 * @version CrawlerScheduledTak, v0.1 2019/3/5 11:55
 */
//@Component
public class CrawlerScheduledTak {

    private static final Logger logger = LoggerFactory.getLogger(CrawlerScheduledTak.class);

    @Autowired
    private DyttCrawlerService dyttCrawlerService;

    // 每12小时爬取一次电影天堂
    //@Scheduled(fixedRate = 12 * 60 * 60 * 1000)
    public void crawler(){
        logger.info("开始爬取电影天堂...");
        // 爬取
        dyttCrawlerService.crawFilm();
        // 处理图片
        dyttCrawlerService.imageHandle();
        logger.info("电影天堂爬取完成...");
    }


}
