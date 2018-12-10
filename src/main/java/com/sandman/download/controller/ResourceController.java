/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.alibaba.fastjson.JSON;
import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.bean.download.ResourceBean;
import com.sandman.download.constant.CommonConstant;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.service.download.ResourceService;
import com.sandman.download.utils.SessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author sunpeikai
 * @version ResourceController, v0.1 2018/12/6 18:01
 */
@Controller
@RequestMapping(value = "/resource")
public class ResourceController extends BaseController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 获取资源信息（type：1 -> 按照下载次数倒叙排序，else -> 按照创建时间倒叙)
     * @auth sunpeikai
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/getResource")
    public BaseResult getResource(@RequestBody ResourceBean resourceBean){
        logger.info("获取资源信息 -> currPage:[{}],pageSize:[{}],type:[{}],search:[{}]",resourceBean.getCurrPage(),resourceBean.getPageSize(),resourceBean.getType(),resourceBean.getSearch());
        if(StringUtils.isBlank(resourceBean.getSearch()) || "undefined".equals(resourceBean.getSearch())){
            resourceBean.setSearch(null);
        }
        List<Resource> resourceList = resourceService.getResourceByType(resourceBean);
        //TODO:确认一下是否需要设置session
        SessionUtils.setSessionAttribute("resources",resourceList);
        logger.info("获取到的数据:[{}]", JSON.toJSONString(resourceList));
        return new BaseResult(resourceList);
    }

    @GetMapping(value = "/list")
    public ModelAndView getList(Integer currPage){
        logger.info("get list page:[{}]",currPage);
        ResourceBean resourceBean = new ResourceBean(currPage, CommonConstant.DEFAULT_LIMIT);
        int count = resourceService.getResourceCountByType(resourceBean);
        List<Resource> resourceList = resourceService.getList(resourceBean);
        return new ModelAndView("/list")
                .addObject("resourceList",resourceList)
                .addObject("currPage",currPage)
                .addObject("count",count);
    }

    @GetMapping(value = "/search")
    public ModelAndView searchResource(ResourceBean resourceBean){
        logger.info("搜索资源 -> search:[{}]",JSON.toJSONString(resourceBean));
        if(StringUtils.isBlank(resourceBean.getSearch()) || "undefined".equals(resourceBean.getSearch())){
            resourceBean.setSearch(null);
        }
        if(null != resourceBean.getCurrPage()){
            resourceBean.setCurrPage((resourceBean.getCurrPage()<1)?1:resourceBean.getCurrPage());
            resourceBean.computeLimit();
        }else{
            resourceBean.setCurrPage(1);
            resourceBean.computeLimit();
        }

        // 按照创建时间排序
        resourceBean.setType(0);
        int count = resourceService.getResourceCountByType(resourceBean);
        List<Resource> resourceList = resourceService.searchResource(resourceBean);
        logger.info("搜索到的 -> [{}]",JSON.toJSONString(resourceList));
        return new ModelAndView("/search")
                .addObject("searchResource",resourceList)
                .addObject("search",resourceBean.getSearch())
                .addObject("count",count)
                .addObject("currPage",resourceBean.getCurrPage());
    }

}
