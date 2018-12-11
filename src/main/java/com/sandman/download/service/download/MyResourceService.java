/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.download;

import com.sandman.download.base.BaseService;
import com.sandman.download.bean.download.MyResourceBean;
import com.sandman.download.dao.mysql.download.model.auto.Resource;

import java.util.List;

/**
 * @author sunpeikai
 * @version MyResourceService, v0.1 2018/12/10 13:58
 */
public interface MyResourceService extends BaseService {

    /**
     * 获取我的资源列表
     * @auth sunpeikai
     * @param
     * @return
     */
    List<Resource> getMyResourceList(MyResourceBean myResourceBean);

    /**
     * 获取我的资源列表count
     * @auth sunpeikai
     * @param
     * @return
     */
    int getMyResourceListCount(MyResourceBean myResourceBean);

    /**
     * 根据id获取资源
     * @auth sunpeikai
     * @param
     * @return
     */
    Resource searchById(Integer id);

    /**
     * 根据id假删
     * @auth sunpeikai
     * @param
     * @return
     */
    int deleteById(Resource resource);
}
