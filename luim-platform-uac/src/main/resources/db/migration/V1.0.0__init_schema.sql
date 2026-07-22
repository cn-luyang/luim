CREATE TABLE `t_account`
(
    `id`           BIGINT UNSIGNED  NOT NULL COMMENT '主键ID',
    `user_id`      VARCHAR(64)      NOT NULL COMMENT '用户ID',
    `account`      VARCHAR(64)      NOT NULL COMMENT '登录账号',
    `account_type` TINYINT UNSIGNED NOT NULL COMMENT '账号类型: 1-手机号, 2-邮箱号',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '账户表';

CREATE TABLE `t_password`
(
    `id`            BIGINT UNSIGNED NOT NULL COMMENT '主键ID',
    `account_id`    BIGINT UNSIGNED NOT NULL COMMENT '账号ID',
    `password_hash` VARCHAR(255)    NOT NULL COMMENT '密码哈希',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '账号密码表';

CREATE TABLE `t_user`
(
    `id`      BIGINT UNSIGNED  NOT NULL COMMENT '主键ID',
    `user_id` VARCHAR(64)      NOT NULL COMMENT '用户ID',
    `cn_name` VARCHAR(64)               DEFAULT NULL COMMENT '中文名',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户表';
