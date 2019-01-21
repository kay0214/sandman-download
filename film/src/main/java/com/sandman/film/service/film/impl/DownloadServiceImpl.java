/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.film.impl;

import com.sandman.film.base.BaseServiceImpl;
import com.sandman.film.dao.mysql.film.model.auto.FilmLog;
import com.sandman.film.dao.mysql.film.model.auto.FilmLogExample;
import com.sandman.film.service.film.DownloadService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author sunpeikai
 * @version DownloadServiceImpl, v0.1 2018/12/12 16:22
 */
@Service
public class DownloadServiceImpl extends BaseServiceImpl implements DownloadService {
    /**
     * 根据resourceId 和 userId查询是否存在记录
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public FilmLog getFilmLogByResourceIdAndUserId(Integer filmId, Integer userId) {
        FilmLogExample filmLogExample = new FilmLogExample();
        filmLogExample.createCriteria().andFilmIdEqualTo(filmId).andUserIdEqualTo(userId).andDelFlagEqualTo(0);
        List<FilmLog> filmLogList = filmLogMapper.selectByExample(filmLogExample);
        if(!CollectionUtils.isEmpty(filmLogList)){
            return filmLogList.get(0);
        }
        return null;
    }
}
