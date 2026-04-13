CREATE DATABASE  IF NOT EXISTS `elearning` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `elearning`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: elearning
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `user_quiz_result`
--

DROP TABLE IF EXISTS `user_quiz_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_quiz_result` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `correct_answers` int NOT NULL,
  `is_completed` bit(1) NOT NULL,
  `score` int NOT NULL,
  `total_questions` int NOT NULL,
  `quiz_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `submitted_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdapqmcmqt830bqwx7775otpy6` (`quiz_id`),
  KEY `FKnqf4xt19viwwnfwegw6815cqu` (`user_id`),
  CONSTRAINT `FKdapqmcmqt830bqwx7775otpy6` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`id`),
  CONSTRAINT `FKnqf4xt19viwwnfwegw6815cqu` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_quiz_result`
--

LOCK TABLES `user_quiz_result` WRITE;
/*!40000 ALTER TABLE `user_quiz_result` DISABLE KEYS */;
INSERT INTO `user_quiz_result` VALUES (1,2,_binary '',100,2,4,3,NULL),(23,1,_binary '',100,1,6,3,NULL),(24,1,_binary '',100,1,11,3,NULL),(25,3,_binary '',100,3,15,3,NULL),(26,0,_binary '',0,1,16,3,NULL),(27,0,_binary '',0,3,17,3,NULL),(28,0,_binary '',0,0,5,3,NULL),(29,1,_binary '',50,2,18,3,NULL),(30,7,_binary '',70,10,7,3,NULL),(31,0,_binary '',0,11,7,4,NULL),(32,0,_binary '',2,2,5,3,'2024-12-31 10:48:07.145000'),(33,0,_binary '',0,1,20,3,'2024-12-31 11:21:32.051000'),(34,0,_binary '',1,1,10,3,'2024-12-31 16:21:14.512000'),(35,0,_binary '',0,1,12,3,'2024-12-31 16:22:54.698000'),(36,0,_binary '',1,1,9,3,'2024-12-31 16:37:24.559000'),(38,0,_binary '',1,1,8,3,'2025-01-01 21:13:52.931000'),(39,0,_binary '',1,1,8,3,'2025-01-01 21:14:21.907000'),(40,0,_binary '',0,1,8,3,'2025-01-01 21:15:58.894000'),(41,0,_binary '',1,1,8,3,'2025-01-01 21:22:19.940000'),(42,0,_binary '',0,1,8,3,'2025-01-01 21:22:35.709000'),(43,0,_binary '',1,2,18,3,'2025-01-01 21:23:12.738000'),(44,0,_binary '',0,2,18,3,'2025-01-01 21:25:32.456000'),(45,0,_binary '',0,2,18,3,'2025-01-01 21:30:51.331000'),(46,1,_binary '',100,1,8,3,'2025-01-01 21:31:34.992000'),(47,1,_binary '',20,5,4,3,'2025-01-02 10:20:57.739000'),(48,0,_binary '',0,1,8,3,'2025-01-02 10:21:52.952000'),(49,0,_binary '',0,1,8,3,'2025-01-02 10:22:17.416000'),(50,0,_binary '',0,1,12,3,'2025-01-02 10:32:19.214000'),(51,0,_binary '',0,1,12,3,'2025-01-02 10:32:26.592000'),(52,1,_binary '',100,1,8,3,'2025-01-02 10:34:04.254000'),(53,0,_binary '',0,1,8,3,'2025-01-02 10:34:49.828000'),(54,0,_binary '',0,1,12,3,'2025-01-02 10:41:28.140000'),(55,1,_binary '',50,2,18,3,'2025-01-02 11:00:55.175000'),(56,0,_binary '',0,1,8,3,'2025-01-02 11:02:16.925000'),(57,1,_binary '',100,1,8,3,'2025-01-02 11:02:48.920000'),(58,0,_binary '',0,1,11,3,'2025-01-02 11:17:38.157000'),(59,1,_binary '',100,1,11,3,'2025-01-02 11:20:04.571000'),(60,0,_binary '',0,1,12,3,'2025-01-02 11:26:44.859000'),(61,4,_binary '',80,5,4,3,'2025-01-02 11:27:17.513000'),(62,0,_binary '',0,1,6,3,'2025-01-02 11:28:12.920000'),(63,1,_binary '',33,3,15,3,'2025-01-02 11:28:40.040000'),(64,0,_binary '',0,1,9,3,'2025-01-02 11:31:00.334000'),(65,1,_binary '',33,3,17,4,'2025-01-02 11:50:29.065000'),(66,3,_binary '',100,3,17,4,'2025-01-02 11:50:59.613000'),(67,10,_binary '',90,11,7,4,'2025-01-02 12:14:52.723000'),(68,1,_binary '',100,1,20,3,'2025-01-02 22:04:26.071000'),(69,0,_binary '',0,1,20,3,'2025-01-02 22:04:53.626000'),(70,0,_binary '',0,1,5,3,'2025-01-02 23:30:47.374000'),(71,1,_binary '',100,1,5,3,'2025-01-02 23:30:59.449000');
/*!40000 ALTER TABLE `user_quiz_result` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-02 23:57:38
