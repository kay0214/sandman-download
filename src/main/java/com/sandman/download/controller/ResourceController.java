/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.alibaba.fastjson.JSON;
import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.bean.download.ResourceBean;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.service.download.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @ResponseBody
    @PostMapping(value = "/getResource")
    public BaseResult getResource(@RequestBody ResourceBean resourceBean){
        logger.info("获取资源信息 -> currPage:[{}],pageSize:[{}]",resourceBean.getCurrPage(),resourceBean.getPageSize());
        List<Resource> resourceList = resourceService.getResource(resourceBean);
        logger.info("获取到的数据:[{}]", JSON.toJSONString(resourceList));
        return new BaseResult(resourceList);
    }
}
