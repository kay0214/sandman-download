/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.admin.impl;

import com.sandman.film.base.BaseServiceImpl;
import com.sandman.film.bean.admin.TemplateManagerRequest;
import com.sandman.film.dao.mysql.system.model.auto.Template;
import com.sandman.film.dao.mysql.system.model.auto.TemplateExample;
import com.sandman.film.service.admin.TemplateManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public int getTemplateCount(TemplateManagerRequest templateManagerRequest) {
        TemplateExample templateExample = convertExample(templateManagerRequest);
        return templateMapper.countByExample(templateExample);
    }

    /**
     * 分页获取模板列表
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<Template> searchList(TemplateManagerRequest templateManagerRequest) {
        TemplateExample templateExample = convertExample(templateManagerRequest);
        templateExample.setOrderByClause("create_time desc");
        computePage(templateManagerRequest.getPage(),templateManagerRequest.getLimit());
        templateExample.setLimitStart(limitStart);
        templateExample.setLimitEnd(limitEnd);
        return templateMapper.selectByExample(templateExample);
    }

    private TemplateExample convertExample(TemplateManagerRequest templateManagerRequest){
        TemplateExample templateExample = new TemplateExample();
        TemplateExample.Criteria criteria = templateExample.createCriteria();
        if(StringUtils.isNotBlank(templateManagerRequest.getTplCode())){
            criteria.andTplCodeLike("%" + templateManagerRequest.getTplCode() + "%");
        }
        if(StringUtils.isNotBlank(templateManagerRequest.getTplName())){
            criteria.andTplNameLike("%" + templateManagerRequest.getTplName() + "%");
        }
        if(templateManagerRequest.getStatus()!=null){
            criteria.andStatusEqualTo(templateManagerRequest.getStatus());
        }
        return templateExample;
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

    /**
     * 插入模板
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int insertTemplate(Template template) {
        Date now = new Date();
        template.setCreateTime(now);
        template.setUpdateTime(now);
        return templateMapper.insertSelective(template);
    }
}
