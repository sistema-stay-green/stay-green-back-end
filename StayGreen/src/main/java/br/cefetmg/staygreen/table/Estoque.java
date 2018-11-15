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
    private Long idEstoque;
    private Long idProduto;
    private Integer quantProduzidaEstoque;
    private Calendar dataProducaoEstoque;

    public Estoque() {
    }

    public Estoque(Long idEstoque, Long idProduto, Integer quantProduzidaEstoque, Calendar dataProducaoEstoque) {
        this.idEstoque = idEstoque;
        this.idProduto = idProduto;
        this.quantProduzidaEstoque = quantProduzidaEstoque;
        this.dataProducaoEstoque = dataProducaoEstoque;
    }

    public Long getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(Long idEstoque) {
        this.idEstoque = idEstoque;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQuantProduzidaEstoque() {
        return quantProduzidaEstoque;
    }

    public void setQuantProduzidaEstoque(Integer quantProduzidaEstoque) {
        this.quantProduzidaEstoque = quantProduzidaEstoque;
    }

    public Calendar getDataProducaoEstoque() {
        return dataProducaoEstoque;
    }

    public void setDataProducaoEstoque(Calendar dataProducaoEstoque) {
        this.dataProducaoEstoque = dataProducaoEstoque;
    }

   
   
}

