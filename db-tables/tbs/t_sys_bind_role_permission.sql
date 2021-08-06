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

 Date: 06/08/2021 11:33:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_bind_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_bind_role_permission`;
CREATE TABLE `t_sys_bind_role_permission`  (
  `role_tb_id` bigint(0) NOT NULL,
  `permission_tb_id` bigint(0) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_bind_role_permission
-- ----------------------------
INSERT INTO `t_sys_bind_role_permission` VALUES (1, 48);
INSERT INTO `t_sys_bind_role_permission` VALUES (2, 12);
INSERT INTO `t_sys_bind_role_permission` VALUES (2, 13);
INSERT INTO `t_sys_bind_role_permission` VALUES (2, 14);
INSERT INTO `t_sys_bind_role_permission` VALUES (2, 15);
INSERT INTO `t_sys_bind_role_permission` VALUES (2, 16);
INSERT INTO `t_sys_bind_role_permission` VALUES (2, 17);
INSERT INTO `t_sys_bind_role_permission` VALUES (2, 18);
INSERT INTO `t_sys_bind_role_permission` VALUES (2, 33);
INSERT INTO `t_sys_bind_role_permission` VALUES (2, 34);
INSERT INTO `t_sys_bind_role_permission` VALUES (2, 35);
INSERT INTO `t_sys_bind_role_permission` VALUES (2, 36);
INSERT INTO `t_sys_bind_role_permission` VALUES (2, 61);

SET FOREIGN_KEY_CHECKS = 1;
