/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller.admin;

import com.alibaba.fastjson.JSON;
import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.bean.admin.UserManagerRequest;
import com.sandman.download.bean.download.UserResultBean;
import com.sandman.download.constant.ReturnMessage;
import com.sandman.download.dao.mysql.system.model.auto.User;
import com.sandman.download.service.admin.UserManagerService;
import com.sandman.download.utils.BeanUtils;
import com.sandman.download.utils.DateUtils;
import com.sandman.download.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public BaseResult searchList(UserManagerRequest userManagerRequest){
        logger.info("查询列表分页 -> page:[{}],limit:[{}]",userManagerRequest.getPage(),userManagerRequest.getLimit());
        int count = userManagerService.getUserCount(userManagerRequest);
        List<User> userList = userManagerService.searchList(userManagerRequest);
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

    @ResponseBody
    @PostMapping(value = "/recharge")
    public BaseResult recharge(Integer userId,Integer gold){
        User user = userManagerService.getUserByUserId(userId);
        logger.info("管理员充值 -> userId:[{}],gold:[{}]",user.getUserId(),gold);
        // 写入积分详情
        userManagerService.goldOperation(userId,null,null,user.getGold(),null,user.getGold() + gold,"用户充值积分",2, DateUtils.getNow());
        user.setGold(user.getGold() + gold);
        int result = userManagerService.updateUser(user);
        if(result>0){
            return new BaseResult();
        }else{
            return new BaseResult(ReturnMessage.ERR_USER_RECHARGE);
        }
    }

    @GetMapping(value = "/vip")
    public ModelAndView vip(Integer userId,Integer role){
        logger.info("管理员充会员 -> userId:[{}],更新后角色:[{}]",userId,role);
        User user = userManagerService.getUserByUserId(userId);
        if(user!=null){
            if(user.getRole() == 1){
                // 当用户是非会员时才写入积分详情
                userManagerService.goldOperation(userId,null,null,user.getGold(),null,user.getGold(),"用户充值VIP",2, DateUtils.getNow());
            }
            if(!role.equals(user.getRole())){
                // 不同时才更新
                user.setRole(role);
                user.setUpdateTime(new Date());
                userManagerService.updateUser(user);
            }

        }
        return new ModelAndView("redirect:/user_manager/init");
    }
}
