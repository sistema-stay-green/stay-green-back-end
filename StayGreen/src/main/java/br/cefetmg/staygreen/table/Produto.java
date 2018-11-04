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
 * @version 1.1
 */
@Tabela("produto")
public class Produto {
    @Id
    private Long id;
    private String nomeProduto;
    private String descrProduto;
    private UnidadesMedidaProduto unidMedProduto;
    private Double valorUnitProduto;
    private Integer quantEstoqueProduto;
    private Integer pontoAvisoProduto;
    private String fotoMercadoria;

    public Produto(Long id, String nomeProduto, String descProduto, UnidadesMedidaProduto unidMedProduto, Double valorProduto, Integer quantEstoqueProduto, Integer pontoAvisoProdto, String fotoMercadoria) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.descrProduto = descProduto;
        this.unidMedProduto = unidMedProduto;
        this.valorUnitProduto = valorProduto;
        this.quantEstoqueProduto = quantEstoqueProduto;
        this.pontoAvisoProduto = pontoAvisoProdto;
        this.fotoMercadoria = fotoMercadoria;
    }

    public Produto(String nomeProduto, String descProduto, UnidadesMedidaProduto unidMedProduto, Double valorProduto, Integer quantEstoqueProduto, Integer pontoAvisoProdto, String fotoMercadoria) {
        this(null, nomeProduto, descProduto, unidMedProduto, valorProduto, quantEstoqueProduto, pontoAvisoProdto, fotoMercadoria);
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

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescrProduto() {
        return descrProduto;
    }

    public void setDescrProduto(String descrProduto) {
        this.descrProduto = descrProduto;
    }

    public UnidadesMedidaProduto getUnidMedProduto() {
        return unidMedProduto;
    }

    public void setUnidMedProduto(UnidadesMedidaProduto unidMedProduto) {
        this.unidMedProduto = unidMedProduto;
    }

    public Double getValorUnitProduto() {
        return valorUnitProduto;
    }

    public void setValorUnitProduto(Double valorUnitProduto) {
        this.valorUnitProduto = valorUnitProduto;
    }

    public Integer getQuantEstoqueProduto() {
        return quantEstoqueProduto;
    }

    public void setQuantEstoqueProduto(Integer quantEstoqueProduto) {
        this.quantEstoqueProduto = quantEstoqueProduto;
    }

    public Integer getPontoAvisoProduto() {
        return pontoAvisoProduto;
    }

    public void setPontoAvisoProduto(Integer pontoAvisoProduto) {
        this.pontoAvisoProduto = pontoAvisoProduto;
    }

    public String getFotoMercadoria() {
        return fotoMercadoria;
    }

    public void setFotoMercadoria(String fotoMercadoria) {
        this.fotoMercadoria = fotoMercadoria;
    }

    @Override
    public String toString() {
        return "{Id: "
                + "Nome: " + this.nomeProduto
                + ", Descrição: " + this.descrProduto
                + ", Valor: " + this.valorUnitProduto
                + ", Quantidade: " + this.quantEstoqueProduto
                + ", Ponto de Aviso" + this.pontoAvisoProduto
                + ", Foto: " + this.fotoMercadoria
                + ", Unidade de Medida: " + this.unidMedProduto
                + "}";
    }
    
    
}
