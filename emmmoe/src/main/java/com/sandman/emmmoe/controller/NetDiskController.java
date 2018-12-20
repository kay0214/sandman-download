/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.controller;

import com.sandman.emmmoe.base.BaseResult;
import com.sandman.emmmoe.bean.BaseController;
import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.NetDisk;
import com.sandman.emmmoe.service.NetDiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sunpeikai
 * @version NetDiskController, v0.1 2018/12/20 11:13
 */
@RestController
@RequestMapping(value = "/netDisk")
public class NetDiskController extends BaseController {

    @Autowired
    private NetDiskService netDiskService;
    /**
     * 分页获取百度网盘地址
     * @auth sunpeikai
     * @param
     * @return
     */
    @GetMapping(value = "/getList")
    public BaseResult getList(Integer page,Integer limit){
        logger.info("查询列表分页 -> page:[{}],limit:[{}]",page,limit);
        page = (page==null)?1:page;
        limit = (limit==null)?10:limit;
        int count = netDiskService.getNetDiskCount();
        List<NetDisk> result = netDiskService.getNetDiskPage(page,limit);
        return new BaseResult(count,result);
    }

    // TODO:其他资源，比如说是网站图片资源
    /**
     * 其他资源
     * @auth sunpeikai
     * @param
     * @return
     */
    @GetMapping(value = "/getOtherList")
    public BaseResult getOtherList(Integer page,Integer limit){
        logger.info("查询列表分页 -> page:[{}],limit:[{}]",page,limit);
        page = (page==null)?1:page;
        limit = (limit==null)?10:limit;
        int count = netDiskService.getOtherListCount();
        List<NetDisk> result = netDiskService.getNetDiskPage(page,limit);
        return new BaseResult(count,result);
    }
}
