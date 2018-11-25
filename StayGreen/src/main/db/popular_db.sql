-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: 25-Nov-2018 às 16:48
-- Versão do servidor: 5.7.19
-- PHP Version: 5.6.31

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

--
-- Extraindo dados da tabela `aluguel`
--

INSERT INTO `aluguel` (`idAluguel`, `idMaquina`, `valorAluguel`, `periodoAluguel`, `dataInicialAluguel`) VALUES
(1, 22, 2900, 365, '2018-04-02'),
(2, 29, 1500, 183, '2018-04-17');

--
-- Extraindo dados da tabela `comprador`
--

INSERT INTO `comprador` (`idComprador`, `nomeComprador`, `enderecoComprador`, `cepComprador`, `modoPagamentoComprador`) VALUES
(1, 'Manuela e Vera Contábil Ltda', 'Rua das Primaveras Cidade Jardim', '38412146', 'BOLETO'),
(2, 'Eduarda e Raul Buffet Ltda', 'Betim Parque das Indústrias', '32671432', 'BOLETO'),
(3, 'Nicolas e Ryan Doces ', 'Belvedere Rua Vicente Basílio de Oliveira', '36201130', 'CARTAO_CREDITO'),
(4, 'Henry e Gael Padaria Ltda', 'Travessa Adelmo de Melo', '57043012', 'CARTAO_DEBITO'),
(5, 'Daiane e Anderson Alimentos ME', 'Rua dos Gaviões', '88137135', 'BOLETO'),
(6, 'Milena e Caroline Casa Noturna ME', 'Rua Hilda Seixas', '69054639', 'CARTAO_DEBITO'),
(7, 'Julia Luzia Duarte', 'Rua da Dama da Noite', '69901248', 'BOLETO'),
(8, 'Gustavo e Elisa Casa Noturna ME', 'Rua Treze de Junho', '78005250', 'CARTAO_DEBITO'),
(9, 'Eloá e Luzia Eletrônica ME', 'Rua Seis', '78715610', 'BOLETO'),
(10, 'Mariana e Benjamin Informática Ltda', 'Rua Cícero Cândido', '58805320', 'BOLETO'),
(14, 'Mariana e Benjamin Informática Ltda', 'Rua Cícero Cândido', '58805320', 'BOLETO'),
(13, 'Tiago Thales Anderson Lima', 'Rua Cícero Cândido', '58805320', 'CARTAO_CREDITO'),
(15, 'Daniel e César Padaria ME', 'Conjunto Frei Eugênio', '38081075', 'CARTAO_DEBITO'),
(16, 'Simone e Pietro Restaurante Ltda', 'Avenida Barão do Rio Branco 2340', '36016902', 'BOLETO'),
(17, 'Renan Danilo Assis', 'Novo Boa Vista', '32145266', 'CARTAO_DEBITO'),
(18, 'Iago e Juan Pizzaria Delivery Ltda', 'Avenida Deputado Newton Macedo', '64091110', 'CARTAO_CREDITO'),
(19, 'Emily Heloise Eduarda da Silva', 'Quadra 112 Sul Rua SR 3', '77020172', 'BOLETO'),
(20, 'Eliane e Marcelo Comercio de Bebidas ME', 'Rua Vicente Gualberto Ribeiro', '64745970', 'CARTAO_DEBITO'),
(21, 'Nicolas e Aparecida Doces ', 'Praça João Soares Vila Brasília Montes Claros', '39400512', 'BOLETO'),
(22, 'Nathan e Severino Pizzaria ME', 'Rua F Residencial Montserrat', '32625240', 'CARTAO_DEBITO'),
(23, 'Louise Maitê das Neves', 'Rua Antônio Ferrarini', '83412640', 'BOLETO'),
(24, 'Camila e Fabiana Buffet ME', 'Rua Natal', '45416972', 'CARTAO_DEBITO'),
(25, 'Caio e Julio Comercio de Bebidas Ltda', 'Metropolitana (Núcleo Bandeirante)', '71730020', 'CARTAO_DEBITO'),
(26, 'Raul e Caroline Buffet Ltda', 'Jardim São Nicolau', '03685090', 'BOLETO'),
(27, 'Martin e Henrique Eletrônica ME', 'Rua Anita Soares', '30350285', 'BOLETO'),
(28, 'Laura Rebeca Almada', 'Rua Rio Verde', '33060276', 'CARTAO_CREDITO'),
(29, 'Antonio e Erick Pizzaria ME', 'Bonsucesso (Barreiro)', '31267714', 'CARTAO_CREDITO'),
(30, 'Theo e Carolina Pães e Doces Ltda', 'Parque Residencial Nova Fronteira', '77415380', 'CARTAO_DEBITO'),
(31, 'Stella Betina Caroline Pinto', 'Rua Taquara', '22740401', 'BOLETO'),
(32, 'Benjamin Filipe Dias', 'Rua Frei Damião', '57070550', 'CARTAO_CREDITO'),
(33, 'Pietro e Thales Pizzaria Ltda', 'Barra do Ceará', '60331570', 'CARTAO_CREDITO'),
(34, 'Carolina e Olivia Informática ME', 'Rua Moacir Paes', '26182145', 'BOLETO'),
(35, 'Thiago Benício Rezende', 'Rua T. Loureiro', '69037255', 'CARTAO_DEBITO'),
(36, 'Tomás Henrique Julio Dias', 'Rua Quintino Bocaiúva', '65917285', 'BOLETO'),
(37, 'Priscila e Julio Restaurante Ltda', 'Rua Moisés Alves dos Santos', '08111510', 'CARTAO_DEBITO');

