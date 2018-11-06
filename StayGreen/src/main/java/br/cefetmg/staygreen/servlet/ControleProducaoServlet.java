/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.servlet;

import br.cefetmg.staygreen.service.ProdutoService;
import br.cefetmg.staygreen.service.InsumoService;
import br.cefetmg.staygreen.table.Insumo;
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
        String idRequisicao = request.getParameter("botao");
        String tipo = request.getParameter("tipo");
        System.out.println(tipo + "/n" + idRequisicao + "/n" + request.getParameter("JSON"));
        switch (idRequisicao) {
            case "adicionar":
                if (tipo.equals("produto")) {
                    Produto produto = JSON.parse(request.getParameter("JSON"), Produto.class);
                    produto.toString();
                    produto.setFotoMercadoria("foto");
                    ProdutoService.AdicionarProduto(produto);
                    resposta = "2";
                } else {
                    Insumo insumo = JSON.parse(request.getParameter("JSON"), Insumo.class);
                    insumo.toString();
                    InsumoService.AdicionarInsumo(insumo);
                    resposta = "2";
                }
                break;
            case "remover":
                if (tipo.equals("produto")) {
                    ProdutoService.deletarProduto(
                            ProdutoService.getProdutoPorId(
                                    request.getParameter("id")));
                } else {
                    InsumoService.deletarInsumo(
                            InsumoService.getInsumoPorId(
                                    request.getParameter("id")));

                }
                break;
            case "editar":
                if (tipo.equals("produto")) {
                    Produto produto = JSON.parse(request.getParameter("JSON"), Produto.class);
                    ProdutoService.atualizarProduto(produto);
                } else {
                    Insumo insumo = JSON.parse(request.getParameter("JSON"), Insumo.class);
                    InsumoService.atualizarInsumo(insumo);
                }
                break;
            case "buscar":
                resposta = JSON.stringify(ProdutoService.get(""));
                break;
            default:
        }
        try (PrintWriter out = response.getWriter()) {
            out.println(resposta);

        }
    }

}
