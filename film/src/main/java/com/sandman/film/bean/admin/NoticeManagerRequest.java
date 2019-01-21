/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.bean.admin;

import com.sandman.film.base.BasePage;

/**
 * @author sunpeikai
 * @version NoticeManagerRequest, v0.1 2019/1/11 17:57
 */
public class NoticeManagerRequest extends BasePage {
    private String title;
    private String content;
    private Integer status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
