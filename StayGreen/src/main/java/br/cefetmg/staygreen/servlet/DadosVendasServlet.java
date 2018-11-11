/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.servlet;

import java.io.IOException;
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.cefetmg.staygreen.table.Comprador;
import br.cefetmg.staygreen.table.ModosPagamento;
import br.cefetmg.staygreen.util.SQL;
import br.cefetmg.staygreen.table.VendaUsuario;
import br.cefetmg.staygreen.table.TipoTransacao;
import br.cefetmg.staygreen.table.Transacao;
import br.cefetmg.staygreen.util.JSON;
import java.util.Date;
import java.util.Calendar;

/**
 * Servlet que recebe informações via POST e envia para o BD
 * @version 1.0
 * @author João Pedro
 */
@WebServlet(name = "DadosVendasServlet", urlPatterns = {"/DadosVendasServlet"})
public class DadosVendasServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        
        Long idItemTransacao = Long.parseLong(request.getParameter("idItemTransacao"));
        Double valorTransacao = Double.parseDouble(request.getParameter("valorTransacao"));
        Integer quantTransacao = Integer.parseInt(request.getParameter("quantTransacao"));
        Integer dia = Integer.parseInt(request.getParameter("dia"));
        Integer mes = Integer.parseInt(request.getParameter("mes"));
        Integer ano = Integer.parseInt(request.getParameter("ano"));
        TipoTransacao tipoTransacao = TipoTransacao.PRODUTO;
        
        Calendar dataTransacao = Calendar.getInstance();
        dataTransacao.set(ano, mes, dia);
        
        Double freteVenda = Double.parseDouble(request.getParameter("freteVenda"));
        Integer tempoEntregaVenda = Integer.parseInt(request.getParameter("tempoEntregaVenda"));
        
        String nomeComprador = request.getParameter("nomeComprador");
        String enderecoComprador = request.getParameter("enderecoComprador");
        String cepComprador = request.getParameter("cepComprador");
        String modoPagamentoString = request.getParameter("modoPagamento");
        
        ModosPagamento modoPagamento = ModosPagamento.valueOf(modoPagamentoString);
        
        Transacao transacao = new Transacao(idItemTransacao, valorTransacao, quantTransacao, dataTransacao, tipoTransacao);
        Comprador comprador = new Comprador(nomeComprador, enderecoComprador, cepComprador, modoPagamento);
        //VendaUsuario venda = new VendaUsuario(idTransacao, idComprador, freteVenda, tempoEntregaVenda, numeroVenda); ERRO
        
        SQL.insert(transacao);
        //SQL.insert(venda);
        SQL.insert(comprador);
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
