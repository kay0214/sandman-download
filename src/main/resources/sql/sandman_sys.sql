/*
Navicat MySQL Data Transfer

Source Server         : sunpeikai-far
Source Server Version : 50640
Source Host           : 117.48.197.114:3306
Source Database       : sandman_sys

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-01-17 17:26:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_carousel
-- ----------------------------
DROP TABLE IF EXISTS `t_carousel`;
CREATE TABLE `t_carousel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL COMMENT '标题',
  `url` varchar(255) NOT NULL COMMENT '链接url',
  `img_url` varchar(255) NOT NULL COMMENT '图片url',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记(0:未被删除,1:已被删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_carousel
-- ----------------------------

-- ----------------------------
-- Table structure for t_friendly_link
-- ----------------------------
DROP TABLE IF EXISTS `t_friendly_link`;
CREATE TABLE `t_friendly_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link_name` varchar(255) NOT NULL COMMENT '链接名',
  `link_url` varchar(255) NOT NULL COMMENT 'url',
  `status` int(11) DEFAULT NULL COMMENT '状态(0:禁用;1:启用)',
  `order_no` int(11) NOT NULL COMMENT '链接排序，决定链接顺序',
  `create_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未被删除,1:已被删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_friendly_link
-- ----------------------------
INSERT INTO `t_friendly_link` VALUES ('1', '恶魔喵爬取结果,用户名sunpeikai,密码spkzq521', 'http://117.48.197.114:8090', '1', '1', '2019-01-09 15:08:51', '2019-01-15 09:24:37', '0');
INSERT INTO `t_friendly_link` VALUES ('2', '百度', 'https://www.baidu.com', '1', '4', '2019-01-11 20:42:02', '2019-01-11 20:42:14', '1');

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL COMMENT '公告标题',
  `content` varchar(255) NOT NULL COMMENT '公告内容',
  `status` int(11) NOT NULL COMMENT '启用状态(0:禁用;1:启用)',
  `order_no` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记(0:未被删除,1:已被删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES ('1', '测试1', '网站功能基本算是完成了，如果您发现本站有任何需要改进的地方，请不吝指教', '1', '1', '2019-01-09 14:16:34', '2019-01-11 14:28:33', '0');
INSERT INTO `t_notice` VALUES ('2', '测试2', '网站比较简易，完全个人开发，如果以后有机会就会做三端适配。', '1', '2', '2019-01-09 14:34:09', '2019-01-13 19:35:04', '0');
INSERT INTO `t_notice` VALUES ('3', '测试3', '免责声明:本站所有资源都是由网友分享，仅供学习和交流使用，如果侵权了您的合法权益，请发邮件至sunpeikai@yeah.net我们立即处理。', '1', '3', '2019-01-10 10:33:58', '2019-01-10 10:34:00', '0');
INSERT INTO `t_notice` VALUES ('4', '测试标题123', '测试内容123', '1', '4', '2019-01-11 20:33:11', '2019-01-11 20:33:23', '1');

-- ----------------------------
-- Table structure for t_secure_config
-- ----------------------------
DROP TABLE IF EXISTS `t_secure_config`;
CREATE TABLE `t_secure_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `api_url` varchar(255) NOT NULL COMMENT '接口地址url',
  `api_name` varchar(255) NOT NULL COMMENT '接口名称',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '开启状态(0:未启用;1:启用)',
  `secure_visit_flag` int(11) NOT NULL DEFAULT '0' COMMENT '安全访问控制标识，  0-无需登陆访问 1-需要登陆访问',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未被删除,1:已被删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_secure_config
-- ----------------------------
INSERT INTO `t_secure_config` VALUES ('1', '/download/download', '下载资源', '1', '1', '2019-01-09 15:00:07', '2019-01-09 15:00:10', '0');
INSERT INTO `t_secure_config` VALUES ('2', '/download/check_info', '下载前检查下载资格', '1', '0', '2019-01-14 06:21:28', '2019-01-09 15:05:14', '0');
INSERT INTO `t_secure_config` VALUES ('3', '/forget/init', '进入忘记密码页面', '1', '0', '2019-01-09 16:43:07', '2019-01-09 16:43:05', '0');
INSERT INTO `t_secure_config` VALUES ('4', '/forget/send_email', '忘记密码发送邮件验证码', '1', '0', '2019-01-09 16:43:50', '2019-01-09 16:43:48', '0');
INSERT INTO `t_secure_config` VALUES ('5', '/forget/right_code', '校验邮箱验证码正确性', '1', '0', '2019-01-09 16:44:21', '2019-01-09 16:44:19', '0');
INSERT INTO `t_secure_config` VALUES ('6', '/forget/modify', '修改登录密码', '1', '0', '2019-01-09 16:44:52', '2019-01-09 16:44:50', '0');
INSERT INTO `t_secure_config` VALUES ('7', '/friendly/init', '获取友情链接列表', '1', '0', '2019-01-09 16:45:28', '2019-01-09 16:45:26', '0');
INSERT INTO `t_secure_config` VALUES ('8', '/my_gold/init', '进入我的积分明细页面', '1', '1', '2019-01-09 16:46:14', '2019-01-09 16:46:12', '0');
INSERT INTO `t_secure_config` VALUES ('9', '/my_gold/search', '查询我的积分明细', '1', '1', '2019-01-09 16:46:35', '2019-01-09 16:46:33', '0');
INSERT INTO `t_secure_config` VALUES ('10', '/', '首页', '1', '0', '2019-01-09 16:47:10', '2019-01-09 16:47:09', '0');
INSERT INTO `t_secure_config` VALUES ('11', '/login/init', '进入登录页面', '1', '0', '2019-01-09 16:48:13', '2019-01-09 16:47:51', '0');
INSERT INTO `t_secure_config` VALUES ('12', '/login/login', '用户登录', '1', '0', '2019-01-09 16:48:44', '2019-01-09 16:48:15', '0');
INSERT INTO `t_secure_config` VALUES ('13', '/login/logOut', '用户退出登录', '1', '0', '2019-01-09 16:48:47', '2019-01-09 16:48:45', '0');
INSERT INTO `t_secure_config` VALUES ('14', '/my_download/init', '进入下载记录页面', '1', '1', '2019-01-09 16:49:31', '2019-01-09 16:49:29', '0');
INSERT INTO `t_secure_config` VALUES ('15', '/my_download/redownload', '重新下载', '1', '1', '2019-01-09 16:50:10', '2019-01-09 16:50:08', '0');
INSERT INTO `t_secure_config` VALUES ('16', '/my_download/check_redownload', '重新下载前检查资格', '1', '1', '2019-01-09 16:50:51', '2019-01-09 16:50:49', '0');
INSERT INTO `t_secure_config` VALUES ('17', '/my_resource/search', '进入我的资源', '1', '1', '2019-01-09 16:51:54', '2019-01-09 16:51:36', '0');
INSERT INTO `t_secure_config` VALUES ('18', '/my_resource/deleteById', '根据id删除我的资源', '1', '1', '2019-01-09 16:52:44', '2019-01-09 16:52:42', '0');
INSERT INTO `t_secure_config` VALUES ('19', '/my_resource/editById', '根据id编辑我的资源', '1', '1', '2019-01-09 16:53:13', '2019-01-09 16:53:11', '0');
INSERT INTO `t_secure_config` VALUES ('20', '/my_resource/submit_edit', '我的资源提交编辑', '1', '1', '2019-01-09 16:53:42', '2019-01-09 16:53:40', '0');
INSERT INTO `t_secure_config` VALUES ('21', '/notice/init', '获取公告栏列表', '1', '0', '2019-01-09 16:54:13', '2019-01-09 16:54:10', '0');
INSERT INTO `t_secure_config` VALUES ('22', '/personal/init', '进入个人中心页面', '1', '1', '2019-01-09 16:54:58', '2019-01-09 16:54:56', '0');
INSERT INTO `t_secure_config` VALUES ('23', '/personal/refresh', '刷新个人信息', '1', '1', '2019-01-09 16:55:45', '2019-01-09 16:55:43', '0');
INSERT INTO `t_secure_config` VALUES ('24', '/personal/upload_icon', '用户上传头像', '1', '1', '2019-01-09 16:56:09', '2019-01-09 16:56:07', '0');
INSERT INTO `t_secure_config` VALUES ('25', '/register/init', '进入注册页面', '1', '0', '2019-01-09 16:57:08', '2019-01-09 16:57:06', '0');
INSERT INTO `t_secure_config` VALUES ('26', '/register/username_valid', '检查用户名是否已被占用', '1', '0', '2019-01-09 16:57:40', '2019-01-09 16:57:38', '0');
INSERT INTO `t_secure_config` VALUES ('27', '/register/email_valid', '查询email是否已被占用', '1', '0', '2019-01-09 16:58:01', '2019-01-09 16:58:00', '0');
INSERT INTO `t_secure_config` VALUES ('28', '/register/register', '注册', '1', '0', '2019-01-09 16:58:20', '2019-01-09 16:58:19', '0');
INSERT INTO `t_secure_config` VALUES ('29', '/register/active_account', '激活账号', '1', '0', '2019-01-09 16:58:51', '2019-01-09 16:58:49', '0');
INSERT INTO `t_secure_config` VALUES ('30', '/resource/getResource', '获取资源信息列表', '0', '0', '2019-01-09 17:00:18', '2019-01-09 16:59:26', '0');
INSERT INTO `t_secure_config` VALUES ('31', '/resource/list', '分页获取资源信息列表', '1', '0', '2019-01-09 16:59:58', '2019-01-09 16:59:56', '0');
INSERT INTO `t_secure_config` VALUES ('32', '/resource/search', '分页搜索资源信息列表', '1', '0', '2019-01-09 17:00:55', '2019-01-09 17:00:53', '0');
INSERT INTO `t_secure_config` VALUES ('33', '/resource/get_info', '资源详细信息', '1', '0', '2019-01-09 17:01:19', '2019-01-09 17:01:17', '0');
INSERT INTO `t_secure_config` VALUES ('34', '/resource/hot_resource', '查询热门资源列表', '1', '0', '2019-01-09 17:01:54', '2019-01-09 17:01:52', '0');
INSERT INTO `t_secure_config` VALUES ('35', '/upload/init', '进入上传资源页面', '1', '1', '2019-01-09 17:02:32', '2019-01-09 17:02:30', '0');
INSERT INTO `t_secure_config` VALUES ('36', '/upload/uploadFile', '上传资源-上传文件', '1', '1', '2019-01-09 17:02:57', '2019-01-09 17:02:55', '0');
INSERT INTO `t_secure_config` VALUES ('37', '/upload/upload_resource', '上传资源-保存数据库', '1', '1', '2019-01-11 16:37:54', '2019-01-11 16:37:54', '0');
INSERT INTO `t_secure_config` VALUES ('38', '/haha/haha', '测试接口', '0', '0', '2019-01-11 20:55:20', '2019-01-11 20:55:27', '1');

-- ----------------------------
-- Table structure for t_sign_in
-- ----------------------------
DROP TABLE IF EXISTS `t_sign_in`;
CREATE TABLE `t_sign_in` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `date_str` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sign_in
-- ----------------------------

-- ----------------------------
-- Table structure for t_template
-- ----------------------------
DROP TABLE IF EXISTS `t_template`;
CREATE TABLE `t_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tpl_code` varchar(255) NOT NULL COMMENT '模板代码',
  `tpl_name` varchar(255) NOT NULL COMMENT '模板名称',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态(0:未启用;1:启用)',
  `tpl_content` varchar(10000) NOT NULL COMMENT '模板内容',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_template
-- ----------------------------
INSERT INTO `t_template` VALUES ('1', 'TPL_EMAIL_ACTIVE', '邮箱-激活', '1', '<head>\r\n    <meta charset=\"utf-8\" />\r\n</head>\r\n<body tabindex=\"0\" role=\"listitem\">\r\n<style media=\"all\" type=\"text/css\">\r\n    td, p, h1, h3, a {font-family: Helvetica, Arial, sans-serif;}\r\n</style>\r\n<table style=\"width: 538px; background-color: #2795e9;\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\">\r\n    <tbody>\r\n    <tr>\r\n        <td style=\" height: 65px; background-color: #000000; border-bottom: 1px solid #4d4b48;\">\r\n            <img src=\"${sandman_logo}$\" width=\"538\" height=\"65\" alt=\"Steam\">\r\n        </td>\r\n    </tr>\r\n    <tr>\r\n        <td bgcolor=\"#17212e\">\r\n            <table width=\"470\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"padding-left: 5px; padding-right: 5px; padding-bottom: 10px;\">\r\n                <tbody>\r\n                <tr bgcolor=\"#17212e\">\r\n                    <td style=\"padding-top: 32px;\">\r\n				<span style=\"padding-top: 16px; padding-bottom: 16px; font-size: 24px; color: #66c0f4; font-family: Arial, Helvetica, sans-serif; font-weight: bold;\">\r\n						尊敬的${recipient}$,您好！\r\n				</span><br>\r\n                    </td>\r\n                </tr>\r\n                <tr>\r\n                    <td style=\"padding-top: 12px;\">\r\n				<span style=\"font-size: 17px; color: #c6d4df; font-family: Arial, Helvetica, sans-serif; font-weight: bold;\">\r\n				<p style=\"text-indent: 2em\">\r\n                    欢迎您注册Sandman资源共享平台,请点击下面的按钮以激活您的账号：\r\n                </p>\r\n				</span>\r\n                    </td>\r\n                </tr>\r\n                <tr>\r\n                    <td>\r\n                        <div>\r\n                            <span style=\"font-size: 24px; color: #66c0f4; font-family: Arial, Helvetica, sans-serif; font-weight: bold;text-align: center\"><a target=\"_blank\" href=\"${activeUrl}$\">${activeUrl}$</a></span>\r\n                        </div>\r\n                    </td>\r\n                </tr>\r\n                <tr bgcolor=\"#121a25\">\r\n                    <td style=\"padding: 20px; font-size: 12px; line-height: 17px; color: #c6d4df; font-family: Arial, Helvetica, sans-serif;\">\r\n                        <p style=\"padding-bottom: 10px; color: #c6d4df;text-indent: 2em;\">\r\n                            该链接有效期为48小时且只能使用一次，请您尽快激活。如果以上链接无法点击，请把链接地址复制到浏览器地址栏中打开\r\n                        </p>\r\n                        <p style=\"padding-bottom: 10px; color: #c6d4df;text-indent: 2em;\">\r\n                            如果您有任何疑问，请您随时拨打我们的客服电话:${sandman_mobile}$。\r\n                        </p>\r\n                    </td>\r\n                </tr>\r\n                <tr>\r\n                    <td style=\"font-size: 12px; color: #6d7880; padding-top: 16px; padding-bottom: 60px;float: right;\">\r\n                        Sandman 团队\r\n                        <br>\r\n                        <a style=\"color: #8f98a0;\" href=\"${sandman_home}$\">${sandman_home}$</a><br>\r\n                    </td>\r\n                </tr>\r\n                </tbody>\r\n            </table>\r\n        </td>\r\n    </tr>\r\n    <tr>\r\n        <td bgcolor=\"#000000\">\r\n            <table width=\"460\" height=\"55\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n                <tbody>\r\n                <tr>\r\n                    <td width=\"350\" style=\"margin-top: 10em\" align=\"center\">\r\n                        <a href=\"${sandman_home}$\" target=\"_blank\" style=\"text-decoration: none\">\r\n                            <span style=\"color: #999999; font-size: 9px; font-family: Verdana, Arial, Helvetica, sans-serif;\">Copyright © 2017-2019 Sandman资源共享平台 版权所有</span>\r\n                        </a>\r\n                    </td>\r\n                </tr>\r\n                </tbody>\r\n            </table>\r\n        </td>\r\n    </tr>\r\n    </tbody>\r\n</table>\r\n<style type=\"text/css\">\r\n    body{font-size:14px;font-family:arial,verdana,sans-serif;line-height:1.666;padding:0;margin:0;overflow:auto;white-space:normal;word-wrap:break-word;min-height:100px}\r\n    td, input, button, select, body{font-family:Helvetica, \'Microsoft Yahei\', verdana}\r\n    pre {white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;width:95%}\r\n    th,td{font-family:arial,verdana,sans-serif;line-height:1.666}\r\n    img{ border:0}\r\n    header,footer,section,aside,article,nav,hgroup,figure,figcaption{display:block}\r\n    blockquote{margin-right:0px}\r\n</style>\r\n<style id=\"ntes_link_color\" type=\"text/css\">\r\n    a,td a{color:#064977}\r\n</style>\r\n</body>', '2018-12-04 12:41:57', '2019-01-11 15:42:03');
INSERT INTO `t_template` VALUES ('3', 'TPL_EMAIL_FORGET_PASSWORD', '邮箱-找回密码', '1', '<head>\r\n    <meta charset=\"utf-8\" />\r\n</head>\r\n<body tabindex=\"0\" role=\"listitem\">\r\n<style media=\"all\" type=\"text/css\">\r\n    td, p, h1, h3, a {font-family: Helvetica, Arial, sans-serif;}\r\n</style>\r\n<table style=\"width: 538px; background-color: #2795e9;\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\">\r\n    <tbody>\r\n    <tr>\r\n        <td style=\" height: 65px; background-color: #000000; border-bottom: 1px solid #4d4b48;\">\r\n            <img src=\"${sandman_logo}$\" width=\"538\" height=\"65\" alt=\"Steam\">\r\n        </td>\r\n    </tr>\r\n    <tr>\r\n        <td bgcolor=\"#17212e\">\r\n            <table width=\"470\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"padding-left: 5px; padding-right: 5px; padding-bottom: 10px;\">\r\n                <tbody>\r\n                <tr bgcolor=\"#17212e\">\r\n                    <td style=\"padding-top: 32px;\">\r\n				<span style=\"padding-top: 16px; padding-bottom: 16px; font-size: 24px; color: #66c0f4; font-family: Arial, Helvetica, sans-serif; font-weight: bold;\">\r\n						尊敬的${recipient}$,您好！\r\n				</span><br>\r\n                    </td>\r\n                </tr>\r\n                <tr>\r\n                    <td style=\"padding-top: 12px;\">\r\n				<span style=\"font-size: 17px; color: #c6d4df; font-family: Arial, Helvetica, sans-serif; font-weight: bold;\">\r\n				<p style=\"text-indent: 2em\">\r\n                    您正在找回Sandman资源共享平台的登录密码,您本次的验证码为：\r\n                </p>\r\n				</span>\r\n                    </td>\r\n                </tr>\r\n                <tr>\r\n                    <td>\r\n                        <div>\r\n                            <span style=\"font-size: 24px; color: #66c0f4; font-family: Arial, Helvetica, sans-serif; font-weight: bold;text-align: center\">${code}$</span>\r\n                        </div>\r\n                    </td>\r\n                </tr>\r\n                <tr bgcolor=\"#121a25\">\r\n                    <td style=\"padding: 20px; font-size: 12px; line-height: 17px; color: #c6d4df; font-family: Arial, Helvetica, sans-serif;\">\r\n                        <p style=\"padding-bottom: 10px; color: #c6d4df;text-indent: 2em;\">\r\n                            该验证码有效期为5分钟。为保证您的账号安全，请勿将此验证码交于他人。\r\n                        </p>\r\n                        <p style=\"padding-bottom: 10px; color: #c6d4df;text-indent: 2em;\">\r\n                            如果您有任何疑问，请您随时拨打我们的客服电话:${sandman_mobile}$。\r\n                        </p>\r\n                    </td>\r\n                </tr>\r\n                <tr>\r\n                    <td style=\"font-size: 12px; color: #6d7880; padding-top: 16px; padding-bottom: 60px;float: right;\">\r\n                        Sandman 团队\r\n                        <br>\r\n                        <a style=\"color: #8f98a0;\" href=\"${sandman_home}$\">${sandman_home}$</a><br>\r\n                    </td>\r\n                </tr>\r\n                </tbody>\r\n            </table>\r\n        </td>\r\n    </tr>\r\n    <tr>\r\n        <td bgcolor=\"#000000\">\r\n            <table width=\"460\" height=\"55\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n                <tbody>\r\n                <tr>\r\n                    <td width=\"350\" style=\"margin-top: 10em\" align=\"center\">\r\n                        <a href=\"${sandman_home}$\" target=\"_blank\" style=\"text-decoration: none\">\r\n                            <span style=\"color: #999999; font-size: 9px; font-family: Verdana, Arial, Helvetica, sans-serif;\">Copyright © 2017-2019 Sandman资源共享平台 版权所有</span>\r\n                        </a>\r\n                    </td>\r\n                </tr>\r\n                </tbody>\r\n            </table>\r\n        </td>\r\n    </tr>\r\n    </tbody>\r\n</table>\r\n<style type=\"text/css\">\r\n    body{font-size:14px;font-family:arial,verdana,sans-serif;line-height:1.666;padding:0;margin:0;overflow:auto;white-space:normal;word-wrap:break-word;min-height:100px}\r\n    td, input, button, select, body{font-family:Helvetica, \'Microsoft Yahei\', verdana}\r\n    pre {white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;width:95%}\r\n    th,td{font-family:arial,verdana,sans-serif;line-height:1.666}\r\n    img{ border:0}\r\n    header,footer,section,aside,article,nav,hgroup,figure,figcaption{display:block}\r\n    blockquote{margin-right:0px}\r\n</style>\r\n<style id=\"ntes_link_color\" type=\"text/css\">\r\n    a,td a{color:#064977}\r\n</style>\r\n</body>', '2019-01-08 10:48:18', '2019-01-11 16:19:03');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `mobile` varchar(255) DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(255) NOT NULL COMMENT '密码(加密后的)',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `salt` varchar(255) NOT NULL DEFAULT 'sandman' COMMENT '加密盐(userName+随机uuid)',
  `gold` int(11) NOT NULL DEFAULT '0' COMMENT '积分',
  `available` int(11) NOT NULL DEFAULT '0' COMMENT '账户是否可用(0:不可用，1:可用)',
  `role` int(11) NOT NULL DEFAULT '1' COMMENT '角色(0:管理员,1:普通用户,2:vip用户)',
  `icon_url` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `reg_ip` varchar(255) NOT NULL COMMENT '注册ip',
  `reg_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '注册时间',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未被删除,1:已被删除)',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'sandman', null, '929525390@qq.com', 'b32ede501aff96c95c6716a06d60cb8e', null, 'sandman', '0', '1', '0', '/icon/default.jpg', '0.0.0.0', '2019-01-11 14:36:41', '2019-01-10 11:21:32', '2019-01-16 08:02:01', '0');
INSERT INTO `t_user` VALUES ('2', 'sunpeikai', null, 'sunpeikai@yeah.net', 'd3bb2fb7cd3e033ee0caa3d31ed2f25a', null, 'XPbiO4', '4', '1', '2', '/icon/default.jpg', '10.10.2.77', '2019-01-11 21:18:05', '2018-12-04 18:02:56', '2019-01-16 17:00:41', '0');

-- ----------------------------
-- Table structure for t_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_user_login_log`;
CREATE TABLE `t_user_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `login_times` int(11) NOT NULL DEFAULT '0' COMMENT '登录次数',
  `login_ip` varchar(255) NOT NULL COMMENT '最后一次登录ip',
  `login_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后一次登录时间',
  `last_ip` varchar(255) NOT NULL COMMENT '上次登录ip',
  `last_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上次登录时间',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user_login_log
-- ----------------------------
INSERT INTO `t_user_login_log` VALUES ('1', '1', '248', '219.147.28.242', '2019-01-16 14:31:55', '219.147.28.242', '2019-01-16 09:21:24', '2018-12-06 11:16:04', '2019-01-17 01:13:13');
INSERT INTO `t_user_login_log` VALUES ('2', '2', '19', '10.10.2.77', '2019-01-16 15:13:38', '10.10.2.77', '2019-01-16 15:09:31', '2019-01-10 18:02:33', '2019-01-17 01:13:17');

-- ----------------------------
-- Table structure for t_validate_code
-- ----------------------------
DROP TABLE IF EXISTS `t_validate_code`;
CREATE TABLE `t_validate_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contact` varchar(255) NOT NULL COMMENT '联系方式',
  `code` varchar(255) NOT NULL COMMENT '验证码',
  `dead_line` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '截止时间',
  `valid` int(11) NOT NULL DEFAULT '1' COMMENT '可用(0:此验证码无效,1:此验证码有效,2:已验证通过)',
  `send` int(11) NOT NULL DEFAULT '0' COMMENT '发送(0:未发送,1:已发送)',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未被删除,1:已被删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_validate_code
-- ----------------------------
