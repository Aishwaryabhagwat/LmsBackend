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
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `correct_option_index` int NOT NULL,
  `is_active` int NOT NULL,
  `quiz_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb0yh0c1qaxfwlcnwo9dms2txf` (`quiz_id`),
  CONSTRAINT `FKb0yh0c1qaxfwlcnwo9dms2txf` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (2,'What is the capital of France?',3,1,4),(3,'What is 2 + 2?',1,1,4),(5,'what is Apple?',0,1,6),(6,'Kya aapke toothpaste me namak hai?',3,0,7),(7,'What is the capital of France?',2,1,8),(8,'What is the capital of India?',2,1,9),(9,'What is the capital of India?',2,1,10),(10,'What is the capital of Pakistan?',0,1,11),(11,'',0,1,12),(12,'demo',3,1,13),(13,'sample quiz',2,1,14),(14,'What is the hardest natural substance on Earth?',0,0,4),(15,'What is the largest planet in our solar system?',0,1,4),(16,'What is the full form of HPC?',1,1,4),(17,'What is the most common interconnect technology used in HPC systems for communication between nodes?',1,1,4),(18,'',0,0,4),(19,'What does OpenMP stand for?',1,1,15),(20,'What does the #pragma omp parallel directive do??',1,1,15),(21,'Which OpenMP directive is used to ensure only one thread executes a block of code?',0,1,15),(22,'What does HPC stand for?',1,1,7),(23,'Which of the following is commonly used in HPC systems?',2,1,7),(24,'Which interconnect technology is widely used in HPC for high-speed communication?',1,1,7),(25,'Which file system is designed for HPC environments?',2,1,7),(26,'What is the primary goal of parallel computing in HPC?',1,1,7),(27,'What is the term for dividing a large computational problem into smaller tasks to run concurrently?',1,1,7),(28,'Which of the following is a popular HPC workload manager?',1,1,7),(29,'Which programming language is commonly used in HPC for parallel computing?',1,1,7),(30,'What is a key feature of a supercomputer?',2,1,7),(31,'What is the primary metric to measure the performance of an HPC system?',1,1,7),(32,'',0,1,16),(33,'Which library is commonly used for deep learning in Python?',2,1,17),(34,'Which of the following methods can be used to prevent overfitting?',1,1,17),(35,'Which of the following algorithms is used for classification tasks?',1,1,17),(36,'ques1 test',2,1,18),(37,'sample question',1,1,18),(38,'ques1',2,1,19),(39,'ques2?',0,1,19),(40,'question demo??',2,0,7),(41,'question??',1,1,7),(42,'What is the capital of Pakistan?',0,1,20),(43,'q1',1,1,21),(44,'',0,0,21),(97,'What is capital of india??',0,1,5);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-02 23:57:42
