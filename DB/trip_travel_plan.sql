CREATE DATABASE  IF NOT EXISTS `trip` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `trip`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: trip
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `travel_plan`
--

DROP TABLE IF EXISTS `travel_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `travel_plan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` varchar(20) NOT NULL,
  `title` text NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `hit` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travel_plan`
--

LOCK TABLES `travel_plan` WRITE;
/*!40000 ALTER TABLE `travel_plan` DISABLE KEYS */;
INSERT INTO `travel_plan` VALUES (52,'ssafy','작성 테스트','2023-05-23 21:37:34',11),(54,'ssafy','가나다라마바사','2023-05-23 21:38:20',1),(55,'ssafy','ㅁㄴㅇㄻㄴㅇㅁㄴㅇ','2023-05-23 21:38:28',2),(56,'ssafy','12312312','2023-05-23 21:53:11',7),(57,'ssafy','ㅁㄴㅇㅁㄴㅇㄴㅁ','2023-05-23 21:38:41',1),(58,'ssafy','ㅁㄴㅇㅁㄴㅇㄴㅁ','2023-05-23 21:38:47',1),(60,'ssafy','ㅁㅇㄴㅁㅇㄴㅁㅇㄴㅁㅇ','2023-05-23 21:39:13',1),(61,'ssafy','ㅁㅋㅌㅊㅋㅌ','2023-05-23 21:39:20',1),(62,'ssafy','ㅁㄴㅇㅁㄴㅇㄴㅁㅇ','2023-05-23 21:39:27',1),(63,'ssafy','ㅁㄴㅇㅁㄴㅇㄴㅁㅇ','2023-05-23 21:52:04',2),(65,'admin','살려줘','2023-05-24 01:04:35',11),(78,'ssafy','대전','2023-05-24 07:57:27',53),(81,'admin','admin 테스트','2023-05-25 04:26:54',8),(82,'admin','북마크 테스트','2023-05-25 04:39:18',8),(84,'ssafy','여행 계획 테스트','2023-05-25 11:45:55',21);
/*!40000 ALTER TABLE `travel_plan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-30 18:52:11
