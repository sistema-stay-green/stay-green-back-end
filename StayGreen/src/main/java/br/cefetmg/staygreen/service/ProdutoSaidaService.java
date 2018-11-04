/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Produto;
import br.cefetmg.staygreen.table.UnidadesMedidaProduto;
import br.cefetmg.staygreen.util.JSON;
import br.cefetmg.staygreen.util.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author arthu
 */
public class ProdutoSaidaService {
    // Objetos de manipulação interna

    private static ResultSet result;

    // Constantes que representam os nomes das colunas na tabela do DB SQL
    private static final String ID_COLUMN;
    private static final String NOME_PRODUTO_COLUMN;
    private static final String DESCRICAO_PRODUTO_COLUMN;
    private static final String UNIDADE_MEDIDA_PRODUTO_COLUMN;
    private static final String VALOR_PRODUTO_COLUMN;
    private static final String QUANTIDADE_ESTOQUE_PRODUTO_COLUMN;
    private static final String PONTO_AVISO_PRODUTO_COLUMN;
    private static final String FOTO_MERCADORIA_COLUMN;

    private static final String TABLE_NAME;

    // inicialização das constantes internas
    static {
        ID_COLUMN = "id";
        NOME_PRODUTO_COLUMN = "nomeProduto";
        DESCRICAO_PRODUTO_COLUMN = "descProduto";
        UNIDADE_MEDIDA_PRODUTO_COLUMN = "unidMedProduto";
        VALOR_PRODUTO_COLUMN = "valorProduto";
        QUANTIDADE_ESTOQUE_PRODUTO_COLUMN = "quantEstoqueProduto";
        PONTO_AVISO_PRODUTO_COLUMN = "pontoAvisoProduto";
        FOTO_MERCADORIA_COLUMN = "fotoMercadoria";

        TABLE_NAME = SQL.getNomeTabela(Produto.class);
    }

    public static Produto getProdutoPorNome(String nome) {

        ArrayList<Produto> produtos = get("WHERE id=" + nome);

        if (produtos == null) {
            System.out.println("NENHUM PRODUTO COM NOME " + nome + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return produtos.get(0);

    }

 
    public static Produto getProdutoPorId(String id) {

        ArrayList<Produto> produtos = get("WHERE id=" + id);

        if (produtos == null) {
            System.out.println("NENHUM PRODUTO COM ID " + id + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return produtos.get(0);
    }


    public static ArrayList<Produto> getProdutosPorNome(String nome) {

        ArrayList<Produto> produtos = get("WHERE nome='" + nome + "'");

        if (produtos == null) {
            System.out.println("NENHUM PRODUTO COM NOME " + nome + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return produtos;
    }

 
    public static ArrayList<Produto> getProdutosPorId(Long id) {

        ArrayList<Produto> produtos = get("WHERE id=" + id);

        if (produtos == null) {
            System.out.println("NENHUM PRODUTO COM ID " + id + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return produtos;
    }


    public static ArrayList<Produto> get(String queryCondition) {

        ArrayList<Produto> produtos = new ArrayList<>();

        try {

            result = SQL.query("SELECT * FROM " + TABLE_NAME + " " + queryCondition);

            if (result.next()) {
                do {
                    Produto produto = new Produto();
                    produto.setId(Long.parseLong(result.getString(ID_COLUMN)));
                    produto.setNomeProduto(result.getString(NOME_PRODUTO_COLUMN));
                    produto.setDescrProduto(result.getString(DESCRICAO_PRODUTO_COLUMN));
                    produto.setUnidMedProduto(UnidadesMedidaProduto.converter(result.getString(UNIDADE_MEDIDA_PRODUTO_COLUMN)));
                    produto.setValorUnitProduto(Double.parseDouble(result.getString(VALOR_PRODUTO_COLUMN)));
                    produto.setQuantEstoqueProduto(Integer.parseInt(result.getString(QUANTIDADE_ESTOQUE_PRODUTO_COLUMN)));
                    produto.setPontoAvisoProduto(Integer.parseInt(result.getString(PONTO_AVISO_PRODUTO_COLUMN)));
                    produto.setFotoMercadoria(result.getString(FOTO_MERCADORIA_COLUMN));
                    produtos.add(produto);

                } while (result.next());
            } else {
                System.out.println("NENHUM PRODUTO COM CONDIÇÃO: " + queryCondition + " FOI ENCONTRADO NO BANCO DE DADOS");
                return null;
            }

        } catch (SQLException ex) {
            System.out.println(ex + " at getRowFromId");
            return null;
        }

        return produtos;
    }

}
