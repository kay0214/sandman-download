/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller.admin;

import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.bean.admin.FriendlyLinkManagerRequest;
import com.sandman.download.dao.mysql.system.model.auto.FriendlyLink;
import com.sandman.download.service.admin.FriendlyLinkManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @author sunpeikai
 * @version FriendlyLinkManagerController, v0.1 2019/1/10 17:31
 */
@Controller
@RequestMapping(value = "/friendly_link_manager")
public class FriendlyLinkManagerController extends BaseController {

    @Autowired
    private FriendlyLinkManagerService friendlyLinkManagerService;

    @GetMapping(value = "/init")
    public ModelAndView init(){

        return new ModelAndView("admin/friendly_link_manager");
    }

    @ResponseBody
    @GetMapping(value = "/search")
    public BaseResult search(FriendlyLinkManagerRequest friendlyLinkManagerRequest){
        logger.info("查询列表分页 -> page:[{}],limit:[{}]",friendlyLinkManagerRequest.getPage(),friendlyLinkManagerRequest.getLimit());
        int count = friendlyLinkManagerService.getFriendlyLinkCount(friendlyLinkManagerRequest);
        List<FriendlyLink> friendlyLinkList = friendlyLinkManagerService.searchList(friendlyLinkManagerRequest);
        return new BaseResult(friendlyLinkList,count);
    }

    @GetMapping(value = "/available")
    public ModelAndView available(Integer id,Integer status){
        logger.info("管理员启用或禁用友情链接 -> id[{}],status:[{}]",id,status);
        FriendlyLink friendlyLink = friendlyLinkManagerService.getFriendlyLinkById(id);
        if(friendlyLink != null && !status.equals(friendlyLink.getStatus())){
            friendlyLink.setStatus(status);
            friendlyLink.setUpdateTime(new Date());
            friendlyLinkManagerService.update(friendlyLink);
        }
        return new ModelAndView("redirect:/friendly_link_manager/init");
    }

    @GetMapping(value = "/edit")
    public ModelAndView edit(Integer id){
        logger.info("管理员修改友情链接 -> id[{}]",id);
        FriendlyLink friendlyLink = friendlyLinkManagerService.getFriendlyLinkById(id);
        return new ModelAndView("admin/friendly_link_audit").addObject("friendlyLink",friendlyLink);
    }

    @PostMapping(value = "/update")
    public ModelAndView update(FriendlyLink friendlyLink){
        logger.info("管理员提交修改公告 -> id[{}]",friendlyLink.getId());
        friendlyLink.setUpdateTime(new Date());
        friendlyLinkManagerService.update(friendlyLink);
        return new ModelAndView("redirect:/friendly_link_manager/init");
    }

    @GetMapping(value = "/insert_init")
    public ModelAndView insertInit(){
        return new ModelAndView("admin/friendly_link_insert");
    }

    @PostMapping(value = "/insert")
    public ModelAndView insert(FriendlyLink friendlyLink){
        logger.info("管理员插入友情链接 -> linkName:[{}]",friendlyLink.getLinkName());
        friendlyLinkManagerService.insertFriendlyLink(friendlyLink);
        return new ModelAndView("redirect:/friendly_link_manager/init");
    }

    @GetMapping(value = "/delete")
    public ModelAndView delete(Integer id){
        logger.info("管理员删除公告 -> id[{}]",id);
        FriendlyLink friendlyLink = friendlyLinkManagerService.getFriendlyLinkById(id);
        if(friendlyLink != null){
            friendlyLink.setDelFlag(1);
            friendlyLink.setUpdateTime(new Date());
            friendlyLinkManagerService.update(friendlyLink);
        }
        return new ModelAndView("redirect:/friendly_link_manager/init");
    }
}
