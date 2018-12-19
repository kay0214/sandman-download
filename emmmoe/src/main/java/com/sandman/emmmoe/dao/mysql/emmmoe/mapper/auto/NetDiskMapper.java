package com.sandman.emmmoe.dao.mysql.emmmoe.mapper.auto;

import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.NetDisk;
import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.NetDiskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NetDiskMapper {
    int countByExample(NetDiskExample example);

    int deleteByExample(NetDiskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NetDisk record);

    int insertSelective(NetDisk record);

    List<NetDisk> selectByExample(NetDiskExample example);

    NetDisk selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NetDisk record, @Param("example") NetDiskExample example);

    int updateByExample(@Param("record") NetDisk record, @Param("example") NetDiskExample example);

    int updateByPrimaryKeySelective(NetDisk record);

    int updateByPrimaryKey(NetDisk record);
}