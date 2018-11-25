-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: 24-Nov-2018 às 22:34
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
(1, 'Fungicida', 'Evitar proliferação de fungos na colheita', 66.33, 240, 100),
(2, 'Herbicida', 'Evitar proliferação de ervas na colheita', 51.98, 300, 150),
(3, 'Rodenticida', 'Evitar ação e presença de roedores na fazenda', 97.35, 50, 70),
(4, 'Antibiótico', 'Medicamentos para tratar possíveis infecções nos animais', 912, 20, 5),
(5, 'Inseticida', 'Evitar proliferação de insetos na colheita', 35.54, 700, 300),
(6, 'Fertilizante', 'Prover nutrientes essenciais ao crescimento das plantas', 11.63, 350, 100),
(7, 'Calcifediol', 'Hidroxicolecalciferol para estimular a produção de leite das vacas.', 1000, 7, 5),
(8, 'Suplemento Mineral para Bovinos', 'Ração e aditivo para a alimentação dos animais', 40.66, 432, 250),
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
(1, 'LEITE', 'Leite com o cuidado em cada processo, para garantir a melhor qualidade aos nossos consumidores', 1.4611, 5500, 1000, '', 'L'),
(2, 'CAFE_ROBUSTA', 'Café originária da África Ocidental ideal para fazer cafés instantâneos ou expressos', 5.5, 4000, 750, '', 'KG'),
(3, 'CAFE_ARABICA', 'Café natural da Etiópia, ele produz cafés de qualidade, finos e requintados com aroma intenso e diversos sabores', 7.3137, 1200, 1000, '', 'KG'),
(4, 'CAFE_BOURBON', 'Variedade de café arábica originária da Etiópia possui doçura natural, textura achocolatada, aroma intenso. Café gourmet', 9.0287, 8500, 3000, '', 'KG');

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
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `nomeUsuario`, `emailUsuario`, `senhaUsuario`, `cnpjUsuario`, `saldoUsuario`) VALUES
(2, 'Rubens', 'rubens@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b', '84.264.158/0001-69', 1000000);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
