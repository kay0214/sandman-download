/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.bean.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sunpeikai
 * @version OverviewResultBean, v0.1 2019/1/14 17:00
 */
public class OverviewResultBean {
    private List<String> legend = new ArrayList<>();
    private List<String> date = new ArrayList<>();
    private List<OverviewDataResult> data = new ArrayList<>();

    public OverviewResultBean() {
    }

    public OverviewResultBean(List<String> legend, List<String> date, List<OverviewDataResult> data) {
        this.legend = legend;
        this.date = date;
        this.data = data;
    }

    public List<String> getLegend() {
        return legend;
    }

    public void setLegend(List<String> legend) {
        this.legend = legend;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<OverviewDataResult> getData() {
        return data;
    }

    public void setData(List<OverviewDataResult> data) {
        this.data = data;
    }
}
