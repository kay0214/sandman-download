/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.admin.impl;

import com.sandman.film.base.BaseServiceImpl;
import com.sandman.film.bean.admin.OverviewDataResult;
import com.sandman.film.bean.admin.OverviewResultBean;
import com.sandman.film.dao.mysql.film.model.auto.FilmLog;
import com.sandman.film.dao.mysql.film.model.auto.FilmLogExample;
import com.sandman.film.dao.mysql.film.model.auto.FindPlzExample;
import com.sandman.film.dao.mysql.system.model.auto.UserExample;
import com.sandman.film.dao.mysql.system.model.auto.UserLoginLogExample;
import com.sandman.film.service.admin.AdminService;
import com.sandman.film.utils.DateUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author sunpeikai
 * @version AdminServiceImpl, v0.1 2019/1/10 11:11
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl implements AdminService {

    /**
     * 根据日期获取下载资源数量
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getBuyCount(Date start, Date end) {
        FilmLogExample filmLogExample = new FilmLogExample();
        filmLogExample.createCriteria().andDelFlagEqualTo(0).andCreateTimeBetween(start, end);
        return filmLogMapper.countByExample(filmLogExample);
    }

    /**
     * 根据日期获取活跃用户数量
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getActiveCount(Date start, Date end) {
        UserLoginLogExample userLoginLogExample = new UserLoginLogExample();
        userLoginLogExample.createCriteria().andLoginTimeBetween(start, end);
        return userLoginLogMapper.countByExample(userLoginLogExample);
    }

    /**
     * 根据日期获取未完全上传数量
     * @auth sunpeikai
     * @param
     * @return
     */
    public int getNotCompleteUploadCount(Date start, Date end) {
        /*ResourceExample resourceExample = new ResourceExample();
        resourceExample.createCriteria().andCreateTimeBetween(start, end).andResourceNameIsNull().andResourceDescIsNull().andDelFlagEqualTo(1);
        return resourceMapper.countByExample(resourceExample);*/
        return 1;
    }

    /**
     * 根据日期获取注册用户数量
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getRegisterCount(Date start, Date end) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAvailableEqualTo(1).andRoleNotEqualTo(0).andRegTimeBetween(start, end);
        return userMapper.countByExample(userExample);
    }

    /**
     * 根据日期获取留言数量
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getFindPlzCount(Date start, Date end) {
        FindPlzExample findPlzExample = new FindPlzExample();
        findPlzExample.createCriteria().andDelFlagEqualTo(0).andCreateTimeBetween(start, end);
        return findPlzMapper.countByExample(findPlzExample);
    }

    /**
     * 获取周下载量，封装起来比较好缓存
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @Cacheable(value = "buyWeekCache")
    public int getBuyCountWeek(Date start, Date end) {
        logger.info("后台报表-下载数量(周),从MySQL中获取数据");
        return getBuyCount(start, end);
    }

    /**
     * 获取月下载量，封装起来比较好缓存
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @Cacheable(value = "buyMonthCache")
    public int getBuyCountMonth(Date start, Date end) {
        logger.info("后台报表-下载数量(月),从MySQL中获取数据");
        return getBuyCount(start, end);
    }

    /**
     * 获取周活跃用户数量
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @Cacheable(value = "activeWeekCache")
    public int getActiveCountWeek(Date start, Date end) {
        logger.info("后台报表-活跃用户数量(周),从MySQL中获取数据");
        return getActiveCount(start, end);
    }

    /**
     * 获取月活跃用户数量
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @Cacheable(value = "activeMonthCache")
    public int getActiveCountMonth(Date start, Date end) {
        logger.info("后台报表-活跃用户数量(月),从MySQL中获取数据");
        return getActiveCount(start, end);
    }

    /**
     * 获取周注册用户数量
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @Cacheable(value = "registerWeekCache")
    public int getRegisterCountWeek(Date start, Date end) {
        logger.info("后台报表-注册用户数量(周),从MySQL中获取数据");
        return getRegisterCount(start, end);
    }

    /**
     * 获取月注册用户数量
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @Cacheable(value = "registerMonthCache")
    public int getRegisterCountMonth(Date start, Date end) {
        logger.info("后台报表-注册用户数量(月),从MySQL中获取数据");
        return getRegisterCount(start, end);
    }

    /**
     * 获取周留言数量
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @Cacheable(value = "findPlzWeekCache")
    public int getFindPlzCountWeek(Date start, Date end) {
        logger.info("后台报表-留言求片数量(周),从MySQL中获取数据");
        return getFindPlzCount(start, end);
    }

    /**
     * 获取月留言数量
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @Cacheable(value = "findPlzMonthCache")
    public int getFindPlzCountMonth(Date start, Date end) {
        logger.info("后台报表-留言求片数量(月),从MySQL中获取数据");
        return getFindPlzCount(start, end);
    }

    /**
     * 获取折线图数据
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    @Cacheable(value = "overviewCache")
    public OverviewResultBean getOverview() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
        List<String> legend = new ArrayList<>();
        List<String> dateStr = new ArrayList<>();
        List<Date> dates = new ArrayList<>();
        List<OverviewDataResult> dataList = new ArrayList<>();
        legend.add("购买数量");
        legend.add("注册用户数量");
        legend.add("留言数量");
        // 获取近30天的日期
        for(int i=30;i>0;i--){
            String dateString = dateFormat.format(DateUtils.getDaysAfter(new Date(),-i));
            dates.add(DateUtils.getDaysAfter(new Date(),-i));
            dateStr.add(dateString);
        }
        for(int i=0;i<legend.size();i++){
            OverviewDataResult dataResult = new OverviewDataResult();
            List<Integer> data = new ArrayList<>();
            for(Date date:dates){
                Date start = DateUtils.getDayStart(date);
                Date end = DateUtils.getDayEnd(date);
                data.add(getResultByType(i,start,end));
            }
            dataResult.setData(data);
            dataResult.setType(i);
            dataList.add(dataResult);
        }
        return new OverviewResultBean(legend,dateStr,dataList);
    }

    private int getResultByType(int type,Date start,Date end){
        int result = 0;
        switch (type){
            case 0:result = getBuyCount(start, end);break;
            case 1:result = getRegisterCount(start, end);break;
            case 2:result = getFindPlzCount(start, end);break;
        }
        return result;
    }
}
