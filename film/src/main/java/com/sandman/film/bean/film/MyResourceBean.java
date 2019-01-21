/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.bean.film;

import com.sandman.film.base.BaseBean;

/**
 * @author sunpeikai
 * @version MyResourceBean, v0.1 2018/12/10 15:43
 */
public class MyResourceBean extends BaseBean {
    private Integer userId;

    private String resourceName;

    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
