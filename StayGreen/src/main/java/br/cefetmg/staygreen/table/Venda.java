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
 */
@Tabela("vendas")
public class Venda {
    @Id
    private Long id;
    private String produto;
    private Double entradaSaida;
    private Calendar dataTransacao;
    private Double valor;

    /**
     * Cria uma venda
     * 
     * @param id Id da venda na tabela
     * @param produto Nome do produto
     * @param entradaSaida Quantidade vendida/comprada
     * @param dataTransacao Data da Venda
     * @param valor Valor da venda
     */
    public Venda(Long id, String produto, Double entradaSaida, Calendar dataTransacao, Double valor) {
        this.id = id;
        this.produto = produto;
        this.entradaSaida = entradaSaida;
        this.dataTransacao = dataTransacao;
        this.valor = valor;
    }

    /**
     * Cria uma venda sem especificar id
     * 
     * @param produto Nome do produto
     * @param entradaSaida Quantidade vendida/comprada
     * @param dataTransacao Data da Venda
     * @param valor Valor da venda
     */
    public Venda(String produto, Double entradaSaida, Calendar dataTransacao, Double valor) {
        this(null, produto, entradaSaida, dataTransacao, valor);
    }
    
    /**
     * Cria uma venda vazia
     */
    public Venda() {
        this(null,null,null,null,null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Double getEntradaSaida() {
        return entradaSaida;
    }

    public void setEntradaSaida(Double entradaSaida) {
        this.entradaSaida = entradaSaida;
    }

    public Calendar getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Calendar dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    
}
