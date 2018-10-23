/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */

package br.cefetmg.staygreen.table;

import br.cefetmg.staygreen.annotation.Id;
import br.cefetmg.staygreen.annotation.Tabela;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe relacionada à tabela de produtos (temporária devido a possíveis mudanças)
 * @author Paulo Vitor
 * @version 1.0
 */
@Tabela("produtos")
public class Produto {
    @Id
    private Long id;
    private String nome;
    private String descricao;
    private Double valorUniCompra;
    private Double valorUniVenda;
    private Double pontoAviso;
    private Double estoque;
    private Double unidadeVenda;
    
    /**
     * Cria um Produto
     * @param id identificação
     * @param nome nome do produto
     * @param descricao descrição do produto
     * @param valorUniCompra valor de compra por unidade
     * @param valorUniVenda valor de venda por unidade
     * @param pontoAviso ponto de aviso do estoque
     * @param estoque estoque disponível
     * @param unidadeVenda unidade de venda
     */
    public Produto(Long id, String nome, String descricao, Double valorUniCompra, Double valorUniVenda, Double pontoAviso, Double estoque, Double unidadeVenda) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valorUniCompra = valorUniCompra;
        this.valorUniVenda = valorUniVenda;
        this.pontoAviso = pontoAviso;
        this.estoque = estoque;
        this.unidadeVenda = unidadeVenda;
    }

    /**
     * Cria um produto sem id
     * @param nome nome do produto
     * @param descricao descrição do produto
     * @param valorUniCompra valor de compra por unidade
     * @param valorUniVenda valor de venda por unidade
     * @param pontoAviso ponto de aviso do estoque
     * @param estoque estoque disponível
     * @param unidadeVenda unidade de venda
     */
    public Produto(String nome, String descricao, Double valorUniCompra, Double valorUniVenda, Double pontoAviso, Double estoque, Double unidadeVenda) {
        this(null, nome, descricao, valorUniCompra, valorUniVenda, pontoAviso, estoque, unidadeVenda);
    }
    
    public Produto() {
        this(null, null, null, null, null, null, null);
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorUniCompra() {
        return valorUniCompra;
    }

    public void setValorUniCompra(Double valorUniCompra) {
        this.valorUniCompra = valorUniCompra;
    }

    public Double getValorUniVenda() {
        return valorUniVenda;
    }

    public void setValorUniVenda(Double valorUniVenda) {
        this.valorUniVenda = valorUniVenda;
    }

    public Double getPontoAviso() {
        return pontoAviso;
    }

    public void setPontoAviso(Double pontoAviso) {
        this.pontoAviso = pontoAviso;
    }

    public Double getEstoque() {
        return estoque;
    }

    public void setEstoque(Double estoque) {
        this.estoque = estoque;
    }

    public Double getUnidadeVenda() {
        return unidadeVenda;
    }

    public void setUnidadeVenda(Double unidadeVenda) {
        this.unidadeVenda = unidadeVenda;
    }

    @Override
    public String toString() {
        return "Id: " + id + "\nNome: " + nome + "\nDescrição: " + descricao + "\nValor de Compra: " + valorUniCompra
            + "\nValor de Venda: " + valorUniVenda 
            + "\nPonto de aviso: " + pontoAviso 
            + "\nEstoque disponível: " +estoque  
            + "\nUnidade de Venda: " + unidadeVenda;
    }
    
    
    
}
