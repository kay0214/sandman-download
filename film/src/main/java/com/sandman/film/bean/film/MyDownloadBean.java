/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.bean.film;

import com.sandman.film.base.BaseBean;

/**
 * @author sunpeikai
 * @version MyDownloadBean, v0.1 2018/12/18 18:01
 */
public class MyDownloadBean extends BaseBean {

    private Integer userId;

    private String resourceName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
