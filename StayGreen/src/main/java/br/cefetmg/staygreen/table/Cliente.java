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
 *
 * @author Paulo Vitor
 * @versio 1.0
 */
@Tabela("clientes")
public class Cliente {
    @Id
    private Long id;
    private String nome;
    private String modoPagamento;
    private String enderecoEntrega;
    private String regiao;
    private Double valorTransporte;
    private Calendar dataAgendadaEntrega;

    /**
     * Cria um cliente
     * 
     * @param id id do cliente na tabela
     * @param nome nome do cliente
     * @param modoPagamento modo de pagamento do cliente
     * @param enderecoEntrega endereco para etrega do cliente
     * @param regiao região do cliente
     * @param valorTransporte valor de frete para entrega
     * @param dataAgendadaEntrega data agentada para a entrega
     */
    public Cliente(Long id, String nome, String modoPagamento, String enderecoEntrega, String regiao, Double valorTransporte, Calendar dataAgendadaEntrega) {
        this.id = id;
        this.nome = nome;
        this.modoPagamento = modoPagamento;
        this.enderecoEntrega = enderecoEntrega;
        this.regiao = regiao;
        this.valorTransporte = valorTransporte;
        this.dataAgendadaEntrega = dataAgendadaEntrega;
    }

    /**
     * Cria um cliente sem id
     * 
     * @param nome nome do cliente
     * @param modoPagamento modo de pagamento do cliente
     * @param enderecoEntrega endereco para etrega do cliente
     * @param regiao região do cliente
     * @param valorTransporte valor de frete para entrega
     * @param dataAgendadaEntrega data agentada para a entrega
     */
    public Cliente(String nome, String modoPagamento, String enderecoEntrega, String regiao, Double valorTransporte, Calendar dataAgendadaEntrega) {
        this(null, nome, modoPagamento, enderecoEntrega, regiao, valorTransporte, dataAgendadaEntrega);
    }

    public Cliente() {
        this(null, null, null, null, null, null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModoPagamento() {
        return modoPagamento;
    }

    public void setModoPagamento(String modoPagamento) {
        this.modoPagamento = modoPagamento;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public Double getValorTransporte() {
        return valorTransporte;
    }

    public void setValorTransporte(Double valorTransporte) {
        this.valorTransporte = valorTransporte;
    }

    public Calendar getDataAgendadaEntrega() {
        return dataAgendadaEntrega;
    }

    public void setDataAgendadaEntrega(Calendar dataAgendadaEntrega) {
        this.dataAgendadaEntrega = dataAgendadaEntrega;
    }
    
    
    
    
    
}
