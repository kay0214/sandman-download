/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.service;

import com.sandman.emmmoe.base.BaseServiceImpl;
import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.NetDisk;
import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.NetDiskExample;
import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.PageInfoExample;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunpeikai
 * @version NetDiskService, v0.1 2018/12/20 11:19
 */
@Service
public class NetDiskService extends BaseServiceImpl {

    public int getNetDiskCount(){
        NetDiskExample netDiskExample = new NetDiskExample();
        netDiskExample.createCriteria().andSuccessEqualTo(1);
        return netDiskMapper.countByExample(netDiskExample);
    }
    /**
     * 获取网盘资源地址
     * @auth sunpeikai
     * @param
     * @return
     */
    public List<NetDisk> getNetDiskPage(Integer page,Integer limit){
        computePage(page, limit);
        NetDiskExample netDiskExample = new NetDiskExample();
        netDiskExample.setOrderByClause("create_time desc");
        netDiskExample.setLimitStart(limitStart);
        netDiskExample.setLimitEnd(limitEnd);
        netDiskExample.createCriteria().andSuccessEqualTo(1);
        return netDiskMapper.selectByExample(netDiskExample);
    }
}
