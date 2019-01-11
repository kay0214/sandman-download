/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller.admin;

import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.bean.admin.LinkManagerRequest;
import com.sandman.download.dao.mysql.system.model.auto.SecureConfig;
import com.sandman.download.service.admin.LinkManagerService;
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
 * @version LinkManagerController, v0.1 2019/1/10 17:37
 */
@Controller
@RequestMapping(value = "/link_manager")
public class LinkManagerController extends BaseController {

    @Autowired
    private LinkManagerService linkManagerService;

    @GetMapping(value = "/init")
    public ModelAndView init(){

        return new ModelAndView("admin/link_manager");
    }

    @ResponseBody
    @GetMapping(value = "/search")
    public BaseResult search(LinkManagerRequest linkManagerRequest){
        logger.info("查询列表分页 -> page:[{}],limit:[{}]",linkManagerRequest.getPage(),linkManagerRequest.getLimit());
        int count = linkManagerService.getLinkCount(linkManagerRequest);
        List<SecureConfig> secureConfigList = linkManagerService.searchList(linkManagerRequest);
        return new BaseResult(secureConfigList,count);
    }

    @GetMapping(value = "/available")
    public ModelAndView available(Integer id,Integer status){
        logger.info("管理员启用或禁用接口 -> id[{}],status:[{}]",id,status);
        SecureConfig secureConfig = linkManagerService.getSecureConfigById(id);
        if(secureConfig != null && !status.equals(secureConfig.getStatus())){
            secureConfig.setStatus(status);
            secureConfig.setUpdateTime(new Date());
            linkManagerService.update(secureConfig);
        }
        return new ModelAndView("redirect:/link_manager/init");
    }

    @GetMapping(value = "/edit")
    public ModelAndView edit(Integer id){
        logger.info("管理员修改接口 -> id[{}]",id);
        SecureConfig secureConfig = linkManagerService.getSecureConfigById(id);
        return new ModelAndView("admin/link_audit").addObject("link",secureConfig);
    }

    @PostMapping(value = "/update")
    public ModelAndView update(SecureConfig secureConfig){
        logger.info("管理员提交修改接口 -> id[{}]",secureConfig.getId());
        secureConfig.setUpdateTime(new Date());
        linkManagerService.update(secureConfig);
        return new ModelAndView("redirect:/link_manager/init");
    }

    @GetMapping(value = "/insert_init")
    public ModelAndView insertInit(){

        return new ModelAndView("admin/link_insert");
    }

    @PostMapping(value = "/insert")
    public ModelAndView insert(SecureConfig secureConfig){
        logger.info("管理员新增接口 -> id[{}]",secureConfig.getApiName());
        linkManagerService.insertApi(secureConfig);
        return new ModelAndView("redirect:/link_manager/init");
    }

    @GetMapping(value = "/delete")
    public ModelAndView delete(Integer id){
        logger.info("管理员删除接口 -> id[{}]",id);
        SecureConfig secureConfig = linkManagerService.getSecureConfigById(id);
        if(secureConfig != null){
            secureConfig.setDelFlag(1);
            secureConfig.setUpdateTime(new Date());
            linkManagerService.update(secureConfig);
        }
        return new ModelAndView("redirect:/link_manager/init");
    }
}
