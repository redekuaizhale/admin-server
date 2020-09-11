/*
 Navicat Premium Data Transfer

 Source Server         : 虚拟机
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 192.168.5.148:3306
 Source Schema         : admin

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 09/09/2020 14:29:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_company
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `version` int(255) NULL DEFAULT NULL COMMENT '版本号',
  `del_flag` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标记（0：正常 1：删除）',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '父级id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '机构简称',
  `full_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '机构全称',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '状态（营业，筹备）',
  `path` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '机构路径',
  `area` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '所属区域',
  `address` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '详细地址',
  `company_order` int(255) NULL DEFAULT NULL COMMENT '排序字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_company
-- ----------------------------
INSERT INTO `t_company` VALUES ('4028b881726d849b01726ea5412d0001', 9, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-01 14:50:23', '4028859a6dc82cbe016dc82ccc280000', '2020-08-26 10:16:35', '0', '总部测试', '总部测试', '营业', '4028b881726d849b01726ea5412d0001', '110000,110100,110101', '坪山区1221111', 1);
INSERT INTO `t_company` VALUES ('4028b88172729d09017272f3878c0002', 10, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-02 10:54:22', '4028859a6dc82cbe016dc82ccc280000', '2020-08-26 10:57:10', '4028b881726d849b01726ea5412d0001', '机构1', '机构1', '营业', '4028b881726d849b01726ea5412d0001,4028b88172729d09017272f3878c0002', '120000,120100,120104', '13123', 1);
INSERT INTO `t_company` VALUES ('4028b88172729d09017272f3a3170003', 6, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-02 10:54:29', '4028859a6dc82cbe016dc82ccc280000', '2020-08-26 10:28:47', '4028b881726d849b01726ea5412d0001', '机构2', '机构2', '营业', '4028b881726d849b01726ea5412d0001,4028b88172729d09017272f3a3170003', '110000,110100,110105', '12313313', 2);
INSERT INTO `t_company` VALUES ('4028b88172729d090172730172450004', 9, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-02 11:09:34', '4028859a6dc82cbe016dc82ccc280000', '2020-08-26 10:32:20', '4028b881726d849b01726ea5412d0001', '机构3', '机构3', '营业', '4028b881726d849b01726ea5412d0001,4028b88172729d090172730172450004', '110000,110100,110102', '213', 3);

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `version` int(255) NULL DEFAULT NULL COMMENT '版本号',
  `del_flag` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标记（0：正常 1：删除）',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `company_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '机构id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `dept_order` int(255) NULL DEFAULT NULL COMMENT '科室排序',
  `remark` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '介绍',
  `setup_date` datetime(0) NULL DEFAULT NULL COMMENT '成立时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('4028851373d7ba5f0173d7ba74bf0000', 0, '0', NULL, '2020-08-10 17:36:28', NULL, '2020-08-10 17:36:28', '4028b881726d849b01726ea5412d0001', '部门1', NULL, NULL, NULL);
INSERT INTO `t_dept` VALUES ('4028851373db1d870173db3ea1520000', 15, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-08-11 09:59:42', '4028859a6dc82cbe016dc82ccc280000', '2020-08-14 14:50:35', '4028b88172729d09017272f3878c0002', '部门1', 1, '部门1', '2020-08-18 00:00:00');
INSERT INTO `t_dept` VALUES ('4028851373dc2f860173dc3a23a90000', 1, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-08-11 14:34:25', '4028859a6dc82cbe016dc82ccc280000', '2020-08-11 14:34:30', '4028b88172729d09017272f3878c0002', '部门2', 1, '部门2', '2020-08-11 00:00:00');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `version` int(255) NULL DEFAULT NULL COMMENT '版本号',
  `del_flag` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标记（0：正常 1：删除）',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级菜单id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件名称',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `redirect` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '重定向地址',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件绝对路径',
  `hidden` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否隐藏',
  `menu_order` int(255) NULL DEFAULT NULL COMMENT '排序字段',
  `use_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用状态(可用，禁用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('4028b88172743de60172743df6910000', 3, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-02 16:55:17', '4028859a6dc82cbe016dc82ccc280000', '2020-08-26 11:12:53', '0', 'sys', '/sys', NULL, '系统管理', '_setting', 'Main', '显示', 2, '可用');
INSERT INTO `t_menu` VALUES ('4028b88172743faf0172743fbf800000', 4, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-02 16:57:14', '4028859a6dc82cbe016dc82ccc280000', '2020-08-26 11:21:58', '4028b88172743de60172743df6910000', 'sys-menu', '/sys-menu', NULL, '菜单管理', '_menu1', '/sys/sys-menu', '显示', 2, '可用');
INSERT INTO `t_menu` VALUES ('4028b881727450ca01727450dcad0000', 5, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-02 17:15:56', '4028859a6dc82cbe016dc82ccc280000', '2020-08-26 11:11:59', '0', 'index', '/index', 'home', '首页', '_home', 'Main', '显示', 1, '可用');
INSERT INTO `t_menu` VALUES ('4028b8817274522c017274523c700000', 4, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-02 17:17:26', '4028859a6dc82cbe016dc82ccc280000', '2020-08-20 14:44:24', '4028b881727450ca01727450dcad0000', '/', '/', NULL, '首页', '_menu-home', '/home/home', '显示', 1, '可用');
INSERT INTO `t_menu` VALUES ('4028b8817278067201727808a9d90000', 1, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 10:35:33', '4028859a6dc82cbe016dc82ccc280000', '2020-08-26 11:13:23', '4028b88172743de60172743df6910000', 'sys-user', '/sys-user', '', '用户管理', '_user', '/sys/sys-user', '显示', 1, '可用');
INSERT INTO `t_menu` VALUES ('4028b881727806720172780990f30001', 1, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 10:36:32', '4028859a6dc82cbe016dc82ccc280000', '2020-08-26 11:14:42', '4028b88172743de60172743df6910000', 'sys-role', '/sys-role', '', '角色管理', '_key', '/sys/sys-role', '显示', 3, '可用');
INSERT INTO `t_menu` VALUES ('4028b881727806720172780a458d0002', 1, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 10:37:19', '4028859a6dc82cbe016dc82ccc280000', '2020-08-26 11:18:59', '4028b88172743de60172743df6910000', 'sys-company', '/sys-company', '', '机构管理', '_org', '/sys/sys-company', '显示', 4, '可用');
INSERT INTO `t_menu` VALUES ('4028b881727806720172780ab9950003', 1, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 10:37:48', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 10:46:08', '4028b88172743de60172743df6910000', 'sys-dept', '/sys-dept', '', '部门管理', '_dept', '/sys/sys-dept', '显示', 5, '可用');

-- ----------------------------
-- Table structure for t_parameter
-- ----------------------------
DROP TABLE IF EXISTS `t_parameter`;
CREATE TABLE `t_parameter`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `version` int(255) NULL DEFAULT NULL COMMENT '版本号',
  `del_flag` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标记（0：正常 1：删除）',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数key',
  `value` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数1',
  `value2` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数2',
  `value3` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数3',
  `value4` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数4',
  `value5` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数5',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `version` int(255) NULL DEFAULT NULL COMMENT '版本号',
  `del_flag` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标记（0：正常 1：删除）',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名称',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编码',
  `remark` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '介绍',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('4028b8817255086f0172551bf10e0009', 1, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-05-27 15:49:54', '4028859a6dc82cbe016dc82ccc280000', '2020-08-13 15:02:53', 'hs岗', NULL, '描述');
INSERT INTO `t_role` VALUES ('4028b8817255086f0172551c3617000a', 2, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-05-27 15:50:12', '4028859a6dc82cbe016dc82ccc280000', '2020-08-14 15:20:27', 'ys岗', NULL, '描述');
INSERT INTO `t_role` VALUES ('4028b8817255086f0172551c620f000b', 1, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-05-27 15:50:23', '4028859a6dc82cbe016dc82ccc280000', '2020-08-14 14:48:35', 'sh岗', NULL, '描述1');
INSERT INTO `t_role` VALUES ('4028b8817255086f0172551c9abf000c', 0, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-05-27 15:50:38', '4028859a6dc82cbe016dc82ccc280000', '2020-05-27 15:50:38', 'gl岗', NULL, '描述');
INSERT INTO `t_role` VALUES ('4028b8817255086f017255dae4d9000e', 2, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-05-27 19:18:28', '4028859a6dc82cbe016dc82ccc280000', '2020-05-27 19:21:23', '超级管理员', NULL, '超级管理员');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `version` int(255) NULL DEFAULT NULL COMMENT '版本号',
  `del_flag` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标记（0：正常 1：删除）',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `company_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属机构',
  `dept_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属部门',
  `login_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态（可用,停用）',
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int(255) NULL DEFAULT NULL COMMENT '年龄',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('402885137292ac84017292d0c2150001', 6, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-08 15:24:14', '4028859a6dc82cbe016dc82ccc280000', '2020-08-14 11:58:02', '4028b88172729d09017272f3878c0002', '4028851373dc2f860173dc3a23a90000', 'zhanghui', '000000', '张辉', '可用', NULL, NULL, '17343009527', NULL, NULL, '2323');
INSERT INTO `t_user` VALUES ('4028859a6dc82cbe016dc82ccc280000', 5, '0', NULL, NULL, '4028859a6dc82cbe016dc82ccc280000', '2020-08-14 14:50:04', '4028b88172729d09017272f3878c0002', '4028851373db1d870173db3ea1520000', 'admin', 'admin', '超级管理员', '可用', NULL, NULL, '17343009527', NULL, NULL, '222');

-- ----------------------------
-- Table structure for t_user_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_user_menu`;
CREATE TABLE `t_user_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `version` int(255) NULL DEFAULT NULL COMMENT '版本号',
  `del_flag` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标记（0：正常 1：删除）',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `menu_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_menu
-- ----------------------------
INSERT INTO `t_user_menu` VALUES ('40288513746c6e1d01746c7921d10014', 0, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-09-08 14:48:32', '4028859a6dc82cbe016dc82ccc280000', '2020-09-08 14:48:32', '402885137292ac84017292d0c2150001', '4028b881727450ca01727450dcad0000');
INSERT INTO `t_user_menu` VALUES ('40288513746c6e1d01746c7921da0015', 0, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-09-08 14:48:32', '4028859a6dc82cbe016dc82ccc280000', '2020-09-08 14:48:32', '402885137292ac84017292d0c2150001', '4028b8817274522c017274523c700000');
INSERT INTO `t_user_menu` VALUES ('40288513746c6e1d01746c7921e30016', 0, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-09-08 14:48:32', '4028859a6dc82cbe016dc82ccc280000', '2020-09-08 14:48:32', '402885137292ac84017292d0c2150001', '4028b88172743de60172743df6910000');
INSERT INTO `t_user_menu` VALUES ('40288513746c6e1d01746c7921f00017', 0, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-09-08 14:48:32', '4028859a6dc82cbe016dc82ccc280000', '2020-09-08 14:48:32', '402885137292ac84017292d0c2150001', '4028b8817278067201727808a9d90000');
INSERT INTO `t_user_menu` VALUES ('40288513746c6e1d01746c7921f60018', 0, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-09-08 14:48:32', '4028859a6dc82cbe016dc82ccc280000', '2020-09-08 14:48:32', '402885137292ac84017292d0c2150001', '4028b88172743faf0172743fbf800000');
INSERT INTO `t_user_menu` VALUES ('40288513746c6e1d01746c7922010019', 0, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-09-08 14:48:32', '4028859a6dc82cbe016dc82ccc280000', '2020-09-08 14:48:32', '402885137292ac84017292d0c2150001', '4028b881727806720172780990f30001');
INSERT INTO `t_user_menu` VALUES ('40288513746c6e1d01746c792206001a', 0, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-09-08 14:48:32', '4028859a6dc82cbe016dc82ccc280000', '2020-09-08 14:48:32', '402885137292ac84017292d0c2150001', '4028b881727806720172780a458d0002');
INSERT INTO `t_user_menu` VALUES ('40288513746c6e1d01746c792211001b', 0, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-09-08 14:48:32', '4028859a6dc82cbe016dc82ccc280000', '2020-09-08 14:48:32', '402885137292ac84017292d0c2150001', '4028b881727806720172780ab9950003');
INSERT INTO `t_user_menu` VALUES ('4028b881727806720172780d73dc0004', 1, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 10:40:47', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 11:30:48', '4028859a6dc82cbe016dc82ccc280000', '4028b881727450ca01727450dcad0000');
INSERT INTO `t_user_menu` VALUES ('4028b881727806720172780d73ee0005', 1, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 10:40:47', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 11:30:48', '4028859a6dc82cbe016dc82ccc280000', '4028b8817274522c017274523c700000');
INSERT INTO `t_user_menu` VALUES ('4028b881727806720172780d74010006', 1, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 10:40:47', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 11:30:48', '4028859a6dc82cbe016dc82ccc280000', '4028b88172743de60172743df6910000');
INSERT INTO `t_user_menu` VALUES ('4028b881727806720172780d74090007', 1, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 10:40:47', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 11:30:48', '4028859a6dc82cbe016dc82ccc280000', '4028b881727806720172780ab9950003');
INSERT INTO `t_user_menu` VALUES ('4028b881727806720172780d74120008', 1, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 10:40:47', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 11:30:48', '4028859a6dc82cbe016dc82ccc280000', '4028b8817278067201727808a9d90000');
INSERT INTO `t_user_menu` VALUES ('4028b881727806720172780d741d0009', 1, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 10:40:47', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 11:30:48', '4028859a6dc82cbe016dc82ccc280000', '4028b88172743faf0172743fbf800000');
INSERT INTO `t_user_menu` VALUES ('4028b881727806720172780d7425000a', 1, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 10:40:47', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 11:30:48', '4028859a6dc82cbe016dc82ccc280000', '4028b881727806720172780990f30001');
INSERT INTO `t_user_menu` VALUES ('4028b881727806720172780d742f000b', 1, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 10:40:47', '4028859a6dc82cbe016dc82ccc280000', '2020-06-03 11:30:48', '4028859a6dc82cbe016dc82ccc280000', '4028b881727806720172780a458d0002');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `version` int(255) NULL DEFAULT NULL COMMENT '版本号',
  `del_flag` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标记（0：正常 1：删除）',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人id',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('4028851372a14c940172a14e4c710000', 1, '1', '4028859a6dc82cbe016dc82ccc280000', '2020-06-11 10:56:03', '4028859a6dc82cbe016dc82ccc280000', '2020-06-11 10:57:55', '402885137292ac84017292d0c2150001', '4028b8817255086f0172551bf10e0009');
INSERT INTO `t_user_role` VALUES ('4028851372a14c940172a150049f0001', 0, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-11 10:57:55', '4028859a6dc82cbe016dc82ccc280000', '2020-06-11 10:57:55', '402885137292ac84017292d0c2150001', '4028b8817255086f0172551bf10e0009');
INSERT INTO `t_user_role` VALUES ('4028851372a14c940172a15004b50002', 0, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-11 10:57:55', '4028859a6dc82cbe016dc82ccc280000', '2020-06-11 10:57:55', '402885137292ac84017292d0c2150001', '4028b8817255086f0172551c3617000a');
INSERT INTO `t_user_role` VALUES ('4028851372a14c940172a15004c90003', 0, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-11 10:57:55', '4028859a6dc82cbe016dc82ccc280000', '2020-06-11 10:57:55', '402885137292ac84017292d0c2150001', '4028b8817255086f0172551c620f000b');
INSERT INTO `t_user_role` VALUES ('4028851372a160690172a1fc62fb0005', 0, '0', '4028859a6dc82cbe016dc82ccc280000', '2020-06-11 14:06:12', '4028859a6dc82cbe016dc82ccc280000', '2020-06-11 14:06:12', '4028859a6dc82cbe016dc82ccc280000', '4028b8817255086f017255dae4d9000e');

SET FOREIGN_KEY_CHECKS = 1;