--
-- Extraindo dados da tabela `estoqueprodutos`
--

INSERT INTO `estoqueprodutos` (`idEstoque`, `idProduto`, `quantProduzidaEstoque`, `dataProducaoEstoque`) VALUES
(1, 1, 640, '2018-11-25'),
(2, 3, 1790, '2018-11-25'),
(3, 4, 7750, '2018-11-25'),
(4, 2, 3230, '2018-11-25'),
(5, 1, 2540, '2018-11-25'),
(6, 4, 7438, '2018-11-25'),
(7, 1, 2800, '2018-11-25'),
(8, 2, 3567, '2018-11-25'),
(9, 3, 2345, '2018-11-25'),
(10, 4, 6050, '2018-11-25'),
(11, 1, 5500, '2018-11-25'),
(12, 4, 3000, '2018-11-25'),
(13, 3, 950, '2018-11-25'),
(14, 2, 4657, '2018-11-25');

--
-- Extraindo dados da tabela `frete`
--

INSERT INTO `frete` (`idFrete`, `regiaoFrete`, `precoFrete`) VALUES
(1, 'SUL', 24),
(2, 'SUDESTE', 18),
(3, 'CENTRO_OESTE', 28),
(4, 'NORDESTE', 30),
(5, 'NORTE', 35);

--
-- Extraindo dados da tabela `insumo`
--

INSERT INTO `insumo` (`idInsumo`, `nomeInsumo`, `finalidadeInsumo`, `valorCompraInsumo`, `quantEstoqueInsumo`, `pontoAvisoInsumo`) VALUES
(1, 'Fungicida', 'Evitar proliferação de fungos na colheita', 66.33, 100, 100),
(2, 'Herbicida', 'Evitar proliferação de ervas na colheita', 51.98, 160, 150),
(3, 'Rodenticida', 'Evitar ação e presença de roedores na fazenda', 97.35, 0, 70),
(4, 'Antibiótico', 'Medicamentos para tratar possíveis infecções nos animais', 912, 20, 5),
(5, 'Inseticida', 'Evitar proliferação de insetos na colheita', 35.54, 700, 300),
(6, 'Fertilizante', 'Prover nutrientes essenciais ao crescimento das plantas', 11.63, 460, 100),
(7, 'Calcifediol', 'Hidroxicolecalciferol para estimular a produção de leite das vacas.', 1000, 10, 5),
(8, 'Suplemento Mineral para Bovinos', 'Ração e aditivo para a alimentação dos animais', 40.66, 440, 250),
(9, 'ADE Pró + Vitamina B12 para Bovinos', 'Ração para alimentar os animais.', 16.68, 500, 300);

--
-- Extraindo dados da tabela `patrimonio`
--

