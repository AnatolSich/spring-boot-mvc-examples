CREATE DATABASE  IF NOT EXISTS `hplus` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hplus`;

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `image_path` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `product` WRITE;
INSERT INTO `product` VALUES
(1,'mineralwater-blueberry','images/mineralwater-blueberry.jpg'),
(2,'mineralwater-lemonlime','images/mineralwater-lemonlime.jpg'),
(3,'mineralwater-orange','images/mineralwater-orange.jpg'),
(4,'mineralwater-peach','images/mineralwater-peach.jpg'),
(5,'mineralwater-raspberry','images/mineralwater-raspberry.jpg'),
(6,'mineralwater-strawberry','images/mineralwater-strawberry.jpg'),
(7,'proteinbar-chocolate','images/proteinbar-chocolate.jpg'),
(8,'proteinbar-lemon','images/proteinbar-lemon.jpg'),
(9,'proteinbar-peanutbutter','images/proteinbar-peanutbutter.jpg'),
(10,'vitamin-a','images/vitamin-a.jpg'),
(11,'vitamin-bcomplex','images/vitamin-bcomplex.jpg'),
(12,'vitamin-calcium','images/vitamin-c.jpg');
UNLOCK TABLES;

CREATE DATABASE  IF NOT EXISTS `hplus`;
USE `hplus`;


DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `activity` varchar(100) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES
(1,'admin','admin','admin','admin','2000-01-03','gym','MALE'),
(2,'admin1','admin1','admin1','admin1','1985-08-23','sport','FEMALE');
UNLOCK TABLES;


