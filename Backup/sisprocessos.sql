-- MySQL dump 10.13  Distrib 5.7.16, for Win64 (x86_64)
--
-- Host: localhost    Database: sisprocessos
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `contribuinte`
--

DROP TABLE IF EXISTS `contribuinte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contribuinte` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(40) NOT NULL,
  `cel` varchar(15) DEFAULT NULL,
  `cidade` varchar(40) NOT NULL,
  `cpf` varchar(20) NOT NULL,
  `dtCadastro` date DEFAULT NULL,
  `dtNasc` date NOT NULL,
  `nome` varchar(80) NOT NULL,
  `sexo` char(1) NOT NULL,
  `tel` varchar(15) DEFAULT NULL,
  `uf` varchar(2) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contribuinte`
--

LOCK TABLES `contribuinte` WRITE;
/*!40000 ALTER TABLE `contribuinte` DISABLE KEYS */;
INSERT INTO `contribuinte` VALUES (1,'moxoto','(99)99999-9999','Paulo Afonso','999.999.999-99',NULL,'1990-02-03','LOURINALDO SILVA CORDEIRO DE PONTES','M','(99)9999-9999','BA'),(3,'A','(99)99999-9999','PA','333.333.333-33','2016-12-07','1990-02-03','CASPER','M','(99)9999-9999','BA'),(4,'b','(11)11111-1111','pa','222.222.222-22','2016-12-07','1990-02-03','cas','F','(11)1111-1111','BA'),(5,'moxoto','(99)99999-9999','pa','111.111.111-11','2016-12-07','1990-02-03','casper','M','(99)9999-9999','BA'),(6,'8','(88)88888-8888','88','444.444.444-44','2016-12-07','1111-11-11','MAIRLA','F','(88)8888-8888','BA');
/*!40000 ALTER TABLE `contribuinte` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER tr_dtCadastro BEFORE INSERT
    ON contribuinte 
    FOR EACH ROW
    SET NEW.dtCadastro = NOW() */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER tr_cpfDuplicado BEFORE INSERT
    
ON contribuinte 
    
FOR EACH ROW
    
BEGIN 
		
DECLARE msg varchar(255);
        
		
IF (select TRUE from contribuinte where cpf = new.cpf) THEN 

SET msg = 'ERRO: CPF JA CADASTRADO';
       
signal sqlstate'45000' set message_text=msg;
        
END IF;
        
	
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER tr_cpfDuplicadoUpdate
BEFORE UPDATE
ON contribuinte 
FOR EACH ROW

BEGIN 
	DECLARE msg varchar(255);
	
IF (select TRUE from contribuinte where cpf = new.cpf and codigo != new.codigo) THEN
SET msg = 'ERRO: CPF JA CADASTRADO';	   
		
signal sqlstate'45000' set message_text=msg;
	
END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `encaminhamento`
--

DROP TABLE IF EXISTS `encaminhamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `encaminhamento` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `dtEncaminhamento` date DEFAULT NULL,
  `observacao` varchar(255) DEFAULT NULL,
  `processo_codigo` bigint(20) NOT NULL,
  `responsavel_codigo` bigint(20) NOT NULL,
  `setor_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_448l2oj87oljplwsnjeauyplf` (`processo_codigo`),
  KEY `FK_t0s9nfn8lpdlbgicusq9k6jjh` (`responsavel_codigo`),
  KEY `FK_arn9jnhj586mectxovcmwputt` (`setor_codigo`),
  CONSTRAINT `FK_448l2oj87oljplwsnjeauyplf` FOREIGN KEY (`processo_codigo`) REFERENCES `processo` (`codigo`),
  CONSTRAINT `FK_arn9jnhj586mectxovcmwputt` FOREIGN KEY (`setor_codigo`) REFERENCES `setor` (`codigo`),
  CONSTRAINT `FK_t0s9nfn8lpdlbgicusq9k6jjh` FOREIGN KEY (`responsavel_codigo`) REFERENCES `funcionario` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `encaminhamento`
