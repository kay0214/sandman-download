package com.sandman.film.dao.mysql.film.mapper.auto;

import com.sandman.film.dao.mysql.film.model.auto.FilmType;
import com.sandman.film.dao.mysql.film.model.auto.FilmTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FilmTypeMapper {
    int countByExample(FilmTypeExample example);

    int deleteByExample(FilmTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FilmType record);

    int insertSelective(FilmType record);

    List<FilmType> selectByExample(FilmTypeExample example);

    FilmType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FilmType record, @Param("example") FilmTypeExample example);

    int updateByExample(@Param("record") FilmType record, @Param("example") FilmTypeExample example);

    int updateByPrimaryKeySelective(FilmType record);

    int updateByPrimaryKey(FilmType record);
}