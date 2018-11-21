/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Aluguel;
import br.cefetmg.staygreen.table.PatrimonioStatusEnum;
import br.cefetmg.staygreen.table.TipoTransacaoEnum;
import br.cefetmg.staygreen.table.Transacao;
import br.cefetmg.staygreen.table.Patrimonio;
import br.cefetmg.staygreen.util.JSON;
import br.cefetmg.staygreen.util.SQL;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

/**
 * @author Gabriel Cruz
 * @version 05-11-18/07:12
 */

public class MaquinasService {

    /**
     * Usado ao comprar uma maquina. 
     * Muda o Estado da maquina para EM_POSSE e adiciona uma nova transação à 
     * tabela transação
     * @param maquina
     * @param quantidade
     * @param dataCompra
     * @return 
     */
    public static String Cadastrar(Patrimonio maquina, int quantidade, 
            Calendar dataCompra){
        try{
            maquina.setStatus("EM_POSSE");
            maquina.setDataCompra(dataCompra);
            PatrimonioAccessService.insert(maquina);
            maquina.setId(SQL.getLastInsertId());
            Transacao compra = new Transacao(null,
                    maquina.getId().longValue(),TransacaoEAluguelService.
                    calculaValorAtual(dataCompra,maquina.
                    getIndiceDepreciacao(),maquina.getValorCompra()),quantidade, 
                    dataCompra,TipoTransacaoEnum.MAQUINA);
            System.out.println(compra.toString());
            TransacaoAccessService.insert(compra);
            return JSON.stringify(maquina);
        }
        catch(Exception ex){
            return "ERRO: "+ex;
        }
    }
    
    /**
     * Usado ao vender uma maquina. 
     * Muda o Estado da maquina para VENDIDO e adiciona uma nova transação à 
     * tabela transação
     * @param maquina
     * @param dataBaixa
     * @return 
     */
    public static String Venda(Patrimonio maquina, Calendar dataBaixa){
        if(maquina.getStatus() == PatrimonioStatusEnum.VENDIDO){
            return "Maquina já vendida";
        }
        else {
            try{
                Transacao venda = new Transacao(null,
                        maquina.getId().longValue(),TransacaoEAluguelService.
                        calculaValorAtual(maquina.getDataCompra(),maquina.
                        getIndiceDepreciacao(), maquina.getValorCompra()), 1, 
                        dataBaixa,TipoTransacaoEnum.MAQUINA);
                TransacaoAccessService.insert(venda);
                maquina.setDataBaixa(dataBaixa);
                maquina.setStatus(PatrimonioStatusEnum.VENDIDO);
                PatrimonioAccessService.update(maquina);
                return JSON.stringify(maquina);
            }
            catch(Exception ex){
                return "ERRO: "+ex;
            }
        }
    }
    
    /**
     * Usado ao alugar uma maquina. 
     * Muda o Estado da maquina para ALUGADO e adiciona um novo aluguel à 
     * tabela aluguel
     * @param maquina
     * @param request
     * @param dataSaida
     * @return 
     */
    public static String Aluguel(Patrimonio maquina, HttpServletRequest request,
            Calendar dataSaida){
        if(maquina.getStatus() == PatrimonioStatusEnum.ALUGADO){
            return "Maquina já foi alugada";
        }
        else {
            try{
                Aluguel aluguel = new Aluguel(null, maquina.getId().longValue(),
                        Double.parseDouble(request.
                        getParameter("valorAluguel")), Integer.parseInt(request.
                        getParameter("periodoAluguel")), dataSaida);
                maquina.setDataSaida(dataSaida);
                maquina.setStatus(PatrimonioStatusEnum.ALUGADO);
                AluguelAccessService.insert(aluguel);
                PatrimonioAccessService.update(maquina);
                ResultSet lastId = SQL.query("SELECT LAST_INSERT_ID()");
                
                if(lastId.next()){
                    maquina.setId(lastId.getInt("LAST_INSERT_ID()"));
                }
                return JSON.stringify(maquina);
            }
            catch(Exception ex){
                return "ERRO: "+ex;
            }
            
        }
    }

    /**
     * Usado ao descartar uma maquina. 
     * Muda o Estado da maquina para DESCARTADO
     * @param maquina
     * @return 
     */
    public static String Descarte(Patrimonio maquina){
        if(maquina.getStatus() == PatrimonioStatusEnum.DESCARTADO){
            return "Maquina já foi descartada";
        }
        else {
            try{
                PatrimonioAccessService.delete(maquina);
                return JSON.stringify(maquina);
            }
            catch(Exception ex){
                return "ERRO: "+ex;
            }
        }
    }
    
    /**
     * Usado ao manutenir uma maquina. 
     * Muda o Estado da maquina para EM_MANUTENCAO
     * @param maquina
     * @param dataRetorno
     * @return 
     */
    public static String Manuntenir(Patrimonio maquina, Calendar dataRetorno){
        if(maquina.getStatus() == PatrimonioStatusEnum.EM_MANUTENCAO){
            return "Maquina já está em manutenção";
        }
        else {
            try{
                maquina.setStatus(PatrimonioStatusEnum.EM_MANUTENCAO);
                maquina.setDataRetorno(dataRetorno);
                return JSON.stringify(maquina);
            }
            catch(Exception ex){
                return "ERRO: "+ex;
            }
        }
    }
    /**
     * Usado ao atualizar uma maquina de uma forma que não foi descrita acima. 
     * @param maquina
     * @param maquinaAtualizada
     * @return 
     */
    public static String Editar(Patrimonio maquina){
        try{
            System.out.println(maquina.getValorCompra());
            PatrimonioAccessService.update(maquina);
            return JSON.stringify(maquina);
        }
        catch(Exception ex){
            return "ERRO: "+ex;
        }
    }
}
