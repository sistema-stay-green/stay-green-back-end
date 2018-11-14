/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Tarefa;
import br.cefetmg.staygreen.table.TarefaTipoEnum;
import br.cefetmg.staygreen.util.Data;
import br.cefetmg.staygreen.util.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Gabriel Brandão
 * @version 1.3
 */
public class TarefaAccessService {
    
    
    //Objetos de manipulação interna.
    private static ResultSet result;
    
    //Constantes que representam os nomes das colunas no DB SQL.
    private static final String ID_COLUMN;
    private static final String NOME_COLUMN;
    private static final String DESCR_COLUMN;
    private static final String TIPO_COLUMN;
    private static final String DATA_INICIAL_COLUMN;
    private static final String PERIOD_REPET_COLUMN;
    private static final String GASTO_COLUMN;
    private static final String QUANT_PRODUZ_COLUMN;
    private static final String INSUMOS_COLUMN;
    private static final String QUANT_INSUMOS_COLUMN;
    
    //Inicialização das constantes
    static{
        ID_COLUMN = "idTarefa";
        NOME_COLUMN = "nomeTarefa";
        DESCR_COLUMN = "descrTarefa";
        TIPO_COLUMN = "tipoTarefa";
        DATA_INICIAL_COLUMN = "dataInicialTarefa";
        PERIOD_REPET_COLUMN = "periodRepetTarefa";
        GASTO_COLUMN = "gastoTarefa";
        QUANT_PRODUZ_COLUMN = "quantProduzTarefa";
        INSUMOS_COLUMN = "insumosTarefa";
        QUANT_INSUMOS_COLUMN = "quantInsumosTarefa";
    }
    
    /**
     * Pesquisa no db 'staygreen' usando o id recebido.
     * @param idTarefa
     * @return Retorna um objeto Tarefa que corresponde ao id recebido.
     */
    public static Tarefa getTarefaFromId(String idTarefa) {
        
        ArrayList<Tarefa> tarefas = get("SELECT * FROM tarefa WHERE idTarefa=" 
                + idTarefa);
        
        if(tarefas == null)
            System.out.println("AVISO! Nenhuma tarefa encontrada com o id: " 
                    + idTarefa);
        
        return tarefas.get(0);
    }
    
    /**
     * Pesquisa no db 'staygreen' usando o nome recebido.
     * @param nomeTarefa
     * @return Retorna um ArrayList de objetos Tarefa que correspondem ao nome
     * recebido.
     */
    public static ArrayList<Tarefa> getTarefasFromNome(String nomeTarefa) {
        
        ArrayList<Tarefa> tarefas = get("SELECT * FROM tarefa WHERE nomeTarefa=" 
                + nomeTarefa);
        
        if(tarefas == null)
            System.out.println("AVISO! Nenhuma tarefa encontrada com o nome: " 
                    + nomeTarefa);
        
        return tarefas;
    }
    
    /**
     * Pesquisa no db 'staygreen' usando o tipo recebido.
     * @param tipoTarefa
     * @return Retorna um ArrayList de objetos Tarefa que correspondem ao tipo
     * recebido.
     */
    public static ArrayList<Tarefa> getTarefasFromTipo(TarefaTipoEnum tipoTarefa) {
        
        ArrayList<Tarefa> tarefas = get("SELECT * FROM tarefa WHERE tipoTarefa=" 
                + tipoTarefa);
        
        if(tarefas == null)
            System.out.println("AVISO! Nenhuma tarefa encontrada com o tipo: " 
                    + tipoTarefa);
        
        return tarefas;
    }
    
    /**
     * Pesquisa no db 'staygreen' todas as tarefas.
     * @return Retorna todos os objetos Tarefa em um ArrayList.
     */
    public static ArrayList<Tarefa> getAll() {
        return get("SELECT * FROM " + SQL.getNomeTabela(Tarefa.class));
    }
    
    /**
     * Remove uma tarefa do bd 'staygreen' usando o id recebido.
     * @param idTarefa Id da tarefa a ser removida.
     * @return Retorna true se a operação for bem sucedida e false se não for.
     */
    public static boolean remove(int idTarefa) {
        return SQL.delete(idTarefa, Tarefa.class);
    }
    
    /**
     * Pesquisa no db 'staygreen' usando a query recebida.
     * @param query query a ser executada
     * @return Retorna os objetos Tarefa encontrados utilizando a query 
     * recebida.
     */
    public static ArrayList<Tarefa> get(String query){
        
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        
        try{
            
            result = SQL.query(query);
            
            if(result.next()) {
                do{
                    
                    Tarefa tarefa = new Tarefa(result.getLong(ID_COLUMN), 
                            result.getString(NOME_COLUMN));
                    tarefa.setDescrTarefa(result.getString(DESCR_COLUMN));
                    tarefa.setTipoTarefa(result.getString(TIPO_COLUMN));
                    tarefa.setDataInicialTarefa(Data.dateToCalendar(result
                            .getDate(DATA_INICIAL_COLUMN)));
                    tarefa.setPeriodRepetTarefa(result
                            .getInt(PERIOD_REPET_COLUMN));
                    tarefa.setGastoTarefa(result.getDouble(GASTO_COLUMN));
                    tarefa.setQuantProduzTarefa(result
                            .getInt(QUANT_PRODUZ_COLUMN));
                    tarefa.setInsumosTarefa(result.getString(INSUMOS_COLUMN));
                    tarefa.setQuantInsumosTarefa(result
                            .getInt(QUANT_INSUMOS_COLUMN));
                    
                    tarefas.add(tarefa);
                    
                }while(result.next());
            } else {
                System.out.println("Nada encontrado com a query fornecida.");
            }
        } catch(SQLException ex) {
            System.out.println(ex + " at getRowFromId");
            return null;
        }
        return tarefas;
    }
    
    /**
     * Insere um objeto Tarefa no DB 'staygreen'
     * @param tarefa objeto tarefa
     * @return Retorna true se não houver problemas na operação.
     */
    public static boolean insert(Tarefa tarefa){
            return SQL.insert(tarefa);
    }
    
    /**
     * Insere vários objetos Tarefa no DB 'staygreen'
     * @param tarefas
     */
    public static void insertAll(ArrayList<Tarefa> tarefas){
        
        for (Tarefa tarefa : tarefas) {
            insert(tarefa);
        }
    }
    
    /**
     * Atualiza a tarefa no DB correspondente ao objeto Tarefa recebido
     * @param tarefa
     * @return Retorna true se não houver problemas na operação.
     */
    public static boolean update(Tarefa tarefa){
            return SQL.update(tarefa);
    }
    
    /**
     * Atualiza as tarefas no DB correspondentes aos objetos Tarefa recebidos
     * @param tarefas
     */
    public static void updateAll(ArrayList<Tarefa> tarefas){
        
        for (Tarefa tarefa : tarefas) {
            SQL.update(tarefa);
        }
    }
}
