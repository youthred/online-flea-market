/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : online_flea_market

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 06/08/2021 11:33:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_goods_star
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_star`;
CREATE TABLE `t_goods_star`  (
  `tb_id` bigint(0) NOT NULL,
  `goods_tb_id` bigint(0) NOT NULL,
  `buyer_sys_user_tb_id` bigint(0) NOT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
