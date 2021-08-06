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

 Date: 06/08/2021 11:33:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_parameter
-- ----------------------------
DROP TABLE IF EXISTS `t_parameter`;
CREATE TABLE `t_parameter`  (
  `tb_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '组名',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE,
  UNIQUE INDEX `parameter_unique`(`group_name`, `code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_parameter
-- ----------------------------
INSERT INTO `t_parameter` VALUES (9, 'CHAT_MESSAGE_TYPE', '1', '文本');
INSERT INTO `t_parameter` VALUES (10, 'CHAT_MESSAGE_TYPE', '2', '图片');
INSERT INTO `t_parameter` VALUES (11, 'CHAT_MESSAGE_TYPE', '-1', 'FIRST-CHAT');
INSERT INTO `t_parameter` VALUES (12, 'USED_BOOK_TYPE', '000', '总类');
INSERT INTO `t_parameter` VALUES (13, 'USED_BOOK_TYPE', '100', '哲学与心理学');
INSERT INTO `t_parameter` VALUES (14, 'USED_BOOK_TYPE', '200', '宗教');
INSERT INTO `t_parameter` VALUES (15, 'USED_BOOK_TYPE', '300', '社会科学');
INSERT INTO `t_parameter` VALUES (16, 'USED_BOOK_TYPE', '400', '语文');
INSERT INTO `t_parameter` VALUES (17, 'USED_BOOK_TYPE', '500', '自然科学');
INSERT INTO `t_parameter` VALUES (18, 'USED_BOOK_TYPE', '600', '应用科学');
INSERT INTO `t_parameter` VALUES (19, 'USED_BOOK_TYPE', '700', '艺术');
INSERT INTO `t_parameter` VALUES (20, 'USED_BOOK_TYPE', '800', '文学');
INSERT INTO `t_parameter` VALUES (21, 'USED_BOOK_TYPE', '900', '历史与地理');

SET FOREIGN_KEY_CHECKS = 1;
