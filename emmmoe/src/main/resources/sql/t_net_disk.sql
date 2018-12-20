/*
Navicat MySQL Data Transfer

Source Server         : root-local
Source Server Version : 50149
Source Host           : localhost:3306
Source Database       : sandman_emmmoe

Target Server Type    : MYSQL
Target Server Version : 50149
File Encoding         : 65001

Date: 2018-12-20 17:40:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_net_disk
-- ----------------------------
DROP TABLE IF EXISTS `t_net_disk`;
CREATE TABLE `t_net_disk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `net_disk` varchar(255) DEFAULT NULL,
  `page` int(11) DEFAULT NULL,
  `pass` varchar(255) DEFAULT 'snzs',
  `unzip_pass` varchar(255) DEFAULT NULL,
  `success` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=632 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_net_disk
-- ----------------------------
INSERT INTO `t_net_disk` VALUES ('1', '1', '1', '1', 'snzs', '', '0', '2018-12-20 17:29:16');
