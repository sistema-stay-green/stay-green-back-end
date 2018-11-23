/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Patrimonio;
import br.cefetmg.staygreen.util.JSON;
import java.util.ArrayList;

/**
 * Classe com a lógica principal de manipulação dos patrimônios da empresa.
 *
 * @author Mei Fagundes, Samuel Simonetti
 * @version 20-11-18/14:37
 */
public class PatrimonioProcessService {

    /**
     * Registra o Patrimônio recebido e o retorna em caso de sucesso.
     * 
     * @param patrimonio
     * @return response
     * @author Samuel Simonetti
     */
    public static String compraPatrimonio(Patrimonio patrimonio) {
        
        String resposta = "";
        
        if (patrimonio != null) {
            if (PatrimonioAccessService.insert(patrimonio)) {
                patrimonio = PatrimonioAccessService.
                    getLastInsertedPatrimonio();
                resposta = JSON.stringify(patrimonio);
            } else
                resposta = "F";
        } else
            resposta = "F";

        return resposta;
    }

    /**
     * Pesquisa por Patrimônios usando os parâmetros recebidos.
     * 
     * @param tipoPesquisa
     * @param formaPesquisa
     * @return response
     * @author Samuel Simonetti
     */
    public static String pesquisaPatrimonio(String tipoPesquisa, String formaPesquisa) {
        
        String resposta = "";
        ArrayList<Patrimonio> patrimonios = new ArrayList<>();
        
        switch (tipoPesquisa) {
            
            case "id":
                patrimonios.add(PatrimonioAccessService.
                        getPatrimonioById(formaPesquisa));

                if (patrimonios.isEmpty())
                    resposta += JSON.stringify(patrimonios);
                else
                    resposta = "N";
                break;

            case "nome":
                patrimonios = PatrimonioAccessService.
                        getPatrimoniosByNome(formaPesquisa);

                if (patrimonios.isEmpty())
                    resposta += JSON.stringify(patrimonios);
                else
                    resposta = "N";
                break;

            default:
                throw new IllegalArgumentException("Parametro 's' possui um valor inválido.");
        }
        return resposta;
    }

    /**
     * Retorna todos os Patrimônios registrados no DB.
     * 
     * @return response
     * @author Samuel Simonetti
     */
    public static String retornaTodosPatrimonio() {
        
        String resposta = "";
        ArrayList<Patrimonio> patrimonios;
        
        patrimonios = PatrimonioAccessService.getAll();
        if (patrimonios != null) 
            resposta = JSON.stringify(patrimonios);
        
        return resposta;
    }

    /**
     * Atualiza um Patrimônio já existente no DB.
     * 
     * @param patrimonio
     * @return response
     * @author Samuel Simonetti
     */
    public static String atualizaPatrimonio(Patrimonio patrimonio) {
        
        String resposta = "";
        
        if (patrimonio != null) {
            if (PatrimonioAccessService.update(patrimonio))
                resposta = "S";
            else
                resposta = "N";
        } else
            resposta = "F";
        return resposta;
    }

    /**
     * Remove um Patrimônio do DB.
     * 
     * @param id
     * @return response code
     * @author Samuel Simonetti
     */
    public static String deletaPatrimonio(String id) {
        
        String resposta = "";
        Patrimonio patrimonio = PatrimonioAccessService.getPatrimonioById(id);
        
        if (patrimonio != null) {
            if (PatrimonioAccessService.delete(patrimonio))
                resposta = "S";
            else
                resposta = "N";
        } else
            resposta = "F";
        return resposta;
    }
}
