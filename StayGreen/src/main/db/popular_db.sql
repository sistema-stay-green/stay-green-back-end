/* 
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */

-- Exemplo: INSERT INTO tabela (...) VALUES (...), (...), ..., (...);

USE `staygreen`;

-- Tabela Patrimonio

INSERT INTO `patrimonio` (`id`, `nome`, `tipo`, `descricao`, `status`, `indiceDepreciacao`, `valorCompra`, `valorAtual`, `dataCompra`, `dataSaida`, `dataBaixa`)
VALUES (NULL, 'Trator', 'Máquina', 'Uma máquina que ajuda na colheita do café.', 'VENDIDO', '15', '5000', '4800', '2018-10-01', '2018-10-03', '2018-10-02');

-- Tabela Vendas

INSERT INTO `vendas` (`id`, `produto`, `entradaSaida`, `dataTransacao`, `valor`) VALUES
(NULL, 'café', 10, '2018-09-11', 21.15),
(NULL, 'leite', 12, '2018-10-16', 21.14);

-- Tabela Clientes

INSERT INTO `clientes` (`id`, `nome`, `modoPagamento`, `enderecoEntrega`, `regiao`, `valorTransporte`, `dataAgendadaEntrega`) 
VALUES (1, 'Daemon Blackfyre', 'Ouro', 'Baixada das Pulgas, 255 - Porto Real', 'Sudeste', 200, '2018-10-29');

-- Tabela ...