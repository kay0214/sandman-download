/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.admin.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.dao.mysql.system.model.auto.SecureConfig;
import com.sandman.download.dao.mysql.system.model.auto.SecureConfigExample;
import com.sandman.download.service.admin.LinkManagerService;
import org.springframework.stereotype.Service;

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
    public int getLinkCount() {
        SecureConfigExample secureConfigExample = new SecureConfigExample();
        secureConfigExample.createCriteria().andDelFlagEqualTo(0);
        return secureConfigMapper.countByExample(secureConfigExample);
    }

    /**
     * 分页获取接口列表
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<SecureConfig> searchList(Integer page, Integer limit) {
        SecureConfigExample secureConfigExample = new SecureConfigExample();
        secureConfigExample.setOrderByClause("create_time desc");
        computePage(page, limit);
        secureConfigExample.setLimitStart(limitStart);
        secureConfigExample.setLimitEnd(limitEnd);
        secureConfigExample.createCriteria().andDelFlagEqualTo(0);
        return secureConfigMapper.selectByExample(secureConfigExample);
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
}
