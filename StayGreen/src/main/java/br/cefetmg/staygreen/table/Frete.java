/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */

package br.cefetmg.staygreen.table;

import br.cefetmg.staygreen.annotation.Id;
import br.cefetmg.staygreen.annotation.Tabela;

/**
 * Representa uma linha da tabele frete
 * @author Paulo Vitor
 */
@Tabela("frete")
public class Frete {
    @Id
    private Long idFrete;
    private RegioesDoBrasilEnum regiaoFrete;
    private Double precoFrete;

    /**
     * Cria uma linha da tabela
     * @param idFrete id na tabela frete
     * @param regiaoFrete regiao do frete
     * @param valorFrete novo valor do frete
     */
    public Frete(Long idFrete, RegioesDoBrasilEnum regiaoFrete, Double valorFrete) {
        this.idFrete = idFrete;
        this.regiaoFrete = regiaoFrete;
        this.precoFrete = valorFrete;
    }

    /**
     * Cria uma linha sem id definido
     * @param regiaoFrete regiao do frete
     * @param valorFrete novo valor do frete
     */
    public Frete(RegioesDoBrasilEnum regiaoFrete, Double valorFrete) {
        this(null, regiaoFrete, valorFrete);
    }

    /**
     * Cria um frete sem valores definidos
     */
    public Frete() {
        this(null, null);
    }

    public Long getIdFrete() {
        return idFrete;
    }

    public void setIdFrete(Long idFrete) {
        this.idFrete = idFrete;
    }

    public RegioesDoBrasilEnum getRegiaoFrete() {
        return regiaoFrete;
    }

    public void setRegiaoFrete(RegioesDoBrasilEnum regiaoFrete) {
        this.regiaoFrete = regiaoFrete;
    }

    public Double getPrecoFrete() {
        return precoFrete;
    }

    public void setPrecoFrete(Double precoFrete) {
        this.precoFrete = precoFrete;
    }
    
    
}
