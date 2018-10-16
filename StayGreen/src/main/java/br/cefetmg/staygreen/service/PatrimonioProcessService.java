<<<<<<< Updated upstream
/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Patrimonio;
import java.util.ArrayList;

/**
 * Classe com a lógica principal de manipulação dos patrimônios da empresa.
 *
 * @author Mei
 */
public class PatrimonioProcessService {
    
    private static final String FIELD_SEPARATOR;
    private static final String OBJECT_SEPARATOR;
    
    static {
        FIELD_SEPARATOR = "§";
        OBJECT_SEPARATOR = "¢";
    }
    
    public static String getPatrimonioByNome(String name){
        
        ArrayList<Patrimonio> patrimonios = new ArrayList<>();
        
        patrimonios = PatrimonioAccessService.getPatrimoniosByNome(name);
        
        return stringfy(patrimonios);
        
    }
    
    /**
     * Concatenates values from objects Patrimonio into a new String
     * @param patrimonios
     * @return String with all values from Patrimonio Objects.
     */
    public static String stringfy(ArrayList<Patrimonio> patrimonios){
        
        String output = new String();
        
        for (Patrimonio patrimonio : patrimonios) {
            
            output += stringfy(patrimonio) + OBJECT_SEPARATOR;
        }
        
        return output;
    }
    
    /**
     * Concatenates values from a object Patrimonio into a new String
     * @param patrimonio
     * @return String with all values from a Patrimonio Object.
     */
    public static String stringfy(Patrimonio patrimonio){
        
        String output = new String();
        
        output += patrimonio.getId().toString() + FIELD_SEPARATOR;
        output += patrimonio.getNome() + FIELD_SEPARATOR;
        output += patrimonio.getTipo() + FIELD_SEPARATOR;
        output += patrimonio.getDescricao() + FIELD_SEPARATOR;
        output += patrimonio.getStatus().toString() + FIELD_SEPARATOR;
        output += patrimonio.getIndiceDepreciacao().toString() + FIELD_SEPARATOR;
        output += patrimonio.getValorCompra().toString() + FIELD_SEPARATOR;
        output += patrimonio.getValorAtual().toString() + FIELD_SEPARATOR;
        output += patrimonio.getDataCompra().getTime().toString() + FIELD_SEPARATOR;
        output += patrimonio.getDataSaida().getTime().toString() + FIELD_SEPARATOR;
        output += patrimonio.getDataBaixa().getTime().toString();
        
        return output;
    }
    
}
=======
/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Patrimonio;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;



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
    
    public static Double Desvaloriza(String id){
        //Calcula desvalorização
        
        //Instancia do patrimônio, desvalorização e Valor de compra
        Patrimonio patrimonio = PatrimonioAccessService.getPatrimonioById(id);

        Double indiceDepreciacaoAnual = patrimonio.getIndiceDepreciacao()/100;

        Double valorCompra = patrimonio.getValorCompra();
 	
	    //Calculo da diferença de data

        Integer anoCompra = patrimonio.getDataCompra().get(Calendar.YEAR);

        Integer anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        
	    Integer diferencaData = anoAtual-anoCompra;

        Double valorAtual = valorCompra - ((diferencaData * indiceDepreciacaoAnual) * valorCompra);
        
	    //Atualiza valor atual no banco de dados
        
	    patrimonio.setValorAtual(valorAtual);

        PatrimonioAccessService.update(patrimonio);
        
        return valorAtual;
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
>>>>>>> Stashed changes
