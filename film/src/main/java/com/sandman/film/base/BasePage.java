/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.base;

/**
 * @author sunpeikai
 * @version BasePage, v0.1 2019/1/11 17:24
 */
public class BasePage {
    private Integer page;
    private Integer limit;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = (page==null)?1:page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = (limit==null)?10:limit;
    }
}
