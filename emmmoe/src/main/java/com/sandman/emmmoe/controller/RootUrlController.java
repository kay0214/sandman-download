/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.controller;

import com.sandman.emmmoe.base.BaseResult;
import com.sandman.emmmoe.bean.BaseController;
import com.sandman.emmmoe.service.RootUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunpeikai
 * @version RootUrlController, v0.1 2018/12/20 15:15
 */
@RestController
@RequestMapping(value = "/rootUrl")
public class RootUrlController extends BaseController {

    @Autowired
    private RootUrlService rootUrlService;

    @GetMapping(value = "/updateUrl")
    public BaseResult updateUrl(String url){
        int result = rootUrlService.updateUrl(url);
        if(result == 0){
            return new BaseResult("url更新失败");
        }else if(result == 1){
            return new BaseResult("url更新成功");
        }else{
            return new BaseResult("无需更新");
        }
    }
}
