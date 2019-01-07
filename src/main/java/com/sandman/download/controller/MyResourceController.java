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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 根据id删除这个资源
     * @auth sunpeikai
     * @param
     * @return
     */
    @GetMapping(value = "/deleteById")
    public ModelAndView deleteById(Integer id){
        logger.info("删除资源 -> id:[{}]",id);
        Integer userId = SessionUtils.getUserId();
        Resource resource = myResourceService.searchById(id);
        if(resource != null){
            //资源存在
            if(null != userId && userId.equals(resource.getUserId())){
                //开始假删
                myResourceService.deleteById(resource);
            }
        }
        return new ModelAndView("redirect:/my_resource/search");
    }

    /**
     * 编辑资源
     * @auth sunpeikai
     * @param
     * @return
     */
    @GetMapping(value = "/editById")
    public ModelAndView editById(Integer id){
        logger.info("编辑资源 -> id:[{}]",id);
        Integer userId = SessionUtils.getUserId();
        Resource resource = myResourceService.searchById(id);
        if(resource != null){
            //资源存在
            if(null != userId && userId.equals(resource.getUserId())){
                //开始编辑
                return new ModelAndView("/edit").addObject("resource",resource);
            }
        }
        return new ModelAndView("redirect:/my_resource/search");
    }

    /**
     * 编辑资源提交
     * @auth sunpeikai
     * @param
     * @return
     */
    @PostMapping(value = "/submit_edit")
    public ModelAndView submitEdit(Resource resource){
        logger.info("resource:[{}]",JSON.toJSONString(resource));
        // 切换到待审核状态:0
        resource.setStatus(0);
        int result = myResourceService.updateResource(resource);
        if(result>0){
            return new ModelAndView("redirect:/my_resource/search");
        }
        return new ModelAndView("/edit").addObject("resource",resource);
    }
}
