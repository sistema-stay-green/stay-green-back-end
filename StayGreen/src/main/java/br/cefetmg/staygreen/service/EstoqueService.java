/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Estoque;
import br.cefetmg.staygreen.util.Data;
import br.cefetmg.staygreen.util.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arthur
 * @coop Mei
 */
public class EstoqueService {
    // Objetos de manipulação interna

    private static ResultSet result;

    // Constantes que representam os nomes das colunas na tabela do DB SQL
    private static final String ID_ESTOQUE_COLUMN;
    private static final String ID_PRODUTO_COLUMN;
    private static final String QUANTIDADE_PRODUZIDA_ESTOQUE_COLUMN;
    private static final String DATA_PRODUCAO_ESTOQUE_COLUMN;

    private static final String TABLE_NAME;

    // inicialização das constantes internas
    static {
        ID_ESTOQUE_COLUMN = "idEstoque";
        ID_PRODUTO_COLUMN = "idProduto";
        QUANTIDADE_PRODUZIDA_ESTOQUE_COLUMN = "quantProduzidaEstoque";
        DATA_PRODUCAO_ESTOQUE_COLUMN = "dataProducaoEstoque";

        TABLE_NAME = SQL.getNomeTabela(Estoque.class);
    }

    /**
     * Método que busca um estoque no BD pelo ID.
     * @param id
     * @return Obejeto do tipo Estoque com a primeira ocorrencia
     * da ID passada por parâmetro.
     */
    public static Estoque getEstoquePorId(String id) {

        ArrayList<Estoque> estoques = get("WHERE `idEstoque`='" + id + "'");

        if (estoques == null) {
            System.out.println("NENHUM ESTOQUE COM ID " + id
                    + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return estoques.get(0);
    }

    /**
     * Método que busca vários estoques no BD pelo ID.
     * @param id
     * @return ArrayList de objetos do tipo Estoque com todas as ocorrencias
     * da ID passada por parâmetro.
     */
    public static ArrayList<Estoque> getEstoquesPorId(Long id) {

        ArrayList<Estoque> estoques = get("WHERE `idEstoque`='" + id + "'");

        if (estoques == null) {
            System.out.println("NENHUM ESTOQUE COM ID " + id
                    + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return estoques;
    }

    /**
     * Método que busca vários estoques no BD pela CONDICAO.
     * @param condicao
     * @return ArrayList de objetos do tipo Estoque com todas as ocorrencias
     * da CONDICAO passado por parâmetro.
     */
    public static ArrayList<Estoque> get(String condicao) {

        ArrayList<Estoque> estoques = new ArrayList<>();

        try {
            result = SQL.query("SELECT * FROM `" + TABLE_NAME
                    + "` " + condicao);
            if (result.next()) {
                do {
                    Estoque estoque = new Estoque();
                    estoque.setIdEstoque(Long.parseLong(
                            result.getString(ID_ESTOQUE_COLUMN)));
                    estoque.setIdProduto(Long.parseLong(
                            result.getString(ID_PRODUTO_COLUMN)));
                    estoque.setQuantProduzidaEstoque(Integer.parseInt(
                            result.getString(QUANTIDADE_PRODUZIDA_ESTOQUE_COLUMN)));
                    estoque.setDataProducaoEstoque(Data.getCalendarFromDateString(
                            result.getString(DATA_PRODUCAO_ESTOQUE_COLUMN)));
                    estoques.add(estoque);

                } while (result.next());
            } else {
                System.out.println("NENHUM ESTOQUE COM CONDIÇÃO: "
                        + condicao + " FOI ENCONTRADO NO BANCO DE DADOS");
                return null;
            }

        } catch (SQLException ex) {
            System.out.println(ex + " at getRowFromId");
            return null;
        } catch (ParseException ex) {
            Logger.getLogger(EstoqueService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return estoques;
    }

    /**
     * Método para adicionar um estoque no BD.
     * @param estoque
     * @return True ou False, dependendo do sucesso com a conexão com BD.
     */
    public static boolean AdicionarEstoque(Estoque estoque) {
        return SQL.insert(estoque);
    }

    /**
     * Método para atualizar um estoque no BD.
     * @param estoque
     * @return True ou False, dependendo do sucesso com a conexão com BD.
     */
    public static boolean atualizarEstoque(Estoque estoque) {
        return SQL.update(estoque);
    }

    /**
     * Método para remover um estoque no BD.
     * @param estoque
     * @return True ou False, dependendo do sucesso com a conexão com BD.
     */
    public static boolean deletarEstoque(Estoque estoque) {
        if (estoque.getIdEstoque() != null) {
            return SQL.delete((int) estoque.getIdEstoque().longValue(),
                    Estoque.class);
        } else {
            System.out.println("NÃO FOI POSSIVEL DELETAR O ESTOQUE,"
                    + "ID INVÁLIDO");
            return false;
        }

    }

    /**
     * Método para removoter todos os estoques do BD.
     * @return True ou False, dependendo do sucesso com a conexão com BD.
     */
    public static boolean deletarEstoqueTodos() {
        ArrayList<Estoque> estoques = get("");
        if (estoques != null) {
            int i = 0;
            do {
                SQL.delete((int) (estoques.get(i).getIdEstoque().longValue()),
                        Estoque.class);
                i++;
            } while (i != estoques.size());
            return true;
        } else {
            System.out.println("Banco de dados já está vazio");
            return false;
        }

    }

}
