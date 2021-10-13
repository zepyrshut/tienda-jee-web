-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: tienda_db
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
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulo` (
  `id_articulo` int NOT NULL AUTO_INCREMENT,
  `nombre_articulo` varchar(45) NOT NULL,
  `precio_articulo` decimal(4,2) NOT NULL,
  `descripcion_articulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_articulo`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
INSERT INTO `articulo` VALUES (0,'Sombrilla',10.99,'Una sombrilla de playa.'),(1,'Butaca',15.99,'Una butaca de playa.'),(2,'Bañador',12.99,'Un bañador para la playa.'),(3,'Bikini',15.99,'Un bikini para la playa.'),(4,'Crema solar',8.99,'Una crema solar para la playa.');
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cesta_compra`
--

DROP TABLE IF EXISTS `cesta_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cesta_compra` (
  `id_cesta_compra` int NOT NULL AUTO_INCREMENT,
  `cliente_id_cliente` int NOT NULL,
  PRIMARY KEY (`id_cesta_compra`,`cliente_id_cliente`),
  UNIQUE KEY `id_cesta_compra_UNIQUE` (`id_cesta_compra`),
  UNIQUE KEY `cliente_id_cliente_UNIQUE` (`cliente_id_cliente`),
  KEY `fk_cesta_compra_cliente1_idx` (`cliente_id_cliente`),
  CONSTRAINT `fk_cesta_compra_cliente1` FOREIGN KEY (`cliente_id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cesta_compra`
--

LOCK TABLES `cesta_compra` WRITE;
/*!40000 ALTER TABLE `cesta_compra` DISABLE KEYS */;
INSERT INTO `cesta_compra` VALUES (74,74),(75,75);
/*!40000 ALTER TABLE `cesta_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `nombre_cliente` varchar(45) NOT NULL,
  `apellido_cliente` varchar(45) NOT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (74,'pedro','perez'),(75,'carmen','serrano');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datos_sesion`
--

DROP TABLE IF EXISTS `datos_sesion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datos_sesion` (
  `id_datos_sesion` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `contrasena` varchar(45) NOT NULL,
  `cliente_id_cliente` int NOT NULL,
  PRIMARY KEY (`id_datos_sesion`,`cliente_id_cliente`),
  UNIQUE KEY `usuario_UNIQUE` (`usuario`),
  UNIQUE KEY `cliente_id_cliente_UNIQUE` (`cliente_id_cliente`),
  UNIQUE KEY `id_datos_sesion_UNIQUE` (`id_datos_sesion`),
  KEY `fk_datos_sesion_cliente1_idx` (`cliente_id_cliente`),
  CONSTRAINT `fk_datos_sesion_cliente1` FOREIGN KEY (`cliente_id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_sesion`
--

LOCK TABLES `datos_sesion` WRITE;
/*!40000 ALTER TABLE `datos_sesion` DISABLE KEYS */;
INSERT INTO `datos_sesion` VALUES (74,'pedrillo','0000',74),(75,'carmelilla','0000',75);
/*!40000 ALTER TABLE `datos_sesion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_cesta`
--

DROP TABLE IF EXISTS `detalle_cesta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_cesta` (
  `articulo_id_articulo` int NOT NULL,
  `cesta_compra_id_cesta_compra` int NOT NULL,
  `cantidad_cesta` int NOT NULL,
  PRIMARY KEY (`articulo_id_articulo`,`cesta_compra_id_cesta_compra`),
  KEY `fk_detallecesta_cestacompra1_idx` (`cesta_compra_id_cesta_compra`),
  CONSTRAINT `fk_detallecesta_articulo1` FOREIGN KEY (`articulo_id_articulo`) REFERENCES `articulo` (`id_articulo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_detallecesta_cestacompra1` FOREIGN KEY (`cesta_compra_id_cesta_compra`) REFERENCES `cesta_compra` (`id_cesta_compra`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_cesta`
--

LOCK TABLES `detalle_cesta` WRITE;
/*!40000 ALTER TABLE `detalle_cesta` DISABLE KEYS */;
INSERT INTO `detalle_cesta` VALUES (1,75,2),(2,75,1),(3,74,2),(3,75,1),(4,74,3),(4,75,1);
/*!40000 ALTER TABLE `detalle_cesta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-03 10:17:28
