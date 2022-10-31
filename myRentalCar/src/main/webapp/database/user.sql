--CREATE TABLE
CREATE TABLE `user` (
   `no` int(11) NOT NULL,
   `id` varchar(40) NOT NULL,
   `password` varchar(40) NOT NULL,
   `name` varchar(10) NOT NULL,
   `phone` varchar(20) NOT NULL,
   `address` varchar(200) NOT NULL,
   `license` varchar(100) NOT NULL,
   `regDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (`no`),
   UNIQUE KEY `id` (`id`),
   UNIQUE KEY `license` (`license`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
 --INSERT
 
 insert into `user`(
	`no`
    ,`id`
    ,`password`
    ,`name`
    ,phone
    ,address
    ,license
 )values(
	7
    ,'apple'
    ,'1234'
    ,'이혜량'
    ,'010-1111-1111'
    ,'경기도 성남시 분당구'
    ,'11-1-02-11111111'
 );