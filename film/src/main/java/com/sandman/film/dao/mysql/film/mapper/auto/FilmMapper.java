package com.sandman.film.dao.mysql.film.mapper.auto;

import com.sandman.film.dao.mysql.film.model.auto.Film;
import com.sandman.film.dao.mysql.film.model.auto.FilmExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FilmMapper {
    int countByExample(FilmExample example);

    int deleteByExample(FilmExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Film record);

    int insertSelective(Film record);

    List<Film> selectByExample(FilmExample example);

    Film selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Film record, @Param("example") FilmExample example);

    int updateByExample(@Param("record") Film record, @Param("example") FilmExample example);

    int updateByPrimaryKeySelective(Film record);

    int updateByPrimaryKey(Film record);
}