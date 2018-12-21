package com.sandman.emmmoe.dao.mysql.emmmoe.mapper.auto;

import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.NotContains;
import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.NotContainsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NotContainsMapper {
    int countByExample(NotContainsExample example);

    int deleteByExample(NotContainsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NotContains record);

    int insertSelective(NotContains record);

    List<NotContains> selectByExample(NotContainsExample example);

    NotContains selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NotContains record, @Param("example") NotContainsExample example);

    int updateByExample(@Param("record") NotContains record, @Param("example") NotContainsExample example);

    int updateByPrimaryKeySelective(NotContains record);

    int updateByPrimaryKey(NotContains record);
}