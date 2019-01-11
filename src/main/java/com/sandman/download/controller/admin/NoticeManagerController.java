/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller.admin;

import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.bean.admin.NoticeManagerRequest;
import com.sandman.download.dao.mysql.system.model.auto.Notice;
import com.sandman.download.service.admin.NoticeManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @author sunpeikai
 * @version NoticeManagerController, v0.1 2019/1/10 17:28
 */
@Controller
@RequestMapping(value = "/notice_manager")
public class NoticeManagerController extends BaseController {

    @Autowired
    private NoticeManagerService noticeManagerService;

    @GetMapping(value = "/init")
    public ModelAndView init(){
        return new ModelAndView("admin/notice_manager");
    }

    @ResponseBody
    @GetMapping(value = "/search")
    public BaseResult search(NoticeManagerRequest noticeManagerRequest){
        logger.info("查询列表分页 -> page:[{}],limit:[{}]",noticeManagerRequest.getPage(),noticeManagerRequest.getLimit());
        int count = noticeManagerService.getNoticeCount(noticeManagerRequest);
        List<Notice> noticeList = noticeManagerService.getNoticeList(noticeManagerRequest);
        return new BaseResult(noticeList,count);
    }

    @GetMapping(value = "/available")
    public ModelAndView available(Integer id,Integer status){
        logger.info("管理员启用或禁用公告 -> id[{}],status:[{}]",id,status);
        Notice notice = noticeManagerService.getNoticeById(id);
        if(notice != null && !status.equals(notice.getStatus())){
            notice.setStatus(status);
            notice.setUpdateTime(new Date());
            noticeManagerService.updateNotice(notice);
        }
        return new ModelAndView("redirect:/notice_manager/init");
    }

    @GetMapping(value = "/edit")
    public ModelAndView edit(Integer id){
        logger.info("管理员修改公告 -> id[{}]",id);
        Notice notice = noticeManagerService.getNoticeById(id);
        return new ModelAndView("admin/notice_audit").addObject("notice",notice);
    }

    @PostMapping(value = "/update")
    public ModelAndView update(Notice notice){
        logger.info("管理员提交修改公告 -> id[{}]",notice.getId());
        notice.setUpdateTime(new Date());
        noticeManagerService.updateNotice(notice);
        return new ModelAndView("redirect:/notice_manager/init");
    }

    @GetMapping(value = "/insert_init")
    public ModelAndView insertInit(){
        return new ModelAndView("admin/notice_insert");
    }

    @PostMapping(value = "/insert")
    public ModelAndView insert(Notice notice){
        logger.info("管理员插入公告 -> title:[{}]",notice.getTitle());
        noticeManagerService.insertNotice(notice);
        return new ModelAndView("redirect:/notice_manager/init");
    }

    @GetMapping(value = "/delete")
    public ModelAndView delete(Integer id){
        logger.info("管理员删除公告 -> id[{}]",id);
        Notice notice = noticeManagerService.getNoticeById(id);
        if(notice != null){
            notice.setDelFlag(1);
            notice.setUpdateTime(new Date());
            noticeManagerService.updateNotice(notice);
        }
        return new ModelAndView("redirect:/notice_manager/init");
    }
}
