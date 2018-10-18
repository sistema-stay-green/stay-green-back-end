/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Maquina;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 * @inspiration PatrimonioProcessService
 * @version 18-10-18/16:22
 */
public class MaquinaProcessService {
    
    private static final String FIELD_SEPARATOR;
    private static final String OBJECT_SEPARATOR;
    
    static {
        FIELD_SEPARATOR = "§";
        OBJECT_SEPARATOR = "¢";
    }
    
    public static String getMaquinaByNome(String name){
        
        ArrayList<Maquina> maquinas = new ArrayList<>();
        
        maquinas = MaquinaAccessService.getMaquinasFromNome(name);
        
        return stringfy(maquinas);
        
    }
    
    /**
     * Concatenates values from objects Maquina into a new String
     * @param maquinas
     * @return String with all values from Maquina Objects.
     */
    public static String stringfy(ArrayList<Maquina> maquinas){
        
        String output = new String();
        
        for (Maquina maquina : maquinas) {
            
            output += stringfy(maquina) + OBJECT_SEPARATOR;
        }
        
        return output;
    }
    
    /**
     * Concatenates values from a object Maquina into a new String
     * @param maquina
     * @return String with all values from a Maquina Object.
     */
    public static String stringfy(Maquina maquina){
        
        String output = new String();
        
        output += maquina.getId().toString() + FIELD_SEPARATOR;
        output += maquina.getNome() + FIELD_SEPARATOR;
        output += maquina.getFinalidade() + FIELD_SEPARATOR;
        output += maquina.getStatus().toString() + FIELD_SEPARATOR;
        output += Double.toString(maquina.getDepreciacaoAnual())  + FIELD_SEPARATOR;
        output += Double.toString(maquina.getValorCompra()) + FIELD_SEPARATOR;
        output += Double.toString(maquina.getValorVenda()) + FIELD_SEPARATOR;
        output += Double.toString(maquina.getValorAtual()) + FIELD_SEPARATOR;
        output += maquina.getDataCompra().getTime().toString() + FIELD_SEPARATOR;
        output += maquina.getDataRetorno().getTime().toString() + FIELD_SEPARATOR;
        output += maquina.getDataBaixa().getTime().toString();
        
        return output;
    }
    
}

