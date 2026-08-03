CREATE TABLE `t_user`
(
    `id`            BIGINT UNSIGNED NOT NULL  COMMENT '主键ID',
    `nickname`      VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '显示昵称/花名',
    `avatar_url`    VARCHAR(512) NOT NULL DEFAULT '' COMMENT '头像地址',
    `phone`         VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '个人手机号(E.164格式, 如+8613800138000, 仅用于登录/找回密码)',
    `user_type`     TINYINT UNSIGNED NOT NULL DEFAULT '1' COMMENT '账号类型: 1-普通用户, 2-系统管理员, 3-机器人/应用服务号',
    `status`        TINYINT UNSIGNED NOT NULL DEFAULT '1' COMMENT '账号状态: 0-禁用/冻结, 1-正常',
    `custom_status` VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '实时IM状态(如: 开会中, 隐身, 勿扰)',
    `last_login_at` DATETIME              DEFAULT NULL COMMENT '最后登录时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

CREATE TABLE `t_employee`
(
    `id`                BIGINT UNSIGNED NOT NULL COMMENT '主键ID',
    `user_id`           BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
    `work_no`           VARCHAR(64)  NOT NULL COMMENT '工号',
    `real_name`         VARCHAR(64)  NOT NULL COMMENT '真实姓名(名片展示)',

    `work_phone`        VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '工作手机号(E.164格式)',
    `work_email`        VARCHAR(128) NOT NULL DEFAULT '' COMMENT '工作邮箱',

    -- 岗位与人事状态
    `position`          VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '主职务/头衔',
    `direct_leader_id`  BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '直属上级员工ID',
    `employment_type`   TINYINT UNSIGNED NOT NULL DEFAULT '1' COMMENT '雇佣类型: 1-正式, 2-试用, 3-实习, 4-外包, 5-顾问',
    `employment_status` TINYINT UNSIGNED NOT NULL DEFAULT '2' COMMENT '在职状态: 1-待入职, 2-在职, 3-休假中, 4-已离职',
    `hired_date`          DATE                  DEFAULT NULL COMMENT '入职日期',
    `resigned_date`       DATE                  DEFAULT NULL COMMENT '离职日期',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工人事表';


CREATE TABLE `t_dept`
(
    `id`         BIGINT UNSIGNED  NOT NULL COMMENT '主键ID',
    `parent_id`  BIGINT UNSIGNED  NOT NULL COMMENT '父部门ID (0:虚拟根节点=公司本身)',
    `dept_name`  VARCHAR(64)      NOT NULL COMMENT '部门名称',
    `level`      INT UNSIGNED     NOT NULL COMMENT '部门层级',
    `path`       VARCHAR(512)     NOT NULL COMMENT '祖先路径前缀',
    `sort_order` INT              NOT NULL DEFAULT 1 COMMENT '排序权值 (数值越大越靠前)',
    `status`     TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态: 1-启用, 2-禁用',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='部门表';
