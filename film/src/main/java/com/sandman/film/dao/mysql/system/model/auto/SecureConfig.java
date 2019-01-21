package com.sandman.film.dao.mysql.system.model.auto;

import java.io.Serializable;
import java.util.Date;

public class SecureConfig implements Serializable {
    private Integer id;

    /**
     * 接口地址url
     *
     * @mbggenerated
     */
    private String apiUrl;

    /**
     * 接口名称
     *
     * @mbggenerated
     */
    private String apiName;

    /**
     * 开启状态(0:未启用;1:启用)
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * 安全访问控制标识，  0-无需登陆访问 1-需要登陆访问
     *
     * @mbggenerated
     */
    private Integer secureVisitFlag;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * 删除标记(0:未被删除,1:已被删除)
     *
     * @mbggenerated
     */
    private Integer delFlag;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl == null ? null : apiUrl.trim();
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName == null ? null : apiName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSecureVisitFlag() {
        return secureVisitFlag;
    }

    public void setSecureVisitFlag(Integer secureVisitFlag) {
        this.secureVisitFlag = secureVisitFlag;
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

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}