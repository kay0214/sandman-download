/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.base;

/**
 * @author sunpeikai
 * @version BaseBean, v0.1 2018/12/6 17:11
 */
public class BaseBean {
    /**
     * 当前页码
     */
    private int currPage;

    /**
     * 当前页条数
     */
    private int pageSize = 20;

    /**
     * 数据库用limitStart
     * @auth sunpeikai
     * @param
     * @return
     */
    private int limitStart = -1;
    /**
     * 数据库用limitEnd
     * @auth sunpeikai
     * @param
     * @return
     */
    private int limitEnd = -1;
    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setLimitStart(int limitStart) {
        this.limitStart = limitStart;
    }

    public int getLimitEnd() {
        return limitEnd;
    }

    public void setLimitEnd(int limitEnd) {
        this.limitEnd = limitEnd;
    }
}
