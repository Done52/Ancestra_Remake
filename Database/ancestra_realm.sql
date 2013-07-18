/*
Navicat MySQL Data Transfer

Source Server         : Connection
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : ancestra_realm

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2013-07-18 14:37:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `accounts`
-- ----------------------------
DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
  `guid` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(30) NOT NULL,
  `pass` varchar(50) NOT NULL,
  `level` int(11) NOT NULL DEFAULT '0',
  `subscription` int(11) NOT NULL DEFAULT '0',
  `email` varchar(100) NOT NULL,
  `lastIP` varchar(15) NOT NULL,
  `lastConnectionDate` varchar(100) NOT NULL,
  `question` varchar(100) NOT NULL DEFAULT 'DELETE?',
  `reponse` varchar(100) NOT NULL DEFAULT 'DELETE',
  `pseudo` varchar(30) NOT NULL,
  `banned` tinyint(3) NOT NULL DEFAULT '0',
  `curIP` varchar(15) NOT NULL,
  `giftID` varchar(100) NOT NULL COMMENT 'giftID1;giftID2 ...',
  `logged` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `account` (`account`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of accounts
-- ----------------------------

-- ----------------------------
-- Table structure for `banip`
-- ----------------------------
DROP TABLE IF EXISTS `banip`;
CREATE TABLE `banip` (
  `ip` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of banip
-- ----------------------------

-- ----------------------------
-- Table structure for `gameservers`
-- ----------------------------
DROP TABLE IF EXISTS `gameservers`;
CREATE TABLE `gameservers` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ServerIP` text NOT NULL,
  `ServerPort` int(11) NOT NULL,
  `State` int(11) NOT NULL,
  `ServerBDD` text NOT NULL,
  `ServerDBName` text NOT NULL,
  `ServerUser` text NOT NULL,
  `ServerPassword` text NOT NULL,
  `key` text NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of gameservers
-- ----------------------------
INSERT INTO `gameservers` VALUES ('1', '127.0.0.1', '5555', '0', '127.0.0.1', 'ancestra_game', 'root', '', 'server1');
