/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.bean.admin;

import com.sandman.film.base.BasePage;

/**
 * @author sunpeikai
 * @version LinkManagerRequest, v0.1 2019/1/11 20:06
 */
public class LinkManagerRequest extends BasePage {
    private String apiName;
    private String apiUrl;
    private Integer status;
    private Integer secureVisitFlag;

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSecureVisitFlag() {
        return secureVisitFlag;
    }

    public void setSecureVisitFlag(Integer secureVisitFlag) {
        this.secureVisitFlag = secureVisitFlag;
    }
}
