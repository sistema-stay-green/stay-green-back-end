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
    private Integer idPatrimonio;
    private String nomePatrimonio;
    private PatrimonioTipoEnum tipoPatrimonio;
    private String finalidadePatrimonio;
    private PatrimonioStatusEnum statusPatrimonio;
    private Double indDeprecPatrimonio;
    private Double valorCompraPatrimonio;
    private Calendar dataCompraPatrimonio;
    private Calendar dataSaidaPatrimonio;
    private Calendar dataRetornoPatrimonio;
    private Calendar dataBaixaPatrimonio;
    
    // Constructors
     
    /**
     *  Cria um patrimônio com um Id e Nome 
     * @param idPatrimonio Id único da linha na tabela
     * @param nomePatrimonio Nome do patrimônio
     */
    public Patrimonio(Integer idPatrimonio, String nomePatrimonio) {
        this.idPatrimonio = idPatrimonio;
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
    
    public void embedPatrimonioData(PatrimonioData patrimonioData){
        
        if(patrimonioData.getDataBaixa() != null)
            this.dataBaixaPatrimonio = patrimonioData.getDataBaixa();
        
        if(patrimonioData.getDataCompra() != null)
            this.dataCompraPatrimonio = patrimonioData.getDataCompra();
        
        if(patrimonioData.getDataRetorno() != null)
            this.dataRetornoPatrimonio = patrimonioData.getDataRetorno();
        
        if(patrimonioData.getDataSaida() != null)
            this.dataSaidaPatrimonio = patrimonioData.getDataSaida();
    }
    
    // Getter and Setter
    public Integer getId() {
        return idPatrimonio;
    }

    public void setId(Integer idPatrimonio) {
        this.idPatrimonio = idPatrimonio;
    }

    public String getNome() {
        return nomePatrimonio;
    }

    public void setNome(String nomePatrimonio   ) {
        this.nomePatrimonio = nomePatrimonio;
    }

    public String getFinalidade() {
        return finalidadePatrimonio;
    }

    public void setFinalidade(String finalidadePatrimonio) {
        this.finalidadePatrimonio = finalidadePatrimonio;
    }

    public Enum getStatus() {
        return statusPatrimonio;
    }

    public void setStatus(PatrimonioStatusEnum statusPatrimonio) {
        this.statusPatrimonio = statusPatrimonio;
    }
    
    public void setStatus(String statusPatrimonio){
        this.statusPatrimonio = PatrimonioStatusEnum.valueOf(statusPatrimonio);
    }

    public Double getIndiceDepreciacao() {
        return indDeprecPatrimonio;
    }

    public void setIndiceDepreciacao(Double indDeprecPatrimonio) {
        this.indDeprecPatrimonio = indDeprecPatrimonio;
    }

    public Double getValorCompra() {
        return valorCompraPatrimonio;
    }

    public void setValorCompra(Double valorCompraPatrimonio) {
        this.valorCompraPatrimonio = valorCompraPatrimonio;
    }

    public Calendar getDataCompra() {
        return dataCompraPatrimonio;
    }

    public void setDataCompra(Calendar dataCompraPatrimonio) {
        this.dataCompraPatrimonio = dataCompraPatrimonio;
    }
    
    public void setDataCompra(Date dataCompraPatrimonio) {
        this.dataCompraPatrimonio = Data.dateToCalendar(dataCompraPatrimonio);
    }

    public Calendar getDataSaida() {
        return dataSaidaPatrimonio;
    }

    public void setDataSaida(Calendar dataSaidaPatrimonio) {
        this.dataSaidaPatrimonio = dataSaidaPatrimonio;
    }
    
    public void setDataSaida(Date dataSaidaPatrimonio) {
        this.dataSaidaPatrimonio = Data.dateToCalendar(dataSaidaPatrimonio);
    }
    
    public Calendar getDataRetorno() {
        return dataRetornoPatrimonio;
    }
    
    public void setDataRetorno(Calendar dataRetornoPatrimonio) {
        this.dataRetornoPatrimonio = dataRetornoPatrimonio;
    }
    
    public void setDataRetorno(Date dataRetornoPatrimonio) {
        this.dataRetornoPatrimonio = Data.dateToCalendar(dataRetornoPatrimonio);
    }

    public Calendar getDataBaixa() {
        return dataBaixaPatrimonio;
    }

    public void setDataBaixa(Calendar dataBaixaPatrimonio) {
        this.dataBaixaPatrimonio = dataBaixaPatrimonio;
    }
    
    public void setDataBaixa(Date dataBaixaPatrimonio) {
        this.dataBaixaPatrimonio = Data.dateToCalendar(dataBaixaPatrimonio);
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



