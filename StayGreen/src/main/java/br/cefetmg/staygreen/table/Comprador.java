/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */

package br.cefetmg.staygreen.table;


import br.cefetmg.staygreen.annotation.Id;
import br.cefetmg.staygreen.annotation.Tabela;

/**
 * Representa um comprador na tabela "comprador"
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
    private ModosPagamento modoPagamentoComprador;

    /**
     * Cria um Comprador com todos os atribútos inicializados
     * @param id id do comprador
     * @param nomeComprador nome do comprador
     * @param enderecoComprador endereço do comprador
     * @param cepComprador cep do comprador
     * @param modoPagamento modo de pagamento
     */
    public Comprador(Long id, String nomeComprador, String enderecoComprador, String cepComprador, ModosPagamento modoPagamento) {
        this.id = id;
        this.nomeComprador = nomeComprador;
        this.enderecoComprador = enderecoComprador;
        this.cepComprador = cepComprador;
        this.modoPagamentoComprador = modoPagamento;
    }
    
    /**
     * Cria um comprador sem id
     * @param nomeComprador nome do comprador
     * @param enderecoComprador endereço do comprador
     * @param cepComprador cep do comprador
     * @param modoPagamento modo de pagamento
     */
    public Comprador(String nomeComprador, String enderecoComprador, String cepComprador, ModosPagamento modoPagamento) {
        this.nomeComprador = nomeComprador;
        this.enderecoComprador = enderecoComprador;
        this.cepComprador = cepComprador;
        this.modoPagamentoComprador = modoPagamento;
    }
    
    /**
     * Cria um comprador com todos os campos nulos
     */
    public Comprador() {
        this(null, null, null, null);
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
        return modoPagamentoComprador;
    }

    public void setModoPagamento(ModosPagamento modoPagamento) {
        this.modoPagamentoComprador = modoPagamento;
    }

    @Override
    public String toString() {
        return "{"
                + "Id: " + this.id
                + "Nome: " + this.nomeComprador
                + "Endereço: " + this.enderecoComprador
                + "CEP: " + this.cepComprador
                + "Modo de pagamento: " + this.modoPagamentoComprador
                + "}";
    }
    
    
    
    
}
