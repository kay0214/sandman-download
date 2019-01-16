/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.base;

import com.sandman.download.dao.mysql.download.model.auto.*;
import com.sandman.download.dao.mysql.mapper.CustomizeMapper;
import com.sandman.download.dao.mysql.system.model.auto.*;
import org.apache.commons.lang3.StringUtils;
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

    protected static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
    protected int limitStart = -1;
    protected int limitEnd = -1;
    protected void computePage(Integer page,Integer limit){
        if(page != null && limit != null){
            limitStart = (page-1) * limit;
            limitEnd = limit;
        }
    }
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
        validateCodeExample.createCriteria().andContactEqualTo(contact).andValidNotEqualTo(0).andDelFlagEqualTo(0);
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
    public ResourceLog insertResourceLog(Integer userId, Integer resourceId,String resourceName, Integer type) {
        ResourceLog resourceLog = new ResourceLog();
        Date now = new Date();
        resourceLog.setUserId(userId);
        resourceLog.setResourceId(resourceId);
        resourceLog.setResourceName(resourceName);
        resourceLog.setType(type);
        switch (type){
            case 1:resourceLog.setDesc("用户上传资源");break;
            case 2:resourceLog.setDesc("用户下载资源");break;
        }
        resourceLog.setCreateTime(now);
        resourceLog.setUpdateTime(now);
        resourceLog.setDelFlag(0);
        int result = resourceLogMapper.insertSelective(resourceLog);
        if(result>0){
            ResourceLogExample resourceLogExample = new ResourceLogExample();
            resourceLogExample.createCriteria().andUserIdEqualTo(userId).andResourceIdEqualTo(resourceId).andTypeEqualTo(type).andCreateTimeEqualTo(now);
            List<ResourceLog> resourceLogList = resourceLogMapper.selectByExample(resourceLogExample);
            if(!CollectionUtils.isEmpty(resourceLogList)){
                return resourceLogList.get(0);
            }
        }
        return null;
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
    public int goldOperation(Integer userId, Integer resourceId,
                             String resourceName, Integer originalGold,
                             Integer resourceGold, Integer currentGold,
                             String desc, Integer type, Date now) {
        // 预留type，1积分扣除，2积分增加
        GoldLog goldLog = new GoldLog();
        goldLog.setUserId(userId);
        goldLog.setResourceId(resourceId);
        goldLog.setResourceName(resourceName);
        goldLog.setOriginalGold(originalGold);
        goldLog.setResourceGold(resourceGold);
        goldLog.setCurrentGold(currentGold);
        goldLog.setOperationDesc(desc);
        goldLog.setCreateTime(now);
        goldLog.setUpdateTime(now);
        return goldLogMapper.insert(goldLog);
    }

    /**
     * 查询积分记录
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public GoldLog getGoldLog(Integer userId, Integer resourceId, Date createTime) {
        GoldLogExample example = new GoldLogExample();
        example.createCriteria().andUserIdEqualTo(userId).andResourceIdEqualTo(resourceId).andCreateTimeEqualTo(createTime).andUpdateTimeEqualTo(createTime);
        List<GoldLog> goldLogList = goldLogMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(goldLogList)){
            return goldLogList.get(0);
        }
        return null;
    }

    /**
     * 更新资源信息
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int updateResource(Resource resource) {
        return resourceMapper.updateByPrimaryKeySelective(resource);
    }

    /**
     * 更新用户信息
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 更新积分记录
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int updateGoldLog(GoldLog goldLog) {
        return goldLogMapper.updateByPrimaryKeySelective(goldLog);
    }

    /**
     * 更新下载记录
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int updateResourceLog(ResourceLog resourceLog) {
        return resourceLogMapper.updateByPrimaryKeySelective(resourceLog);
    }

    /**
     * 根据id获取到资源操作记录
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public ResourceLog getResourceLogById(Integer id) {
        return resourceLogMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据contact删除验证码数据
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public void deleteByContact(String contact){
        ValidateCodeExample validateCodeExample = new ValidateCodeExample();
        validateCodeExample.createCriteria().andContactEqualTo(contact);
        validateCodeMapper.deleteByExample(validateCodeExample);
    }


    @Override
    public void updateValidateCode(ValidateCode validateCode) {
        validateCodeMapper.updateByPrimaryKey(validateCode);
    }

    /**
     * 根据邮箱删除一个账户
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public int deleteUserByEmail(String email) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(email);
        return userMapper.deleteByExample(userExample);
    }
}
