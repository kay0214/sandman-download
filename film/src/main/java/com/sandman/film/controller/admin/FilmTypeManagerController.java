/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.controller.admin;

import com.sandman.film.base.BaseController;
import com.sandman.film.base.BaseResult;
import com.sandman.film.bean.admin.TypeManagerRequest;
import com.sandman.film.dao.mysql.film.model.auto.FilmType;
import com.sandman.film.service.admin.TypeManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * @author sunpeikai
 * @version FilmTypeManagerController, v0.1 2019/1/22 14:03
 */
@Controller
@RequestMapping(value = "/type_manager")
public class FilmTypeManagerController extends BaseController {

    @Autowired
    private TypeManagerService typeManagerService;

    @GetMapping(value = "/init")
    public ModelAndView init(){

        return new ModelAndView("admin/type_manager");
    }

    @ResponseBody
    @GetMapping(value = "/search")
    public BaseResult search(TypeManagerRequest typeManagerRequest){
        int count = typeManagerService.getTypeCount(typeManagerRequest);
        List<FilmType> filmTypeList = typeManagerService.searchList(typeManagerRequest);
        return new BaseResult(filmTypeList,count);
    }

    @GetMapping(value = "/available")
    public ModelAndView available(Integer id,Integer status){
        logger.info("管理员启用或禁用类型 -> id[{}],status:[{}]",id,status);
        FilmType filmType = typeManagerService.getFilmTypeById(id);
        if(filmType != null && !status.equals(filmType.getStatus())){
            filmType.setStatus(status);
            filmType.setUpdateTime(new Date());
            typeManagerService.updateFilmType(filmType);
        }
        return new ModelAndView("redirect:/type_manager/init");
    }

    @GetMapping(value = "/edit")
    public ModelAndView edit(Integer id){
        FilmType filmType = typeManagerService.getFilmTypeById(id);
        return new ModelAndView("admin/type_audit").addObject("filmType",filmType);
    }

    @PostMapping(value = "/update")
    public ModelAndView update(FilmType filmType){
        filmType.setUpdateTime(new Date());
        typeManagerService.updateFilmType(filmType);
        return new ModelAndView("redirect:/type_manager/init");
    }

    @GetMapping(value = "/delete")
    public ModelAndView delete(Integer id){
        FilmType filmType = typeManagerService.getFilmTypeById(id);
        filmType.setDelFlag(1);
        filmType.setUpdateTime(new Date());
        typeManagerService.updateFilmType(filmType);
        return new ModelAndView("redirect:/type_manager/init");
    }

    @GetMapping(value = "/insert_init")
    public ModelAndView insertInit(){
        return new ModelAndView("admin/type_insert");
    }

    @PostMapping(value = "/insert")
    public ModelAndView insert(FilmType filmType){
        typeManagerService.insertFilmType(filmType);
        return new ModelAndView("redirect:/type_manager/init");
    }
}
