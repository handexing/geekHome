/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50711
 Source Host           : localhost:3306
 Source Schema         : geek

 Target Server Type    : MySQL
 Target Server Version : 50711
 File Encoding         : 65001

 Date: 05/10/2017 14:33:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `STATE` int(1) DEFAULT NULL,
  `IS_SYSTEM` int(1) DEFAULT 0,
  `SALT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CREATE_TIME` datetime(0) DEFAULT NULL,
  `UPDATE_TIME` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'c5941c5f3bc693a75e6e863bd2c55ce3', 1, 0, '1ab6d62faa91ae7deec76d6f13ef1600', '2017-09-14 18:06:21', NULL);
INSERT INTO `admin` VALUES (2, 'handx', 'c5941c5f3bc693a75e6e863bd2c55ce3', 1, 0, '1ab6d62faa91ae7deec76d6f13ef1600', '2017-09-25 17:00:01', NULL);
INSERT INTO `admin` VALUES (7, 'rose', '211d20069e92801a33bca60109a7f5f3', 1, 0, 'f6076aa00f49933a82b1685e3b180afc', '2017-09-29 15:42:16', NULL);

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADMIN_ID` bigint(20) DEFAULT NULL,
  `ROLE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理員角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (1, 1, 1);
INSERT INTO `admin_role` VALUES (2, 2, 2);
INSERT INTO `admin_role` VALUES (9, 7, 2);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `THEME_ID` bigint(20) DEFAULT NULL COMMENT '主题ID，问与答，开源，博客',
  `CONTENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `CREATE_TIME` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for discuss
-- ----------------------------
DROP TABLE IF EXISTS `discuss`;
CREATE TABLE `discuss`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) DEFAULT NULL COMMENT '用户id',
  `TITLE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标题',
  `LABLE_ID` bigint(20) DEFAULT NULL COMMENT '标签id',
  `COLLECT_COUNT` int(255) DEFAULT NULL COMMENT '收藏数量',
  `BROWSE_COUNT` int(255) DEFAULT NULL COMMENT '浏览数量',
  `TYPE` int(1) DEFAULT NULL COMMENT '类型：1-问与答 2-博客 3-开源',
  `CONTENT` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '内容',
  `COMMENT_ID` bigint(20) DEFAULT NULL COMMENT '评论ID',
  `CREATE_TIME` datetime(0) DEFAULT NULL,
  `UPDATE_TIME` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问与答表Q & A' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LABLE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PARENT_ID` bigint(20) DEFAULT 0,
  `SORT` int(255) DEFAULT NULL,
  `TYPE` int(1) DEFAULT NULL,
  `CREATE_TIME` datetime(0) DEFAULT NULL,
  `UPDATE_TIME` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of label
-- ----------------------------
INSERT INTO `label` VALUES (1, 'JAVA', 0, 0, 1, '2017-10-03 14:44:03', NULL);
INSERT INTO `label` VALUES (2, '多线程', 1, 1, 1, '2017-10-03 14:44:07', NULL);
INSERT INTO `label` VALUES (3, 'spring', 0, 0, 1, '2017-10-03 15:42:46', '2017-10-03 15:43:22');
INSERT INTO `label` VALUES (5, 'springboot', 3, 1, 1, '2017-10-03 15:43:22', NULL);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `TYPE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型',
  `URL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求路径',
  `CODE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限标识',
  `PARENT_ID` bigint(20) DEFAULT NULL COMMENT '父ID',
  `PARENT_IDS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父IDS',
  `SORT` int(11) DEFAULT 0 COMMENT '顺序',
  `CREATE_TIME` datetime(0) DEFAULT NULL,
  `UPDATE_TIME` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '系统管理', 'menu', '/system/systemPage', 'system:manage', 0, '', 1, '2017-09-15 10:20:26', '2017-09-27 14:03:11');
