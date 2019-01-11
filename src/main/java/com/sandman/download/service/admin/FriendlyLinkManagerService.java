/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.admin;

import com.sandman.download.base.BaseService;
import com.sandman.download.bean.admin.FriendlyLinkManagerRequest;
import com.sandman.download.dao.mysql.system.model.auto.FriendlyLink;

import java.util.List;

/**
 * @author sunpeikai
 * @version FriendlyLinkManagerService, v0.1 2019/1/10 17:32
 */
public interface FriendlyLinkManagerService extends BaseService {

    /**
     * 友情链接count
     * @auth sunpeikai
     * @param
     * @return
     */
    int getFriendlyLinkCount(FriendlyLinkManagerRequest friendlyLinkManagerRequest);

    /**
     * 分页获取友情链接列表
     * @auth sunpeikai
     * @param
     * @return
     */
    List<FriendlyLink> searchList(FriendlyLinkManagerRequest friendlyLinkManagerRequest);

    /**
     * 根据id获取友情链接
     * @auth sunpeikai
     * @param
     * @return
     */
    FriendlyLink getFriendlyLinkById(Integer id);

    /**
     * 更新友情链接
     * @auth sunpeikai
     * @param
     * @return
     */
    int update(FriendlyLink friendlyLink);
}
