-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Oct 11, 2018 at 01:07 AM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `staygreen`
--

-- --------------------------------------------------------

--
-- Table structure for table `patrimonio`
--

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
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `patrimonio`
--

INSERT INTO `patrimonio` (`Id`, `Nome`, `Tipo`, `Descrição`, `Status`, `Índice de Depreciação`, `Valor da Compra`, `Valor Atual`, `Data da Compra`, `Data da Saída`, `Dada da Baixa`) VALUES
(4, 'Trator', 'Máquina', 'Uma máquina que ajuda na colheita do café.', 'VENDIDO', 15, 5000, 4800, '2018-10-01', '2018-10-03', '2018-10-02');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
