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
import br.cefetmg.staygreen.service.ControleDeMaquinasUtilService;
import java.util.ArrayList;

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
            String stringDataCompra = request.getParameter("dataCompra"),
                   stringDataSaida = request.getParameter("dataSaida"),
                   stringDataRetorno = request.getParameter("dataRetorno"),
                   stringDataBaixa = request.getParameter("dataBaixa"),
                   stringQuantidade = request.getParameter("quantidade");
            int quantidade = Integer.parseInt(stringQuantidade);
            
            
            if(maquina != null) {
                switch(request.getParameter("acao")){
                    case "c":
                        MaquinasService.Cadastrar(maquina, quantidade, 
                                ControleDeMaquinasUtilService.
                                converteStringToCalendar(stringDataCompra));
                        break;
                    case "v":
                        MaquinasService.Venda(maquina,ControleDeMaquinasUtilService.
                                converteStringToCalendar(stringDataBaixa));
                        break;
                    case "a":
                        MaquinasService.Aluguel(maquina, request,
                                ControleDeMaquinasUtilService.
                                converteStringToCalendar(stringDataSaida));
                        break;
                    case "d":
                        MaquinasService.Descarte(maquina);
                        break;
                    case "m":
                        MaquinasService.Manuntenir(maquina,
                                ControleDeMaquinasUtilService.
                                converteStringToCalendar(stringDataRetorno));
                        break;
                    case "r":
                        ArrayList<Patrimonio> maquinas =PatrimonioAccessService.
                                get(null);
                        break;
                    case "e":
                        MaquinasService.Editar(maquina, JSON.parse(request.
                                getParameter("maquinasAtualizar"),
                                Patrimonio.class));
                        break;
                    default: 
                        throw new IllegalArgumentException("Opção Invalida");
                }
            } 
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
