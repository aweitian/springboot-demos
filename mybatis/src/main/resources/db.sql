create database if not exists yonghedb charset utf8;

CREATE TABLE `emp` (
   `id` int(11) NOT NULL AUTO_INCREMENT,
   `name` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
   `job` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
   `salary` double DEFAULT NULL,
   `dept_id` int(10) unsigned DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

CREATE TABLE `dept` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

insert into emp values(null, '张三', '程序员', 3300,1);
insert into emp values(null, '李四', '程序员', 2800,1);
insert into emp values(null, '王五', '程序员鼓励师', 2700,1);
insert into emp values(null, '王二', '部门总监', 4200,1);
insert into emp values(null, '麻子', '程序员', 3000,1);
insert into emp values(null, '最帅三太子', '程序员', 3500,1);
insert into emp values(null, '苍老师', '程序员', 3700,1);
insert into emp values(null, '波多野结衣', 'CEO', 5000,2);


insert  into `dept`(`id`,`name`) values
                                     (1,'开发部'),
                                     (2,'人事部');


