/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.bean.admin;

import com.sandman.film.base.BasePage;

/**
 * @author sunpeikai
 * @version TemplateManagerRequest, v0.1 2019/1/11 19:54
 */
public class TemplateManagerRequest extends BasePage {
    private String tplCode;
    private String tplName;
    private Integer status;

    public String getTplCode() {
        return tplCode;
    }

    public void setTplCode(String tplCode) {
        this.tplCode = tplCode;
    }

    public String getTplName() {
        return tplName;
    }

    public void setTplName(String tplName) {
        this.tplName = tplName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
