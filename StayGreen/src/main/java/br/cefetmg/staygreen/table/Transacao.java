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
     * @param valorTransacao Valor total da transação (valor individual vezes
     * quantidade de elementos)
     * @param quantTransacao a quantidade de produtos vendida ou comprada
     * @param tipoTransacao Tipo do produto a ser transacionado
     * @param dataTransacao Data de transação do produto
     */
    public Transacao(Long idTransacao, Long idItemTransacao, double valorTransacao, 
            int quantTransacao, Calendar dataTransacao, 
            TipoTransacao tipoTransacao) {
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
     * @return o Long idItemTransacao, Id do elemento localizado em outra tabela
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
     * @return o double valorTransacao, que é o valor total da transação
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
     * @return o int quantTransacao,a quantidade de produtos vendida ou comprada
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
     * @return o tipoTransacao, tipo do produto a ser transacionado
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
     * @param tipoTransacao
     */
    public void setTipoTransacao(String tipoTransacao) {
        switch(tipoTransacao){
            case "MAQUINA":
                this.tipoTransacao = TipoTransacao.MAQUINA;
                break;
            case "PATRIMONIO":
                this.tipoTransacao = TipoTransacao.PATRIMONIO;
                break;
            case "INSUMO":
                this.tipoTransacao = TipoTransacao.INSUMO;
                break;
            case "PRODUTO":
                this.tipoTransacao = TipoTransacao.PRODUTO;
                break;
        }
    }
    
    /**
     *
     * @return o Calendar dataTransacao, data de transação do produto
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
     * @return o Id único da linha na tabela
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
     * @return e calcula o double valorTotal
     */
    public double getValorTotal(double valorIndividual, int tipo){
        return TransacaoEAluguelService.calculaValorTotal(valorIndividual, 
                quantTransacao, tipo);
    }

    @Override
    public String toString() {
        return "Transacao{" + "idTransacao=" + idTransacao + ", idItemTransacao=" + idItemTransacao + ", valorTransacao=" + valorTransacao + ", quantTransacao=" + quantTransacao + ", dataTransacao=" + dataTransacao + ", tipoTransacao=" + tipoTransacao + '}';
    }
    
    
}