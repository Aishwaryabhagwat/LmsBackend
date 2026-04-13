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
-- Table structure for table `quiz_option`
--

DROP TABLE IF EXISTS `quiz_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz_option` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `text` varchar(255) DEFAULT NULL,
  `question_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3tn69ehmohhd77gufh49cq4sj` (`question_id`),
  CONSTRAINT `FK3tn69ehmohhd77gufh49cq4sj` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=393 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_option`
--

LOCK TABLES `quiz_option` WRITE;
/*!40000 ALTER TABLE `quiz_option` DISABLE KEYS */;
INSERT INTO `quiz_option` VALUES (1,'Berlin',2),(2,'Madrid',2),(3,'Paris',2),(4,'Rome',2),(5,'3',3),(6,'4',3),(7,'5',3),(8,'6',3),(13,'fruit',5),(14,'veg',5),(15,'pulses',5),(16,'millet',5),(17,'haa',6),(18,'nahi',6),(19,'pata nahi',6),(20,'jaa puch ke aa',6),(21,'Berlin',7),(22,'Madrid',7),(23,'Paris',7),(24,'Rome',7),(25,'Berlin',8),(26,'Madrid',8),(27,'Delhi',8),(28,'Rome',8),(29,'Berlin',9),(30,'Madrid',9),(31,'Delhi',9),(32,'Rome',9),(33,'Islamabad',10),(34,'Lahore',10),(35,'Delhi',10),(36,'Rome',10),(37,'',11),(38,'',11),(39,'',11),(40,'',11),(41,'demo',12),(42,'demo',12),(43,'demo',12),(44,'demo',12),(45,'sample quiz',13),(46,'sample quiz',13),(47,'sample quiz',13),(48,'sample quiz',13),(49,'Jupiter',15),(50,'Saturn',15),(51,'Earth',15),(52,'Mars',15),(53,'High Processing Computing',16),(54,'High Performance Computing',16),(55,'Highly Performant Computers',16),(56,'Huge Parallel Computing',16),(57,' Ethernet',17),(58,'InfiniBand',17),(59,' Wi-Fi',17),(60,'USB',17),(61,'',18),(62,'',18),(63,'',18),(64,'',18),(65,'Open Multi-threading Protocol',19),(66,'Open Multiprocessing ',19),(67,'Open Multithreaded Parallelism',19),(68,' Open Memory Processing',19),(69,'Creates a single thread to run code in parallel',20),(70,'Creates a team of threads to execute the code in parallel',20),(71,'Executes the code sequentially',20),(72,'Disables parallel execution',20),(73,'#pragma omp critical',21),(74,'#pragma omp single',21),(75,'#pragma omp atomic',21),(76,'#pragma omp parallel',21),(77,'High Power Computing',22),(78,'High Performance Computing',22),(79,'High Parallel Computing',22),(80,'Hybrid Processing Core',22),(81,'CPUs only',23),(82,'GPUs only',23),(83,'A combination of CPUs and GPUs',23),(84,'Cloud servers only',23),(85,'Ethernet',24),(86,'InfiniBand',24),(87,'USB',24),(88,'Wi-Fi',24),(89,'FAT32',25),(90,'NTFS',25),(91,'Lustre',25),(92,'HDFS',25),(93,'To reduce power consumption',26),(94,'To increase computational speed',26),(95,'To simplify coding',26),(96,'To reduce memory usage',26),(97,'Serialization',27),(98,'Parallelization',27),(99,'Scheduling',27),(100,'Virtualization',27),(101,'Kubernetes',28),(102,'SLURM',28),(103,'Apache Hadoop',28),(104,'Docker',28),(105,'Python',29),(106,'C++ with MPI or OpenMP',29),(107,'HTML',29),(108,'JavaScript',29),(109,'High clock speed of a single processor',30),(110,'Large memory capacity',30),(111,'Massive parallelism',30),(112,'Low energy consumption',30),(113,'Clock speed (GHz)',31),(114,'Floating Point Operations Per Second (FLOPS)',31),(115,'Number of cores',31),(116,'Amount of RAM',31),(117,'',32),(118,'',32),(119,'',32),(120,'',32),(121,'NumPy',33),(122,'Scikit-learn  ',33),(123,'TensorFlow',33),(124,'Matplotlib',33),(125,'Increasing model complexity',34),(126,'Using dropout in neural networks',34),(127,'Reducing training data size',34),(128,'Using more features',34),(129,'Linear Regression',35),(130,'K-Nearest Neighbors (KNN)',35),(131,'Principal Component Analysis (PCA)',35),(132,'K-Means Clustering',35),(133,'option1',36),(134,'option2',36),(135,'option3',36),(136,'option4',36),(137,'12',37),(138,'23',37),(139,'36',37),(140,'45',37),(141,'op1',38),(142,'op2',38),(143,'op3',38),(144,'op4',38),(145,'op1',39),(146,'op2',39),(147,'op3',39),(148,'op4',39),(149,'a',40),(150,'b',40),(151,'c',40),(152,'d',40),(153,'a',41),(154,'b',41),(155,'c',41),(156,'d',41),(157,'Islamabad',42),(158,'Lahore',42),(159,'Delhi',42),(160,'Rome',42),(161,'a',43),(162,'b',43),(163,'c',43),(164,'d',43),(165,'',44),(166,'',44),(167,'',44),(168,'',44),(377,'delhi',97),(378,'kolkata',97),(379,'mumbai',97),(380,'banglore',97);
/*!40000 ALTER TABLE `quiz_option` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-02 23:57:39
