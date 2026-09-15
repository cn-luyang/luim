CREATE TABLE `uac_user`
(
    `id`        BIGINT UNSIGNED NOT NULL COMMENT '主键ID',
    `real_name` VARCHAR(64)  NOT NULL COMMENT '真实姓名',
    `email`     VARCHAR(128) NOT NULL COMMENT '个人邮箱',
    `user_type` TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '用户类型：1-员工',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

CREATE TABLE `uac_account`
(
    `id`           BIGINT UNSIGNED NOT NULL COMMENT '主键ID',
    `user_id`      BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
    `account`      VARCHAR(128) NOT NULL COMMENT '登录账号',
    `account_type` TINYINT(1)  NOT NULL COMMENT '账号类型：1-个人邮箱 2-工作邮箱 3-手机号 4-工号',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_identifier_type` (`account`, `account_type`),
    KEY            `idx_user_id` (`user_id`)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci COMMENT='登录账号表';

CREATE TABLE `uac_password`
(
    `id`            BIGINT UNSIGNED NOT NULL COMMENT '主键ID',
    `user_id`       BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
    `password_hash` VARCHAR(255) NOT NULL COMMENT '密码Hash值',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci COMMENT='密码表';

CREATE TABLE `uac_employee`
(
    `id`            BIGINT UNSIGNED NOT NULL COMMENT '主键ID',
    `user_id`       BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
    `dept_id`       BIGINT UNSIGNED NOT NULL COMMENT '部门ID',
    `employee_no`   VARCHAR(64)  NOT NULL COMMENT '员工工号',
    `work_email`    VARCHAR(128) NOT NULL COMMENT '工作邮箱',
    `employee_type` TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '员工类型：1-正式员工 2-实习生 3-外包员工',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci COMMENT='员工表';

CREATE TABLE `uac_dept`
(
    `id`         BIGINT UNSIGNED  NOT NULL COMMENT '主键ID',
    `parent_id`  BIGINT UNSIGNED  NOT NULL COMMENT '父部门ID',
    `dept_name`  VARCHAR(64)  NOT NULL COMMENT '部门名称',
    `dept_level` INT UNSIGNED     NOT NULL COMMENT '部门层级',
    `dept_path`  VARCHAR(512) NOT NULL COMMENT '部门全路径',
    `sort_order` INT          NOT NULL DEFAULT 1 COMMENT '排序顺序',
    `status`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 1-启用， 2-禁用',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='部门表';
