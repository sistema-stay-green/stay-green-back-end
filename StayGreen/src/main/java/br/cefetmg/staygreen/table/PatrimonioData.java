/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.table;

import br.cefetmg.staygreen.util.Data;
import java.text.ParseException;
import java.util.Calendar;

/**
 * Objeto de armazenamento auxiliar das Datas do objeto Patrimonio.
 * @author Mei Fagundes
 */
public class PatrimonioData {

    private String dataCompra;
    private String dataSaida;
    private String dataBaixa;
    private String dataRetorno;

    public PatrimonioData(String dataCompra, String dataSaida,
            String dataBaixa, String dataRetorno) {
        
        this.dataCompra = dataCompra;
        this.dataSaida = dataSaida;
        this.dataBaixa = dataBaixa;
        this.dataRetorno = dataRetorno;
        
    }
    
    public PatrimonioData(){
        this(null, null, null, null);
    }
    
    public Calendar getDataCompra() {
        
        try{
            if (dataCompra != null)
                return Data.getCalendarFromDateTimeString(dataCompra);
        } catch(ParseException parseException){
            System.out.println("ERRO! " + parseException + " at getDataCompra.");
        }
        return null;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Calendar getDataSaida() {
        
        try{
            if (dataSaida != null)
                return Data.getCalendarFromDateTimeString(dataSaida);
        } catch(ParseException parseException){
            System.out.println("ERRO! " + parseException + " at getDataSaida.");
        }
        return null;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Calendar getDataBaixa() {
        
        try{
            if (dataBaixa != null)
                return Data.getCalendarFromDateTimeString(dataBaixa);
        } catch(ParseException parseException){
            System.out.println("ERRO! " + parseException + " at getDataBaixa.");
        }
        return null;
    }

    public void setDataBaixa(String dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public Calendar getDataRetorno() {
        
        try{
            if (dataRetorno != null)
                return Data.getCalendarFromDateTimeString(dataRetorno);
        } catch(ParseException parseException){
            System.out.println("ERRO! " + parseException + " at getDataRetorno.");
        }
        return null;
    }

    public void setDataRetorno(String dataRetorno) {
        this.dataRetorno = dataRetorno;
    }
    
    
    
}
