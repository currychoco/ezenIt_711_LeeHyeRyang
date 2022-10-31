--CREATE TABLE--
CREATE TABLE `board` (
   `no` int(11) NOT NULL,
   `title` varchar(20) NOT NULL,
   `content` varchar(4000) NOT NULL,
   `user` varchar(20) NOT NULL,
   `regDate` timestamp NOT NULL,
   `modDate` timestamp NOT NULL,
   `viewCnt` int(11) NOT NULL,
   PRIMARY KEY (`no`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
 --INSERT--
  insert into board(
	`no`
    ,title
    ,content
    ,`user`
    ,regDate
    ,modDate
    ,viewCnt
 )values(
	5
    ,'test1'
    ,'test1'
    ,'apple'
    ,now()
    ,now()
    ,10
 );