/*
Navicat MySQL Data Transfer

Source Server         : Connection
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : ancestra_game

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2013-07-19 01:05:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `quest_objectifs`
-- ----------------------------
DROP TABLE IF EXISTS `quest_objectifs`;
CREATE TABLE `quest_objectifs` (
  `id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `arguments` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of quest_objectifs
-- ----------------------------
INSERT INTO `quest_objectifs` VALUES ('1', '4', '');

-- ----------------------------
-- Table structure for `quest_steps`
-- ----------------------------
DROP TABLE IF EXISTS `quest_steps`;
CREATE TABLE `quest_steps` (
  `id` int(11) NOT NULL,
  `dialogue` int(11) NOT NULL,
  `gainkamas` int(11) NOT NULL,
  `gainxp` int(11) NOT NULL,
  `gainobjet` varchar(255) NOT NULL,
  `objectifs` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of quest_steps
-- ----------------------------
INSERT INTO `quest_steps` VALUES ('1', '311', '75', '100', '', '1');

-- ----------------------------
-- Table structure for `quests_data`
-- ----------------------------
DROP TABLE IF EXISTS `quests_data`;
CREATE TABLE `quests_data` (
  `id` int(11) NOT NULL,
  `conditions` varchar(255) NOT NULL,
  `steps` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of quests_data
-- ----------------------------
INSERT INTO `quests_data` VALUES ('1', '', '');
