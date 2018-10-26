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
 * @author Mei
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
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String resposta = "";
        Patrimonio patrimonio = JSON.parse(request.getParameter("patrimonio"), Patrimonio.class);
        
        if(patrimonio != null) {
                switch(request.getParameter("action")){
                    case "v"://Caso de venda
                        if(PatrimonioProcessService.vendaPatrimonio(request.getParameter("id")))
                            resposta = "O patrimÃ´nio [" + request.getParameter("id") + "] foi vendido pelo valor de R$ " + 
                                    PatrimonioProcessService.desvalorizaPatrimonio(request.getParameter("id"));
                            else
                                resposta = "O patrimonio [" + request.getParameter("id") + "] jÃ¡ foi vendido.";       
                        break;
                    case "c"://Caso de compra
                        PatrimonioProcessService.compraPatrimonio(patrimonio.getNome(), patrimonio.getTipo(), patrimonio.getFinalidade(),
                                patrimonio.getIndiceDepreciacao(), patrimonio.getValorCompra(), patrimonio.getDataCompra());
                        resposta = "Patrimonio comprado com sucesso.";
                        break;
                    case "s"://Caso de saÃ­da
                        /*if(request.getParameter("tipoSaida") == )
                        */
                        break;
                    case "e"://Caso de entrada
                        break;
                    default://Caso base
                        break;
                        
                }
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
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
