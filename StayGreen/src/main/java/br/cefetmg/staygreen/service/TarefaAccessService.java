/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Tarefa;
import br.cefetmg.staygreen.util.Data;
import static br.cefetmg.staygreen.util.Data.dateToCalendar;
import br.cefetmg.staygreen.util.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Gabriel Brandão
 * @version 1.2
 */
public class TarefaAccessService {
    
    
    //Objetos de manipulação interna.
    private static ResultSet result;
    
    //Constantes que representam os nomes das colunas no DB SQL.
    private static final String ID_COLUMN;
    private static final String NOME_COLUMN;
    private static final String TIPO_COLUMN;
    private static final String CAMINHO_IMG_COLUMN;
    private static final String DATA_MARCADA_COLUMN;
    private static final String REPETICAO_COLUMN;
    private static final String PRODUCAO_PREVISTA_COLUMN;
    private static final String VALOR_GASTO_COLUMN;
    
    //Inicialização das constantes
    static{
        ID_COLUMN = "id";
        NOME_COLUMN = "nome";
        TIPO_COLUMN = "tipo";
        CAMINHO_IMG_COLUMN = "caminhoImg";
        DATA_MARCADA_COLUMN = "dataMarcada";
        REPETICAO_COLUMN = "repeticao";
        PRODUCAO_PREVISTA_COLUMN = "producaoPrevista";
        VALOR_GASTO_COLUMN = "valorGasto";
    }
    
    /**
     * Pesquisa no db 'staygreen' usando o id recebido.
     * @param id
     * @return Retorna um objeto Tarefa que corresponde ao id recebido.
     */
    public static Tarefa getTarefaFromId(String id) {
        
        ArrayList<Tarefa> tarefas = get("SELECT * FROM tarefa WHERE id=" + id);
        
        if(tarefas == null)
            System.out.println("AVISO! Nenhuma tarefa encontrada com o id: " 
                    + id);
        
        return tarefas.get(0);
    }
    
    /**
     * Pesquisa no db 'staygreen' usando o nome recebido.
     * @param nome
     * @return Retorna um ArrayList de objetos Tarefa que correspondem ao nome
     * recebido.
     */
    public static ArrayList<Tarefa> getTarefasFromNome(String nome) {
        
        ArrayList<Tarefa> tarefas = get("SELECT * FROM tarefa WHERE nome=" 
                + nome);
        
        if(tarefas == null)
            System.out.println("AVISO! Nenhuma tarefa encontrada com o nome: " 
                    + nome);
        
        return tarefas;
    }
    
    /**
     * Pesquisa no db 'staygreen' usando o tipo recebido.
     * @param tipo
     * @return Retorna um ArrayList de objetos Tarefa que correspondem ao tipo
     * recebido.
     */
    public static ArrayList<Tarefa> getTarefasFromTipo(String tipo) {
        
        ArrayList<Tarefa> tarefas = get("SELECT * FROM tarefa WHERE tipo=" 
                + tipo);
        
        if(tarefas == null)
            System.out.println("AVISO! Nenhuma tarefa encontrada com o tipo: " 
                    + tipo);
        
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
     * @param id Id da tarefa a ser removida.
     * @return Retorna true se a operação for bem sucedida e false se não for.
     */
    public static boolean remove(int id) {
        return SQL.delete(id, Tarefa.class);
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
                    tarefa.setTipo(result.getString(TIPO_COLUMN));
                    tarefa.setCaminhoImg(result.getString(CAMINHO_IMG_COLUMN));
                    tarefa.setDataMarcada(Data.dateToCalendar(result.getDate(DATA_MARCADA_COLUMN)));
                    tarefa.setRepeticao(result.getInt(REPETICAO_COLUMN));
                    tarefa.setProducaoPrevista(result.getDouble(PRODUCAO_PREVISTA_COLUMN));
                    tarefa.setValorGasto(result.getDouble(VALOR_GASTO_COLUMN));
                    
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
