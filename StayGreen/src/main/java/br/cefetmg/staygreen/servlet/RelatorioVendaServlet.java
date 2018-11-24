/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.servlet;

import br.cefetmg.staygreen.table.Produto;
import br.cefetmg.staygreen.table.Transacao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.cefetmg.staygreen.util.SQL;
import br.cefetmg.staygreen.util.JSON;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.cefetmg.staygreen.util.Data;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Servlet que faz o relatório das vendas
 * @author João Pedro
 * @version 1.0
 */
@WebServlet(name = "RelatorioVendaServlet", urlPatterns = {"/RelatorioVendaServlet"})
public class RelatorioVendaServlet extends HttpServlet {

    private class RelatorioVendas{
        private final Double valor;
        private final String nome;
        private final Integer dia;
        private final Integer mes;
        private final Integer ano;

        public RelatorioVendas(Double valor, String nome, Integer dia, Integer mes, Integer ano) {
            this.valor = valor;
            this.nome = nome;
            this.dia = dia;
            this.mes = mes;
            this.ano = ano;
        }
   }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        List<RelatorioVendas> relatorio = new LinkedList();
        
        ResultSet idTransacaoRS = SQL.query("SELECT idTransacao FROM VendaUsuario");
        Integer idTransacao = 0;
        while(idTransacaoRS.next()){
            idTransacao = idTransacaoRS.getInt("idTransacao");
            ResultSet valorTransacaoRS = SQL.query("SELECT `valorTransacao` FROM `" 
                    + SQL.getNomeTabela(Transacao.class) +"` WHERE `idTransacao`= " 
                    + idTransacao);
            Double valorTransacao;

            while(valorTransacaoRS.next()){
                valorTransacao = valorTransacaoRS.getDouble("valorTransacao");
                ResultSet dataTransacaoRS = SQL.query("SELECT dataTransacao FROM `" 
                    + SQL.getNomeTabela(Transacao.class) +"` WHERE `idTransacao`= " + idTransacao);
                Calendar dateTransacao;

                while(dataTransacaoRS.next()){
                    dateTransacao = Data.dateToCalendar(dataTransacaoRS.getDate("dataTransacao"));
                    ResultSet idItemTransacaoRS = SQL.query("SELECT idItemTransacao FROM `"
                            + SQL.getNomeTabela(Transacao.class) + "` WHERE `idTransacao`= "
                            + idTransacao);
                    Integer idItemTransacao;

                    while(idItemTransacaoRS.next()){
                        idItemTransacao = idItemTransacaoRS.getInt("idItemTransacao");
                        ResultSet nomeProdutoRS = SQL.query("SELECT nomeProduto FROM `"
                                + SQL.getNomeTabela(Produto.class) +"` WHERE idProduto = "
                                + idItemTransacao);
                        String nomeProduto;

                        while(nomeProdutoRS.next()){
                            nomeProduto = nomeProdutoRS.getString("nomeProduto");

                            relatorio.add( new RelatorioVendas(valorTransacao,
                                    nomeProduto, dateTransacao.get(Calendar.DATE),
                                    dateTransacao.get(Calendar.MONTH),
                                    dateTransacao.get(Calendar.YEAR)) );
                                                   
                            
                        }
                    }
                }
            }
        }
        String relatorioJSON = JSON.stringify(relatorio);
        try (PrintWriter out = response.getWriter()) {
                                out.print(relatorioJSON);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioVendaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioVendaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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

