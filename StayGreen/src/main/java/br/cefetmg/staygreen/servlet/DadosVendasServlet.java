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
import br.cefetmg.staygreen.table.ModosPagamentoEnum;
import br.cefetmg.staygreen.table.Produto;
import br.cefetmg.staygreen.util.SQL;
import br.cefetmg.staygreen.util.JSON;
import br.cefetmg.staygreen.table.VendaUsuario;
import br.cefetmg.staygreen.table.TipoTransacaoEnum;
import br.cefetmg.staygreen.table.Transacao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet que recebe informações via POST e envia para o BD
 * @version 1.2
 * @author João Pedro
 */
@WebServlet(name = "DadosVendasServlet", urlPatterns = {"/DadosVendasServlet"})
public class DadosVendasServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);

        // Dados do comprador
        String nomeComprador = request.getParameter("nomeComprador");
        String enderecoComprador = request.getParameter("enderecoComprador");
        String cepComprador = request.getParameter("cepComprador");
        String modoPagamentoString = request.getParameter("modoPagamento");
        
        ModosPagamento modoPagamento = ModosPagamento.valueOf(modoPagamentoString);
        
        //insere comprador
        Comprador comprador = new Comprador(nomeComprador, enderecoComprador,
                cepComprador, modoPagamento);
        SQL.insert(comprador);
        
        /**
         * Id do comprador
         */
        Long idComprador = new Long(SQL.getLastInsertId());
        
        //Pega dados da venda
        Double freteVenda = Double.parseDouble(request.getParameter("freteVenda"));
        Integer tempoEntregaVenda = Integer.parseInt(request.getParameter("tempoEntregaVenda"));
        
        //Confere se a tabela vendas está vazia
        ResultSet resultSetAux = SQL.query("SELECT COUNT(*) AS total FROM VendaUsuario");
        Integer tabelaVazia = 0;
        while(resultSetAux.next()){
            tabelaVazia = resultSetAux.getInt("total");
        }
        
        //Decide o numero da venda
        Integer numeroVenda;
        if(tabelaVazia == 0){
            numeroVenda = 1;
        } else {
            Integer numeroVendaAux = 0;
            resultSetAux = SQL.query("SELECT MAX(idVenda) AS id FROM VendaUsuario");
            while(resultSetAux.next())
                numeroVendaAux = resultSetAux.getInt("id");
            numeroVenda = numeroVendaAux + 1;
        }
        
        //Pega as transacoes
        String transacaoJSON = request.getParameter("transacoes");
        Transacao[] transacoes = JSON.parse(transacaoJSON, Transacao[].class);
        
        //Data da transacao
        Integer dia = Integer.parseInt(request.getParameter("dia"));
        Integer mes = Integer.parseInt(request.getParameter("mes"));
        Integer ano = Integer.parseInt(request.getParameter("ano"));
        TipoTransacaoEnum tipoTransacao = TipoTransacaoEnum.PRODUTO;
        
        Calendar dataTransacao = Calendar.getInstance();
        dataTransacao.set(ano, mes, dia);
        
        //Para cada transacao
        for (Transacao transacao : transacoes) {
            //Set data e tipo
            transacao.setDataTransacao(dataTransacao);
            transacao.setTipoTransacao(tipoTransacao);
            
            Long idProduto = transacao.getIdItemTransacao();
            
            ResultSet estoqueProdutoRS = SQL.query("SELECT `quantEstoqueProduto` FROM " 
                    + SQL.getNomeTabela(Produto.class) + " WHERE `idProduto`=" 
                    + idProduto);
            while(estoqueProdutoRS.next()){
                Integer estoqueProduto = estoqueProdutoRS.getInt("quantEstoqueProduto");
                SQL.update(new Produto(idProduto, null, null, null, null,
                        estoqueProduto - transacao.getQuantTransacao(),
                        null, null));
            }
            
            //Insere transacao
            SQL.insert(transacao);
            
            //id da transacao
            Long idTransacao = new Long(SQL.getLastInsertId());
            
            //Insere a venda
            VendaUsuario venda = new VendaUsuario(idTransacao, idComprador,
                    freteVenda, tempoEntregaVenda, numeroVenda);
            SQL.insert(venda);
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
            Logger.getLogger(DadosVendasServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DadosVendasServlet.class.getName()).log(Level.SEVERE, null, ex);
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
