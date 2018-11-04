/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Insumo;
import br.cefetmg.staygreen.util.JSON;
import br.cefetmg.staygreen.util.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author arthu
 */
public class InsumoSaidaService {
    // Objetos de manipulação interna

    private static ResultSet result;

    // Constantes que representam os nomes das colunas na tabela do DB SQL.
    private static final String ID_COLUMN;
    private static final String NOME_INSUMO_COLUMN;
    private static final String FINALIDADE_INSUMO_COLUMN;
    private static final String VALOR_UNITARIO_INSUMO_COLUMN;
    private static final String QUANTIDADE_ESTOQUE_INSUMO_COLUMN;
    private static final String PONTO_AVISO_INSUMO_COLUMN;

    private static final String TABLE_NAME;

    // inicialização das constantes internas.
    static {
        ID_COLUMN = "id";
        NOME_INSUMO_COLUMN = "nomeInsumo";
        FINALIDADE_INSUMO_COLUMN = "finalidadeInsumo";
        VALOR_UNITARIO_INSUMO_COLUMN = "valorUnitInsumo";
        QUANTIDADE_ESTOQUE_INSUMO_COLUMN = "quantEstoqueInsumo";
        PONTO_AVISO_INSUMO_COLUMN = "pontoAvisoInsumo";

        TABLE_NAME = SQL.getNomeTabela(Insumo.class);
    }

    public static Insumo getInsumoPorNome(String nome) {

        ArrayList<Insumo> insumos = get("WHERE id=" + nome);

        if (insumos == null) {
            System.out.println("NENHUM INSUMO COM NOME " + nome + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return insumos.get(0);

    }

    /**
     * Pesquisa na DB 'staygreen' usando o Id recebido.
     *
     * @param id
     * @return Retorna um objeto Insumo que corresponde ao Id recebido.
     */
    public static Insumo getInsumoPorId(String id) {

        ArrayList<Insumo> insumos = get("WHERE id=" + id);

        if (insumos == null) {
            System.out.println("NENHUM INSUMO COM ID " + id + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return insumos.get(0);
    }

    /**
     * Pesquisa na DB 'staygreen' usando o Nome recebido.
     *
     * @param nome
     * @return Retorna objetos Insumo que correspondam ao Nome recebido.
     */
    public static ArrayList<Insumo> getInsumosPorNome(String nome) {

        ArrayList<Insumo> insumos = get("WHERE nome='" + nome + "'");

        if (insumos == null) {
            System.out.println("NENHUM INSUMO COM NOME " + nome + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return insumos;
    }

    /**
     * Pesquisa na DB 'staygreen' usando o Nome recebido.
     *
     * @param id
     * @return Retorna objetos Insumo que correspondam ao Nome recebido.
     */
    public static ArrayList<Insumo> getInsumosPorId(Long id) {

        ArrayList<Insumo> insumos = get("WHERE id=" + id);

        if (insumos == null) {
            System.out.println("NENHUM INSUMO COM ID " + id + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return insumos;
    }

    /**
     * Pesquisa na DB 'staygreen' usando a queryCondition recebida.
     *
     * @param queryCondition
     * @return Retorna objetos Insumo resultantes da queryCondition de pesquisa
     * recebida.
     */
    public static ArrayList<Insumo> get(String queryCondition) {

        ArrayList<Insumo> insumos = new ArrayList<>();

        try {

            result = SQL.query("SELECT * FROM " + TABLE_NAME + " " + queryCondition);

            if (result.next()) {
                do {
                    Insumo insumo = new Insumo();
                    insumo.setId(Long.parseLong(result.getString(ID_COLUMN)));
                    insumo.setNomeInsumo(result.getString(NOME_INSUMO_COLUMN));
                    insumo.setFinalidadeInsumo(result.getString(FINALIDADE_INSUMO_COLUMN));
                    insumo.setValorUnitInsumo(Double.parseDouble(result.getString(VALOR_UNITARIO_INSUMO_COLUMN)));
                    insumo.setQuantEstoqueInsumo(Integer.parseInt(result.getString(QUANTIDADE_ESTOQUE_INSUMO_COLUMN)));
                    insumo.setPontoAvisoInsumo(Integer.parseInt(result.getString(PONTO_AVISO_INSUMO_COLUMN)));
                    insumos.add(insumo);
                    
                } while (result.next());
            } else {
                System.out.println("NENHUM INSUMO COM CONDIÇÃO: " + queryCondition + " FOI ENCONTRADO NO BANCO DE DADOS");
                return null;
            }

        } catch (SQLException ex) {
            System.out.println(ex + " at getRowFromId");
            return null;
        }

        return insumos;
    }

}
