drop database if exists ssolearning;

create database if not exists ssolearning;

use ssolearning;

-- 用户表
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
                       username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
                       password VARCHAR(255) NOT NULL COMMENT '用户密码',
                       email VARCHAR(100) UNIQUE COMMENT '邮箱地址',
                       phone VARCHAR(20) UNIQUE COMMENT '手机号',
                       enabled TINYINT(1) DEFAULT 1 COMMENT '是否启用: 1=启用, 0=禁用',
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                       updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='用户表';

-- 角色表
CREATE TABLE roles (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '角色ID',
                       name VARCHAR(50) NOT NULL UNIQUE COMMENT '角色名称',
                       description VARCHAR(255) COMMENT '角色描述',
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                       updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='角色表';

-- 权限表
CREATE TABLE permissions (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '权限ID',
                             name VARCHAR(50) NOT NULL UNIQUE COMMENT '权限名称',
                             description VARCHAR(255) COMMENT '权限描述',
                             created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='权限表';

-- 用户角色关系表 (多对多)
CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL COMMENT '用户ID',
                            role_id BIGINT NOT NULL COMMENT '角色ID',
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                            FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
) COMMENT='用户-角色关系表';

-- 角色权限关系表 (多对多)
CREATE TABLE role_permissions (
                                  role_id BIGINT NOT NULL COMMENT '角色ID',
                                  permission_id BIGINT NOT NULL COMMENT '权限ID',
                                  PRIMARY KEY (role_id, permission_id),
                                  FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
                                  FOREIGN KEY (permission_id) REFERENCES permissions(id) ON DELETE CASCADE
) COMMENT='角色-权限关系表';

-- 操作日志表（可选，用于记录权限相关的操作日志）
CREATE TABLE operation_logs (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '日志ID',
                                user_id BIGINT COMMENT '操作用户ID',
                                action VARCHAR(255) COMMENT '操作内容',
                                created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
                                FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
) COMMENT='操作日志表';