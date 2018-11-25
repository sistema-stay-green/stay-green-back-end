/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.servlet;

import br.cefetmg.staygreen.table.Frete;
import br.cefetmg.staygreen.table.RegioesDoBrasilEnum;
import br.cefetmg.staygreen.util.SQL;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que atualiza o valor dos fretes
 * @author Paulo Vitor
 */
@WebServlet(name = "VendasFretesServlet", urlPatterns = {"/VendasFretesServlet"})
public class VendasFretesServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        
        RegioesDoBrasilEnum regiao = RegioesDoBrasilEnum.valueOf(
                request.getParameter("regiao"));
        String valorString = request.getParameter("valorFrete");
        Double valor = Double.parseDouble(valorString);
        
        List<Frete> fretes = SQL.getRegistros(Frete.class);
        
        fretes.forEach((frete) -> {
            if(regiao.equals(frete.getRegiaoFrete())){
                frete.setPrecoFrete(valor);
                SQL.update(frete);
            }
        });
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
