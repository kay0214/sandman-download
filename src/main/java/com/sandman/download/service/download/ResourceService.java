/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.download;

import com.sandman.download.base.BaseService;
import com.sandman.download.bean.download.ResourceBean;
import com.sandman.download.dao.mysql.download.model.auto.Resource;

import java.util.List;

/**
 * @author sunpeikai
 * @version ResourceService, v0.1 2018/12/6 18:02
 */
public interface ResourceService extends BaseService {

    /**
     * 获取资源信息（type：1 -> 按照下载次数倒叙排序，else -> 按照创建时间倒叙)
     * @auth sunpeikai
     * @param
     * @return
     */
    List<Resource> getResourceByType(ResourceBean resourceBean);

    /**
     * 获取资源count（type：1 -> 按照下载次数倒叙排序，else -> 按照创建时间倒叙)
     * @auth sunpeikai
     * @param
     * @return
     */
    int getResourceCountByType(ResourceBean resourceBean);

    /**
     * 搜索资源
     * @auth sunpeikai
     * @param
     * @return
     */
    List<Resource> searchResource(ResourceBean resourceBean);

    /**
     * 资源分页
     * @auth sunpeikai
     * @param
     * @return
     */
    List<Resource> getList(ResourceBean resourceBean);

    /**
     * 获取热门资源(调用其他方法，这里封装一个方法是为了方便缓存)
     * @auth sunpeikai
     * @param
     * @return
     */
    List<Resource> getHotResources();
}
