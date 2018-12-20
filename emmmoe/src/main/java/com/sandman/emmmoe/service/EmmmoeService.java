/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.sandman.emmmoe.base.BaseServiceImpl;
import com.sandman.emmmoe.config.SystemConfig;
import com.sandman.emmmoe.constant.CommonConstant;
import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.*;
import com.sandman.emmmoe.utils.HttpUnitUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunpeikai
 * @version EmmmoeService, v0.1 2018/12/19 16:45
 */
@Service
public class EmmmoeService extends BaseServiceImpl {

    private String emmmoePage = "";

    /**
     * 获取每页的数据列表的链接
     * @auth sunpeikai
     * @param
     * @return
     */
    public Map<String,Integer> getEmmmoePageList(String emmmoe){
        Map<String,Integer> resultMap = new HashMap<>();
        emmmoePage = emmmoe + SystemConfig.getEmmmoePrefix();
        int result = 0;
        int page = 1;
        int pageCount = 0;
        int insert = 0;
        int insertCount = 0;
        boolean scan = true;
        WebClient webClient = HttpUnitUtils.getWebClient();
        while (scan){
            String emmmoePageList = emmmoePage.replace("{page}",String.valueOf(page));
            logger.info("当前页面:[{}]",emmmoePageList);
            try{
                // 解析获取页面
                HtmlPage htmlPage = webClient.getPage(emmmoePageList);
                String title = htmlPage.getTitleText();
                if(title.contains("未找到页面")){
                    scan = false;
                    break;
                }
                DomNodeList<DomElement> domElements = htmlPage.getElementsByTagName("article");
                for(DomElement domElement:domElements){
                    DomNodeList<HtmlElement> h2List = domElement.getElementsByTagName("h2");
                    for(HtmlElement htmlElement:h2List){
                        DomNodeList<HtmlElement> aList = htmlElement.getElementsByTagName("a");
                        for(HtmlElement a:aList){
                            pageCount ++;
                            PageInfo pageInfo = new PageInfo();
                            pageInfo.setTitle(a.asText());
                            pageInfo.setPage(page);
                            pageInfo.setUri(a.getAttribute("href").replace(emmmoe,""));
                            pageInfo.setCreateTime(new Date());
                            if(!checkPageInfoExist(pageInfo)){
                                // 如果数据库不存在数据
                                insertCount += pageInfoMapper.insertSelective(pageInfo);
                            }

                        }
                    }
                }
            }catch (Exception e){
                scan = false;
            }
            result += pageCount;
            insert += insertCount;
            logger.info("第[{}]页一共找到[{}]个链接,本页收录了[{}]个链接",page,pageCount,insertCount);
            pageCount = 0;
            insertCount = 0;
            page ++;
        }
        webClient.close();
        resultMap.put("count",result);
        resultMap.put("insert",insert);
        logger.info("总共包含记录:[{}],本次收录记录:[{}]",result,insert);
        return resultMap;
    }

    /**
     * 获取百度网盘地址
     * @auth sunpeikai
     * @param
     * @return
     */
    public int getNetDisk(){
        logger.info("开始获取百度网盘地址");
        String rootUrl = getRootUrl();
        int result = 0;
        PageInfoExample pageInfoExample = new PageInfoExample();
        pageInfoExample.setOrderByClause("page ASC");
        pageInfoExample.createCriteria().andSuccessEqualTo(0);
        List<PageInfo> pageInfoList = pageInfoMapper.selectByExample(pageInfoExample);
        if(!CollectionUtils.isEmpty(pageInfoList)){
            WebClient webClient = HttpUnitUtils.getWebClient();
            for(PageInfo pageInfo:pageInfoList){
                //这里获取百度云网盘地址
                try{
                    HtmlPage htmlPage = webClient.getPage(rootUrl + pageInfo.getUri());
                    DomNodeList<DomElement> aList = htmlPage.getElementsByTagName("a");
                    for(DomElement a:aList){
                        String aClass = a.getAttribute("class");
                        String rel = a.getAttribute("rel");
                        if(aClass.contains("downcloud") || "nofollow".equals(rel)){
                            String url = a.getAttribute("href");
                            if(url.contains("pan.baidu.com")){
                                // 只保留百度网盘
                                String netDiskUrl = url.substring(url.lastIndexOf(CommonConstant.EMMMOE_GOTO)).replace(CommonConstant.EMMMOE_GOTO,"");
                                logger.info("获取到的百度网盘url:[{}]",netDiskUrl);
                                NetDisk netDisk = new NetDisk();
                                netDisk.setTitle(pageInfo.getTitle());
                                netDisk.setNetDisk(netDiskUrl);
                                netDisk.setPage(pageInfo.getPage());
                                netDisk.setPass("snzs");
                                // 解压密码后续手动操作
                                netDisk.setSuccess(0);
                                netDisk.setCreateTime(new Date());
                                boolean success = netDiskMapper.insertSelective(netDisk)>0;
                                if(success){
                                    result += 1;
                                    // 处理完了以后，把pageInfo的success置为1
                                    pageInfo.setSuccess(1);
                                    pageInfoMapper.updateByPrimaryKeySelective(pageInfo);
                                }
                            }else{
                                pageInfo.setSuccess(2);
                                pageInfoMapper.updateByPrimaryKeySelective(pageInfo);
                            }

                        }
                    }
                }catch (Exception e){
                    logger.info("获取百度网盘url失败,失败的pageInfo id:[{}]",pageInfo.getId());
                }
            }
            webClient.close();
        }
        return result;
    }

    /**
     * 查询pageInfo是否已经存在，如果存在且需要更新则更新。
     * @auth sunpeikai
     * @param
     * @return
     */
    private boolean checkPageInfoExist(PageInfo pageInfo){
        PageInfoExample pageInfoExample = new PageInfoExample();
        pageInfoExample.createCriteria().andUriEqualTo(pageInfo.getUri()).andTitleEqualTo(pageInfo.getTitle());
        List<PageInfo> pageInfoList = pageInfoMapper.selectByExample(pageInfoExample);
        if(!CollectionUtils.isEmpty(pageInfoList)){
            PageInfo database = pageInfoList.get(0);
            if(!database.getPage().equals(pageInfo.getPage())){
                database.setPage(pageInfo.getPage());
                pageInfoMapper.updateByPrimaryKeySelective(database);
            }
            return true;
        }
        return false;
    }
}
