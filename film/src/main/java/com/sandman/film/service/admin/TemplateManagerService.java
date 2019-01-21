/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.admin;

import com.sandman.film.base.BaseService;
import com.sandman.film.bean.admin.TemplateManagerRequest;
import com.sandman.film.dao.mysql.system.model.auto.Template;

import java.util.List;

/**
 * @author sunpeikai
 * @version TemplateManagerService, v0.1 2019/1/10 17:35
 */
public interface TemplateManagerService extends BaseService {

    /**
     * 获取模板count
     * @auth sunpeikai
     * @param
     * @return
     */
    int getTemplateCount(TemplateManagerRequest templateManagerRequest);

    /**
     * 分页获取模板列表
     * @auth sunpeikai
     * @param
     * @return
     */
    List<Template> searchList(TemplateManagerRequest templateManagerRequest);

    /**
     * 根据id查询模板
     * @auth sunpeikai
     * @param
     * @return
     */
    Template getTemplateById(Integer id);

    /**
     * 更新模板
     * @auth sunpeikai
     * @param
     * @return
     */
    int updateTemplate(Template template);

    /**
     * 删除模板
     * @auth sunpeikai
     * @param
     * @return
     */
    int deleteTemplate(Integer id);

    /**
     * 插入模板
     * @auth sunpeikai
     * @param
     * @return
     */
    int insertTemplate(Template template);
}
