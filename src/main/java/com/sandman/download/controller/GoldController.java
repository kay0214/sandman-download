/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.dao.mysql.download.model.auto.GoldLog;
import com.sandman.download.service.download.GoldService;
import com.sandman.download.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author sunpeikai
 * @version GoldController, v0.1 2019/1/7 15:35
 */
@Controller
@RequestMapping(value = "/my_gold")
public class GoldController extends BaseController {

    @Autowired
    private GoldService goldService;

    @GetMapping(value = "/init")
    public ModelAndView init(){
        logger.info("entry my gold");
        return new ModelAndView("my_gold");
    }

    @ResponseBody
    @GetMapping(value = "/search")
    public BaseResult search(Integer page,Integer limit){
        Integer userId = SessionUtils.getUserId();
        page = (page==null)?1:page;
        limit = (limit==null)?10:limit;
        logger.info("查询列表分页 -> page:[{}],limit:[{}],userId:[{}]",page,limit,userId);
        Integer count = goldService.goldListCount(userId);
        List<GoldLog> goldLogList = goldService.search(userId,page,limit);
        return new BaseResult(goldLogList,count);
    }
}
