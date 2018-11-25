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
 * @author Arthur
 */
@Tabela("EstoqueProdutos")
public class EstoqueProdutos {
    @Id
    private Long idEstoque;
    private Long idProduto;
    private Integer quantProduzidaEstoque;
    private Calendar dataProducaoEstoque;

    /**
     * Construtor sem parâmetros.
     */
    public EstoqueProdutos() {
        this(null, null, null, null);
    }

    /**
     * Construtor com todos os parâmetros.
     * @param idEstoque
     * @param idProduto
     * @param quantProduzidaEstoque
     * @param dataProducaoEstoque
     */
    public EstoqueProdutos(Long idEstoque, Long idProduto,
            Integer quantProduzidaEstoque, Calendar dataProducaoEstoque) {
        this.idEstoque = idEstoque;
        this.idProduto = idProduto;
        this.quantProduzidaEstoque = quantProduzidaEstoque;
        this.dataProducaoEstoque = dataProducaoEstoque;
    }

    /**
     * Método para ler o estoque de um produto.
     * @return Long estoque do produto.
     */
    public Long getIdEstoque() {
        return idEstoque;
    }

    /**
     *  Método para gravar o estoque de um produto.
     * @param idEstoque
     */
    public void setIdEstoque(Long idEstoque) {
        this.idEstoque = idEstoque;
    }

    /**
     * Método para ler o id de um produto.
     * @return Long id do produto.
     */
    public Long getIdProduto() {
        return idProduto;
    }

    /**
     *  Método para gravar o id de um produto.
     *  @param idProduto
     */
    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * Método para ler a quantidade produzida de um produto.
     * @return Integer quantidade produzida de um produto.
     */
    public Integer getQuantProduzidaEstoque() {
        return quantProduzidaEstoque;
    }

    /**
     *  Método para gravar a quantidade produzida de um produto.
     * @param quantProduzidaEstoque
     */
    public void setQuantProduzidaEstoque(Integer quantProduzidaEstoque) {
        this.quantProduzidaEstoque = quantProduzidaEstoque;
    }

    /**
     * Método para ler a data da produção de um produto.
     * @return Calendar data da produção de um produto
     */
    public Calendar getDataProducaoEstoque() {
        return dataProducaoEstoque;
    }

    /**
     *  Método para gravar a data da produção de um produto.
     * @param dataProducaoEstoque
     */
    public void setDataProducaoEstoque(Calendar dataProducaoEstoque) {
        this.dataProducaoEstoque = dataProducaoEstoque;
    }

    @Override
    /**
     *  Método para transformar o objeto estoquePordutos em string.
     * @return String das propriedades do objeto e seus valores.
     */
    public String toString() {
        return "EstoqueProdutos{" + "idEstoque=" + idEstoque
                + ", idProduto=" + idProduto
                + ", quantProduzidaEstoque=" + quantProduzidaEstoque
                + ", dataProducaoEstoque=" + dataProducaoEstoque + '}';
    }

   
   
}

