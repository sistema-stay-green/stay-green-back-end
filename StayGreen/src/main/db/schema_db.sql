/* 
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */

-- ----------------------------
-- Criação do banco de dados:
-- ----------------------------

DROP DATABASE IF EXISTS `staygreen`;

CREATE DATABASE `staygreen` DEFAULT CHARACTER SET utf8;

USE `staygreen`;

-- ----------------------
-- Criação das tabelas:
-- ----------------------

-- Tabela Patrimonio

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

DROP TABLE IF EXISTS `patrimonio`;
CREATE TABLE IF NOT EXISTS `patrimonio` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(30) DEFAULT NULL,
  `Tipo` varchar(30) DEFAULT NULL,
  `Descrição` text,
  `Status` enum('VENDIDO','ALUGADO','EM_POSSE','DESCARTADO') DEFAULT NULL,
  `Índice de Depreciação` double DEFAULT NULL,
  `Valor da Compra` double DEFAULT NULL,
  `Valor Atual` double DEFAULT NULL,
  `Data da Compra` date DEFAULT NULL,
  `Data da Saída` date DEFAULT NULL,
  `Dada da Baixa` date DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- Tabela ...

-- Exemplo: CREATE TABLE tabela (...) DEFAULT CHARACTER SET utf8;