INSERT INTO `menu` VALUES (2, '菜单管理', 'menu', '/menu/menuPage', 'menu:index', 1, NULL, 3, '2017-09-15 11:14:01', NULL);
INSERT INTO `menu` VALUES (3, '修改排序', 'auth', '/menu/updateOrder', 'menu:sort', 2, NULL, 2, '2017-09-15 14:56:28', NULL);
INSERT INTO `menu` VALUES (4, '保存资源菜单', 'auth', '/menu/saveMenu', 'menu:save', 2, NULL, 3, '2017-09-19 15:43:09', NULL);
INSERT INTO `menu` VALUES (6, '获取资源', 'auth', '/menu/getMenu', 'menu:getMenu', 2, NULL, 4, '2017-09-19 16:50:46', NULL);
INSERT INTO `menu` VALUES (7, '资源列表', 'auth', '/menu/menuList', 'menu:allMenu', 2, NULL, 5, '2017-09-19 20:56:08', NULL);
INSERT INTO `menu` VALUES (14, '删除资源', 'auth', '/menu/delMenu', 'menu:delete', 2, NULL, 6, '2017-09-20 14:40:25', NULL);
INSERT INTO `menu` VALUES (26, '角色管理', 'menu', '/role/rolePage', 'role:index', 1, NULL, 2, '2017-09-20 16:18:52', NULL);
INSERT INTO `menu` VALUES (27, '角色列表', 'auth', '/role/roleList', 'role:list', 26, NULL, 1, '2017-09-20 16:35:42', NULL);
INSERT INTO `menu` VALUES (28, '角色添加', 'auth', '/role/savaRole', 'role:save', 26, NULL, 2, '2017-09-20 17:16:47', NULL);
INSERT INTO `menu` VALUES (29, '删除角色', 'auth', '/role/delRole', 'role:delete', 26, NULL, 3, '2017-09-20 17:28:57', NULL);
INSERT INTO `menu` VALUES (30, '菜单列表', 'auth', '/role/menuTree', 'role:menutree', 26, NULL, 4, '2017-09-21 15:39:22', NULL);
INSERT INTO `menu` VALUES (31, '角色授权列表', 'auth', '/role/roleDialogPage', 'role:roleDialog', 26, NULL, 5, '2017-09-21 17:18:58', NULL);
INSERT INTO `menu` VALUES (32, '角色授权', 'auth', '/role/grant', 'role:grant', 26, NULL, 6, '2017-09-25 13:39:37', NULL);
INSERT INTO `menu` VALUES (33, '管理员管理', 'menu', '/admin/adminPage', 'admin:index', 1, NULL, 1, '2017-09-27 14:03:11', NULL);
INSERT INTO `menu` VALUES (34, '管理员列表', 'auth', '/admin/adminList', 'admin:list', 33, NULL, 1, '2017-09-27 15:19:40', NULL);
INSERT INTO `menu` VALUES (35, '添加管理员', 'auth', '/admin/saveAdmin', 'admin:save', 33, NULL, 2, '2017-09-29 10:41:59', NULL);
INSERT INTO `menu` VALUES (36, '删除管理员', 'auth', '/admin/delAdmin', 'admin:delete', 33, NULL, 3, '2017-09-29 15:15:23', NULL);
INSERT INTO `menu` VALUES (37, '开源管理', 'menu', '/openSource/opensourcePage', 'openSource:manage', 0, NULL, 1, '2017-09-29 15:48:09', '2017-09-29 15:49:13');
INSERT INTO `menu` VALUES (38, '资源列表', 'menu', '/openSource/openSourcePage', 'openSource:index', 37, NULL, 1, '2017-09-29 15:49:13', NULL);
INSERT INTO `menu` VALUES (39, '标签管理', 'menu', '/lablel/labelPage', 'label:manage', 0, NULL, 0, '2017-10-03 14:52:12', NULL);
INSERT INTO `menu` VALUES (40, '标签列表页', 'menu', '/label/labelPage', 'label:index', 39, NULL, 1, '2017-10-03 14:53:16', NULL);
INSERT INTO `menu` VALUES (41, '标签列表', 'auth', '/label/labelList', 'label:allMenu', 39, NULL, 2, '2017-10-03 14:54:26', NULL);
INSERT INTO `menu` VALUES (42, '保存标签', 'auth', '/label/saveLabel', 'label:save', 39, NULL, 3, '2017-10-03 15:25:23', NULL);
INSERT INTO `menu` VALUES (43, '获取标签', 'auth', '/label/getLabel', 'menu:getLabel', 39, NULL, 4, '2017-10-03 15:31:27', NULL);
INSERT INTO `menu` VALUES (44, '排序标签', 'auth', '/label/updateOrder', 'label:sort', 39, NULL, 5, '2017-10-03 15:34:44', NULL);
INSERT INTO `menu` VALUES (45, '删除标签', 'auth', '/label/delLabel', 'label:delete', 39, NULL, 6, '2017-10-03 15:36:53', NULL);
INSERT INTO `menu` VALUES (46, '用户管理', 'menu', '/user/userPage', 'user:manage', 0, NULL, 1, '2017-10-05 14:29:20', NULL);
INSERT INTO `menu` VALUES (47, '用户列表页', 'menu', '/user/userPage', 'user:index', 46, NULL, 1, '2017-10-05 14:30:17', NULL);

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `REPLY_ID` bigint(255) DEFAULT NULL COMMENT '回复人ID',
  `USER_IDS` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '@回复得人们',
  `CONTENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CREATE_TIME` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '回复表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ROLE_DESC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `STATE` int(11) DEFAULT 1,
  `CREATE_TIME` datetime(0) DEFAULT NULL,
  `UPDATE_TIME` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', '超级管理员', 1, '2017-09-15 14:59:17', NULL);
