package com.sandman.download.dao.mysql.download.mapper.auto;

import com.sandman.download.dao.mysql.download.model.auto.GoldLog;
import com.sandman.download.dao.mysql.download.model.auto.GoldLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoldLogMapper {
    int countByExample(GoldLogExample example);

    int deleteByExample(GoldLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoldLog record);

    int insertSelective(GoldLog record);

    List<GoldLog> selectByExample(GoldLogExample example);

    GoldLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoldLog record, @Param("example") GoldLogExample example);

    int updateByExample(@Param("record") GoldLog record, @Param("example") GoldLogExample example);

    int updateByPrimaryKeySelective(GoldLog record);

    int updateByPrimaryKey(GoldLog record);
}