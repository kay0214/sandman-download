/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.bean.film;

import com.sandman.film.base.BasePage;

/**
 * @author sunpeikai
 * @version MyDownloadBean, v0.1 2018/12/18 18:01
 */
public class MyDownloadBean extends BasePage {

    private Integer userId;

    private String filmName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }
}
