/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.servlet;

import br.cefetmg.staygreen.service.TarefaAccessService;
import br.cefetmg.staygreen.table.Tarefa;
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
 * @author Gabriel Brandão
 * @version 1.0
 */
@WebServlet(name = "TarefaBDServlet", urlPatterns = {"/TarefaBDServlet"})
public class TarefaBDServlet extends HttpServlet {

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
        Tarefa tarefa = JSON.parse(request.getParameter("tarefa"), Tarefa.class);
        if(tarefa != null) {
            switch(request.getParameter("operation")) {
                case "r":
                    /*if(TarefaAccessService.remove(tarefa.getId()))
                        resposta = "Tarefa de id " + tarefa.getId() + " removida com sucesso!";
                    else
                        resposta = "Nenhuma tarefa encontrada com o id " + tarefa.getId();*/
                    break;
                case "u":
                    if(TarefaAccessService.update(tarefa))
                        resposta = "Tarefa atualizada com sucesso.";
                    else 
                        resposta = "Tarefa não encontrada.";
                    break;
                case "a":
                    if(TarefaAccessService.insert(tarefa))
                        resposta = "Tarefa inserida com sucesso.";
                    else
                        resposta = "Não foi possível inserir a tarefa.";
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
