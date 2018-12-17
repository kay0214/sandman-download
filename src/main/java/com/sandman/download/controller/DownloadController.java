/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.bean.download.CheckInfoBean;
import com.sandman.download.constant.ReturnMessage;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.dao.mysql.system.model.auto.User;
import com.sandman.download.service.download.DownloadService;
import com.sandman.download.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sunpeikai
 * @version DownloadController, v0.1 2018/12/12 16:21
 */
@Controller
@RequestMapping(value = "/download")
public class DownloadController extends BaseController {

    @Autowired
    private DownloadService downloadService;

    @GetMapping(value = "/download")
    public ModelAndView download(Integer id){
        logger.info("download -> id:[{}]",id);
        // 当前登录用户
        Integer userId = SessionUtils.getUserId();
        // 防止积分没有刷新过来
        User user = downloadService.getUserByUserId(userId);
        Resource resource = downloadService.getResourceById(id);
        if(resource != null){
            //当前登录用户与资源拥有者不是同一人
            if(!resource.getUserId().equals(user.getUserId())){
                logger.info("上传下载不同人");
                //下载前的检查已经判断过用户积分是否足够

            }else{
                logger.info("上传下载同一人");
            }
            return new ModelAndView("redirect:/resource/get_info?id=" + id);
        }
        // 没有这个资源就定向到首页
        return new ModelAndView("redirect:/");
    }

    /**
     * 下载前的检查
     * @auth sunpeikai
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/check_info")
    public BaseResult checkInfo(@RequestBody CheckInfoBean checkInfoBean){
        // 检查 1：资源是否存在，2：积分是否足够，3：type为2是 是否是VIP
        logger.info("下载前检查 -> id:[{}],type:[{}]",checkInfoBean.getId(),checkInfoBean.getType());
        Resource resource = downloadService.getResourceById(checkInfoBean.getId());
        if(resource == null){
            //资源不存在
            return new BaseResult(ReturnMessage.ERR_RESOURCE_NOT_EXIST);
        }
        Integer userId = SessionUtils.getUserId();
        User user = downloadService.getUserByUserId(userId);
        int curUserGold = user.getGold();//当前用户积分
        int resGold = resource.getResourceGold();//资源积分
        if(curUserGold<resGold){
            //积分不足
            return new BaseResult(ReturnMessage.ERR_USER_GOLD_NOT_ENOUGH);
        }
        if(checkInfoBean.getType() == 2 && user.getRole() != 2){
            // 用户不是VIP
            return new BaseResult(ReturnMessage.ERR_USER_NOT_VIP);
        }
        return new BaseResult();
    }
}
