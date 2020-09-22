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

 Date: 12/04/2020 17:11:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for drug_show
-- ----------------------------
DROP TABLE IF EXISTS `drug_show`;
CREATE TABLE `drug_show` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `drug_id` int(11) NOT NULL,
  `drug_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `show_id` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `show_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `drug_place` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `creator` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_user` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
