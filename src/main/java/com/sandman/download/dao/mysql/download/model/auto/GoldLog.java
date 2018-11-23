package com.sandman.download.dao.mysql.download.model.auto;

import java.io.Serializable;
import java.util.Date;

public class GoldLog implements Serializable {
    private Integer id;

    /**
     * 用户id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * 资源id
     *
     * @mbggenerated
     */
    private Integer resourceId;

    private String resourceName;

    /**
     * 原有积分
     *
     * @mbggenerated
     */
    private Integer originalGold;

    /**
     * 资源积分
     *
     * @mbggenerated
     */
    private Integer resourceGold;

    /**
     * 现有积分
     *
     * @mbggenerated
     */
    private Integer currentGold;

    /**
     * 操作描述
     *
     * @mbggenerated
     */
    private String operationDesc;

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

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public Integer getOriginalGold() {
        return originalGold;
    }

    public void setOriginalGold(Integer originalGold) {
        this.originalGold = originalGold;
    }

    public Integer getResourceGold() {
        return resourceGold;
    }

    public void setResourceGold(Integer resourceGold) {
        this.resourceGold = resourceGold;
    }

    public Integer getCurrentGold() {
        return currentGold;
    }

    public void setCurrentGold(Integer currentGold) {
        this.currentGold = currentGold;
    }

    public String getOperationDesc() {
        return operationDesc;
    }

    public void setOperationDesc(String operationDesc) {
        this.operationDesc = operationDesc == null ? null : operationDesc.trim();
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