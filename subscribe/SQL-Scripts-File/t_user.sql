CREATE TABLE t_user (
id int(25) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键', 
user_num int(55) unsigned NOT NULL COMMENT '学号/职工号',
user_name varchar(20) NOT NULL COMMENT '姓名', 
phone varchar(40) NOT NULL COMMENT '电话',              
mailbox varchar(80) NOT NULL COMMENT '邮箱',
role mediumint(1) NOT NULL COMMENT '角色:0-管理员,1-教师，2-学生',
password char(55) NOT NULL COMMENT '密码密文',                            
salt char(35) NOT NULL COMMENT '盐密钥',
PRIMARY KEY (`id`),                                                                                                              
UNIQUE KEY `user_num` (`user_num`),
UNIQUE KEY `phone` (`phone`),                                                               
UNIQUE KEY `mailbox` (`mailbox`)                                               
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账号表';