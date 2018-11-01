/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.table;

import br.cefetmg.staygreen.annotation.Id;
import br.cefetmg.staygreen.annotation.Tabela;
import br.cefetmg.staygreen.util.Data;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Mei
 * @version 10-10-18/14:20
 */
@Tabela("patrimonio")
public class Patrimonio {
    
    // Attributes
    @Id
    private Integer id;
    private String nome;
    private String tipo;
    private String finalidade;
    private PatrimonioStatusEnum status;
    private Double indiceDepreciacao;
    private Double valorCompra;
    private Calendar dataCompra;
    private Calendar dataSaida;
    private Calendar dataRetorno;
    private Calendar dataBaixa;
    
    
    // Constructors

    /**
     * @author Gabriel Cruz
     * Cria um patrimônio com os para uso da MaquinaServlet
     * @param id
     * @param nome
     * @param finalidade
     * @param status
     * @param indiceDepreciacao
     * @param valorCompra
     * @param dataCompra
     */

    public Patrimonio(Integer id, String nome, String finalidade, PatrimonioStatusEnum status, Double indiceDepreciacao, Double valorCompra, Calendar dataCompra) {
        this.id = id;
        this.nome = nome;
        this.tipo = "MAQUINA";
        this.finalidade = finalidade;
        this.status = status;
        this.indiceDepreciacao = indiceDepreciacao;
        this.valorCompra = valorCompra;
        this.dataCompra = dataCompra;
    }
     
    /**
     *  Cria um patrimônio com um Id e Nome 
     * @param id Id único da linha na tabela
     * @param nome Nome do patrimônio
     */
    public Patrimonio(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    /**
     * Cria um patrimônio com um Nome
     * @param nome Nome do patrimônio
     */
    public Patrimonio(String nome){
        this(null, nome);
    }
    
    /**
     * Cria um patrimônio com dados null
     */
    public Patrimonio(){
        this(null, null);
    }
    
    // Getter and Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(PatrimonioStatusEnum status) {
        this.status = status;
    }
    
    public void setStatus(String status){
        
        try {
            this.status = PatrimonioStatusEnum.valueOf(status);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception + " at Patrimonio.setStatus()");
            System.out.println(" !!! ERRO !!! O valor inserido não corresponde "
                    + "a um estado possível de PatrimonioStatusEnum");
        }
    }

    public Double getIndiceDepreciacao() {
        return indiceDepreciacao;
    }

    public void setIndiceDepreciacao(Double indiceDepreciacao) {
        this.indiceDepreciacao = indiceDepreciacao;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Calendar getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Calendar dataCompra) {
        this.dataCompra = dataCompra;
    }
    
    public void setDataCompra(Date dataCompra) {
        this.dataCompra = Data.dateToCalendar(dataCompra);
    }

    public Calendar getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Calendar dataSaida) {
        this.dataSaida = dataSaida;
    }
    
    public void setDataSaida(Date dataSaida) {
        this.dataSaida = Data.dateToCalendar(dataSaida);
    }
    
    public Calendar getDataRetorno() {
        return dataRetorno;
    }
    
    public void setDataRetorno(Calendar dataRetorno) {
        this.dataRetorno = dataRetorno;
    }
    
    public void setDataRetorno(Date dataRetorno) {
        this.dataRetorno = Data.dateToCalendar(dataRetorno);
    }

    public Calendar getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(Calendar dataBaixa) {
        this.dataBaixa = dataBaixa;
    }
    
    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = Data.dateToCalendar(dataBaixa);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}



