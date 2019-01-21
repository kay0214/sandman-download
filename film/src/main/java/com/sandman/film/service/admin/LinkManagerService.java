/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.admin;

import com.sandman.film.base.BaseService;
import com.sandman.film.bean.admin.LinkManagerRequest;
import com.sandman.film.dao.mysql.system.model.auto.SecureConfig;

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
    int getLinkCount(LinkManagerRequest linkManagerRequest);

    /**
     * 分页获取接口列表
     * @auth sunpeikai
     * @param
     * @return
     */
    List<SecureConfig> searchList(LinkManagerRequest linkManagerRequest);

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

    /**
     * 新增接口
     * @auth sunpeikai
     * @param
     * @return
     */
    int insertApi(SecureConfig secureConfig);
}
