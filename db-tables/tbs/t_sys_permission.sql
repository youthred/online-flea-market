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

 Date: 06/08/2021 11:34:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_permission`;
CREATE TABLE `t_sys_permission`  (
  `tb_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `permission_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `request_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permission_code` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permission_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permission_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pid` bigint(0) NOT NULL,
  `permit_any` bit(1) NOT NULL COMMENT '是否无需认证',
  PRIMARY KEY (`tb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_permission
-- ----------------------------
INSERT INTO `t_sys_permission` VALUES (3, '/city/**', 'ANY', 'city::any', '中国城市等级树', NULL, 48, b'1');
INSERT INTO `t_sys_permission` VALUES (4, '/common/**', 'ANY', 'common::any', '公共接口', NULL, 48, b'1');
INSERT INTO `t_sys_permission` VALUES (5, '/dev/**', 'ANY', 'dev::any', '开发', NULL, 48, b'0');
INSERT INTO `t_sys_permission` VALUES (6, '/dev/log.html', 'GET', 'dev::log.html', '开发日志页面', '跳转至开发日志页面', 5, b'1');
INSERT INTO `t_sys_permission` VALUES (7, '/goods/**', 'ANY', 'goods::any', '商品', NULL, 48, b'0');
INSERT INTO `t_sys_permission` VALUES (8, '/goods/page', 'POST', 'goods::page', '首页商品分页', NULL, 7, b'1');
INSERT INTO `t_sys_permission` VALUES (9, '/goods/*', 'GET', 'goods::detail', '通过TBID获取对应商品详情', NULL, 7, b'1');
INSERT INTO `t_sys_permission` VALUES (10, '/goods/*.html', 'GET', 'goods::detail.html', '通过TBID跳转对应商品详情页面', NULL, 7, b'1');
INSERT INTO `t_sys_permission` VALUES (11, '/goods/report', 'POST', 'goods::report', '举报商品', NULL, 7, b'1');
INSERT INTO `t_sys_permission` VALUES (12, '/goods/doChat/*.html', 'GET', 'goods::doChat.html', '跳转至私聊页面', NULL, 7, b'0');
INSERT INTO `t_sys_permission` VALUES (13, '/goods/offShelf/*', 'PUT', 'goods::offShelf', '根据TBID下架商品', NULL, 7, b'0');
INSERT INTO `t_sys_permission` VALUES (14, '/goods/onShelf/*', 'PUT', 'goods::onShelf', '根据TBID上架商品', NULL, 7, b'0');
INSERT INTO `t_sys_permission` VALUES (15, '/goods/save', 'POST', 'goods::save', '新增闲置', NULL, 7, b'0');
INSERT INTO `t_sys_permission` VALUES (16, '/goods/update', 'PUT', 'goods::update', '更新商品', NULL, 7, b'0');
INSERT INTO `t_sys_permission` VALUES (17, '/goods/delete/*', 'DELETE', 'goods::delete', '根据TBID删除商品', '更新为[下架、已删除]', 7, b'0');
INSERT INTO `t_sys_permission` VALUES (18, '/goods/buy/*', 'PUT', 'goods::buy', '购买商品', NULL, 7, b'0');
INSERT INTO `t_sys_permission` VALUES (19, '/loginCtrl', 'NONE', 'loginCtrl', '登录控制', NULL, 48, b'1');
INSERT INTO `t_sys_permission` VALUES (20, '/login.html', 'GET', 'loginCtrl::loginPage', '登录页面', NULL, 19, b'1');
INSERT INTO `t_sys_permission` VALUES (21, '/register.html', 'GET', 'loginCtrl::register.html', '注册页面', NULL, 19, b'1');
INSERT INTO `t_sys_permission` VALUES (22, '/imageCaptcha', 'GET', 'loginCtrl::imageCaptcha', '图片验证码', NULL, 19, b'1');
INSERT INTO `t_sys_permission` VALUES (23, '/currentLoginUser', 'GET', 'loginCtrl::currentLoginUser', '当前登录用户消息', NULL, 19, b'1');
INSERT INTO `t_sys_permission` VALUES (24, '/register', 'POST', 'loginCtrl::register', '用户注册', NULL, 19, b'1');
INSERT INTO `t_sys_permission` VALUES (25, '/verify/*', 'GET', 'loginCtrl::verify', '确认新用户', '确认新用户合法（验证合法邮箱），验证成功后跳转到提示页面', 19, b'1');
INSERT INTO `t_sys_permission` VALUES (26, '/netty/**', 'ANY', 'netty::any', 'NETTY服务', NULL, 48, b'0');
INSERT INTO `t_sys_permission` VALUES (27, '/netty/groupSex.html', 'GET', 'netty::groupSex.html', '群聊页面', NULL, 26, b'1');
INSERT INTO `t_sys_permission` VALUES (28, '/netty/groupSexNettyHost', 'GET', 'netty::groupSexNettyHost', '群聊HOST', NULL, 26, b'1');
INSERT INTO `t_sys_permission` VALUES (29, '/netty/goodsPrivateNettyHost', 'GET', 'netty::goodsPrivateNettyHost', '私聊HOST', NULL, 26, b'1');
INSERT INTO `t_sys_permission` VALUES (30, '/home/**', 'ANY', 'home::any', '用户后台', NULL, 48, b'0');
INSERT INTO `t_sys_permission` VALUES (31, '/home/my.html', 'GET', 'home::my.html', '[我的]页面', NULL, 30, b'0');
INSERT INTO `t_sys_permission` VALUES (32, '/home/admin.html', 'GET', 'home::admin.html', '[系统管理]页面', NULL, 30, b'0');
INSERT INTO `t_sys_permission` VALUES (33, '/home/my/posted/page', 'POST', 'home::my::postedPage', '我发布的 - 分页', NULL, 31, b'0');
INSERT INTO `t_sys_permission` VALUES (34, '/home/my/sold/page', 'POST', 'home::my::soldPage', '我卖出的 - 分页', NULL, 31, b'0');
INSERT INTO `t_sys_permission` VALUES (35, '/home/my/bought/page', 'POST', 'home::my::boughtPage', '我买到的 - 分页', NULL, 31, b'0');
INSERT INTO `t_sys_permission` VALUES (36, '/home/my/privateChat/chats', 'GET', 'home::my::privateChats', '私聊列表', NULL, 31, b'0');
INSERT INTO `t_sys_permission` VALUES (37, '/home/admin/goods/report/**', 'ANY', 'home::admin::goods::report::any', '系统管理 - 商品管理 - 举报审核', NULL, 32, b'0');
INSERT INTO `t_sys_permission` VALUES (38, '/home/admin/goods/report/page', 'POST', 'home::admin::goods::report::page', '举报审核分页', NULL, 37, b'0');
INSERT INTO `t_sys_permission` VALUES (39, '/home/admin/goods/report/review/*/*', 'PUT', 'home::admin::goods::report::review', '审核', NULL, 37, b'0');
INSERT INTO `t_sys_permission` VALUES (40, '/home/admin/user/user/**', 'ANY', 'home::admin::user::user::any', '系统管理 - 用户管理 - 用户', NULL, 32, b'0');
INSERT INTO `t_sys_permission` VALUES (41, '/home/admin/user/user/page', 'POST', 'home::admin::user::user::page', '用户管理分页', NULL, 40, b'0');
INSERT INTO `t_sys_permission` VALUES (42, '/home/admin/user/user/resetPassword/*', 'PUT', 'home::admin::user::user::resetPassword', '重置密码', NULL, 40, b'0');
INSERT INTO `t_sys_permission` VALUES (43, '/home/admin/user/user/enableOrDisable/*/*', 'PUT', 'home::admin::user::user::enableOrDisable', '启用或禁用', NULL, 40, b'0');
INSERT INTO `t_sys_permission` VALUES (44, '/home/admin/user/user/roles/*', 'GET', 'home::admin::user::user::roles', '所有角色', '已绑定[bound=true]', 40, b'0');
INSERT INTO `t_sys_permission` VALUES (45, '/home/admin/user/user/roleBind/*', 'PUT', 'home::admin::user::user::roleBind', '角色绑定', NULL, 40, b'0');
INSERT INTO `t_sys_permission` VALUES (46, '/home/admin/auth/permission/**', 'ANY', 'home::admin::auth::permission::any', '系统管理 - 权限管理 - 权限', NULL, 32, b'0');
INSERT INTO `t_sys_permission` VALUES (47, '/home/admin/auth/permission/page', 'POST', 'home::admin::auth::permission::page', '权限分页', NULL, 46, b'0');
INSERT INTO `t_sys_permission` VALUES (48, '/**', 'ANY', 'any', '所有权限', '管理员拥有所有权限', -1, b'0');
INSERT INTO `t_sys_permission` VALUES (49, '/', 'GET', 'index', '首页', NULL, 48, b'1');
INSERT INTO `t_sys_permission` VALUES (50, '/home/admin/auth/permission/tree', 'GET', 'home::admin::auth::permission::tree', '权限结构树', NULL, 46, b'0');
INSERT INTO `t_sys_permission` VALUES (51, '/city/treeDeep1', 'GET', 'city::treeDeep1', '省市', NULL, 3, b'1');
INSERT INTO `t_sys_permission` VALUES (52, '/city/treeDeep2', 'GET', 'city::treeDeep2', '省市区', NULL, 3, b'1');
INSERT INTO `t_sys_permission` VALUES (53, '/city/treeDeep3', 'GET', 'city::treeDeep1', '省市区县', NULL, 3, b'1');
INSERT INTO `t_sys_permission` VALUES (54, '/city/*', 'GET', 'city::getCityInfoById', '根据ID获取城市信息', '注意不是TBID', 3, b'1');
INSERT INTO `t_sys_permission` VALUES (55, '/common/appInfo', 'GET', 'common::appInfo', '项目信息', NULL, 4, b'1');
INSERT INTO `t_sys_permission` VALUES (56, '/home/admin/auth/role/**', 'ANY', 'home::admin::auth::role::any', '系统管理 - 权限管理 - 角色', NULL, 32, b'0');
INSERT INTO `t_sys_permission` VALUES (57, '/home/admin/auth/role/list', 'GET', 'home::admin::auth::role::list', '角色列表', NULL, 56, b'0');
INSERT INTO `t_sys_permission` VALUES (58, '/home/admin/auth/role/permissionBoundTree/*', 'GET', 'home::admin::auth::role::permissionBoundTree', '包含角色绑定信息的权限结构树', '已绑定[checked=true]', 56, b'0');
INSERT INTO `t_sys_permission` VALUES (59, '/home/admin/auth/role/permissionBind/*', 'PUT', 'home::admin::auth::role::permissionBind', '权限绑定', NULL, 56, b'0');
INSERT INTO `t_sys_permission` VALUES (60, '/goods/usedBookTypes', 'GET', 'goods::usedBookTypes', '二手书分类', NULL, 7, b'1');
INSERT INTO `t_sys_permission` VALUES (61, '/home/my/posted/usedBookTypes', 'GET', 'home::my::postedUsedBookTypes', '新增修改时二手书分类', '已隐藏“总类”，CODE=000', 31, b'0');

SET FOREIGN_KEY_CHECKS = 1;
