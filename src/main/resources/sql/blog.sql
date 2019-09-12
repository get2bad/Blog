/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-09-12 14:17:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES ('1', 'index', '/');
INSERT INTO `tb_permission` VALUES ('2', 'background', '/background');
INSERT INTO `tb_permission` VALUES ('3', 'login', '/login');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `authName` varchar(255) NOT NULL,
  `authDescription` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', 'root', '全站管理员');
INSERT INTO `tb_role` VALUES ('2', 'admin', '普通管理员');
INSERT INTO `tb_role` VALUES ('3', 'writer', '作者');
INSERT INTO `tb_role` VALUES ('4', 'commer', '评论者');
INSERT INTO `tb_role` VALUES ('5', 'reader', '读者');

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_permission
-- ----------------------------
INSERT INTO `tb_role_permission` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `registDate` varchar(255) DEFAULT NULL,
  `remember` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `QQ` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `lastSignInIP` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `userIconLocation` varchar(255) DEFAULT NULL,
  `isDenySignIn` int(11) DEFAULT NULL,
  `activationCode` varchar(255) DEFAULT NULL,
  `isActived` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'wills', 'ed7943a54225adc707128a438aab9fe6', '2019-08-06', '1', '1', '17607113011', '978090490', 'loveing490@qq.com', '山东省龙口市西城区', '9999', '127.0.0.1', '1997-02-08 12:40:05', '宇宙第一帅！', null, '0', '78563', '1');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('1', '1', '1');
