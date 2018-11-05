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
            String resposta = "";
            Patrimonio maquina = JSON.parse(request.getParameter("maquinaJSON"),
                    Patrimonio.class);   
            int quantidade=Integer.parseInt(request.
                                getParameter("quantAluguel"));
/*          Patrimonio maquina = new Patrimonio(21,"Tratorzinho",
                    "Trator para as crianças aprenderem a profissão","EM_POSSE",
                    10.0, 10000.0, Calendar.getInstance());// Dados recebidos */
            if(maquina != null) {
                switch(request.getParameter("acao")){
                    case "c":
                        MaquinasService.Compra(maquina, quantidade);
                        break;
                    case "v":
                        MaquinasService.Venda(maquina, quantidade);
                        break;
                    case "a":
                        MaquinasService.Aluguel(maquina, request);
                        break;
                    case "d":
                        MaquinasService.Descarte(maquina);
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
