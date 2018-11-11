/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Métodos úteis para a manipulação de objetos relacionados a Data.
 * @author Mei Fagundes
 */
public class Data {
    
    private static final SimpleDateFormat DATE_FORMAT;
    private static final SimpleDateFormat DATE_TIME_FORMAT;
    
    static {
        DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
    }
    
    /**
     * Converte um objeto java.sql.Data para um objeto Calendar
     * @param date 
     * @return Retorna um objeto Calendar convertido.
     * @author Mei Fagundes
     */
    public static Calendar dateToCalendar(java.sql.Date date){
        
        if(date != null){
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        }else
            return null;
    }
    
    /**
     * Converte uma String de formato "yyyy-MM-dd-HH-mm" para
     * um objeto Calendar com os respectivos valores.
     * 
     * @param dateTime String de formato "yyyy-MM-dd-HH-mm".
     * @return Objeto Calendar
     * @throws ParseException
     * @author Mei Fagundes
     */
    public static Calendar getCalendarFromDateTimeString(String dateTime) 
            throws ParseException{
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DATE_TIME_FORMAT.parse(dateTime));
        return calendar;
    }
    
    /**
     * Converte uma String de formato "yyyy-MM-dd" para
     * um objeto Calendar com os respectivos valores.
     * 
     * @param date String de formato "yyyy-MM-dd".
     * @return Objeto Calendar
     * @throws ParseException
     * @author Mei Fagundes
     */
    public static Calendar getCalendarFromDateString(String date) 
            throws ParseException{
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DATE_FORMAT.parse(date));
        return calendar;
    }
}
