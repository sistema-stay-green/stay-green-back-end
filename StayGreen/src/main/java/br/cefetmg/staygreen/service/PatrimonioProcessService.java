/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */

package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Patrimonio;
import br.cefetmg.staygreen.table.PatrimonioStatusEnum;
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
    
    @Deprecated
    public static String getPatrimonioByNome(String name){
        
        ArrayList<Patrimonio> patrimonios = new ArrayList<>();
        
        patrimonios = PatrimonioAccessService.getPatrimoniosByNome(name);
        
        return stringfy(patrimonios);
        
    }
    
    /**
     * Calcula e aplica a desvalorização de acordo com a Data
     * @param id
     * @return valor atual (dia do acesso)
     * @author Simonetti
     */
    public static Double desvalorizaPatrimonio(String id){
        
        // Instancia do patrimÃ´nio, desvalorizaÃ§Ã£o e Valor de compra
        Patrimonio patrimonio = PatrimonioAccessService.getPatrimonioById(id);

        Double indiceDepreciacaoAnual = patrimonio.getIndiceDepreciacao()/100;

        Double valorCompra = patrimonio.getValorCompra();
 	
	// Calculo da diferenÃ§a de data

        Integer anoCompra = patrimonio.getDataCompra().get(Calendar.YEAR);

        Integer anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        
	    Integer diferencaData = anoAtual-anoCompra;

        Double valorAtual = valorCompra - ((diferencaData * indiceDepreciacaoAnual) * valorCompra);
        
        return valorAtual;
    }
    
    /**
     * Vende um patrimonio
     * @param id
     * @return true ou false dependendo se o patrimonio já estiver vendido
     * @author Simonetti
     */
    public static boolean vendaPatrimonio(String id){
        //InstÃ¢ncia do objeto patrimÃ´nio a partir da sua Id
        Patrimonio patrimonio = PatrimonioAccessService.getPatrimonioById(id);
        //VerificaÃ§Ã£o 
        if(patrimonio.getStatus() == PatrimonioStatusEnum.VENDIDO)
            return false;
            else {
                Calendar dataBaixa = Calendar.getInstance();
                
                patrimonio.setStatus("VENDIDO");
                
                patrimonio.setDataBaixa(dataBaixa);
                
                PatrimonioAccessService.update(patrimonio);
                
                return true;          
        }
    }
    
    /**
     * Compra um patrimonio
     * @param nome
     * @param tipo
     * @param finalidade
     * @param indiceDepreciacao
     * @param valorCompra
     * @param dataCompra
     * @author Simonetti
     */
    public static void compraPatrimonio( String nome, String tipo, String finalidade,
        Double indiceDepreciacao, Double valorCompra, Calendar dataCompra){
        
        Patrimonio novoPatrimonio = new Patrimonio();
        
        novoPatrimonio.setNome(nome);
        
        novoPatrimonio.setTipo(tipo);
        
        novoPatrimonio.setFinalidade(finalidade);
        
        novoPatrimonio.setStatus("EM_POSSE");
        
        novoPatrimonio.setIndiceDepreciacao(indiceDepreciacao);
        
        novoPatrimonio.setValorCompra(valorCompra);
        
        novoPatrimonio.setDataCompra(dataCompra);
                
        PatrimonioAccessService.insert(novoPatrimonio);      

    }
    
    /**
     * Coloca um patrimonio em manutencao
     * @param id
     * @return true ou false dependendo se foi para manutenção corretamente
     * @author Simonetti
     */
    public static boolean colocaEmManutencao(String id){
        Patrimonio patrimonio = PatrimonioAccessService.getPatrimonioById(id);
        if(patrimonio.getStatus() != PatrimonioStatusEnum.EM_POSSE){
            return false;
            }else{
                patrimonio.setStatus("EM_MANUTENCAO");
                patrimonio.setDataSaida(Calendar.getInstance());
                PatrimonioAccessService.update(patrimonio);
                return true;
        }
    }
    
    /**
     * Aluga um patrimonio para terceiros
     * @param id
     * @return true ou false dependendo se for alugado corretamente
     * @author Simonetti
     */
    public static boolean alugaPatrimonio(String id){
        Patrimonio patrimonio = PatrimonioAccessService.getPatrimonioById(id);
        if(patrimonio.getStatus() != PatrimonioStatusEnum.EM_POSSE){
            return false;
            }else{
                patrimonio.setStatus("ALUGADO");
                patrimonio.setDataSaida(Calendar.getInstance());
                PatrimonioAccessService.update(patrimonio);
                return true;
        }
    }
    
    /**
     * Recebe um patrimonio que estava fora da fazenda
     * @param id
     * @return true ou false dependendo se for alugado corretamente
     * @author Simonetti
     */
    public static boolean recebePatrimonio(String id){
       Patrimonio patrimonio = PatrimonioAccessService.getPatrimonioById(id);
       if(patrimonio.getStatus() == PatrimonioStatusEnum.EM_POSSE)
            return false;
            else{
                patrimonio.setStatus("EM_POSSE");
                patrimonio.setDataRetorno(Calendar.getInstance());
                //patrimonio.setDataSaida(null);
                PatrimonioAccessService.update(patrimonio);
                return true;
       }
       
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
