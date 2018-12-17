/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.base;

import com.sandman.download.dao.mysql.download.model.auto.GoldLog;
import com.sandman.download.dao.mysql.download.model.auto.Resource;
import com.sandman.download.dao.mysql.download.model.auto.ResourceLog;
import com.sandman.download.dao.mysql.mapper.CustomizeMapper;
import com.sandman.download.dao.mysql.system.model.auto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author sunpeikai
 * @version BaseServiceImpl, v0.1 2018/12/3 11:57
 */
@Service
public class BaseServiceImpl extends CustomizeMapper implements BaseService {

    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
    /**
     * 根据username查询user信息
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username).andAvailableEqualTo(1);
        List<User> userList = userMapper.selectByExample(userExample);
        if(!CollectionUtils.isEmpty(userList)){
            return userList.get(0);
        }
        return null;
    }

    /**
     * 根据userId获取用户
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public User getUserByUserId(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * 更新user表
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int updateUserByUserId(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 根据code获取信息模板
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public Template getTemplateByCode(String tplCode) {
        TemplateExample templateExample = new TemplateExample();
        templateExample.createCriteria().andTplCodeEqualTo(tplCode).andStatusEqualTo(1);
        List<Template> templateList = templateMapper.selectByExample(templateExample);
        if(!CollectionUtils.isEmpty(templateList)){
            return templateList.get(0);
        }
        return null;
    }

    /**
     * 根据contact查询验证码
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public ValidateCode getValidateCodeByContact(String contact) {
        ValidateCodeExample validateCodeExample = new ValidateCodeExample();
        validateCodeExample.createCriteria().andContactEqualTo(contact).andValidEqualTo(1).andDelFlagEqualTo(0);
        List<ValidateCode> validateCodeList = validateCodeMapper.selectByExample(validateCodeExample);
        if(!CollectionUtils.isEmpty(validateCodeList)){
            return validateCodeList.get(0);
        }
        return null;
    }

    /**
     * 根据userId查询登录日志
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public UserLoginLog getLoginLogByUserId(int userId) {
        UserLoginLogExample userLoginLogExample = new UserLoginLogExample();
        userLoginLogExample.createCriteria().andUserIdEqualTo(userId);
        List<UserLoginLog> userLoginLogList = userLoginLogMapper.selectByExample(userLoginLogExample);
        if(!CollectionUtils.isEmpty(userLoginLogList)){
            return userLoginLogList.get(0);
        }
        return null;
    }

    /**
     * 获取控制访问权限list
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<SecureConfig> getSecureConfigList() {
        SecureConfigExample secureConfigExample = new SecureConfigExample();
        secureConfigExample.createCriteria().andStatusEqualTo(1).andDelFlagEqualTo(0);
        return secureConfigMapper.selectByExample(secureConfigExample);
    }

    /**
     * 添加资源操作日志
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int insertResourceLog(Integer userId, Integer resourceId, Integer type) {
        ResourceLog resourceLog = new ResourceLog();
        Date now = new Date();
        resourceLog.setUserId(userId);
        resourceLog.setResourceId(resourceId);
        resourceLog.setType(type);
        switch (type){
            case 1:resourceLog.setDesc("用户上传资源");break;
            case 2:resourceLog.setDesc("用户下载资源");break;
        }
        resourceLog.setCreateTime(now);
        resourceLog.setUpdateTime(now);
        resourceLog.setDelFlag(0);
        return resourceLogMapper.insertSelective(resourceLog);
    }

    /**
     * 根据资源id获取详细信息
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public Resource getResourceById(Integer id) {
        return resourceMapper.selectByPrimaryKey(id);
    }

    /**
     * 积分操作记录
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public GoldLog goldOperation(Integer userId, Integer resourceId,
                                 String resourceName, Integer originalGold,
                                 Integer resourceGold, Integer currentGold,
                                 String desc, Integer type) {
        GoldLog goldLog = new GoldLog();
        goldLog.setUserId(userId);
        if(resourceId != null){
            goldLog.setResourceId(resourceId);
        }
        return null;
    }
}
