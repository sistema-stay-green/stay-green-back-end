/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */

package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Patrimonio;
import java.util.ArrayList;
import java.util.Calendar;

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
     * Calcula e aplica a desvalorização de acordo com a Data
     * @param id
     * @return
     */
    public static Double Desvaloriza(String id){
        
        // Instancia do patrimônio, desvalorização e Valor de compra
        Patrimonio patrimonio = PatrimonioAccessService.getPatrimonioById(id);

        Double indiceDepreciacaoAnual = patrimonio.getIndiceDepreciacao()/100;

        Double valorCompra = patrimonio.getValorCompra();
 	
	// Calculo da diferença de data

        Integer anoCompra = patrimonio.getDataCompra().get(Calendar.YEAR);

        Integer anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        
	    Integer diferencaData = anoAtual-anoCompra;

        Double valorAtual = valorCompra - ((diferencaData * indiceDepreciacaoAnual) * valorCompra);
        
        // Atualiza valor atual no banco de dados
        
	patrimonio.setValorAtual(valorAtual);

        PatrimonioAccessService.update(patrimonio);
        
        return valorAtual;
    }
    
    /**
     * Concatenates values from objects Patrimonio into a new String
     * @param patrimonios
     * @return String with all values from Patrimonio Objects.
     */
    @Deprecated
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
    @Deprecated
    public static String stringfy(Patrimonio patrimonio){
        
        String output = new String();
        
        output += patrimonio.getId().toString() + FIELD_SEPARATOR;
        output += patrimonio.getNome() + FIELD_SEPARATOR;
        output += patrimonio.getTipo() + FIELD_SEPARATOR;
        output += patrimonio.getFinalidade() + FIELD_SEPARATOR;
        output += patrimonio.getStatus().toString() + FIELD_SEPARATOR;
        output += patrimonio.getIndiceDepreciacao().toString() + FIELD_SEPARATOR;
        output += patrimonio.getValorCompra().toString() + FIELD_SEPARATOR;
        output += patrimonio.getDataCompra().getTime().toString() + FIELD_SEPARATOR;
        output += patrimonio.getDataSaida().getTime().toString() + FIELD_SEPARATOR;
        output += patrimonio.getDataBaixa().getTime().toString();
        
        return output;
    }
    
}
