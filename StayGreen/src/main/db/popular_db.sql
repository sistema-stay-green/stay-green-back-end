/* 
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */

-- Exemplo: INSERT INTO tabela (...) VALUES (...), (...), ..., (...);

-- Tabela Patrimonio

INSERT INTO `patrimonio` (`Id`, `Nome`, `Tipo`, `Descrição`, `Status`, `Índice de Depreciação`, `Valor da Compra`, `Valor Atual`, `Data da Compra`, `Data da Saída`, `Dada da Baixa`)
VALUES (NULL, 'Trator', 'Máquina', 'Uma máquina que ajuda na colheita do café.', 'VENDIDO', '15', '5000', '4800', '2018-10-01', '2018-10-03', '2018-10-02');

-- Tabela ...