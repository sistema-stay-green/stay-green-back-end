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
 * @coop Arthur
 * Atualizei algumas coisas da tabela pra ficar igual ao bd
 */
@Tabela("Comprador")
public class Comprador {
    @Id
    private Long idComprador;
    private String nomeComprador;
    private String enderecoComprador;
    private String cepComprador;
    private ModosPagamentoEnum modoPagamentoComprador;

    /**
     * Cria um Comprador com todos os atribútos inicializados
     * @param idComprador idComprador do comprador
     * @param nomeComprador nome do comprador
     * @param enderecoComprador endereço do comprador
     * @param cepComprador cep do comprador
     * @param modoPagamento modo de pagamento
     */
    public Comprador(Long idComprador, String nomeComprador, String enderecoComprador, String cepComprador, ModosPagamentoEnum modoPagamento) {
        this.idComprador = idComprador;
        this.nomeComprador = nomeComprador;
        this.enderecoComprador = enderecoComprador;
        this.cepComprador = cepComprador;
        this.modoPagamentoComprador = modoPagamento;
    }
    
    /**
     * Cria um comprador sem idComprador
     * @param nomeComprador nome do comprador
     * @param enderecoComprador endereço do comprador
     * @param cepComprador cep do comprador
     * @param modoPagamento modo de pagamento
     */
    public Comprador(String nomeComprador, String enderecoComprador, String cepComprador, ModosPagamentoEnum modoPagamento) {
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

    public Long getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(Long idComprador) {
        this.idComprador = idComprador;
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

    public ModosPagamentoEnum getModoPagamento() {
        return modoPagamentoComprador;
    }

    public void setModoPagamento(ModosPagamentoEnum modoPagamento) {
        this.modoPagamentoComprador = modoPagamento;
    }

    @Override
    public String toString() {
        return "Comprador{" + "idComprador=" + idComprador + ", nomeComprador=" + nomeComprador + ", enderecoComprador=" + enderecoComprador + ", cepComprador=" + cepComprador + ", modoPagamento=" + modoPagamentoComprador + '}';
    }

   
    
    
    
}
