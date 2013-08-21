/*
Navicat MySQL Data Transfer

Source Server         : web
Source Server Version : 50162
Source Host           : 127.0.0.1:3306
Source Database       : web

Target Server Type    : MYSQL
Target Server Version : 50162
File Encoding         : 65001

Date: 2012-07-27 10:20:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `outputtypes`
-- ----------------------------
DROP TABLE IF EXISTS `outputtypes`;
CREATE TABLE `outputtypes` (
  `id` tinyint(4) unsigned NOT NULL DEFAULT '0',
  `type` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of outputtypes
-- ----------------------------
INSERT INTO `outputtypes` VALUES ('0', 'json');