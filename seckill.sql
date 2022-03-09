/*
 Navicat Premium Data Transfer

 Source Server         : WQL
 Source Server Type    : MySQL
 Source Server Version : 50527
 Source Host           : localhost:3306
 Source Schema         : seckill

 Target Server Type    : MySQL
 Target Server Version : 50527
 File Encoding         : 65001

 Date: 09/03/2022 20:24:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `goods_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `goods_title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品标题',
  `goods_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `goods_detail` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品详情',
  `goods_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `goods_stock` int(11) NULL DEFAULT NULL COMMENT '商品库存，-1表示没有',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '雷神11', '雷神(ThundeRobot) 911Air星战四代 15.6英寸游戏笔记本电脑', 'https://img14.360buyimg.com/n0/jfs/t1/102780/4/22524/292375/61e8ca6eEa2130f72/84c9f09851b1cc18.jpg', '11代八核i7,16G大内存，512GSSD高速固态，RTX3050Ti全新显卡', 6299.00, 100);
INSERT INTO `goods` VALUES (2, '机械师F117', '机械师F117毒药', 'https://img14.360buyimg.com/n0/jfs/t1/157707/7/22518/120535/61af43f5E9e01358a/372fa94455f3ded7.jpg', '144Hz全面屏11代酷睿i7八核RTX3050Ti独显', 7389.00, 200);

-- ----------------------------
-- Table structure for seckilluser
-- ----------------------------
DROP TABLE IF EXISTS `seckilluser`;
CREATE TABLE `seckilluser`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'MD5加密后的password',
  `passwordslat` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'slat为密码的安全拼接',
  `headportrait` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `register_date` datetime NULL DEFAULT NULL COMMENT '用户注册时间',
  `last_login_date` datetime NULL DEFAULT NULL COMMENT '用户最后一次登录时间',
  `login_count` int(255) NULL DEFAULT NULL COMMENT '用户登录的次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of seckilluser
-- ----------------------------
INSERT INTO `seckilluser` VALUES (1, 'wql', '56eac4c4e275e3363a2d2f8db55561c2', 'afjdtfjb', NULL, '2022-02-27 22:47:36', NULL, NULL);

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  `delivery_addr_id` bigint(20) NULL DEFAULT NULL COMMENT '收货地址id',
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送过来的商品名称',
  `goods_count` int(11) NULL DEFAULT NULL COMMENT '商品数量',
  `goods_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `order_channel` tinyint(4) NULL DEFAULT NULL,
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '订单状态，0新键未支付，1已支付，2已发货，3已收货，4已退款，5已完成',
  `create_date` datetime NULL DEFAULT NULL COMMENT '订单的时间',
  `pay_date` datetime NULL DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_seckill_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_seckill_goods`;
CREATE TABLE `t_seckill_goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀商品ID',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  `seckill_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '秒杀价',
  `stock_count` int(20) NULL DEFAULT NULL COMMENT '库存数量',
  `start_date` datetime NULL DEFAULT NULL COMMENT '秒杀开始时间',
  `end_date` datetime NULL DEFAULT NULL COMMENT '秒杀结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_seckill_goods
-- ----------------------------
INSERT INTO `t_seckill_goods` VALUES (1, 1, 5000.00, 47, '2022-03-06 21:29:41', '2022-03-10 21:29:50');
INSERT INTO `t_seckill_goods` VALUES (2, 2, 6999.00, 49, '2022-03-06 21:30:34', '2022-03-07 21:30:37');

-- ----------------------------
-- Table structure for t_seckill_order
-- ----------------------------
DROP TABLE IF EXISTS `t_seckill_order`;
CREATE TABLE `t_seckill_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀订单ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单ID',
  `goods_id` bigint(20) NULL DEFAULT NULL COMMENT '商品ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
