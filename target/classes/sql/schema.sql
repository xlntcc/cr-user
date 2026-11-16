CREATE DATABASE IF NOT EXISTS car_rental DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE car_rental;

-- 用户表
CREATE TABLE IF NOT EXISTS t_user (
    user_id     BIGINT       NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    username    VARCHAR(64) NOT NULL COMMENT '用户名',
    password    VARCHAR(128) NOT NULL COMMENT '密码(BCrypt加密存储)',
    nickname    VARCHAR(64) DEFAULT NULL COMMENT '昵称',
    email       VARCHAR(128) DEFAULT NULL COMMENT '邮箱',
    phone       VARCHAR(20) DEFAULT NULL COMMENT '手机号',
    status      TINYINT      DEFAULT 1 COMMENT '状态 0-禁用 1-启用',
    create_time DATETIME     DEFAULT NULL COMMENT '创建时间',
    update_time DATETIME     DEFAULT NULL COMMENT '更新时间',
    deleted     TINYINT      DEFAULT 0 COMMENT '逻辑删除 0-未删 1-已删',
    PRIMARY KEY (user_id),
    UNIQUE KEY uk_username (username)
) ENGINE = InnoDB COMMENT = '用户表';
