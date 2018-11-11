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
 * @version 15-10-18/07:16
 * @coop Arthur
 * @version 11/11/18/17:47
 * Modifiquei o nome do id e seus métodos para que ficasse igual ao do BD.
 */
@Tabela("transacao")
public class Transacao {
    // Atributos
    @Id
    private Long idTransacao;
    private Long idItemTransacao;
    private double valorTransacao;
    private int quantTransacao;
    private Calendar dataTransacao;
    private TipoTransacao tipoTransacao;
    
    // Construtores

    /**
     *  Cria uma produto com todos os parametros
     * @param idTransacao Id único da linha na tabela
     * @param idItemTransacao Id do elemento localizado em outra tabela
     * @param valorTransacao Valor total da transação (valor individual * quantidade de elementos)
     * @param quantTransacao a quantidade de produtos vendida ou comprada
     * @param tipoTransacao Tipo do produto a ser transacionado
     * @param dataTransacao Data de transação do produto
     */
    public Transacao(Long idTransacao, Long idItemTransacao, double valorTransacao, int quantTransacao, Calendar dataTransacao, TipoTransacao tipoTransacao) {
        this.idTransacao = idTransacao;
        this.idItemTransacao = idItemTransacao;
        this.valorTransacao = valorTransacao;
        this.quantTransacao = quantTransacao;
        this.dataTransacao = dataTransacao;
        this.tipoTransacao = tipoTransacao;
    }
    
    public Transacao(Long idItemTransacao, double valorTransacao, int quantTransacao, Calendar dataTransacao, TipoTransacao tipoTransacao) {
        this.idItemTransacao = idItemTransacao;
        this.valorTransacao = valorTransacao;
        this.quantTransacao = quantTransacao;
        this.dataTransacao = dataTransacao;
        this.tipoTransacao = tipoTransacao;
    }

    /**
     * Cria uma transação com dados null
     */
    public Transacao() {
        this(null,null,0,0,null, null);
    }
    
    //Sets e Gets

    /**
     *
     * @return
     */

    public Long getIdItemTransacao() {
        return idItemTransacao;
    }

    /**
     *
     * @param idItemTransacao
     */
    public void setIdItemTransacao(Long idItemTransacao) {
        this.idItemTransacao = idItemTransacao;
    }

    /**
     *
     * @return
     */
    public double getValorTransacao() {
        return valorTransacao;
    }

    /**
     *
     * @param valorTransacao
     */
    public void setValorTransacao(double valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    /**
     *
     * @return
     */
    public int getQuantTransacao() {
        return quantTransacao;
    }

    /**
     *
     * @param quantTransacao
     */
    public void setQuantTransacao(int quantTransacao) {
        this.quantTransacao = quantTransacao;
    }

    /**
     *
     * @return
     */
    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    /**
     *
     * @param tipoTransacao
     */
    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }
    
    /**
     *
     * @return
     */
    public Calendar getDataTransacao() {
        return dataTransacao;
    }

    /**
     *
     * @param dataTransacao
     */
    public void setDataTransacao(Calendar dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    /**
     *
     * @return
     */
    public Long getIdTransacao() {
        return idTransacao;
    }

    /**
     *
     * @param idTransacao
     */
    public void setIdTransacao(Long idTransacao) {
        this.idTransacao = idTransacao;
    }   
    
    /**
     *
     * @param valorIndividual
     * @param tipo
     * @return
     */
    public double getValorTotal(double valorIndividual, int tipo){
        return TransacaoEAluguelService.calculaValorTotal(valorIndividual, quantTransacao, tipo);
    }

    @Override
    public String toString() {
        return "Transacao{" + "idTransacao=" + idTransacao + ", idItemTransacao=" + idItemTransacao + ", valorTransacao=" + valorTransacao + ", quantTransacao=" + quantTransacao + ", dataTransacao=" + dataTransacao + ", tipoTransacao=" + tipoTransacao + '}';
    }
    
    
}