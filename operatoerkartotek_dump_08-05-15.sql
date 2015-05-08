-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: kartotek
-- ------------------------------------------------------
-- Server version	5.6.24-log

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
-- Table structure for table `operatoer`
--

DROP TABLE IF EXISTS `operatoer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operatoer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `navn` varchar(20) NOT NULL,
  `ini` varchar(3) NOT NULL,
  `cpr` varchar(10) NOT NULL,
  `pass` varchar(8) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `level` tinyint(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operatoer`
--

LOCK TABLES `operatoer` WRITE;
/*!40000 ALTER TABLE `operatoer` DISABLE KEYS */;
INSERT INTO `operatoer` VALUES (31,'Nicklas Hansen','NH','1205910000','passNH31',1,2),(32,'Jens Hansen','JH','1212121212','passJH32',1,1),(33,'Hanne Hansen','HH','1212121212','passHH33',1,1),(34,'Gurli Mogensen','GM','2173985783','passGM34',1,1),(35,'Mogens Jensen','MJ','1212121212','passMJ35',1,1),(36,'Frederik Adamsen','FA','1212121212','passFA36',1,1),(37,'Victor Oernby','VO','1212121212','passVO37',1,1),(38,'Mogen Svendsen','MS','1212121212','passMS38',0,1),(39,'Robert Eriksen','RE','1212121212','passRE39',1,1),(40,'Mogens Inaktiv','MI','1212121212','passMI40',0,1),(41,'Mogens Inaktiv2','MI','1212121212','passMI41',0,1),(42,'Mogens Inaktiv3','MI','1212121212','passMI42',0,2),(43,'Anders And','AA','1111111111','passAA43',1,1),(44,'Andersine And','AA','1010101010','passAA44',1,1);
/*!40000 ALTER TABLE `operatoer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'kartotek'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-08 14:04:32
