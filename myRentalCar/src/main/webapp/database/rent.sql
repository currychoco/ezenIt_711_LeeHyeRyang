--CREATE TABLE
CREATE TABLE `rent` (
   `no` int(11) NOT NULL,
   `userno` int(11) NOT NULL,
   `carno` int(11) NOT NULL,
   `resDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   `sDate` timestamp NOT NULL,
   `eDate` timestamp NOT NULL,
   `price` int(11) NOT NULL,
   `extra` int(11) DEFAULT '0',
   `return` tinyint(1) DEFAULT '0',
   PRIMARY KEY (`no`),
   KEY `userfk` (`userno`),
   KEY `carfk2` (`carno`),
   CONSTRAINT `carfk2` FOREIGN KEY (`carno`) REFERENCES `car` (`no`),
   CONSTRAINT `userfk` FOREIGN KEY (`userno`) REFERENCES `user` (`no`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
 --INSERT
 insert into rent(
	`no`
    ,userno
    ,carno
    ,sDate
    ,eDate
    ,price
)values(
	50
    ,1
    ,10020
    ,now()
    ,now()
    ,60000
);