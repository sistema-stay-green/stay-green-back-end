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
 * Ao requisitar este servlet, os parâmetros tais como devem ser escritos na URL
 * que irá fazer a requisição são:
 *
 * operacao => (OBRIGATORIO) Parâmetro responsável por informar ao servlet qual
 * função/método deve ser executado.
 *
 * tipo => (OPCIONAL) Parâmetro responsável por informar ao servlet qual tipo do
 * objeto deve ser criado, Produto ou Insumo.
 *
 * JSON => (OPCIONAL) Parâmetro responsável por passar a string que será
 * convertida em objeto.
 *
 * id => (OPCIONAL) Parâmetro responsável por informar o ID do produto ou insumo
 * que deve ser atualizado ou removido. Parâmetro também responsável por
 * ajudar na função de relatório
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
        String operacao = request.getParameter("operacao");
        String tipo = request.getParameter("tipo");
        switch (operacao) {
            case "adicionar":
                if (tipo.equals("produto")) {
                    Produto produto = JSON.parse(request.getParameter("JSON"),
                            Produto.class);
                    produto.setFotoMercadoria("foto");
                    ProdutoService.AdicionarProduto(produto);
                } else {
                    Insumo insumo = JSON.parse(request.getParameter("JSON"),
                            Insumo.class);
                    insumo.toString();
                    InsumoService.AdicionarInsumo(insumo);
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
            case "removerTodos":
                if (tipo.equals("produto")) {
                    ProdutoService.deletarProdutoTodos();
                } else {
                    InsumoService.deletarInsumoTodos();
                }
                break;
            case "removerTodosPI":
                ProdutoService.deletarProdutoTodos();
                InsumoService.deletarInsumoTodos();
                break;
            case "atualizar":
                if (tipo.equals("produto")) {
                    Produto produto = JSON.parse(request.getParameter("JSON"),
                            Produto.class);
                    ProdutoService.atualizarProduto(produto);
                } else {
                    Insumo insumo = JSON.parse(request.getParameter("JSON"),
                            Insumo.class);
                    InsumoService.atualizarInsumo(insumo);
                }
                break;
            case "buscar":
                if (tipo.equals("produto")) {
                    resposta = JSON.stringify(ProdutoService.getProdutoPorId(
                            request.getParameter("id")));
                } else {
                    resposta = JSON.stringify(InsumoService.getInsumoPorId(
                            request.getParameter("id")));
                }
            case "buscarTodos":
                if (tipo.equals("produto")) {
                    resposta = JSON.stringify(ProdutoService.get(""));
                } else {
                    resposta = JSON.stringify(InsumoService.get(""));
                }
                break;
            case "buscarTodosPI":
                resposta = JSON.stringify(ProdutoService.get(""))
                        + JSON.stringify(InsumoService.get(""));
                break;
            case "filtro":
                if (tipo.equals("produto")) {
                    resposta = JSON.stringify(ProdutoService.getProdutoPorNome(
                            request.getParameter("nome")));
                } else {
                    resposta = JSON.stringify(InsumoService.getInsumoPorNome(
                            request.getParameter("nome")));
                }
                break;
            case "relatorio1":
                String id = request.getParameter("id");
                resposta = JSON.stringify();

                break;
            case "relatorio2":

                break;
            default:
        }
        try (PrintWriter out = response.getWriter()) {
            out.println(resposta);

        }
    }

}
