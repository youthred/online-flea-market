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

 Date: 06/08/2021 11:32:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`  (
  `tb_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pics` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'split with \"|\"',
  `views` bigint(0) DEFAULT 0,
  `city_id` bigint(0) NOT NULL COMMENT 't_china_city.id',
  `type` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品类型',
  `type_code` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型CODE',
  `seller_sys_user_tb_id` bigint(0) NOT NULL,
  `off_shelf` bit(1) NOT NULL COMMENT '是否已下架',
  `deleted` bit(1) NOT NULL COMMENT '是否已删除',
  PRIMARY KEY (`tb_id`) USING BTREE,
  INDEX `goods_search`(`desc`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES (1, '2021-01-16 22:14:56', '2021-04-29 15:17:17', 13.00, ';hghg撒大苏打\\n[ddddd', 'https://source.unsplash.com/random?rd=1', 141, 1101, 'USED_BOOK_TYPE', '500', 1, b'0', b'0');
INSERT INTO `t_goods` VALUES (2, '2021-01-16 22:24:14', '2021-04-22 14:50:24', 14.00, ';lop;d哦i机阿里山可见度拉萨', 'https://source.unsplash.com/random?rd=2', 44, 1101, 'USED_BOOK_TYPE', '100', 1, b'0', b'0');
INSERT INTO `t_goods` VALUES (4, '2021-01-16 22:24:14', '2021-04-22 19:38:53', 14.00, 'lklnkl哦i机阿里山可见度拉萨asdasdasssssssssssssssssssssss', 'https://source.unsplash.com/random?rd=3', 47, 1101, 'USED_BOOK_TYPE', '100', 1, b'1', b'0');
INSERT INTO `t_goods` VALUES (5, '2021-01-16 22:24:14', '2021-01-17 01:54:13', 14.00, ';jkjr哦i机阿里山可见度拉萨fasdfasdffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff', 'https://source.unsplash.com/random?rd=4', 28, 1101, 'USED_BOOK_TYPE', '100', 1, b'0', b'0');
INSERT INTO `t_goods` VALUES (6, '2021-01-16 22:24:14', '2021-04-22 20:40:44', 14.00, 'dsqqq哦i机阿里山可见度拉萨', 'https://source.unsplash.com/random?rd=5', 8, 1101, 'USED_BOOK_TYPE', '100', 1, b'1', b'0');
INSERT INTO `t_goods` VALUES (7, '2021-01-16 22:24:14', '2021-04-11 21:31:09', 14.00, 'asdsadasd哦i机阿里山可见度拉萨', 'https://source.unsplash.com/random?rd=6', 1, 1101, 'USED_BOOK_TYPE', '100', 1, b'1', b'0');
INSERT INTO `t_goods` VALUES (8, '2021-01-16 22:24:14', '2021-04-16 19:01:32', 14.00, 'gsdfg哦i机阿里山可见度拉萨dasdas', 'https://source.unsplash.com/random?rd=7', 1, 1101, 'USED_BOOK_TYPE', '100', 1, b'1', b'0');
INSERT INTO `t_goods` VALUES (9, '2021-01-16 22:24:14', '2021-01-17 01:54:20', 14.00, 'asd哦i机阿里山可见度拉萨', 'https://source.unsplash.com/random?rd=8', 4, 1101, 'USED_BOOK_TYPE', '100', 1, b'0', b'0');
INSERT INTO `t_goods` VALUES (10, '2021-01-16 22:24:14', '2021-04-29 15:17:35', 14.00, 'hdss哦i机阿bnws里山可见度拉萨asdasdasd', 'https://source.unsplash.com/random?rd=9', 3, 3209, 'USED_BOOK_TYPE', '100', 1, b'0', b'0');
INSERT INTO `t_goods` VALUES (11, '2021-01-16 22:24:14', '2021-01-17 01:54:24', 14.00, 'dfgh哦i机阿里山可见度拉萨', 'https://source.unsplash.com/random?rd=10', 0, 1101, 'USED_BOOK_TYPE', '100', 1, b'0', b'0');
INSERT INTO `t_goods` VALUES (12, '2021-01-16 22:24:14', '2021-01-17 01:54:27', 14.00, 'ffaa哦i机阿里山可见度拉萨', 'https://source.unsplash.com/random?rd=11', 4, 1101, 'USED_BOOK_TYPE', '100', 1, b'0', b'0');
INSERT INTO `t_goods` VALUES (13, '2021-04-04 10:35:47', '2021-04-11 21:30:45', 2.00, 'test goods desc3', 'https://source.unsplash.com/random?rd=12', 2, 1101, 'USED_BOOK_TYPE', '100', 2, b'0', b'0');
INSERT INTO `t_goods` VALUES (14, '2021-04-04 10:54:18', '2021-04-04 10:54:18', 0.00, '123', 'https://source.unsplash.com/random?rd=13', 1, 1101, 'USED_BOOK_TYPE', '100', 2, b'1', b'1');
INSERT INTO `t_goods` VALUES (15, '2021-04-04 10:56:02', '2021-04-04 10:56:02', 0.00, '123', 'https://source.unsplash.com/random?rd=14', 0, 1101, 'USED_BOOK_TYPE', '100', 2, b'1', b'1');
INSERT INTO `t_goods` VALUES (16, '2021-04-04 11:21:44', '2021-04-11 21:30:44', 0.01, '123333', 'https://source.unsplash.com/random?rd=15', 6, 1101, 'USED_BOOK_TYPE', '100', 2, b'0', b'0');
INSERT INTO `t_goods` VALUES (17, '2021-04-04 11:22:38', '2021-04-04 11:22:38', 1.00, '123', 'https://source.unsplash.com/random?rd=16', 0, 1101, 'USED_BOOK_TYPE', '100', 2, b'1', b'1');
INSERT INTO `t_goods` VALUES (18, '2021-04-12 20:43:02', '2021-04-12 21:21:46', 1.00, 'qwe', 'https://source.unsplash.com/random?rd=17', 3, 1101, 'USED_BOOK_TYPE', '100', 1, b'1', b'0');
INSERT INTO `t_goods` VALUES (19, '2021-04-22 16:48:59', '2021-04-22 16:48:59', 1.00, '123123', 'https://source.unsplash.com/random?rd=177', 0, 1101, 'USED_BOOK_TYPE', '100', 1, b'0', b'0');
INSERT INTO `t_goods` VALUES (20, '2021-04-22 16:49:19', '2021-04-22 16:49:34', 123.00, '123', 'https://source.unsplash.com/random?rd=176', 0, 1101, 'USED_BOOK_TYPE', '100', 1, b'1', b'0');
INSERT INTO `t_goods` VALUES (21, '2021-04-29 15:13:55', '2021-04-29 15:14:11', 1.00, '123', 'https://source.unsplash.com/random?rd=0.3687787114499259', 0, 1101, 'USED_BOOK_TYPE', '300', 1, b'0', b'0');

SET FOREIGN_KEY_CHECKS = 1;
