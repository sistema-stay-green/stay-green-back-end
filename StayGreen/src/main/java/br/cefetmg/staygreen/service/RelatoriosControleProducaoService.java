/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Tarefa;
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
     * Método que gera o relatório Histórico por mercadorias/por período: O
     * usuário irá selecionar um período de tempo (diário, semanal, mensal) e
     * será mostrado todas as compras feitas por ele naquele período de tempo.
     *
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
        int diaAtual = Calendar.getInstance().get(Calendar.DATE);
        int mesAtual = Calendar.getInstance().get(Calendar.MONTH);
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        int diaTarefa;
        int mesTarefa;
        int anoTarefa;
        ArrayList<Integer> resultados = new ArrayList();
        ArrayList<Tarefa> tarefas = TarefaAccessService.get("SELECT * FROM `tarefa`");
        for (int i = 0; i < tarefas.size(); i++) {
            diaTarefa = tarefas.get(i).getDataInicialTarefa().get(Calendar.DATE);
            mesTarefa = tarefas.get(i).getDataInicialTarefa().get(Calendar.MONTH);
            anoTarefa = tarefas.get(i).getDataInicialTarefa().get(Calendar.YEAR);
            if (diaAtual >= diaTarefa && mesAtual >= mesTarefa && anoAtual >= anoTarefa) {
                if ((diaAtual - 7) <= diaTarefa) {
                    switch (tarefas.get(i).getPeriodRepetTarefa()) {
                        case 1:
                            resultados.add((diaAtual - diaTarefa) * tarefas.get(i).getQuantProduzTarefa());
                            break;
                        case 2:
                            resultados.add((diaAtual - diaTarefa) / 2 * tarefas.get(i).getQuantProduzTarefa());
                            break;
                        case 3:
                            resultados.add((diaAtual - diaTarefa) / 3 * tarefas.get(i).getQuantProduzTarefa());
                            break;
                        case 4:
                            resultados.add((diaAtual - diaTarefa) / 4 * tarefas.get(i).getQuantProduzTarefa());
                            break;
                        case 5:
                            resultados.add((diaAtual - diaTarefa) / 5 * tarefas.get(i).getQuantProduzTarefa());
                            break;
                        case 6:
                            resultados.add((diaAtual - diaTarefa) / 6 * tarefas.get(i).getQuantProduzTarefa());
                            break;
                        case 7:
                            resultados.add((diaAtual - diaTarefa) / 7 * tarefas.get(i).getQuantProduzTarefa());
                            break;
                        default:
                    }
                }
            }
        }
        int aux = 0;
        for(int i = 0; i < resultados.size(); i++){
            aux += resultados.get(i);
        }
        System.out.println(resultados.toString());

        return String.valueOf(aux);
    }

}
