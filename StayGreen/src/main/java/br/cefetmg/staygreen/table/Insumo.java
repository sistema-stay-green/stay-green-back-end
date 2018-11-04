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
    private Long id;
    private String nomeInsumo;
    private String finalidadeInsumo;
    private Double valorUnitInsumo;
    private Integer quantEstoqueInsumo;
    private Integer pontoAvisoInsumo;

    public Insumo() {
        this(null, null, null, null, null, null);
    }

    public Insumo(Long id, String nomeInsumo, String finalidadeInsumo, Double valorUnitInsumo, Integer quantEstoqueInsumo, Integer pontoAvisoInsumo) {
        this.id = id;
        this.nomeInsumo = nomeInsumo;
        this.finalidadeInsumo = finalidadeInsumo;
        this.valorUnitInsumo = valorUnitInsumo;
        this.quantEstoqueInsumo = quantEstoqueInsumo;
        this.pontoAvisoInsumo = pontoAvisoInsumo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return valorUnitInsumo;
    }

    public void setValorUnitInsumo(Double valorUnitInsumo) {
        this.valorUnitInsumo = valorUnitInsumo;
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
    
    
    
}
