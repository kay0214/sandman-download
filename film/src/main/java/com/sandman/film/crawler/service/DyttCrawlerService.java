/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.crawler.service;

/**
 * @author sunpeikai
 * @version DyttCrawlerService, v0.1 2019/3/5 15:01
 */
public interface DyttCrawlerService {

    /**
     * 从电影天堂爬取数据
     * @auth sunpeikai
     * @param
     * @return
     */
    void crawFilm();

    /**
     * 处理图片
     * @auth sunpeikai
     * @param
     * @return
     */
    void imageHandle();
}
