/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author sunpeikai
 * @version DateUtils, v0.1 2018/12/4 16:26
 */
public class DateUtils {

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
