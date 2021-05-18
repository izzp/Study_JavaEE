/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2021-05-18 09:07:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address_tb
-- ----------------------------
DROP TABLE IF EXISTS `address_tb`;
CREATE TABLE `address_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(20) DEFAULT NULL,
  `city` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address_tb
-- ----------------------------
INSERT INTO `address_tb` VALUES ('1', 'Hebei', 'Cangzhou');
INSERT INTO `address_tb` VALUES ('2', 'Shanxi', 'Datong');
INSERT INTO `address_tb` VALUES ('3', 'Shandong', 'Heze');

-- ----------------------------
-- Table structure for user_tb
-- ----------------------------
DROP TABLE IF EXISTS `user_tb`;
CREATE TABLE `user_tb` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(10) DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  `add_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_add` (`add_id`),
  CONSTRAINT `fk_user_add` FOREIGN KEY (`add_id`) REFERENCES `address_tb` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1015 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_tb
-- ----------------------------
INSERT INTO `user_tb` VALUES ('1001', 'Tom', '34', '1');
INSERT INTO `user_tb` VALUES ('1004', 'Morris', '30', '1');
INSERT INTO `user_tb` VALUES ('1006', 'Rose', '16', '2');
INSERT INTO `user_tb` VALUES ('1007', 'Xiaoming', '12', '3');
INSERT INTO `user_tb` VALUES ('1008', 'haha', '12', '3');
INSERT INTO `user_tb` VALUES ('1009', 'aaa', '1', null);
INSERT INTO `user_tb` VALUES ('1010', 'bbb', '2', null);
INSERT INTO `user_tb` VALUES ('1011', '哈哈', '56', '1');
INSERT INTO `user_tb` VALUES ('1012', '呵呵23', null, '2');
INSERT INTO `user_tb` VALUES ('1013', '嘿嘿', '34', '3');
INSERT INTO `user_tb` VALUES ('1014', '思思', '21', '3');
