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
    private String nomePatrimonio;
    private PatrimonioTipoEnum tipoPatrimonio;
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
     *  Cria um patrimônio com um Id e Nome 
     * @param id Id único da linha na tabela
     * @param nomePatrimonio Nome do patrimônio
     */
    public Patrimonio(Integer id, String nomePatrimonio) {
        this.id = id;
        this.nomePatrimonio = nomePatrimonio;
    }
    
    /**
     * Cria um patrimônio com um Nome
     * @param nomePatrimonio Nome do patrimônio
     */
    public Patrimonio(String nomePatrimonio){
        this(null, nomePatrimonio);
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
        return nomePatrimonio;
    }

    public void setNome(String nomePatrimonio   ) {
        this.nomePatrimonio = nomePatrimonio;
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
        
            this.status = PatrimonioStatusEnum.valueOf(status);
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

    public PatrimonioTipoEnum getTipo() {
        return tipoPatrimonio;
    }

    public void setTipo(PatrimonioTipoEnum tipoPatrimonio) {
        this.tipoPatrimonio = tipoPatrimonio;
    }
    
    public void setTipo(String tipoPatrimonio){
        this.tipoPatrimonio = PatrimonioTipoEnum.valueOf(tipoPatrimonio);
    }
}