INSERT INTO `role` VALUES (2, '普通管理员', '普通管理员', 1, '2017-09-21 15:08:36', '2017-09-21 14:14:07');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(20) DEFAULT NULL,
  `MENU_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (40, 2, 1);
INSERT INTO `role_menu` VALUES (41, 2, 33);
INSERT INTO `role_menu` VALUES (42, 2, 34);
INSERT INTO `role_menu` VALUES (43, 1, 1);
INSERT INTO `role_menu` VALUES (44, 1, 2);
INSERT INTO `role_menu` VALUES (45, 1, 3);
INSERT INTO `role_menu` VALUES (46, 1, 4);
INSERT INTO `role_menu` VALUES (47, 1, 6);
INSERT INTO `role_menu` VALUES (48, 1, 7);
INSERT INTO `role_menu` VALUES (49, 1, 14);
INSERT INTO `role_menu` VALUES (50, 1, 26);
INSERT INTO `role_menu` VALUES (51, 1, 27);
INSERT INTO `role_menu` VALUES (52, 1, 28);
INSERT INTO `role_menu` VALUES (53, 1, 29);
INSERT INTO `role_menu` VALUES (54, 1, 30);
INSERT INTO `role_menu` VALUES (55, 1, 31);
INSERT INTO `role_menu` VALUES (56, 1, 32);
INSERT INTO `role_menu` VALUES (57, 1, 33);
INSERT INTO `role_menu` VALUES (58, 1, 34);
INSERT INTO `role_menu` VALUES (59, 1, 35);
INSERT INTO `role_menu` VALUES (60, 1, 36);
INSERT INTO `role_menu` VALUES (61, 1, 37);
INSERT INTO `role_menu` VALUES (62, 1, 38);
INSERT INTO `role_menu` VALUES (63, 1, 39);
INSERT INTO `role_menu` VALUES (64, 1, 40);
INSERT INTO `role_menu` VALUES (65, 1, 41);
INSERT INTO `role_menu` VALUES (66, 1, 42);
INSERT INTO `role_menu` VALUES (67, 1, 43);
INSERT INTO `role_menu` VALUES (68, 1, 44);
INSERT INTO `role_menu` VALUES (69, 1, 45);
INSERT INTO `role_menu` VALUES (70, 1, 46);
INSERT INTO `role_menu` VALUES (71, 1, 47);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SEX` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BIRTHDAY` date DEFAULT NULL,
  `EMAIL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PHONE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BRIEF` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '简介',
  `HEAD_IMG_URL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `STATE` int(1) DEFAULT NULL COMMENT '状态',
  `CREATE_TIME` datetime(0) DEFAULT NULL,
  `UPDATE_TIME` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
