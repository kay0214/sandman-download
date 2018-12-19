/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.base;

import com.sandman.emmmoe.dao.mysql.mapper.CustomizeMapper;
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

}
