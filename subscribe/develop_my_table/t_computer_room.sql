CREATE TABLE `t_computer_room` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `room_num` int NOT NULL COMMENT '机房房间编号',
  `total_sets` int unsigned NOT NULL COMMENT '机位总数',
  `available_status` mediumint NOT NULL COMMENT '机房是否可用,0-不可用,1-可用',
  `admin_num_operated` bigint unsigned DEFAULT NULL COMMENT '本次进行操作(增删改)的管理员之工号',
  `operated_time` timestamp DEFAULT NULL COMMENT '本次操作(增删改)时间',
  `location` varchar(120) NOT NULL COMMENT '地点位置',
	`act_available_quantity` int NOT NULL COMMENT 'actual number of computers available in the computer room',
	 
  PRIMARY KEY (`id`),
  UNIQUE KEY `room_num` (`room_num`),
  UNIQUE KEY `location` (`location`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机房信息表';
----------
room_num 机房房间编号-int
total_sets 机位总数-int
available_status 机房是否可用,0-不可用,1-可用-int
act_available_quantity 机房内实际可用电脑数量-int
admin_num_operated 本次进行操作(增删改)的管理员之工号-long
operated_time 本次操作(增删改)时间-timestamp
location 地点位置-varchar
-----------
mysql> alter table t_computer_room add act_available_quantity int not null comment 'actual number of computers available in the computer room';
Query OK, 0 rows affected (2.83 sec)
Records: 0  Duplicates: 0  Warnings: 0
