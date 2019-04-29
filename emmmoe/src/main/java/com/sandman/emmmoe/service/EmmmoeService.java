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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${emmmoe.mainPageTag}")
    private String mainPageTag;
    @Value("${emmmoe.urlClass}")
    private String[] urlClass;
    @Value("${emmmoe.urlRel}")
    private String[] urlRel;

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
                DomNodeList<DomElement> domElements = htmlPage.getElementsByTagName(mainPageTag);
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
                e.printStackTrace();
                //scan = false;
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
        // 获取到未完成的pageInfo
        List<PageInfo> pageInfoList = getNotSuccessPage();
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
                        if(contains(aClass,urlClass) || contains(rel,urlRel)){
                            String url = a.getAttribute("href");
                            // 只保留百度网盘
                            String netDiskUrl = "";
                            try{
                                if(url.contains(CommonConstant.EMMMOE_GOTO)){
                                    netDiskUrl = url.substring(url.lastIndexOf(CommonConstant.EMMMOE_GOTO)).replace(CommonConstant.EMMMOE_GOTO,"");
                                }else{
                                    netDiskUrl = url;
                                }

                            }catch (StringIndexOutOfBoundsException e){
                                // 如果报数组越界，则继续处理下一个a标签
                                continue;
                            }
                            // 不能爬就continue
                            if(!canCrawlUrl(netDiskUrl) || !netDiskUrl.contains("http")){
                                continue;
                            }

                            // 判断重复
                            boolean exist = checkNetDiskExist(netDiskUrl);
                            if(!exist){
                                if(netDiskUrl.contains("pan.baidu.com")){
                                    // 百度网盘
                                    logger.info("获取到的百度网盘url:[{}]",netDiskUrl);
                                    boolean success = updateNetDisk(pageInfo.getTitle(),pageInfo.getUri(),netDiskUrl,pageInfo.getPage(),"snzs",2);
                                    if(success){
                                        result += 1;
                                        // 处理完了以后，把pageInfo的success置为1
                                        pageInfo.setSuccess(1);
                                        pageInfoMapper.updateByPrimaryKeySelective(pageInfo);
                                    }
                                }else if(netDiskUrl.contains("openload.co")){
                                    // openload网盘
                                    logger.info("获取到的openload网盘url:[{}]",netDiskUrl);
                                    boolean success = updateNetDisk(pageInfo.getTitle(),pageInfo.getUri(),netDiskUrl,pageInfo.getPage(),"其他网盘资源",3);
                                    if(success){
                                        result += 1;
                                        // 处理完了以后，把pageInfo的success置为1
                                        pageInfo.setSuccess(1);
                                        pageInfoMapper.updateByPrimaryKeySelective(pageInfo);
                                    }
                                }else if(netDiskUrl.contains("i.loli.net")){
                                    // 恶魔喵地址
                                    logger.info("获取到的恶魔喵url:[{}]",netDiskUrl);
                                    boolean success = updateNetDisk(pageInfo.getTitle(),pageInfo.getUri(),netDiskUrl,pageInfo.getPage(),"其他网盘资源",3);
                                    if(success){
                                        result += 1;
                                        // 处理完了以后，把pageInfo的success置为1
                                        pageInfo.setSuccess(1);
                                        pageInfoMapper.updateByPrimaryKeySelective(pageInfo);
                                    }
                                }else{
                                    logger.info("获取到的其他资源url:[{}]",netDiskUrl);
                                    // success=3其他网盘及非网盘资源
                                    boolean success = updateNetDisk(pageInfo.getTitle(),pageInfo.getUri(),netDiskUrl,pageInfo.getPage(),"其他非网盘资源",3);
                                    if(success){
                                        result += 1;
                                        // 处理完了以后，把pageInfo的success置为1
                                        pageInfo.setSuccess(1);
                                        pageInfoMapper.updateByPrimaryKeySelective(pageInfo);
                                    }

                                }

                            }

                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    logger.info("获取百度网盘url失败,失败的pageInfo id:[{}]，错误原因:[{}]",pageInfo.getId(),e.getMessage());
                }
            }
            webClient.close();
        }
        // 这里处理完了以后再把所有未完成的pageinfo添加到netDisk，以为大多数是在线图片包
        List<PageInfo> notSuccessList = getNotSuccessPage();
        if(!CollectionUtils.isEmpty(notSuccessList)){
            for(PageInfo notSuccess:notSuccessList){
                if(canCrawlUrl(rootUrl + notSuccess.getUri()) && !checkNetDiskExist(rootUrl + notSuccess.getUri())){
                    logger.info("在线资源 -> title:[{}],url[{}]",notSuccess.getTitle(),rootUrl + notSuccess.getUri());
                    // success=4  在线资源
                    boolean success = updateNetDisk(notSuccess.getTitle(),notSuccess.getUri(),rootUrl + notSuccess.getUri(),notSuccess.getPage(),"在线资源无需密码",4);
                    if(success){
                        result += 1;
                        //在线资源置为2，且不再重复扫描
                        notSuccess.setSuccess(2);
                        pageInfoMapper.updateByPrimaryKeySelective(notSuccess);
                    }
                }
            }
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
        pageInfoExample.createCriteria().andUriEqualTo(pageInfo.getUri());
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

    public boolean check(String title,String uri){
        return checkNetDiskExist(uri);
    }

    /**
     * 网盘地址防重复
     * @auth sunpeikai
     * @param
     * @return
     */
    private boolean checkNetDiskExist(String uri){
        if(StringUtils.isBlank(uri)){
            return true;
        }
        NetDiskExample netDiskExample = new NetDiskExample();
        netDiskExample.createCriteria().andNetDiskEqualTo(uri);
        return netDiskMapper.selectByExample(netDiskExample).size()>0;
    }

    /**
     * 公共插入网盘资源表的方法
     * @auth sunpeikai
     * @param
     * @return
     */
    private boolean updateNetDisk(String title,String moe,String url,Integer page,String pass,Integer success){
        NetDisk netDisk = new NetDisk();
        netDisk.setTitle(title);
        netDisk.setMoeUrl(moe);
        netDisk.setNetDisk(url);
        netDisk.setPage(page);
        netDisk.setPass(pass);
        // 解压密码后续手动操作
        // 待确认资源
        netDisk.setSuccess(success);
        netDisk.setCreateTime(new Date());
        return netDiskMapper.insertSelective(netDisk)>0;
    }

    /**
     * 判断是否爬取该url
     * @auth sunpeikai
     * @param
     * @return
     */
    private boolean canCrawlUrl(String url){
        if(CollectionUtils.isEmpty(CommonConstant.notContainsList)){
            logger.info("-- 正在从数据库中加载不爬取列表 --");
            CommonConstant.notContainsList = getNotContainsUrl();
        }
        for(NotContains notContains : CommonConstant.notContainsList){
            if(url.contains(notContains.getNotContains())){
                return false;
            }
        }
        return true;
    }

    private boolean contains(String str,String... compare){
        for(String source:compare){
            if(str.contains(source)){
                return true;
            }
        }
        return false;
    }
}
