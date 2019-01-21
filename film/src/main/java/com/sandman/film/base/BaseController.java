/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.base;

import com.sandman.film.bean.film.CheckSuccessBean;
import com.sandman.film.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sunpeikai
 * @version BaseController, v0.1 2018/11/23 14:48
 */
public class BaseController {
    public static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 下载前检查(成功后)
     * @auth sunpeikai
     * @param
     * @return
     */
    public void checkSuccess(Integer resourceId,Integer resourceLogId){
        CheckSuccessBean checkSuccessBean = new CheckSuccessBean();
        checkSuccessBean.setResourceId(resourceId);
        checkSuccessBean.setResourceLogId(resourceLogId);
        SessionUtils.setSessionAttribute("checkSuccess",checkSuccessBean);
    }

    /**
     * 判断下载前检查是否通过
     * @auth sunpeikai
     * @param
     * @return
     */
    public int checkIfSuccess(Integer resourceId){
        try{
            CheckSuccessBean checkSuccessBean = (CheckSuccessBean)SessionUtils.getSessionAttribute("checkSuccess");
            if(checkSuccessBean != null){
                if(resourceId.equals(checkSuccessBean.getResourceId())){
                    if(null != checkSuccessBean.getResourceLogId()){
                        return 2;
                    }else{
                        return 1;
                    }
                }else{
                    return 0;
                }

            }
            return 0;
        }catch (Exception e){
            logger.info("检查出错!");
            return 0;
        }
    }
}
