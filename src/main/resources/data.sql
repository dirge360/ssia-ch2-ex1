-- 插入用户
INSERT INTO users (username, password, email)
VALUES ('admin', 'encrypted_password', 'admin@example.com');

-- 插入角色
INSERT INTO roles (name, description)
VALUES ('ADMIN', 'Administrator role with full permissions');

-- 插入权限
INSERT INTO permissions (name, description)
VALUES ('CREATE_USER', 'Permission to create new users'),
       ('DELETE_USER', 'Permission to delete users'),
       ('VIEW_REPORTS', 'Permission to view reports');

-- 为用户分配角色
INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1); -- 给用户ID为1的用户分配角色ID为1的角色

-- 为角色分配权限
INSERT INTO role_permissions (role_id, permission_id)
VALUES (1, 1), -- 给角色ID为1的角色分配权限ID为1
       (1, 2),
       (1, 3);
