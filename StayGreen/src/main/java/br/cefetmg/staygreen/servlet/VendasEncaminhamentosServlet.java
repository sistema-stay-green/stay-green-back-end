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
import br.cefetmg.staygreen.table.Transacao;
import br.cefetmg.staygreen.table.Comprador;
import br.cefetmg.staygreen.table.VendaUsuario;
import br.cefetmg.staygreen.util.Data;
import br.cefetmg.staygreen.util.JSON;
import br.cefetmg.staygreen.util.SQL;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Produz o relatório de encamiminhamentos e retorna em formato json
 * @author Paulo Vitor
 */
@WebServlet(name = "VendasEncaminhamentosServlet", urlPatterns = {"/VendasEncaminhamentosServlet"})
public class VendasEncaminhamentosServlet extends HttpServlet {
    
    /**
     * Classe privada que representa uma entrada do relatório
     */
    private class RelatorioEntry{
        private final int dia;
        private final int mes;
        private final int ano;
        private final String nome;

        /**
         * Produz uma entrada do relat´relatório
         * @param dia dia da venda
         * @param mes mes da venda
         * @param ano ano da venda
         * @param nome nome do comprador
         */
        public RelatorioEntry(int dia, int mes, int ano, String nome) {
            this.dia = dia;
            this.mes = mes;
            this.ano = ano;
            this.nome = nome;
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        List<RelatorioEntry> relatorio = new LinkedList();
        
        List<VendaUsuario> vendas = (List<VendaUsuario>) SQL.getRegistros(VendaUsuario.class);
        Integer ultimaVenda = 0;
        for(VendaUsuario venda : vendas){
            if(!Objects.equals(venda.getNumeroVenda(), ultimaVenda)){
                ultimaVenda = venda.getNumeroVenda();
                Long idTransacao = venda.getIdTransacao();
                Long idComprador = venda.getIdComprador();
                
                ResultSet compradorRs = SQL.query("SELECT * from " 
                        + SQL.getNomeTabela(Comprador.class) 
                        + " WHERE idComprador=" + idComprador);
                while(compradorRs.next()){
                    String nome = compradorRs.getString("nomeComprador");
                    
                    ResultSet transacao = SQL.query("SELECT * FROM "
                            + SQL.getNomeTabela(Transacao.class)
                            + " WHERE idTransacao=" + idTransacao);
                    if(transacao.next()){
                        Calendar dataEntrega = Data.dateToCalendar(transacao.getDate("dataTransacao"));
                        relatorio.add(new RelatorioEntry(
                                dataEntrega.get(Calendar.DATE),
                                dataEntrega.get(Calendar.MONTH),
                                dataEntrega.get(Calendar.YEAR), nome));
                    }
                }
            }
        }
        
        try(PrintWriter out = response.getWriter()) {
            out.println(JSON.stringify(relatorio));
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(VendasEncaminhamentosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(VendasEncaminhamentosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