INSERT INTO `patrimonio` (`idPatrimonio`, `nomePatrimonio`, `finalidadePatrimonio`, `tipoPatrimonio`, `valorCompraPatrimonio`, `dataCompraPatrimonio`, `statusPatrimonio`, `dataSaidaPatrimonio`, `dataRetornoPatrimonio`, `dataBaixaPatrimonio`, `indDeprecPatrimonio`) VALUES
(1, 'Pistola De Vacinação', 'Equipamento médico para manutenção da saúde dos animais', 'UTENSILIO', 306.69, '2017-06-14', 'EM_POSSE', NULL, NULL, NULL, 5),
(2, 'Fazenda Boa Vista', 'Fazenda Boa Vista, Porto Feliz', 'TERRENO', 5856, '2015-01-22', 'EM_POSSE', NULL, NULL, NULL, 4),
(3, 'Fazenda Verde Olhar', 'Rua Barão do Rio Branco 94', 'TERRENO', 12906400, '2017-03-02', 'EM_POSSE', NULL, NULL, NULL, 7),
(4, 'Arisca', 'Bezerra Nelore - Fêmea (12 meses)', 'ANIMAL', 900, '2017-05-01', 'EM_POSSE', NULL, NULL, NULL, 20),
(5, 'Beleza', 'Vaca Jersey - Fêmea (5 anos)', 'ANIMAL', 2600, '2017-11-17', 'EM_POSSE', NULL, NULL, NULL, 20),
(6, 'Dondoca', 'Vaca Jersey - Fêmea (2 anos)', 'ANIMAL', 1000, '2018-05-15', 'EM_POSSE', NULL, NULL, NULL, 20),
(7, 'Mimosa', 'Holstein Friesian cattle - Fêmea (17 anos)', 'ANIMAL', 350, '2017-06-05', 'EM_POSSE', NULL, NULL, NULL, 15),
(8, 'Grinalda', 'Simmental cattle - Fêmea (20 anos)', 'ANIMAL', 650, '2018-04-03', 'EM_POSSE', NULL, NULL, NULL, 20),
(9, 'Bernardete', 'Texas Longhorn - (18 anos)', 'ANIMAL', 450, '2018-04-11', 'EM_POSSE', NULL, NULL, NULL, 20),
(10, 'Estrela', 'Holstein Friesian cattle - Fêmea (19 anos)', 'ANIMAL', 900, '2017-06-06', 'EM_POSSE', NULL, NULL, NULL, 15),
(11, 'Milka', 'Simmental cattle - Fêmea (21 anos)', 'ANIMAL', 320, '2018-04-01', 'EM_POSSE', NULL, NULL, NULL, 20),
(12, 'Pintadinha', 'Texas Longhorn - (13 anos)', 'ANIMAL', 550, '2017-08-01', 'EM_POSSE', NULL, NULL, NULL, 20),
(13, 'Celeiro', 'Galpão metálico estilo Celeiro Americano contendo: estruturas metálicas com cobertura em telhas trapezoidal', 'IMOVEL', 300000, '2015-12-02', 'EM_POSSE', NULL, NULL, NULL, 30),
(14, 'Silo', 'Benfeitoria agrícola destinada ao armazenamento de produtos agrícolas', 'IMOVEL', 46000, '2016-05-17', 'EM_POSSE', NULL, NULL, NULL, 26),
(15, 'Silo', 'Benfeitoria agrícola destinada ao armazenamento de produtos agrícolas', 'IMOVEL', 46000, '2016-05-18', 'EM_POSSE', NULL, NULL, NULL, 26),
(16, 'Esatbulo', 'Local vedado onde o gado bovino são recolhidos', 'IMOVEL', 20000, '2016-03-21', 'EM_POSSE', NULL, NULL, NULL, 25),
(17, 'Chácara ', 'Propriedade rural', 'IMOVEL', 1200000, '2016-09-10', 'EM_POSSE', NULL, NULL, NULL, 7.5),
(18, 'Freightliner M2 dump truck', 'Caminhão de carga pesada da marca Freightliner ', 'VEICULO', 24000, '2016-04-15', 'EM_POSSE', NULL, NULL, NULL, 21),
(19, 'Ford Ranger', 'Pickup ford range 2019', 'VEICULO', 109390, '2018-11-16', 'EM_POSSE', NULL, NULL, NULL, 35),
(20, 'Semi-trailer truck Road train', 'Caminhão de múltiplos carregamentos para cargas pesadas e entregas longas', 'VEICULO', 50000, '2017-06-30', 'EM_POSSE', NULL, NULL, NULL, 22),
(21, 'Segadeira', 'Utilizada para cortar grama, cereais e capim – esse último transformado em feno para alimentação animal', 'MAQUINA', 360000, '2015-10-01', 'EM_POSSE', NULL, NULL, NULL, 10),
(22, 'Ordenhadeira', 'Uma máquina que ajuda no processo de retirar leite.', 'MAQUINA', 3250, '2017-12-01', 'ALUGADO', '2018-04-02', '2019-04-02', NULL, 3),
(23, 'Patrola Rebocada', 'Utilizada em estradas de terra e no meio dos canaviais para nivelar o terreno por onde passam os veículos', 'MAQUINA', 1250, '2017-12-01', 'EM_MANUTENCAO', '2018-06-02', '2019-01-02', NULL, 3),
(24, 'Enfardadora', 'Produz fardos de feno cilindricos', 'MAQUINA', 97000, '2015-02-01', 'DESCARTADO', NULL, NULL, '2018-10-03', 4),
(25, 'Enfardadora', 'Produz fardos de feno cilindricos', 'MAQUINA', 175000, '2018-07-07', 'VENDIDO', NULL, NULL, '2018-09-30', 2.5),
(26, 'Enleirador de Café', 'Cata grãos espalhados pelo solo', 'MAQUINA', 93000, '2017-05-29', 'EM_POSSE', NULL, NULL, NULL, 2),
(27, 'Trator Massey Ferguson 290', 'Uma maquina versátil e de fácil adaptação para trabalhos diversos', 'MAQUINA', 30996, '1989-02-23', 'VENDIDO', NULL, NULL, '2015-09-07', 1.5),
(28, 'Trator D6k Caterpillar', 'Uma maquina versátil e de fácil adaptação para trabalhos diversos', 'MAQUINA', 93000, '2015-08-12', 'EM_POSSE', NULL, NULL, NULL, 5),
(29, 'Trator Valmet 60id ', 'Uma maquina versátil e de fácil adaptação para trabalhos diversos', 'MAQUINA', 27000, '2018-02-05', 'ALUGADO', '2018-04-17', '2018-10-17', NULL, 4.5);

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`idProduto`, `nomeProduto`, `descrProduto`, `valorUnitProduto`, `quantEstoqueProduto`, `pontoAvisoProduto`, `fotoMercadoria`, `unidMedProduto`) VALUES
(1, 'LEITE', 'Leite com o cuidado em cada processo, para garantir a melhor qualidade aos nossos consumidores', 1.462, 5500, 1000, 'imgs/PaginaVendas/leite.jpg', 'L'),
(2, 'CAFE_ROBUSTA', 'Café originária da África Ocidental ideal para fazer cafés instantâneos ou expressos', 5.5, 4657, 750, 'imgs/PaginaVendas/cafe-robusta.jpg', 'KG'),
(3, 'CAFE_ARABICA', 'Café natural da Etiópia, ele produz cafés de qualidade, finos e requintados com aroma intenso e diversos sabores', 7.313, 950, 1000, 'imgs/PaginaVendas/cafe-arabica.jpg', 'KG'),
(4, 'CAFE_BOURBON', 'Variedade de café arábica originária da Etiópia possui doçura natural, textura achocolatada, aroma intenso. Café gourmet', 9.028, 3000, 3000, 'imgs/PaginaVendas/cafe-bourbon.jpg', 'KG');

