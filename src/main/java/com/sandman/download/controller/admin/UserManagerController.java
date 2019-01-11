/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller.admin;

import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.bean.download.UserResultBean;
import com.sandman.download.dao.mysql.system.model.auto.User;
import com.sandman.download.service.admin.UserManagerService;
import com.sandman.download.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sunpeikai
 * @version UserManagerController, v0.1 2019/1/10 17:22
 */
@Controller
@RequestMapping(value = "/user_manager")
public class UserManagerController extends BaseController {

    @Autowired
    private UserManagerService userManagerService;

    /**
     * 进入会员管理页面
     * @auth sunpeikai
     * @param
     * @return
     */
    @GetMapping(value = "/init")
    public ModelAndView init(){
        return new ModelAndView("admin/user_manager");
    }

    @ResponseBody
    @GetMapping(value = "/search_list")
    public BaseResult searchList(Integer page,Integer limit){
        page = (page==null)?1:page;
        limit = (limit==null)?10:limit;
        logger.info("查询列表分页 -> page:[{}],limit:[{}]",page,limit);
        int count = userManagerService.getUserCount();
        List<User> userList = userManagerService.searchList(page,limit);
        List<UserResultBean> userResultBeanList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(userList)){
            userResultBeanList = BeanUtils.convertBeanList(userList,UserResultBean.class);
        }
        return new BaseResult(userResultBeanList,count);
    }

    @GetMapping(value = "/update_status")
    public ModelAndView updateStatus(Integer userId,Integer status){
        User user = userManagerService.getUserByUserId(userId);
        if(user != null && user.getRole() != 0 && !status.equals(user.getAvailable())){
            // 不是管理员
            user.setAvailable(status);
            user.setUpdateTime(new Date());
            userManagerService.updateUser(user);
        }
        return new ModelAndView("redirect:/user_manager/init");
    }
}
