CREATE TABLE `id_segment_config`
(
    `id`            BIGINT      NOT NULL COMMENT '主键',
    `business_code` VARCHAR(32) NOT NULL COMMENT '业务标识',
    `id_prefix`     INT         NOT NULL COMMENT 'ID前缀，编入ID高位',
    `init_step`     INT         NOT NULL DEFAULT 100 COMMENT '初始步长（首次申请用）',
    `min_step`      INT         NOT NULL DEFAULT 100 COMMENT '最小步长',
    `max_step`      INT         NOT NULL DEFAULT 100000 COMMENT '最大步长',
    `description`   VARCHAR(256)         DEFAULT NULL COMMENT '描述',
    `status`        TINYINT     NOT NULL DEFAULT 1 COMMENT '状态：1-启用 2-停用',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_business_code` (`business_code`),
    UNIQUE KEY `uk_id_prefix` (`id_prefix`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='号段配置表';


CREATE TABLE `id_segment_alloc`
(
    `id`            BIGINT       NOT NULL COMMENT '主键',
    `business_code` VARCHAR(128) NOT NULL COMMENT '业务标识',
    `current_max`   BIGINT       NOT NULL DEFAULT 0 COMMENT '当前已分配到的最大号段值',
    `current_step`  INT          NOT NULL DEFAULT 1000 COMMENT '动态调整后的当前步长',
    `update_time`   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_business_code` (`business_code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='号段游标状态表';

INSERT INTO `id_segment_alloc`
    (`business_code`, `max_id`, `step`, `min_step`)
VALUES ('__registry_id__', 0, 100, 100),
       ('__prefix_alloc__', 0, 100, 100);
