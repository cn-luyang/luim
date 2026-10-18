CREATE TABLE `t_client`
(
    `id`                     BIGINT       NOT NULL COMMENT '主键ID',
    `client_id`              VARCHAR(64)  NOT NULL COMMENT '客户端ID',
    `client_name`            VARCHAR(64)  NOT NULL COMMENT '客户端名称',
    `client_secret`          VARCHAR(128) NOT NULL COMMENT '客户端密钥',
    `redirect_uris`          JSON         DEFAULT NULL COMMENT '授权回调地址列表',
    `access_token_validity`  INT          DEFAULT 7200 COMMENT '访问令牌有效期(秒)',
    `refresh_token_validity` INT          DEFAULT 604800 COMMENT '刷新令牌有效期(秒)',
    `description`            VARCHAR(256) DEFAULT NULL COMMENT '应用描述',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_client_id` (`client_id`)
) ENGINE = InnoDB COMMENT = '客户端表';

CREATE TABLE `t_token`
(
    `id`                         BIGINT        NOT NULL COMMENT '主键ID',
    `client_id`                  VARCHAR(64)   NOT NULL COMMENT '客户端ID',
    `user_id`                    VARCHAR(64)   DEFAULT NULL COMMENT '用户ID',
    `access_token`               VARCHAR(1024) NOT NULL COMMENT '访问令牌',
    `refresh_token`              VARCHAR(1024) DEFAULT NULL COMMENT '刷新令牌',
    `access_token_issued_time`   DATETIME(3)   NOT NULL COMMENT 'AccessToken签发时间',
    `access_token_expires_time`  DATETIME(3)   NOT NULL COMMENT 'AccessToken过期时间',
    `refresh_token_expires_time` DATETIME(3)   DEFAULT NULL COMMENT 'RefreshToken过期时间',
    `extra_info`                 JSON          DEFAULT NULL COMMENT '附加信息',
    `created_by`                 VARCHAR(64)   DEFAULT NULL COMMENT '创建人',
    `created_time`               DATETIME(3) DEFAULT NULL COMMENT '创建时间',
    `updated_by`                 VARCHAR(64)   DEFAULT NULL COMMENT '更新人',
    `updated_time`               DATETIME(3) DEFAULT NULL COMMENT '更新时间',
    `deleted`                    BOOLEAN       DEFAULT FALSE COMMENT '是否删除: {[1:删除:true] [0:未删除:false]}',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_access_token` (`access_token`(255) ASC) USING BTREE COMMENT 'access_token唯一索引',
    UNIQUE INDEX `uk_refresh_token` (`refresh_token`(255) ASC) USING BTREE COMMENT 'refresh_token唯一索引',
    INDEX                        `idx_client_id` (`client_id` ASC) USING BTREE COMMENT '按客户端查询加速'
) ENGINE = InnoDB COMMENT = 'OAuth2 Token表';
