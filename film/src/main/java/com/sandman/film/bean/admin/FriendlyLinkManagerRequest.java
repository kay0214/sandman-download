/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.bean.admin;

import com.sandman.film.base.BasePage;

/**
 * @author sunpeikai
 * @version FriendlyLinkManagerRequest, v0.1 2019/1/11 18:06
 */
public class FriendlyLinkManagerRequest extends BasePage {
    private String linkName;
    private String linkUrl;
    private Integer status;

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
