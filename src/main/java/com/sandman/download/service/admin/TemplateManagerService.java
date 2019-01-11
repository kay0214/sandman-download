/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.service.admin;

import com.sandman.download.base.BaseService;
import com.sandman.download.dao.mysql.system.model.auto.Template;

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
    int getTemplateCount();

    /**
     * 分页获取模板列表
     * @auth sunpeikai
     * @param
     * @return
     */
    List<Template> searchList(Integer page, Integer limit);

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
}
