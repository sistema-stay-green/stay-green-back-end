/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.annotation.Id;
import java.util.Calendar;

/**
 *
 * @author Aluno
 */
public class VendaService {
    // Atributos
    @Id
    private Long id;
    private String produto;
    private String tipo;
    private double entradaSaida;
    private double valor;
    private Calendar dataTransacao;
    
    // Construtores

    /**
     *  Cria uma maquina com todos os parametros
     * @param id Id único da linha na tabela
     * @param produto Produto a ser vendido
     * @param tipo Venda ou compra
     * @param entradaSaida a qunatidade de produtos vendida ou comprada
     * @param valor Valor do produto individualmente
     * @param dataTransacao Data de venda/compra do produto
     */
    public VendaService(Long id, String produto, String tipo, double entradaSaida, double valor, Calendar dataTransacao) {
        this.id = id;
        this.produto = produto;
        this.tipo = tipo;
        this.entradaSaida = entradaSaida;
        this.valor = valor;
        this.dataTransacao = dataTransacao;
    }

    /**
     * Cria uma venda/compra com dados null
     */
    public VendaService() {
        this(null,null,null,0,0,null);
    }
    
    //Sets e Gets

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
    public String getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     *
     * @return
     */
    public double getEntradaSaida() {
        return entradaSaida;
    }

    /**
     *
     * @param entradaSaida
     */
    public void setEntradaSaida(double entradaSaida) {
        this.entradaSaida = entradaSaida;
    }

    /**
     *
     * @return
     */
    public double getValor() {
        return valor;
    }

    /**
     *
     * @param valor
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     *
     * @return
     */
    public Calendar getDataTransacao() {
        return dataTransacao;
    }

    /**
     *
     * @param dataTransacao
     */
    public void setDataTransacao(Calendar dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

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
    public double getValorTotal(){
        return calculaValorTotal();
    }
    
    //Outros metódos importantes
    
    /**
     * Calcula o valor total com base no valor e a quantidade de produtos que
     * foi comprada/vendida.
     */
    private double calculaValorTotal(){
        double valorTotal=valor*entradaSaida;
        
        if(tipo.equals("compra")){
            return 0-valorTotal;
        }
        else
            return valorTotal;
    }
}
