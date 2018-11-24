

DROP DATABASE IF EXISTS `staygreen`;

CREATE DATABASE `staygreen` DEFAULT CHARACTER SET utf8;

USE `staygreen`;

-- Create tables section -------------------------------------------------


-- Table Aluguel

CREATE TABLE IF NOT EXISTS `Aluguel` (
    `idAluguel` BigInt(20) NOT NULL AUTO_INCREMENT,
    `idMaquina` BigInt(20) DEFAULT NULL,
    `valorAluguel` Double DEFAULT NULL,
    `periodoAluguel` BigInt DEFAULT NULL,
    `dataInicialAluguel` Date DEFAULT NULL,
    PRIMARY KEY (`idAluguel`)
)
CHARSET = utf8;


-- Table Comprador

CREATE TABLE `Comprador`
(
  `idComprador` BigInt NOT NULL AUTO_INCREMENT,
  `nomeComprador` Varchar(40) NOT NULL,
  `enderecoComprador` Varchar(120) NOT NULL,
  `cepComprador` Varchar(8) NOT NULL,
  `modoPagamentoComprador` Enum('CARTAO_CREDITO', 'CARTAO_DEBITO', 'BOLETO') NOT NULL,
  PRIMARY KEY (`idComprador`)
)
CHARSET = utf8;


-- Table EstoqueProdutos

CREATE TABLE `EstoqueProdutos`
(
  `idEstoque` BigInt NOT NULL AUTO_INCREMENT,
  `idProduto` BigInt NOT NULL,
  `quantProduzidaEstoque` BigInt NOT NULL,
  `dataProducaoEstoque` Date NOT NULL,
  PRIMARY KEY (`idEstoque`)
)
CHARSET = utf8;


-- Table Insumo

CREATE TABLE `Insumo`
(
  `idInsumo` BigInt NOT NULL AUTO_INCREMENT,
  `nomeInsumo` Varchar(40) NOT NULL,
  `finalidadeInsumo` Varchar(120) NOT NULL,
  `valorCompraInsumo` Double NOT NULL,
  `quantEstoqueInsumo` BigInt NOT NULL,
  `pontoAvisoInsumo` BigInt NOT NULL,
  PRIMARY KEY (`idInsumo`)
)
CHARSET = utf8;


-- Table Patrimonio

CREATE TABLE `Patrimonio`
(
  `idPatrimonio` Int NOT NULL AUTO_INCREMENT,
  `nomePatrimonio` Varchar(40) NOT NULL,
  `finalidadePatrimonio` text NOT NULL,
  `tipoPatrimonio` Enum('MAQUINA', 'ANIMAL', 'IMOVEL', 'MERCADORIA', 'UTENSILIO', 'VEICULO', 'TERRENO', 'OUTROS') NOT NULL,
  `valorCompraPatrimonio` Double NOT NULL,
  `dataCompraPatrimonio` Date NOT NULL,
  `statusPatrimonio` Enum('EM_POSSE', 'VENDIDO', 'ALUGADO', 'DESCARTADO', 'EM_MANUTENCAO') NOT NULL,
  `dataSaidaPatrimonio` Date,
  `dataRetornoPatrimonio` Date,
  `dataBaixaPatrimonio` Date,
  `indDeprecPatrimonio` Double NOT NULL,
  PRIMARY KEY (`idPatrimonio`)
)
CHARSET = utf8;


-- Table Produto

CREATE TABLE `Produto`
(
  `idProduto` BigInt NOT NULL AUTO_INCREMENT,
  `nomeProduto` Enum('LEITE', 'CAFE_BOURBON', 'CAFE_ROBUSTA', 'CAFE_ARABICA') NOT NULL,
  `descrProduto` Varchar(120) NOT NULL,
  `valorUnitProduto` Double NOT NULL,
  `quantEstoqueProduto` BigInt NOT NULL,
  `pontoAvisoProduto` BigInt NOT NULL,
  `fotoMercadoria` Varchar(200) NOT NULL,
  `unidMedProduto` Enum('KG', 'L') NOT NULL,
  PRIMARY KEY (`idProduto`)
)
CHARSET = utf8;


-- Table Tarefa

CREATE TABLE `Tarefa`
(
  `idTarefa` BigInt NOT NULL AUTO_INCREMENT,
  `nomeTarefa` Varchar(40) NOT NULL,
  `descrTarefa` Varchar(200) NOT NULL,
  `tipoTarefa` Enum('ADUBACAO', 'IRRIGACAO', 'ARAR', 'MAQUINARIO', 'COLHEITA', 'PECUARIA', 'OUTRAS') NOT NULL,
  `dataInicialTarefa` Date NOT NULL,
  `periodRepetTarefa` BigInt NOT NULL,
  `gastoTarefa` Double NOT NULL,
  `quantProduzTarefa` BigInt NOT NULL,
  `insumosTarefa` Varchar(200) NOT NULL,
  `quantInsumosTarefa` BigInt NOT NULL,
  `produtoProduzido` Enum('LEITE', 'CAFE_BOURBON', 'CAFE_ROBUSTA', 'CAFE_ARABICA') NOT NULL,
  PRIMARY KEY (`idTarefa`)
)
CHARSET = utf8;


-- Table Transacao

CREATE TABLE `Transacao`
(
  `idTransacao` BigInt NOT NULL AUTO_INCREMENT,
  `valorTransacao` Double NOT NULL,
  `quantTransacao` BigInt NOT NULL DEFAULT 1,
  `dataTransacao` Date NOT NULL,
  `idItemTransacao` BigInt NOT NULL,
  `tipoTransacao` Enum('PATRIMONIO', 'INSUMO', 'PRODUTO', 'MAQUINA') NOT NULL,
  PRIMARY KEY (`idTransacao`)
)
CHARSET = utf8;


-- Table Usuario

CREATE TABLE `Usuario`
(
  `idUsuario` BigInt NOT NULL AUTO_INCREMENT,
  `nomeUsuario` Varchar(40) NOT NULL,
  `emailUsuario` Varchar(40) NOT NULL,
  `senhaUsuario` Varchar(128) NOT NULL,
  `cnpjUsuario` Varchar(40) NOT NULL,
  `saldoUsuario` Double NOT NULL,
  PRIMARY KEY (`idUsuario`)
)
CHARSET = utf8;


-- Table VendaUsuario

CREATE TABLE `VendaUsuario`
(
  `idVenda` BigInt NOT NULL AUTO_INCREMENT,
  `idTransacao` BigInt NOT NULL,
  `freteVenda` Double NOT NULL,
  `tempoEntregaVenda` BigInt NOT NULL,
  `idComprador` BigInt NOT NULL,
  `numeroVenda` BigInt NOT NULL,
  PRIMARY KEY (`idVenda`)
)
CHARSET = utf8;


-- Table Frete

CREATE TABLE `Frete`
(
  `idFrete` BigInt NOT NULL AUTO_INCREMENT,
  `regiaoFrete` Enum ('SUL', 'SUDESTE', 'CENTRO_OESTE', 'NORDESTE', 'NORTE') NOT NULL,
  `precoFrete` Double NOT NULL,
  PRIMARY KEY (`idFrete`)
)
CHARSET = utf8;
