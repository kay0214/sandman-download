package com.sandman.film.dao.mysql.system.model.auto;

import java.io.Serializable;
import java.util.Date;

public class Template implements Serializable {
    private Integer id;

    /**
     * 模板代码
     *
     * @mbggenerated
     */
    private String tplCode;

    /**
     * 模板名称
     *
     * @mbggenerated
     */
    private String tplName;

    /**
     * 状态(0:未启用;1:启用)
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * 模板内容
     *
     * @mbggenerated
     */
    private String tplContent;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTplCode() {
        return tplCode;
    }

    public void setTplCode(String tplCode) {
        this.tplCode = tplCode == null ? null : tplCode.trim();
    }

    public String getTplName() {
        return tplName;
    }

    public void setTplName(String tplName) {
        this.tplName = tplName == null ? null : tplName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTplContent() {
        return tplContent;
    }

    public void setTplContent(String tplContent) {
        this.tplContent = tplContent == null ? null : tplContent.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}