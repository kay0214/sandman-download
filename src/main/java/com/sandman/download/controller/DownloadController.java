/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.controller;

import com.sandman.download.base.BaseController;
import com.sandman.download.base.BaseResult;
import com.sandman.download.bean.download.CheckInfoBean;
import com.sandman.download.constant.CommonConstant;
import com.sandman.download.constant.ReturnMessage;
import com.sandman.download.dao.mysql.download.model.auto.GoldLog;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.dao.mysql.download.model.auto.ResourceLog;
import com.sandman.download.dao.mysql.system.model.auto.User;
import com.sandman.download.service.download.DownloadService;
import com.sandman.download.utils.FileUtils;
import com.sandman.download.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

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
    public ModelAndView download(Integer id, HttpServletResponse response){
        logger.info("emmmoe -> id:[{}]",id);
        // 检查是否通过
        int checkSuccess = checkIfSuccess(id);
        if(checkSuccess == 0){
            // 未通过检查
            return new ModelAndView("redirect:/resource/get_info?id=" + id).addObject("errorMsg","检查未通过");
        }

        // 当前登录用户
        Integer userId = SessionUtils.getUserId();
        // 防止积分没有刷新过来
        User user = downloadService.getUserByUserId(userId);
        Resource resource = downloadService.getResourceById(id);
        if(resource != null){

            String fileNameWithoutType = FileUtils.getFileNameByUrl(resource.getResourceUrl());
            String fileName = ("file".equals(resource.getResourceType()))?fileNameWithoutType:(fileNameWithoutType + "." + resource.getResourceType());

            if(checkSuccess == 2){
                // 通过检查，且resourceLogId不为空.说明该用户已下载过该资源，直接放行下载
                logger.info("用户已下载过该资源 -> resourceId:[{}]",resource.getId());
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/force-emmmoe");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=\"" + FileUtils.getRightFileNameUseCode(FileUtils.getFileNameRemoveTime(fileName)) + "\"");// 设置文件名
                boolean success = FileUtils.download(FileUtils.getFilePathByUrl(resource.getResourceUrl()),fileName,response);
                if(!success){
                    return new ModelAndView("redirect:/resource/get_info?id=" + id).addObject("errorMsg",ReturnMessage.ERR_DOWNLOAD.getMessage());
                }
            }

            //当前登录用户与资源拥有者不是同一人
            if(!resource.getUserId().equals(user.getUserId())){
                logger.info("上传下载不同人,fileName:[{}]",fileName);
                //下载前的检查已经判断过用户积分是否足够
                //下载者写入积分详情
                GoldLog downloadUserRecord = downloadService.goldOperation(userId,resource.getId(),resource.getResourceName(),user.getGold(),resource.getResourceGold(),(user.getGold()-resource.getResourceGold()), CommonConstant.GOLD_REDUCE_DESC,1);
                //资源拥有者写入积分详情
                User owner = downloadService.getUserByUserId(resource.getUserId());
                GoldLog ownerRecord = downloadService.goldOperation(owner.getUserId(),resource.getId(),resource.getResourceName(),owner.getGold(),resource.getResourceGold(),(owner.getGold()+resource.getResourceGold()), CommonConstant.GOLD_ADD_DESC,2);
                //下载者写入下载记录
                ResourceLog downloadRecord = downloadService.insertResourceLog(user.getUserId(),resource.getId(),resource.getResourceName(),2);

                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/force-emmmoe");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=\"" + FileUtils.getRightFileNameUseCode(FileUtils.getFileNameRemoveTime(fileName)) + "\"");// 设置文件名
                boolean success = FileUtils.download(FileUtils.getFilePathByUrl(resource.getResourceUrl()),fileName,response);
                if(success){//如果下载成功
                    logger.info("上传下载不同人，下载成功");
                    //资源下载次数++
                    logger.info("id:{}的资源下载次数+1。原下载次数:{}",resource.getId(),resource.getDownloadCount());
                    resource.setDownloadCount(resource.getDownloadCount()+1);
                    logger.info("现下载次数:{}",resource.getDownloadCount());
                    //更新数据库
                    downloadService.updateResource(resource);
                    // 资源更新成功才会去更新用户积分
                    //用户积分操作: 下载者扣除积分，上传者加上积分
                    logger.info("curUserGold={},resGold={},ownerGold={}",user.getGold(),resource.getResourceGold(),owner.getGold());
                    //如果积分足够，扣除相应积分
                    user.setGold(user.getGold() - resource.getResourceGold());
                    //资源拥有者加上相应积分
                    owner.setGold(owner.getGold() + resource.getResourceGold());
                    downloadService.updateUser(user);
                    downloadService.updateUser(owner);

                }else{//下载失败，两个用户信息还没有保存，所以只需要删除日志记录和积分记录即可
                    logger.info("上传下载不同人,下载失败");
                    //删除下载者积分记录
                    downloadUserRecord.setDelFlag(1);
                    downloadService.updateGoldLog(downloadUserRecord);
                    //删除资源拥有者积分记录
                    ownerRecord.setDelFlag(1);
                    downloadService.updateGoldLog(ownerRecord);
                    //删除下载记录
                    downloadRecord.setDelFlag(1);
                    downloadService.updateResourceLog(downloadRecord);
                    return new ModelAndView("redirect:/resource/get_info?id=" + id).addObject("errorMsg",ReturnMessage.ERR_DOWNLOAD.getMessage());
                }

            }else{
                logger.info("上传下载同一人,fileName:[{}]",fileName);
                response.setHeader("content-type", "application/octet-stream");
                // 设置强制下载不打开
                response.setContentType("application/force-emmmoe");
                // 设置文件名
                response.addHeader("Content-Disposition", "attachment;fileName=\"" + FileUtils.getRightFileNameUseCode(FileUtils.getFileNameRemoveTime(fileName)) + "\"");
                boolean success = FileUtils.download(FileUtils.getFilePathByUrl(resource.getResourceUrl()),fileName,response);
                if(!success){
                    // 下载出错
                    return new ModelAndView("redirect:/resource/get_info?id=" + id).addObject("errorMsg",ReturnMessage.ERR_DOWNLOAD.getMessage());
                }
            }
            return new ModelAndView("redirect:/resource/get_info?id=" + id).addObject("errorMsg","操作出错");
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
        User user = SessionUtils.getUser();
        if(user == null){
            return new BaseResult(ReturnMessage.ERR_USER_LOGIN_INVALID);
        }
        Resource resource = downloadService.getResourceById(checkInfoBean.getId());
        if(resource == null){
            //资源不存在
            return new BaseResult(ReturnMessage.ERR_RESOURCE_NOT_EXIST);
        }
        Integer userId = SessionUtils.getUserId();
        // 判断是否下载过
        ResourceLog resourceLog = downloadService.getResourceLogByResourceIdAndUserId(checkInfoBean.getId(),userId);
        if(resourceLog != null){
            // 如果已经下载过了
            checkSuccess(checkInfoBean.getId(),resourceLog.getId());
        }else{
            // 如果还没下载过
            user = downloadService.getUserByUserId(userId);
            int curUserGold = user.getGold();//当前用户积分
            int resGold = resource.getResourceGold();//资源积分
            if(curUserGold<resGold){
                //积分不足,判断资源是否是该用户的
                if(!resource.getUserId().equals(userId)){
                    return new BaseResult(ReturnMessage.ERR_USER_GOLD_NOT_ENOUGH);
                }
                logger.info("走到这里说明该资源是登录用户上传的资源");
            }
            if(checkInfoBean.getType() == 2 && user.getRole() != 2){
                // 用户不是VIP
                return new BaseResult(ReturnMessage.ERR_USER_NOT_VIP);
            }
            checkSuccess(checkInfoBean.getId(),null);
        }

        return new BaseResult();
    }
}
