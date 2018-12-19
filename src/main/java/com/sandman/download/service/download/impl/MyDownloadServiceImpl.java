/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.download.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.bean.download.MyDownloadBean;
import com.sandman.download.bean.download.MyDownloadResultBean;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.dao.mysql.download.model.auto.ResourceLog;
import com.sandman.download.dao.mysql.download.model.auto.ResourceLogExample;
import com.sandman.download.service.download.MyDownloadService;
import com.sandman.download.utils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author sunpeikai
 * @version MyDownloadServiceImpl, v0.1 2018/12/18 17:23
 */
@Service
public class MyDownloadServiceImpl extends BaseServiceImpl implements MyDownloadService {

    /**
     * 获取我的下载记录count
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getAllMyDownloadCount(MyDownloadBean myDownloadBean) {
        ResourceLogExample resourceLogExample = convertExample(myDownloadBean);
        return resourceLogMapper.countByExample(resourceLogExample);
    }

    /**
     * 获取我的下载记录
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<MyDownloadResultBean> getAllMyDownload(MyDownloadBean myDownloadBean) {
        ResourceLogExample resourceLogExample = convertExample(myDownloadBean);
        List<ResourceLog> resourceLogList = resourceLogMapper.selectByExample(resourceLogExample);
        if(!CollectionUtils.isEmpty(resourceLogList)){
            // bean 转换
            List<MyDownloadResultBean> myDownloadResultBeanList = BeanUtils.convertBeanList(resourceLogList,MyDownloadResultBean.class);
            for(MyDownloadResultBean myDownloadResultBean : myDownloadResultBeanList){
                Resource resource = getResourceById(myDownloadResultBean.getResourceId());
                myDownloadResultBean.setResource(resource);
            }
            return myDownloadResultBeanList;
        }
        return null;
    }


    private ResourceLogExample convertExample(MyDownloadBean myDownloadBean){
        ResourceLogExample resourceLogExample = new ResourceLogExample();
        ResourceLogExample.Criteria criteria = resourceLogExample.createCriteria();
        criteria.andUserIdEqualTo(myDownloadBean.getUserId()).andDelFlagEqualTo(0).andTypeEqualTo(2);
        if(myDownloadBean.getLimitStart() >= 0){
            resourceLogExample.setLimitStart(myDownloadBean.getLimitStart());
            resourceLogExample.setLimitEnd(myDownloadBean.getLimitEnd());
        }
        if(StringUtils.isNotBlank(myDownloadBean.getResourceName())){
            criteria.andResourceNameLike("%" + myDownloadBean.getResourceName() + "%");
        }
        return resourceLogExample;
    }
}
