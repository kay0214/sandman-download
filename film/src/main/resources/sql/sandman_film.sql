/*
Navicat MySQL Data Transfer

Source Server         : sandman-local
Source Server Version : 50149
Source Host           : localhost:3306
Source Database       : sandman_film

Target Server Type    : MYSQL
Target Server Version : 50149
File Encoding         : 65001

Date: 2019-01-22 18:01:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_film
-- ----------------------------
DROP TABLE IF EXISTS `t_film`;
CREATE TABLE `t_film` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `film_name` varchar(255) NOT NULL COMMENT '电影名',
  `film_url` varchar(255) NOT NULL COMMENT '电影url',
  `film_password` varchar(255) NOT NULL COMMENT '链接的密码',
  `film_image` varchar(255) NOT NULL COMMENT '电影封面',
  `film_actor` varchar(255) DEFAULT NULL COMMENT '电影主演',
  `film_type` int(11) DEFAULT NULL,
  `film_time` varchar(255) DEFAULT NULL COMMENT '上映时间',
  `film_clarity` varchar(255) DEFAULT NULL COMMENT '影片清晰度',
  `film_area` varchar(255) DEFAULT NULL COMMENT '电影地区',
  `film_director` varchar(255) DEFAULT NULL COMMENT '电影导演',
  `film_language` varchar(255) DEFAULT NULL COMMENT '电影语言',
  `film_desc` varchar(255) DEFAULT NULL COMMENT '电影介绍',
  `film_gold` int(11) NOT NULL,
  `buy_count` int(11) NOT NULL DEFAULT '0' COMMENT '购买次数',
  `status` int(11) DEFAULT '1' COMMENT '状态(0:未启用;1:启用)',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标记(0:未被删除,1:已被删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_film
-- ----------------------------
INSERT INTO `t_film` VALUES ('1', '大黄蜂', 'https://www.baidu.com', '123', 'http://117.48.197.114/icon/default.jpg', 'haha', '1', '2019', '高清', '美国', '导演1', '英语中字幕', '大黄蜂剧情', '1', '0', '1', '2019-01-21 15:00:50', '2019-01-21 16:59:05', '0');
INSERT INTO `t_film` VALUES ('2', '蚁人', 'https://www.sian.com', '123', 'http://117.48.197.114/icon/default.jpg', 'hehe', '2', '2018', '高清', '中国', '导演2', '英文中字', '蚁人剧情', '1', '1', '1', '2019-01-21 15:22:52', '2019-01-21 16:59:09', '0');
INSERT INTO `t_film` VALUES ('3', '来电狂响（Kill Mobile）', '暂无', '暂无', 'http://117.48.197.114/film/20190122162658来电狂响.jpg', '佟大为、马丽、霍思燕、乔杉、田雨、代乐乐、奚梦瑶', '2', '2018-12-28', '高清', '中国', '于淼', '中文', '该片讲述了七位好友在聚会中玩了一个游戏，将手机交出，分享所有来电、短信、微信、甚至广告弹窗，掀开了一场啼笑皆非的情感风暴', '1', '0', '1', '2019-01-22 16:28:21', '2019-01-22 16:28:21', '0');

-- ----------------------------
-- Table structure for t_film_log
-- ----------------------------
DROP TABLE IF EXISTS `t_film_log`;
CREATE TABLE `t_film_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `film_id` int(11) NOT NULL,
  `film_name` varchar(255) DEFAULT NULL,
  `film_url` varchar(255) DEFAULT NULL COMMENT '影片地址',
  `film_password` varchar(255) DEFAULT NULL COMMENT '影片提取码',
  `desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标记(0:未被删除,1:已被删除)',
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_film_log
-- ----------------------------
INSERT INTO `t_film_log` VALUES ('1', '6', '1', '大黄蜂', 'https://www.baidu.com', '123', '用户购买影片', '2019-01-21 20:12:53', '2019-01-21 20:51:35', '0');
INSERT INTO `t_film_log` VALUES ('2', '6', '2', '蚁人', 'https://www.sian.com', '123', '用户购买影片', '2019-01-21 22:05:07', '2019-01-21 22:05:07', '0');

-- ----------------------------
-- Table structure for t_film_type
-- ----------------------------
DROP TABLE IF EXISTS `t_film_type`;
CREATE TABLE `t_film_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '分类名',
  `status` int(11) DEFAULT NULL COMMENT '状态(0:未启用;1:启用)',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_film_type
-- ----------------------------
INSERT INTO `t_film_type` VALUES ('1', '记录片', '1', '2019-01-22 14:23:08', '2019-01-22 17:52:10', '0');
INSERT INTO `t_film_type` VALUES ('2', '战争片', '1', '2019-01-22 14:23:25', '2019-01-22 17:52:18', '0');
INSERT INTO `t_film_type` VALUES ('3', '历史片', '1', '2019-01-22 17:51:19', '2019-01-22 17:52:26', '0');
INSERT INTO `t_film_type` VALUES ('4', '传记片', '1', '2019-01-22 17:52:33', '2019-01-22 17:52:33', '0');
INSERT INTO `t_film_type` VALUES ('5', '体育片', '1', '2019-01-22 17:52:40', '2019-01-22 17:52:40', '0');
INSERT INTO `t_film_type` VALUES ('6', '科幻片', '1', '2019-01-22 17:52:49', '2019-01-22 17:52:49', '0');
INSERT INTO `t_film_type` VALUES ('7', '魔幻片', '1', '2019-01-22 17:52:57', '2019-01-22 17:52:57', '0');
INSERT INTO `t_film_type` VALUES ('8', '奇幻片', '1', '2019-01-22 17:53:06', '2019-01-22 17:53:06', '0');
INSERT INTO `t_film_type` VALUES ('9', '文艺片', '1', '2019-01-22 17:53:13', '2019-01-22 17:53:13', '0');
INSERT INTO `t_film_type` VALUES ('10', '动漫片', '1', '2019-01-22 17:53:24', '2019-01-22 17:53:24', '0');
INSERT INTO `t_film_type` VALUES ('11', '武侠片', '1', '2019-01-22 17:53:31', '2019-01-22 17:53:31', '0');
INSERT INTO `t_film_type` VALUES ('12', '古装片', '1', '2019-01-22 17:53:38', '2019-01-22 17:53:38', '0');
INSERT INTO `t_film_type` VALUES ('13', '动作片', '1', '2019-01-22 17:53:44', '2019-01-22 17:53:44', '0');
INSERT INTO `t_film_type` VALUES ('14', '爱情片', '1', '2019-01-22 17:53:51', '2019-01-22 17:53:51', '0');
INSERT INTO `t_film_type` VALUES ('15', '剧情片', '1', '2019-01-22 17:53:57', '2019-01-22 17:53:57', '0');
INSERT INTO `t_film_type` VALUES ('16', '喜剧片', '1', '2019-01-22 17:54:04', '2019-01-22 17:54:04', '0');
INSERT INTO `t_film_type` VALUES ('17', '家庭片', '1', '2019-01-22 17:54:11', '2019-01-22 17:54:11', '0');
INSERT INTO `t_film_type` VALUES ('18', '伦理片', '1', '2019-01-22 17:54:18', '2019-01-22 17:54:18', '0');
INSERT INTO `t_film_type` VALUES ('19', '恐怖片', '1', '2019-01-22 17:54:24', '2019-01-22 17:54:24', '0');
INSERT INTO `t_film_type` VALUES ('20', '惊悚片', '1', '2019-01-22 17:54:31', '2019-01-22 17:54:31', '0');
INSERT INTO `t_film_type` VALUES ('21', '冒险片', '1', '2019-01-22 17:54:38', '2019-01-22 17:54:38', '0');
INSERT INTO `t_film_type` VALUES ('22', '犯罪片', '1', '2019-01-22 17:54:45', '2019-01-22 17:54:45', '0');
INSERT INTO `t_film_type` VALUES ('23', '悬疑片', '1', '2019-01-22 17:54:51', '2019-01-22 17:54:51', '0');

-- ----------------------------
-- Table structure for t_find_plz
-- ----------------------------
DROP TABLE IF EXISTS `t_find_plz`;
CREATE TABLE `t_find_plz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '称呼',
  `content` varchar(255) NOT NULL COMMENT '留言内容',
  `contact` varchar(255) NOT NULL COMMENT '联系方式',
  `qq` varchar(255) DEFAULT NULL,
  `wechat` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '状态(0:未回复,1:已回复)',
  `status_desc` varchar(255) DEFAULT NULL COMMENT '回复',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_find_plz
-- ----------------------------
INSERT INTO `t_find_plz` VALUES ('1', '孙沛凯', '测试回复内容', 'sunpeikai@yeah.net', '929525390', '17685749636', '1', 'hahahah', '2019-01-22 11:02:27', '2019-01-22 17:13:06', '0');
INSERT INTO `t_find_plz` VALUES ('2', '哎呀飞碟', '测试留言内容', '17685749636', '', '', '0', null, '2019-01-22 11:37:21', '2019-01-22 11:37:21', '0');

-- ----------------------------
-- Table structure for t_gold_log
-- ----------------------------
DROP TABLE IF EXISTS `t_gold_log`;
CREATE TABLE `t_gold_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `film_id` int(11) DEFAULT '0' COMMENT '电影id',
  `film_name` varchar(255) DEFAULT '' COMMENT '电影名称',
  `original_gold` int(11) DEFAULT NULL COMMENT '原有积分',
  `film_gold` int(11) DEFAULT NULL COMMENT '电影积分',
  `current_gold` int(11) DEFAULT NULL COMMENT '现有积分',
  `operation_desc` varchar(255) NOT NULL COMMENT '操作描述',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '删除标记(0:未被删除,1:已被删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_gold_log
-- ----------------------------
INSERT INTO `t_gold_log` VALUES ('1', '6', null, '用户签到奖励积分', '5', null, '6', '用户签到奖励积分', '2019-01-21 17:49:37', '2019-01-21 17:49:37', '0');
INSERT INTO `t_gold_log` VALUES ('2', '6', '1', '大黄蜂', '6', '1', '5', '用户购买影片', '2019-01-21 20:12:53', '2019-01-21 20:12:53', '0');
INSERT INTO `t_gold_log` VALUES ('3', '6', '2', '蚁人', '5', '1', '4', '用户购买影片', '2019-01-21 22:05:07', '2019-01-21 22:05:07', '0');
INSERT INTO `t_gold_log` VALUES ('4', '7', null, '用户签到奖励积分', '1', null, '2', '用户签到奖励积分', '2019-01-21 22:08:31', '2019-01-21 22:08:31', '0');
INSERT INTO `t_gold_log` VALUES ('5', '6', null, '用户签到奖励积分', '4', null, '5', '用户签到奖励积分', '2019-01-22 09:17:50', '2019-01-22 09:17:50', '0');
