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
import java.sql.ResultSet;
import java.sql.SQLException;
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
        ArrayList<Patrimonio> patrimonios;
        
            switch(request.getParameter("action")){
                
                //Valores iniciais do retorno: S -> Sem problemas
                //                             R -> Comando redundante
                //                             I -> Comando ilegal
                //                             E -> Patrimonio(s) encontrado(s)
                //                             N -> Nenhum patrimonio encontrado/não recebido
                
                case "c": //Caso de compra

                    patrimonio = JSON.parse(request.getParameter("patrimonio"), Patrimonio.class);
                    Calendar currentTime = Calendar.getInstance();

                    PatrimonioProcessService.compraPatrimonio(
                            patrimonio.getNome(), patrimonio.getTipo(),
                            patrimonio.getFinalidade(), patrimonio.getIndiceDepreciacao(),
                            patrimonio.getValorCompra(), currentTime
                    );
                    
                    try{
                    ResultSet lastId = SQL.query("SELECT LAST_INSERT_ID()");
                    if(lastId.next()){
                        Patrimonio patrimonioReturn = PatrimonioAccessService.getPatrimonioById(
                                Integer.toString(lastId.getInt("LAST_INSERT_ID")));
                        resposta = JSON.stringify(patrimonioReturn);
                    }
                    }catch(SQLException ex){
                        System.out.println(ex + " at case Compra");
                    }
                    break;

                case "s": //Caso de saída

                    switch(request.getParameter("tipoSaida")){

                        case "ALUGADO":
                            if(PatrimonioProcessService.alugaPatrimonio(request.getParameter("id")))
                                resposta = "S";
                                else
                                    resposta = "I";
                            break;

                        case "EM_MANUTENCAO":
                            if(PatrimonioProcessService.colocaEmManutencao(request.getParameter("id")))
                                resposta = "S";
                                else
                                    resposta = "I";
                            break;
                        
                        case "VENDA": //Caso de venda

                            if(PatrimonioProcessService.vendaPatrimonio(request.getParameter("id")))
                                resposta = "S";
                                else
                                    resposta = "R";       
                            break;

                        default:
                            throw new IllegalArgumentException("Parametro 'tipoSaida' possui um valor inválido.");
                    }

                case "e": //Caso de entrada
                    if(PatrimonioProcessService.recebePatrimonio(request.getParameter("id")))
                        resposta = "S";
                        else
                            resposta = "I";
                    break;

                case "p": //Caso de pesquisa

                    switch(request.getParameter("pesquisarPor")){

                        case "id":
                            patrimonios = PatrimonioAccessService.getPatrimoniosByNome(request.getParameter("id"));
                            
                            if (patrimonios != null) {
                                
                                // Caso o patrimonio seja diferente de null insere o valor E (consultar tabela no inicio do switch de casos)
                                resposta += "E";
                                
                                // Caso Stringfy não funcione para ArrayList:
                                for (Patrimonio currentPatrimonio : patrimonios) {
                                    resposta += JSON.stringify(currentPatrimonio);
                                }
                                
                                // Caso funcione:
                                //resposta = JSON.stringify(patrimonios);
                            } else
                                resposta = "N";
                            break;

                        case "nome":
                            
                            patrimonios = PatrimonioAccessService.getPatrimoniosByNome(request.getParameter("name"));
                            
                            if (patrimonios != null) {
                                
                                // Caso o patrimonio seja diferente de null insere o valor E (consultar tabela no inicio do switch de casos)
                                resposta += "E";
                                
                                // Caso Stringfy não funcione para ArrayList:
                                for (Patrimonio currentPatrimonio : patrimonios) {
                                    resposta += JSON.stringify(currentPatrimonio);
                                }
                                
                                // Caso funcione:
                                //resposta = JSON.stringify(patrimonios);
                            } else
                                resposta = "N";
                            break;

                        default:
                            throw new IllegalArgumentException("Parametro 'pesquisarPor' possui um valor inválido.");
                    }
                    break;

                case "r": //Caso de retorno de todos os patrimonios
                    patrimonios = PatrimonioAccessService.get("");
                            
                        if (patrimonios != null) {
                            // Caso Stringfy não funcione para ArrayList:
                            resposta += "E";
                            
                            for (Patrimonio currentPatrimonio : patrimonios) {
                                resposta += JSON.stringify(currentPatrimonio);
                            }
                                
                                // Caso funcione:
                                //resposta = JSON.stringify(patrimonios);
                        } else
                            resposta = "N";
                    break;
                    
                case "u": 
                    patrimonio = JSON.parse(request.getParameter("patrimonio"), Patrimonio.class);
                    if(patrimonio != null){
                        
                        PatrimonioAccessService.update(patrimonio);
                        resposta = "S";
                        }else
                            resposta = "N";
                    break;
                    
                case "d":
                    patrimonio = JSON.parse(request.getParameter("patrimonio"), Patrimonio.class);
                    if(patrimonio != null){
                        PatrimonioAccessService.delete(patrimonio);
                        resposta = "S";
                        }else
                            resposta = "N";
                    
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
