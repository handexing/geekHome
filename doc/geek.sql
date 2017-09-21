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

 Date: 21/09/2017 17:30:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STATE` int(1) NULL DEFAULT NULL,
  `IS_SYSTEM` int(1) NULL DEFAULT 0,
  `SALT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'c5941c5f3bc693a75e6e863bd2c55ce3', 1, 0, '1ab6d62faa91ae7deec76d6f13ef1600', '2017-09-14 18:06:21', NULL);

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `ADMIN_ID` bigint(20) NULL DEFAULT NULL,
  `ROLE_ID` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理員角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (1, 1);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `TYPE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `URL` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求路径',
  `CODE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `PARENT_ID` bigint(20) NULL DEFAULT NULL COMMENT '父ID',
  `PARENT_IDS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父IDS',
  `SORT` int(11) NULL DEFAULT 0 COMMENT '顺序',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜單表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '系统管理', 'menu', '/system/systemPage', 'system:admin', 0, '', 1, '2017-09-15 10:20:26', NULL);
INSERT INTO `menu` VALUES (2, '菜单管理', 'auth', '/menu/menuPage', 'menu:index', 1, NULL, 1, '2017-09-15 11:14:01', NULL);
INSERT INTO `menu` VALUES (3, '修改排序', 'auth', '/menu/updateOrder', 'menu:sort', 2, NULL, 2, '2017-09-15 14:56:28', NULL);
INSERT INTO `menu` VALUES (4, '保存资源菜单', 'auth', '/menu/saveMenu', 'menu:save', 2, NULL, 3, '2017-09-19 15:43:09', NULL);
INSERT INTO `menu` VALUES (6, '获取资源', 'auth', '/menu/getMenu', 'menu:getMenu', 2, NULL, 4, '2017-09-19 16:50:46', NULL);
INSERT INTO `menu` VALUES (7, '资源列表', 'auth', '/menu/menuList', 'menu:allMenu', 2, NULL, 5, '2017-09-19 20:56:08', NULL);
INSERT INTO `menu` VALUES (14, '删除资源', 'auth', '/menu/delMenu', 'menu:delete', 2, NULL, 6, '2017-09-20 14:40:25', NULL);
INSERT INTO `menu` VALUES (26, '角色管理', 'auth', '/role/rolePage', 'role:index', 1, NULL, 1, '2017-09-20 16:18:52', NULL);
INSERT INTO `menu` VALUES (27, '角色列表', 'auth', '/role/roleList', 'role:list', 26, NULL, 1, '2017-09-20 16:35:42', NULL);
INSERT INTO `menu` VALUES (28, '角色添加', 'auth', '/role/savaRole', 'role:save', 26, NULL, 2, '2017-09-20 17:16:47', NULL);
INSERT INTO `menu` VALUES (29, '删除角色', 'auth', '/role/delRole', 'role:delete', 26, NULL, 3, '2017-09-20 17:28:57', NULL);
INSERT INTO `menu` VALUES (30, '菜单列表', 'auth', '/role/menuTree', 'role:menutree', 26, NULL, 4, '2017-09-21 15:39:22', NULL);
INSERT INTO `menu` VALUES (31, '角色菜单对话框', 'anth', '/role/roleDialogPage', 'role:roleDialog', 26, NULL, 5, '2017-09-21 17:18:58', NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ROLE_DESC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STATE` int(11) NULL DEFAULT 1,
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL,
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

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
  `ROLE_ID` bigint(20) NULL DEFAULT NULL,
  `MENU_ID` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 1, 1);
INSERT INTO `role_menu` VALUES (2, 1, 2);
INSERT INTO `role_menu` VALUES (3, 1, 3);
INSERT INTO `role_menu` VALUES (4, 1, 4);
INSERT INTO `role_menu` VALUES (5, 1, 6);
INSERT INTO `role_menu` VALUES (6, 1, 7);
INSERT INTO `role_menu` VALUES (7, 1, 14);
INSERT INTO `role_menu` VALUES (8, 1, 26);
INSERT INTO `role_menu` VALUES (9, 1, 27);
INSERT INTO `role_menu` VALUES (10, 1, 28);
INSERT INTO `role_menu` VALUES (11, 1, 29);
INSERT INTO `role_menu` VALUES (12, 1, 30);
INSERT INTO `role_menu` VALUES (13, 1, 31);

SET FOREIGN_KEY_CHECKS = 1;
