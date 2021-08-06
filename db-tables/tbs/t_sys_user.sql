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

 Date: 06/08/2021 11:34:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
  `tb_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名，唯一',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '加密后的密码',
  `nickname` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `enabled` bit(1) NOT NULL COMMENT '是否启用',
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE,
  UNIQUE INDEX `t_sys_user_username_unique`(`username`) USING BTREE,
  UNIQUE INDEX `t_sys_user_email_unique`(`email`) USING BTREE,
  UNIQUE INDEX `t_sys_user_nickname_unique`(`nickname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', '张三', 'pj.w@qq.com', b'1', '2020-12-13 00:09:54', '2021-04-24 13:49:04');
INSERT INTO `t_sys_user` VALUES (2, 'jack', '4ff9fc6e4e5d5f590c4f2134a8cc96d1', '李四', 'pp@qq.com', b'1', '2020-12-13 00:11:39', '2021-04-26 22:08:01');
INSERT INTO `t_sys_user` VALUES (14, 'rose', 'fcdc7b4207660a1372d0cd5491ad856e', 'rose', 'rose@qq.com', b'0', '2021-04-25 17:33:04', '2021-04-27 18:03:41');

SET FOREIGN_KEY_CHECKS = 1;
