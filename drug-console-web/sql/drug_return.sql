/*
 Navicat Premium Data Transfer

 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : 47.104.90.52:3306
 Source Schema         : drug_console

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 12/04/2020 17:10:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for drug_return
-- ----------------------------
DROP TABLE IF EXISTS `drug_return`;
CREATE TABLE `drug_return` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `drug_id` int(11) NOT NULL,
  `drug_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `number` int(11) DEFAULT NULL,
  `sell_time` timestamp NULL DEFAULT NULL,
  `cause` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `creator` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_user` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
