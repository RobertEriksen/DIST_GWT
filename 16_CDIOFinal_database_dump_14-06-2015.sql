-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 62.79.16.16    Database: grp16
-- ------------------------------------------------------
-- Server version	5.6.22

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
-- Table structure for table `brugere`
--

DROP TABLE IF EXISTS `brugere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brugere` (
  `opr_id` int(11) NOT NULL AUTO_INCREMENT,
  `opr_navn` text NOT NULL,
  `ini` text NOT NULL,
  `cpr` varchar(10) NOT NULL,
  `password` text NOT NULL,
  `active` tinyint(1) NOT NULL,
  `level` tinyint(1) NOT NULL,
  PRIMARY KEY (`opr_id`),
  UNIQUE KEY `cpr_UNIQUE` (`cpr`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brugere`
--

LOCK TABLES `brugere` WRITE;
/*!40000 ALTER TABLE `brugere` DISABLE KEYS */;
INSERT INTO `brugere` VALUES (1,'Tom Erichsen','TE','123456789','HelloKitty',1,4),(2,'Jens Hansen','JH','0987654320','passJH02',1,3),(3,'Gurli Jensen','GJ','0987654333','passGJ03',1,2),(4,'Joseph Stoyr','JS','0000000000','PassJS04',1,1);
/*!40000 ALTER TABLE `brugere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produktbatch`
--

DROP TABLE IF EXISTS `produktbatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produktbatch` (
  `pb_id` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `recept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pb_id`),
  KEY `recept_id` (`recept_id`),
  CONSTRAINT `produktbatch_ibfk_1` FOREIGN KEY (`recept_id`) REFERENCES `recept` (`recept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produktbatch`
--

LOCK TABLES `produktbatch` WRITE;
/*!40000 ALTER TABLE `produktbatch` DISABLE KEYS */;
INSERT INTO `produktbatch` VALUES (12,0,10);
/*!40000 ALTER TABLE `produktbatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produktbatchkomponent`
--

DROP TABLE IF EXISTS `produktbatchkomponent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produktbatchkomponent` (
  `pb_id` int(11) NOT NULL DEFAULT '0',
  `rb_id` int(11) NOT NULL DEFAULT '0',
  `tara` double DEFAULT NULL,
  `netto` double DEFAULT NULL,
  `opr_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pb_id`,`rb_id`),
  KEY `rb_id` (`rb_id`),
  KEY `opr_id` (`opr_id`),
  CONSTRAINT `pbk_3` FOREIGN KEY (`opr_id`) REFERENCES `brugere` (`opr_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `produktbatchkomponent_ibfk_1` FOREIGN KEY (`pb_id`) REFERENCES `produktbatch` (`pb_id`),
  CONSTRAINT `produktbatchkomponent_ibfk_2` FOREIGN KEY (`rb_id`) REFERENCES `raavarebatch` (`rb_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produktbatchkomponent`
--

LOCK TABLES `produktbatchkomponent` WRITE;
/*!40000 ALTER TABLE `produktbatchkomponent` DISABLE KEYS */;
/*!40000 ALTER TABLE `produktbatchkomponent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raavare`
--

DROP TABLE IF EXISTS `raavare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `raavare` (
  `raavare_id` int(11) NOT NULL,
  `raavare_navn` text,
  `leverandoer` text,
  PRIMARY KEY (`raavare_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raavare`
--

LOCK TABLES `raavare` WRITE;
/*!40000 ALTER TABLE `raavare` DISABLE KEYS */;
INSERT INTO `raavare` VALUES (2,'Vand','Ostejohn'),(3,'Salt','Ostejohn'),(4,'Citron','JKDS');
/*!40000 ALTER TABLE `raavare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raavarebatch`
--

DROP TABLE IF EXISTS `raavarebatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `raavarebatch` (
  `raavare_id` int(11) DEFAULT NULL,
  `rb_id` int(11) NOT NULL,
  `maengde` double DEFAULT NULL,
  PRIMARY KEY (`rb_id`),
  KEY `raavare_id` (`raavare_id`),
  CONSTRAINT `raavarebatch_ibfk_1` FOREIGN KEY (`raavare_id`) REFERENCES `raavare` (`raavare_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raavarebatch`
--

LOCK TABLES `raavarebatch` WRITE;
/*!40000 ALTER TABLE `raavarebatch` DISABLE KEYS */;
INSERT INTO `raavarebatch` VALUES (4,2,3),(2,100,10),(3,123,5);
/*!40000 ALTER TABLE `raavarebatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recept`
--

DROP TABLE IF EXISTS `recept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recept` (
  `recept_id` int(11) NOT NULL,
  `recept_navn` text,
  PRIMARY KEY (`recept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recept`
--

LOCK TABLES `recept` WRITE;
/*!40000 ALTER TABLE `recept` DISABLE KEYS */;
INSERT INTO `recept` VALUES (10,'Saltvand m. Citron');
/*!40000 ALTER TABLE `recept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receptkomponent`
--

DROP TABLE IF EXISTS `receptkomponent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receptkomponent` (
  `recept_id` int(11) NOT NULL DEFAULT '0',
  `raavare_id` int(11) NOT NULL DEFAULT '0',
  `nom_netto` double DEFAULT NULL,
  `tolerance` double DEFAULT NULL,
  PRIMARY KEY (`recept_id`,`raavare_id`),
  KEY `raavare_id` (`raavare_id`),
  CONSTRAINT `receptkomponent_ibfk_1` FOREIGN KEY (`recept_id`) REFERENCES `recept` (`recept_id`),
  CONSTRAINT `receptkomponent_ibfk_2` FOREIGN KEY (`raavare_id`) REFERENCES `raavare` (`raavare_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receptkomponent`
--

LOCK TABLES `receptkomponent` WRITE;
/*!40000 ALTER TABLE `receptkomponent` DISABLE KEYS */;
INSERT INTO `receptkomponent` VALUES (10,2,0.473,0.3),(10,3,0.1,2.4),(10,4,0.33,1.8);
/*!40000 ALTER TABLE `receptkomponent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'grp16'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-14 16:40:20
