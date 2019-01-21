/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.utils;

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
     * 获取某一天的开始时间，即00:00:00
     * @auth sunpeikai
     * @param
     * @return
     */
    public static Date getDayStart(Date today){
        String start = new SimpleDateFormat("yyyy-MM-dd").format(today) + " 00:00:00";
        Date startDate = new Date();
        try{
            startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(start);
        }catch (Exception e){
            System.out.println("抛出异常");
        }
        return startDate;
    }

    /**
     * 获取某一天的结束时间，即23:59:59
     * @auth sunpeikai
     * @param
     * @return
     */
    public static Date getDayEnd(Date today){
        String start = new SimpleDateFormat("yyyy-MM-dd").format(today) + " 23:59:59";
        Date startDate = new Date();
        try{
            startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(start);
        }catch (Exception e){
            System.out.println("抛出异常");
        }
        return startDate;
    }

    /**
     * 获取afterMonth天后的日期
     * @auth sunpeikai
     * @param
     * @return
     */
    public static Date getMonthsAfter(Date date, int afterMonth){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, afterMonth);
        date = cal.getTime();
        return date;
    }

    /**
     * 获取afterDay天后的日期
     * @auth sunpeikai
     * @param
     * @return
     */
    public static Date getDaysAfter(Date date, int afterDay){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, afterDay);
        date = cal.getTime();
        return date;
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

    /**
     * 获取当前时间
     * @auth sunpeikai
     * @param
     * @return
     */
    public static Date getNow(){
        return new Date();
    }

    /**
     * 获取当前时间的String类型
     * @auth sunpeikai
     * @param
     * @return
     */
    public static String getNowStryyyyMMddHHmmss(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(new Date());
    }

}
