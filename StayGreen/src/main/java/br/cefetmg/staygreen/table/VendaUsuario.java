/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.table;

import br.cefetmg.staygreen.annotation.Id;
import br.cefetmg.staygreen.annotation.Tabela;

/**
 * Classe da tabela de vendas
 * 
 * @author Paulo Vitor
 * @version 1.0
 */
@Tabela("VendaUsuario")
public class VendaUsuario {
    @Id
    private Long id;
    private Long idTransacao;
    private Long idComprador;
    private Double freteVenda;
    private Integer tempoEntregaVenda;
    private Integer numeroVenda;

    public VendaUsuario(Long id, Long idTransacao, Long idComprador, Double freteVenda, Integer tempoEntregaVenda, Integer numeroVenda) {
        this.id = id;
        this.idTransacao = idTransacao;
        this.idComprador = idComprador;
        this.freteVenda = freteVenda;
        this.tempoEntregaVenda = tempoEntregaVenda;
        this.numeroVenda = numeroVenda;
    }

    public VendaUsuario(Long idTransacao, Long idComprador, Double freteVenda, Integer tempoEntregaVenda, Integer numeroVenda) {
        this.idTransacao = idTransacao;
        this.idComprador = idComprador;
        this.freteVenda = freteVenda;
        this.tempoEntregaVenda = tempoEntregaVenda;
        this.numeroVenda = numeroVenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getTempoEntregaVenda() {
        return tempoEntregaVenda;
    }

    public void setTempoEntregaVenda(Integer tempoEntregaVenda) {
        this.tempoEntregaVenda = tempoEntregaVenda;
    }

    public Integer getNumeroVenda() {
        return numeroVenda;
    }

    public void setNumeroVenda(Integer numeroVenda) {
        this.numeroVenda = numeroVenda;
    }
    
}
