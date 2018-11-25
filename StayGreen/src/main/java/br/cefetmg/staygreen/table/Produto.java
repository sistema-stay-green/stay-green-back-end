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
    
    /**
     * Contrutor sem parâmetros.
     */
    public Produto() {
        this(null, null, null, null, null, null, null, null);
    }

    /**
     * Contrutor com todos as propriedades do objeto Produto como parâmetros.
     * @param idProduto
     * @param nomeProduto
     * @param descrProduto
     * @param unidMedProduto
     * @param valorUnitProduto
     * @param quantEstoqueProduto
     * @param pontoAvisoProduto
     * @param fotoMercadoria
     */
    public Produto(Long idProduto, NomeProdutoEnum nomeProduto,
            String descrProduto, UnidadesMedidaProdutoEnum unidMedProduto,
            Double valorUnitProduto, Integer quantEstoqueProduto,
            Integer pontoAvisoProduto, String fotoMercadoria) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descrProduto = descrProduto;
        this.unidMedProduto = unidMedProduto;
        this.valorUnitProduto = valorUnitProduto;
        this.quantEstoqueProduto = quantEstoqueProduto;
        this.pontoAvisoProduto = pontoAvisoProduto;
        this.fotoMercadoria = fotoMercadoria;
    }

    /**
     * Método para ler o id de um Produto.
     * @return Long id de um produto.
     */
    public Long getIdProduto() {
        return idProduto;
    }

    /**
     * Método para gravar o id de um Produto.
     * @param idProduto
     */
    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * Método para ler o nome de um Produto.
     * @return Enum NomeProdutoEnum com o nome do produto.
     */
    public NomeProdutoEnum getNomeProduto() {
        return nomeProduto;
    }

    /**
     *  Método para gravar o nome de um Produto.
     * @param nomeProduto
     */
    public void setNomeProduto(NomeProdutoEnum nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    /**
     * Método para ler a descrição de um Produto.
     * @return String com a descrição do Produto.
     */
    public String getDescrProduto() {
        return descrProduto;
    }

    /**
     * Método para gravar a descrição de um Produto.
     * @param descrProduto
     */
    public void setDescrProduto(String descrProduto) {
        this.descrProduto = descrProduto;
    }

    /**
     * Método para ler a unidade de medida de um Produto.
     * @return Enum UnidadesMediaProdutoEnum com a unidade de medida do Produto.
     */
    public UnidadesMedidaProdutoEnum getUnidMedProduto() {
        return unidMedProduto;
    }

    /**
     * Método para gravar a unidade de medida de um Produto.
     * @param unidMedProduto
     */
    public void setUnidMedProduto(UnidadesMedidaProdutoEnum unidMedProduto) {
        this.unidMedProduto = unidMedProduto;
    }

    /**
     * Método para ler o valor de uma unidade de um Produto.
     * @return Double com o valor de uma unidade de um Produto.
     */
    public Double getValorUnitProduto() {
        return valorUnitProduto;
    }

    /**
     * Método para gravar a unidade de medida de um Produto.
     * @param valorUnitProduto
     */
    public void setValorUnitProduto(Double valorUnitProduto) {
        this.valorUnitProduto = valorUnitProduto;
    }

    /**
     * Método para ler a quantidade estoque do Produto.
     * @return Integer quantidade estoque do Produto.
     */
    public Integer getQuantEstoqueProduto() {
        return quantEstoqueProduto;
    }

    /**
     * Método para gravar a quantidade estoque do Produto.
     * @param quantEstoqueProduto
     */
    public void setQuantEstoqueProduto(Integer quantEstoqueProduto) {
        this.quantEstoqueProduto = quantEstoqueProduto;
    }

    /**
     * Método para ler o ponto de aviso do Produto, isto é, quando o número de
     * estoque estiver proximo, abaixo ou igual ao ponto de aviso, um alerta
     * é emitido.
     * @return Integer com ponto de aviso do Produto.
     */
    public Integer getPontoAvisoProduto() {
        return pontoAvisoProduto;
    }

    /**
     * Método para gravar o ponto de aviso do Produto, isto é, quando o número de
     * estoque estiver proximo, abaixo ou igual ao ponto de aviso, um alerta
     * é emitido.
     * @param pontoAvisoProduto
     */
    public void setPontoAvisoProduto(Integer pontoAvisoProduto) {
        this.pontoAvisoProduto = pontoAvisoProduto;
    }

    /**
     * Método para ler o caminho para a foto do Produto.
     * @return String com caminho da foto do Produto.
     */
    public String getFotoMercadoria() {
        return fotoMercadoria;
    }

    /**
     * Método para gravar o caminho para a foto do Produto.
     * @param fotoMercadoria
     */
    public void setFotoMercadoria(String fotoMercadoria) {
        this.fotoMercadoria = fotoMercadoria;
    }

    @Override
    /**
     * Método para transformar o objeto Produto em string.
     * @return String das propriedades do objeto e seus valores.
     */
    public String toString() {
        return "Produto{" + "idProduto=" + idProduto
                + ", nomeProduto=" + nomeProduto
                + ", descrProduto=" + descrProduto
                + ", unidMedProduto=" + unidMedProduto
                + ", valorUnitProduto=" + valorUnitProduto
                + ", quantEstoqueProduto=" + quantEstoqueProduto
                + ", pontoAvisoProduto=" + pontoAvisoProduto
                + ", fotoMercadoria=" + fotoMercadoria + '}';
    }

    
    
}
