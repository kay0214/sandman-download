package com.sandman.film.dao.mysql.system.mapper.auto;

import com.sandman.film.dao.mysql.system.model.auto.Template;
import com.sandman.film.dao.mysql.system.model.auto.TemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TemplateMapper {
    int countByExample(TemplateExample example);

    int deleteByExample(TemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Template record);

    int insertSelective(Template record);

    List<Template> selectByExample(TemplateExample example);

    Template selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Template record, @Param("example") TemplateExample example);

    int updateByExample(@Param("record") Template record, @Param("example") TemplateExample example);

    int updateByPrimaryKeySelective(Template record);

    int updateByPrimaryKey(Template record);
}