--

LOCK TABLES `encaminhamento` WRITE;
/*!40000 ALTER TABLE `encaminhamento` DISABLE KEYS */;
INSERT INTO `encaminhamento` VALUES (1,'2016-01-06','aa',1,1,2),(2,'2016-02-06','bbbb',1,4,1),(3,'2016-03-06','ccc',1,2,2);
/*!40000 ALTER TABLE `encaminhamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `cel` varchar(15) DEFAULT NULL,
  `cpf` varchar(20) NOT NULL,
  `dtAdmissao` date DEFAULT NULL,
  `dtNasc` date NOT NULL,
  `nome` varchar(80) NOT NULL,
  `sexo` char(1) NOT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `setor_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_m2sfi88crxe5purxxsbxmju6y` (`setor_codigo`),
  CONSTRAINT `FK_m2sfi88crxe5purxxsbxmju6y` FOREIGN KEY (`setor_codigo`) REFERENCES `setor` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (1,'(11)11111-1111','111.111.111-11','2011-01-01','2001-01-01','joao','M','(11)1111-1111',2),(2,'(22)22222-2222','222.222.222-22','2012-02-02','2002-02-02','maria','F','(22)2222-2222',2),(3,'(33)33333-3333','333.333.333-33','2013-03-03','2003-03-03','joaquin','M','(33)3333-3333',1),(4,'(44)44444-4444','444.444.444-44','2014-04-04','2004-04-04','zefinha','F','(44)4444-4444',1);
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `processo`
--

DROP TABLE IF EXISTS `processo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processo` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `dtFinal` date DEFAULT NULL,
  `dtProcesso` date DEFAULT NULL,
  `numero` varchar(30) NOT NULL,
  `situacao` varchar(30) NOT NULL,
  `contribuinte_codigo` bigint(20) NOT NULL,
  `tipo_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_narr3aeveikinvf5pwelucycs` (`contribuinte_codigo`),
  KEY `FK_bq5klqlttk0jqesbck9nx2fqj` (`tipo_codigo`),
  CONSTRAINT `FK_bq5klqlttk0jqesbck9nx2fqj` FOREIGN KEY (`tipo_codigo`) REFERENCES `tipo` (`codigo`),
  CONSTRAINT `FK_narr3aeveikinvf5pwelucycs` FOREIGN KEY (`contribuinte_codigo`) REFERENCES `contribuinte` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processo`
--

LOCK TABLES `processo` WRITE;
/*!40000 ALTER TABLE `processo` DISABLE KEYS */;
INSERT INTO `processo` VALUES (1,NULL,'2016-01-06','1111111','aberto',1,1);
/*!40000 ALTER TABLE `processo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setor`
--

DROP TABLE IF EXISTS `setor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `setor` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(80) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setor`
--

LOCK TABLES `setor` WRITE;
/*!40000 ALTER TABLE `setor` DISABLE KEYS */;
INSERT INTO `setor` VALUES (1,'RECEITA'),(2,'AUTENTICAO');
/*!40000 ALTER TABLE `setor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo`
--

DROP TABLE IF EXISTS `tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo`
--

LOCK TABLES `tipo` WRITE;
/*!40000 ALTER TABLE `tipo` DISABLE KEYS */;
INSERT INTO `tipo` VALUES (1,'COMPRA'),(2,'VENDA'),(3,'DOACAO'),(4,'A'),(5,'B'),(6,'AFAS'),(7,'DASFA3WE');
/*!40000 ALTER TABLE `tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `login` varchar(50) NOT NULL,
  `senha` varchar(32) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `funcionario_codigo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_ha0f8fm30013tp2ghytbji923` (`funcionario_codigo`),
  CONSTRAINT `FK_ha0f8fm30013tp2ghytbji923` FOREIGN KEY (`funcionario_codigo`) REFERENCES `funcionario` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'','admin','202cb962ac59075b964b07152d234b70','ADMIN',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-09 18:48:55
