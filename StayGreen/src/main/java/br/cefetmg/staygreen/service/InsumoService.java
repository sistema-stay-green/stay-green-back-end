/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Insumo;
import br.cefetmg.staygreen.util.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Arthur
 * @coop Mei
 */
public class InsumoService {
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
        ID_COLUMN = "idInsumo";
        NOME_INSUMO_COLUMN = "nomeInsumo";
        FINALIDADE_INSUMO_COLUMN = "finalidadeInsumo";
        VALOR_UNITARIO_INSUMO_COLUMN = "valorUnitInsumo";
        QUANTIDADE_ESTOQUE_INSUMO_COLUMN = "quantEstoqueInsumo";
        PONTO_AVISO_INSUMO_COLUMN = "pontoAvisoInsumo";

        TABLE_NAME = SQL.getNomeTabela(Insumo.class);
    }

    public static Insumo getInsumoPorNome(String nome) {

        ArrayList<Insumo> insumos = get("WHERE `nomeInsumo`='" + nome + "'");
        if (insumos == null) {
            System.out.println("NENHUM INSUMO COM NOME " + nome
                    + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return insumos.get(0);

    }

    public static Insumo getInsumoPorId(String id) {

        ArrayList<Insumo> insumos = get("WHERE `idInsumo`='" + id + "'");

        if (insumos == null) {
            System.out.println("NENHUM INSUMO COM ID " + id
                    + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return insumos.get(0);
    }

    public static ArrayList<Insumo> getInsumosPorNome(String nome) {

        ArrayList<Insumo> insumos = get("WHERE `nomeInsumo`='" + nome + "'");

        if (insumos == null) {
            System.out.println("NENHUM INSUMO COM NOME " + nome
                    + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return insumos;
    }

    public static ArrayList<Insumo> getInsumosPorId(Long id) {

        ArrayList<Insumo> insumos = get("WHERE `idInsumo`='" + id + "'");

        if (insumos == null) {
            System.out.println("NENHUM INSUMO COM ID " + id
                    + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return insumos;
    }

    public static ArrayList<Insumo> get(String queryCondition) {

        ArrayList<Insumo> insumos = new ArrayList<>();

        try {

            result = SQL.query("SELECT * FROM " + TABLE_NAME
                    + " " + queryCondition);

            if (result.next()) {
                do {
                    Insumo insumo = new Insumo();
                    insumo.setIdInsumo(Long.parseLong(
                            result.getString(ID_COLUMN)));
                    insumo.setNomeInsumo(
                            result.getString(NOME_INSUMO_COLUMN));
                    insumo.setFinalidadeInsumo(
                            result.getString(FINALIDADE_INSUMO_COLUMN));
                    insumo.setValorUnitInsumo(Double.parseDouble(
                            result.getString(VALOR_UNITARIO_INSUMO_COLUMN)));
                    insumo.setQuantEstoqueInsumo(Integer.parseInt(
                            result.getString(QUANTIDADE_ESTOQUE_INSUMO_COLUMN)));
                    insumo.setPontoAvisoInsumo(Integer.parseInt(
                            result.getString(PONTO_AVISO_INSUMO_COLUMN)));
                    insumos.add(insumo);

                } while (result.next());
            } else {
                System.out.println("NENHUM INSUMO COM CONDIÇÃO: "
                        + queryCondition + " FOI ENCONTRADO NO BANCO DE DADOS");
                return null;
            }

        } catch (SQLException ex) {
            System.out.println(ex + " at getRowFromId");
            return null;
        }

        return insumos;
    }

    public static void AdicionarInsumo(Insumo insumo) {
        SQL.insert(insumo);
        System.out.println(insumo.toString());
    }

    public static void AdicionarInsumos(ArrayList<Insumo> insumos) {

        for (Insumo insumo : insumos) {
            SQL.insert(insumo);
        }
    }

    public static void atualizarInsumo(Insumo insumo) {

        SQL.update(insumo);
    }

    public static void atualizarInsumos(ArrayList<Insumo> insumos) {

        for (Insumo insumo : insumos) {
            SQL.update(insumo);
        }
    }

    public static void deletarInsumo(Insumo insumo) {

        if (insumo.getIdInsumo() != null) {
            SQL.delete((int) insumo.getIdInsumo().longValue(), Insumo.class);
        } else {
            System.out.println("NÃO FOI POSSIVEL DELETAR O INSUMO");
        }
    }
     public static void deletarInsumoTodos() {
        ArrayList<Insumo> insumos = get("");
        int i = 0;
        do{
            SQL.delete((int) (insumos.get(i).getIdInsumo().longValue()), Insumo.class);
            i++;
        }while(i != insumos.size());
    }
}
