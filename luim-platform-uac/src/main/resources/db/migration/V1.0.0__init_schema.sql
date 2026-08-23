CREATE TABLE `uac_user`
(
    `id`        BIGINT UNSIGNED NOT NULL COMMENT '主键ID',
    `real_name` VARCHAR(64)  NOT NULL COMMENT '真实姓名',
    `email`     VARCHAR(128) NOT NULL COMMENT '邮箱地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

CREATE TABLE `uac_dept`
(
    `id`         BIGINT UNSIGNED  NOT NULL COMMENT '主键ID',
    `parent_id`  BIGINT UNSIGNED  NOT NULL COMMENT '父部门ID',
    `dept_name`  VARCHAR(64)  NOT NULL COMMENT '部门名称',
    `dept_level` INT UNSIGNED     NOT NULL COMMENT '部门层级',
    `dept_path`  VARCHAR(512) NOT NULL COMMENT '部门全路径',
    `sort_order` INT          NOT NULL DEFAULT 1 COMMENT '排序顺序',
    `status`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 1-启用, 2-禁用',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='部门表';

CREATE TABLE `uac_user_dept`
(
    `id`      BIGINT UNSIGNED  NOT NULL COMMENT '主键ID',
    `user_id` BIGINT UNSIGNED  NOT NULL COMMENT '用户ID',
    `dept_id` BIGINT UNSIGNED  NOT NULL COMMENT '部门ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_dept` (`user_id`, `dept_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户与部门关联表';
