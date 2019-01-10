/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller.admin;

import com.sandman.download.base.BaseController;
import com.sandman.download.service.admin.FriendlyLinkManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sunpeikai
 * @version FriendlyLinkManagerController, v0.1 2019/1/10 17:31
 */
@Controller
@RequestMapping(value = "/friendly_link_manager")
public class FriendlyLinkManagerController extends BaseController {

    @Autowired
    private FriendlyLinkManagerService friendlyLinkManagerService;

    @GetMapping(value = "/init")
    public ModelAndView init(){

        return new ModelAndView("admin/friendly_link_manager");
    }
}
