/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.servlet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.cefetmg.staygreen.table.Transacao;
import br.cefetmg.staygreen.util.SQL;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Guilherme da Silva
 */
@WebServlet(name = "RelatorioResultadoServlet", urlPatterns = {"/RelatorioResultadoServlet"})
public class RelatorioResultadoServlet extends HttpServlet {
    
    private static ResultSet result;
    private static final String TABLE_NAME = SQL.getNomeTabela(Transacao.class);
            
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            ArrayList vendasECompras = new ArrayList<>();
            double vendas = 0, compras = 0, aux = 0;
            
            result = SQL.query("SELECT * FROM `" + TABLE_NAME + "` WHERE `valorTransacao` != 0");

            try{
                if(result.isBeforeFirst()){
                    while(result.next()){
                        vendasECompras.add(result.getDouble("valorTransacao"));
                    }
                }else
                    System.out.println("ERRO - Tabela vazia");
            }catch(SQLException e){
                System.out.println(e + " at getRowFromId");
            }
            
            //compras são valores negativos
            for (int i = 0; i < vendasECompras.size(); i++) {
                aux = (double)(vendasECompras.get(i));
                if(aux > 0){
                    vendas += aux;
                }else{
                    compras += aux;
                }   
            }
            
            double total = vendas+compras;
            
            out.println(total);
        }
    }

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
