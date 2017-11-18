/*
 Navicat Premium Data Transfer

 Source Server         : geekHome
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : rm-uf6bw093xs11h6m1qo.mysql.rds.aliyuncs.com:3306
 Source Schema         : geek_dev

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 18/11/2017 14:39:39
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
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SYSTEM_TYPE_ID` bigint(20) DEFAULT NULL COMMENT '系统类型',
  `TYPE_ID` bigint(20) DEFAULT NULL COMMENT '标签id',
  `TITLE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标题',
  `SUBTITLE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '副标题',
  `CONTENT` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '内容',
  `STATUS` int(1) DEFAULT 3 COMMENT '状态',
  `COLLECT_COUNT` int(255) DEFAULT 0 COMMENT '收藏数量',
  `BROWSE_COUNT` int(255) DEFAULT 0 COMMENT '浏览数量',
  `BANNER_IMG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'banner图片',
  `CREATE_TIME` datetime(0) DEFAULT NULL,
  `UPDATE_TIME` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'blog表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (4, 65, 58, '士大夫士大夫石帆胜丰沙发上都很经济法规', '是公司公司的给我人头问题问题让微软提供v不DVD发v地方v的v', '的vDVD v发的vDVD沟通即可', 3, 0, 3, NULL, '2017-11-02 09:42:53', NULL);
INSERT INTO `blog` VALUES (5, 66, 58, '的事发生的事故发生的发生发射点发生发生', '十分士大夫士大夫士大夫大师傅士大夫十分', '发射点发顺丰发顺丰的似的发射点发生发放发', 3, 0, 2, NULL, '2017-11-02 09:43:18', NULL);
INSERT INTO `blog` VALUES (6, 67, 58, '撒旦似的发到公司发噶色发射点发是的发放', '阿斯顿发射点发大水发射点发顺丰大苏打', '啊打发发射点范德萨打发法色发射点发', 3, 0, 2, NULL, '2017-11-02 09:43:38', NULL);
INSERT INTO `blog` VALUES (7, 68, 58, 'sdfsfsfdsfsd', 'sdfsdfsdfsd', 'fsdfsdgffdgsdfgsdfg', 3, 0, 9, NULL, '2017-11-02 09:49:31', NULL);
INSERT INTO `blog` VALUES (8, 69, 59, 'this is test！！', '个手动阀手动阀手动阀', ':relaxed: :smirk: :kissing_heart: :flushed: :pensive: :yum: :grimacing: :frowning: :joy:\n\n### 前言\n> 这是一个测试blog！！', 3, 0, 1, NULL, '2017-11-03 11:14:23', NULL);
INSERT INTO `blog` VALUES (9, 71, 60, '前端呀前端', '前端呀前端前端呀前端前端呀前端前端呀前端前端呀前端前端呀前端前端呀前端', ':grin: :kissing_closed_eyes: :relaxed:\n\n### 前端\n> 这是前端', 3, 0, 2, NULL, '2017-11-03 11:18:08', NULL);
INSERT INTO `blog` VALUES (10, 70, 58, '极客屋需求文档', '极客屋是极客们的社区。在这里大家能够多多分享自己正在做的有趣事物、最近的学习、交流想法，在这里找到朋友认识更多有趣的人。极客屋致力于打造一流开源技术社区！', '# 极客屋需求文档\n\n## 说明\n> `极客屋`是极客们的社区。在这里大家能够多多分享自己正在做的有趣事物、最近的学习、交流想法，在这里找到朋友认识更多有趣的人。极客屋致力于打造一流开源技术社区！\n\n## 极客屋功能模块\n> 目前大致分为以下几种模块，后期如有好的idea在添加。\n\n1. 首页\n2. 开源\n3. 博客\n4. 问与答\n5. 标签节点\n\n### 1. 首页展示\n> 首页包含以下几种功能模块，后期可能扩展更多：\n\n1.1 滚动栏\n1.2 热搜标签\n1.3 热门文章\n1.4 问与答\n1.5 今日热议\n1.6 专题学习\n1.7 开源排行\n\n#### 1.1 滚动栏\n主要用于显示特别好玩得`开源项目`，`有趣得专题`,`热门文章`等。\n\n#### 1.2 热搜标签\n用于显示用户查看最多得`30`个标签，点击标签可以直接查询相关内容，如：开源项目，问与答，博客文章等。\n\n#### 1.3 热门文章\n用于显示近期被收藏/浏览/评论数量最多得`8`篇文章。【加载更多】按钮点击跳转到博客文章列表页面。根据不用分类查询文章。根据用户技术选择推荐文章。文章可被分享到其他平台。\n\n#### 1.4 问与答\n用于显示最近提问的`6`条数据。点击条目可进入问题描述详情页。用户讨论信息带分页效果。\n\n#### 1.5 今日热议\n显示今天讨论最激烈得`10`条数据。根据浏览数量和评论数量`排名显示`。点击条目可进入问题描述详情页。用户讨论信息带分页效果。\n\n#### 1.6 专题学习\n用于显示`12`条被收藏最多得专题栏目。点击进去可查看专题详情。\n\n#### 1.7 开源排行\n用于展示用户近期投得最有趣得`10`个开源项目。点击进入开源详情页面。\n\n### 2. 开源\n> 用于显示不同分类下的开源项目，帮助用户`推广`和`发现`更多有趣得项目。包含以下功能模块：\n\n2.1 开源类别的获取\n2.2 切换类别tab，动态数据，列表分页。\n2.3 本类推荐\n2.4 点击查看开源详情页\n2.5 评论回复，收藏，分享功能\n\n#### 2.1 开源类别的获取\n动态获取开源类别，组装tab显示。点击`更多`tab按钮，可查看更多开源分类信息。\n\n#### 2.2 切换类别tab，动态数据，列表分页。\n点击tab选项动态获取开源列表信息分页展示。\n\n#### 2.3 本类推荐\n每个开源类别下推荐`4`个开源项目。根据`收藏数量`。\n\n#### 2.4 点击查看开源详情页\n点击查看开源详情信息。\n\n#### 2.5 评论回复，收藏功能\n在开源项目详情页可以评论回复讨论，并且用户可收藏，分享该项目。\n\n\n\n\n\n\n\n', 3, 0, 14, NULL, '2017-11-08 10:37:14', NULL);
INSERT INTO `blog` VALUES (11, 64, 59, 'android四大组件详细介绍', 'Android从入门到放弃！！！', ':flushed:android四大组件详细介绍', 3, 0, 3, NULL, '2017-11-08 14:50:03', NULL);
INSERT INTO `blog` VALUES (12, 64, 59, 'Android webview详解', 'Android webview详解', 'Android webview详解', 3, 0, 4, NULL, '2017-11-08 14:53:48', NULL);
INSERT INTO `blog` VALUES (13, 68, 61, '啊打发撒旦', 'a\'d\'s\'fa\'s\'fa', ' 啊多发点发达省份', 3, 0, 1, NULL, '2017-11-13 13:37:04', NULL);
INSERT INTO `blog` VALUES (14, 68, 62, '啊地方撒撒旦', '啊打发士大夫', '啊打发阿斯顿', 3, 0, 2, NULL, '2017-11-13 13:37:12', NULL);
INSERT INTO `blog` VALUES (15, 67, 74, '了空间环境官方都是', '；林肯郡海关法的', '将用户提供范德萨', 3, 0, 1, NULL, '2017-11-13 13:37:22', NULL);
INSERT INTO `blog` VALUES (16, 65, 60, '发计划是否过大', '啊是v多个温柔我给微软', '啊v范德萨给发嘎嘎', 3, 0, 1, NULL, '2017-11-13 13:37:33', NULL);
INSERT INTO `blog` VALUES (17, 67, 60, '还是广东分公司', '撒旦发个顺丰的', '师傅给施工方的', 3, 0, 2, NULL, '2017-11-13 13:37:43', NULL);
INSERT INTO `blog` VALUES (18, 67, 60, '啊打发手动阀撒旦', '啊手动阀实打实', '啊大苏打发', 3, 0, 4, NULL, '2017-11-13 13:37:53', NULL);
INSERT INTO `blog` VALUES (19, 66, 60, '去日期额外人去', '服务器热舞', '日为其人情味vv', 3, 0, 5, NULL, '2017-11-13 13:38:20', NULL);

-- ----------------------------
-- Table structure for blog_type
-- ----------------------------
DROP TABLE IF EXISTS `blog_type`;
CREATE TABLE `blog_type`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `STATUS` int(1) DEFAULT 1,
  `USER_ID` bigint(20) DEFAULT NULL COMMENT '用户ID，主要用于博客类别',
  `CREATE_TIME` datetime(0) DEFAULT NULL,
  `UPDATE_TIME` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户blog类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_type
-- ----------------------------
INSERT INTO `blog_type` VALUES (58, 'spring', 1, 5, '2017-11-01 15:59:08', NULL);
INSERT INTO `blog_type` VALUES (59, 'java', 1, 5, '2017-11-01 15:59:28', NULL);
INSERT INTO `blog_type` VALUES (60, '前端', 1, 5, '2017-11-01 16:00:11', NULL);
INSERT INTO `blog_type` VALUES (61, '数据库', 1, 5, '2017-11-01 16:00:41', NULL);
INSERT INTO `blog_type` VALUES (62, 'test', 1, 5, '2017-11-07 10:40:48', NULL);
INSERT INTO `blog_type` VALUES (63, 'Java', 1, 24, '2017-11-07 11:17:03', NULL);
INSERT INTO `blog_type` VALUES (64, '移动开发', 1, 0, '2017-11-08 14:35:50', NULL);
INSERT INTO `blog_type` VALUES (65, '架构', 1, 0, '2017-11-08 14:35:52', NULL);
INSERT INTO `blog_type` VALUES (66, '云计算/大数据', 1, 0, '2017-11-08 14:35:54', NULL);
INSERT INTO `blog_type` VALUES (67, '互联网', 1, 0, '2017-11-08 14:35:57', NULL);
INSERT INTO `blog_type` VALUES (68, '运维', 1, 0, '2017-11-08 14:35:59', NULL);
INSERT INTO `blog_type` VALUES (69, '数据库', 1, 0, '2017-11-08 14:36:02', NULL);
INSERT INTO `blog_type` VALUES (70, '前端', 1, 0, '2017-11-08 14:36:04', NULL);
INSERT INTO `blog_type` VALUES (71, '编程语言', 1, 0, '2017-11-08 14:36:07', NULL);
INSERT INTO `blog_type` VALUES (72, '研发管理', 1, 0, '2017-11-08 14:36:09', NULL);
INSERT INTO `blog_type` VALUES (73, '综合', 1, 0, '2017-11-08 14:36:12', NULL);
INSERT INTO `blog_type` VALUES (74, '哈哈', 1, 5, '2017-11-09 10:23:51', NULL);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) DEFAULT NULL,
  `THEME_ID` bigint(20) DEFAULT NULL COMMENT '主题ID，问与答，开源，博客',
  `CONTENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `TYPE` int(1) DEFAULT NULL COMMENT '评论类型：1-开源 2-问与答 3-博客',
  `CREATE_TIME` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 6, 1, '哇塞这么神奇!', 2, '2017-10-17 14:25:04');
INSERT INTO `comment` VALUES (2, 6, 23, '哇塞这么神奇!', 2, '2017-10-25 20:42:24');
INSERT INTO `comment` VALUES (3, 7, 23, ':relieved: :kissing_closed_eyes: :heart_eyes: :smirk: :smiley:', 2, '2017-10-25 20:47:19');
INSERT INTO `comment` VALUES (4, 8, 23, ':dizzy_face: :mask: :yum: :rage: :tired_face:', 2, '2017-10-25 20:47:22');
INSERT INTO `comment` VALUES (5, 9, 23, ':dash: :zzz: :yellow_heart: :alien: :cry: :sob:', 2, '2017-10-25 20:47:24');
INSERT INTO `comment` VALUES (6, 10, 23, ':pray: :shit: :poop: :sparkling_heart: :star2:', 2, '2017-10-25 20:47:26');
INSERT INTO `comment` VALUES (7, 11, 23, ':bowtie: :smile: :laughing: :blush: :smiley: :relaxed: :smirk:', 2, '2017-10-25 20:47:30');
INSERT INTO `comment` VALUES (8, 12, 23, ':stuck_out_tongue: :sleeping: :worried: :frowning: :anguished: :open_mouth: :grimacing: :confused: :hushed:', 2, '2017-10-25 20:47:32');
INSERT INTO `comment` VALUES (9, 13, 23, ':tw-1f170: :tw-1f17e: :tw-1f17f: :tw-1f191: :tw-1f192: :tw-1f0cf: :tw-1f004:', 2, '2017-10-25 20:47:34');
INSERT INTO `comment` VALUES (10, 14, 23, ':tw-1f30c: :tw-1f236: :tw-1f237: :tw-1f238: :tw-1f23a: :tw-1f250: :tw-1f300: :tw-1f302: :tw-1f305:', 2, '2017-10-25 20:47:37');
INSERT INTO `comment` VALUES (11, 15, 23, ':tw-1f46a: :tw-1f46b: :tw-1f46c: :tw-1f46d: :tw-1f46f: :tw-1f470: :tw-1f471: :tw-1f472: :tw-1f473: :tw-1f474: :tw-1f475: :tw-1f476: :tw-1f477: :tw-1f478:', 2, '2017-10-25 20:47:40');
INSERT INTO `comment` VALUES (12, 16, 23, ':tw-1f46a: :tw-1f46b: :tw-1f46c: :tw-1f46d: :tw-1f46f: :tw-1f470: :tw-1f471: :tw-1f472: :tw-1f473: :tw-1f474: :tw-1f475: :tw-1f476: :tw-1f477: :tw-1f478:', 2, '2017-10-25 20:30:15');
INSERT INTO `comment` VALUES (13, 5, 15, ':satisfied:这是一个测试', 2, '2017-10-25 21:54:29');
INSERT INTO `comment` VALUES (14, 5, 15, ':tw-1f435:这又是一个测试！！', 2, '2017-10-25 21:55:32');
INSERT INTO `comment` VALUES (15, 5, 15, ':joy:这还是一个测试。。。', 2, '2017-10-25 21:57:16');
INSERT INTO `comment` VALUES (16, 5, 7, '测试', 2, '2017-10-26 13:36:04');
INSERT INTO `comment` VALUES (17, 5, 7, ':flushed:', 2, '2017-10-26 13:40:47');
INSERT INTO `comment` VALUES (18, 5, 23, ':confused:', 2, '2017-10-26 13:42:13');
INSERT INTO `comment` VALUES (19, 5, 24, ':laughing:还没开发完', 2, '2017-10-26 14:58:23');
INSERT INTO `comment` VALUES (20, 5, 12, ':smirk:什么鬼', 2, '2017-10-26 16:52:47');
INSERT INTO `comment` VALUES (21, 5, 16, ':laughing:', 2, '2017-10-26 17:08:03');
INSERT INTO `comment` VALUES (22, 5, 11, ':laughing:', 2, '2017-10-26 22:07:28');
INSERT INTO `comment` VALUES (23, 5, 11, ':sob:', 2, '2017-10-26 22:07:46');
INSERT INTO `comment` VALUES (24, 5, 25, ':satisfied:老铁，厉害了', 2, '2017-10-30 09:39:30');
INSERT INTO `comment` VALUES (25, 5, 1, '@Inbox fdgdgd gfdg 韩:relaxed:', 2, '2017-11-03 16:17:48');
INSERT INTO `comment` VALUES (26, 5, 12, '@handx', 2, '2017-11-03 16:28:09');
INSERT INTO `comment` VALUES (27, 5, 4, '法撒旦发发达的说法', 3, '2017-11-08 13:37:02');
INSERT INTO `comment` VALUES (28, 5, 10, 'test', 3, '2017-11-08 13:55:59');
INSERT INTO `comment` VALUES (29, 5, 10, '啊但是发射点', 3, '2017-11-08 13:56:51');
INSERT INTO `comment` VALUES (30, 5, 10, ':satisfied:### test', 3, '2017-11-08 13:57:43');
INSERT INTO `comment` VALUES (31, 5, 15, '@sdfs @sdfs @sdfs ', 2, '2017-11-13 17:26:06');
INSERT INTO `comment` VALUES (32, 5, 18, '啊手动阀手动阀', 3, '2017-11-16 14:49:49');

-- ----------------------------
-- Table structure for comment_reply
-- ----------------------------
DROP TABLE IF EXISTS `comment_reply`;
CREATE TABLE `comment_reply`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `THEME_ID` bigint(20) DEFAULT NULL,
  `REPLY_USER_ID` bigint(20) DEFAULT NULL COMMENT '@回复得人id',
  `USER_ID` bigint(20) DEFAULT NULL COMMENT '用户id',
  `CONTENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `TYPE` int(1) DEFAULT NULL COMMENT '评论类型：1-开源 2-问与答 3-博客',
  `CREATE_TIME` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '回复表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment_reply
-- ----------------------------
INSERT INTO `comment_reply` VALUES (1, 23, 6, 5, '是啊，就是这么神奇！', 2, '2017-10-17 14:27:13');
INSERT INTO `comment_reply` VALUES (2, 23, 8, 5, 'gdgsdfgs', 2, '2017-11-14 10:07:49');

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LABLE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PARENT_ID` bigint(20) DEFAULT 0,
  `SORT` int(255) DEFAULT NULL,
  `STATUS` int(1) DEFAULT 1,
  `TYPE` int(1) DEFAULT NULL COMMENT '1：标签 2：开源 3：问与答 4：博客',
  `CREATE_TIME` datetime(0) DEFAULT NULL,
  `UPDATE_TIME` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of label
-- ----------------------------
INSERT INTO `label` VALUES (8, '创意', 0, 1, 1, 3, '2017-10-11 10:39:16', NULL);
INSERT INTO `label` VALUES (9, '分享创造', 8, 1, 1, 3, '2017-10-11 10:39:49', NULL);
INSERT INTO `label` VALUES (10, '设计', 8, 1, 1, 3, '2017-10-11 10:40:19', NULL);
INSERT INTO `label` VALUES (11, '奇思妙想', 8, 1, 1, 3, '2017-10-11 10:40:23', NULL);
INSERT INTO `label` VALUES (12, '问与答', 0, 2, 1, 3, '2017-10-11 10:43:15', NULL);
INSERT INTO `label` VALUES (13, '技术', 0, 3, 1, 3, '2017-10-11 10:43:18', NULL);
INSERT INTO `label` VALUES (14, '好玩', 0, 4, 1, 3, '2017-10-11 10:43:21', NULL);
INSERT INTO `label` VALUES (15, '吐槽', 0, 5, 1, 3, '2017-10-11 10:43:23', NULL);
INSERT INTO `label` VALUES (16, '酷工作', 0, 6, 1, 3, '2017-10-11 10:43:25', NULL);
INSERT INTO `label` VALUES (17, '城市', 0, 7, 1, 3, '2017-10-11 10:43:28', NULL);
INSERT INTO `label` VALUES (18, '交易', 0, 8, 1, 3, '2017-10-11 10:43:30', NULL);
INSERT INTO `label` VALUES (20, '问与答', 12, 1, 1, 3, '2017-10-11 10:49:52', NULL);
INSERT INTO `label` VALUES (21, '程序员', 13, 1, 1, 3, '2017-10-11 10:49:54', NULL);
INSERT INTO `label` VALUES (22, 'python', 13, 2, 1, 3, '2017-10-11 10:49:59', NULL);
INSERT INTO `label` VALUES (23, 'android', 13, 3, 1, 3, '2017-10-11 10:50:15', NULL);
INSERT INTO `label` VALUES (24, 'Linux', 13, 4, 1, 3, '2017-10-11 10:50:18', NULL);
INSERT INTO `label` VALUES (25, '分享发现', 14, 1, 1, 3, '2017-10-11 10:50:20', NULL);
INSERT INTO `label` VALUES (26, '电子游戏', 14, 2, 1, 3, '2017-10-11 10:50:23', NULL);
INSERT INTO `label` VALUES (27, '电影', 14, 3, 1, 3, '2017-10-11 10:50:25', NULL);
INSERT INTO `label` VALUES (28, '旅游', 14, 4, 1, 3, '2017-10-11 10:50:30', NULL);
INSERT INTO `label` VALUES (29, '吐槽', 15, 1, 1, 3, '2017-10-11 10:50:33', NULL);
INSERT INTO `label` VALUES (30, '酷工作', 16, 1, 1, 3, '2017-10-11 10:50:35', NULL);
INSERT INTO `label` VALUES (31, '求职', 16, 2, 1, 3, '2017-10-11 10:50:38', NULL);
INSERT INTO `label` VALUES (32, '职场话题', 16, 3, 1, 3, '2017-10-11 10:50:41', NULL);
INSERT INTO `label` VALUES (33, '外包', 16, 4, 1, 3, '2017-10-11 10:50:43', NULL);
INSERT INTO `label` VALUES (34, '上海', 17, 1, 1, 3, '2017-10-11 10:50:46', NULL);
INSERT INTO `label` VALUES (35, '北京', 17, 2, 1, 3, '2017-10-11 10:50:50', NULL);
INSERT INTO `label` VALUES (36, '深圳', 17, 3, 1, 3, '2017-10-11 10:50:53', NULL);
INSERT INTO `label` VALUES (37, '成都', 17, 4, 1, 3, '2017-10-11 10:50:57', NULL);
INSERT INTO `label` VALUES (38, '杭州', 17, 5, 1, 3, '2017-10-11 10:51:00', NULL);
INSERT INTO `label` VALUES (39, '二手交易', 18, 1, 1, 3, '2017-10-11 10:51:03', NULL);
INSERT INTO `label` VALUES (40, '物换物', 18, 2, 1, 3, '2017-10-11 10:51:06', NULL);
INSERT INTO `label` VALUES (41, '免费赠送', 18, 3, 1, 3, '2017-10-11 10:51:09', NULL);
INSERT INTO `label` VALUES (42, '团购', 18, 4, 1, 3, '2017-10-11 10:51:12', NULL);
INSERT INTO `label` VALUES (46, 'Java', 0, 1, 1, 1, '2017-11-01 13:28:44', '2017-11-01 13:29:51');
INSERT INTO `label` VALUES (47, '线程', 46, 1, 1, 1, '2017-11-01 13:42:21', NULL);
INSERT INTO `label` VALUES (48, 'java', 0, 1, 1, 2, '2017-11-01 15:03:43', NULL);
INSERT INTO `label` VALUES (49, 'python', 0, 2, 1, 2, '2017-11-01 15:03:51', NULL);
INSERT INTO `label` VALUES (50, 'javascript', 0, 3, 1, 2, '2017-11-01 15:04:01', NULL);
INSERT INTO `label` VALUES (51, 'C#', 0, 4, 1, 2, '2017-11-01 15:04:08', NULL);
INSERT INTO `label` VALUES (52, 'ruby', 0, 5, 1, 2, '2017-11-01 15:04:15', NULL);
INSERT INTO `label` VALUES (53, 'go', 0, 6, 1, 2, '2017-11-01 15:04:20', NULL);
INSERT INTO `label` VALUES (54, 'php', 0, 7, 1, 2, '2017-11-01 15:04:26', NULL);
INSERT INTO `label` VALUES (55, 'node-js', 0, 8, 1, 2, '2017-11-01 15:04:35', '2017-11-01 15:04:44');
INSERT INTO `label` VALUES (56, 'angular', 0, 9, 1, 2, '2017-11-01 15:04:56', NULL);
INSERT INTO `label` VALUES (57, 'shell', 0, 10, 1, 2, '2017-11-01 15:05:01', NULL);

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
-- Table structure for open_source_content
-- ----------------------------
DROP TABLE IF EXISTS `open_source_content`;
CREATE TABLE `open_source_content`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LABEL_ID` bigint(20) DEFAULT NULL COMMENT '标签id',
  `TITLE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标题',
  `SUBTITLE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '副标题',
  `CONTENT` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '内容',
  `STATUS` int(1) DEFAULT 1 COMMENT '状态',
  `COLLECT_COUNT` int(255) DEFAULT 0 COMMENT '收藏数量',
  `BROWSE_COUNT` int(255) DEFAULT 0 COMMENT '浏览数量',
  `BANNER_IMG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'banner图片',
  `CREATE_TIME` datetime(0) DEFAULT NULL,
  `UPDATE_TIME` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '开源内容表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question_answers
-- ----------------------------
DROP TABLE IF EXISTS `question_answers`;
CREATE TABLE `question_answers`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) DEFAULT NULL COMMENT '用户id',
  `LABEL_ID` bigint(20) DEFAULT NULL COMMENT '标签id',
  `TITLE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标题',
  `CONTENT` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '内容',
  `STATUS` int(1) DEFAULT NULL COMMENT '状态',
  `COLLECT_COUNT` int(255) DEFAULT 0 COMMENT '收藏数量',
  `BROWSE_COUNT` int(255) DEFAULT 0 COMMENT '浏览数量',
  `CREATE_TIME` datetime(0) DEFAULT NULL,
  `UPDATE_TIME` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问与答表Q & A' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question_answers
-- ----------------------------
INSERT INTO `question_answers` VALUES (1, 5, 8, '手动阀手动阀', 'SDF速度SAD撒旦:relaxed: :sweat_smile: :pensive: :confounded:2017-10-16 10:41:49 星期一', 2, 0, 20, '2017-10-27 10:41:52', NULL);
INSERT INTO `question_answers` VALUES (2, 5, 8, '一个关于“转发旧号码未接来电、短信”的解决方案', '啊多发点大师傅士大夫', 2, 0, 2, '2017-10-27 10:44:09', NULL);
INSERT INTO `question_answers` VALUES (3, 5, 8, '的发射点发的发多少', '啊多发点是否法撒旦发啊多发点是否', 2, 0, 0, '2017-10-27 10:47:40', NULL);
INSERT INTO `question_answers` VALUES (4, 5, 8, '发射点发的时间和海关扣了', '立刻脚后跟范德萨OIUYTREWQ', 2, 0, 0, '2017-10-27 10:47:55', NULL);
INSERT INTO `question_answers` VALUES (5, 5, 8, '又山寨了一个传统颜色的配色网站', '.,MNBVCXZZ\';LKJHGFDSA看脚后跟范德萨', 2, 0, 2, '2017-10-27 10:48:12', NULL);
INSERT INTO `question_answers` VALUES (6, 5, 8, '哦iu一头热完全离开建行股份的美女不v', '会让你人沟通拜访v的撒而更广泛的v新政策执行所发生的', 2, 0, 1, '2017-10-27 10:48:42', NULL);
INSERT INTO `question_answers` VALUES (7, 5, 8, '实打实大师大的', ':expressionless: :expressionless: :expressionless:', 2, 0, 27, '2017-10-27 17:17:37', NULL);
INSERT INTO `question_answers` VALUES (8, 6, 8, '还记得简悦吗？ 1.0.4 版带来了可支持任意页面生成阅读模式', 'adsfads', 2, 0, 6, '2017-10-27 17:36:40', NULL);
INSERT INTO `question_answers` VALUES (9, 7, 8, '写了一个交互式的 ssh 批量操作客户端，支持根据服务端的执行文件和路径自动补全，支持批量上传下载文件', 's', 2, 0, 4, '2017-10-27 17:36:44', NULL);
INSERT INTO `question_answers` VALUES (10, 8, 8, '写了一个 Lemon 语言到 Objective-C 的绑定，可以用 Lemon 语言写 Mac 和 iOS 了。', 's', 2, 0, 2, '2017-10-27 17:36:48', NULL);
INSERT INTO `question_answers` VALUES (11, 9, 8, '如果你有不愿公开的点子的话，关于这些点子你有哪些愿意公开的信息？', 's', 2, 0, 19, '2017-10-27 17:36:52', NULL);
INSERT INTO `question_answers` VALUES (12, 10, 8, '女士时装订阅类平台，寻找技术大牛一起创业', 'ss', 2, 0, 26, '2017-10-27 17:36:55', NULL);
INSERT INTO `question_answers` VALUES (13, 11, 8, '在 Nodeclub 基础上仿花瓣的一个小项目, 有兴趣的小伙伴搞起', 's', 2, 0, 5, '2017-10-27 17:36:59', NULL);
INSERT INTO `question_answers` VALUES (14, 5, 8, '业余项目，收集了很多优秀开发者的博客,微博...', 'this is test', 2, 0, 13, '2017-10-27 09:45:00', NULL);
INSERT INTO `question_answers` VALUES (15, 5, 9, '最帅网易云音乐播放器 ieaseMusic 更新 1.0.0 正式版，增加高品质音乐，聚合 QQ 音乐，酷狗音乐解决版权问题导致的灰色死链。', 'ahahahahaha', 2, 0, 55, '2017-10-27 09:53:29', NULL);
INSERT INTO `question_answers` VALUES (16, 5, 20, '我开发的开源 JS 地图引擎 maptalks.js, 请大家不吝赐教!', 'fadfadf', 2, 0, 3, '2017-10-27 10:44:06', NULL);
INSERT INTO `question_answers` VALUES (17, 5, 21, '程序员程序员', '程序员程序员程序员程序员程序员程序员程序员程序员', 2, 0, 2, '2017-10-27 14:34:54', NULL);
INSERT INTO `question_answers` VALUES (18, 5, 26, '王者农药真的那么好玩吗？', '王者农药真的那么好玩吗？王者农药真的那么好玩吗？王者农药真的那么好玩吗？王者农药真的那么好玩吗？', 2, 0, 3, '2017-10-27 14:35:21', NULL);
INSERT INTO `question_answers` VALUES (19, 5, 29, '网好卡啊啊啊~~~~', '网好卡啊啊啊~~~~网好卡啊啊啊~~~~', 2, 0, 0, '2017-10-27 14:35:47', NULL);
INSERT INTO `question_answers` VALUES (20, 5, 31, '实习求职', '实习求职实习求职实习求职', 2, 0, 0, '2017-10-27 14:36:17', NULL);
INSERT INTO `question_answers` VALUES (21, 5, 34, '有毒有毒', '有毒有毒', 2, 0, 5, '2017-10-27 14:36:32', NULL);
INSERT INTO `question_answers` VALUES (22, 5, 39, '自行车', '自行车自行车自行车', 2, 0, 1, '2017-10-27 14:37:08', NULL);
INSERT INTO `question_answers` VALUES (23, 5, 11, '如果空气算是一种慢性毒药，杀死人需要75/100年....', '#### 如果空气算是一种慢性毒药，杀死人需要75/100年....\n:satisfied: :satisfied:什么鬼\n2017-10-24 17:07:58 星期二\n\n------------\n\n#### 极客屋上线了\n哈哈哈哈哈~:relaxed: :relaxed: :relaxed:', 2, 0, 243, '2017-10-27 20:08:44', NULL);
INSERT INTO `question_answers` VALUES (24, 17, 20, '还不能改个人设置？', '默认头像真的好丑  \n\n注册完账号第一件事就是想去改资料', 2, 0, 22, '2017-10-27 22:35:16', NULL);
INSERT INTO `question_answers` VALUES (25, 20, 25, '发现落事网社区', '落事网www.luosh.com', 3, 0, 10, '2017-10-27 23:17:56', NULL);
INSERT INTO `question_answers` VALUES (26, 5, 9, '极客屋问与答支持图片上传预览了~', '![](http://39.106.56.107/images/1509503090634.jpg)\n\n', 3, 0, 5, '2017-11-01 10:25:40', NULL);

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
  `SEX` int(1) DEFAULT NULL,
  `BIRTHDAY` date DEFAULT NULL,
  `EMAIL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PHONE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BRIEF` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '简介',
  `HEAD_IMG_URL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `STATE` int(1) DEFAULT 1 COMMENT '状态',
  `CREATE_TIME` datetime(0) DEFAULT NULL,
  `UPDATE_TIME` datetime(0) DEFAULT NULL,
  `COMPANY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ADDRESS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `GIT_HUB_URL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `WEB_SITE_URL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (5, 'handx', 0, NULL, '908716835@qq.com', '15618243357', '0d6dac1b11e346b17c19ecbf49ae6c7b', '爱生活，爱编程。', 'img/1.png', 1, '2017-10-09 15:23:40', '2017-11-03 14:56:07', '阿里巴巴', '上海', 'handexing', 'www.geekuser.cn');
INSERT INTO `user` VALUES (6, 'jack', 0, NULL, '908716836@qq.com', NULL, '0d6dac1b11e346b17c19ecbf49ae6c7b', '用户很懒，什么都没说明....', 'img/2.png', 1, '2017-10-17 14:23:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (7, 'yannis', 0, NULL, 'p_y2020@163.com', '17626972358', 'b4fdf01936b27912da30f16367697bd0', '大家都没错。', 'img/3.png', 1, '2017-10-19 17:27:26', '2017-11-05 19:27:39', '华为南京研究所', '江苏省南京市', 'git', 'www.google.com');
INSERT INTO `user` VALUES (8, 'toono', 0, NULL, 'p_y2020@163.com', NULL, '8e0878027cc03f151eb2039441a625c6', '用户很懒，什么都没说明....', 'img/4.png', 1, '2017-10-19 17:28:08', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (9, 'Omooo', 0, NULL, '869759698@qq.com', NULL, '900a44073e14f0cf721821dbfc5a3a5e', '用户很懒，什么都没说明....', 'img/4.png', 1, '2017-10-19 23:07:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (10, 'pinklay', 0, NULL, NULL, NULL, '1c8ec6fcaedbee45fa59a06b786fae6a', '用户很懒，什么都没说明....', 'img/5.png', 1, '2017-10-20 16:33:09', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (11, 'mune', 0, NULL, NULL, NULL, '3d4d87c7770a9d26cf1c7a2d18fcd3b3', '用户很懒，什么都没说明....', 'img/6.png', 1, '2017-10-20 16:47:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (12, 'darluc', 0, NULL, NULL, NULL, 'de4c3685de872c458429c8fc4b206f3a', '用户很懒，什么都没说明....', 'img/7.png', 1, '2017-10-20 16:48:13', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (13, 'pezy', 0, NULL, NULL, NULL, 'be960d2a207eaa9517c29582c5857e35', '用户很懒，什么都没说明....', 'img/8.png', 1, '2017-10-20 16:48:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (14, 'jeneser', 0, NULL, NULL, NULL, '2fd79fec1cbedd67622c9d6b09bd72e4', '用户很懒，什么都没说明....', 'img/8.png', 1, '2017-10-20 16:51:06', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (15, 'sniperhgy', 0, NULL, NULL, NULL, '2a690f33b7ca3d328d4cd2886bee4506', '用户很懒，什么都没说明....', 'img/9.png', 1, '2017-10-20 16:56:11', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (16, 'yannisPeng', 0, NULL, NULL, NULL, 'd0620768d89adf2cc2c8935838ead72d', '用户很懒，什么都没说明....', 'img/11.png', 1, '2017-10-23 14:24:03', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (17, 'opengps', 0, NULL, NULL, NULL, 'fd366a86ccadd0670ed13c3dcb0fd97d', '用户很懒，什么都没说明....', 'img/12.png', 1, '2017-10-24 22:33:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (18, 'quietjosen ', 0, NULL, NULL, NULL, '782f2432a89236c482b099d0326af97e', '用户很懒，什么都没说明....', 'img/21.png', 1, '2017-10-25 08:21:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (19, 'doodlister', 0, NULL, NULL, NULL, 'b6d884c6081d9ac7432a45aff9a15311', '用户很懒，什么都没说明....', 'img/15.png', 1, '2017-10-25 10:07:16', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (20, 'caoyingde', 0, NULL, NULL, NULL, 'a231d6a25af4b7fbc22c8b53efee1c90', '用户很懒，什么都没说明....', 'img/34.png', 1, '2017-10-27 23:17:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (21, 'yannisPengyu', 0, NULL, NULL, NULL, '7cf4570a211ff45e31d5a4a283f970b2', '用户很懒，什么都没说明....', 'img/39.png', 1, '2017-10-30 21:20:24', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (22, '123', 0, NULL, NULL, NULL, '077e9cee41219e38c684e77f02bece87', '用户很懒，什么都没说明....', 'img/41.png', 1, '2017-10-30 21:31:30', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (23, 'mokey', 0, NULL, NULL, NULL, '7706360ae11193270a7bee131639b439', '用户很懒，什么都没说明....', 'img/43.png', 1, '2017-11-03 10:29:07', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (24, 'rose', 0, NULL, NULL, NULL, '81c7a2b91ea79ebd1e18d5c94bda033f', '用户很懒，什么都没说明....', 'img/6.png', 1, '2017-11-07 10:45:45', NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