--
-- Extraindo dados da tabela `tarefa`
--

INSERT INTO `tarefa` (`idTarefa`, `nomeTarefa`, `descrTarefa`, `tipoTarefa`, `dataInicialTarefa`, `periodRepetTarefa`, `gastoTarefa`, `quantProduzTarefa`, `insumosTarefa`, `quantInsumosTarefa`, `produtoProduzido`) VALUES
(1, 'Adubar Plantação de Café Robusta', 'Usar o fertilizante para aumentar  os nutrientes do solo. 60R$/ha', 'ADUBACAO', '2016-04-05', 20, 300, 0, 'Fertilizante', 1, 'CAFE_ROBUSTA'),
(4, 'Adubar Plantação de Café Burbuon', 'Usar o fertilizante para aumentar  os nutrientes do solo. 60R$/ha', 'ADUBACAO', '2017-04-05', 15, 420, 0, 'Fertilizante', 1, 'CAFE_BOURBON'),
(5, 'Adubar Plantação de Café Arabica', 'Usar o fertilizante para aumentar  os nutrientes do solo. 60R$/ha', 'ADUBACAO', '2017-06-05', 26, 240, 0, 'Fertilizante', 1, 'CAFE_ARABICA'),
(6, 'Capinar o solo', 'Descompactá a terra e viabilizar um melhor desenvolvimento das raízes das plantas, expondo o subsolo à ação do sol, ajudando a aumentar a temperatura', 'ARAR', '2015-12-09', 15, 3500, 0, '', 0, 'CAFE_BOURBON'),
(7, 'Arar a Plantação de Café Burbuon', 'Descompactar o solo para facilitar o plantio', 'ARAR', '2017-04-05', 13, 1300, 0, '', 0, 'CAFE_BOURBON'),
(8, 'Arar a Plantação de Café Robusta', 'Descompactar o solo para facilitar o plantio', 'ARAR', '2016-04-05', 13, 1600, 0, '', 0, 'CAFE_ROBUSTA'),
(9, 'Arar a Plantação de Café Arabica', 'Descompactar o solo para facilitar o plantio', 'ARAR', '2017-06-05', 13, 2000, 0, '', 0, 'CAFE_ARABICA'),
(10, 'Exame médico dos animais', 'verificar a saúde dos animais da fazenda', 'PECUARIA', '2017-08-01', 7, 10000, 0, 'Antibiótico', 0, 'LEITE'),
(11, 'Irrigar plantações de café', 'Aguar toda colheita de café Robusta', 'IRRIGACAO', '2015-04-01', 3, 3200, 0, '', 0, 'CAFE_ROBUSTA'),
(12, 'Colher safra de Café Robusta', 'Recolher as sementes de Café Robusta', 'COLHEITA', '2016-04-10', 5, 1200, 1500, '', 0, 'CAFE_ROBUSTA'),
(13, 'Colher safra de Café Arábica', 'Colher safra de Café Arábica', 'COLHEITA', '2017-06-10', 7, 2000, 800, '', 0, 'CAFE_ARABICA'),
(14, 'Colher safra de Café Burbon', 'Colher safra de Café Burbon', 'ARAR', '2017-04-10', 13, 2345, 500, '', 0, 'CAFE_BOURBON'),
(15, 'Recolher leite das vacas', 'Extrair o leite das vacas', 'PECUARIA', '2016-12-02', 3, 120, 500, '', 0, 'LEITE');

