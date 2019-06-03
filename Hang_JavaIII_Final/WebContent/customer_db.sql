

CREATE DATABASE  IF NOT EXISTS `customer_db`;
USE `customer_db`;

DROP TABLE IF EXISTS `customer_table`;

CREATE TABLE `customer_table` (
  `CustID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `Address` varchar(60) DEFAULT NULL,
  `PhoneNumber` varchar(15) DEFAULT NULL,
  `Email` varchar(60) DEFAULT NULL,
  `UserName` varchar(45) DEFAULT NULL,
  `PassWord` varchar(15) DEFAULT NULL,
  `TypeOfCar` varchar(10) DEFAULT NULL,
  `Brand` varchar(10) DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `NumberOfDays` int DEFAULT NULL,
  `Rate` int DEFAULT NULL,
  `TotalPrice` int DEFAULT NULL,
  PRIMARY KEY (`CustID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

LOCK TABLES `customer_table` WRITE;
INSERT INTO `customer` VALUES (1,'Nihan','Ni','123 ABC','555-555-5555','Nihan@google.com');
UNLOCK TABLES;

select * from customer_table;

CREATE USER 'demo'@'localhost' IDENTIFIED BY 'demo';

GRANT ALL PRIVILEGES ON * . * TO 'demo'@'localhost';