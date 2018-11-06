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
 * @author arthu
 */
@Tabela("Insumo")
public class Insumo {
    @Id
    private Long idInsumo;
    private String nomeInsumo;
    private String finalidadeInsumo;
    private Double valorCompraInsumo;
    private Integer quantEstoqueInsumo;
    private Integer pontoAvisoInsumo;

    public Insumo() {
        this(null, null, null, null, null, null);
    }

    public Insumo(Long idInsumo, String nomeInsumo, String finalidadeInsumo, Double valorCompraInsumo, Integer quantEstoqueInsumo, Integer pontoAvisoInsumo) {
        this.idInsumo = idInsumo;
        this.nomeInsumo = nomeInsumo;
        this.finalidadeInsumo = finalidadeInsumo;
        this.valorCompraInsumo = valorCompraInsumo;
        this.quantEstoqueInsumo = quantEstoqueInsumo;
        this.pontoAvisoInsumo = pontoAvisoInsumo;
    }

    public Long getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(Long idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getNomeInsumo() {
        return nomeInsumo;
    }

    public void setNomeInsumo(String nomeInsumo) {
        this.nomeInsumo = nomeInsumo;
    }

    public String getFinalidadeInsumo() {
        return finalidadeInsumo;
    }

    public void setFinalidadeInsumo(String finalidadeInsumo) {
        this.finalidadeInsumo = finalidadeInsumo;
    }

    public Double getValorUnitInsumo() {
        return valorCompraInsumo;
    }

    public void setValorUnitInsumo(Double valorCompraInsumo) {
        this.valorCompraInsumo = valorCompraInsumo;
    }

    public Integer getQuantEstoqueInsumo() {
        return quantEstoqueInsumo;
    }

    public void setQuantEstoqueInsumo(Integer quantEstoqueInsumo) {
        this.quantEstoqueInsumo = quantEstoqueInsumo;
    }

    public Integer getPontoAvisoInsumo() {
        return pontoAvisoInsumo;
    }

    public void setPontoAvisoInsumo(Integer pontoAvisoInsumo) {
        this.pontoAvisoInsumo = pontoAvisoInsumo;
    }

    @Override
    public String toString() {
        return "Insumo{" + "id=" + idInsumo + ", nomeInsumo=" + nomeInsumo
                + ", finalidadeInsumo=" + finalidadeInsumo
                + ", valorCompraInsumo=" + valorCompraInsumo
                + ", quantEstoqueInsumo=" + quantEstoqueInsumo
                + ", pontoAvisoInsumo=" + pontoAvisoInsumo + '}';
    }
    
    
    
}
