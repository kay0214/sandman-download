/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.alibaba.fastjson.JSON;
import com.sandman.download.base.BaseController;
import com.sandman.download.bean.download.MyResourceBean;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.service.download.MyResourceService;
import com.sandman.download.utils.SessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author sunpeikai
 * @version MyResourceController, v0.1 2018/12/10 13:57
 */
@Controller
@RequestMapping(value = "/my_resource")
public class MyResourceController extends BaseController {

    @Autowired
    private MyResourceService myResourceService;

    @GetMapping(value = "/search")
    public ModelAndView init(MyResourceBean myResourceBean){
        Integer userId = SessionUtils.getUserId();
        if(null != myResourceBean.getCurrPage()){
            myResourceBean.setCurrPage((myResourceBean.getCurrPage()<1)?1:myResourceBean.getCurrPage());
            myResourceBean.computeLimit();
        }else{
            myResourceBean.setCurrPage(1);
            myResourceBean.computeLimit();
        }
        if(userId != null){
            myResourceBean.setUserId(userId);
            logger.info("myResourceBean=【{}】", JSON.toJSONString(myResourceBean));
            int count = myResourceService.getMyResourceListCount(myResourceBean);
            List<Resource> resourceList = myResourceService.getMyResourceList(myResourceBean);
            return new ModelAndView("/my_resource")
                    .addObject("resourceList",resourceList)
                    .addObject("count",count)
                    .addObject("status", myResourceBean.getStatus())
                    .addObject("resourceName",myResourceBean.getResourceName())
                    .addObject("currPage",myResourceBean.getCurrPage());
        }
        return new ModelAndView("/login");
    }
}
