/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.servlet;

import br.cefetmg.staygreen.service.PatrimonioProcessService;
import br.cefetmg.staygreen.table.Patrimonio;
import br.cefetmg.staygreen.util.JSON;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mei Fagundes, Samuel Simonetti
 * @version 20-11-18/14:37
 */
@WebServlet(name = "PatrimonioServlet", urlPatterns = {"/PatrimonioServlet"})
public class PatrimonioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @author Samuel Simonetti, Mei Fagundes
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try(PrintWriter out = response.getWriter()) {
            
            String resposta = "";
        
            switch(request.getParameter("action")){
                
                // Valores iniciais do retorno: S -> Succeded
                //                              F -> Failed
                //                              N -> Not found
                
                case "c": // Caso de compra.
                    resposta = PatrimonioProcessService.compraPatrimonio(JSON.parse(request.getParameter("patrimonio"), Patrimonio.class));
                    break;

                case "s": // Caso de pesquisa.
                    resposta = PatrimonioProcessService.pesquisaPatrimonio(request.getParameter("s"), request.getParameter(request.getParameter("s")));
                    break;

                case "r": // Caso de retorno de todos os patrimonios.

                    resposta = PatrimonioProcessService.retornaTodosPatrimonio();
                    break;
                    
                case "u": // Caso de update de um patrimonio existente.
                    resposta = PatrimonioProcessService.atualizaPatrimonio(JSON.parse(request.getParameter("patrimonio"), Patrimonio.class));
                    break;
                    
                case "d": // Caso de remoção.
                    resposta = PatrimonioProcessService.deletaPatrimonio(request.getParameter("id"));
                    break;

                default: // Caso não existente.
                    response.sendError(500);
            }
            out.println(resposta);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
    }// </editor-fold>

}
