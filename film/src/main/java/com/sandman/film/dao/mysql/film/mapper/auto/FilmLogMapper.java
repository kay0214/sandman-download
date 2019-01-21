package com.sandman.film.dao.mysql.film.mapper.auto;

import com.sandman.film.dao.mysql.film.model.auto.FilmLog;
import com.sandman.film.dao.mysql.film.model.auto.FilmLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FilmLogMapper {
    int countByExample(FilmLogExample example);

    int deleteByExample(FilmLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FilmLog record);

    int insertSelective(FilmLog record);

    List<FilmLog> selectByExample(FilmLogExample example);

    FilmLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FilmLog record, @Param("example") FilmLogExample example);

    int updateByExample(@Param("record") FilmLog record, @Param("example") FilmLogExample example);

    int updateByPrimaryKeySelective(FilmLog record);

    int updateByPrimaryKey(FilmLog record);
}