/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.table;

import br.cefetmg.staygreen.annotation.Tabela;
import br.cefetmg.staygreen.annotation.Id;
import br.cefetmg.staygreen.service.TransacaoEAluguelService;
import java.util.Calendar;

/**
 * @author Gabriel Cruz
 * @version 21-10-18/14:53
 */
@Tabela("aluguel")
public class Aluguel {
    //Atributos
    @Id
    private Long idAluguel;
    private Long idMaquina;
    private double valorAluguel;
    private int periodoAluguel;
    private Calendar dataInicialAluguel;

    // Construtores

    /**
     *  Cria uma produto com todos os parametros
     * @param idAluguel Id único da linha na tabela
     * @param idMaquina Id da maquina localizado na tabela Patrimonio
     * @param valorAluguel Valor mensal recebido com o aluguel
     * @param periodoAluguel Periodo total que o aluguel está demarcado
     * @param dataInicialAluguel Data do emprestimo da produto
     */
    
    public Aluguel(Long idAluguel, Long idMaquina, double valorAluguel, 
            int periodoAluguel, Calendar dataInicialAluguel) {
        this.idAluguel = idAluguel;
        this.idMaquina = idMaquina;
        this.valorAluguel = valorAluguel;
        this.periodoAluguel = periodoAluguel;
        this.dataInicialAluguel = dataInicialAluguel;
    }   
    
    /**
     * Cria uma venda/compra com dados null
     */
    public Aluguel() {
        this(null,null,0,0,null);
    }
    //Sets e Gets

    /**
     *
     * @return o idAluguel, Id único da linha na tabela
     */
    public Long getIdAluguel() {
        return idAluguel;
    }

    /**
     *
     * @param idAluguel
     */
    public void setIdAluguel(Long idAluguel) {
        this.idAluguel = idAluguel;
    }

    /**
     *
     * @return o Id da maquina, localizado na tabela Patrimonio
     */
    public Long getIdMaquina() {
        return idMaquina;
    }

    /**
     *
     * @param idMaquina
     */
    public void setIdMaquina(Long idMaquina) {
        this.idMaquina = idMaquina;
    }

    /**
     *
     * @return ValorAluguel, valor mensal recebido com o aluguel
     */
    public double getValorAluguel() {
        return valorAluguel;
    }

    /**
     *
     * @param valorAluguel
     */
    public void setValorAluguel(double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    /**
     *
     * @return PeriodoAluguel, periodo total que o aluguel está demarcado
     */
    public int getPeriodoAluguel() {
        return periodoAluguel;
    }

    /**
     *
     * @param periodoAluguel
     */
    public void setPeriodoAluguel(int periodoAluguel) {
        this.periodoAluguel = periodoAluguel;
    }

    /**
     *
     * @return DataInicialAluguel, data do emprestimo da produto
     */
    public Calendar getDataInicialAluguel() {
        return dataInicialAluguel;
    }

    /**
     *
     * @param dataInicialAluguel
     */
    public void setDataInicialAluguel(Calendar dataInicialAluguel) {
        this.dataInicialAluguel = dataInicialAluguel;
    }

    /**
     *
     * @return o valor recebido até a data atual
     */
    public double getValorRecebido(){
        return TransacaoEAluguelService.
                calculaValorRecebido(valorAluguel, dataInicialAluguel);
    }
    
    /**
     *
     * @return o valor a ser recebido até a data final
     */
    public double getValorTotal(){
        return TransacaoEAluguelService.
                calculaValorTotalAluguel(valorAluguel, periodoAluguel);
    }
    
    /**
     *
     * @return a data que o proximo pagamento deve ser efetuado
     */
    public Calendar getProximoPagamento() {
        return TransacaoEAluguelService.
                calculaProximoPagamento(dataInicialAluguel);
    }
    
    /**
     *
     * @return a data que o contrato do aluguel acaba
     */
    public Calendar getDataFinalAluguel() {
        return TransacaoEAluguelService.calculaDataFinal(dataInicialAluguel,
                periodoAluguel);
    }
}
