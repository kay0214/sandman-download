/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.constant;

/**
 * @author sunpeikai
 * @version ReturnMessage, v0.1 2018/12/3 11:29
 */
public enum ReturnMessage {

    // 正确信息
    SUCCESS_USER_USERNAME_AVAILABLE("000","可使用的用户名"),
    SUCCESS_USER_LOGIN("000","登录成功"),

    SUCCESS_EMAIL_SEND("000","邮件发送成功"),

    //通用错误信息
    // 通用错误信息
    ERR_OBJECT_REQUIRED("ETY000001","{0}不能为空"),
    ERR_OBJECT_BLANK("ETY000002","{0}未填写"),//**未填写错误
    ERR_OBJECT_DIGIT("ETY000003","{0}不能存在非数字"),
    ERR_OBJECT_DATE("ETY000004","{0}非日期格式"),
    ERR_OBJECT_MAIL("ETY000005","{0}非法邮件地址"),
    ERR_OBJECT_VALUE("ETY000006","传入参数{0}的值非法"),
    ERR_OBJECT_GET("ETY000007","获取{0}失败"),//提取通用
    ERR_OBJECT_INVALID("ETY000008","无效的{0}"),//提取通用
    ERR_OBJECT_UNMATCH("ETY000009","{0}不符合接口要求，请重新传入"),
    ERR_OBJECT_EXCEED_LIMIT("ETY000010","{0}超出限制长度"),
    ERR_OBJECT_DECRYPT("ETY000011","{0}解密失败"),
    ERR_OBJECT_EXISTS("ETY000012","{0}已存在"),

    // 用户相关错误ERR_USER_
    ERR_USER_USERNAME_EXIST("EUS000001","用户名已存在"),
    ERR_USER_REGISTER("EUS000002","用户注册失败"),
    ERR_USER_NOT_EXIST("EUS000003","用户不存在"),
    ERR_USER_LOGIN_INVALID("EUS000004","登录失效，请重新登录"),

    // 邮件相关错误信息ERR_EMAIL_
    ERR_EMAIL_USED("EEM000001", "邮箱已被占用"),
    ERR_EMAIL_FMT("EEM000002", "邮箱格式不正确"),
    ERR_EMAIL_SEND("EEM000003", "邮件发送失败"),

    // 密码相关错误ERR_PASSWORD_
    ERR_PASSWORD("EPS000001","密码不正确"),

    // 枚举终结
    ERR_ENUM("", "");

    private String message;
    private String code;

    private ReturnMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
