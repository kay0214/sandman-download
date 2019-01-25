package com.sandman.film.dao.mysql.film.mapper.customize;

import com.sandman.film.dao.mysql.film.model.auto.FilmLog;
import com.sandman.film.dao.mysql.film.model.auto.FilmLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FilmLogCustomizeMapper {
    List<FilmLog> selectByExample(FilmLogExample example);
}