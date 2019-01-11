/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller.admin;

import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.bean.admin.ResourceManagerRequest;
import com.sandman.download.bean.download.UserResultBean;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.dao.mysql.system.model.auto.User;
import com.sandman.download.service.admin.ResourceManagerService;
import com.sandman.download.utils.BeanUtils;
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
 * @version ResourceManagerController, v0.1 2019/1/10 17:25
 */
@Controller
@RequestMapping(value = "/resource_manager")
public class ResourceManagerController extends BaseController {

    @Autowired
    private ResourceManagerService resourceManagerService;

    @GetMapping(value = "/init")
    public ModelAndView init(){

        return new ModelAndView("admin/resource_manager");
    }

    @ResponseBody
    @GetMapping(value = "/search")
    public BaseResult search(ResourceManagerRequest resourceManagerRequest){
        logger.info("查询列表分页 -> page:[{}],limit:[{}]",resourceManagerRequest.getPage(),resourceManagerRequest.getLimit());
        int count = resourceManagerService.getResourceCount(resourceManagerRequest);
        List<Resource> resourceList = resourceManagerService.searchList(resourceManagerRequest);
        return new BaseResult(resourceList,count);
    }

    @GetMapping(value = "/delete")
    public ModelAndView delete(Integer id){
        logger.info("管理员删除资源id -> [{}]",id);
        Resource resource = resourceManagerService.getResourceById(id);
        resource.setDelFlag(1);
        resource.setUpdateTime(new Date());
        resourceManagerService.updateResource(resource);
        return new ModelAndView("redirect:/resource_manager/init");
    }

    @GetMapping(value = "/audit")
    public ModelAndView audit(Integer id){
        logger.info("管理员编辑资源id -> [{}]",id);
        Resource resource = resourceManagerService.getResourceById(id);
        return new ModelAndView("admin/resource_audit").addObject("resource",resource);
    }

    @PostMapping(value = "/update")
    public ModelAndView update(Integer id,Integer status,String statusDesc){
        logger.info("管理员审核资源 -> id:[{}],status:[{}],statusDesc:[{}]",id,status,statusDesc);
        Resource resource = resourceManagerService.getResourceById(id);
        resource.setStatus(status);
        resource.setStatusDesc(statusDesc);
        resource.setUpdateTime(new Date());
        resourceManagerService.updateResource(resource);
        return new ModelAndView("redirect:/resource_manager/init");
    }
}
