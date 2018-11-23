package com.sandman.download.dao.mysql.download.mapper.auto;

import com.sandman.download.dao.mysql.download.model.auto.ResourceLog;
import com.sandman.download.dao.mysql.download.model.auto.ResourceLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourceLogMapper {
    int countByExample(ResourceLogExample example);

    int deleteByExample(ResourceLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ResourceLog record);

    int insertSelective(ResourceLog record);

    List<ResourceLog> selectByExample(ResourceLogExample example);

    ResourceLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ResourceLog record, @Param("example") ResourceLogExample example);

    int updateByExample(@Param("record") ResourceLog record, @Param("example") ResourceLogExample example);

    int updateByPrimaryKeySelective(ResourceLog record);

    int updateByPrimaryKey(ResourceLog record);
}