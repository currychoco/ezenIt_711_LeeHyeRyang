--CRATE TABLE
CREATE TABLE `notice` (
   `no` int(11) NOT NULL,
   `title` varchar(40) NOT NULL,
   `content` varchar(4000) NOT NULL,
   `writer` int(11) NOT NULL,
   `viewCnt` int(11) NOT NULL DEFAULT '0',
   `regDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (`no`),
   KEY `managerfk` (`writer`),
   CONSTRAINT `managerfk` FOREIGN KEY (`writer`) REFERENCES `manager` (`no`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
 --INSERT
 insert into notice(
	`no`
    ,title
    ,content
    ,writer
)values(
	39
    ,'testNoticeTitle'
    ,'testNoticeContent'
    ,1
);