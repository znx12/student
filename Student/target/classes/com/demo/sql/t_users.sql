/*
 Navicat Premium Data Transfer

 Source Server         : A本地win10-MySQL 5.6-3306
 Source Server Type    : MySQL
 Source Server Version : 50646
 Source Host           : localhost:3306
 Source Schema         : element_users01

 Target Server Type    : MySQL
 Target Server Version : 50646
 File Encoding         : 65001

 Date: 30/08/2021 21:38:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_users
-- ----------------------------

DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users`
(
    `id`       int(10) NOT NULL AUTO_INCREMENT,
    `name`     varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `birthday` timestamp(0) NULL DEFAULT NULL,
    `gender`   varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `address`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

SET
FOREIGN_KEY_CHECKS = 1;