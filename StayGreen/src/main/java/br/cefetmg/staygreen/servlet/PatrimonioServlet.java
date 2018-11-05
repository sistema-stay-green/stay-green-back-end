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
import br.cefetmg.staygreen.util.SQL;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        ArrayList<Patrimonio> patrimonios;
        
            switch(request.getParameter("action")){
                
                //Valores iniciais do retorno: S -> Sem problemas
                //                             R -> Comando redundante
                //                             I -> Comando ilegal
                //                             E -> Patrimonio(s) encontrado(s)
                //                             N -> Nenhum patrimonio encontrado/não recebido
                
                case "c": //Caso de compra

                    patrimonio = JSON.parse(request.getParameter("patrimonio"), Patrimonio.class);
                    String dataCompraString =  request.getParameter("dataCompra");
                    DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                    Date dataCompraDate;
                    Calendar currentTime = Calendar.getInstance();
                     try {
                        dataCompraDate = (Date)format.parse(dataCompraString);
                        currentTime.setTime(dataCompraDate);
                    } catch (ParseException ex) {
                        System.out.println("Exception "+ex+" at parse Date");
                    }
                    
                    

                    PatrimonioProcessService.compraPatrimonio(
                            patrimonio.getNome(), patrimonio.getTipo(),
                            patrimonio.getFinalidade(), patrimonio.getIndiceDepreciacao(),
                            patrimonio.getValorCompra(), currentTime
                    );
                    
                    patrimonio = PatrimonioAccessService.getLastInsertedPatrimonio();
                    
                    if(patrimonio != null)
                        resposta = JSON.stringify(patrimonio);
                            
                    
                    break;

                case "s": //Caso de saída

                    switch(request.getParameter("tipoSaida")){

                        case "ALUGADO":
                            PatrimonioProcessService.alugaPatrimonio(request.getParameter("id"));
                            break;

                        case "EM_MANUTENCAO":
                            PatrimonioProcessService.colocaEmManutencao(request.getParameter("id"));
                            break;
                        
                        case "VENDA": //Caso de venda
                            PatrimonioProcessService.vendaPatrimonio(request.getParameter("id"));
                            break;

                        default:
                            throw new IllegalArgumentException("Parametro 'tipoSaida' possui um valor inválido.");
                    }

                case "e": //Caso de entrada
                    PatrimonioProcessService.recebePatrimonio(request.getParameter("id"));
                    break;

                case "p": //Caso de pesquisa

                    switch(request.getParameter("pesquisarPor")){

                        case "id":
                            patrimonios = PatrimonioAccessService.getPatrimoniosByNome(request.getParameter("id"));
                            
                            if (patrimonios != null) {
                                // Caso Stringfy não funcione para ArrayList:
                                for (Patrimonio currentPatrimonio : patrimonios) {
                                    resposta += JSON.stringify(currentPatrimonio);
                                }
                                
                                // Caso funcione:
                                //resposta = JSON.stringify(patrimonios);
                            } 
                            break;

                        case "nome":
                            
                            patrimonios = PatrimonioAccessService.getPatrimoniosByNome(request.getParameter("name"));
                            
                            if (patrimonios != null) {                               
                                // Caso Stringfy não funcione para ArrayList:
                                for (Patrimonio currentPatrimonio : patrimonios) {
                                    resposta += JSON.stringify(currentPatrimonio);
                                }
                                
                                // Caso funcione:
                                //resposta = JSON.stringify(patrimonios);
                            }
                            break;

                        default:
                            throw new IllegalArgumentException("Parametro 'pesquisarPor' possui um valor inválido.");
                    }
                    break;

                case "r": //Caso de retorno de todos os patrimonios
                    patrimonios = PatrimonioAccessService.get("");
                            
                    if (patrimonios != null) {

                        for (Patrimonio currentPatrimonio : patrimonios) {
                            resposta += JSON.stringify(currentPatrimonio);
                        }
                                
                        // Caso funcione:
                        //resposta = JSON.stringify(patrimonios);
                    }
                    break;
                    
                case "u": 
                    patrimonio = JSON.parse(request.getParameter("patrimonio"), Patrimonio.class);
                    if(patrimonio != null){
                        PatrimonioAccessService.update(patrimonio);
                    }
                    break;
                    
                case "d":
                    patrimonio = JSON.parse(request.getParameter("patrimonio"), Patrimonio.class);
                    if(patrimonio != null){
                        PatrimonioAccessService.delete(patrimonio);
                    }
                    
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
