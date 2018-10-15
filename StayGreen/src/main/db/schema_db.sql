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
  `id` int(20) NOT NULL AUTO_INCREMENT,
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

-- Tabela Vendas

DROP TABLE IF EXISTS `vendas`;
CREATE TABLE IF NOT EXISTS `vendas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `produto` varchar(30) DEFAULT NULL,
  `entradaSaida` double DEFAULT NULL,
  `dataTransacao` date DEFAULT NULL,
  `valor` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- Tabela Clientes

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) DEFAULT NULL,
  `modoPagamento` varchar(30) DEFAULT NULL,
  `enderecoEntrega` text,
  `regiao` varchar(30) DEFAULT NULL,
  `valorTransporte` double DEFAULT NULL,
  `dataAgendadaEntrega` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- Tabela ...

-- Exemplo: CREATE TABLE tabela (...) DEFAULT CHARACTER SET utf8;
