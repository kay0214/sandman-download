/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.scheduled;

import com.sandman.emmmoe.service.EmmmoeService;
import com.sandman.emmmoe.service.RootUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author sunpeikai
 * @version RootUrlScheduledTask, v0.1 2018/12/20 14:46
 */
@Component
public class RootUrlScheduledTask {

    private static final Logger logger = LoggerFactory.getLogger(RootUrlScheduledTask.class);

    @Autowired
    private RootUrlService rootUrlService;
    @Autowired
    private EmmmoeService emmmoeService;

    // 每12小时更新一次,获取最新登船地址
    @Scheduled(fixedRate = 12 * 60 * 60 * 1000)
    public void updateRootUrl(){
        rootUrlService.getEnableRootUrl();
        logger.info("获取最新登船地址完成!");
    }

    // 每24小时获取一次未完成的百度网盘地址
    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void updateNetDisk(){
        int handle = emmmoeService.getNetDisk();
        logger.info("获取百度网盘地址完成，本次更新[{}]个网盘资源",handle);
    }
}
