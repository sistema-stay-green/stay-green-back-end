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
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) DEFAULT NULL,
  `tipo` varchar(30) DEFAULT NULL,
  `descricao` text,
  `status` enum('VENDIDO','ALUGADO','EM_POSSE','DESCARTADO') DEFAULT NULL,
  `indiceDepreciacao` double DEFAULT NULL,
  `valorCompra` double DEFAULT NULL,
  `valorAtual` double DEFAULT NULL,
  `dataCompra` date DEFAULT NULL,
  `dataSaida` date DEFAULT NULL,
  `dataBaixa` date DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- Tabela Tarefa

DROP TABLE IF EXISTS `tarefa`;
CREATE TABLE IF NOT EXISTS `tarefa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(30) DEFAULT NULL,
  `tipo` VARCHAR(30) DEFAULT NULL,
  `caminhoImg` VARCHAR(30) DEFAULT NULL,
  `dataMarcada` date DEFAULT NULL,
  `repeticao` int DEFAULT NULL,
  `producaoPrevista` double DEFAULT NULL,
  `valorGasto` double DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- Tabela ...

-- Exemplo: CREATE TABLE tabela (...) DEFAULT CHARACTER SET utf8;
