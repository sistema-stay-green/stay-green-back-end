/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.TipoTransacao;
import br.cefetmg.staygreen.table.Transacao;
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
public class TransacaoService {
    // Objetos de manipulação interna

    private static ResultSet result;

    // Constantes que representam os nomes das colunas na tabela do DB SQL
    private static final String ID_TRANSACAO_COLUMN;
    private static final String VALOR_TRANSACAO_COLUMN;
    private static final String QUANT_TRANSACAO_COLUMN;
    private static final String DATA_TRANSACAO_COLUMN;
    private static final String ID_ITEM_TRANSACAO_COLUMN;
    private static final String TIPO_TRANSACAO_COLUMN;

    private static final String TABLE_NAME;

    // inicialização das constantes internas
    static {
        ID_TRANSACAO_COLUMN = "idTransacao";
        VALOR_TRANSACAO_COLUMN = "valorTransacao";
        QUANT_TRANSACAO_COLUMN = "quantTransacao";
        DATA_TRANSACAO_COLUMN = "dataTransacao";
        ID_ITEM_TRANSACAO_COLUMN = "idItemTransacao";
        TIPO_TRANSACAO_COLUMN = "tipoTransacao";
        
        TABLE_NAME = SQL.getNomeTabela(Transacao.class);
    }

     /**
     * Método que busca várias transacões no BD pela CONDICAO.
     * @param condicao
     * @return ArrayList de objetos do tipo Transacao com todas as ocorrencias
     * da CONDICAO passado por parâmetro.
     */
    public static ArrayList<Transacao> get(String condicao) {

        ArrayList<Transacao> transacaos = new ArrayList<>();

        try {
            result = SQL.query("SELECT * FROM `" + TABLE_NAME
                    + "` " + condicao);

            if (result.next()) {
                do {
                    Transacao transacao = new Transacao();
                    transacao.setDataTransacao(
                            Data.getCalendarFromDateString(
                                    result.getString(DATA_TRANSACAO_COLUMN)));
                    transacao.setIdItemTransacao(
                            Long.parseLong(
                                    result.getString(ID_ITEM_TRANSACAO_COLUMN)));
                    transacao.setIdTransacao(
                            Long.parseLong(
                                    result.getString(ID_TRANSACAO_COLUMN)));
                    transacao.setQuantTransacao(
                            Integer.parseInt(
                                    result.getString(QUANT_TRANSACAO_COLUMN)));
                    transacao.setTipoTransacao(
                            TipoTransacao.converter(
                                    result.getString(TIPO_TRANSACAO_COLUMN)));
                    transacao.setValorTransacao(
                            Double.parseDouble(
                                    result.getString(VALOR_TRANSACAO_COLUMN)));
                    
                    transacaos.add(transacao);
                } while (result.next());
            } else {
                System.out.println("NENHUM TRANSACAO COM CONDIÇÃO: "
                        + condicao + " FOI ENCONTRADO NO BANCO DE DADOS");
                return null;
            }

        } catch (SQLException ex) {
            System.out.println(ex + " at getRowFromId");
            return null;
        } catch (ParseException ex) {
            Logger.getLogger(TransacaoService.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        return transacaos;
    }

}
