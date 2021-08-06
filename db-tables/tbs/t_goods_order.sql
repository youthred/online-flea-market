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

 Date: 06/08/2021 11:33:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_goods_order
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_order`;
CREATE TABLE `t_goods_order`  (
  `tb_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL COMMENT '订单创建时间',
  `complete_time` datetime(0) DEFAULT NULL COMMENT '订单完成时间',
  `done` bit(1) NOT NULL COMMENT '订单是否已完成',
  `goods_tb_id` bigint(0) NOT NULL COMMENT '商品TBID',
  `ordered` bit(1) NOT NULL COMMENT '是否订下',
  `paid` bit(1) NOT NULL COMMENT '是否付款',
  `seller_sys_user_tb_id` bigint(0) NOT NULL COMMENT '卖家用户TBID',
  `buyer_sys_user_tb_id` bigint(0) NOT NULL COMMENT '买家用户TBID',
  PRIMARY KEY (`tb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_goods_order
-- ----------------------------
INSERT INTO `t_goods_order` VALUES (9, '2021-04-11 21:31:09', '2021-04-11 21:31:09', b'1', 7, b'1', b'1', 1, 2);
INSERT INTO `t_goods_order` VALUES (10, '2021-04-12 21:21:46', '2021-04-12 21:21:46', b'1', 18, b'1', b'1', 1, 2);
INSERT INTO `t_goods_order` VALUES (11, '2021-04-16 19:01:32', '2021-04-16 19:01:32', b'1', 8, b'1', b'1', 1, 2);

SET FOREIGN_KEY_CHECKS = 1;
