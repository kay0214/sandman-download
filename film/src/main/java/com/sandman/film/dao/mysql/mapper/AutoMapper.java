package com.sandman.film.dao.mysql.mapper;

import com.sandman.film.dao.mysql.film.mapper.auto.*;
import com.sandman.film.dao.mysql.system.mapper.auto.*;
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
    protected FilmMapper filmMapper;
    @Autowired
    protected FilmLogMapper filmLogMapper;
    @Autowired
    protected FindPlzMapper findPlzMapper;
    @Autowired
    protected FilmTypeMapper filmTypeMapper;
    @Autowired
    protected TemplateMapper templateMapper;
    @Autowired
    protected SecureConfigMapper secureConfigMapper;
    /** 系统组件 */
    @Autowired
    protected NoticeMapper noticeMapper;
    @Autowired
    protected FriendlyLinkMapper friendlyLinkMapper;
    @Autowired
    protected SignInMapper signInMapper;
}

