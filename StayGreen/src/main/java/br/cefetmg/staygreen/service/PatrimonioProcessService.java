/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Patrimonio;
import java.util.ArrayList;



/**
 *
 * @author Mei
 */
public class PatrimonioProcessService {
    
    private static final String ITEM_SEPARATOR;
    private static final String OBJECT_SEPARATOR;
    
    static {
        ITEM_SEPARATOR = "§";
        OBJECT_SEPARATOR = "¢";
    }
    
    public static String getPatrimonioByNome(String name){
        
        ArrayList<Patrimonio> patrimonios = new ArrayList<>();
        
        patrimonios = PatrimonioAccessService.getPatrimoniosByNome(name);
        
        return stringfy(patrimonios);
        
    }
    
    public static String stringfy(ArrayList<Patrimonio> patrimonios){
        
        String output = "";
        
        for (Patrimonio patrimonio : patrimonios) {
            
            output.concat(stringfy(patrimonio));
        }
        
        return output;
    }
    
    public static String stringfy(Patrimonio patrimonio){
        
        String output = "";
        
        output.concat(patrimonio.getId().toString() + ITEM_SEPARATOR);
        output.concat(patrimonio.getNome() + ITEM_SEPARATOR);
        output.concat(patrimonio.getTipo() + ITEM_SEPARATOR);
        output.concat(patrimonio.getDescricao() + ITEM_SEPARATOR);
        output.concat(patrimonio.getStatus().toString() + ITEM_SEPARATOR);
        output.concat(patrimonio.getIndiceDepreciacao().toString() + ITEM_SEPARATOR);
        output.concat(patrimonio.getValorCompra().toString() + ITEM_SEPARATOR);
        output.concat(patrimonio.getValorAtual().toString() + ITEM_SEPARATOR);
        output.concat(patrimonio.getDataCompra().getTime().toString() + ITEM_SEPARATOR);
        output.concat(patrimonio.getDataSaida().getTime().toString() + ITEM_SEPARATOR);
        output.concat(patrimonio.getDataBaixa().getTime().toString() + OBJECT_SEPARATOR);
        
        return output;
    }
    
}
