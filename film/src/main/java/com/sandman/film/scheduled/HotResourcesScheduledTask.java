/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sunpeikai
 * @version HotResourcesScheduledTask, v0.1 2018/12/20 14:46
 */
//@Component
public class HotResourcesScheduledTask {

    private static final Logger logger = LoggerFactory.getLogger(HotResourcesScheduledTask.class);

    /*@Autowired
    private FilmService resourceService;

    // 每5分钟获取一次热门资源列表
    //@Scheduled(fixedRate = 5 * 60 * 1000)
    public void updateRootUrl(){
        CommonConstant.HOT_RESOURCES = resourceService.getResourceByType(new FilmBean(1, CommonConstant.HOT_RESOURCES_LIMIT,1));
        if(!CollectionUtils.isEmpty(CommonConstant.HOT_RESOURCES)){
            logger.info("获取热门资源列表成功（每5分钟获取一次）");
        }else{
            logger.info("获取热门资源列表失败（每5分钟获取一次）");
        }
    }*/

}
