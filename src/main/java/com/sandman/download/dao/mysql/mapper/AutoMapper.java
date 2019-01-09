package com.sandman.download.dao.mysql.mapper;

import com.sandman.download.dao.mysql.download.mapper.auto.GoldLogMapper;
import com.sandman.download.dao.mysql.download.mapper.auto.ResourceLogMapper;
import com.sandman.download.dao.mysql.download.mapper.auto.ResourceMapper;
import com.sandman.download.dao.mysql.system.mapper.auto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoMapper {

    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected ValidateCodeMapper validateCodeMapper;
    @Autowired
    protected UserLoginLogMapper userLoginLogMapper;
    @Autowired
    protected GoldLogMapper goldLogMapper;
    @Autowired
    protected ResourceMapper resourceMapper;
    @Autowired
    protected ResourceLogMapper resourceLogMapper;
    @Autowired
    protected TemplateMapper templateMapper;
    @Autowired
    protected SecureConfigMapper secureConfigMapper;
    /** 系统组件 */
    @Autowired
    protected NoticeMapper noticeMapper;
    @Autowired
    protected FriendlyLinkMapper friendlyLinkMapper;
}

