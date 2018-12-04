/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.sandman.download.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author sunpeikai
 * @version SystemConfig, v0.1 2018/11/23 11:07
 */
@Component
public class SystemConfig {
    // sftp参数
    private static String USERNAME;
    private static String PASSWORD;
    private static String HOST;
    private static int SSH_PORT;
    // 文件上传参数
    private static String PATH_PREFIX;
    private static String TEMP_FILE_PATH;

    // 域名
    private static String SERVER_HOST;


    // 邮件配置
    private static String SMTP_HOST;
    private static String SMTP_PORT;
    private static String SENDER_ACCOUNT;
    private static String SENDER_PASSWORD;
    private static String MY_NICK_NAME;
    //LOGO
    private static String SANDMAN_LOGO;
    //首页
    private static String SANDMAN_HOME;
    //客服电话
    private static String SANDMAN_MOBILE;


    private static String BLOGGER_DEFAULT_IMG;
    private static String TEMPLATES_PATH;

    @Value("${sftpServer.userName}")
    private void setUSERNAME(String USERNAME) {
        SystemConfig.USERNAME = USERNAME;
    }

    @Value("${sftpServer.password}")
    private void setPASSWORD(String PASSWORD) {
        SystemConfig.PASSWORD = PASSWORD;
    }

    @Value("${sftpServer.host}")
    private void setHOST(String HOST) {
        SystemConfig.HOST = HOST;
    }

    @Value("${sftpServer.sshPort}")
    private void setSshPort(int sshPort) {
        SSH_PORT = sshPort;
    }

    @Value("${sftpServer.prefix}")
    public void setPathPrefix(String pathPrefix) {
        PATH_PREFIX = pathPrefix;
    }

    @Value("${sftpServer.tempFilePath}")
    public void setTempFilePath(String tempFilePath) {
        TEMP_FILE_PATH = tempFilePath;
    }

    @Value("${sftpServer.bloggerDefaultImg}")
    public void setBloggerDefaultImg(String bloggerDefaultImg) {
        BLOGGER_DEFAULT_IMG = bloggerDefaultImg;
    }

    @Value("${sftpServer.templatesPath}")
    public void setTemplatesPath(String templatesPath) {
        TEMPLATES_PATH = templatesPath;
    }

    @Value("${sandman.server.host}")
    public void setServerHost(String serverHost) {
        SERVER_HOST = serverHost;
    }

    @Value("${mail.smtp.host}")
    public void setSmtpHost(String smtpHost) {
        SMTP_HOST = smtpHost;
    }

    @Value("${mail.smtp.port}")
    public void setSmtpPort(String smtpPort) {
        SMTP_PORT = smtpPort;
    }

    @Value("${mail.senderAccount}")
    public void setSenderAccount(String senderAccount) {
        SENDER_ACCOUNT = senderAccount;
    }

    @Value("${mail.password}")
    public void setSenderPassword(String senderPassword) {
        SENDER_PASSWORD = senderPassword;
    }

    @Value("${mail.myNickName}")
    public void setMyNickName(String myNickName) {
        MY_NICK_NAME = myNickName;
    }

    @Value("${sandman.logo}")
    public void setSandmanLogo(String sandmanLogo) {
        SANDMAN_LOGO = sandmanLogo;
    }

    @Value("${sandman.home}")
    public void setSandmanHome(String sandmanHome) {
        SANDMAN_HOME = sandmanHome;
    }

    @Value("${sandman.mobile}")
    public void setSandmanMobile(String sandmanMobile) {
        SANDMAN_MOBILE = sandmanMobile;
    }

    //get方法位置
    public static String getSmtpHost() {
        return SMTP_HOST;
    }

    public static String getSmtpPort() {
        return SMTP_PORT;
    }

    public static String getSenderAccount() {
        return SENDER_ACCOUNT;
    }

    public static String getSenderPassword() {
        return SENDER_PASSWORD;
    }

    public static String getMyNickName() {
        return MY_NICK_NAME;
    }

    public static String getServerHost() {
        return SERVER_HOST;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getHOST() {
        return HOST;
    }

    public static int getSshPort() {
        return SSH_PORT;
    }

    public static String getPathPrefix() {
        return PATH_PREFIX;
    }

    public static String getTempFilePath() {
        return TEMP_FILE_PATH;
    }
    public static String getLinePathPrefix(){
        return "http://" + getHOST();
    }

    public static String getBloggerDefaultImg() {
        return BLOGGER_DEFAULT_IMG;
    }

    public static String getTemplatesPath() {
        return TEMPLATES_PATH;
    }

    public static String getSandmanHome() {
        return SANDMAN_HOME;
    }

    public static String getSandmanMobile() {
        return SANDMAN_MOBILE;
    }

    public static String getSandmanLogo() {
        return SANDMAN_LOGO;
    }
}
