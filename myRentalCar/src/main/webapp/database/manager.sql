--CREATE TABLE
CREATE TABLE `manager` (
   `no` int(11) NOT NULL,
   `name` varchar(20) NOT NULL,
   PRIMARY KEY (`no`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
 --INSERT
 insert into manager values(
	1
    ,'매니저'
);