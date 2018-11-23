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
     * @return o stringfy da maquina, para que o front-end possa utilizar
     */
    public static String Cadastrar(Patrimonio maquina, int quantidade, 
            Calendar dataCompra){
        try{
            maquina.setStatus("EM_POSSE");
            maquina.setDataCompra(dataCompra);
            PatrimonioAccessService.insert(maquina);
            maquina.setId(SQL.getLastInsertId());
            Transacao compra = new Transacao(null, maquina.getId().longValue(),
                    ((-1)*ControleDeMaquinasUtilService.
                    calculaValorAtual(dataCompra,maquina.getIndiceDepreciacao(),
                    maquina.getValorCompra())),quantidade, dataCompra,
                    TipoTransacaoEnum.MAQUINA);
            TransacaoAccessService.insert(compra);
            System.out.println(maquina.getId());
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
     * @return o stringfy da maquina, para que o front-end possa utilizar
     */
    public static String Venda(Patrimonio maquina, Calendar dataBaixa){
            try{
                maquina.setDataBaixa(dataBaixa);
                maquina.setStatus(PatrimonioStatusEnum.VENDIDO);
                PatrimonioAccessService.update(maquina);
                Transacao venda = new Transacao(null,
                        maquina.getId().longValue(),ControleDeMaquinasUtilService.
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
     * @return o stringfy da maquina, para que o front-end possa utilizar
     */
    public static String Aluguel(Patrimonio maquina, HttpServletRequest request,
        Calendar dataSaida, Calendar dataRetorno){
        try{
            
 
            
            maquina.getDataCompra();
            maquina.setDataSaida(dataSaida);
            maquina.setDataRetorno(dataRetorno);
            maquina.setStatus(PatrimonioStatusEnum.ALUGADO);
            
            PatrimonioAccessService.update(maquina);
            maquina = PatrimonioAccessService.getPatrimonioById
                (Integer.toString(maquina.getId()));
            Aluguel aluguel = new Aluguel(null, maquina.getId().longValue(),
                    Double.parseDouble(request.getParameter("valorAluguel")),
                    ControleDeMaquinasUtilService.diasEntre(dataSaida,dataRetorno),
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
     * @return o stringfy da maquina, para que o front-end possa utilizar
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
     * @return o stringfy da maquina, para que o front-end possa utilizar
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
     * @return o stringfy da maquina, para que o front-end possa utilizar
     */
    public static String Editar(Patrimonio maquina){
        try{
            PatrimonioAccessService.update(maquina);
            maquina = PatrimonioAccessService.getPatrimonioById
                (Integer.toString(maquina.getId()));
            return JSON.stringify(maquina);
        }
        catch(Exception ex){
            return "ERRO: "+ex;
        }
    }
}
