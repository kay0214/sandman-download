package com.sandman.download.dao.mysql.system.model.auto;

import java.io.Serializable;
import java.util.Date;

public class ValidateCode implements Serializable {
    private Integer id;

    /**
     * 联系方式
     *
     * @mbggenerated
     */
    private String contact;

    /**
     * 验证码
     *
     * @mbggenerated
     */
    private String code;

    /**
     * 截止时间
     *
     * @mbggenerated
     */
    private Date deadLine;

    /**
     * 可用(0:此验证码无效,1:此验证码有效)
     *
     * @mbggenerated
     */
    private Integer valid;

    /**
     * 发送(0:未发送,1:已发送)
     *
     * @mbggenerated
     */
    private Integer send;

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Integer getSend() {
        return send;
    }

    public void setSend(Integer send) {
        this.send = send;
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