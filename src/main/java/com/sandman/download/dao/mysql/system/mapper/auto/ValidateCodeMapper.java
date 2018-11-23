package com.sandman.download.dao.mysql.system.mapper.auto;

import com.sandman.download.dao.mysql.system.model.auto.ValidateCode;
import com.sandman.download.dao.mysql.system.model.auto.ValidateCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ValidateCodeMapper {
    int countByExample(ValidateCodeExample example);

    int deleteByExample(ValidateCodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ValidateCode record);

    int insertSelective(ValidateCode record);

    List<ValidateCode> selectByExample(ValidateCodeExample example);

    ValidateCode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ValidateCode record, @Param("example") ValidateCodeExample example);

    int updateByExample(@Param("record") ValidateCode record, @Param("example") ValidateCodeExample example);

    int updateByPrimaryKeySelective(ValidateCode record);

    int updateByPrimaryKey(ValidateCode record);
}