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

 Date: 06/08/2021 11:32:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_chat_message
-- ----------------------------
DROP TABLE IF EXISTS `t_chat_message`;
CREATE TABLE `t_chat_message`  (
  `tb_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL COMMENT '消息创建时间',
  `goods_tb_id` bigint(0) NOT NULL COMMENT '商品TBID',
  `buyer_sys_user_tb_id` bigint(0) NOT NULL COMMENT '买家用户TBID',
  `seller_sys_user_tb_id` bigint(0) NOT NULL COMMENT '卖家用户TBID',
  `message_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息内容',
  `message_type_code` int(0) NOT NULL COMMENT '消息类型',
  `sender_sys_user_tb_id` bigint(0) NOT NULL COMMENT '消息发送者用户TBID',
  `read_buyer` bit(1) DEFAULT NULL COMMENT '买家已读',
  `read_seller` bit(1) DEFAULT NULL COMMENT '卖家已读',
  PRIMARY KEY (`tb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 135 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_chat_message
-- ----------------------------
INSERT INTO `t_chat_message` VALUES (115, '2021-04-01 21:26:57', 1, 2, 1, '你好', -1, 2, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (116, '2021-04-01 21:27:02', 1, 2, 1, '123', 1, 2, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (117, '2021-04-01 21:27:33', 1, 2, 1, '22', 1, 1, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (118, '2021-04-03 15:03:49', 1, 2, 1, '123', 1, 1, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (119, '2021-04-10 16:10:29', 16, 1, 2, '你好', -1, 1, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (120, '2021-04-11 10:28:21', 16, 1, 2, '123123', 1, 1, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (121, '2021-04-11 10:29:04', 16, 1, 2, '111', 1, 2, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (122, '2021-04-11 10:29:40', 1, 2, 1, '123123', 1, 2, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (123, '2021-04-11 10:30:17', 1, 2, 1, '11', 1, 1, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (124, '2021-04-11 10:30:18', 1, 2, 1, '12313', 1, 1, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (125, '2021-04-11 10:30:19', 1, 2, 1, '12123', 1, 1, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (126, '2021-04-11 18:46:56', 7, 2, 1, '你好', -1, 2, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (127, '2021-04-12 21:21:44', 18, 2, 1, '你好', -1, 2, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (128, '2021-04-12 21:23:14', 10, 2, 1, '你好', -1, 2, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (129, '2021-04-12 21:28:12', 8, 2, 1, '你好', -1, 2, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (130, '2021-04-13 20:32:54', 7, 14, 1, '你好', -1, 14, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (131, '2021-04-16 18:22:36', 7, 14, 1, '123', 1, 14, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (132, '2021-04-16 19:02:23', 8, 14, 1, '你好', -1, 14, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (133, '2021-04-16 19:08:51', 1, 14, 1, '你好', -1, 14, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (134, '2021-04-16 19:47:33', 1, 14, 1, 'a', 1, 14, b'1', b'1');
INSERT INTO `t_chat_message` VALUES (135, '2021-05-05 12:43:32', 1, 2, 1, '1', 1, 1, b'1', b'1');

SET FOREIGN_KEY_CHECKS = 1;
