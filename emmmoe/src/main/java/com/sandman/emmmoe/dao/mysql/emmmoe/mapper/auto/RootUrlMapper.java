package com.sandman.emmmoe.dao.mysql.emmmoe.mapper.auto;

import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.RootUrl;
import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.RootUrlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RootUrlMapper {
    int countByExample(RootUrlExample example);

    int deleteByExample(RootUrlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RootUrl record);

    int insertSelective(RootUrl record);

    List<RootUrl> selectByExample(RootUrlExample example);

    RootUrl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RootUrl record, @Param("example") RootUrlExample example);

    int updateByExample(@Param("record") RootUrl record, @Param("example") RootUrlExample example);

    int updateByPrimaryKeySelective(RootUrl record);

    int updateByPrimaryKey(RootUrl record);
}