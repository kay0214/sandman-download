/*
Navicat MySQL Data Transfer

Source Server         : root-local
Source Server Version : 50149
Source Host           : localhost:3306
Source Database       : sandman_emmmoe

Target Server Type    : MYSQL
Target Server Version : 50149
File Encoding         : 65001

Date: 2018-12-20 17:39:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_root_url
-- ----------------------------
DROP TABLE IF EXISTS `t_root_url`;
CREATE TABLE `t_root_url` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `root` int(11) DEFAULT NULL COMMENT '''根目录(0:登船地址;1:资源地址)''',
  `create_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_root_url
-- ----------------------------
INSERT INTO `t_root_url` VALUES ('1', '恶魔喵获取登船地址的url', 'https://www.fli.moe', '0', '2018-12-20 14:09:46');
INSERT INTO `t_root_url` VALUES ('2', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 16:24:38');
INSERT INTO `t_root_url` VALUES ('3', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 16:28:59');
INSERT INTO `t_root_url` VALUES ('4', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 16:38:22');
INSERT INTO `t_root_url` VALUES ('5', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 16:40:03');
INSERT INTO `t_root_url` VALUES ('6', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 16:51:54');
INSERT INTO `t_root_url` VALUES ('7', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 16:55:38');
INSERT INTO `t_root_url` VALUES ('8', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 16:58:11');
INSERT INTO `t_root_url` VALUES ('9', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 17:03:00');
INSERT INTO `t_root_url` VALUES ('10', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 17:08:41');
INSERT INTO `t_root_url` VALUES ('11', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 17:09:31');
INSERT INTO `t_root_url` VALUES ('12', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 17:10:36');
INSERT INTO `t_root_url` VALUES ('13', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 17:11:43');
INSERT INTO `t_root_url` VALUES ('14', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 17:12:22');
INSERT INTO `t_root_url` VALUES ('15', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 17:13:10');
INSERT INTO `t_root_url` VALUES ('16', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 17:14:06');
INSERT INTO `t_root_url` VALUES ('17', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 17:15:09');
INSERT INTO `t_root_url` VALUES ('18', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 17:15:57');
INSERT INTO `t_root_url` VALUES ('19', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 17:27:50');
INSERT INTO `t_root_url` VALUES ('20', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 17:29:34');
INSERT INTO `t_root_url` VALUES ('21', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 17:32:43');
INSERT INTO `t_root_url` VALUES ('22', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 17:33:28');
INSERT INTO `t_root_url` VALUES ('23', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 17:34:40');
INSERT INTO `t_root_url` VALUES ('24', '恶魔喵最新登船地址', 'https://qwq.emm.moe', '1', '2018-12-20 17:35:41');
