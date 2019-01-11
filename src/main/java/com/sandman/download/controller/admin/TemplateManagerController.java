/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller.admin;

import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.bean.admin.TemplateManagerRequest;
import com.sandman.download.dao.mysql.system.model.auto.Template;
import com.sandman.download.service.admin.TemplateManagerService;
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
 * @version TemplateManagerController, v0.1 2019/1/10 17:34
 */
@Controller
@RequestMapping(value = "/template_manager")
public class TemplateManagerController extends BaseController {

    @Autowired
    private TemplateManagerService templateManagerService;

    @GetMapping(value = "/init")
    public ModelAndView init(){

        return new ModelAndView("admin/template_manager");
    }

    @ResponseBody
    @GetMapping(value = "/search")
    public BaseResult search(TemplateManagerRequest templateManagerRequest){
        logger.info("查询列表分页 -> page:[{}],limit:[{}]",templateManagerRequest.getPage(),templateManagerRequest.getLimit());
        int count = templateManagerService.getTemplateCount(templateManagerRequest);
        List<Template> templateList = templateManagerService.searchList(templateManagerRequest);
        return new BaseResult(templateList,count);
    }

    @GetMapping(value = "/available")
    public ModelAndView available(Integer id,Integer status){
        logger.info("管理员启用或禁用模板 -> id[{}],status:[{}]",id,status);
        Template template = templateManagerService.getTemplateById(id);
        if(template != null && !status.equals(template.getStatus())){
            template.setStatus(status);
            template.setUpdateTime(new Date());
            templateManagerService.updateTemplate(template);
        }
        return new ModelAndView("redirect:/template_manager/init");
    }

    @GetMapping(value = "/edit")
    public ModelAndView edit(Integer id){
        logger.info("管理员修改模板 -> id[{}]",id);
        Template template = templateManagerService.getTemplateById(id);
        return new ModelAndView("admin/template_audit").addObject("template",template);
    }

    @PostMapping(value = "/update")
    public ModelAndView update(Template template){
        logger.info("管理员提交修改模板 -> id[{}]",template.getId());
        template.setUpdateTime(new Date());
        templateManagerService.updateTemplate(template);
        return new ModelAndView("redirect:/template_manager/init");
    }

    @GetMapping(value = "/delete")
    public ModelAndView delete(Integer id){
        logger.info("管理员删除模板 -> id[{}]",id);
        templateManagerService.deleteTemplate(id);
        return new ModelAndView("redirect:/template_manager/init");
    }
}
