/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.servlet;


import br.cefetmg.staygreen.service.ProdutoEntradaService;
import br.cefetmg.staygreen.service.ProdutoSaidaService;
import br.cefetmg.staygreen.table.Produto;
import br.cefetmg.staygreen.util.JSON;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet para exemplo, sem utilidade para o projeto.
 *
 * @author Arthur
 */
@WebServlet(name = "ControleProducaoServlet", urlPatterns = {"/ControleProducaoServlet"})
public class ControleProducaoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String resposta = "";
        Produto produto = JSON.parse(request.getParameter("JSON"), Produto.class);
        produto.setFotoMercadoria("foto");
        try (PrintWriter out = response.getWriter()) {
            ProdutoEntradaService.AdicionarProduto(produto);
            resposta = produto.toString();
            out.println(resposta);
            
        }
    }

}
