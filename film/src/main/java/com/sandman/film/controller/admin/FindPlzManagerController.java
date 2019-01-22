/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.controller.admin;

import com.sandman.film.base.BaseController;
import com.sandman.film.base.BaseResult;
import com.sandman.film.bean.film.FindPlzBean;
import com.sandman.film.bean.film.FindPlzPageBean;
import com.sandman.film.constant.ReturnMessage;
import com.sandman.film.dao.mysql.film.model.auto.FindPlz;
import com.sandman.film.service.admin.FindPlzManagerService;
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
 * @version FindPlzManagerController, v0.1 2019/1/22 12:44
 */
@Controller
@RequestMapping(value = "/findPlz_manager")
public class FindPlzManagerController extends BaseController {

    @Autowired
    private FindPlzManagerService findPlzManagerService;

    @GetMapping(value = "/init")
    public ModelAndView init(){
        return new ModelAndView("admin/findPlz_manager");
    }

    @ResponseBody
    @GetMapping(value = "/search")
    public BaseResult search(FindPlzPageBean findPlzPageBean){
        int count = findPlzManagerService.getFindPlzCount(findPlzPageBean);
        List<FindPlz> findPlzList = findPlzManagerService.searchList(findPlzPageBean);
        return new BaseResult(findPlzList,count);
    }

    @ResponseBody
    @PostMapping(value = "/repay")
    public BaseResult repay(Integer id,String content){
        FindPlz findPlz = new FindPlz();
        findPlz.setId(id);
        findPlz.setStatus(1);
        findPlz.setStatusDesc(content);
        findPlz.setUpdateTime(new Date());
        int result = findPlzManagerService.updateFindPlz(findPlz);
        if(result > 0){
            return new BaseResult();
        }else{
            return new BaseResult(ReturnMessage.ERR_REPAY);
        }
    }
}
