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
import java.util.HashMap;
import java.util.Map;

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
                dataLimite.add(Calendar.WEEK_OF_YEAR, -1);
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
     * Método que  gera o Relatório de produção, este utiliza as informações de
     * produção das tarefas da agenda eletrônica, para fazer o cálculo da
     * produção semanal.     *
     * 
     * @return Map<String, Integer> Retorna um map, com as informações da
     * produção total da semana.
     */
    public static Map<String, Integer> relatorio2() {
        ArrayList<Tarefa> tarefas = TarefaAccessService.getAll();
        ArrayList<Integer> producaoIndividual = new ArrayList(4);
        producaoIndividual.add(0, 0);
        producaoIndividual.add(1, 0);
        producaoIndividual.add(2, 0);
        producaoIndividual.add(3, 0);
        for (int i = 0; i < tarefas.size(); i++) {
            switch (tarefas.get(i).getProdutoProduzido()) {
                case LEITE:
                    producaoIndividual.set(0, producaoIndividual.get(0)
                            + relatorio2Check(tarefas.get(i)));
                    break;
                case CAFE_ROBUSTA:
                    producaoIndividual.set(1, producaoIndividual.get(1)
                            + relatorio2Check(tarefas.get(i)));
                    break;
                case CAFE_BOURBON:
                    System.out.println("bourbon");
                    producaoIndividual.set(2, producaoIndividual.get(2)
                            + relatorio2Check(tarefas.get(i)));
                    break;
                case CAFE_ARABICA:
                    producaoIndividual.set(3, producaoIndividual.get(3)
                            + relatorio2Check(tarefas.get(i)));
                    break;
                default:
            }
        }  
        Map<String, Integer> producaoTotal = new HashMap<>();
        producaoTotal.put("LEITE", producaoIndividual.get(0));
        producaoTotal.put("CAFE_ROBUSTA", producaoIndividual.get(1));
        producaoTotal.put("CAFE_BOURBON", producaoIndividual.get(2));
        producaoTotal.put("CAFE_ARABICA", producaoIndividual.get(3));
        return producaoTotal;
    }

    /**
     * Método auxiliar do relatório2, serve para fazer operações lógicas
     * repetitivas e parecidas, recebe uma tarefa como paramentro e calcula
     * sua produção individual.
     * 
     * @param tarefa
     * @return inteiro da produção individual de uma tarefa.
     */
    public static Integer relatorio2Check(Tarefa tarefa) {
        Calendar dataAtual = Calendar.getInstance();
        Calendar dataTarefa = tarefa.getDataInicialTarefa();
        Calendar dataSemana = Calendar.getInstance();
        dataSemana.add(Calendar.WEEK_OF_YEAR, -1);
        int semana = dataAtual.get(Calendar.DATE) - dataSemana.get(Calendar.DATE);
        int resultado = 0;
        if (dataAtual.compareTo(dataTarefa) > 0) {
            switch (tarefa.getPeriodRepetTarefa()) {
                case 1:
                    resultado = semana * tarefa.getQuantProduzTarefa();
                    break;
                case 2:
                    resultado = semana / 2 * tarefa.getQuantProduzTarefa();
                    break;
                case 3:
                    resultado = semana / 3 * tarefa.getQuantProduzTarefa();
                    break;
                case 4:
                    resultado = semana / 4 * tarefa.getQuantProduzTarefa();
                    break;
                case 5:
                    resultado = semana / 5 * tarefa.getQuantProduzTarefa();
                    break;
                case 6:
                    resultado = semana / 6 * tarefa.getQuantProduzTarefa();
                    break;
                case 7:
                    resultado = tarefa.getQuantProduzTarefa();
                    break;
                default:
            }
        }
        return resultado;
    }

}
