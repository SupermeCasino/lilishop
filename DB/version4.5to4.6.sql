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