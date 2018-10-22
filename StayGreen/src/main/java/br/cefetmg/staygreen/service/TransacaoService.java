/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import java.util.Calendar;


/**
 *
 * @author Gabriel
 * @version 15-10-18/07:16
 * 
 */
public class TransacaoService {
    
    //Outros metódos importantes
    
    /**
     * Calcula o valor total com base no valor e a quantidade de produtos que
     * foi comprada/vendida.
     * 
     * @param valor
     * @param entradaSaida
     * @param tipo
     * @return 
     */
    public static double calculaValorTotal(double valor, double entradaSaida, String tipo){
        double valorTotal=valor*entradaSaida;
        
        if(tipo.equals("compra")){
            return 0-valorTotal;
        }
        else
            return valorTotal;
    }
    
    /**
     * Calcula o valor atual com base no valor de compra, a depreciação anual e
     * o periodo de tempo entre a data atual e a data de compra
     * @param valorCompra
     * @param depreciacaoAnual
     * @param dataCompra
     * @return 
     */
    
    public static double calculaValorAtual(double valorCompra, double depreciacaoAnual, Calendar dataCompra){
        double valorAuxiliar = valorCompra;
        for (int i = 0; i < tempoEmAnos(dataCompra); i++) {
            valorAuxiliar=valorAuxiliar-((valorAuxiliar/100)*depreciacaoAnual);
        }
        return valorAuxiliar;
    }
    
    /**
     * Calcula o tempo em anos entre a data Atual e a data inicial
     * @param dataInicial
     */
    private static int tempoEmAnos(Calendar dataInicial){
        return dataAtual().get(Calendar.YEAR)-dataInicial.get(Calendar.YEAR);
    }
    
    /**
     * Calcula o tempo em meses entre a data Atual e a data inicial
     * @param dataInicial
     */
    private static int tempoEmMeses(Calendar dataInicial){
        return dataAtual().get(Calendar.MONTH)-
                dataInicial.get(Calendar.MONTH);
    }
    
    /**
     * Calcula a data Atual
     */
    private static Calendar dataAtual(){
        return Calendar.getInstance();
    }
    
    /**
     * Calcula o valor recebido com o aluguel da maquina ate o momento
     * @param taxaMensal
     * @param dataEmprestimo
     * @return 
     */
    public static double calculaValorRecebido(double taxaMensal, Calendar dataEmprestimo){
        return tempoEmMeses(dataEmprestimo)*taxaMensal;
    }
    
    /**
     * Calcula a data do roximo pagamento e relação da data que a maquina foi 
     * alugada na primeira vez. Se o dia de pagar nesse mês já passou a data de
     * pagar é no proximo mês.
     * @param dataEmprestimo
     * @return 
     */
    public static Calendar calculaProximoPagamento(Calendar dataEmprestimo){
        Calendar dataProxPagamento;
        dataProxPagamento = Calendar.getInstance();
        int diaInicial = dataEmprestimo.get(Calendar.DAY_OF_MONTH);
        int diaAtual = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        if (diaAtual>diaInicial) { 
            dataProxPagamento.set(Calendar.getInstance().get(Calendar.YEAR), (Calendar.getInstance().get(Calendar.MONTH)+1), diaInicial);
        }
        else
            dataProxPagamento.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), diaInicial);
        return dataProxPagamento;
    }
    
}
