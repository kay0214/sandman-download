/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.bean.film;

import com.sandman.film.base.BaseBean;

/**
 * @author sunpeikai
 * @version FilmBean, v0.1 2018/12/6 17:17
 */
public class FilmBean extends BaseBean {

    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public FilmBean(){
        super();
    }
    public FilmBean(int currPage, int pageSize){
        super(currPage, pageSize);
    }
    public FilmBean(int currPage, int pageSize, int type){
        super(currPage, pageSize);
        this.type = type;
    }
    public FilmBean(int currPage, int pageSize, int type, String search){
        super(currPage, pageSize);
        this.type = type;
        this.search = search;
    }
    private int type = 0;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
