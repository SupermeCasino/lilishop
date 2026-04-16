DROP TABLE IF EXISTS `li_member_group`;
CREATE TABLE `li_member_group` (
                                   `id` bigint NOT NULL COMMENT 'ID',
                                   `create_by` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者',
                                   `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
                                   `delete_flag` bit(1) DEFAULT NULL COMMENT '删除标志 true/false 删除/未删除',
                                   `update_by` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新者',
                                   `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
                                   `group_name` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '分组名称',
                                   `description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分组描述',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=Dynamic;

DROP TABLE IF EXISTS `li_member_group_user`;
CREATE TABLE `li_member_group_user` (
                                        `id` bigint NOT NULL COMMENT 'ID',
                                        `create_by` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者',
                                        `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
                                        `delete_flag` bit(1) DEFAULT NULL COMMENT '删除标志 true/false 删除/未删除',
                                        `update_by` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新者',
                                        `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
                                        `group_id` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '分组ID',
                                        `member_id` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '会员ID',
                                        PRIMARY KEY (`id`),
                                        UNIQUE KEY `uniq_group_member` (`group_id`,`member_id`),
                                        KEY `idx_group_id` (`group_id`),
                                        KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=Dynamic;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE IF NOT EXISTS `li_goods_group` (
  `id` bigint NOT NULL COMMENT 'ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `delete_flag` bit(1) DEFAULT b'0' COMMENT '删除标志 true/false 删除/未删除',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `group_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '分组名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分组描述',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_li_goods_group_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='商品分组';

CREATE TABLE IF NOT EXISTS `li_goods_group_goods` (
  `id` bigint NOT NULL COMMENT 'ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `delete_flag` bit(1) DEFAULT b'0' COMMENT '删除标志 true/false 删除/未删除',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `group_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '分组ID',
  `goods_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '商品ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_li_goods_group_goods` (`group_id`,`goods_id`) USING BTREE,
  KEY `idx_li_goods_group_goods_group_id` (`group_id`) USING BTREE,
  KEY `idx_li_goods_group_goods_goods_id` (`goods_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='商品分组商品关联';

SET FOREIGN_KEY_CHECKS = 1;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 在 商品 -> 关联管理/关联商品 下新增子菜单：商品分组
-- parent_id = 1367044657296441344 （二级目录 association / Main）
-- 若已存在同 parent 且同 path 节点，则不会重复插入
INSERT INTO `li_menu` (
  `id`,
  `create_by`,
  `create_time`,
  `delete_flag`,
  `update_by`,
  `update_time`,
  `description`,
  `front_route`,
  `icon`,
  `level`,
  `name`,
  `parent_id`,
  `path`,
  `sort_order`,
  `title`,
  `front_component`,
  `permission`
)
SELECT
  200401020001000001,
  'admin',
  NOW(),
  b'0',
  'admin',
  NOW(),
  '商品-关联商品子菜单-商品分组',
  'goods/group/index',
  'md-list',
  2,
  'managerGoodsGroup',
  '1367044657296441344',
  'goods-group',
  3.00,
  '商品分组',
  NULL,
  '/manager/goods/goodsGroup*'
FROM DUAL
WHERE NOT EXISTS (
  SELECT 1
  FROM `li_menu`
  WHERE `parent_id` = '1367044657296441344'
    AND `path` = 'goods-group'
);

SET FOREIGN_KEY_CHECKS = 1;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 在 商品 -> 关联管理/关联商品 下新增子菜单：虚拟销量
-- parent_id = 1367044657296441344 （二级目录 association / Main）
-- 若已存在同 parent 且同 path 节点，则不会重复插入
INSERT INTO `li_menu` (
  `id`,
  `create_by`,
  `create_time`,
  `delete_flag`,
  `update_by`,
  `update_time`,
  `description`,
  `front_route`,
  `icon`,
  `level`,
  `name`,
  `parent_id`,
  `path`,
  `sort_order`,
  `title`,
  `front_component`,
  `permission`
)
SELECT
  200401020001000002,
  'admin',
  NOW(),
  b'0',
  'admin',
  NOW(),
  '商品-关联商品子菜单-虚拟销量',
  'goods/virtual-sales/index',
  'md-stats',
  2,
  'goods-virtual-sales',
  '1367044657296441344',
  'goods-virtual-sales',
  3.50,
  '虚拟销量',
  NULL,
  '/manager/goods/goods/list*,/manager/goods/goods/get*'
FROM DUAL
WHERE NOT EXISTS (
  SELECT 1
  FROM `li_menu`
  WHERE `parent_id` = '1367044657296441344'
    AND `path` = 'goods-virtual-sales'
);

SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE `li_goods_sku`
ADD COLUMN `virtual_sales` INT DEFAULT 0 COMMENT '虚拟销量' AFTER `buy_count`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- 扩展发票信息字段（买家发票详情/订单发票信息使用）
ALTER TABLE `li_receipt`
  ADD COLUMN `receipt_type` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '发票类型：电子普通发票/增值税专用发票' AFTER `receipt_title`,
  ADD COLUMN `receipt_phone` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '收票人手机号' AFTER `taxpayer_id`,
  ADD COLUMN `receipt_email` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '收票人邮箱' AFTER `receipt_phone`,
  ADD COLUMN `company_address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '单位地址' AFTER `receipt_email`,
  ADD COLUMN `company_phone` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '单位电话' AFTER `company_address`,
  ADD COLUMN `bank_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '开户银行' AFTER `company_phone`,
  ADD COLUMN `bank_account` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '银行账户' AFTER `bank_name`,
  ADD COLUMN `personal_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '个人名称' AFTER `receipt_title`,
  ADD COLUMN `company_name` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '单位名称' AFTER `personal_name`,
 ADD COLUMN `invoice_address` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '发票地址(URL)' AFTER `company_name`;

ALTER TABLE `li_member_evaluation`
    ADD COLUMN `top` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否置顶' AFTER `reply_status`;


