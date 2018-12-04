/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author sunpeikai
 * @version DateUtils, v0.1 2018/12/4 16:26
 */
public class DateUtils {

    /**
     * 判断传入的日期是否在当前日期之前(用于判断是否过期)
     * @auth sunpeikai
     * @param
     * @return
     */
    public static boolean beforeNow(Date date){
        Date now = new Date();
        return now.getTime()>date.getTime();
    }

    /**
     * 获取afterHour小时后的日期
     * @param date
     * @param afterHour
     * @return
     */
    public static Date getHoursAfter(Date date, int afterHour){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, afterHour);
        date = cal.getTime();
        return date;
    }
    /**
     * 获取afterMin分钟后的日期
     * @param date
     * @param afterMin
     * @return
     */
    public static Date getMinutesAfter(Date date, int afterMin) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 24小时制
        cal.add(Calendar.MINUTE, afterMin);
        date = cal.getTime();
        return date;
    }

}
