/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.cefetmg.staygreen.table.Cliente;
import br.cefetmg.staygreen.util.SQL;
import br.cefetmg.staygreen.table.Venda;
import br.cefetmg.staygreen.util.JSON;

/**
 * Servlet que recebe informações via POST e envia para o BD
 * @version 1.0
 * @author João Pedro
 */
@WebServlet(name = "DadosVendasServlet", urlPatterns = {"/DadosVendasServlet"})
public class DadosVendasServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String vendaJSON = request.getParameter("venda");
        String clienteJSON = request.getParameter("cliente");
        
        Venda venda = JSON.parse(vendaJSON, Venda.class);
        Cliente cliente = JSON.parse(clienteJSON, Cliente.class);
        
        SQL.insert(cliente);
        SQL.insert(venda);
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
