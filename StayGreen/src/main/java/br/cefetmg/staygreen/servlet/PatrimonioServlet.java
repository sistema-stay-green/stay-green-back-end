/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.servlet;

import br.cefetmg.staygreen.service.PatrimonioAccessService;
import br.cefetmg.staygreen.service.PatrimonioProcessService;
import br.cefetmg.staygreen.table.Patrimonio;
import br.cefetmg.staygreen.util.JSON;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
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
     * @author Simonetti, Mei
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        String resposta = "";
        Patrimonio patrimonio;
        
            switch(request.getParameter("action")){

                case "v": //Caso de venda

                    if(PatrimonioProcessService.vendaPatrimonio(request.getParameter("id")))
                        resposta = "O patrimonio [" + request.getParameter("id") + "] foi vendido pelo valor de R$ " + 
                                PatrimonioProcessService.desvalorizaPatrimonio(request.getParameter("id"));
                        else
                            resposta = "O patrimonio [" + request.getParameter("id") + "] já foi vendido.";       
                    break;

                case "c": //Caso de compra

                    patrimonio = JSON.parse(request.getParameter("patrimonio"), Patrimonio.class);
                    Calendar currentTime = Calendar.getInstance();

                    PatrimonioProcessService.compraPatrimonio(
                            patrimonio.getNome(), patrimonio.getTipo(),
                            patrimonio.getFinalidade(), patrimonio.getIndiceDepreciacao(),
                            patrimonio.getValorCompra(), currentTime
                    );

                    resposta = "Patrimonio comprado com sucesso.";
                    break;

                case "s": //Caso de saída

                    switch(request.getParameter("tipoSaida")){

                        case "alugado":
                            if(PatrimonioProcessService.alugaPatrimonio(request.getParameter("id")))
                                resposta = "Patrimonio alugado com sucesso.";
                                else
                                    resposta = "Este patrimonio não está em posse do proprietário.";
                            break;

                        case "em_manutencao":
                            if(PatrimonioProcessService.colocaEmManutencao(request.getParameter("id")))
                                resposta = "Patrimonio colocado em manutenção com sucesso.";
                                else
                                    resposta = "Este patrimonio não está em posse do proprietário.";
                            break;

                        default:
                            throw new IllegalArgumentException("Parametro 'tipoSaida' possui um valor inválido.");
                    }

                case "e": //Caso de entrada
                    if(PatrimonioProcessService.recebePatrimonio(request.getParameter("id")))
                        resposta = "Patrimonio recebido com sucesso.";
                        else
                            resposta = "Patrimonio já esta em posse do proprietario.";
                    break;

                case "p": //Caso de pesquisa

                    switch(request.getParameter("pesquisarPor")){

                        case "id":
                            patrimonio = PatrimonioAccessService.getPatrimonioById(request.getParameter("id"));
                            resposta += JSON.stringify(patrimonio);
                            break;

                        case "nome":
                            String name = request.getParameter("nome");
                            ArrayList<Patrimonio> patrimonios = PatrimonioAccessService.getPatrimoniosByNome(name);
                            
                            if (patrimonios != null) {
                                
                                // Caso Stringfy não funcione para ArrayList:
                                for (Patrimonio currentPatrimonio : patrimonios) {
                                    resposta += JSON.stringify(currentPatrimonio);
                                }
                                
                                // Caso funcione:
                                //resposta = JSON.stringify(patrimonios);
                            } else
                                resposta = "Nenhum Patrimonio foi encontrado com o nome " + name + ".";
                            break;

                        default:
                            throw new IllegalArgumentException("Parametro 'pesquisarPor' possui um valor inválido.");
                    }
                    break;

                case "r": //Caso de retorno de todos os patrimonios
                    ArrayList<Patrimonio> patrimonios = PatrimonioAccessService.get("");
                    resposta += JSON.stringify(patrimonios);
                    break;

                default: //Caso base
                    throw new IllegalArgumentException("Parametro 'action' possui um valor inválido.");
            }
        
        try (PrintWriter out = response.getWriter()) {
            
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
