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

 Date: 06/08/2021 11:33:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_bind_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_bind_user_role`;
CREATE TABLE `t_sys_bind_user_role`  (
  `user_tb_id` bigint(0) NOT NULL,
  `role_tb_id` bigint(0) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_bind_user_role
-- ----------------------------
INSERT INTO `t_sys_bind_user_role` VALUES (1, 1);
INSERT INTO `t_sys_bind_user_role` VALUES (2, 2);
INSERT INTO `t_sys_bind_user_role` VALUES (14, 2);

SET FOREIGN_KEY_CHECKS = 1;
