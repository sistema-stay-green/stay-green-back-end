/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.util;

import java.util.Calendar;

/**
 * Métodos úteis para a manipulação de objetos relacionados a Data.
 * @author Mei
 */
public class Data {
    
    /**
     * Converte um objeto java.sql.Data para um objeto Calendar
     * @param date 
     * @return Retorna um objeto Calendar convertido.
     */
    public static Calendar dateToCalendar(java.sql.Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    
}
