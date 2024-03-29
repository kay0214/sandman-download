package com.sandman.emmmoe.dao.mysql.emmmoe.model.auto;

import java.io.Serializable;
import java.util.Date;

public class NetDisk implements Serializable {
    private Integer id;

    private String title;

    private String moeUrl;

    private String netDisk;

    private Integer page;

    private String pass;

    private String unzipPass;

    private Integer success;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getMoeUrl() {
        return moeUrl;
    }

    public void setMoeUrl(String moeUrl) {
        this.moeUrl = moeUrl == null ? null : moeUrl.trim();
    }

    public String getNetDisk() {
        return netDisk;
    }

    public void setNetDisk(String netDisk) {
        this.netDisk = netDisk == null ? null : netDisk.trim();
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass == null ? null : pass.trim();
    }

    public String getUnzipPass() {
        return unzipPass;
    }

    public void setUnzipPass(String unzipPass) {
        this.unzipPass = unzipPass == null ? null : unzipPass.trim();
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}