/*
Navicat MySQL Data Transfer

Source Server         : sandman-local
Source Server Version : 50149
Source Host           : localhost:3306
Source Database       : sandman_sys

Target Server Type    : MYSQL
Target Server Version : 50149
File Encoding         : 65001

Date: 2018-11-23 18:09:27
*/

SET FOREIGN_KEY_CHECKS=0;

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
  `icon_url` varchar(255) NOT NULL COMMENT '头像地址',
  `reg_ip` varchar(255) NOT NULL COMMENT '注册ip',
  `reg_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未被删除,1:已被删除)',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_user_login_log`;
CREATE TABLE `t_user_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `login_times` int(11) NOT NULL DEFAULT '0' COMMENT '登录次数',
  `login_ip` varchar(255) NOT NULL COMMENT '最后一次登录ip',
  `login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次登录时间',
  `last_ip` varchar(255) NOT NULL COMMENT '上次登录ip',
  `last_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上次登录时间',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_validate_code
-- ----------------------------
DROP TABLE IF EXISTS `t_validate_code`;
CREATE TABLE `t_validate_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contact` varchar(255) NOT NULL COMMENT '联系方式',
  `code` varchar(255) NOT NULL COMMENT '验证码',
  `dead_line` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '截止时间',
  `valid` int(11) NOT NULL DEFAULT '1' COMMENT '可用(0:此验证码无效,1:此验证码有效)',
  `send` int(11) NOT NULL DEFAULT '0' COMMENT '发送(0:未发送,1:已发送)',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未被删除,1:已被删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_validate_code
-- ----------------------------
