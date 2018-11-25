/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.table;

import br.cefetmg.staygreen.annotation.Id;
import br.cefetmg.staygreen.annotation.Tabela;
import java.util.Calendar;

/**
 * Classe da tabela de vendas
 * 
 * @author Paulo Vitor
 * @version 1.0
 * @coop Arthur
 * Atualizei algumas coisas da tabela pra ficar igual ao bd.
 */
@Tabela("vendausuario")
public class VendaUsuario {
    @Id
    private Long idVenda;
    private Long idTransacao;
    private Long idComprador;
    private Double freteVenda;
    private Calendar tempoEntregaVenda;
    private Integer numeroVenda;

    /**
     * Cria uma Venda
     * @param idVenda idVenda da venda
     * @param idTransacao idTransacao da venda na tabela transação
     * @param idComprador idComprador do comprador
     * @param freteVenda preço do frete da venda
     * @param tempoEntregaVenda dia de entrega da venda
     * @param numeroVenda numero da venda
     */
    public VendaUsuario(Long idVenda, Long idTransacao, Long idComprador, Double freteVenda, Calendar tempoEntregaVenda, Integer numeroVenda) {
        this.idVenda = idVenda;
        this.idTransacao = idTransacao;
        this.idComprador = idComprador;
        this.freteVenda = freteVenda;
        this.tempoEntregaVenda = tempoEntregaVenda;
        this.numeroVenda = numeroVenda;
    }

    /**
     * Cria uma venda sem o idVenda
     * @param idTransacao idVenda da venda na tabela transação
     * @param idComprador idVenda do comprador
     * @param freteVenda preço do frete da venda
     * @param tempoEntregaVenda dia de entrega da venda
     * @param numeroVenda numero da venda
     */
    public VendaUsuario(Long idTransacao, Long idComprador, Double freteVenda, Calendar tempoEntregaVenda, Integer numeroVenda) {
        this.idTransacao = idTransacao;
        this.idComprador = idComprador;
        this.freteVenda = freteVenda;
        this.tempoEntregaVenda = tempoEntregaVenda;
        this.numeroVenda = numeroVenda;
    }

    /**
     * Cria uma venda com todos os campos nulos
     */
    public VendaUsuario() {
        this(null, null, null, null, null);
    }

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public Long getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Long idTransacao) {
        this.idTransacao = idTransacao;
    }

    public Long getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(Long idComprador) {
        this.idComprador = idComprador;
    }

    public Double getFreteVenda() {
        return freteVenda;
    }

    public void setFreteVenda(Double freteVenda) {
        this.freteVenda = freteVenda;
    }

    public Calendar getTempoEntregaVenda() {
        return tempoEntregaVenda;
    }

    public void setTempoEntregaVenda(Calendar tempoEntregaVenda) {
        this.tempoEntregaVenda = tempoEntregaVenda;
    }

    public Integer getNumeroVenda() {
        return numeroVenda;
    }

    public void setNumeroVenda(Integer numeroVenda) {
        this.numeroVenda = numeroVenda;
    }

    @Override
    public String toString() {
        return "VendaUsuario{" + "idVenda=" + idVenda + ", idTransacao=" + idTransacao + ", idComprador=" + idComprador + ", freteVenda=" + freteVenda + ", tempoEntregaVenda=" + tempoEntregaVenda + ", numeroVenda=" + numeroVenda + '}';
    }

   
    
    
}
