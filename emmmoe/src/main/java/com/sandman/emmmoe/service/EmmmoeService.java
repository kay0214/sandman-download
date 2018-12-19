/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.emmmoe.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.sandman.emmmoe.base.BaseServiceImpl;
import com.sandman.emmmoe.config.SystemConfig;
import com.sandman.emmmoe.dao.mysql.emmmoe.model.auto.PageInfo;
import com.sandman.emmmoe.utils.HttpUnitUtils;
import org.springframework.stereotype.Service;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.Date;

/**
 * @author sunpeikai
 * @version EmmmoeService, v0.1 2018/12/19 16:45
 */
@Service
public class EmmmoeService extends BaseServiceImpl {

    private String emmmoePage = "";

    public int getEmmmoePageList(String emmmoe){
        emmmoePage = emmmoe + SystemConfig.getEmmmoePrefix();
        int result = 0;
        int page = 1;
        int pageCount = 0;
        int insertCount = 0;
        boolean scan = true;
        while (scan){
            String emmmoePageList = emmmoePage.replace("{page}",String.valueOf(page));
            logger.info("当前页面:[{}]",emmmoePageList);
            WebClient webClient = HttpUnitUtils.getWebClient();
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
                            pageInfo.setUrl(a.getAttribute("href"));
                            pageInfo.setCreateTime(new Date());
                            insertCount += pageInfoMapper.insertSelective(pageInfo);
                            //System.out.println("href:" + a.getAttribute("href") + ";;;text:" + a.asText());
                        }
                    }
                }
            }catch (Exception e){
                scan = false;
            }
            result += pageCount;
            logger.info("第[{}]页一共找到[{}]个链接",page,pageCount);
            pageCount = 0;
            page ++;
        }
        logger.info("总共包含记录:[{}],收录记录:[{}]",result,insertCount);
        return result;
    }
}
