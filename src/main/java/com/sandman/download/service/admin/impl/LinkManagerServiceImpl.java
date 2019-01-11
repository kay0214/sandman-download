/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.admin.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.bean.admin.LinkManagerRequest;
import com.sandman.download.dao.mysql.system.model.auto.SecureConfig;
import com.sandman.download.dao.mysql.system.model.auto.SecureConfigExample;
import com.sandman.download.service.admin.LinkManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author sunpeikai
 * @version LinkManagerServiceImpl, v0.1 2019/1/10 17:38
 */
@Service
public class LinkManagerServiceImpl extends BaseServiceImpl implements LinkManagerService {

    /**
     * 获取接口count
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getLinkCount(LinkManagerRequest linkManagerRequest) {
        SecureConfigExample secureConfigExample = convertExample(linkManagerRequest);
        return secureConfigMapper.countByExample(secureConfigExample);
    }

    /**
     * 分页获取接口列表
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<SecureConfig> searchList(LinkManagerRequest linkManagerRequest) {
        SecureConfigExample secureConfigExample = convertExample(linkManagerRequest);
        secureConfigExample.setOrderByClause("create_time desc");
        computePage(linkManagerRequest.getPage(), linkManagerRequest.getLimit());
        secureConfigExample.setLimitStart(limitStart);
        secureConfigExample.setLimitEnd(limitEnd);
        return secureConfigMapper.selectByExample(secureConfigExample);
    }

    private SecureConfigExample convertExample(LinkManagerRequest linkManagerRequest){
        SecureConfigExample secureConfigExample = new SecureConfigExample();
        SecureConfigExample.Criteria criteria = secureConfigExample.createCriteria().andDelFlagEqualTo(0);
        if(StringUtils.isNotBlank(linkManagerRequest.getApiName())){
            criteria.andApiNameLike("%" + linkManagerRequest.getApiName() + "%");
        }
        if(StringUtils.isNotBlank(linkManagerRequest.getApiUrl())){
            criteria.andApiUrlLike("%" + linkManagerRequest.getApiUrl() + "%");
        }
        if(linkManagerRequest.getStatus() != null){
            criteria.andStatusEqualTo(linkManagerRequest.getStatus());
        }
        if(linkManagerRequest.getSecureVisitFlag() != null){
            criteria.andSecureVisitFlagEqualTo(linkManagerRequest.getSecureVisitFlag());
        }
        return secureConfigExample;
    }

    /**
     * 根据id获取接口
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public SecureConfig getSecureConfigById(Integer id) {
        return secureConfigMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新接口
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int update(SecureConfig secureConfig) {
        return secureConfigMapper.updateByPrimaryKeySelective(secureConfig);
    }

    /**
     * 新增接口
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int insertApi(SecureConfig secureConfig) {
        Date now = new Date();
        secureConfig.setCreateTime(now);
        secureConfig.setUpdateTime(now);
        secureConfig.setDelFlag(0);
        return secureConfigMapper.insertSelective(secureConfig);
    }
}
