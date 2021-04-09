 CREATE TABLE `t_user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_num` bigint unsigned DEFAULT NULL,
  `user_name` varchar(20) NOT NULL COMMENT '姓名',
  `phone` varchar(40) NOT NULL COMMENT '电话',
  `mailbox` varchar(80) NOT NULL COMMENT '邮箱',
  `role` mediumint NOT NULL COMMENT '角色:0-管理员,1-教师，2-学生',
  `password` char(55) NOT NULL COMMENT '密码密文',
  `salt` char(35) NOT NULL COMMENT '盐密钥',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `mailbox` (`mailbox`),
  UNIQUE KEY `user_num` (`user_num`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账号表';

 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
 update t_user set role=2 where id>4;