/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.dao.mysql.system.model.auto.FriendlyLink;
import com.sandman.download.service.system.FriendlyLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author sunpeikai
 * @version FriendlyLinkController, v0.1 2019/1/9 14:59
 */
@Controller
@RequestMapping(value = "/friendly")
public class FriendlyLinkController extends BaseController {

    @Autowired
    private FriendlyLinkService friendlyLinkService;

    @ResponseBody
    @GetMapping(value = "/init")
    public BaseResult init(){
        List<FriendlyLink> friendlyLinkList = friendlyLinkService.getFriendlyLinkList();
        return new BaseResult(friendlyLinkList);
    }
}