--
-- Extraindo dados da tabela `transacao`
--

INSERT INTO `transacao` (`idTransacao`, `valorTransacao`, `quantTransacao`, `dataTransacao`, `idItemTransacao`, `tipoTransacao`) VALUES
(1, 73.13, -10, '2018-09-03', 3, 'PRODUTO'),
(2, 2193, -1500, '2018-09-06', 1, 'PRODUTO'),
(3, 2924, -2000, '2018-09-06', 1, 'PRODUTO'),
(4, 550, -100, '2018-09-10', 2, 'PRODUTO'),
(5, 2257, -250, '2018-09-18', 4, 'PRODUTO'),
(6, 1650, -300, '2018-09-22', 2, 'PRODUTO'),
(7, 225.70000000000002, -25, '2018-09-22', 4, 'PRODUTO'),
(8, 175.44, -120, '2018-10-01', 1, 'PRODUTO'),
(9, 66, -12, '2018-10-04', 2, 'PRODUTO'),
(10, 315.98, -35, '2018-10-08', 4, 'PRODUTO'),
(14, 1128.5, -125, '2018-10-22', 4, 'PRODUTO'),
(13, 27.5, -5, '2018-10-16', 2, 'PRODUTO'),
(15, 394.74, -270, '2018-10-30', 1, 'PRODUTO'),
(16, 677.1, -75, '2018-10-30', 4, 'PRODUTO'),
(17, 1128.5, -125, '2018-11-04', 4, 'PRODUTO'),
(18, 511.7, -350, '2018-11-08', 1, 'PRODUTO'),
(19, 108.33600000000001, -12, '2018-11-11', 4, 'PRODUTO'),
(20, 1925, -350, '2018-11-11', 2, 'PRODUTO'),
(21, 1462, -1000, '2018-11-19', 1, 'PRODUTO'),
(22, 175.44, -120, '2018-11-22', 1, 'PRODUTO'),
(23, 45.14, -5, '2018-11-24', 4, 'PRODUTO'),
(24, -116.30000000000001, 10, '2018-11-25', 6, 'INSUMO'),
(25, -325.28, 8, '2018-11-25', 8, 'INSUMO'),
(26, 146.2, -100, '2018-11-25', 1, 'PRODUTO'),
(27, 731.3, -100, '2018-11-25', 3, 'PRODUTO'),
(28, 108.33600000000001, -12, '2018-11-25', 4, 'PRODUTO'),
(29, 550, -100, '2018-11-25', 2, 'PRODUTO'),
(30, 731, -500, '2018-11-25', 1, 'PRODUTO'),
(31, 1805.6000000000001, -200, '2018-11-25', 4, 'PRODUTO'),
(32, 225.70000000000002, -25, '2018-11-25', 4, 'PRODUTO'),
(33, 365.65, -50, '2018-11-25', 3, 'PRODUTO'),
(34, 137.5, -25, '2018-11-25', 2, 'PRODUTO'),
(35, 146.2, -100, '2018-11-25', 1, 'PRODUTO'),
(36, 1083.3600000000001, -120, '2018-11-25', 4, 'PRODUTO'),
(37, 660, -120, '2018-11-25', 2, 'PRODUTO'),
(38, 438.59999999999997, -300, '2018-11-25', 1, 'PRODUTO'),
(39, 1100, -200, '2018-11-25', 2, 'PRODUTO'),
(40, 45.14, -5, '2018-11-25', 4, 'PRODUTO'),
(41, 73.13, -10, '2018-11-25', 3, 'PRODUTO'),
(42, 550, -100, '2018-11-25', 2, 'PRODUTO'),
(43, 146.2, -100, '2018-11-25', 1, 'PRODUTO'),
(44, 365.65, -50, '2018-11-25', 3, 'PRODUTO'),
(45, 451.40000000000003, -50, '2018-11-25', 4, 'PRODUTO'),
(46, 550, -100, '2018-11-25', 2, 'PRODUTO'),
(47, 584.8, -400, '2018-11-25', 1, 'PRODUTO'),
(48, 72.224, -8, '2018-11-25', 4, 'PRODUTO'),
(49, 73.13, -10, '2018-11-25', 3, 'PRODUTO'),
(50, 467.5, -85, '2018-11-25', 2, 'PRODUTO'),
(51, 204.68, -140, '2018-11-25', 1, 'PRODUTO'),
(52, 550, -100, '2018-11-25', 2, 'PRODUTO'),
(53, 131.57999999999998, -90, '2018-11-25', 1, 'PRODUTO'),
(54, 55, -10, '2018-11-25', 2, 'PRODUTO'),
(55, 14.62, -10, '2018-11-25', 1, 'PRODUTO'),
(56, 511.90999999999997, -70, '2018-11-25', 3, 'PRODUTO'),
(57, 1650, -300, '2018-11-25', 2, 'PRODUTO'),
(58, 4514, -500, '2018-11-25', 4, 'PRODUTO'),
(59, 146.2, -100, '2018-11-25', 1, 'PRODUTO'),
(60, 451.40000000000003, -50, '2018-11-25', 4, 'PRODUTO'),
(61, 247.5, -45, '2018-11-25', 2, 'PRODUTO'),
(62, 4333.4400000000005, -480, '2018-11-25', 4, 'PRODUTO'),
(63, -1163, 100, '2018-11-25', 6, 'INSUMO'),
(64, -3000, 3, '2018-11-25', 7, 'INSUMO'),
(65, 0, -225, '2018-11-25', 2, 'INSUMO'),
(66, 0, -140, '2018-11-25', 1, 'INSUMO'),
(67, -4418.3, 85, '2018-11-25', 2, 'INSUMO'),
(68, 0, -50, '2018-11-25', 3, 'INSUMO');

