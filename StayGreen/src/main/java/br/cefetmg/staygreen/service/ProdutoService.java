/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.EstoqueProdutos;
import br.cefetmg.staygreen.table.NomeProdutoEnum;
import br.cefetmg.staygreen.table.Produto;
import br.cefetmg.staygreen.table.Transacao;
import br.cefetmg.staygreen.table.UnidadesMedidaProdutoEnum;
import br.cefetmg.staygreen.util.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author Arthur
 * @coop Mei
 */
public class ProdutoService {
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
        ID_COLUMN = "idProduto";
        NOME_PRODUTO_COLUMN = "nomeProduto";
        DESCRICAO_PRODUTO_COLUMN = "descrProduto";
        UNIDADE_MEDIDA_PRODUTO_COLUMN = "unidMedProduto";
        VALOR_PRODUTO_COLUMN = "valorUnitProduto";
        QUANTIDADE_ESTOQUE_PRODUTO_COLUMN = "quantEstoqueProduto";
        PONTO_AVISO_PRODUTO_COLUMN = "pontoAvisoProduto";
        FOTO_MERCADORIA_COLUMN = "fotoMercadoria";

        TABLE_NAME = SQL.getNomeTabela(Produto.class);
    }

    /**
     * Método que busca um produto no BD pelo NOME.
     *
     * @param nome
     * @return Obejeto do tipo Produto com a primeira ocorrencia do NOME passado
     * por parâmetro.
     */
    public static Produto getProdutoPorNome(String nome) {

        ArrayList<Produto> produtos = get("WHERE `nomeProduto`='" + nome + "'");
        if (produtos == null) {
            System.out.println("NENHUM PRODUTO COM NOME " + nome
                    + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return produtos.get(0);

    }

    /**
     * Método que busca um produto no BD pelo ID.
     *
     * @param id
     * @return Obejeto do tipo Produto com a primeira ocorrencia da ID passada
     * por parâmetro.
     */
    public static Produto getProdutoPorId(String id) {
        ArrayList<Produto> produtos = get("WHERE `idProduto`='" + id + "'");

        if (produtos == null) {
            System.out.println("NENHUM PRODUTO COM ID " + id
                    + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }
        return produtos.get(0);
    }

    /**
     * Método que busca vários produtos no BD pelo NOME.
     *
     * @param nome
     * @return ArrayList de objetos do tipo Produto com todas as ocorrencias do
     * NOME passado por parâmetro.
     */
    public static ArrayList<Produto> getProdutosPorNome(String nome) {

        ArrayList<Produto> produtos = get("WHERE `nomeProduto`='" + nome + "'");

        if (produtos == null) {
            System.out.println("NENHUM PRODUTO COM NOME " + nome
                    + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return produtos;
    }

    /**
     * Método que busca vários produtos no BD pelo ID.
     *
     * @param id
     * @return ArrayList de objetos do tipo Produto com todas as ocorrencias da
     * ID passada por parâmetro.
     */
    public static ArrayList<Produto> getProdutosPorId(Long id) {

        ArrayList<Produto> produtos = get("WHERE `idProduto`='" + id + "'");

        if (produtos == null) {
            System.out.println("NENHUM PRODUTO COM ID " + id
                    + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return produtos;
    }

    /**
     * Método que busca vários produtos no BD pela CONDICAO.
     *
     * @param condicao
     * @return ArrayList de objetos do tipo Produto com todas as ocorrencias da
     * CONDICAO passado por parâmetro.
     */
    public static ArrayList<Produto> get(String condicao) {

        ArrayList<Produto> produtos = new ArrayList<>();

        try {
            result = SQL.query("SELECT * FROM `" + TABLE_NAME
                    + "` " + condicao + ";");
            if (result.next()) {
                result.first();
                do {
                    Produto produto = new Produto();
                    produto.setIdProduto(
                            result.getLong(ID_COLUMN));
                    produto.setNomeProduto(NomeProdutoEnum.converter(
                            result.getString(NOME_PRODUTO_COLUMN)));
                    produto.setDescrProduto(
                            result.getString(DESCRICAO_PRODUTO_COLUMN));
                    produto.setUnidMedProduto(UnidadesMedidaProdutoEnum.converter(
                            result.getObject(UNIDADE_MEDIDA_PRODUTO_COLUMN)));
                    produto.setValorUnitProduto(
                            result.getDouble(VALOR_PRODUTO_COLUMN));
                    produto.setQuantEstoqueProduto(
                            result.getInt(QUANTIDADE_ESTOQUE_PRODUTO_COLUMN));
                    produto.setPontoAvisoProduto(
                            result.getInt(PONTO_AVISO_PRODUTO_COLUMN));
                    produto.setFotoMercadoria(
                            result.getString(FOTO_MERCADORIA_COLUMN));
                    produtos.add(produto);
                } while (result.next());
            } else {
                System.out.println("NENHUM PRODUTO COM CONDIÇÃO: "
                        + condicao + " FOI ENCONTRADO NO BANCO DE DADOS");
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex + " at getRowFromId");
            try {
                result.close();
            } catch (SQLException ex1) {
                System.out.println(ex1 + " at getRowFromId");
            }
            return null;
        }

        return produtos;
    }

    /**
     * Método para atualizar um produto no BD.
     *
     * @param produto
     * @return True ou False, dependendo do sucesso com a conexão com BD.
     */
    public static boolean atualizarProduto(Produto produto) {
        Produto p = ProdutoService.getProdutoPorId(String.valueOf(produto.getIdProduto()));
        if (!Objects.equals(p.getQuantEstoqueProduto(), produto.getQuantEstoqueProduto())) {
            EstoqueProdutos estoque = new EstoqueProdutos();
            estoque.setDataProducaoEstoque(Calendar.getInstance());
            estoque.setIdProduto(produto.getIdProduto());
            estoque.setQuantProduzidaEstoque(produto.getQuantEstoqueProduto());
            if (EstoqueService.AdicionarEstoque(estoque)) {
                return SQL.update(produto);
            } else {
                return false;
            }
        } else {
            return SQL.update(produto);
        }
    }

    /**
     * Método para remover um produto no BD.
     *
     * @param id
     * @return True ou False, dependendo do sucesso com a conexão com BD.
     */
    public static boolean deletarProduto(String id) {
        Produto produto = getProdutoPorId(id);
        if (produto.getIdProduto() != null) {
            EstoqueProdutos estoque = new EstoqueProdutos();
            Transacao transacao = new Transacao();
            produto.setDescrProduto("-");
            produto.setFotoMercadoria("-");
            produto.setPontoAvisoProduto(0);
            estoque.setDataProducaoEstoque(Calendar.getInstance());
            estoque.setIdProduto(produto.getIdProduto());
            estoque.setQuantProduzidaEstoque(produto.getQuantEstoqueProduto() * -1);
            transacao.setDataTransacao(Calendar.getInstance());
            transacao.setIdItemTransacao(produto.getIdProduto());
            transacao.setQuantTransacao(produto.getQuantEstoqueProduto() * -1);           
            transacao.setTipoTransacao("PRODUTO");
            transacao.setValorTransacao(produto.getQuantEstoqueProduto() * produto.getValorUnitProduto() * -1);
            produto.setValorUnitProduto(0.0);
            produto.setQuantEstoqueProduto(0);
            if (TransacaoService.AdicionarTransacao(transacao)) {
                if (EstoqueService.AdicionarEstoque(estoque)) {
                    return SQL.update(produto);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            System.out.println("NÃO FOI POSSIVEL DELETAR O PRODUTO,"
                    + "ID INVÁLIDO");
            return false;
        }

    }

    /**
     * Método para removoter todos os produtos do BD.
     *
     * @return True ou False, dependendo do sucesso com a conexão com BD.
     */
    public static boolean deletarProdutoTodos() {
        ArrayList<Produto> produtos = get("");
        if (produtos != null) {
            for (int i = 0; i < produtos.size(); i++) {
                deletarProduto(String.valueOf(produtos.get(i).getIdProduto()));
            }
            return true;
        } else {
            System.out.println("Banco de dados já está vazio");
            return false;
        }

    }

}
