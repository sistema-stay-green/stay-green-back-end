/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Aluguel;
import br.cefetmg.staygreen.util.Data;
import br.cefetmg.staygreen.util.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Gabriel Cruz
 * @version 03-11-18/10:34
 */
public class AluguelAccessService {
    
    
    //Objetos de manipulação interna.
    private static ResultSet result;
    
    //Constantes que representam os nomes das colunas no DB SQL.
    private static final String ID_ALUGUEL_COLUMN;
    private static final String ID_MAQUINA_COLUMN;
    private static final String VALOR_COLUMN;
    private static final String PERIODO_COLUMN;
    private static final String DATA_COLUMN;
    
    //Inicialização das constantes
    static{
        ID_ALUGUEL_COLUMN = "id";
        ID_MAQUINA_COLUMN = "idMaquinaAluguel";
        VALOR_COLUMN = "valorAluguel";
        PERIODO_COLUMN = "quantAluguel";
        DATA_COLUMN = "dataAluguel";
    }
    
    /**
     * Pesquisa no db 'staygreen' usando o id recebido.
     * @param idAluguel
     * @return um objeto Aluguel que corresponde ao id recebido.
     */
    public static Aluguel getAluguelFromId(String idAluguel) {
        
        ArrayList<Aluguel> alugueis = get("SELECT * FROM aluguel WHERE idAluguel=" + idAluguel);
        
        if (alugueis == null){
            System.out.println("Nenhum aluguel encontrado com o Id: " + idAluguel);
            return null;
        }
        
        return alugueis.get(0);
    }
    
    /**
     * Pesquisa no db 'staygreen' usando o id da maquina a ser aluguada.
     * @param idMaquinaAluguel
     * @return um objeto Aluguel que corresponde à maquina cujo id foi recebido.
     */
    public static Aluguel getAluguelFromIdItem(String idMaquinaAluguel) {
        
        ArrayList<Aluguel> alugueis = get("SELECT * FROM aluguel WHERE idMaquinaAluguel=" + idMaquinaAluguel);
       
        if (alugueis == null){
            System.out.println("Nenhum aluguel encontrado com o maquina de id: " + idMaquinaAluguel);
            return null;
        }
        
        return alugueis.get(0);
    }    
    
    /**
     * Pesquisa no db 'staygreen' todas os alugueis.
     * @return todos os objetos Aluguel em um ArrayList.
     */
    public static ArrayList<Aluguel> getAll() {
        return get("SELECT * FROM " + SQL.getNomeTabela(Aluguel.class));
    }
    
    /**
     * Remove um aluguel do bd 'staygreen' usando o id recebido.
     * @param id Id da aluguel a ser removida.
     * @return true se a operação for bem sucedida e false se não for.
     */
    public static boolean delete(int id) {
        return SQL.delete(id, Aluguel.class);
    }
    
    /**
     * Pesquisa no db 'staygreen' usando a query recebida.
     * @param query query a ser executada
     * @return os objetos Aluguel encontrados utilizando a query recebida.
     */
    public static ArrayList<Aluguel> get(String query){
        
        ArrayList<Aluguel> alugueis = new ArrayList<>();
        
        try{
            
            result = SQL.query(query);
            
            if(result.next()) {
                do{
                    Aluguel aluguel=new Aluguel(result.getLong(ID_ALUGUEL_COLUMN), 
                            result.getLong(ID_MAQUINA_COLUMN), 
                            result.getDouble(VALOR_COLUMN),
                            result.getInt(PERIODO_COLUMN), null);
                    aluguel.setDataInicialAluguel(Data.dateToCalendar(result
                            .getDate(DATA_COLUMN)));
                    
                    
                    alugueis.add(aluguel);
                    
                }while(result.next());
            } else {
                System.out.println("Nada encontrado com a query fornecida.");
            }
        } catch(SQLException ex) {
            System.out.println(ex + " at getRowFromId");
            return null;
        }
        return alugueis;
    }
    
    /**
     * Insere um objeto Aluguel no DB 'staygreen'
     * @param aluguel objeto aluguel
     * @return true se não houver problemas na operação.
     */
    public static boolean insert(Aluguel aluguel){
        return SQL.insert(aluguel);
    }
    
    /**
     * Insere vários objetos Aluguel no DB 'staygreen'
     * @param alugueis
     */
    public static void insertAll(ArrayList<Aluguel> alugueis){
        alugueis.forEach((aluguel) -> {
            insert(aluguel);
        });
    }
    
    /**
     * Atualiza a aluguel no DB correspondente ao objeto Aluguel recebido
     * @param aluguel
     * @return true se não houver problemas na operação.
     */
    public static boolean update(Aluguel aluguel){
        return SQL.update(aluguel);
    }
    
    /**
     * Atualiza os alugueis no DB correspondentes aos objetos Aluguel recebidos
     * @param alugueis
     */
    public static void updateAll(ArrayList<Aluguel> alugueis){        
        alugueis.forEach((aluguel) -> {
            SQL.update(aluguel);
        });
    }
}