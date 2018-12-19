/*
Navicat MySQL Data Transfer

Source Server         : sandman-local
Source Server Version : 50149
Source Host           : localhost:3306
Source Database       : sandman_download

Target Server Type    : MYSQL
Target Server Version : 50149
File Encoding         : 65001

Date: 2018-11-23 18:09:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_gold_log
-- ----------------------------
DROP TABLE IF EXISTS `t_gold_log`;
CREATE TABLE `t_gold_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `resource_id` int(11) DEFAULT '0' COMMENT '资源id',
  `resource_name` varchar(255) DEFAULT '',
  `original_gold` int(11) DEFAULT NULL COMMENT '原有积分',
  `resource_gold` int(11) DEFAULT NULL COMMENT '资源积分',
  `current_gold` int(11) DEFAULT NULL COMMENT '现有积分',
  `operation_desc` varchar(255) NOT NULL COMMENT '操作描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `del_flag` int(11) NOT NULL COMMENT '删除标记(0:未被删除,1:已被删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_gold_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `resource_name` varchar(255) NOT NULL COMMENT '资源名称',
  `resource_url` varchar(255) NOT NULL COMMENT '资源URL',
  `resource_gold` varchar(255) NOT NULL DEFAULT '0' COMMENT '资源所需积分',
  `resource_size` double NOT NULL DEFAULT '0' COMMENT '资源大小',
  `resource_type` varchar(255) NOT NULL COMMENT '资源类型',
  `resource_desc` varchar(255) DEFAULT NULL COMMENT '资源描述',
  `download_count` int(11) NOT NULL DEFAULT '0' COMMENT '下载次数',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '资源状态(0:待审核,1:审核通过,2:审核失败)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未被删除,1:已被删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_resource
-- ----------------------------

-- ----------------------------
-- Table structure for t_resource_log
-- ----------------------------
DROP TABLE IF EXISTS `t_resource_log`;
CREATE TABLE `t_resource_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `resource_id` int(11) NOT NULL COMMENT '资源id',
  `type` int(11) NOT NULL COMMENT '日志类型(1:上传,2:下载)',
  `desc` varchar(255) DEFAULT NULL COMMENT '操作描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未被删除,1:已被删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_resource_log
-- ----------------------------
