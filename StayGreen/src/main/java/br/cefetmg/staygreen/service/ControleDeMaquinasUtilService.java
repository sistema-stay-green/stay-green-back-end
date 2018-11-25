/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author Gabriel
 * @version 22-10-18/07:12
 * 
 */
public class ControleDeMaquinasUtilService {
    
    /**
     * Calcula o valor total.
     * 
     * @param valor
     * @param quantTransacao
     * @param tipo
     * @return  double calculaValorTotal, o valor total com base no valor 
     * e a quantidade de produtos que foi transacionada
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
     * Calcula o valor total do aluguel.
     * 
     * @param valor
     * @param tempo
     * @return double calculaValorTotalAluguel, o valor total do aluguel com 
     * base no valor e a no tempo de aluguel.
     */
    public static double calculaValorTotalAluguel(double valor, int tempo){
        return valor*tempo;
    }
    
    /**
     * Calcula o tempo em meses.
     * 
     * @param dataInicial
     * @return int tempoEmMeses, a quantidade de meses entre a data atual e a 
     * data inicial.
     */
    private static int tempoEmMeses(Calendar dataInicial){
        return dataAtual().get(Calendar.MONTH)-
                dataInicial.get(Calendar.MONTH);
    }
    
    /**
     * Calcula o tempo em anos.
     * 
     * @param dataInicial
     * @return int tempoEmAnos, a quantidade de anos entre a data atual e a data
     * inicial.
     */
    private static int tempoEmAnos(Calendar dataInicial){
        return dataAtual().get(Calendar.YEAR)-
                dataInicial.get(Calendar.YEAR);
    }
    
    /**
     * Calcula o tempo em anos entre a data Atual e a data inicial.
     * 
     * @param dataFinal
     * @return int TempoEmAnosFuturos, diferençca de tempo entre agora e uma 
     * data no futuro.
     */
    public static int tempoEmAnosFuturos(Calendar dataFinal){
        return dataFinal.get(Calendar.YEAR)-dataAtual().get(Calendar.YEAR);
    }
    
    /**
     * Calcula a data Atual.
     * 
     * @return Calendar dataAtual, a data que atual no momento que a função for 
     * ser chamada.
     */
    public static Calendar dataAtual(){
        return Calendar.getInstance();
    }
    
    /**
     * Calcula o valor recebido com o aluguel da maquina ate o momento.
     * 
     * @param taxaMensal
     * @param dataEmprestimo
     * @return double calculaValorRecebido, o valor recebido com o aluguel da 
     * maquina ate o momento.
     */
    public static double calculaValorRecebido(double taxaMensal, 
            Calendar dataEmprestimo){
        return tempoEmMeses(dataEmprestimo)*taxaMensal;
    }
    
    /**
     * Calcula a data do proximo pagamento.
     * 
     * @param dataEmprestimo
     * @return Calendar calculaProximoPagamento, a data do proximo pagamento e 
     * relação da data que a maquina foi alugada na primeira vez. Se o dia de 
     * pagar nesse mês já passou a data de pagar é no proximo mês.
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
     * 
     * @param dataInicial
     * @param periodoAluguel
     * @return Calendar calculaDataFinal, a data que o contrato do aluguel acaba
     * com base na data inicial e o periodo do aluguel.
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
     * 
     * @param dataInicial
     * @param indiceDepreciacao
     * @param valorCompra
     * @return double calculaValorAtual, o valor atual com base no tempo entre o
     * dia que a função for chamada e a data de Compra, no indice de depreciação
     * e no valor de compra.
     */
    public static double calculaValorAtual(Calendar dataInicial, 
            double indiceDepreciacao, double valorCompra){
        double valorAtual=valorCompra;
        
        for (int i = 0; i < tempoEmAnos(dataInicial); i++) {
            valorAtual=valorAtual-(valorAtual*(indiceDepreciacao/100));
        }
        
        return valorAtual;
    }
    
    /**
     * Converte um valor String para Calendar.
     * 
     * @param stringData
     * @return Calendar converteStringToCalendar, um valor calendar que foi convertido de uma String.
     */
    public static Calendar converteStringToCalendar(String stringData){
        String stringDatas[];
        stringDatas = stringData.split("/");
        int dia = Integer.parseInt(stringDatas[0]);
        int mes = Integer.parseInt(stringDatas[1]);
        int ano = Integer.parseInt(stringDatas[2]);
        Calendar data= Calendar.getInstance();
        data.set(ano, mes, dia);
        return data;
    }
    
    /**
     * Calcula a quantidade de dias entre duas datas.
     * 
     * @autor Guilherme Sena
     * @param dataInicio
     * @param dataFinal
     * @return int diasEntre, a quantidade de dias entre duas datas.
     */
    public static int diasEntre(Calendar dataInicio, Calendar dataFinal){
        long dataFinalEmMilisegundos = dataFinal.getTimeInMillis();
        long dataInicialEmMilisegundos = dataInicio.getTimeInMillis();
        return (int) TimeUnit.MILLISECONDS.toDays(Math.
                abs(dataFinalEmMilisegundos - dataInicialEmMilisegundos));
    }
}
