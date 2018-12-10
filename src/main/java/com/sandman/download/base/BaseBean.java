/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.base;

/**
 * @author sunpeikai
 * @version BaseBean, v0.1 2018/12/6 17:11
 */
public class BaseBean {
    public BaseBean(){

    }
    public BaseBean(int currPage,int pageSize){
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.computeLimit();
    }
    /**
     * 当前页码
     */
    private Integer currPage = 0;

    /**
     * 当前页条数
     */
    private Integer pageSize = 10;

    /**
     * 数据库用limitStart
     * @auth sunpeikai
     * @param
     * @return
     */
    private Integer limitStart = -1;
    /**
     * 数据库用limitEnd
     * @auth sunpeikai
     * @param
     * @return
     */
    private Integer limitEnd = -1;

    public void computeLimit(){
        limitStart = (currPage-1) * pageSize;
        limitEnd = pageSize;
        System.out.println("计算分页:limitStart=" + limitStart + "::limitEnd=" + limitEnd);
    }

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart = limitStart;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd = limitEnd;
    }
}
