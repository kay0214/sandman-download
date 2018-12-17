/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.base;

import com.sandman.download.dao.mysql.download.model.auto.GoldLog;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.dao.mysql.system.model.auto.*;

import java.util.List;

/**
 * @author sunpeikai
 * @version BaseService, v0.1 2018/12/3 11:56
 */
public interface BaseService {
    /**
     * 根据username查询user信息
     * @auth sunpeikai
     * @param
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 根据userId获取用户
     * @auth sunpeikai
     * @param
     * @return
     */
    User getUserByUserId(Integer userId);

    /**
     * 更新user表
     * @auth sunpeikai
     * @param
     * @return
     */
    int updateUserByUserId(User user);

    /**
     * 根据code获取信息模板
     * @auth sunpeikai
     * @param
     * @return
     */
    Template getTemplateByCode(String tplCode);

    /**
     * 根据contact查询验证码
     * @auth sunpeikai
     * @param
     * @return
     */
    ValidateCode getValidateCodeByContact(String contact);

    /**
     * 根据userId查询登录日志
     * @auth sunpeikai
     * @param
     * @return
     */
    UserLoginLog getLoginLogByUserId(int userId);

    /**
     * 获取控制访问权限list
     * @auth sunpeikai
     * @param
     * @return
     */
    List<SecureConfig> getSecureConfigList();

    /**
     * 添加资源操作日志
     * @auth sunpeikai
     * @param
     * @return
     */
    int insertResourceLog(Integer userId,Integer resourceId,Integer type);

    /**
     * 根据资源id获取详细信息
     * @auth sunpeikai
     * @param
     * @return
     */
    Resource getResourceById(Integer id);

    /**
     * 积分操作记录
     * @auth sunpeikai
     * @param
     * @return
     */
    GoldLog goldOperation(Integer userId,
                    Integer resourceId,
                    String resourceName,
                    Integer originalGold,
                    Integer resourceGold,
                    Integer currentGold,
                    String desc,
                    Integer type);
}
