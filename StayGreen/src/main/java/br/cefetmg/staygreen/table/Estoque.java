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
 * @author arthu
 */
@Tabela("Estoque")
public class Estoque {
    @Id
    private Long id;
    private Integer quantProduzitoEstoque;
    private Calendar dataProducaoEstoque;

    public Estoque() {
        this(null, null, null);
    }

    public Estoque(Long id, Integer quantProduzitoEstoque, Calendar dataProducaoEstoque) {
        this.id = id;
        this.quantProduzitoEstoque = quantProduzitoEstoque;
        this.dataProducaoEstoque = dataProducaoEstoque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantProduzitoEstoque() {
        return quantProduzitoEstoque;
    }

    public void setQuantProduzitoEstoque(Integer quantProduzitoEstoque) {
        this.quantProduzitoEstoque = quantProduzitoEstoque;
    }

    public Calendar getDataProducaoEstoque() {
        return dataProducaoEstoque;
    }

    public void setDataProducaoEstoque(Calendar dataProducaoEstoque) {
        this.dataProducaoEstoque = dataProducaoEstoque;
    }
    
    
}

