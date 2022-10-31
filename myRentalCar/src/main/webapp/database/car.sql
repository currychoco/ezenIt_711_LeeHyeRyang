--CREATE TABLE

CREATE TABLE `car` (
   `no` int(11) NOT NULL,
   `car_no` varchar(20) NOT NULL,
   `type` int(11) NOT NULL,
   `price` int(11) NOT NULL,
   `img` varchar(1000) NOT NULL,
   `DOM` int(11) NOT NULL,
   `fuel` varchar(20) NOT NULL,
   PRIMARY KEY (`no`),
   UNIQUE KEY `car_no` (`car_no`),
   KEY `carfk` (`type`),
   CONSTRAINT `carfk` FOREIGN KEY (`type`) REFERENCES `car_type` (`no`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
 
 --INSERT
  insert into car values(
	'10020'
    ,'33다3333'
    ,51
    ,60000
    ,'https://search.pstatic.net/common?quality=75&direct=true&ttype=input&src=https%3A%2F%2Fdbscthumb-phinf.pstatic.net%2F5662_000_8%2F20221025163119572_2BOIJZA1N.png%2F20221025162201_A.png%3Ftype%3Dm1500'
	,2023
    ,'휘발유'
 );