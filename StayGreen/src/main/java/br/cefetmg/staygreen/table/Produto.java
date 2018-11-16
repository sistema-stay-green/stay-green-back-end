/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */

package br.cefetmg.staygreen.table;

import br.cefetmg.staygreen.annotation.Id;
import br.cefetmg.staygreen.annotation.Tabela;
/**
 * Representa uma linha da tabela "produto"
 * @author Paulo Vitor
 * @author Arthur
 * @version 1.1
 */
@Tabela("produto")
public class Produto {
    @Id
    private Long idProduto;
    private NomeProdutoEnum nomeProduto;
    private String descrProduto;
    private UnidadesMedidaProdutoEnum unidMedProduto;
    private Double valorUnitProduto;
    private Integer quantEstoqueProduto;
    private Integer pontoAvisoProduto;
    private String fotoMercadoria;

    public Produto() {
        this(null, null, null, null, null, null, null, null);
    }

    public Produto(Long idProduto, NomeProdutoEnum nomeProduto, String descrProduto, UnidadesMedidaProdutoEnum unidMedProduto, Double valorUnitProduto, Integer quantEstoqueProduto, Integer pontoAvisoProduto, String fotoMercadoria) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descrProduto = descrProduto;
        this.unidMedProduto = unidMedProduto;
        this.valorUnitProduto = valorUnitProduto;
        this.quantEstoqueProduto = quantEstoqueProduto;
        this.pontoAvisoProduto = pontoAvisoProduto;
        this.fotoMercadoria = fotoMercadoria;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public NomeProdutoEnum getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(NomeProdutoEnum nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescrProduto() {
        return descrProduto;
    }

    public void setDescrProduto(String descrProduto) {
        this.descrProduto = descrProduto;
    }

    public UnidadesMedidaProdutoEnum getUnidMedProduto() {
        return unidMedProduto;
    }

    public void setUnidMedProduto(UnidadesMedidaProdutoEnum unidMedProduto) {
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
        return "Produto{" + "idProduto=" + idProduto + ", nomeProduto=" + nomeProduto + ", descrProduto=" + descrProduto + ", unidMedProduto=" + unidMedProduto + ", valorUnitProduto=" + valorUnitProduto + ", quantEstoqueProduto=" + quantEstoqueProduto + ", pontoAvisoProduto=" + pontoAvisoProduto + ", fotoMercadoria=" + fotoMercadoria + '}';
    }

    
    
}
