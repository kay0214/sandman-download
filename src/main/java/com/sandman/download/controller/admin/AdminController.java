/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller.admin;

import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.bean.system.ReportResultBean;
import com.sandman.download.config.SystemConfig;
import com.sandman.download.constant.CommonConstant;
import com.sandman.download.constant.ReturnMessage;
import com.sandman.download.dao.mysql.system.model.auto.User;
import com.sandman.download.service.admin.AdminService;
import com.sandman.download.utils.DateUtils;
import com.sandman.download.utils.PasswordEncrypt;
import com.sandman.download.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

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
        Date todayStart = DateUtils.getDayStart(new Date());
        Date todayEnd = DateUtils.getDayEnd(new Date());
        Date weekStart = DateUtils.getDayStart(DateUtils.getDaysAfter(new Date(),-7));
        Date monthStart = DateUtils.getDayStart(DateUtils.getMonthsAfter(new Date(),-1));
        // 管理员首页查询
        ReportResultBean upload = new ReportResultBean(adminService.getUploadCount(todayStart,todayEnd),adminService.getUploadCount(weekStart,todayEnd),adminService.getUploadCount(monthStart,todayEnd));
        ReportResultBean download = new ReportResultBean(adminService.getDownloadCount(todayStart,todayEnd),adminService.getDownloadCount(weekStart,todayEnd),adminService.getDownloadCount(monthStart,todayEnd));
        ReportResultBean active = new ReportResultBean(adminService.getActiveCount(todayStart,todayEnd),adminService.getActiveCount(weekStart,todayEnd),adminService.getActiveCount(monthStart,todayEnd));
        ReportResultBean register = new ReportResultBean(adminService.getRegisterCount(todayStart,todayEnd),adminService.getRegisterCount(weekStart,todayEnd),adminService.getRegisterCount(monthStart,todayEnd));
        return new ModelAndView("admin/index")
                .addObject("upload",upload)
                .addObject("download",download)
                .addObject("active",active)
                .addObject("register",register);
    }

    // TODO:还差一个折线图不知道怎么弄呢
}
