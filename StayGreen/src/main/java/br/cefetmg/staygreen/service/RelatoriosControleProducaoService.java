/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Transacao;
import br.cefetmg.staygreen.util.JSON;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Arthur
 * @coop Mei
 */
public class RelatoriosControleProducaoService {

    /**
     * Método que gera o relatório Histórico por mercadorias/por período:
     * O usuário irá selecionar um período de tempo (diário, semanal, mensal)
     * e será mostrado todas as compras feitas por ele naquele período de tempo.

     * @param id
     * @return JSON string de todas as trasações feitas pelo usuário no período
     * de tempo escolhido.
     */
    public static String relatorio1(String id) {
        Calendar dataAtual = null;
        Calendar dataLimite = null;
        switch (id) {
            case "diario":
                dataAtual = dataLimite = Calendar.getInstance();
                break;
            case "semanal":
                dataAtual = Calendar.getInstance();
                dataLimite = Calendar.getInstance();
                dataLimite.add(Calendar.DATE, -7);
                break;
            case "mensal":
                dataAtual = Calendar.getInstance();
                dataLimite = Calendar.getInstance();
                dataLimite.add(Calendar.MONTH, -1);
                break;
            default:
                return null;
        }
        SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
        String busca = "WHERE `dataTransacao` BETWEEN '"
                + formatoData.format(dataLimite.getTime())
                + "' AND '"
                + formatoData.format(dataAtual.getTime())
                + "'";
        ArrayList<Transacao> transacaos = TransacaoService.get(busca);
        return JSON.stringify(transacaos);
    }

    /**
     *
     * @param id
     * @return
     */
    public static String relatorio2(String id) {

        return null;
    }

}
