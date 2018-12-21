package com.sandman.emmmoe.dao.mysql.mapper;

import com.sandman.emmmoe.dao.mysql.emmmoe.mapper.auto.NetDiskMapper;
import com.sandman.emmmoe.dao.mysql.emmmoe.mapper.auto.NotContainsMapper;
import com.sandman.emmmoe.dao.mysql.emmmoe.mapper.auto.PageInfoMapper;
import com.sandman.emmmoe.dao.mysql.emmmoe.mapper.auto.RootUrlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoMapper {
    @Autowired
    protected PageInfoMapper pageInfoMapper;
    @Autowired
    protected NetDiskMapper netDiskMapper;
    @Autowired
    protected RootUrlMapper rootUrlMapper;
    @Autowired
    protected NotContainsMapper notContainsMapper;
}

