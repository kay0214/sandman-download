/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.base;

import com.sandman.download.dao.mysql.mapper.CustomizeMapper;
import com.sandman.download.dao.mysql.system.model.auto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);
        if(!CollectionUtils.isEmpty(userList)){
            return userList.get(0);
        }
        return null;
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
        validateCodeExample.createCriteria().andContactEqualTo(contact).andDelFlagEqualTo(0);
        List<ValidateCode> validateCodeList = validateCodeMapper.selectByExample(validateCodeExample);
        if(!CollectionUtils.isEmpty(validateCodeList)){
            return validateCodeList.get(0);
        }
        return null;
    }

}
