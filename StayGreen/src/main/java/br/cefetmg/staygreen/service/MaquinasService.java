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
import java.util.concurrent.TimeUnit;

/**
 * @author Gabriel Cruz
 * @version 05-11-18/07:12
 */

public class MaquinasService {
    
    
    private static int diasEntre(Calendar dataInicio, Calendar dataFinal){
        long end = dataFinal.getTimeInMillis();
        long start = dataInicio.getTimeInMillis();
        return (int) TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
    }
    
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
            try{
                maquina.setDataBaixa(dataBaixa);
                maquina.setStatus(PatrimonioStatusEnum.VENDIDO);
                PatrimonioAccessService.update(maquina);
                Transacao venda = new Transacao(null,
                        maquina.getId().longValue(),TransacaoEAluguelService.
                        calculaValorAtual(maquina.getDataCompra(),maquina.
                        getIndiceDepreciacao(), maquina.getValorCompra()), 1, 
                        dataBaixa,TipoTransacaoEnum.MAQUINA);
                TransacaoAccessService.insert(venda);
                return JSON.stringify(maquina);
            }
            catch(Exception ex){
                return "ERRO: "+ex;
            }
    }
    
    /**
     * Usado ao alugar uma maquina. 
     * Muda o Estado da maquina para ALUGADO e adiciona um novo aluguel à 
     * tabela aluguel
     * @param maquina
     * @param request
     * @param dataSaida
     * @param dataRetorno
     * @return 
     */
    public static String Aluguel(Patrimonio maquina, HttpServletRequest request,
        Calendar dataSaida, Calendar dataRetorno){
        try{
            
            // Exemplo de como pegar a maquina antiga e editar a dataCompra dela
            Patrimonio maquinaAntiga = PatrimonioAccessService.getPatrimonioById
                (Integer.toString(maquina.getId()));
            maquina.setDataCompra(maquinaAntiga.getDataCompra());
            // Fim do exemplo
            maquina.setDataSaida(dataSaida);
            maquina.setDataRetorno(dataRetorno);
            maquina.setStatus(PatrimonioStatusEnum.ALUGADO);
            
            PatrimonioAccessService.update(maquina);
            Aluguel aluguel = new Aluguel(null, maquina.getId().longValue(),
                    Double.parseDouble(request.getParameter("valorAluguel")),
                    TransacaoEAluguelService.diasEntre(dataSaida,dataRetorno),
                    dataSaida);
            AluguelAccessService.insert(aluguel);
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
    

    /**
     * Usado ao descartar uma maquina. 
     * Muda o Estado da maquina para DESCARTADO
     * @param maquina
     * @return 
     */
    public static String Descarte(Patrimonio maquina, Calendar dataBaixa){
            
            try{
                maquina.setStatus(PatrimonioStatusEnum.DESCARTADO);
                maquina.setDataBaixa(dataBaixa);
                maquina.setDataSaida((Calendar) null);
                maquina.setDataRetorno((Calendar) null);
                PatrimonioAccessService.update(maquina);
                return JSON.stringify(maquina);
            }
            catch(Exception ex){
                return "ERRO: "+ex;
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
            try{
                maquina.setStatus(PatrimonioStatusEnum.EM_MANUTENCAO);
                maquina.setDataRetorno(dataRetorno);
                PatrimonioAccessService.update(maquina);
                return JSON.stringify(maquina);
            }
            catch(Exception ex){
                return "ERRO: "+ex;
            }
        }
    /**
     * Usado ao atualizar uma maquina de uma forma que não foi descrita acima. 
     * @param maquina
     * @return 
     */
    public static String Editar(Patrimonio maquina){
        try{
            PatrimonioAccessService.update(maquina);
            return JSON.stringify(maquina);
        }
        catch(Exception ex){
            return "ERRO: "+ex;
        }
    }
}
