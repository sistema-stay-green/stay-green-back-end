/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Aluguel;
import br.cefetmg.staygreen.table.PatrimonioStatusEnum;
import br.cefetmg.staygreen.table.TipoTransacao;
import br.cefetmg.staygreen.table.Transacao
import br.cefetmg.staygreen.table.Patrimonio;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

/**
 * @author Gabriel Cruz
 * @version 05-11-18/07:12
 */

public class MaquinasService {
    public static void Compra(Patrimonio maquina, int quantidade){
        Calendar dataCompra= Calendar.getInstance();
        maquina.setDataCompra(dataCompra);
        if(quantidade==0){    
            Transacao compra = new Transacao(maquina.getId().longValue(),
                    maquina.getId().longValue(),TransacaoEAluguelService.
                    calculaValorAtual(maquina. getDataCompra(),maquina.
                    getIndiceDepreciacao(), maquina.getValorCompra()), 1, 
                    dataCompra,TipoTransacao.MAQUINA);
            TransacaoAccessService.insert(compra);
        }
        else{
            Transacao compra = new Transacao(maquina.getId().longValue(),
                    maquina.getId().longValue(),TransacaoEAluguelService.
                            calculaValorAtual(maquina.
                    getDataCompra(),maquina.getIndiceDepreciacao(), maquina. 
                    getValorCompra()), quantidade, dataCompra,TipoTransacao.
                    MAQUINA);
            TransacaoAccessService.insert(compra);
        } 
        maquina.setStatus("EM_POSSE");
        PatrimonioAccessService.insert(maquina);
    }
    
    public static void Venda(Patrimonio maquina, int quantidade){
        if(maquina.getStatus() == PatrimonioStatusEnum.VENDIDO){
            System.out.println("Maquina já vendida");
        }
        else {
            Calendar dataBaixa = Calendar.getInstance();
            if(quantidade==0){    
                Transacao venda = new Transacao(maquina.getId().longValue(),
                        maquina.getId().longValue(),TransacaoEAluguelService.
                        calculaValorAtual(maquina.getDataCompra(),maquina.
                        getIndiceDepreciacao(), maquina.getValorCompra()), 1, 
                        dataBaixa,TipoTransacao.MAQUINA);
                TransacaoAccessService.insert(venda);
            }
            else{
                Transacao venda = new Transacao(maquina.getId().longValue(),
                        maquina.getId().longValue(),TransacaoEAluguelService.
                        calculaValorAtual(maquina.getDataCompra(),maquina.
                        getIndiceDepreciacao(), maquina.getValorCompra()), 
                        quantidade, dataBaixa,TipoTransacao.MAQUINA);
                TransacaoAccessService.insert(venda);
            }    
            maquina.setDataBaixa(dataBaixa);
            maquina.setStatus("VENDIDO");
            PatrimonioAccessService.update(maquina);
        }
    }
    
    public static void Aluguel(Patrimonio maquina, HttpServletRequest request){
        if(maquina.getStatus() == PatrimonioStatusEnum.ALUGADO){
            System.out.println("Maquina já foi alugada");
        }
        else {
            Calendar dataSaida = Calendar.getInstance();
            Aluguel aluguel = new Aluguel(maquina.getId().
                    longValue(), maquina.getId().longValue(),
                    Double.parseDouble(request.
                    getParameter("valorAluguel")),
                    Integer.parseInt(request.
                    getParameter("periodoAluguel")),dataSaida);
            maquina.setDataSaida(dataSaida);
            maquina.setStatus("ALUGADO");
            AluguelAccessService.insert(aluguel);
            PatrimonioAccessService.update(maquina);
        }
    }
    public static void Descarte(Patrimonio maquina){
        if(maquina.getStatus() == PatrimonioStatusEnum.DESCARTADO){
            System.out.println("Maquina tinha sido descartada");
        }
        else {
            PatrimonioAccessService.delete(maquina);
        }
    }
}
