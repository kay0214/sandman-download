/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.controller;

import com.sandman.emmmoe.bean.BaseController;
import com.sandman.emmmoe.service.EmmmoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author sunpeikai
 * @version PageListController, v0.1 2018/12/19 16:04
 */
@Controller
@RequestMapping(value = "/work")
public class PageListController extends BaseController {

    @Autowired
    private EmmmoeService emmmoeService;

    @GetMapping(value = "/init")
    public ModelAndView init(){

        return new ModelAndView("crawl");
    }

    @GetMapping(value = "/crawl")
    public ModelAndView getPageList(){
        String emmmoe = emmmoeService.getRootUrl();
        logger.info("emmmoe:[{}]",emmmoe);
        Map<String,Integer> result = emmmoeService.getEmmmoePageList(emmmoe);
        int handle = emmmoeService.getNetDisk();
        return new ModelAndView("crawl")
                .addObject("count",result.get("count"))
                .addObject("insert",result.get("insert"))
                .addObject("handle",handle);
    }

    @ResponseBody
    @GetMapping(value = "/test")
    public void test(String title,String uri){
        logger.info("参数title:[{}],uri:[{}]",title,uri);
        boolean exist = emmmoeService.check(title, uri);
        logger.info("是否存在:[{}]",exist);
    }

}
