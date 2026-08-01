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
