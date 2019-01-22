/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.service.admin.impl;

import com.alibaba.fastjson.JSONObject;
import com.sandman.film.base.BaseServiceImpl;
import com.sandman.film.bean.admin.FilmManagerRequest;
import com.sandman.film.config.SystemConfig;
import com.sandman.film.dao.mysql.film.model.auto.Film;
import com.sandman.film.dao.mysql.film.model.auto.FilmExample;
import com.sandman.film.service.admin.FilmManagerService;
import com.sandman.film.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author sunpeikai
 * @version FilmManagerServiceImpl, v0.1 2019/1/10 17:27
 */
@Service
public class FilmManagerServiceImpl extends BaseServiceImpl implements FilmManagerService {

    /**
     * 获取资源总数
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int getFilmCount(FilmManagerRequest filmManagerRequest) {
        FilmExample filmExample = convertExample(filmManagerRequest);
        return filmMapper.countByExample(filmExample);
    }

    /**
     * 分页获取资源列表
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<Film> searchList(FilmManagerRequest filmManagerRequest) {
        FilmExample filmExample = convertExample(filmManagerRequest);
        filmExample.setOrderByClause("create_time desc");
        computePage(filmManagerRequest.getPage(), filmManagerRequest.getLimit());
        filmExample.setLimitStart(limitStart);
        filmExample.setLimitEnd(limitEnd);
        return filmMapper.selectByExample(filmExample);
    }

    private FilmExample convertExample(FilmManagerRequest filmManagerRequest){
        FilmExample filmExample = new FilmExample();
        FilmExample.Criteria criteria = filmExample.createCriteria().andDelFlagEqualTo(0);
        if(StringUtils.isNotBlank(filmManagerRequest.getFilmName())){
            criteria.andFilmNameLike("%" + filmManagerRequest.getFilmName() + "%");
        }
        if(filmManagerRequest.getFilmType() != null){
            criteria.andFilmTypeEqualTo(filmManagerRequest.getFilmType());
        }
        if(filmManagerRequest.getStatus() != null){
            criteria.andStatusEqualTo(filmManagerRequest.getStatus());
        }
        return filmExample;
    }

    /**
     * 修改图片封面
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public JSONObject modifyImage(MultipartFile file) {
        JSONObject result = new JSONObject();
        String filePath = SystemConfig.getFilmImagePath(); //  /home/sandman/film/image
        String fileName = FileUtils.getFileNameByTime(file.getOriginalFilename());
        String url = SystemConfig.getFilmImage() + "/" + fileName;  //  http://117.48.197.114/film/{fileName}
        //开始将文件上传到远程服务器
        File tempFile = FileUtils.getFileByMultipartFile(file);//MultiPartFile转File
        boolean uploadSuccess = FileUtils.upload(filePath,fileName,tempFile);//上传服务器
        tempFile.delete();
        if(uploadSuccess){//如果上传远程服务器成功
            result.put("url",url);
        }
        return result;
    }

    /**
     * 添加影片
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int insertFilm(Film film) {
        film.setBuyCount(0);
        film.setCreateTime(new Date());
        film.setUpdateTime(new Date());
        film.setDelFlag(0);
        return filmMapper.insertSelective(film);
    }
}
