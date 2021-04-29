CREATE TABLE `t_subscribe` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `applicant` bigint unsigned NOT NULL COMMENT '申请者(必须是学生,对应学号)',
  `reviewer`  bigint DEFAULT NULL COMMENT 'Reviewer(Must be Teacher,corresponding Teacher Number)',
  `subscribe_status` mediumint NOT NULL COMMENT '预约状态:0-审核中,1-预约成功,2-预约失败,3-取消预约',
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

mysql> INSERT INTO t_subscribe(applicant,subscribe_status,room_num,use_interval,apply_use_date,application_start_time) VALUES (1889970,1,2,2,'2021-04-11','2021-03-21 12:12:12');
Query OK, 1 row affected (0.19 sec)

----------------------
SELECT id,room_num FROM t_subscribe WHERE applicant=学号 AND subscribe_status=成功(1) AND apply_use_date=同一天 AND use_interval=同时段;

SELECT id,room_num FROM t_subscribe WHERE applicant=188997 AND subscribe_status=1 AND apply_use_date='2021-04-21 16:00:00' AND use_interval=2;

mysql> SELECT id,room_num AS room,application_start_time AS start_time FRO
M t_subscribe WHERE applicant=188997 AND subscribe_status=0 AND apply_use_date='2021-04-21 16:00:00' AND use_interval=2;
+----+------+---------------------+
| id | room | start_time          |
+----+------+---------------------+
| 12 |    1 | 2021-04-12 07:18:24 |
+----+------+---------------------+
1 row in set (0.00 sec)

mysql> SELECT id,room_num AS room,application_start_time AS start_time FROM t_subscribe WHERE applicant=188997 AND subscribe_status=0 AND apply_use_date='2021-04-21 16:00:00' AND use_interval=1;
+----+------+---------------------+
| id | room | start_time          |
+----+------+---------------------+
| 11 |    1 | 2021-04-12 07:18:09 |
+----+------+---------------------+
1 row in set (0.00 sec)

mysql> SELECT COUNT(id) FROM t_subscribe WHERE subscribe_status=1 AND room_num=1;
----------------
