/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Maquina;
import br.cefetmg.staygreen.util.SQL;
import br.cefetmg.staygreen.util.Data;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Gabriel, Mei
 * @version 16-10-18/18:29
 */
public class MaquinaAccessService {
    
    // Objetos de manipulação interna.
    private static ResultSet result;
    
    // Constantes que representam os nomes das colunas na tabela do DB SQL.
    private static final String ID_COLUMN;
    private static final String NOME_COLUMN;
    private static final String FINALIDADE_COLUMN;
    private static final String STATUS_COLUMN;
    private static final String DEPRECIACAO_ANUAL_COLUMN;
    private static final String VALOR_COMPRA_COLUMN;
    private static final String VALOR_ATUAL_COLUMN;
    private static final String VALOR_VENDA_COLUMN;
    private static final String DATA_COMPRA_COLUMN;
    private static final String DATA_RETORNO_COLUMN;
    private static final String DATA_BAIXA_COLUMN;
    
    // inicialização das constantes internas.
    static {
        ID_COLUMN = "id";
        NOME_COLUMN = "nome";
        FINALIDADE_COLUMN = "finalidade";
        STATUS_COLUMN = "status";
        DEPRECIACAO_ANUAL_COLUMN = "depreciacaoAnual";
        VALOR_COMPRA_COLUMN = "valorCompra";
        VALOR_ATUAL_COLUMN = "valorAtual";
        VALOR_VENDA_COLUMN = "valorVenda";
        DATA_COMPRA_COLUMN = "dataCompra";
        DATA_RETORNO_COLUMN = "dataRetorno";
        DATA_BAIXA_COLUMN = "dataBaixa";
    }
    
    /**
     * Pesquisa na DB 'staygreen' usando o Id recebido.
     * @param id
     * @return Retorna um objeto Maquina que corresponde ao Id recebido.
     */
    public static Maquina getMaquinaFromId(String id){
       
        
        ArrayList<Maquina> maquinas = get("SELECT * FROM maquina WHERE id=" + id);
        
        if (maquinas == null)
            System.out.println("AVISO! Nenhum maquina encontrado com o Id: " + id);
        
        return maquinas.get(0);
    }
    
    /**
     * Pesquisa na DB 'staygreen' usando o Nome recebido.
     * @param nome
     * @return Retorna objetos Maquina que correspondam ao Nome recebido.
     */
    public static ArrayList<Maquina> getMaquinasFromNome(String nome){
        
        ArrayList<Maquina> maquinas = get("SELECT * FROM maquina WHERE nome='" + nome + "'");
        
        if (maquinas == null)
            System.out.println("AVISO! Nenhum maquina encontrado com o Nome: " + nome);
        
        return maquinas;
        
    }
    
    /**
     * Pesquisa na DB 'staygreen' usando o Tipo recebido.
     * @param tipo
     * @return Retorna objetos Maquina que correspondam ao Tipo recebido. 
     */
    public static ArrayList<Maquina> getMaquinasFromTipo(String tipo){
        
        ArrayList<Maquina> maquinas = get("SELECT * FROM maquina WHERE tipo='" + tipo + "'");
        
        if (maquinas == null)
            System.out.println("AVISO! Nenhum maquina encontrado com o Tipo: " + tipo);
        
        return maquinas;
        
    }
    
    /**
     * Pesquisa na DB 'staygreen' usando a query recebida.
     * @param query
     * @return Retorna objetos Maquina resultantes da query de pesquisa recebida.
     */
    public static ArrayList<Maquina> get(String query){
        
        ArrayList<Maquina> maquinas = new ArrayList<>();
        
        try {
            result = SQL.query(query);
            if (result.next()){
                do {
                    Maquina maquina = new Maquina(
                            result.getLong(ID_COLUMN), 
                            result.getString(NOME_COLUMN),
                            result.getString(FINALIDADE_COLUMN),
                            result.getDouble(DEPRECIACAO_ANUAL_COLUMN),
                            result.getDouble(VALOR_COMPRA_COLUMN),
                            result.getDouble(VALOR_VENDA_COLUMN),
                            Data.dateToCalendar(result.getDate(DATA_COMPRA_COLUMN)),
                            Data.dateToCalendar(result.getDate(DATA_RETORNO_COLUMN)),
                            Data.dateToCalendar(result.getDate(DATA_BAIXA_COLUMN)));
                    maquina.setStatus(result.getString(STATUS_COLUMN));
                    maquinas.add(maquina);    
                } while (result.next());
            }
            else{
                System.out.println("AVISO! Nenhum resultado encontrado na query fornecida.");
            }
        } 
        catch (SQLException ex) {
            System.out.println(ex + " at getRowFromId");
            return null;
        }
        return maquinas;
    }
    
    /**
     * Insere um objeto Maquina na DB 'staygreen'
     * @param maquina
     */
    public static void insert(Maquina maquina){
        
            SQL.insert(maquina);
    }
    
    /**
     * Insere vários objetos Maquina na DB 'staygreen'
     * @param maquinas
     */
    public static void insertAll(ArrayList<Maquina> maquinas){
        
        maquinas.forEach((maquina) -> {
            insert(maquina);
        });
    }
    
    /**
     * Atualiza o maquina na DB correspondente ao objeto Maquina recebido
     * @param maquina
     */
    public static void update(Maquina maquina){
        
            SQL.update(maquina);
    }
    
    /**
     * Atualiza os maquinas na DB correspondentes aos objetos Maquina recebidos
     * @param maquinas
     */
    public static void updateAll(ArrayList<Maquina> maquinas){
        
        maquinas.forEach((maquina) -> {
            SQL.update(maquina);
        });
    }
    
    /**
     * Converte um objeto java.sql.Data para um objeto Calendar
     * @param date 
     * @return Retorna um objeto Calendar convertido.
     */
    private static Calendar dateToCalendar(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
