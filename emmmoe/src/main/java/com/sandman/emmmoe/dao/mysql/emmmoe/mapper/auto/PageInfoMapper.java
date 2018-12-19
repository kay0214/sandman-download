package com.sandman.emmmoe.dao.mysql.emmmoe.mapper.auto;

import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.PageInfo;
import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.PageInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PageInfoMapper {
    int countByExample(PageInfoExample example);

    int deleteByExample(PageInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PageInfo record);

    int insertSelective(PageInfo record);

    List<PageInfo> selectByExample(PageInfoExample example);

    PageInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PageInfo record, @Param("example") PageInfoExample example);

    int updateByExample(@Param("record") PageInfo record, @Param("example") PageInfoExample example);

    int updateByPrimaryKeySelective(PageInfo record);

    int updateByPrimaryKey(PageInfo record);
}