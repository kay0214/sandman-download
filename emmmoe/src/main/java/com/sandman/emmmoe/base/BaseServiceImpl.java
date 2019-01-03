/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.base;

import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.*;
import com.sandman.emmmoe.dao.mysql.mapper.CustomizeMapper;
import org.apache.commons.lang3.StringUtils;
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

    protected static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
    protected int limitStart = -1;
    protected int limitEnd = -1;
    protected void computePage(Integer page,Integer limit){
        limitStart = (page-1) * limit;
        limitEnd = limit;
    }

    /**
     * 获取emmmoe登船地址页面
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public String getRootUrl(){
        RootUrlExample rootUrlExample = new RootUrlExample();
        rootUrlExample.setOrderByClause("create_time desc");
        rootUrlExample.createCriteria().andRootEqualTo(1);
        List<RootUrl> rootUrls = rootUrlMapper.selectByExample(rootUrlExample);
        if(!CollectionUtils.isEmpty(rootUrls)){
            for(RootUrl rootUrl:rootUrls){
                if(StringUtils.isNotBlank(rootUrl.getUrl())){
                    return rootUrl.getUrl();
                }
            }

        }
        return null;
    }

    /**
     * 爬取时不包含的url列表
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<NotContains> getNotContainsUrl() {
        NotContainsExample notContainsExample = new NotContainsExample();
        notContainsExample.createCriteria().andEnableEqualTo(1);
        return notContainsMapper.selectByExample(notContainsExample);
    }

    /**
     * 获取到未完成的pageInfo
     * @auth sunpeikai
     * @param
     * @return
     */
    @Override
    public List<PageInfo> getNotSuccessPage() {
        PageInfoExample pageInfoExample = new PageInfoExample();
        pageInfoExample.setOrderByClause("page ASC");
        pageInfoExample.createCriteria().andSuccessEqualTo(0);
        return pageInfoMapper.selectByExample(pageInfoExample);
    }
}
