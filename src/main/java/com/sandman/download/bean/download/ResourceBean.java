/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.bean.download;

import com.sandman.download.base.BaseBean;

/**
 * @author sunpeikai
 * @version ResourceBean, v0.1 2018/12/6 17:17
 */
public class ResourceBean extends BaseBean {

    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public ResourceBean(){
        super();
    }
    public ResourceBean(int currPage,int pageSize){
        super(currPage, pageSize);
    }
    public ResourceBean(int currPage,int pageSize,int type){
        super(currPage, pageSize);
        this.type = type;
    }
    private int type = 0;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
