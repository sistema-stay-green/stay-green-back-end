/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Transacao;
import br.cefetmg.staygreen.util.Data;
import br.cefetmg.staygreen.util.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Gabriel Cruz
 * @version 02-11-18/17:57
 */
public class TransacaoAccessService {
    
    
    //Objetos de manipulação interna.
    private static ResultSet result;
    
    //Constantes que representam os nomes das colunas no DB SQL.
    private static final String ID_COLUMN;
    private static final String ID_ITEM_TRANSACAO_COLUMN;
    private static final String VALOR_COLUMN;
    private static final String QUANTIDADE_COLUMN;
    private static final String TIPO_COLUMN;
    private static final String DATA_COLUMN;
    
    //Inicialização das constantes
    static{
        ID_COLUMN = "id";
        ID_ITEM_TRANSACAO_COLUMN = "idItemTransacao";
        VALOR_COLUMN = "valorTransacao";
        QUANTIDADE_COLUMN = "quantTransacao";
        TIPO_COLUMN = "tipoTransacao";
        DATA_COLUMN = "dataTransacao";
    }
    
    /**
     * Pesquisa no db 'staygreen' usando o id recebido.
     * @param id
     * @return um objeto Transação que corresponde ao id recebido.
     */
    public static Transacao getTransacaoFromId(String id) {
        
        ArrayList<Transacao> transacoes = get("SELECT * FROM transacao WHERE id=" + id);
        
        if (transacoes == null){
            System.out.println("Nenhuma transacao encontrada com o Id: " + id);
            return null;
        }
        
        return transacoes.get(0);
    }
    
    /**
     * Pesquisa no db 'staygreen' usando o id do item a ser transacionado.
     * @param idItemTransacao
     * @return um objeto Transação que corresponde ao item cujo id foi recebido.
     */
    public static Transacao getTransacaoFromIdItem(String idItemTransacao) {
        
        ArrayList<Transacao> transacoes = get("SELECT * FROM transacao WHERE idItemTransacao=" + idItemTransacao);
       
        if (transacoes == null){
            System.out.println("Nenhuma transacao encontrada com o item de id: " + idItemTransacao);
            return null;
        }
        
        return transacoes.get(0);
    }    
    
    /**
     * Pesquisa no db 'staygreen' todas as transações.
     * @return todos os objetos Transação em um ArrayList.
     */
    public static ArrayList<Transacao> getAll() {
        return get("SELECT * FROM " + SQL.getNomeTabela(Transacao.class));
    }
    
    /**
     * Remove uma transacao do bd 'staygreen' usando o id recebido.
     * @param id Id da transação a ser removida.
     * @return true se a operação for bem sucedida e false se não for.
     */
    public static boolean delete(int id) {
        return SQL.delete(id, Transacao.class);
    }
    
    /**
     * Pesquisa no db 'staygreen' usando a query recebida.
     * @param query query a ser executada
     * @return os objetos Transação encontrados utilizando a query recebida.
     */
    public static ArrayList<Transacao> get(String query){
        
        ArrayList<Transacao> transacoes = new ArrayList<>();
        
        try{
            
            result = SQL.query(query);
            
            if(result.next()) {
                do{
                    Transacao transacao=new Transacao(result.getLong(ID_COLUMN), 
                            result.getLong(ID_ITEM_TRANSACAO_COLUMN), 
                            result.getDouble(VALOR_COLUMN),
                            result.getInt(QUANTIDADE_COLUMN), null, null);
                    transacao.setTipoTransacao(result.getString(TIPO_COLUMN));
                    transacao.setDataTransacao(Data.dateToCalendar(result
                            .getDate(DATA_COLUMN)));
                    
                    
                    transacoes.add(transacao);
                    
                }while(result.next());
            } else {
                System.out.println("Nada encontrado com a query fornecida.");
            }
        } catch(SQLException ex) {
            System.out.println(ex + " at getRowFromId");
            return null;
        }
        return transacoes;
    }
    
    /**
     * Insere um objeto Transação no DB 'staygreen'
     * @param transacao objeto transacao
     * @return true se não houver problemas na operação.
     */
    public static boolean insert(Transacao transacao){
            return SQL.insert(transacao);
    }
    
    /**
     * Insere vários objetos Transação no DB 'staygreen'
     * @param transacoes
     */
    public static void insertAll(ArrayList<Transacao> transacoes){
        transacoes.forEach((transacao) -> {
            insert(transacao);
        });
    }
    
    /**
     * Atualiza a transação no DB correspondente ao objeto Transação recebido
     * @param transacao
     * @return true se não houver problemas na operação.
     */
    public static boolean update(Transacao transacao){
        return SQL.update(transacao);
    }
    
    /**
     * Atualiza as transações no DB correspondentes aos objetos Transação recebidos
     * @param transacoes
     */
    public static void updateAll(ArrayList<Transacao> transacoes){        
        transacoes.forEach((transacao) -> {
            SQL.update(transacao);
        });
    }
}