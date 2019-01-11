/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.admin;

import com.sandman.download.base.BaseService;
import com.sandman.download.dao.mysql.system.model.auto.SecureConfig;

import java.util.List;

/**
 * @author sunpeikai
 * @version LinkManagerService, v0.1 2019/1/10 17:38
 */
public interface LinkManagerService extends BaseService {

    /**
     * 获取接口count
     * @auth sunpeikai
     * @param
     * @return
     */
    int getLinkCount();

    /**
     * 分页获取接口列表
     * @auth sunpeikai
     * @param
     * @return
     */
    List<SecureConfig> searchList(Integer page, Integer limit);

    /**
     * 根据id获取接口
     * @auth sunpeikai
     * @param
     * @return
     */
    SecureConfig getSecureConfigById(Integer id);

    /**
     * 更新接口
     * @auth sunpeikai
     * @param
     * @return
     */
    int update(SecureConfig secureConfig);
}
