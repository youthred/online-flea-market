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

 Date: 06/08/2021 11:33:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_goods_report
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_report`;
CREATE TABLE `t_goods_report`  (
  `tb_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL,
  `goods_tb_id` bigint(0) NOT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '举报理由',
  `reviewed` bit(1) NOT NULL COMMENT '是否已审查',
  `passed` bit(1) DEFAULT NULL COMMENT '是否举报通过（下架对应商品）',
  `reviewer_sys_user_tb_id` bigint(0) DEFAULT NULL COMMENT '审查人',
  `review_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品举报' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_goods_report
-- ----------------------------
INSERT INTO `t_goods_report` VALUES (20, '2021-02-08 10:56:57', 5, 'dfasdfawefasd', b'1', b'1', 1, '2021-04-22 19:44:57');
INSERT INTO `t_goods_report` VALUES (21, '2021-03-03 16:12:42', 5, 'asd', b'1', b'0', 1, '2021-04-22 19:49:20');
INSERT INTO `t_goods_report` VALUES (30, '2021-04-22 20:37:36', 9, '4', b'1', b'0', 1, '2021-04-22 20:38:32');
INSERT INTO `t_goods_report` VALUES (31, '2021-04-22 20:40:10', 6, '1', b'1', b'1', 1, '2021-04-22 20:40:44');
INSERT INTO `t_goods_report` VALUES (32, '2021-04-22 20:42:23', 1, '1', b'1', b'0', 1, '2021-04-25 00:05:22');
INSERT INTO `t_goods_report` VALUES (33, '2021-04-22 20:42:26', 1, '2', b'0', NULL, NULL, NULL);
INSERT INTO `t_goods_report` VALUES (34, '2021-04-22 20:42:28', 1, '3', b'0', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
