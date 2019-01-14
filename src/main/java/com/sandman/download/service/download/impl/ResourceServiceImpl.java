/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.download.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.bean.download.ResourceBean;
import com.sandman.download.constant.CommonConstant;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.dao.mysql.download.model.auto.ResourceExample;
import com.sandman.download.service.download.ResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunpeikai
 * @version ResourceServiceImpl, v0.1 2018/12/6 18:02
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl implements ResourceService {

    /**
     * 获取资源信息（type：1 -> 按照下载次数倒叙排序，else -> 按照创建时间倒叙)
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<Resource> getResourceByType(ResourceBean resourceBean) {
        ResourceExample resourceExample = convertExample(resourceBean);
        return resourceMapper.selectByExample(resourceExample);
    }

    /**
     * 获取资源count（type：1 -> 按照下载次数倒叙排序，else -> 按照创建时间倒叙)
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getResourceCountByType(ResourceBean resourceBean) {
        ResourceExample resourceExample = convertExample(resourceBean);
        return resourceMapper.countByExample(resourceExample);
    }

    /**
     * 搜索资源
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<Resource> searchResource(ResourceBean resourceBean) {
        ResourceExample resourceExample = convertExample(resourceBean);
        return resourceMapper.selectByExample(resourceExample);
    }

    /**
     * 获取资源列表分页显示
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<Resource> getList(ResourceBean resourceBean) {
        ResourceExample resourceExample = convertExample(resourceBean);
        return resourceMapper.selectByExample(resourceExample);
    }

    /**
     * 获取热门资源(调用其他方法，这里封装一个方法是为了方便缓存)
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @Cacheable(value = "hotResourcesCache")
    public List<Resource> getHotResources() {
        logger.info("从mysql中获取热门资源");
        return getResourceByType(new ResourceBean(1, CommonConstant.HOT_RESOURCES_LIMIT,1));
    }

    private ResourceExample convertExample(ResourceBean resourceBean){
        ResourceExample resourceExample = new ResourceExample();
        if(StringUtils.isNotBlank(resourceBean.getSearch())){
            resourceExample.or().andStatusEqualTo(1).andDelFlagEqualTo(0).andResourceNameLike("%" + resourceBean.getSearch() + "%");
            resourceExample.or().andStatusEqualTo(1).andDelFlagEqualTo(0).andResourceDescLike("%" + resourceBean.getSearch() + "%");
        }else{
            resourceExample.createCriteria().andStatusEqualTo(1).andDelFlagEqualTo(0);
        }
        if(resourceBean.getLimitStart()>=0){
            resourceExample.setLimitStart(resourceBean.getLimitStart());
            resourceExample.setLimitEnd(resourceBean.getLimitEnd());
        }
        if(resourceBean.getType() == 1){
            resourceExample.setOrderByClause("download_count desc");
        }else{
            resourceExample.setOrderByClause("create_time desc");
        }
        return resourceExample;
    }
}
