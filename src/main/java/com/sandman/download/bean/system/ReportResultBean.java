/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.bean.system;

/**
 * @author sunpeikai
 * @version ReportResultBean, v0.1 2019/1/10 15:15
 */
public class ReportResultBean {
    private int day;
    private int week;
    private int month;

    public ReportResultBean() {
    }

    public ReportResultBean(int day, int week, int month) {
        this.day = day;
        this.week = week;
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
