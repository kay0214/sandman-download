/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.controller;

import com.sandman.film.base.BaseController;
import com.sandman.film.base.BaseResult;
import com.sandman.film.dao.mysql.system.model.auto.Notice;
import com.sandman.film.service.system.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author sunpeikai
 * @version NoticeController, v0.1 2019/1/9 14:06
 */
@Controller
@RequestMapping(value = "/notice")
public class NoticeController extends BaseController {

    @Autowired
    private NoticeService noticeService;

    @ResponseBody
    @GetMapping(value = "/init")
    public BaseResult init(){
        List<Notice> noticeList = noticeService.getNoticeList();
        return new BaseResult(noticeList);
    }
}
