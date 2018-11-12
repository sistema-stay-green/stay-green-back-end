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
 * @author Arthur
 */
@Tabela("Insumo")
public class Insumo {
    @Id
    private Long idInsumo; //identidade númerica
    private String nomeInsumo; //nome
    private String finalidadeInsumo; //finalidade, para que serve
    private Double valorCompraInsumo; // valor de compra
    private Integer quantEstoqueInsumo; // quantidade no estoque
    private Integer pontoAvisoInsumo; // ponto de aviso para estoque baixo

    /**
     * Contrutor sem parâmetros.
     */
    public Insumo() {
        this(null, null, null, null, null, null);
    }

    /**
     * Contrutor com todos as propriedades da classe como parâmetros.
     * @param idInsumo
     * @param nomeInsumo
     * @param finalidadeInsumo
     * @param valorCompraInsumo
     * @param quantEstoqueInsumo
     * @param pontoAvisoInsumo
     */
    public Insumo(Long idInsumo, String nomeInsumo, String finalidadeInsumo,
            Double valorCompraInsumo, Integer quantEstoqueInsumo,
            Integer pontoAvisoInsumo) {
        this.idInsumo = idInsumo;
        this.nomeInsumo = nomeInsumo;
        this.finalidadeInsumo = finalidadeInsumo;
        this.valorCompraInsumo = valorCompraInsumo;
        this.quantEstoqueInsumo = quantEstoqueInsumo;
        this.pontoAvisoInsumo = pontoAvisoInsumo;
    }

    /**
     *  
     * @return Id de um objeto insumo
     */
    public Long getIdInsumo() {
        return idInsumo;
    }

    /**
     * Define o id de um objeto insumo
     * @param idInsumo
     */
    public void setIdInsumo(Long idInsumo) {
        this.idInsumo = idInsumo;
    }

    /**
     * 
     * @return nome de um objeto insumo
     */
    public String getNomeInsumo() {
        return nomeInsumo;
    }

    /**
     * Define o nome de um objeto insumo
     * @param nomeInsumo
     */
    public void setNomeInsumo(String nomeInsumo) {
        this.nomeInsumo = nomeInsumo;
    }

    /**
     *
     * @return a finalidade de um objeto insumo
     */
    public String getFinalidadeInsumo() {
        return finalidadeInsumo;
    }

    /**
     * Define o nome de um objeto insumo
     * @param finalidadeInsumo
     */
    public void setFinalidadeInsumo(String finalidadeInsumo) {
        this.finalidadeInsumo = finalidadeInsumo;
    }

    /**
     * 
     * @return o valor de cada unidade de um objeto insumo
     */
    public Double getvalorCompraInsumo() {
        return valorCompraInsumo;
    }

    /**
     * Define o valor de cada unidade de um objeto insumo
     * @param valorCompraInsumo
     */
    public void setvalorCompraInsumo(Double valorCompraInsumo) {
        this.valorCompraInsumo = valorCompraInsumo;
    }

    /**
     * 
     * @return estoque de um objeto insumo
     */
    public Integer getQuantEstoqueInsumo() {
        return quantEstoqueInsumo;
    }

    /**
     * Define o estoque de um objeto insumo
     * @param quantEstoqueInsumo
     */
    public void setQuantEstoqueInsumo(Integer quantEstoqueInsumo) {
        this.quantEstoqueInsumo = quantEstoqueInsumo;
    }

    /**
     *  
     * @return ponto de aviso para o estoque baixo de um objeto insumo
     */
    public Integer getPontoAvisoInsumo() {
        return pontoAvisoInsumo;
    }

    /**
     * Define o ponto de aviso para o estoque baixo de um objeto insumo
     * @param pontoAvisoInsumo
     */
    public void setPontoAvisoInsumo(Integer pontoAvisoInsumo) {
        this.pontoAvisoInsumo = pontoAvisoInsumo;
    }
    
    @Override
    /**
     *
     * @return String de todas as propriedas do objeto insumo
     */
    public String toString() {
        return "Insumo{" + "id=" + idInsumo + ", nomeInsumo=" + nomeInsumo
                + ", finalidadeInsumo=" + finalidadeInsumo
                + ", valorCompraInsumo=" + valorCompraInsumo
                + ", quantEstoqueInsumo=" + quantEstoqueInsumo
                + ", pontoAvisoInsumo=" + pontoAvisoInsumo + '}';
    }
    
    
    
}
