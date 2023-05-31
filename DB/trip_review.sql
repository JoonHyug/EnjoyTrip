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
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_uid` int NOT NULL,
  `content` varchar(300) DEFAULT NULL,
  `point` int NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `content_id` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `review_chk_1` CHECK (((1 <= `point`) and (`point` <= 10)))
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,1,'1',1,'2023-05-02 04:43:35',1),(4,2,'132134124',5,'2023-05-12 06:29:15',741658),(5,2,'아님니ㅏㅓㅣ마너ㅏㅣㅓㅁㄴㅁㄴ마ㅣㅇ',5,'2023-05-12 06:34:52',741658),(6,2,'asdasdasdsadasdasdas',2,'2023-05-12 06:50:35',741658),(7,2,'가낟ㅁ나암ㄴ암ㄴ암낭ㅁ낭ㅁ낭',1,'2023-05-12 07:13:02',741658),(8,2,'ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ',5,'2023-05-12 07:15:06',741658),(9,2,'ㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ',5,'2023-05-12 07:16:19',741658),(10,2,'ㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅋㅌㅊㅋㅊㅋㅌ1231',5,'2023-05-12 07:16:50',741658),(11,2,'11111111111111111111',5,'2023-05-12 07:18:00',741658),(12,2,'22222222222222222222',5,'2023-05-12 07:20:47',741658),(13,2,'123123123',5,'2023-05-12 07:29:10',741658),(14,2,'312312312312312',5,'2023-05-12 07:29:41',741658),(15,2,'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ',5,'2023-05-12 07:29:49',741658),(16,2,'ㅁㄴㅇㅁㄴㅇㅁㄴㅇ1231231213123',5,'2023-05-12 07:32:03',741658),(17,2,'ㅋㅋㅋㅋㅋㅋㅋㅋ',5,'2023-05-12 07:32:09',741658),(18,2,'ㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗ',5,'2023-05-12 07:32:16',741658),(19,2,'테스트',5,'2023-05-12 07:57:45',741658),(20,2,'테스트',5,'2023-05-12 08:00:54',129438),(21,2,'1.5테스트',2,'2023-05-12 08:13:51',741658),(22,2,'리뷰 수정이 가능합니다',4,'2023-05-25 13:30:09',741658),(23,2,'@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@',10,'2023-05-13 10:03:57',741658),(24,2,'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa',10,'2023-05-13 15:31:28',741658),(25,2,'asdasdasdasdsadaaaaaaaaaaaaaaaaaaaaㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁaaaaaaaaaaaaaaaaaaaaaㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ',5,'2023-05-13 15:31:44',741658),(26,2,'그림자는 고행을 꾸며 얼마나 실로 끓는다. 방황하여도, 가슴에 그들의 기쁘며, 아름다우냐? 가슴이 못할 힘차게 튼튼하며, 고행을 것이다. 사랑의 이상의 긴지라 아니더면, 듣는다. 착목한는 가장 싶이 풍부하게 석가는 그리하였는가? 크고 같이, 되는 고동을 쓸쓸하랴? 방황하였으며, 얼음이 청춘의 열매를 얼음 돋고, 주며, 보배를 아니다. 끝까지 과실이 뜨고, 광야에서 설레는 몸이 충분히 구하지 그리하였는가? 천하를 얼마나 이것이야말로 아름다우냐? 청춘을 자신과 인간에 들어 심장의 고행을 피다.',5,'2023-05-13 15:32:21',741658),(27,1,'MySQL 테스트',9,'2023-05-13 17:25:27',741658),(28,2,'Map객체 테스트',10,'2023-05-13 17:37:46',741658),(29,2,'입력 테스트',10,'2023-05-13 17:53:46',741658),(30,2,'테스트',10,'2023-05-16 08:42:41',741658),(31,2,'테스트',10,'2023-05-16 08:44:00',741658),(32,2,'등록 테스트',10,'2023-05-19 16:37:48',741658),(33,2,'ㅁㄴㅇㅁㄴㅇㄴㅁㅇㄴㅁ',10,'2023-05-21 14:32:35',250279),(34,2,'ㅏㅏㅏㅏㅏㅏ',10,'2023-05-24 00:40:20',1066804),(35,1,'리뷰 잘 되나?',10,'2023-05-25 03:45:46',741658),(36,1,'',2,'2023-05-25 03:45:51',741658),(37,2,'리뷰가 잘 등록됩니다',5,'2023-05-25 13:01:29',741658),(38,1,'대전 시청광장 최고',10,'2023-05-25 13:08:38',2743325),(39,1,'엑스포 다리 이뻐요',10,'2023-05-25 13:09:34',2738026),(40,1,'남선 공원 좋아요',8,'2023-05-25 13:11:17',2758252),(41,1,'리뷰를 등록해보자',10,'2023-05-25 13:33:48',741957),(42,1,'리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다리뷰가 길어지면 접힌다',10,'2023-05-25 13:34:06',741957),(43,1,'후기 등록',3,'2023-05-25 13:36:03',741658);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
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
