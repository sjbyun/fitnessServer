CREATE DATABASE  IF NOT EXISTS `fitness_member` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fitness_member`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: fitness_member
-- ------------------------------------------------------
-- Server version	5.6.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `member_info`
--

DROP TABLE IF EXISTS `member_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_info` (
  `name` varchar(15) NOT NULL,
  `age` int(11) NOT NULL,
  `phone_num` varchar(11) NOT NULL,
  `email_addr` varchar(45) NOT NULL,
  `phone_unique_id` varchar(30) DEFAULT NULL,
  `ip_addr` varchar(16) DEFAULT NULL,
  `isPT` int(1) NOT NULL,
  `cont_term` int(11) NOT NULL,
  `lockerroom_num` int(11) NOT NULL,
  `is_infoAllow` char(1) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `isAdmin` int(1) NOT NULL,
  PRIMARY KEY (`phone_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_info`
--

LOCK TABLES `member_info` WRITE;
/*!40000 ALTER TABLE `member_info` DISABLE KEYS */;
INSERT INTO `member_info` VALUES ('주지훈',26,'01025324264','jujihoon01@naver.com','0111886','192.123.555.23',0,3,15,'n','absdf',0),('test3',22,'01029391929','2293@asd.com',NULL,NULL,0,5,5,'0',NULL,0),('변석진',28,'01029656373','admin@gmail.com','0129329','192.331.231.223',0,0,2,'n','tjrwls',1),('test4',44,'10293919293','123@sef.com',NULL,NULL,0,5,5,'0',NULL,0);
/*!40000 ALTER TABLE `member_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-13  3:07:09
