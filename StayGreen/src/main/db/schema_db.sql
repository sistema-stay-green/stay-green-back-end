/*
Created: 22/10/2018
Modified: 22/10/2018
Project: DB_TrabalhoFinal
Model: schema_db.sql
Database: MySQL 5.7
*/


-- Create tables section -------------------------------------------------

-- Table Produto

CREATE TABLE `Produto`
(
  `idProduto` Int NOT NULL AUTO_INCREMENT,
  `nomeProduto` Varchar(40) NOT NULL,
  `descrProduto` Varchar(120) NOT NULL,
  `valorUnitProduto` Double NOT NULL,
  `quantEstoqueProduto` Int NOT NULL,
  `pontoAvisoProduto` Int NOT NULL,
  `fotoMercadoria` Varchar(200) NOT NULL,
  `unidMedProduto` Enum('KG', 'L') NOT NULL,
  PRIMARY KEY (`idProduto`)
)
;

-- Table Patrimonio

CREATE TABLE `Patrimonio`
(
  `idPatrimonio` Int NOT NULL AUTO_INCREMENT,
  `nomePatrimonio` Varchar(40) NOT NULL,
  `finalidadePatrimonio` Varchar(120) NOT NULL,
  `tipoPatrimonio` Enum('MAQUINA', 'OUTROS') NOT NULL,
  `valorCompraPatrimonio` Double NOT NULL,
  `dataCompraPatrimonio` Date NOT NULL,
  `statusPatrimonio` Enum('EM_POSSE', 'VENDIDO', 'ALUGADO', 'DESCARTADO', 'MANUTENÇÃO') NOT NULL,
  `dataSaidaPatrimonio` Date,
  `dataRetornoPatrimonio` Date,
  `dataBaixaPatrimonio` Date,
  `indDeprecPatrimonio` Double NOT NULL,
  PRIMARY KEY (`idPatrimonio`)
)
;

-- Table Insumo

CREATE TABLE `Insumo`
(
  `idInsumo` Int NOT NULL AUTO_INCREMENT,
  `nomeInsumo` Varchar(40) NOT NULL,
  `finalidadeInsumo` Varchar(120) NOT NULL,
  `valorCompraInsumo` Double NOT NULL,
  `quantEstoqueInsumo` Int NOT NULL,
  `pontoAvisoInsumo` Int NOT NULL,
  PRIMARY KEY (`idInsumo`)
)
;

-- Table Tarefa

CREATE TABLE `Tarefa`
(
  `idTarefa` Int NOT NULL AUTO_INCREMENT,
  `nomeTarefa` Varchar(40) NOT NULL,
  `descrTarefa` Varchar(200) NOT NULL,
  `tipoTarefa` Enum('ADUBAÇÃO', 'IRRIGAÇÃO', 'ARAR', 'MAQUINÁRIO', 'COLHEITA', 'PECUÁRIA', 'OUTRAS') NOT NULL,
  `dataInicialTarefa` Date NOT NULL,
  `periodRepetTarefa` Int NOT NULL,
  `gastoTarefa` Double NOT NULL,
  `quantProduzTarefa` Int NOT NULL,
  `insumosTarefa` Varchar(200) NOT NULL,
  `quantInsumosTarefa` Varchar(100) NOT NULL,
  PRIMARY KEY (`idTarefa`)
)
;

-- Table Comprador

CREATE TABLE `Comprador`
(
  `idComprador` Int NOT NULL AUTO_INCREMENT,
  `nomeComprador` Varchar(40) NOT NULL,
  `enderecoComprador` Varchar(120) NOT NULL,
  `cepComprador` Varchar(8) NOT NULL,
  `modoPagamentoComprador` Enum('CARTAO_CREDITO', 'CARTAO_DEBITO', 'BOLETO') NOT NULL,
  PRIMARY KEY (`idComprador`)
)
;

-- Table Usuario

CREATE TABLE `Usuario`
(
  `idUsuario` Int NOT NULL AUTO_INCREMENT,
  `nomeUsuario` Varchar(40) NOT NULL,
  `emailUsuario` Varchar(40) NOT NULL,
  `senhaUsuario` Varchar(20) NOT NULL,
  `cnpjUsuario` Varchar(40) NOT NULL,
  `saldoUsuario` Double NOT NULL,
  PRIMARY KEY (`idUsuario`)
)
;

-- Table VendaUsuario

CREATE TABLE `VendaUsuario`
(
  `idVenda` Int NOT NULL AUTO_INCREMENT,
  `idTransacao` Int NOT NULL,
  `freteVenda` Double NOT NULL,
  `tempoEntregaVenda` Int NOT NULL,
  `idComprador` Int NOT NULL,
  `numeroVenda` Int NOT NULL,
  PRIMARY KEY (`idVenda`)
)
;

-- Table Transacao

CREATE TABLE `Transacao`
(
  `idTransacao` Int NOT NULL AUTO_INCREMENT,
  `valorTransacao` Double NOT NULL,
  `quantTransacao` Int NOT NULL DEFAULT 1,
  `dataTransacao` Date NOT NULL,
  `idItemTransacao` Int NOT NULL,
  `tipoTransacao` Enum('PATRIMONIO', 'INSUMO', 'PRODUTO', 'MAQUINA') NOT NULL,
  PRIMARY KEY (`idTransacao`)
)
;

-- Table EstoqueProdutos

CREATE TABLE `EstoqueProdutos`
(
  `idEstoque` Int NOT NULL AUTO_INCREMENT,
  `idProduto` Int NOT NULL,
  `quantProduzidaEstoque` Int NOT NULL,
  `dataProducaoEstoque` Date NOT NULL,
  PRIMARY KEY (`idEstoque`)
)
;

-- Table Aluguel

CREATE TABLE `Aluguel`
(
  `idAluguel` Int NOT NULL AUTO_INCREMENT,
  `idMaquina` Int NOT NULL,
  `valorAluguel` Double NOT NULL,
  `periodoAluguel` Int NOT NULL,
  `dataInicialAluguel` Date NOT NULL,
  PRIMARY KEY (`idAluguel`)
)
;


