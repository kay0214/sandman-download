/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.controller;

import com.sandman.emmmoe.bean.BaseController;
import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.NetDisk;
import com.sandman.emmmoe.service.ManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sunpeikai
 * @version ManagerController, v0.1 2018/12/20 10:53
 */
@Controller
@RequestMapping(value = "/manage")
public class ManagerController extends BaseController {

    @Autowired
    private ManagerService managerService;

    @GetMapping(value = "/complete")
    public ModelAndView complete(Integer id,String unzipPass){
        logger.info("entry manage,id:[{}],unzipPass:[{}]",id,unzipPass);
        if(id!=null && StringUtils.isNotBlank(unzipPass)){
            logger.info("保存id为[{}],的解压密码[{}]",id,unzipPass);
            managerService.updateUnzipPass(id, unzipPass);
        }
        NetDisk netDisk = managerService.getOneUnHandleNetDisk();
        return new ModelAndView("manage").addObject("netDisk",netDisk);
    }

    @GetMapping(value = "/delete")
    public ModelAndView delete(Integer id){
        logger.info("删除网盘资源 -> id:[{}]",id);
        managerService.delete(id);
        return new ModelAndView("redirect:/manage/complete");
    }
}
