/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.controller;

import com.sandman.film.base.BaseController;
import com.sandman.film.base.BaseResult;
import com.sandman.film.dao.mysql.film.model.auto.Film;
import com.sandman.film.service.film.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sunpeikai
 * @version FilmController, v0.1 2019/1/21 16:15
 */
@Controller
@RequestMapping(value = "/film")
public class FilmController extends BaseController {

    @Autowired
    private FilmService filmService;

    @GetMapping(value = "/get_info")
    public ModelAndView getInfo(Integer id){
        Film film = filmService.getFilmById(id);
        return new ModelAndView("film_info").addObject("film",film);
    }

    @ResponseBody
    @GetMapping(value = "/buy_film")
    public BaseResult buyFilm(Integer id){
        logger.info("购买影片");
        return new BaseResult();
    }
}
