/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sandman.film.base.BaseController;
import com.sandman.film.base.BaseResult;
import com.sandman.film.bean.admin.FilmManagerRequest;
import com.sandman.film.constant.ReturnMessage;
import com.sandman.film.dao.mysql.film.model.auto.Film;
import com.sandman.film.dao.mysql.film.model.auto.FilmType;
import com.sandman.film.service.admin.FilmManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @author sunpeikai
 * @version ResourceManagerController, v0.1 2019/1/10 17:25
 */
@Controller
@RequestMapping(value = "/film_manager")
public class FilmManagerController extends BaseController {

    @Autowired
    private FilmManagerService filmManagerService;

    @GetMapping(value = "/init")
    public ModelAndView init(){
        List<FilmType> filmTypeList = filmManagerService.getAllType();
        return new ModelAndView("admin/film_manager").addObject("filmTypeList",filmTypeList);
    }

    @ResponseBody
    @GetMapping(value = "/search")
    public BaseResult search(FilmManagerRequest filmManagerRequest){
        logger.info("查询列表分页 -> page:[{}],limit:[{}]", filmManagerRequest.getPage(), filmManagerRequest.getLimit());
        int count = filmManagerService.getFilmCount(filmManagerRequest);
        List<Film> filmList = filmManagerService.searchList(filmManagerRequest);
        return new BaseResult(filmList,count);
    }

    @GetMapping(value = "/delete")
    public ModelAndView delete(Integer id){
        logger.info("管理员删除资源id -> [{}]",id);
        Film film = filmManagerService.getFilmById(id);
        film.setDelFlag(1);
        film.setUpdateTime(new Date());
        filmManagerService.updateFilm(film);
        return new ModelAndView("redirect:/film_manager/init");
    }

    @GetMapping(value = "/audit")
    public ModelAndView audit(Integer id){
        logger.info("管理员编辑资源id -> [{}]",id);
        Film film = filmManagerService.getFilmById(id);
        List<FilmType> filmTypeList = filmManagerService.getAllType();
        return new ModelAndView("admin/film_audit").addObject("film",film).addObject("filmTypeList",filmTypeList);
    }

    @PostMapping(value = "/update")
    public ModelAndView update(Film film){
        logger.info("管理员更新影片 -> [{}]", JSON.toJSONString(film));
        film.setUpdateTime(new Date());
        filmManagerService.updateFilm(film);
        return new ModelAndView("redirect:/film_manager/init");
    }

    @ResponseBody
    @PostMapping(value = "/modify_image")
    public BaseResult modifyImage(@RequestParam("file") MultipartFile file){
        logger.info("修改影片封面 -> size:[{}]",file.getSize());
        JSONObject result = filmManagerService.modifyImage(file);
        if(result.containsKey("url")){
            return new BaseResult(result);
        }else{
            return new BaseResult(ReturnMessage.ERR_UPLOAD_FAILED);
        }
    }

    @GetMapping(value = "/insert_init")
    public ModelAndView insertInit(){
        List<FilmType> filmTypeList = filmManagerService.getAllType();
        return new ModelAndView("admin/film_insert").addObject("filmTypeList",filmTypeList);
    }

    @PostMapping(value = "/insert")
    public ModelAndView insert(Film film){
        logger.info("插入影片 -> [{}]",JSON.toJSONString(film));
        filmManagerService.insertFilm(film);
        return new ModelAndView("redirect:/film_manager/init");
    }
}
