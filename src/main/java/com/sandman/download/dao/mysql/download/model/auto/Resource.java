package com.sandman.download.dao.mysql.download.model.auto;

import java.io.Serializable;
import java.util.Date;

public class Resource implements Serializable {
    private Integer id;

    /**
     * 用户id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * 用户名
     *
     * @mbggenerated
     */
    private String username;

    /**
     * 昵称
     *
     * @mbggenerated
     */
    private String nickname;

    /**
     * 资源名称
     *
     * @mbggenerated
     */
    private String resourceName;

    /**
     * 资源URL
     *
     * @mbggenerated
     */
    private String resourceUrl;

    /**
     * 资源所需积分
     *
     * @mbggenerated
     */
    private String resourceGold;

    /**
     * 资源大小
     *
     * @mbggenerated
     */
    private Double resourceSize;

    /**
     * 资源类型
     *
     * @mbggenerated
     */
    private String resourceType;

    /**
     * 资源描述
     *
     * @mbggenerated
     */
    private String resourceDesc;

    /**
     * 下载次数
     *
     * @mbggenerated
     */
    private Integer downloadCount;

    /**
     * 资源状态(0:待审核,1:审核通过,2:审核失败)
     *
     * @mbggenerated
     */
    private Integer status;

    private String statusDesc;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
    }

    public String getResourceGold() {
        return resourceGold;
    }

    public void setResourceGold(String resourceGold) {
        this.resourceGold = resourceGold == null ? null : resourceGold.trim();
    }

    public Double getResourceSize() {
        return resourceSize;
    }

    public void setResourceSize(Double resourceSize) {
        this.resourceSize = resourceSize;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc == null ? null : resourceDesc.trim();
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc == null ? null : statusDesc.trim();
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