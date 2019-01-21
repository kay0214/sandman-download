package com.sandman.film.dao.mysql.film.model.auto;

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
     * 电影id
     *
     * @mbggenerated
     */
    private Integer filmId;

    /**
     * 电影名称
     *
     * @mbggenerated
     */
    private String filmName;

    /**
     * 原有积分
     *
     * @mbggenerated
     */
    private Integer originalGold;

    /**
     * 电影积分
     *
     * @mbggenerated
     */
    private Integer filmGold;

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

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName == null ? null : filmName.trim();
    }

    public Integer getOriginalGold() {
        return originalGold;
    }

    public void setOriginalGold(Integer originalGold) {
        this.originalGold = originalGold;
    }

    public Integer getFilmGold() {
        return filmGold;
    }

    public void setFilmGold(Integer filmGold) {
        this.filmGold = filmGold;
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