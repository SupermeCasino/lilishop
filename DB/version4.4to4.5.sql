SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `li_category_parameter_group_rel`;
CREATE TABLE `li_category_parameter_group_rel` (
  `id` bigint NOT NULL COMMENT 'ID',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `delete_flag` bit(1) DEFAULT NULL COMMENT '删除标志 true/false 删除/未删除',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(6) DEFAULT NULL COMMENT '更新时间',
  `category_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分类ID',
  `group_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '参数组ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_li_category_parameter_group_rel_category_group` (`category_id`,`group_id`) USING BTREE,
  KEY `idx_li_category_parameter_group_rel_category_id` (`category_id`) USING BTREE,
  KEY `idx_li_category_parameter_group_rel_group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='分类-参数组关联';

INSERT IGNORE INTO `li_category_parameter_group_rel` (
  `id`,`create_by`,`create_time`,`delete_flag`,`update_by`,`update_time`,`category_id`,`group_id`
)
SELECT
  `id`,`create_by`,`create_time`,`delete_flag`,`update_by`,`update_time`,`category_id`,`id`
FROM `li_category_parameter_group`
WHERE `category_id` IS NOT NULL;

ALTER TABLE `li_category_parameter_group` DROP COLUMN `category_id`;

ALTER TABLE `li_parameters`
  MODIFY COLUMN `category_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分类id';

SET FOREIGN_KEY_CHECKS = 1;
