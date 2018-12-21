package com.sandman.emmmoe.dao.mysql.emmmoe.model.auto;

import java.io.Serializable;
import java.util.Date;

public class NotContains implements Serializable {
    private Integer id;

    private String title;

    private String notContains;

    /**
     * 是否启用(0:未启用;1:启用)
     *
     * @mbggenerated
     */
    private Integer enable;

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

    public String getNotContains() {
        return notContains;
    }

    public void setNotContains(String notContains) {
        this.notContains = notContains == null ? null : notContains.trim();
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}