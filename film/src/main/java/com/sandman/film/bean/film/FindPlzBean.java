/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.bean.film;

import com.sandman.film.base.BaseBean;

/**
 * @author sunpeikai
 * @version FindPlzBean, v0.1 2019/1/22 10:25
 */
public class FindPlzBean extends BaseBean {
    private String name;
    private String contact;
    private String content;
    private String qq;
    private String wechat;
    private Integer status;
    public FindPlzBean(){
        super();
    }
    public FindPlzBean(int currPage,int pageSize){
        super(currPage, pageSize);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
