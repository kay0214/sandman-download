package com.sandman.film.dao.mysql.film.mapper.auto;

import com.sandman.film.dao.mysql.film.model.auto.FindPlz;
import com.sandman.film.dao.mysql.film.model.auto.FindPlzExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FindPlzMapper {
    int countByExample(FindPlzExample example);

    int deleteByExample(FindPlzExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FindPlz record);

    int insertSelective(FindPlz record);

    List<FindPlz> selectByExample(FindPlzExample example);

    FindPlz selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FindPlz record, @Param("example") FindPlzExample example);

    int updateByExample(@Param("record") FindPlz record, @Param("example") FindPlzExample example);

    int updateByPrimaryKeySelective(FindPlz record);

    int updateByPrimaryKey(FindPlz record);
}