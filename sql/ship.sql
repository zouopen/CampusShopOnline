/*
 Navicat Premium Data Transfer

 Source Server         : DemoTest
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : ship

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 12/08/2019 23:53:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for buyer_address
-- ----------------------------
DROP TABLE IF EXISTS `buyer_address`;
CREATE TABLE `buyer_address`  (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `buyer_openid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家微信openid',
  `buyer_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家姓名',
  `buyer_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家地址',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buyer_address
-- ----------------------------
INSERT INTO `buyer_address` VALUES (1, 'ew3euwhd7sjw9diwkq', '张三', '13500117518', '1-414', '2019-04-26 15:11:55', '2019-04-26 15:11:55');
INSERT INTO `buyer_address` VALUES (2, 'ew3euwhd7sjw9diwks', '李四', '13500117518', '1-414', '2019-04-26 16:13:24', '2019-04-26 16:13:24');
INSERT INTO `buyer_address` VALUES (3, 'ew3euwhd7sjw9diwkq', '李四', '13500117518', '1-414', '2019-04-26 16:50:35', '2019-04-26 16:50:35');
INSERT INTO `buyer_address` VALUES (4, 'ew3euwhd7sjw9diwkq', 't', '13500117851', '1-415', '2019-04-26 21:03:32', '2019-04-26 21:03:32');
INSERT INTO `buyer_address` VALUES (5, 'ew3euwhd7sjw9diwkq', 'ts', '13500117851', '1-416', '2019-04-26 21:03:46', '2019-04-26 21:03:46');
INSERT INTO `buyer_address` VALUES (6, 'ew3euwhd7sjw9diwkq', 'tsa', '13500117851', '1-416', '2019-04-26 21:03:49', '2019-04-26 21:03:49');
INSERT INTO `buyer_address` VALUES (7, 'ew3euwhd7sjw9diwkq', 'tsaa', '13500117851', '1-416', '2019-04-26 21:03:53', '2019-04-26 21:03:53');

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `detail_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_type` int(11) NOT NULL COMMENT '店铺编号',
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_price` decimal(8, 2) NOT NULL COMMENT '当前价格,单位分',
  `product_discount` decimal(8, 2) NULL DEFAULT 0.00 COMMENT '商品折扣',
  `product_quantity` int(11) NOT NULL COMMENT '数量',
  `product_icon` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小图',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`detail_id`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('1548504225922163064', '1548504225595666523', 1, '1', '麻辣火锅', 100.00, NULL, 2, '1', '2019-01-26 19:19:09', '2019-01-26 20:02:53');
INSERT INTO `order_detail` VALUES ('1548504225942744036', '1548504225595666523', 1, '2', '火锅', 100.00, 0.50, 2, '1', '2019-01-26 19:19:25', '2019-01-26 20:02:53');
INSERT INTO `order_detail` VALUES ('1548504273517890572', '1548504273328919854', 1, '1', '麻辣火锅', 100.00, NULL, 2, '1', '2019-01-26 19:19:09', '2019-01-26 20:03:45');
INSERT INTO `order_detail` VALUES ('1548504273531393897', '1548504273328919854', 1, '2', '火锅', 100.00, NULL, 2, '1', '2019-01-26 19:19:25', '2019-01-26 20:04:24');
INSERT INTO `order_detail` VALUES ('1550075128702556304', '1550075128693482425', 2, '1549293751324695124', '孜然牛肉懒人火锅<自热>', 123.00, NULL, 2, '', '2019-02-04 23:22:31', '2019-02-04 23:22:31');
INSERT INTO `order_detail` VALUES ('1550141740460106667', '1550141740332508382', 2, '1549293751324695124', '孜然牛肉懒人火锅<自热>', 123.00, NULL, 2, '', '2019-02-04 23:22:31', '2019-02-14 00:25:28');
INSERT INTO `order_detail` VALUES ('1550146627916201875', '1550146627631338162', 2, '1549293751324695124', '孜然牛肉懒人火锅<自热>', 123.00, NULL, 2, '', '2019-02-04 23:22:31', '2019-02-14 18:55:40');
INSERT INTO `order_detail` VALUES ('1550341470518925184', '1550341470154307257', 2, '1549293751324695124', '孜然牛肉懒人火锅<自热>', 123.00, NULL, 2, '', '2019-02-04 23:22:31', '2019-02-14 20:17:07');
INSERT INTO `order_detail` VALUES ('1551096056720942777', '1551096056705187803', 2, '1549293751324695124', '孜然牛肉懒人火锅& & lt;自热& gt;', 123.00, NULL, 2, '', '2019-02-25 20:00:57', '2019-02-25 20:00:57');
INSERT INTO `order_detail` VALUES ('1551096238286369599', '1551096238282670243', 2, '1549293751324695124', '孜然牛肉懒人火锅& & lt;自热& gt;', 123.00, NULL, 2, '', '2019-02-25 20:03:58', '2019-02-25 20:03:58');
INSERT INTO `order_detail` VALUES ('1551096760801263226', '1551096760783286190', 2, '1549293751324695124', '孜然牛肉懒人火锅& & lt;自热& gt;', 123.00, NULL, 2, '', '2019-02-25 20:12:41', '2019-02-25 20:12:41');
INSERT INTO `order_detail` VALUES ('1551108679012737661', '1551108678998914308', 2, '1549293751324695124', '孜然牛肉懒人火锅& & lt;自热& gt;', 123.00, NULL, 2, '', '2019-02-25 23:31:19', '2019-02-25 23:31:19');
INSERT INTO `order_detail` VALUES ('1551108695911300471', '1551108695907591196', 2, '1549293751324695124', '孜然牛肉懒人火锅& & lt;自热& gt;', 123.00, NULL, 2, '', '2019-02-25 23:31:36', '2019-02-25 23:31:36');
INSERT INTO `order_detail` VALUES ('1551108735028463276', '1551108735025324967', 2, '1549293751324695124', '孜然牛肉懒人火锅& & lt;自热& gt;', 123.00, NULL, 2, '', '2019-02-25 23:32:15', '2019-02-25 23:32:15');
INSERT INTO `order_detail` VALUES ('1551108759525531785', '1551108759523176114', 2, '1549293751324695124', '孜然牛肉懒人火锅& & lt;自热& gt;', 123.00, NULL, 2, '', '2019-02-25 23:32:40', '2019-02-25 23:32:40');
INSERT INTO `order_detail` VALUES ('1551108890826197992', '1551108890820257978', 2, '1549293751324695124', '孜然牛肉懒人火锅& & lt;自热& gt;', 123.00, NULL, 2, '', '2019-02-25 23:34:51', '2019-02-25 23:34:51');
INSERT INTO `order_detail` VALUES ('1551109005346257548', '1551108976218330133', 2, '1549293751324695124', '孜然牛肉懒人火锅& & lt;自热& gt;', 123.00, NULL, 2, '', '2019-02-25 23:36:47', '2019-02-25 23:36:47');
INSERT INTO `order_detail` VALUES ('1551109270668253430', '1551109258375647305', 2, '1549293751324695124', '孜然牛肉懒人火锅& & lt;自热& gt;', 123.00, NULL, 2, '', '2019-02-25 23:41:16', '2019-02-25 23:41:16');
INSERT INTO `order_detail` VALUES ('1551109567497421737', '1551109567199713093', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-02-25 23:46:08', '2019-02-25 23:46:08');
INSERT INTO `order_detail` VALUES ('1551439537764468832', '1551439537738952833', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-01 19:25:38', '2019-03-01 19:25:38');
INSERT INTO `order_detail` VALUES ('1551439610886185290', '1551439610882384983', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-01 19:26:51', '2019-03-01 19:26:51');
INSERT INTO `order_detail` VALUES ('1551441062201162549', '1551441062184496291', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-01 19:51:02', '2019-03-01 19:51:02');
INSERT INTO `order_detail` VALUES ('1551441094822586847', '1551441094817575841', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-01 19:51:35', '2019-03-01 19:51:35');
INSERT INTO `order_detail` VALUES ('1551442031776993118', '1551442031765165960', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-01 20:07:12', '2019-03-01 20:07:12');
INSERT INTO `order_detail` VALUES ('1551442112643744627', '1551442112640846225', 2, '1549293751324695124', '孜然牛肉懒人火锅& & lt;自热& gt;', 123.00, NULL, 1, '', '2019-03-01 20:08:33', '2019-03-01 20:08:33');
INSERT INTO `order_detail` VALUES ('1551455460682207505', '1551455460346796013', 2, '1549293751324695124', '孜然牛肉懒人火锅& & lt;自热& gt;', 123.00, NULL, 1, '', '2019-03-01 23:51:01', '2019-03-01 23:51:01');
INSERT INTO `order_detail` VALUES ('1551456783373484540', '1551456783067350050', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-02 00:13:03', '2019-03-02 00:13:03');
INSERT INTO `order_detail` VALUES ('1551456785148805390', '1551456785144771566', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-02 00:13:05', '2019-03-02 00:13:05');
INSERT INTO `order_detail` VALUES ('1551456785984987675', '1551456785980926567', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-02 00:13:06', '2019-03-02 00:13:06');
INSERT INTO `order_detail` VALUES ('1551456786574962280', '1551456786570480503', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-02 00:13:07', '2019-03-02 00:13:07');
INSERT INTO `order_detail` VALUES ('1551456787147786968', '1551456787144407438', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-02 00:13:07', '2019-03-02 00:13:07');
INSERT INTO `order_detail` VALUES ('1551456787643603639', '1551456787640963450', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-02 00:13:08', '2019-03-02 00:13:08');
INSERT INTO `order_detail` VALUES ('1551456788217690382', '1551456788212162078', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-02 00:13:08', '2019-03-02 00:13:08');
INSERT INTO `order_detail` VALUES ('1551456844975157922', '1551456844972929841', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-02 00:14:05', '2019-03-02 00:14:05');
INSERT INTO `order_detail` VALUES ('1551456845671557129', '1551456845667533096', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-02 00:14:06', '2019-03-02 00:14:06');
INSERT INTO `order_detail` VALUES ('1551460717218282040', '1551460717197811566', 1, '1549293308953779370', 'test', 123.00, 0.00, 1, '123', '2019-03-02 01:18:37', '2019-03-02 01:18:37');
INSERT INTO `order_detail` VALUES ('1551460798614623586', '1551460798606153862', 1, '1549293308953779370', 'test', 123.00, 0.00, 1, '123', '2019-03-02 01:19:59', '2019-03-02 01:19:59');
INSERT INTO `order_detail` VALUES ('1551460989439688175', '1551460989436456569', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-03-02 01:23:09', '2019-03-02 01:23:09');
INSERT INTO `order_detail` VALUES ('1551461089496319049', '1551461089492936971', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-03-02 01:24:49', '2019-03-02 01:24:49');
INSERT INTO `order_detail` VALUES ('1551461115151844839', '1551461115148954804', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-02 01:25:15', '2019-03-02 01:25:15');
INSERT INTO `order_detail` VALUES ('1551461247682535003', '1551461247662901809', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-03-02 01:27:28', '2019-03-02 01:27:28');
INSERT INTO `order_detail` VALUES ('1551461673863745385', '1551461291790737852', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-03-02 01:34:34', '2019-03-02 01:34:34');
INSERT INTO `order_detail` VALUES ('1551461713513511918', '1551461711569942916', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-03-02 01:35:14', '2019-03-02 01:35:14');
INSERT INTO `order_detail` VALUES ('1551463149543895420', '1551463147859845004', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-03-02 01:59:10', '2019-03-02 01:59:10');
INSERT INTO `order_detail` VALUES ('1551463283529623173', '1551463283001138936', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-02 02:01:24', '2019-03-02 02:01:24');
INSERT INTO `order_detail` VALUES ('1551463284024475885', '1551463283001138936', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-03-02 02:01:24', '2019-03-02 02:01:24');
INSERT INTO `order_detail` VALUES ('1551466543713271019', '1551466543703524624', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-02 02:55:44', '2019-03-02 02:55:44');
INSERT INTO `order_detail` VALUES ('1551466543750611208', '1551466543703524624', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-03-02 02:55:44', '2019-03-02 02:55:44');
INSERT INTO `order_detail` VALUES ('1551468114607823138', '1551468114302724776', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-02 03:21:55', '2019-03-02 03:21:55');
INSERT INTO `order_detail` VALUES ('1551468114651278246', '1551468114302724776', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-03-02 03:21:55', '2019-03-02 03:21:55');
INSERT INTO `order_detail` VALUES ('1551515632315764149', '1551515632006129414', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-02 16:33:52', '2019-03-02 16:33:52');
INSERT INTO `order_detail` VALUES ('1551515632358776889', '1551515632006129414', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-03-02 16:33:52', '2019-03-02 16:33:52');
INSERT INTO `order_detail` VALUES ('1551515670752698817', '1551515670749689082', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-02 16:34:31', '2019-03-02 16:34:31');
INSERT INTO `order_detail` VALUES ('1551515670758236399', '1551515670749689082', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-03-02 16:34:31', '2019-03-02 16:34:31');
INSERT INTO `order_detail` VALUES ('1551515746042809988', '1551515746039744741', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-02 16:35:46', '2019-03-02 16:35:46');
INSERT INTO `order_detail` VALUES ('1551515746046167621', '1551515746039744741', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-03-02 16:35:46', '2019-03-02 16:35:46');
INSERT INTO `order_detail` VALUES ('1551515876531475748', '1551515876528314612', 2, '1549293751324695124', '孜然牛肉懒人火锅& & lt;自热& gt;', 123.00, NULL, 1, '', '2019-03-02 16:37:57', '2019-03-02 16:37:57');
INSERT INTO `order_detail` VALUES ('1551515937870359927', '1551515937864534904', 1, '1549292512782735611', '皮蛋粥', 3.50, NULL, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', '2019-03-02 16:38:58', '2019-03-02 16:38:58');
INSERT INTO `order_detail` VALUES ('1551515937879227605', '1551515937864534904', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-03-02 16:38:58', '2019-03-02 16:38:58');
INSERT INTO `order_detail` VALUES ('1551515959834921113', '1551515959831583426', 2, '1549293751324695124', '孜然牛肉懒人火锅& & lt;自热& gt;', 123.00, NULL, 1, '', '2019-03-02 16:39:20', '2019-03-02 16:39:20');
INSERT INTO `order_detail` VALUES ('1554019289348595896', '1554019289262983219', 1, '1549293308953779370', 'test', 123.00, NULL, 2, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-03-31 16:01:29', '2019-03-31 16:01:29');
INSERT INTO `order_detail` VALUES ('1554019289382448539', '1554019289262983219', 1, '1549292512782735611', '皮蛋粥', 3.90, NULL, 2, 'http://elliopeng.nat100.top.qiniudns.com/image/upload/ca75f6aa-c5b9-4484-acf9-3234043f733d/', '2019-03-31 16:01:29', '2019-03-31 16:01:29');
INSERT INTO `order_detail` VALUES ('1554019624727593436', '1554019624659467292', 1, '1549293308953779370', 'test', 123.00, NULL, 2, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-03-31 16:07:05', '2019-03-31 16:07:05');
INSERT INTO `order_detail` VALUES ('1554019624765714649', '1554019624659467292', 1, '1549292512782735611', '皮蛋粥', 3.90, NULL, 2, 'http://elliopeng.nat100.top.qiniudns.com/image/upload/ca75f6aa-c5b9-4484-acf9-3234043f733d/', '2019-03-31 16:07:05', '2019-03-31 16:07:05');
INSERT INTO `order_detail` VALUES ('1556032517611309753', '1556032517585532417', 1, '1549292512782735611', '皮蛋粥', 3.90, NULL, 2, 'http://elliopeng.nat100.top.qiniudns.com/image/upload/ca75f6aa-c5b9-4484-acf9-3234043f733d/', '2019-04-23 23:15:18', '2019-04-23 23:15:18');
INSERT INTO `order_detail` VALUES ('1556032517643886143', '1556032517585532417', 1, '1549293308953779370', 'test', 123.00, NULL, 2, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-04-23 23:15:18', '2019-04-23 23:15:18');
INSERT INTO `order_detail` VALUES ('1556910507270311807', '1556910507212153802', 1, '1549292512782735611', '皮蛋粥', 3.90, NULL, 1, 'http://elliopeng.nat100.top.qiniudns.com/image/upload/ca75f6aa-c5b9-4484-acf9-3234043f733d/', '2019-05-04 03:08:27', '2019-05-04 03:08:27');
INSERT INTO `order_detail` VALUES ('1556910507320277073', '1556910507212153802', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-05-04 03:08:27', '2019-05-04 03:08:27');
INSERT INTO `order_detail` VALUES ('1556910611498168550', '1556910611442641713', 1, '1549292512782735611', '皮蛋粥', 3.90, NULL, 1, 'http://elliopeng.nat100.top.qiniudns.com/image/upload/ca75f6aa-c5b9-4484-acf9-3234043f733d/', '2019-05-04 03:10:12', '2019-05-04 03:10:12');
INSERT INTO `order_detail` VALUES ('1556910611543654115', '1556910611442641713', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-05-04 03:10:12', '2019-05-04 03:10:12');
INSERT INTO `order_detail` VALUES ('1556911503029616126', '1556911503012299358', 1, '1549292512782735611', '皮蛋粥', 3.90, NULL, 1, 'http://elliopeng.nat100.top.qiniudns.com/image/upload/ca75f6aa-c5b9-4484-acf9-3234043f733d/', '2019-05-04 03:25:03', '2019-05-04 03:25:03');
INSERT INTO `order_detail` VALUES ('1556911503141651461', '1556911503012299358', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-05-04 03:25:03', '2019-05-04 03:25:03');
INSERT INTO `order_detail` VALUES ('1556911564498257090', '1556911564408602359', 1, '1549292512782735611', '皮蛋粥', 3.90, NULL, 1, 'http://elliopeng.nat100.top.qiniudns.com/image/upload/ca75f6aa-c5b9-4484-acf9-3234043f733d/', '2019-05-04 03:26:05', '2019-05-04 03:26:05');
INSERT INTO `order_detail` VALUES ('1556911564555595063', '1556911564408602359', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-05-04 03:26:05', '2019-05-04 03:26:05');
INSERT INTO `order_detail` VALUES ('1556911664169806221', '1556911664162883461', 1, '1549292512782735611', '皮蛋粥', 3.90, NULL, 1, 'http://elliopeng.nat100.top.qiniudns.com/image/upload/ca75f6aa-c5b9-4484-acf9-3234043f733d/', '2019-05-04 03:27:44', '2019-05-04 03:27:44');
INSERT INTO `order_detail` VALUES ('1556911664183811444', '1556911664162883461', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-05-04 03:27:44', '2019-05-04 03:27:44');
INSERT INTO `order_detail` VALUES ('1556993013531388110', '1556993013474177770', 1, '1549292512782735611', '皮蛋粥', 3.90, NULL, 1, 'http://elliopeng.nat100.top.qiniudns.com/image/upload/ca75f6aa-c5b9-4484-acf9-3234043f733d/', '2019-05-05 02:03:34', '2019-05-05 02:03:34');
INSERT INTO `order_detail` VALUES ('1556993013578261732', '1556993013474177770', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-05-05 02:03:34', '2019-05-05 02:03:34');
INSERT INTO `order_detail` VALUES ('1556993545998559393', '1556993545944725499', 1, '1549292512782735611', '皮蛋粥', 3.90, NULL, 1, 'http://elliopeng.nat100.top.qiniudns.com/image/upload/ca75f6aa-c5b9-4484-acf9-3234043f733d/', '2019-05-05 02:12:26', '2019-05-05 02:12:26');
INSERT INTO `order_detail` VALUES ('1556993546041470693', '1556993545944725499', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-05-05 02:12:26', '2019-05-05 02:12:26');
INSERT INTO `order_detail` VALUES ('1556993714753292613', '1556993714750636401', 1, '1549292512782735611', '皮蛋粥', 3.90, NULL, 1, 'http://elliopeng.nat100.top.qiniudns.com/image/upload/ca75f6aa-c5b9-4484-acf9-3234043f733d/', '2019-05-05 02:15:15', '2019-05-05 02:15:15');
INSERT INTO `order_detail` VALUES ('1556993714761480673', '1556993714750636401', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-05-05 02:15:15', '2019-05-05 02:15:15');
INSERT INTO `order_detail` VALUES ('1556994070331304579', '1556994070329465482', 1, '1549292512782735611', '皮蛋粥', 3.90, NULL, 1, 'http://elliopeng.nat100.top.qiniudns.com/image/upload/ca75f6aa-c5b9-4484-acf9-3234043f733d/', '2019-05-05 02:21:10', '2019-05-05 02:21:10');
INSERT INTO `order_detail` VALUES ('1556994070348748454', '1556994070329465482', 1, '1549293308953779370', 'test', 123.00, NULL, 1, 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', '2019-05-05 02:21:10', '2019-05-05 02:21:10');

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master`  (
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_type` int(11) NOT NULL COMMENT '店铺编号',
  `buyer_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8, 2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '订单状态, 默认为新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '支付状态, 默认未支付',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `idx_buyer_openid`(`buyer_openid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_master
-- ----------------------------
INSERT INTO `order_master` VALUES ('1551456844972929841', 1, ' 张三', '13642914834', ' 地址', 'ew3euwhd7sjw9diwkq', 3.50, 0, 0, '2019-03-02 00:14:05', '2019-03-02 00:14:05');
INSERT INTO `order_master` VALUES ('1551456845667533096', 1, ' 张三', '13642914834', ' 地址', 'ew3euwhd7sjw9diwkq', 3.50, 0, 0, '2019-03-02 00:14:06', '2019-03-02 00:14:06');
INSERT INTO `order_master` VALUES ('1551460717197811566', 1, ' 张三', '13642914834', ' 地址', 'ew3euwhd7sjw9diwkq', 0.00, 0, 0, '2019-03-02 01:18:37', '2019-03-02 01:18:37');
INSERT INTO `order_master` VALUES ('1551460798606153862', 1, ' 张三', '13642914834', ' 地址', 'ew3euwhd7sjw9diwkq', 0.00, 0, 0, '2019-03-02 01:19:59', '2019-03-02 01:19:59');
INSERT INTO `order_master` VALUES ('1551460989436456569', 1, ' 张三', '13642914834', ' 地址', 'ew3euwhd7sjw9diwkq', 123.00, 0, 0, '2019-03-02 01:23:09', '2019-03-02 01:23:09');
INSERT INTO `order_master` VALUES ('1551461089492936971', 1, ' 张三', '13642914834', ' 地址', 'ew3euwhd7sjw9diwkq', 123.00, 0, 0, '2019-03-02 01:24:50', '2019-03-02 01:24:50');
INSERT INTO `order_master` VALUES ('1551461115148954804', 1, ' 张三', '13642914834', ' 地址', 'ew3euwhd7sjw9diwkq', 3.50, 0, 0, '2019-03-02 01:25:15', '2019-03-02 01:25:15');
INSERT INTO `order_master` VALUES ('1551461247662901809', 1, ' 张三', '13642914834', ' 地址', 'ew3euwhd7sjw9diwkq', 123.00, 0, 0, '2019-03-02 01:27:28', '2019-03-02 01:27:28');
INSERT INTO `order_master` VALUES ('1551461291790737852', 1, ' 张三', '13642914834', ' 地址', 'ew3euwhd7sjw9diwkq', 123.00, 0, 0, '2019-03-02 01:34:35', '2019-03-02 01:34:35');
INSERT INTO `order_master` VALUES ('1551461711569942916', 1, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 123.00, 0, 0, '2019-03-02 01:35:14', '2019-03-02 01:35:14');
INSERT INTO `order_master` VALUES ('1551463147859845004', 1, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 123.00, 0, 0, '2019-03-02 01:59:12', '2019-03-02 01:59:12');
INSERT INTO `order_master` VALUES ('1551463283001138936', 1, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 126.50, 0, 0, '2019-03-02 02:01:25', '2019-03-02 02:01:25');
INSERT INTO `order_master` VALUES ('1551466543703524624', 1, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 126.50, 0, 0, '2019-03-02 02:55:44', '2019-03-02 02:55:44');
INSERT INTO `order_master` VALUES ('1551468114302724776', 1, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 126.50, 0, 0, '2019-03-02 03:21:55', '2019-03-02 03:21:55');
INSERT INTO `order_master` VALUES ('1551515632006129414', 1, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 126.50, 0, 0, '2019-03-02 16:33:52', '2019-03-02 16:33:52');
INSERT INTO `order_master` VALUES ('1551515670749689082', 1, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 126.50, 0, 0, '2019-03-02 16:34:31', '2019-03-02 16:34:31');
INSERT INTO `order_master` VALUES ('1551515746039744741', 1, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 126.50, 0, 0, '2019-03-02 16:35:46', '2019-03-02 16:35:46');
INSERT INTO `order_master` VALUES ('1551515876528314612', 2, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 123.00, 0, 0, '2019-03-02 16:37:57', '2019-03-02 16:37:57');
INSERT INTO `order_master` VALUES ('1551515937864534904', 1, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 126.50, 0, 0, '2019-03-02 16:38:58', '2019-03-02 16:38:58');
INSERT INTO `order_master` VALUES ('1551515959831583426', 2, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 123.00, 0, 0, '2019-03-02 16:39:20', '2019-03-02 16:39:20');
INSERT INTO `order_master` VALUES ('1554019289262983219', 1, '鑫辉', '123', '地标', 'ew3euwhd7sjw9diwkq', 253.80, 0, 0, '2019-03-31 16:01:29', '2019-03-31 16:01:29');
INSERT INTO `order_master` VALUES ('1554019624659467292', 1, '鑫辉', '123', '地标', 'ew3euwhd7sjw9diwkq', 253.80, 0, 0, '2019-03-31 16:07:05', '2019-03-31 16:07:05');
INSERT INTO `order_master` VALUES ('1556032517585532417', 1, '张三', '13500117851', '1-415', 'ew3euwhd7sjw9diwkq', 253.80, 0, 0, '2019-04-23 23:15:18', '2019-04-23 23:15:18');
INSERT INTO `order_master` VALUES ('1556910507212153802', 1, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 126.90, 0, 0, '2019-05-04 03:08:27', '2019-05-04 03:08:27');
INSERT INTO `order_master` VALUES ('1556910611442641713', 1, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 126.90, 0, 0, '2019-05-04 03:10:12', '2019-05-04 03:10:12');
INSERT INTO `order_master` VALUES ('1556911503012299358', 1, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 126.90, 0, 0, '2019-05-04 03:25:03', '2019-05-04 03:25:03');
INSERT INTO `order_master` VALUES ('1556911564408602359', 1, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 126.90, 0, 0, '2019-05-04 03:26:05', '2019-05-04 03:26:05');
INSERT INTO `order_master` VALUES ('1556911664162883461', 1, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 126.90, 0, 0, '2019-05-04 03:27:44', '2019-05-04 03:27:44');
INSERT INTO `order_master` VALUES ('1556993013474177770', 1, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 126.90, 0, 0, '2019-05-05 02:03:34', '2019-05-05 02:03:34');
INSERT INTO `order_master` VALUES ('1556993545944725499', 1, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 126.90, 0, 0, '2019-05-05 02:12:26', '2019-05-05 02:12:26');
INSERT INTO `order_master` VALUES ('1556993714750636401', 1, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 126.90, 0, 0, '2019-05-05 02:15:15', '2019-05-05 02:15:15');
INSERT INTO `order_master` VALUES ('1556994070329465482', 1, '1', '13642914834', '1-414', 'ew3euwhd7sjw9diwkq', 126.90, 0, 0, '2019-05-05 02:21:10', '2019-05-05 02:21:10');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类目名字',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `shop_type` int(11) NOT NULL COMMENT '店铺编号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (1, '热销榜', 1, 1, '2019-02-17 14:20:27', '2019-02-17 14:20:27');
INSERT INTO `product_category` VALUES (2, '热销榜', 1, 2, '2019-02-17 14:21:11', '2019-02-17 14:21:11');
INSERT INTO `product_category` VALUES (3, 'test', 2, 2, '2019-02-17 14:58:39', '2019-02-17 14:58:39');
INSERT INTO `product_category` VALUES (4, 'test', 2, 1, '2019-02-17 14:59:01', '2019-02-17 14:59:01');
INSERT INTO `product_category` VALUES (5, '热销榜', 1, 266627290, '2019-02-21 19:51:09', '2019-02-21 19:51:09');
INSERT INTO `product_category` VALUES (6, '冬天必备', 2, 266627290, '2019-02-21 19:51:32', '2019-02-21 19:51:32');
INSERT INTO `product_category` VALUES (7, 'test', 1, 3, '2019-02-23 03:12:12', '2019-02-23 03:12:12');
INSERT INTO `product_category` VALUES (8, '1111', 2, 3, '2019-02-23 03:18:59', '2019-02-23 03:25:12');
INSERT INTO `product_category` VALUES (9, 't', 3, 1, '2019-02-24 02:43:19', '2019-03-14 10:11:28');

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info`  (
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_price` decimal(8, 2) NOT NULL COMMENT '单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小图',
  `product_status` tinyint(3) NULL DEFAULT 0 COMMENT '商品状态,0正常1下架',
  `product_discount` decimal(8, 2) NULL DEFAULT NULL COMMENT '商品折扣',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `shop_type` int(11) NOT NULL COMMENT '店铺编号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `idx_product_id`(`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES ('1549292512782735611', '皮蛋粥', 3.90, 88, '123', 'http://elliopeng.nat100.top.qiniudns.com/image/upload/ca75f6aa-c5b9-4484-acf9-3234043f733d/', 0, NULL, 1, 1, '2019-02-04 23:01:52', '2019-05-05 02:21:11');
INSERT INTO `product_info` VALUES ('1549293308953779370', 'test', 123.00, 96, '123', 'http://pm5kff7hk.bkt.clouddn.com/image/upload/269887a4-e83a-4f3d-ae50-b8bdc068ab1d/', 0, NULL, 1, 1, '2019-02-04 23:15:09', '2019-05-05 02:21:11');
INSERT INTO `product_info` VALUES ('1549293590570911396', 'test', 123.00, 123, '超级辣', '123', 0, NULL, 1, 1, '2019-02-04 23:19:50', '2019-02-17 14:25:04');
INSERT INTO `product_info` VALUES ('1549293751324695124', '孜然牛肉懒人火锅& & lt;自热& gt;', 123.00, 181, '123', '', 0, NULL, 1, 2, '2019-02-04 23:22:31', '2019-03-02 16:39:32');
INSERT INTO `product_info` VALUES ('1550074385501845986', '孜然牛肉懒人火锅<自热>', 123.00, 123, '123', '123', 0, 12.30, 1, 1, '2019-02-14 00:13:05', '2019-02-17 14:25:55');
INSERT INTO `product_info` VALUES ('1550074687912431924', '爆辣辣条', 123.00, 123, '123', '213', 0, 12.30, 1, 1, '2019-02-14 00:18:07', '2019-02-14 00:18:07');
INSERT INTO `product_info` VALUES ('1550749926185378057', '孜然牛肉懒人火锅', 15.50, 15, '超好吃的火锅', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534604329030&di=04f81fe7d6e22a50a9bc9549a0dc8e65&imgtype=0&src=http%3A%2F%2Fpic.chinaz.com%2F2018%2F0127%2F18012720113695768.jpg', 0, 0.50, 1, 266627290, '2019-02-21 19:52:06', '2019-03-29 00:45:34');
INSERT INTO `product_info` VALUES ('1550862808543258315', '12', 1.00, 1, '1', '1', 0, NULL, 1, 3, '2019-02-23 03:13:28', '2019-02-23 03:13:43');
INSERT INTO `product_info` VALUES ('1551357365901279909', '科学实验器', 12.00, 1, '1', 'http://elliopeng.nat100.top.qiniudns.com/image/upload/c17ddfac-3ec0-472f-ba71-fecddcf5ce11/', 0, NULL, 1, 1, '2019-02-28 20:36:06', '2019-03-26 16:16:09');

-- ----------------------------
-- Table structure for seller_info
-- ----------------------------
DROP TABLE IF EXISTS `seller_info`;
CREATE TABLE `seller_info`  (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_type` int(11) NULL DEFAULT NULL COMMENT '店铺编号',
  `salt` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '盐',
  `status` int(64) NOT NULL COMMENT '账号状态',
  `openid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信openid',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `upe_seller_info_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '卖家信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seller_info
-- ----------------------------
INSERT INTO `seller_info` VALUES (7, '123', '1ecfc20d08581ea641e1903168e8eab3', 1, 'tmdp4', 1, 'abcs', '2019-04-06 20:13:59', '2019-05-13 09:06:58');
INSERT INTO `seller_info` VALUES (9, '321', 'f663ea8658928f7b5c02bbae710a926f', 2, 'stz2z', 1, 'abd', '2019-02-23 03:31:41', '2019-02-25 23:39:46');
INSERT INTO `seller_info` VALUES (18, 'admin', 'bb0823e4bdea3b3658ce261259ea5265', NULL, 'xj8h7', 1, NULL, '2019-02-24 01:50:20', '2019-02-24 01:50:20');

-- ----------------------------
-- Table structure for seller_role
-- ----------------------------
DROP TABLE IF EXISTS `seller_role`;
CREATE TABLE `seller_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `status` int(11) NOT NULL COMMENT '可用状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_role_name`(`name`) USING BTREE,
  UNIQUE INDEX `unique_role_value`(`value`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seller_role
-- ----------------------------
INSERT INTO `seller_role` VALUES (1, '管理员', 'admin', '2019-02-16 00:06:06', '2019-02-16 00:06:10', 1);
INSERT INTO `seller_role` VALUES (2, '超级管理员', 'super', '2019-02-16 00:07:43', '2019-02-16 00:07:46', 1);
INSERT INTO `seller_role` VALUES (3, '普通用户', 'user', '2019-02-16 00:08:05', '2019-02-16 00:08:07', 1);

-- ----------------------------
-- Table structure for seller_system_security
-- ----------------------------
DROP TABLE IF EXISTS `seller_system_security`;
CREATE TABLE `seller_system_security`  (
  `ip_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `seller_ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '非法IP',
  `seller_repeat` int(64) NOT NULL DEFAULT 0 COMMENT '强行登陆次数',
  `seller_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '非法登录用户',
  `failure_cause` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '错误原因',
  `create_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ip_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seller_system_security
-- ----------------------------
INSERT INTO `seller_system_security` VALUES ('1557715132791871500', '192.168.75.187', 16, NULL, '密码错误次数太多了', '2019-05-13 10:38:52.82');

-- ----------------------------
-- Table structure for seller_user_role
-- ----------------------------
DROP TABLE IF EXISTS `seller_user_role`;
CREATE TABLE `seller_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seller_user_role
-- ----------------------------
INSERT INTO `seller_user_role` VALUES (2, 7, 3, '2019-02-17 19:45:56', 'root');
INSERT INTO `seller_user_role` VALUES (3, 9, 3, '2019-02-18 22:28:41', 'root');
INSERT INTO `seller_user_role` VALUES (10, 18, 1, '2019-02-21 19:23:13', NULL);

-- ----------------------------
-- Table structure for shop_audit
-- ----------------------------
DROP TABLE IF EXISTS `shop_audit`;
CREATE TABLE `shop_audit`  (
  `audit_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `shop_founder` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店铺创建者',
  `shop_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店铺名称',
  `shop_description` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺描述',
  `shop_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者电话号码',
  `openid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家微信openid',
  `shop_img` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺图片',
  `shop_category_type` int(11) NOT NULL COMMENT '店铺类目编号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`audit_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_audit
-- ----------------------------
INSERT INTO `shop_audit` VALUES (6, 'sww', '123', 'asass', 'qqq', 'sdad', '13500557816', 'sda', NULL, 2, '2019-04-23 01:46:36', '2019-04-23 01:46:36');
INSERT INTO `shop_audit` VALUES (7, 'swww', '123', 'asass', 'qqqw', 'sdad', '13500557816', 'sda', NULL, 2, '2019-04-23 01:46:41', '2019-04-23 01:46:41');
INSERT INTO `shop_audit` VALUES (8, 'swwww', '123', 'asass', 'qqqww', 'sdad', '13500557816', 'sda', NULL, 2, '2019-04-23 01:46:45', '2019-04-23 01:46:45');
INSERT INTO `shop_audit` VALUES (9, 'swwwww', '123', 'asass', 'qqqwww', 'sdad', '13500557816', 'sda', NULL, 2, '2019-04-23 01:46:53', '2019-04-23 01:46:53');
INSERT INTO `shop_audit` VALUES (10, 'swwwwwq', '123', 'asass', 'qqqwwwq', 'sdad', '13500557816', 'sda', NULL, 2, '2019-04-23 01:46:58', '2019-04-23 01:46:58');
INSERT INTO `shop_audit` VALUES (11, 'swwwwwqq', '123', 'asass', 'qqqwwwqq', 'sdad', '13500557816', 'sda', NULL, 2, '2019-04-23 01:47:26', '2019-04-23 01:47:26');
INSERT INTO `shop_audit` VALUES (12, 'swwwwwqqa', '123', 'asass', 'qqqwwwqqa', 'sdad', '13500557816', 'sda', NULL, 2, '2019-04-23 01:52:29', '2019-04-23 01:52:29');
INSERT INTO `shop_audit` VALUES (13, 'swwwwwqqaa', '123', 'asass', 'qqqwwwqqaa', 'sdad', '13500557816', 'sda', NULL, 2, '2019-04-23 01:52:40', '2019-04-23 01:52:40');
INSERT INTO `shop_audit` VALUES (14, '360844558', 'zoupen..', 'hack', 'hack', 'hack', '13642914834', NULL, 'http://prf8h91yd.bkt.clouddn.com/image/upload/8169084f-d1e6-47e7-ac50-43791e55c980//storage/emulated/0/PictureSelector.temp.jpg', 1, '2019-05-13 10:38:42', '2019-05-13 10:38:42');

-- ----------------------------
-- Table structure for shop_category
-- ----------------------------
DROP TABLE IF EXISTS `shop_category`;
CREATE TABLE `shop_category`  (
  `shop_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_category_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店铺类目名字',
  `shop_category_type` int(11) NOT NULL COMMENT '店铺类目编号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`shop_category_id`) USING BTREE,
  UNIQUE INDEX `upe_shop_category_type`(`shop_category_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店铺类别' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_category
-- ----------------------------
INSERT INTO `shop_category` VALUES (1, '创业基地', 1, '2019-01-20 16:23:45', '2019-04-14 16:10:27');
INSERT INTO `shop_category` VALUES (2, '汤粉', 3, '2019-01-20 16:56:22', '2019-01-20 16:56:22');
INSERT INTO `shop_category` VALUES (3, 'aaa', 155522944, '2019-04-14 16:10:04', '2019-04-15 15:00:07');
INSERT INTO `shop_category` VALUES (4, 'ssss', 155531168, '2019-04-15 15:00:16', '2019-04-15 15:00:16');
INSERT INTO `shop_category` VALUES (5, '1', 155734798, '2019-05-09 04:38:52', '2019-05-09 04:42:54');

-- ----------------------------
-- Table structure for shop_info
-- ----------------------------
DROP TABLE IF EXISTS `shop_info`;
CREATE TABLE `shop_info`  (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_type` int(11) NOT NULL COMMENT '店铺编号',
  `shop_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店铺名称',
  `shop_founder` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店铺创建者',
  `shop_description` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺描述',
  `shop_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者电话号码',
  `openid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信openid',
  `shop_img` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺图片',
  `shop_status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '商店状态,0营业时间1非营业时间',
  `shop_category_type` int(11) NOT NULL COMMENT '店铺类目编号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`shop_id`) USING BTREE,
  UNIQUE INDEX `upe_shop_type`(`shop_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店铺信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_info
-- ----------------------------
INSERT INTO `shop_info` VALUES (1, 1, 'Test', 'test', '123456', '110', 'abcs', 'http://xxxx.com', 0, 1, '2019-04-06 20:13:59', '2019-05-08 01:01:27');
INSERT INTO `shop_info` VALUES (2, 2, '嘉鹏科技有限公司', '嘉鹏', '神仙店', '110', 'abd', 'http://xxxx.com', 0, 3, '2019-02-23 03:31:41', '2019-04-06 16:20:16');
INSERT INTO `shop_info` VALUES (3, 3, '123', '123', '123', '12345678', '123', '', 0, 3, '2019-02-23 03:10:54', '2019-04-07 18:13:29');
INSERT INTO `shop_info` VALUES (4, 156278214, 'hack', 'hack', 'hack', '13642914834', NULL, 'http://prf8h91yd.bkt.clouddn.com/image/upload/8169084f-d1e6-47e7-ac50-43791e55c980//storage/emulated/0/PictureSelector.temp.jpg', 1, 1, '2019-07-11 02:08:02', '2019-07-11 02:08:02');
INSERT INTO `shop_info` VALUES (5, 156278217, 'hack', 'hack', 'hack', '13642914834', NULL, 'http://prf8h91yd.bkt.clouddn.com/image/upload/8169084f-d1e6-47e7-ac50-43791e55c980//storage/emulated/0/PictureSelector.temp.jpg', 1, 1, '2019-07-11 02:08:05', '2019-07-11 02:08:05');

SET FOREIGN_KEY_CHECKS = 1;
