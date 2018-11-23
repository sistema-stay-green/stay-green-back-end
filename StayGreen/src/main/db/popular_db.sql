/* 
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */

-- Exemplo: INSERT INTO tabela (...) VALUES (...), (...), ..., (...);

USE `staygreen`;

-- Tabela Patrimonio

INSERT INTO `Patrimonio` (`idPatrimonio`, `nomePatrimonio`, `finalidadePatrimonio`, `tipoPatrimonio`, `valorCompraPatrimonio`, `dataCompraPatrimonio`, `statusPatrimonio`, `dataSaidaPatrimonio`, `dataRetornoPatrimonio`, `dataBaixaPatrimonio`, `indDeprecPatrimonio`)
-- VALUES (NULL, 'Trator', 'Máquina', 'Uma máquina que ajuda na colheita do café.', 'VENDIDO', '15', '5000', '4800', '2018-10-01', '2018-10-03', '2018-10-02');

VALUES (NULL, 'Segadeira', 'Utilizada para cortar grama, cereais e capim – esse último transformado em feno para alimentação animal.', 'MAQUINA', '360000', '2014-10-01','EM_POSSE', NULL, NULL, NULL, 10),
            (NULL, 'Vaca', 'Pra dar leite', 'ANIMAL', '5000', '2014-10-01','EM_POSSE', NULL, NULL, NULL, 10),	
            (NULL, 'Ordenhadeira', 'Uma máquina que ajuda no processo de retirar leite.', 'MAQUINA', '3250', '2017-12-01','ALUGADO', '2018-04-02', '2019-04-02', NULL, 3),
            (NULL, 'Patrola rebocada', 'Utilizada em estradas de terra e no meio dos canaviais para nivelar o terreno por onde passam os veículos.', 'MAQUINA', '1250', '2017-12-01','EM_MANUTENCAO', '2018-06-02', '2019-01-02', NULL, 3),
            (NULL, 'Enfardadora', 'Produz fardos de feno cilindricos.', 'MAQUINA', '97000', '2014-02-01','DESCARTADO', NULL, NULL, '2018-10-03', 4),
            (NULL, 'Enfardadora', 'Produz fardos de fenso cilindricos.', 'MAQUINA', '175000', '2018-07-07','EM_POSSE', NULL, NULL, NULL, 2.5),
            (NULL, 'Enleirador de café', 'Cata grãos espalhados pelo solo.', 'MAQUINA', '93000', '2017-05-29','EM_POSSE', NULL, NULL, NULL, 2),
	    (NULL, 'Trator Massey Ferguson 290', 'Uma maquina versátil e de fácil adaptação para trabalhos diversos.', 'MAQUINA', '30996', '1989-02-23','VENDIDO', NULL, NULL, '2015-09-07', 1),
            (NULL, 'Trator D6k Caterpillar', 'Uma maquina versátil e de fácil adaptação para trabalhos diversos.', 'MAQUINA', '304000', '2015-08-12','EM_POSSE', NULL, NULL, NULL, 4);
-- Tabela Tarefa

-- INSERT INTO `tarefa` (`id`, `nome`, `tipo`, `caminhoImg`, `dataMarcada`, `repeticao`, `producaoPrevista`, `valorGasto`)
-- VALUES (NULL, 'Trabalho Final', 'Impossível', 'imgs/aaaa.png', '2018-11-26', '7', '12000', '12000');

-- Tabela ...