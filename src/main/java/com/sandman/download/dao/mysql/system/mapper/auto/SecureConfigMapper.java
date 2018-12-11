package com.sandman.download.dao.mysql.system.mapper.auto;

import com.sandman.download.dao.mysql.system.model.auto.SecureConfig;
import com.sandman.download.dao.mysql.system.model.auto.SecureConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SecureConfigMapper {
    int countByExample(SecureConfigExample example);

    int deleteByExample(SecureConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SecureConfig record);

    int insertSelective(SecureConfig record);

    List<SecureConfig> selectByExample(SecureConfigExample example);

    SecureConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SecureConfig record, @Param("example") SecureConfigExample example);

    int updateByExample(@Param("record") SecureConfig record, @Param("example") SecureConfigExample example);

    int updateByPrimaryKeySelective(SecureConfig record);

    int updateByPrimaryKey(SecureConfig record);
}