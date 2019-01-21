/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.bean.film;

import com.sandman.film.constant.CommonConstant;
import com.sandman.film.utils.BeanUtils;

import java.util.Date;

/**
 * @author sunpeikai
 * @version UserResultBean, v0.1 2019/1/11 9:24
 */
public class UserResultBean {
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
     * 手机号
     *
     * @mbggenerated
     */
    private String mobile;

    /**
     * 邮箱
     *
     * @mbggenerated
     */
    private String email;

    /**
     * 密码(加密后的)
     *
     * @mbggenerated
     */
    private String password;

    /**
     * 昵称
     *
     * @mbggenerated
     */
    private String nickname;

    /**
     * 加密盐(userName+随机uuid)
     *
     * @mbggenerated
     */
    private String salt;

    /**
     * 积分
     *
     * @mbggenerated
     */
    private Integer gold;

    /**
     * 账户是否可用(0:不可用，1:可用)
     *
     * @mbggenerated
     */
    private Integer available;

    /**
     * 角色(0:管理员,1:普通用户,2:vip用户)
     *
     * @mbggenerated
     */
    private Integer role;

    /**
     * 注册ip
     *
     * @mbggenerated
     */
    private String regIp;

    /**
     * 注册时间
     *
     * @mbggenerated
     */
    private Date regTime;

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
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        // 手机号脱敏
        this.mobile = BeanUtils.sensitiveReplace(mobile, CommonConstant.MOBILE_SENSITIVE_START,CommonConstant.MOBILE_SENSITIVE_END);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = BeanUtils.sensitiveReplace(email,1,email.indexOf("@"));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        // 密码脱敏
        this.password = BeanUtils.sensitiveReplace(password,0,password.length());
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
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
