/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.dao.mysql.mapper;

import com.sandman.film.dao.mysql.film.mapper.customize.FilmLogCustomizeMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sunpeikai
 * @version CustomizeMapper, v0.1 2018/12/3 12:03
 */
public class CustomizeMapper extends AutoMapper {
    @Autowired
    protected FilmLogCustomizeMapper filmLogCustomizeMapper;
}
