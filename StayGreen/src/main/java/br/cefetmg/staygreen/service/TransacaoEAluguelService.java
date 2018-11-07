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
 * @version 22-10-18/07:12
 * 
 */
public class TransacaoEAluguelService {
    
    /**
     * Calcula o valor total com base no valor e a quantidade de produtos que
     * foi transacionada.
     * 
     * @param valor
     * @param quantTransacao
     * @param tipo
     * @return 
     */
    public static double calculaValorTotal(double valor, int quantTransacao, 
            int tipo){
        double valorTotal=valor*quantTransacao;
        
        if(tipo<0){
            return 0-valorTotal;
        }
        else
            return valorTotal;
    }
    
    /**
     * Calcula o valor total com base no valor e a quantidade de produtos que
     * foi transacionada.
     * 
     * @param valor
     * @param tempo
     * @return 
     */
    public static double calculaValorTotalAluguel(double valor, int tempo){
        return valor*tempo;
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
     * Calcula o tempo em anos entre a data Atual e a data inicial
     * @param dataInicial
     */
    private static int tempoEmAnos(Calendar dataInicial){
        return dataAtual().get(Calendar.YEAR)-
                dataInicial.get(Calendar.YEAR);
    }
    
    /**
     * Calcula o tempo em anos entre a data Atual e a data inicial
     * @param dataFinal
     * @return int TempoEmAnosFuturos, diferençca de tempo entre agora e uma 
     * data no futuro
     */
    public static int tempoEmAnosFuturos(Calendar dataFinal){
        return dataFinal.get(Calendar.YEAR)-dataAtual().get(Calendar.YEAR);
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
    public static double calculaValorRecebido(double taxaMensal, 
            Calendar dataEmprestimo){
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
            dataProxPagamento.set(Calendar.getInstance().get(Calendar.YEAR), 
                    (Calendar.getInstance().get(Calendar.MONTH)+1), diaInicial);
        }
        else
            dataProxPagamento.set(Calendar.getInstance().get(Calendar.YEAR), 
                    Calendar.getInstance().get(Calendar.MONTH), diaInicial);
        return dataProxPagamento;
    }
    
    /**
     * Calcula a data que o contrato do aluguel acaba.
     * @param dataInicial
     * @param periodoAluguel
     * @return 
     */
    public static Calendar calculaDataFinal(Calendar dataInicial, 
            int periodoAluguel){
        Calendar dataFimContrato;
        dataFimContrato = Calendar.getInstance();
        
        dataFimContrato.set(dataInicial.get(Calendar.YEAR), (dataInicial.
                get(Calendar.MONTH)+periodoAluguel), dataInicial.
                get(Calendar.DAY_OF_MONTH));
        
        return dataFimContrato;
    }
    
    /**
     * Calcula o valor atual.
     * @param dataInicial
     * @param indiceDepreciacao
     * @param valorCompra
     * @return 
     */
    public static double calculaValorAtual(Calendar dataInicial, 
            double indiceDepreciacao, double valorCompra){
        double valorAtual=valorCompra;
        
        for (int i = 0; i < tempoEmAnos(dataInicial); i++) {
            valorAtual=valorAtual-(valorAtual*(indiceDepreciacao/100));
        }
        
        return valorAtual;
    }
    
}
