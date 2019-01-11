/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.admin.impl;

import com.sandman.download.base.BaseServiceImpl;
import com.sandman.download.dao.mysql.system.model.auto.Template;
import com.sandman.download.dao.mysql.system.model.auto.TemplateExample;
import com.sandman.download.service.admin.TemplateManagerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunpeikai
 * @version TemplateManagerServiceImpl, v0.1 2019/1/10 17:35
 */
@Service
public class TemplateManagerServiceImpl extends BaseServiceImpl implements TemplateManagerService {

    /**
     * 获取模板count
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getTemplateCount() {
        return templateMapper.countByExample(new TemplateExample());
    }

    /**
     * 分页获取模板列表
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<Template> searchList(Integer page, Integer limit) {
        TemplateExample templateExample = new TemplateExample();
        templateExample.setOrderByClause("create_time desc");
        computePage(page, limit);
        templateExample.setLimitStart(limitStart);
        templateExample.setLimitEnd(limitEnd);
        return templateMapper.selectByExample(templateExample);
    }

    /**
     * 根据id查询模板
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public Template getTemplateById(Integer id) {
        return templateMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新模板
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int updateTemplate(Template template) {
        return templateMapper.updateByPrimaryKeySelective(template);
    }

    /**
     * 删除模板
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int deleteTemplate(Integer id) {
        return templateMapper.deleteByPrimaryKey(id);
    }
}