--
-- Extraindo dados da tabela `vendausuario`
--

INSERT INTO `vendausuario` (`idVenda`, `idTransacao`, `idComprador`, `freteVenda`, `tempoEntregaVenda`, `numeroVenda`) VALUES
(1, 1, 1, 18, '2018-09-04', 1),
(2, 2, 2, 18, '2018-09-07', 2),
(3, 3, 3, 18, '2018-09-07', 3),
(4, 4, 4, 30, '2018-09-11', 4),
(5, 5, 5, 24, '2018-09-19', 5),
(6, 6, 6, 35, '2018-09-23', 6),
(7, 7, 7, 30, '2018-09-23', 7),
(8, 8, 8, 28, '2018-10-02', 8),
(9, 9, 9, 18, '2018-10-05', 9),
(10, 10, 10, 30, '2018-10-09', 10),
(14, 14, 14, 30, '2018-10-23', 14),
(13, 13, 13, 30, '2018-10-17', 11),
(15, 15, 15, 18, '2018-10-31', 15),
(16, 16, 16, 18, '2018-10-31', 16),
(17, 17, 17, 18, '2018-11-05', 17),
(18, 18, 18, 30, '2018-11-09', 18),
(19, 19, 19, 24, '2018-11-12', 19),
(20, 20, 20, 35, '2018-11-12', 20),
(21, 21, 21, 18, '2018-11-20', 21),
(22, 22, 22, 18, '2018-11-23', 22),
(23, 23, 23, 24, '2018-11-25', 23),
(24, 26, 24, 30, '2018-11-30', 24),
(25, 27, 24, 30, '2018-11-30', 24),
(26, 28, 24, 30, '2018-11-30', 24),
(27, 29, 25, 28, '2018-12-03', 27),
(28, 30, 25, 28, '2018-12-03', 27),
(29, 31, 25, 28, '2018-12-03', 27),
(30, 32, 26, 18, '2018-12-12', 30),
(31, 33, 26, 18, '2018-12-12', 30),
(32, 34, 26, 18, '2018-12-12', 30),
(33, 35, 26, 18, '2018-12-12', 30),
(34, 36, 33, 18, '2018-12-19', 34),
(35, 37, 33, 18, '2018-12-19', 34),
(36, 38, 33, 18, '2018-12-19', 34),
(37, 39, 27, 18, '2018-12-17', 37),
(38, 40, 28, 28, '2018-12-31', 38),
(39, 41, 28, 28, '2018-12-31', 38),
(40, 42, 29, 18, '2018-12-14', 40),
(41, 43, 29, 18, '2018-12-14', 40),
(42, 44, 41, 24, '2018-12-29', 42),
(43, 45, 41, 24, '2018-12-29', 42),
(44, 46, 41, 24, '2018-12-29', 42),
(45, 47, 41, 24, '2018-12-29', 42),
(46, 48, 30, 35, '2018-12-25', 46),
(47, 49, 30, 35, '2018-12-25', 46),
(48, 50, 30, 35, '2018-12-25', 46),
(49, 51, 30, 35, '2018-12-25', 46),
(50, 52, 31, 30, '2018-12-15', 50),
(51, 53, 31, 30, '2018-12-15', 50),
(52, 54, 32, 24, '2018-12-11', 52),
(53, 55, 32, 24, '2018-12-11', 52),
(54, 56, 33, 30, '2018-12-16', 54),
(55, 57, 33, 30, '2018-12-16', 54),
(56, 58, 34, 18, '2018-12-18', 56),
(57, 59, 35, 35, '2018-11-30', 57),
(58, 60, 36, 30, '2018-12-05', 58),
(59, 61, 36, 30, '2018-12-05', 58),
(60, 62, 37, 18, '2018-12-07', 60);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
