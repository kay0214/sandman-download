/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.service;

import com.sandman.emmmoe.base.BaseServiceImpl;
import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.NetDisk;
import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.NetDiskExample;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author sunpeikai
 * @version ManagerService, v0.1 2018/12/20 17:18
 */
@Service
public class ManagerService extends BaseServiceImpl {

    /**
     * 获取一个未处理的网盘地址
     * @auth sunpeikai
     * @param
     * @return
     */
    public NetDisk getOneUnHandleNetDisk(){
        NetDiskExample netDiskExample = new NetDiskExample();
        netDiskExample.setOrderByClause("page asc");
        netDiskExample.createCriteria().andSuccessEqualTo(0);
        List<NetDisk> netDiskList = netDiskMapper.selectByExample(netDiskExample);
        if(!CollectionUtils.isEmpty(netDiskList)){
            return netDiskList.get(0);
        }
        return null;
    }

    public void updateUnzipPass(Integer id,String unzipPass){
        NetDisk netDisk = netDiskMapper.selectByPrimaryKey(id);
        netDisk.setUnzipPass(unzipPass);
        netDiskMapper.updateByPrimaryKeySelective(netDisk);
    }
}
