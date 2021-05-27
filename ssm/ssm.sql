/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2021-05-27 07:52:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `dep_id` int(11) NOT NULL AUTO_INCREMENT,
  `dep_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`dep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('101', 'D-AA');
INSERT INTO `department` VALUES ('102', 'D-BB');
INSERT INTO `department` VALUES ('103', 'D-CC');
INSERT INTO `department` VALUES ('104', 'D-DD');
INSERT INTO `department` VALUES ('105', 'D-EE');

-- ----------------------------
-- Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(20) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `dep_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `dep_id` (`dep_id`),
  CONSTRAINT `fk_emp_dep` FOREIGN KEY (`dep_id`) REFERENCES `department` (`dep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1010 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1002', 'E-BB', '1', 'bb@163.com', '102');
INSERT INTO `employee` VALUES ('1003', 'E-CC', '0', 'cc@163.com', '103');
INSERT INTO `employee` VALUES ('1004', 'E-DD', '1', 'dd@163.com', '104');
INSERT INTO `employee` VALUES ('1005', 'E-EE', '1', 'ee@163.com', '105');
INSERT INTO `employee` VALUES ('1006', 'tom', '1', 'tom@163.com', '104');
