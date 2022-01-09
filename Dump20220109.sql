CREATE DATABASE  IF NOT EXISTS `youtube` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `youtube`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: youtube
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authority` (
  `id` int NOT NULL AUTO_INCREMENT,
  `privilege` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES (1,'user:read'),(2,'user:update'),(3,'user:create'),(4,'user:delete');
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `unit_price` decimal(19,2) DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `video_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `K_order_id` (`order_id`),
  KEY `FK_video_id` (`video_id`),
  CONSTRAINT `FK_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK_video_id` FOREIGN KEY (`video_id`) REFERENCES `videos_rows` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_tracking_number` varchar(255) DEFAULT NULL,
  `total_price` decimal(19,2) DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `status` varchar(128) DEFAULT NULL,
  `date_created` datetime(6) DEFAULT NULL,
  `last_updated` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_customer_id` (`customer_id`),
  CONSTRAINT `FK_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER_READ'),(2,'ROLE_USER_EDIT'),(3,'ROLE_USER_CREATE'),(4,'ROLE_USER_DELETE');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_authority`
--

DROP TABLE IF EXISTS `role_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_authority` (
  `role_id` int NOT NULL,
  `authority_id` int NOT NULL,
  PRIMARY KEY (`role_id`,`authority_id`),
  KEY `FK_USER_idx` (`role_id`),
  KEY `FK_role_authority_02` (`authority_id`),
  CONSTRAINT `FK_role_authority_01` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_role_authority_02` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_authority`
--

LOCK TABLES `role_authority` WRITE;
/*!40000 ALTER TABLE `role_authority` DISABLE KEYS */;
INSERT INTO `role_authority` VALUES (1,1),(2,1),(2,2),(3,1),(3,2),(3,3),(4,1),(4,2),(4,3),(4,4);
/*!40000 ALTER TABLE `role_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(150) NOT NULL,
  `email` varchar(250) DEFAULT NULL,
  `profile_image_url` varchar(1250) DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `last_login_date_display` datetime DEFAULT NULL,
  `join_date` datetime DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL,
  `is_not_locked` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'1','lợi','đắc','admin','$2a$10$d9wcXblkVDgDDLhXs.UTD.WNuwoMc2xAxfpf.rml7GI.Xp2uxYguS$2a$10$d9wcXblkVDgDDLhXs.UTD.WNuwoMc2xAxfpf.rml7GI.Xp2uxYguS','admin@gmail.com','http://localhost:8080/api/user/image/profile/admin','2021-12-24 17:46:39','2021-12-24 17:46:27',NULL,1,1),(2,'8672580238','tom','my','tommy','$2a$10$d9wcXblkVDgDDLhXs.UTD.WNuwoMc2xAxfpf.rml7GI.Xp2uxYguS','dacloi.1299@gmail.com','http://localhost:8080/api/user/image/profile/tommy','2022-01-06 22:02:48','2022-01-06 20:52:36','2021-12-14 22:15:32',1,1),(3,'6905012135','loi','dac','client','$2a$10$yFts8r5pXUZ8QlQPbcAGjOj664csWLyvbakTxwV4/IgHShEgLDVRO','tomy@gmail.com','http://localhost:8080/api/user/image/profile/client','2021-12-24 17:48:36',NULL,'2021-12-24 17:47:14',1,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_authority`
--

DROP TABLE IF EXISTS `user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_authority` (
  `user_id` int NOT NULL,
  `authority_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`authority_id`),
  KEY `FK_USER_idx` (`user_id`),
  KEY `FK_user_authority_02` (`authority_id`),
  CONSTRAINT `FK_user_authority_01` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_user_authority_02` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_authority`
--

LOCK TABLES `user_authority` WRITE;
/*!40000 ALTER TABLE `user_authority` DISABLE KEYS */;
INSERT INTO `user_authority` VALUES (1,1),(1,2),(1,3),(1,4),(2,1),(3,1);
/*!40000 ALTER TABLE `user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_USER_idx` (`user_id`),
  KEY `FK_user_role_02` (`role_id`),
  CONSTRAINT `FK_user_role_01` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_user_role_02` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(1,2),(1,3),(1,4),(2,1),(3,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos_cat`
--

DROP TABLE IF EXISTS `videos_cat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videos_cat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `alias` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `image` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `weight` smallint unsigned NOT NULL DEFAULT '0',
  `keywords` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `add_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos_cat`
--

LOCK TABLES `videos_cat` WRITE;
/*!40000 ALTER TABLE `videos_cat` DISABLE KEYS */;
INSERT INTO `videos_cat` VALUES (2,'Tình Cảm','tinh-cam','Phim tình cảm','default.jpg',2,NULL,'2021-12-14'),(3,'Hoạt Hình','hoat-hinh','Phim hoạt hình','default.jpg',3,NULL,'2021-12-14');
/*!40000 ALTER TABLE `videos_cat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos_logs`
--

DROP TABLE IF EXISTS `videos_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videos_logs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sid` mediumint NOT NULL DEFAULT '0',
  `user_id` int DEFAULT NULL,
  `status` tinyint NOT NULL DEFAULT '0',
  `note` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `add_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos_logs`
--

LOCK TABLES `videos_logs` WRITE;
/*!40000 ALTER TABLE `videos_logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `videos_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos_orders`
--

DROP TABLE IF EXISTS `videos_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videos_orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `transactions_id` int NOT NULL,
  `playlist_id` smallint NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `add_time` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_videos_orders_01` (`playlist_id`),
  KEY `FK_videos_orders_02` (`transactions_id`),
  KEY `FK_videos_orders_03` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos_orders`
--

LOCK TABLES `videos_orders` WRITE;
/*!40000 ALTER TABLE `videos_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `videos_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos_playlist`
--

DROP TABLE IF EXISTS `videos_playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videos_playlist` (
  `video_id` int NOT NULL,
  `playlist_id` int NOT NULL,
  PRIMARY KEY (`video_id`,`playlist_id`),
  KEY `FK_VIDEO_idx` (`video_id`),
  KEY `FK_videos_playlist_02` (`playlist_id`),
  CONSTRAINT `FK_videos_playlist_cat` FOREIGN KEY (`playlist_id`) REFERENCES `videos_playlist_cat` (`id`),
  CONSTRAINT `FK_videos_rows` FOREIGN KEY (`video_id`) REFERENCES `videos_rows` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos_playlist`
--

LOCK TABLES `videos_playlist` WRITE;
/*!40000 ALTER TABLE `videos_playlist` DISABLE KEYS */;
INSERT INTO `videos_playlist` VALUES (1,1),(2,1);
/*!40000 ALTER TABLE `videos_playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos_playlist_cat`
--

DROP TABLE IF EXISTS `videos_playlist_cat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videos_playlist_cat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` smallint unsigned NOT NULL DEFAULT '1',
  `private_mode` smallint unsigned NOT NULL DEFAULT '1',
  `numbers` smallint NOT NULL DEFAULT '10',
  `title` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `alias` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `image` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `weight` smallint NOT NULL DEFAULT '0',
  `keywords` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `hitstotal` mediumint unsigned NOT NULL DEFAULT '0',
  `favorite` mediumint unsigned NOT NULL DEFAULT '0',
  `add_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos_playlist_cat`
--

LOCK TABLES `videos_playlist_cat` WRITE;
/*!40000 ALTER TABLE `videos_playlist_cat` DISABLE KEYS */;
INSERT INTO `videos_playlist_cat` VALUES (1,1,1,10,'Trending','trending','','trending',1,'trending',1,1,'2022-01-03');
/*!40000 ALTER TABLE `videos_playlist_cat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos_rows`
--

DROP TABLE IF EXISTS `videos_rows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videos_rows` (
  `id` int NOT NULL AUTO_INCREMENT,
  `catid` int NOT NULL,
  `author` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `artist` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `sourceid` mediumint NOT NULL DEFAULT '0',
  `add_time` date DEFAULT NULL,
  `status` tinyint NOT NULL DEFAULT '1',
  `archive` tinyint unsigned NOT NULL DEFAULT '0',
  `title` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `alias` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `hometext` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `vid_path` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'đường dẫn video',
  `vid_type` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `vid_duration` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'số lượng phút',
  `homeimgfile` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `homeimgalt` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `allowed_comm` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `allowed_rating` tinyint unsigned NOT NULL DEFAULT '0',
  `hitstotal` mediumint unsigned NOT NULL DEFAULT '0',
  `hitscm` mediumint unsigned NOT NULL DEFAULT '0',
  `total_rating` int NOT NULL DEFAULT '0',
  `click_rating` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_videos_rows_alias` (`alias`),
  KEY `FK_videos_rows_01` (`catid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos_rows`
--

LOCK TABLES `videos_rows` WRITE;
/*!40000 ALTER TABLE `videos_rows` DISABLE KEYS */;
INSERT INTO `videos_rows` VALUES (1,2,'Sơn tùng','Sơn tùng',1,'2021-01-04',1,1,'Em của ngày hôm qua','em-cua-ngay-hom-qua','Video Em Của Ngày Hôm Qua','https://www.youtube.com/watch?v=yedmPqDAHKU','','3:00','','','1',1,1,1,1,1),(2,3,'2','2',2,'2021-01-04',22,2,'2','2','2','2','2','2','2','2','22',0,22,2,2,22222);
/*!40000 ALTER TABLE `videos_rows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos_rows_favourite`
--

DROP TABLE IF EXISTS `videos_rows_favourite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videos_rows_favourite` (
  `user_id` int NOT NULL COMMENT 'user id',
  `video_id` int NOT NULL COMMENT 'video id',
  PRIMARY KEY (`user_id`,`video_id`),
  KEY `FK_USER_idx` (`user_id`),
  KEY `FK_videos_rows_favourite_02` (`video_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos_rows_favourite`
--

LOCK TABLES `videos_rows_favourite` WRITE;
/*!40000 ALTER TABLE `videos_rows_favourite` DISABLE KEYS */;
/*!40000 ALTER TABLE `videos_rows_favourite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos_rows_report`
--

DROP TABLE IF EXISTS `videos_rows_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videos_rows_report` (
  `user_id` int NOT NULL,
  `id` int unsigned NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos_rows_report`
--

LOCK TABLES `videos_rows_report` WRITE;
/*!40000 ALTER TABLE `videos_rows_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `videos_rows_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos_sources`
--

DROP TABLE IF EXISTS `videos_sources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videos_sources` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `link` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `logo` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `weight` mediumint unsigned NOT NULL DEFAULT '0',
  `add_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos_sources`
--

LOCK TABLES `videos_sources` WRITE;
/*!40000 ALTER TABLE `videos_sources` DISABLE KEYS */;
/*!40000 ALTER TABLE `videos_sources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos_tags`
--

DROP TABLE IF EXISTS `videos_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videos_tags` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numnews` mediumint NOT NULL DEFAULT '0',
  `alias` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `image` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `keywords` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos_tags`
--

LOCK TABLES `videos_tags` WRITE;
/*!40000 ALTER TABLE `videos_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `videos_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos_tags_id`
--

DROP TABLE IF EXISTS `videos_tags_id`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videos_tags_id` (
  `id` int NOT NULL,
  `tid` mediumint NOT NULL,
  `keyword` varchar(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos_tags_id`
--

LOCK TABLES `videos_tags_id` WRITE;
/*!40000 ALTER TABLE `videos_tags_id` DISABLE KEYS */;
/*!40000 ALTER TABLE `videos_tags_id` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos_transactions`
--

DROP TABLE IF EXISTS `videos_transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videos_transactions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `playlist_id` int NOT NULL,
  `phone` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK_videos_transactions_01` (`playlist_id`),
  KEY `FK_videos_transactions_03` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos_transactions`
--

LOCK TABLES `videos_transactions` WRITE;
/*!40000 ALTER TABLE `videos_transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `videos_transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'youtube'
--

--
-- Dumping routines for database 'youtube'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-09 11:45:39
