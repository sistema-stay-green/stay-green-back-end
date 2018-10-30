/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */

package br.cefetmg.staygreen.table;


import br.cefetmg.staygreen.annotation.Id;
import br.cefetmg.staygreen.annotation.Tabela;

/**
 *
 * @author Paulo Vitor
 * @versio 1.0
 */
@Tabela("Comprador")
public class Comprador {
    @Id
    private Long id;
    private String nomeComprador;
    private String enderecoComprador;
    private String cepComprador;
    private ModosPagamento modoPagamento;

    public Comprador(Long id, String nomeComprador, String enderecoComprador, String cepComprador, ModosPagamento modoPagamento) {
        this.id = id;
        this.nomeComprador = nomeComprador;
        this.enderecoComprador = enderecoComprador;
        this.cepComprador = cepComprador;
        this.modoPagamento = modoPagamento;
    }

    public Comprador(String nomeComprador, String enderecoComprador, String cepComprador, ModosPagamento modoPagamento) {
        this.nomeComprador = nomeComprador;
        this.enderecoComprador = enderecoComprador;
        this.cepComprador = cepComprador;
        this.modoPagamento = modoPagamento;
    }
    
    public Comprador() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }

    public String getEnderecoComprador() {
        return enderecoComprador;
    }

    public void setEnderecoComprador(String enderecoComprador) {
        this.enderecoComprador = enderecoComprador;
    }

    public String getCepComprador() {
        return cepComprador;
    }

    public void setCepComprador(String cepComprador) {
        this.cepComprador = cepComprador;
    }

    public ModosPagamento getModoPagamento() {
        return modoPagamento;
    }

    public void setModoPagamento(ModosPagamento modoPagamento) {
        this.modoPagamento = modoPagamento;
    }
    
    
    
    
}
