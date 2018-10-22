/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.table;

import br.cefetmg.staygreen.annotation.Tabela;
import br.cefetmg.staygreen.annotation.Id;
import br.cefetmg.staygreen.service.TransacaoService;
import java.util.Calendar;

/**
 *
 * @author Aluno
 */
@Tabela("aluguel")
public class Aluguel {
    //Atributos
    @Id
    private Long id;
    private String produto;
    private String locatario;
    private double taxaMensal;
    private double valorTotal;
    private Calendar dataEmprestimo;
    private Calendar dataTermino;
    private Calendar dataProximoPagamento;
    
    // Construtores

    /**
     *  Cria uma produto com todos os parametros
     * @param id Id único da linha na tabela
     * @param produto String a ser vendida
     * @param locatario Locatário da produto
     * @param taxaMensal Taxa a ser recebida mensalmente pelo emprestimo
     * @param dataEmprestimo Data do emprestimo da produto
     * @param dataTermino Data do Termino da produto
     */
    public Aluguel(Long id, String produto, String locatario, double taxaMensal, Calendar dataEmprestimo, Calendar dataTermino) {
        this.id = id;
        this.produto = produto;
        this.locatario = locatario;
        this.taxaMensal = taxaMensal;
        this.dataEmprestimo = dataEmprestimo;
        this.dataTermino = dataTermino;
        dataProximoPagamento = 
                TransacaoService.calculaProximoPagamento(dataEmprestimo);
        valorTotal=TransacaoService.
                calculaValorRecebido(taxaMensal, dataEmprestimo);
    }
    
    /**
     * Cria uma venda/compra com dados null
     */
    public Aluguel() {
        this(null,null,null,0,null,null);
    }
    //Sets e Gets

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getProduto() {
        return produto;
    }

    /**
     *
     * @param produto
     */
    public void setProduto(String produto) {
        this.produto = produto;
    }

    /**
     *
     * @return
     */
    public String getLocatario() {
        return locatario;
    }

    /**
     *
     * @param locatario
     */
    public void setLocatario(String locatario) {
        this.locatario = locatario;
    }

    /**
     *
     * @return
     */
    public double getTaxaMensal() {
        return taxaMensal;
    }

    /**
     *
     * @param taxaMensal
     */
    public void setTaxaMensal(double taxaMensal) {
        this.taxaMensal = taxaMensal;
    }

    /**
     *
     * @return
     */
    public Calendar getDataEmprestimo() {
        return dataEmprestimo;
    }

    /**
     *
     * @param dataEmprestimo
     */
    public void setDataEmprestimo(Calendar dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    
    /**
     *
     * @return
     */
    public Calendar getDataTermino() {
        return dataTermino;
    }

    /**
     *
     * @param dataTermino
     */
    public void setDataTermino(Calendar dataTermino) {
        this.dataTermino = dataTermino;
    }
    
    /**
     *
     * @return
     */
    public double getValorRecebido(){
        valorTotal=TransacaoService.
                calculaValorRecebido(taxaMensal, dataEmprestimo);
        return valorTotal;
    }
    
    /**
     *
     * @return
     */
    public Calendar getProximoPagamento() {
        dataProximoPagamento=
                TransacaoService.calculaProximoPagamento(dataEmprestimo);
        return dataProximoPagamento;
    }
}
