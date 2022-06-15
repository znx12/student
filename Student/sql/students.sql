/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50554
 Source Host           : localhost:3306
 Source Schema         : students

 Target Server Type    : MySQL
 Target Server Version : 50554
 File Encoding         : 65001

 Date: 15/06/2022 09:49:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `course_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `credit` float NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除 0 存在 1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '语文', 2, 0);
INSERT INTO `course` VALUES (2, '生物', 3.5, 0);
INSERT INTO `course` VALUES (3, '科学', 1.5, 0);
INSERT INTO `course` VALUES (4, '英语', 1.5, 0);
INSERT INTO `course` VALUES (5, '物理', 4.5, 0);
INSERT INTO `course` VALUES (6, '化学', 4.6, 0);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stud_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` tinyint(3) UNSIGNED NOT NULL,
  `grade` int(11) NOT NULL,
  `deleted` tinyint(1) NOT NULL COMMENT '逻辑删除 0存在 1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 'zhangsan', 1, 2, 0);
INSERT INTO `student` VALUES (2, '王仁', 0, 2, 0);
INSERT INTO `student` VALUES (3, '王老五', 0, 3, 0);
INSERT INTO `student` VALUES (4, '张五', 0, 1, 0);
INSERT INTO `student` VALUES (5, '李四', 1, 4, 0);
INSERT INTO `student` VALUES (6, '郭勇', 1, 18, 0);
INSERT INTO `student` VALUES (7, '林天', 0, 10, 0);
INSERT INTO `student` VALUES (8, '李华', 1, 5, 0);

-- ----------------------------
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生与课程关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student_course
-- ----------------------------
INSERT INTO `student_course` VALUES (68, 1, 1);
INSERT INTO `student_course` VALUES (69, 1, 2);
INSERT INTO `student_course` VALUES (70, 1, 3);
INSERT INTO `student_course` VALUES (71, 1, 4);
INSERT INTO `student_course` VALUES (72, 1, 5);
INSERT INTO `student_course` VALUES (73, 2, 1);
INSERT INTO `student_course` VALUES (74, 3, 1);
INSERT INTO `student_course` VALUES (75, 4, 2);
INSERT INTO `student_course` VALUES (76, 5, 1);
INSERT INTO `student_course` VALUES (77, 4, 1);

SET FOREIGN_KEY_CHECKS = 1;
