/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.controller.admin;

import com.sandman.film.base.BaseController;
import com.sandman.film.base.BaseResult;
import com.sandman.film.bean.admin.OverviewResultBean;
import com.sandman.film.bean.system.ReportResultBean;
import com.sandman.film.config.SystemConfig;
import com.sandman.film.constant.CommonConstant;
import com.sandman.film.constant.ReturnMessage;
import com.sandman.film.dao.mysql.system.model.auto.User;
import com.sandman.film.service.admin.AdminService;
import com.sandman.film.utils.DateUtils;
import com.sandman.film.utils.PasswordEncrypt;
import com.sandman.film.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author sunpeikai
 * @version UserController, v0.1 2018/12/3 11:25
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    @GetMapping(value = "/init")
    public ModelAndView init(){

        return new ModelAndView("admin/login");
    }

    @ResponseBody
    @PostMapping(value = "/login")
    public BaseResult login(String username,String password){
        logger.info("login -> username:[{}],password:[{}]",username,password);
        User user = adminService.getUserByUsername(username);
        if(user != null){
            // 是管理员角色
            if(user.getRole() == 0){
                // 该用户存在，验证密码正确性
                String passwordEncrypt = PasswordEncrypt.getPasswordEncrypt(password,user.getSalt());
                if(passwordEncrypt.equals(user.getPassword())){
                    logger.info("与数据库密码一致，允许登录");
                    user.setIconUrl(SystemConfig.getLinePathPrefix() + user.getIconUrl());
                    SessionUtils.setSessionAttribute("user",user);
                    SessionUtils.setSessionExpireTime(CommonConstant.LOGIN_EXPIRE);
                    return new BaseResult(ReturnMessage.SUCCESS_USER_LOGIN);
                }else{
                    logger.info("与数据库密码不一致，不允许登录");
                    return new BaseResult(ReturnMessage.ERR_PASSWORD);
                }
            }
            return new BaseResult(ReturnMessage.ERR_USER_IS_NOT_ADMIN);
        }else{
            logger.info("该用户不存在");
            return new BaseResult(ReturnMessage.ERR_USER_NOT_EXIST);
        }
    }

    @GetMapping(value = "/index")
    public ModelAndView index(){
        long start = System.currentTimeMillis();
        Date todayStart = DateUtils.getDayStart(new Date());
        Date todayEnd = DateUtils.getDayEnd(new Date());
        Date lastDayEnd = DateUtils.getDayEnd(DateUtils.getDaysAfter(new Date(),-1));
        Date weekStart = DateUtils.getDayStart(DateUtils.getDaysAfter(new Date(),-7));
        Date monthStart = DateUtils.getDayStart(DateUtils.getMonthsAfter(new Date(),-1));
        // 上传数量
        int uploadWeek = adminService.getUploadCountWeek(weekStart,lastDayEnd);
        int uploadMonth = adminService.getUploadCountMonth(weekStart,lastDayEnd);
        int uploadToday = adminService.getUploadCount(todayStart,todayEnd);
        // 下载数量
        int downloadWeek = adminService.getDownloadCountWeek(weekStart,lastDayEnd);
        int downloadMonth = adminService.getDownloadCountMonth(monthStart,lastDayEnd);
        int downloadToday = adminService.getDownloadCount(todayStart,todayEnd);
        // 活跃用户数量
        int activeWeek = adminService.getActiveCountWeek(weekStart,lastDayEnd);
        int activeMonth = adminService.getActiveCountMonth(monthStart,lastDayEnd);
        int activeToday = adminService.getActiveCount(todayStart,todayEnd);
        // 注册用户数量
        int registerWeek = adminService.getRegisterCountWeek(weekStart,lastDayEnd);
        int registerMonth = adminService.getRegisterCountMonth(monthStart,lastDayEnd);
        int registerToday = adminService.getRegisterCount(todayStart,todayEnd);
        // 管理员首页查询
        ReportResultBean upload = new ReportResultBean(uploadToday,uploadWeek + uploadToday,uploadMonth + uploadToday);
        ReportResultBean download = new ReportResultBean(downloadToday,downloadWeek + downloadToday,downloadMonth + downloadToday);
        ReportResultBean active = new ReportResultBean(activeToday,activeWeek + activeToday,activeMonth + activeToday);
        ReportResultBean register = new ReportResultBean(registerToday,registerWeek + registerToday,registerMonth + registerToday);
        logger.info("获取后台报表数据完成,耗时:[{}]",(System.currentTimeMillis() - start));
        return new ModelAndView("admin/index")
                .addObject("upload",upload)
                .addObject("download",download)
                .addObject("active",active)
                .addObject("register",register);
    }

    @ResponseBody
    @GetMapping(value = "/overview")
    public BaseResult overview(){
        long start = System.currentTimeMillis();
        OverviewResultBean resultBean = adminService.getOverview();
        logger.info("获取后台折线数据完成,耗时:[{}]",(System.currentTimeMillis() - start));
        return new BaseResult(resultBean);
    }

}
