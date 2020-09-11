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

 Date: 11/09/2020 18:15:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
                                `parameter_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数key',
                                `value` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数1',
                                `value2` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数2',
                                `value3` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数3',
                                `value4` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数4',
                                `value5` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数5',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
