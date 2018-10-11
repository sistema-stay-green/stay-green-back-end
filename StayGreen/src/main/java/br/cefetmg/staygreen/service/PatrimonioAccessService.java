/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Patrimonio;
import br.cefetmg.staygreen.util.SQL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Mei
 * @version 10-10-18/18:29
 */
public class PatrimonioAccessService {
    
    // Objetos de manipulação interna.
    private static Patrimonio patrimonio;
    private static ResultSet result;
    
    // Constantes que representam os nomes das colunas na tabela do DB SQL.
    private static final String ID_COLUMN;
    private static final String NOME_COLUMN;
    private static final String TIPO_COLUMN;
    private static final String DESCRICAO_COLUMN;
    private static final String STATUS_COLUMN;
    private static final String INDICE_DEPRECIACAO_COLUMN;
    private static final String VALOR_COMPRA_COLUMN;
    private static final String VALOR_ATUAL_COLUMN;
    private static final String DATA_COMPRA_COLUMN;
    private static final String DATA_SAIDA_COLUMN;
    private static final String DATA_BAIXA_COLUMN;
    
    // inicialização das constantes internas.
    static {
        ID_COLUMN = "Id";
        NOME_COLUMN = "Nome";
        TIPO_COLUMN = "Tipo";
        DESCRICAO_COLUMN = "Descrição";
        STATUS_COLUMN = "Status";
        INDICE_DEPRECIACAO_COLUMN = "Índice de Depreciação";
        VALOR_COMPRA_COLUMN = "Valor da Compra";
        VALOR_ATUAL_COLUMN = "Valor Atual";
        DATA_COMPRA_COLUMN = "Data da Compra";
        DATA_SAIDA_COLUMN = "Data da Saída";
        DATA_BAIXA_COLUMN = "Dada da Baixa";
    }
    
    /**
     * Pesquisa e retorna uma linha da tabela patrimônio através do Id. 
     * @param id
     * @return Retorna um objeto patrimônio com os dados da linha pesquisada.
     */
    public static Patrimonio getRowFromId(String id){
        
        try {
            
            result = SQL.query("SELECT * FROM patrimonio WHERE Id=" + id);

            if (result.next()) {
                
                patrimonio = new Patrimonio(result.getLong(ID_COLUMN), result.getString(NOME_COLUMN));
                patrimonio.setTipo(result.getString(TIPO_COLUMN));
                patrimonio.setDescricao(result.getString(DESCRICAO_COLUMN));
                patrimonio.setStatus(result.getString(STATUS_COLUMN));
                patrimonio.setIndiceDepreciacao(result.getDouble(INDICE_DEPRECIACAO_COLUMN));
                patrimonio.setValorCompra(result.getDouble(VALOR_COMPRA_COLUMN));
                patrimonio.setValorAtual(result.getDouble(VALOR_ATUAL_COLUMN));
                patrimonio.setDataCompra(dateToCalendar(result.getDate(DATA_COMPRA_COLUMN)));
                patrimonio.setDataSaida(dateToCalendar(result.getDate(DATA_SAIDA_COLUMN)));
                patrimonio.setDataBaixa(dateToCalendar(result.getDate(DATA_BAIXA_COLUMN)));
            } else{
                System.out.println("AVISO! getRowFromId não encontrou nenhum patrimônio com o Id: " + id);
                return null;
            }
            
            return patrimonio;
            
        } catch (SQLException ex) {
            System.out.println(ex + " at getRowFromId");
            return null;
        }
    }
    
    /**
     * Converte um objeto java.sql.Date para um objeto Calendar
     * @param date 
     * @return Retorna um objeto Calendar convertido.
     */
    private static Calendar dateToCalendar(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
