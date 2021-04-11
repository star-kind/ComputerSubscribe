CREATE TABLE `t_subscribe` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `applicant` bigint unsigned NOT NULL COMMENT '申请者(必须是学生,对应学号)',
  `reviewer`  bigint DEFAULT NULL COMMENT 'Reviewer(Must be Teacher,corresponding Teacher Number)',
  `subscribe_status` mediumint NOT NULL COMMENT '预约状态:0-审核中,1-预约成功,2-预约失败,4-取消预约',
  `room_num` mediumint NOT NULL COMMENT '所申请的机房编号',
  `application_start_time` timestamp NOT NULL COMMENT '申请发起时间',
  `use_interval` mediumint NOT NULL COMMENT 'apply for the use time of computer room:0-morning,1-afternoon,2-night',
  
  `handle_time` timestamp NULL DEFAULT NULL COMMENT 'The Time is Subscribe Processing time',
  `apply_use_date` timestamp NOT NULL COMMENT 'the specific date of application using',
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='预约申请单子表';
-------------------------------
mysql> alter table t_subscribe change application_date apply_use_date timestamp
    -> not null comment 'the specific date of application using';
Query OK, 0 rows affected (0.61 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> alter table t_subscribe add application_date timestamp not null comment 'Application Date';
Query OK, 0 rows affected (1.27 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> ALTER TABLE t_subscribe CHANGE handle_time handle_time timestamp DEFAULT NULL COMMENT 
'The Time is Subscribe Processing time';

mysql> alter table t_subscribe modify use_time mediumint;
Query OK, 0 rows affected (3.82 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> alter table t_subscribe change use_interval use_interval mediumint
not null comment 'apply for the use time of computer room:0-morning,1-afternoon,2-night';

mysql> ALTER TABLE t_subscribe CHANGE reviewer reviewer bigint DEFAULT NULL COMMENT 
'Reviewer(Must be Teacher,corresponding Teacher Number)';