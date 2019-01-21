/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.controller;

import com.sandman.film.base.BaseController;
import com.sandman.film.base.BaseResult;
import com.sandman.film.constant.ReturnMessage;
import com.sandman.film.dao.mysql.film.model.auto.Film;
import com.sandman.film.dao.mysql.film.model.auto.FilmLog;
import com.sandman.film.dao.mysql.system.model.auto.User;
import com.sandman.film.service.film.FilmService;
import com.sandman.film.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        logger.info("购买影片,id:[{}]",id);
        User user = filmService.getUserByUserId(SessionUtils.getUserId());
        if(user!=null){
            FilmLog filmLog = filmService.getFilmLogByUserIdAndFilmId(user.getUserId(),id);
            // 判断是否重复购买
            if(filmLog == null){
                // 未购买
                Film film = filmService.getFilmById(id);
                // 判断是否存在
                if(film!=null){
                    // 判断用户积分是否足以购买该影片
                    if(user.getGold()>film.getFilmGold()){
                        // 积分足够可以购买影片
                        int result = filmService.buyFilm(user,film);
                        if(result>0){
                            return new BaseResult();
                        }else{
                            return new BaseResult(ReturnMessage.ERR_USER_BUY_FILM);
                        }
                    }
                    // 积分不足
                    return new BaseResult(ReturnMessage.ERR_USER_GOLD_NOT_ENOUGH);
                }
                // 资源不存在
                return new BaseResult(ReturnMessage.ERR_RESOURCE_NOT_EXIST);
            }
            // 已购买
            return new BaseResult(ReturnMessage.ERR_USER_ALREADY_BUY);
        }
        return new BaseResult(ReturnMessage.ERR_USER_LOGIN_INVALID);
    }

    @ResponseBody
    @GetMapping(value = "/hot_film")
    public BaseResult hotResources(){
        List<Film> hotFilm = filmService.getHotFilm();
        return new BaseResult(hotFilm);
    }
}
