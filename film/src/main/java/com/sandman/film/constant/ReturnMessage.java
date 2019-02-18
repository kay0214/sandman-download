/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.film.constant;

/**
 * @author sunpeikai
 * @version ReturnMessage, v0.1 2018/12/3 11:29
 */
public enum ReturnMessage {

    // 正确信息
    SUCCESS_USER_USERNAME_AVAILABLE("000","可使用的用户名"),
    SUCCESS_USER_LOGIN("000","登录成功"),
    SUCCESS_USER_SIGN_IN("000","签到成功"),
    SUCCESS_PASSWORD_MODIFY("000","密码修改成功"),
    SUCCESS_EMAIL_SEND("000","邮件发送成功"),
    SUCCESS_USER_UPDATE("000","用户信息更新成功"),
    SUCCESS_DOWNLOAD("000","下载成功"),

    //通用错误信息
    // 通用错误信息
    ERR_OBJECT_REQUIRED("ETY000001","{0}不能为空"),
    ERR_OBJECT_BLANK("ETY000002","{0}未填写"),//**未填写错误
    ERR_OBJECT_DIGIT("ETY000003","{0}不能存在非数字"),
    ERR_OBJECT_DATE("ETY000004","{0}非日期格式"),
    ERR_OBJECT_MAIL("ETY000005","{0}非法邮件地址"),
    ERR_OBJECT_VALUE("ETY000006","{0}非法"),
    ERR_OBJECT_GET("ETY000007","获取{0}失败"),//提取通用
    ERR_OBJECT_INVALID("ETY000008","无效的{0}"),//提取通用
    ERR_OBJECT_UNMATCH("ETY000009","{0}不符合接口要求，请重新传入"),
    ERR_OBJECT_EXCEED_LIMIT("ETY000010","{0}超出限制长度"),
    ERR_OBJECT_DECRYPT("ETY000011","{0}解密失败"),
    ERR_OBJECT_EXISTS("ETY000012","{0}已存在"),

    // 用户相关错误ERR_USER_
    ERR_USER_USERNAME_EXIST("EUS000001","用户名已存在"),
    ERR_USER_REGISTER("EUS000002","用户注册失败"),
    ERR_USER_NOT_EXIST("EUS000003","用户不存在或已被禁用"),
    ERR_USER_LOGIN_INVALID("EUS000004","请先登录"),
    ERR_USER_GOLD_NOT_ENOUGH("EUS000005","积分不足"),
    ERR_USER_NOT_VIP("EUS000006","抱歉，您不是VIP用户"),
    ERR_USER_UPDATE("EUS000007","用户信息更新失败"),
    ERR_USER_IS_NOT_ADMIN("EUS000008","该用户不是管理员"),
    ERR_USER_RECHARGE("EUS000009","用户充值失败"),
    ERR_USER_SIGN_IN("EUS000010","用户签到失败"),
    ERR_USER_BUY_FILM("EUS000011","用户兑换失败"),
    ERR_USER_ALREADY_BUY("EUS000012","您已兑换该影片"),

    // 资源相关错误ERR_RESOURCE_
    ERR_RESOURCE_NOT_EXIST("ERS000001","资源不存在"),
    ERR_RESOURCE_DOWNLOAD_CHECK("ERS000002","未通过资格检查"),

    // 下载相关错误ERR_DOWNLOAD_
    ERR_DOWNLOAD("EDL000001","下载出错"),

    // 上传相关错误ERR_UPLOAD_
    ERR_UPLOAD_EXCEED_MAX_SIZE("EUP000001","您是{0},只能上传小于{1}的文件"),
    ERR_UPLOAD_FAILED("EUP000002","上传失败"),

    // 邮件相关错误信息ERR_EMAIL_
    ERR_EMAIL_USED("EEM000001", "邮箱已被占用"),
    ERR_EMAIL_FMT("EEM000002", "邮箱格式不正确"),
    ERR_EMAIL_SEND("EEM000003", "邮件发送失败"),

    // 密码相关错误ERR_PASSWORD_
    ERR_PASSWORD("EPS000001","密码不正确"),
    ERR_PASSWORD_MODIFY("EPS000002","密码修改失败"),

    // 回复相关错误ERR_REPAY_
    ERR_REPAY("ERP000001","回复失败"),

    // 激活码相关错误ERR_VALIDATE_CODE_
    ERR_VALIDATE_CODE("EVC000001","激活码不正确"),
    ERR_VALIDATE_CODE_NOT_EXIST("EVC000002","激活码不存在"),
    ERR_VALIDATE_CODE_EXPIRED("EVC000003","激活码已过期"),
    ERR_VALIDATE_CODE_NOT_PASS("EVC000004","验证未通过"),

    // 重启应用错误信息
    ERR_APPLICATION_RESTART("EAP000001","应用重启失败"),

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
