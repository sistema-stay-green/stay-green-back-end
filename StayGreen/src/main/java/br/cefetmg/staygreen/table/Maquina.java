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
 * @author Gabriel Cruz
 * @version 15-10-18/10:35
 */
@Tabela("maquina")
public class Maquina {
    
    // Atributos
    @Id
    private Long id;
    private String nome;
    private String finalidade;
    private PatrimonioStatusEnum status;
    private double depreciacaoAnual;
    private double valorCompra;
    private double valorAtual;
    private double valorVenda;
    private Calendar dataCompra;
    private Calendar dataRetorno;
    private Calendar dataBaixa;
    
    
    // Construtores
    
    /**
     *  Cria uma maquina com todos os parametros
     * @param id Id único da linha na tabela
     * @param nome Nome da maquina
     * @param finalidade Finalidade da maquina
     * @param status Condição da maquina
     * @param depreciacaoAnual Depreciação anual da maquina
     * @param valorAtual Valor atual da maquina
     * @param valorVenda Valor de venda da maquina
     * @param dataCompra Data de compra da maquina
     * @param dataRetorno Data de retorno da maquina (data que ela retorna do aluguel)
     * @param dataBaixa Data de saida da maquina (data que ela foi vendida ou descartada)
     */
    public Maquina(Long id, String nome, String finalidade, 
            PatrimonioStatusEnum status, double depreciacaoAnual,
            double valorAtual, double valorVenda, Calendar dataCompra, 
            Calendar dataRetorno, Calendar dataBaixa) {
        this(null,nome,finalidade,status,deprediacaoAnual,
                valorAtual,valorVenda,dataCompra,dataRetorno,
                dataBaixa);
        calculaValorAtual();
    }

    /**
     * Cria uma maquina com dados null
     */
    public Maquina(){
        this(null,null,null,null,null,null,null,null,null,null);
        valorAtual=0;
    }
    
    //Sets e Gets

    /**
     *
     * @return
     */
    public String getFinalidade() {
        return finalidade;
    }

    /**
     *
     * @param finalidade
     */
    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    /**
     *
     * @return
     */
    public double getDepreciacaoAnual() {
        return depreciacaoAnual;
    }

    /**
     *
     * @param depreciacaoAnual
     */
    public void setDepreciacaoAnual(double depreciacaoAnual) {
        this.depreciacaoAnual = depreciacaoAnual;
    }

    /**
     *
     * @return
     */
    public Calendar getDataCompra() {
        return dataCompra;
    }

    /**
     *
     * @param dataCompra
     */
    public void setDataCompra(Calendar dataCompra) {
        this.dataCompra = dataCompra;
    }

    /**
     *
     * @return
     */
    public Calendar getDataRetorno() {
        return dataRetorno;
    }

    /**
     *
     * @param dataRetorno
     */
    public void setDataRetorno(Calendar dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    /**
     *
     * @return
     */
    public Calendar getDataBaixa() {
        return dataBaixa;
    }

    /**
     *
     * @param dataBaixa
     */
    public void setDataBaixa(Calendar dataBaixa) {
        this.dataBaixa = dataBaixa;
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
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public PatrimonioStatusEnum getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(PatrimonioStatusEnum status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public double getValorAtual() {
        if (valorAtual==0) {
            calculaValorAtual();
        }
        return valorAtual;
    }

    /**
     *
     * @return
     */
    public double getValorCompra() {
        return valorCompra;
    }

    /**
     *
     * @param valorCompra
     */
    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    /**
     *
     * @return 
     */
    public double getValorVenda() {
        return valorVenda;
    }

    /**
     *
     * @param valorVenda
     */
    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }
    
    //Outros metódos importantes
    
    /**
     * Calcula o tempo em anos entre a data Atual e a data de compra, utilizada
     * para calcular o Valor Atual
     */
    private int tempoEmAnos(){
        return Calendar.getInstance().get(Calendar.YEAR)-dataCompra.get(Calendar.YEAR)
    }
    
    /**
     * Calcula o valor atual com base no valor de compra, a depreciação anual e
     * o periodo de tempo entre a data atual e a data de compra
     */
    private void calculaValorAtual(){
        double valorAuxiliar = valorCompra;
        for (int i = 0; i < tempoEmAnos(); i++) {
            valorAuxiliar=valorAuxiliar-((valorAuxiliar/100)*depreciacaoAnual);
        }
        valorAtual=valorAuxiliar;
    }
       
}