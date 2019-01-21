/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.bean.admin;

import java.util.List;

/**
 * @author sunpeikai
 * @version OverviewDataResult, v0.1 2019/1/14 17:21
 */
public class OverviewDataResult {
    private int type;
    private List<Integer> data;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
