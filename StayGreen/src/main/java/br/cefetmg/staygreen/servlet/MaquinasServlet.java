/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.servlet;

import br.cefetmg.staygreen.service.AluguelAccessService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.cefetmg.staygreen.util.JSON;
import br.cefetmg.staygreen.table.Patrimonio;
import br.cefetmg.staygreen.service.PatrimonioAccessService;
import br.cefetmg.staygreen.service.TransacaoAccessService;
import static br.cefetmg.staygreen.service.TransacaoEAluguelService.
        calculaValorAtual;
import br.cefetmg.staygreen.table.Aluguel;
import br.cefetmg.staygreen.table.PatrimonioStatusEnum;
import br.cefetmg.staygreen.table.TipoTransacao;
import br.cefetmg.staygreen.table.Transacao;
import java.util.Calendar;
/**
 *
 * @author Aluno
 */
@WebServlet(name = "MaquinasServlet", urlPatterns = {"/MaquinasServlet"})
public class MaquinasServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()){
            String resposta = "";
            Patrimonio maquina = JSON.parse(request.getParameter("maquinaJSON"),
                    Patrimonio.class);   
/*          Patrimonio maquina = new Patrimonio(21,"Tratorzinho",
                    "Trator para as crianças aprenderem a profissão","EM_POSSE",
                    10.0, 10000.0, Calendar.getInstance());// Dados recebidos */
            if(maquina != null) {
                switch(request.getParameter("acao")){
                    case "c":
                        Calendar dataCompra= Calendar.getInstance();
                        maquina.setDataCompra(dataCompra);
                        Transacao compra = new Transacao(maquina.getId().
                                longValue(), maquina.getId().longValue(),
                                calculaValorAtual(maquina.getDataCompra(),
                                maquina.getIndiceDepreciacao(),
                                maquina.getValorCompra()), 1, dataCompra,
                                TipoTransacao.MAQUINA);
                        maquina.setStatus("EM_POSSE");
                        TransacaoAccessService.insert(compra);
                        PatrimonioAccessService.insert(maquina);
                        break;
                    case "v":
                        if(maquina.getStatus() == PatrimonioStatusEnum.VENDIDO){
                            System.out.println("Maquina já vendida");
                        }
                        else {
                            Calendar dataBaixa = Calendar.getInstance();
                            Transacao venda = new Transacao(maquina.getId().
                                    longValue(), maquina.getId().longValue(),
                                    calculaValorAtual(maquina.getDataCompra(),
                                    maquina.getIndiceDepreciacao(),
                                    maquina.getValorCompra()), 1, dataBaixa,
                                    TipoTransacao.MAQUINA);
                            maquina.setDataBaixa(dataBaixa);
                            maquina.setStatus("VENDIDO");
                            TransacaoAccessService.insert(venda);
                            PatrimonioAccessService.update(maquina);
                        }
                        break;
                    case "a":
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
                            maquina.setStatus("VENDIDO");
                            AluguelAccessService.insert(aluguel);
                            PatrimonioAccessService.update(maquina);
                        }
                        break;
                    case "d":
                        if(maquina.getStatus() == PatrimonioStatusEnum.DESCARTADO){
                            System.out.println("Maquina tinha sido descartada");
                        }
                        else {
                            PatrimonioAccessService.delete(maquina);
                        }
                        break;
                }
            } 
            
            PatrimonioAccessService.insert(maquina);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods.Click  on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        response = fixHeader(response);
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        response = fixHeader(response);
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    public HttpServletResponse fixHeader(HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, PUT, POST, OPT"
                + "IONS, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Max-Age", "86400");
        return response;
    }
    // </editor-fold>

}
