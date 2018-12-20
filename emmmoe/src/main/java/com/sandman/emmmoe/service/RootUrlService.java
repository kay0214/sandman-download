/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sandman.emmmoe.base.BaseServiceImpl;
import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.RootUrl;
import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.RootUrlExample;
import com.sandman.emmmoe.utils.HttpUnitUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author sunpeikai
 * @version RootUrlService, v0.1 2018/12/20 14:50
 */
@Service
public class RootUrlService extends BaseServiceImpl {

    /**
     * 更新获取登船url的地址，一般不会改变
     * @auth sunpeikai
     * @param
     * @return
     */
    public int updateUrl(String url){
        RootUrl rootUrl = null;
        RootUrlExample rootUrlExample = new RootUrlExample();
        rootUrlExample.createCriteria().andRootEqualTo(0);
        List<RootUrl> rootUrls = rootUrlMapper.selectByExample(rootUrlExample);
        if(!CollectionUtils.isEmpty(rootUrls)){
            rootUrl = rootUrls.get(0);
        }
        if(rootUrl != null){
            if(url.equals(rootUrl.getUrl())){
                return 2;
            }else{
                rootUrl.setUrl(url);
                rootUrl.setCreateTime(new Date());
                return rootUrlMapper.updateByPrimaryKey(rootUrl);
            }
        }
        return 0;
    }

    /**
     * 更新最新的登船地址
     * @auth sunpeikai
     * @param
     * @return
     */
    public void getEnableRootUrl(){
        // 获取到登船地址的页面
        RootUrl rootUrl = null;
        RootUrlExample rootUrlExample = new RootUrlExample();
        rootUrlExample.createCriteria().andRootEqualTo(0);
        List<RootUrl> rootUrls = rootUrlMapper.selectByExample(rootUrlExample);
        if(!CollectionUtils.isEmpty(rootUrls)){
            rootUrl = rootUrls.get(0);
        }
        // 登船地址获取页面有效
        if(rootUrl != null){
            String url = getSecondRootUrl(rootUrl.getUrl());
            logger.info("url:[{}]",url);
            RootUrl newUrl = new RootUrl();
            newUrl.setTitle("恶魔喵最新登船地址");
            newUrl.setUrl(url);
            newUrl.setRoot(1);
            newUrl.setCreateTime(new Date());
            rootUrlMapper.insertSelective(newUrl);
        }

    }

    private String getSecondRootUrl(String rootUrl){
        logger.info("登船地址:[{}]",rootUrl);
        String result = "";
        WebClient webClient = HttpUnitUtils.getWebClient();
        try{
            HtmlPage htmlPage = webClient.getPage(rootUrl);
            DomNodeList<DomElement> domElements = htmlPage.getElementsByTagName("p");
            for(DomElement domElement:domElements){
                if(domElement.asText().contains("登船地址")){
                    DomNodeList<HtmlElement> aList = domElement.getElementsByTagName("a");
                    for(HtmlElement a:aList){
                        if(a.asText().contains("https://")){
                            result = a.getAttribute("href");
                        }
                    }
                }
            }
        }catch (Exception e){
            logger.info("登船地址获取失败!");
        }

        return result;
    }
}
