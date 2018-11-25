/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.TipoTransacaoEnum;
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
                    transacao.setTipoTransacao(TipoTransacaoEnum.converter(
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
    
    /**
     * Método para adicionar um transacao no BD.
     * @param transacao
     * @return True ou False, dependendo do sucesso com a conexão com BD.
     */
    public static boolean AdicionarTransacao(Transacao transacao) {
        
        return SQL.insert(transacao);
    }

    /**
     * Método para atualizar um transacao no BD.
     * @param transacao
     * @return True ou False, dependendo do sucesso com a conexão com BD.
     */
    public static boolean atualizarTransacao(Transacao transacao) {
        System.out.println(transacao.toString());
        return SQL.update(transacao);
    }

    /**
     * Método para remover um transacao no BD.
     * @param transacao
     * @return True ou False, dependendo do sucesso com a conexão com BD.
     */
    public static boolean deletarTransacao(Transacao transacao) {
        if (transacao.getIdTransacao() != null) {
            return SQL.delete((int) transacao.getIdTransacao().longValue(),
                    Transacao.class);
        } else {
            System.out.println("NÃO FOI POSSIVEL DELETAR O INSUMO,"
                    + "ID INVÁLIDO");
            return false;
        }

    }

    /**
     * Método para removoter todos os transacaos do BD.
     * @return True ou False, dependendo do sucesso com a conexão com BD.
     */
    public static boolean deletarTransacaoTodos() {
        ArrayList<Transacao> transacaos = get("");
        if (transacaos != null) {
            int i = 0;
            do {
                SQL.delete((int) (transacaos.get(i).getIdTransacao().longValue()),
                        Transacao.class);
                i++;
            } while (i != transacaos.size());
            return true;
        } else {
            System.out.println("Banco de dados já está vazio");
            return false;
        }

    }
    
    


}
