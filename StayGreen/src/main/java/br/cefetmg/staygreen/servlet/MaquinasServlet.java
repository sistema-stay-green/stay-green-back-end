/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.servlet;

import br.cefetmg.staygreen.service.MaquinasService;
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
import br.cefetmg.staygreen.service.TransacaoEAluguelService;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Gabriel Cruz
 * @version 03-11-18/14:09
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
            
            Patrimonio maquina = JSON.parse(request.getParameter("maquinasJSON"),
                    Patrimonio.class);
            String resposta = "",
                   stringDataCompra = request.getParameter("dataCompra"),
                   stringDataSaida = request.getParameter("dataSaida"),
                   stringDataRetorno = request.getParameter("dataRetorno"),
                   stringDataBaixa = request.getParameter("dataBaixa"),
                   stringQuantidade = request.getParameter("quantidade");
            int quantidade = Integer.parseInt(stringQuantidade);
                       
            switch(request.getParameter("acao")){
                case "c":
                    resposta = MaquinasService.Cadastrar(maquina, quantidade, 
                            TransacaoEAluguelService.
                            converteStringToCalendar(stringDataCompra));
                    break;
                case "v":
                    resposta = MaquinasService.Venda(maquina,TransacaoEAluguelService.
                            converteStringToCalendar(stringDataBaixa));
                    break;
                case "a":
                    resposta = MaquinasService.Aluguel(maquina, request,
                            TransacaoEAluguelService.
                            converteStringToCalendar(stringDataSaida),
                            TransacaoEAluguelService.
                            converteStringToCalendar(stringDataRetorno));
                    break;
                case "d":
                    resposta = MaquinasService.Descarte(maquina, TransacaoEAluguelService.
                            converteStringToCalendar(stringDataBaixa) );
                    break;
                case "m":
                    resposta = MaquinasService.Manuntenir(maquina,
                            TransacaoEAluguelService.
                            converteStringToCalendar(stringDataRetorno));
                    break;

                case "r":      
                    ArrayList<Patrimonio> maquinas =PatrimonioAccessService.
                            get("WHERE tipoPatrimonio = 'MAQUINA'");
                    resposta = JSON.stringify(maquinas);              
                    break;
                case "e":
                    maquina.setDataCompra
                            (TransacaoEAluguelService.
                            converteStringToCalendar(stringDataCompra)); 
                    resposta = MaquinasService.Editar(maquina);
                    break;
                default: 
                    throw new IllegalArgumentException("Opção Invalida");
            }
            out.println(resposta);
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
    // </editor-fold>

}
