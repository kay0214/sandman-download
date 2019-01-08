/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.base;

import com.sandman.download.dao.mysql.download.model.auto.GoldLog;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.dao.mysql.download.model.auto.ResourceLog;
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
    ResourceLog insertResourceLog(Integer userId, Integer resourceId,String resourceName, Integer type);

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

    /**
     * 更新资源信息
     * @auth sunpeikai
     * @param
     * @return
     */
    int updateResource(Resource resource);

    /**
     * 更新用户信息
     * @auth sunpeikai
     * @param
     * @return
     */
    int updateUser(User user);

    /**
     * 更新积分记录
     * @auth sunpeikai
     * @param
     * @return
     */
    int updateGoldLog(GoldLog goldLog);

    /**
     * 更新下载记录
     * @auth sunpeikai
     * @param
     * @return
     */
    int updateResourceLog(ResourceLog resourceLog);

    /**
     * 根据id获取到资源操作记录
     * @auth sunpeikai
     * @param
     * @return
     */
    ResourceLog getResourceLogById(Integer id);


    /**
     * 根据contact删除验证码数据
     * @auth sunpeikai
     * @param
     * @return
     */
    void deleteByContact(String contact);


    /**
     * 更新验证码状态为 1 已发送
     * @auth sunpeikai
     * @param
     * @return
     */
    void updateValidateCode(ValidateCode validateCode);

    /**
     * 根据邮箱删除一个账户
     * @auth sunpeikai
     * @param
     * @return
     */
    int deleteUserByEmail(String email);

}
