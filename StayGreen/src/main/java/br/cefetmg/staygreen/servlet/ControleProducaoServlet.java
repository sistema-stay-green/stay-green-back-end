/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.servlet;

import br.cefetmg.staygreen.service.EstoqueService;
import br.cefetmg.staygreen.service.ProdutoService;
import br.cefetmg.staygreen.service.InsumoService;
import br.cefetmg.staygreen.service.RelatoriosControleProducaoService;
import br.cefetmg.staygreen.service.TransacaoService;
import br.cefetmg.staygreen.table.EstoqueProdutos;
import br.cefetmg.staygreen.table.Insumo;
import br.cefetmg.staygreen.table.Produto;
import br.cefetmg.staygreen.table.Transacao;
import br.cefetmg.staygreen.util.JSON;
import br.cefetmg.staygreen.util.SQL;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
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
 * que deve ser atualizado ou removido. Parâmetro também responsável por ajudar
 * na função de relatório
 *
 * @author Arthur
 */
@WebServlet(name = "ControleProducaoServlet", urlPatterns = {"/ControleProducaoServlet"})
public class ControleProducaoServlet extends HttpServlet {

    /**
     * Faz a comunição do front-end (HTML/JS/AJAX) com o back-end (Services e
     * BD)
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String resposta = "";
        String operacao = request.getParameter("operacao");
        String tipo = request.getParameter("tipo");
        Boolean res = false;
        /**
         * Switch que define qual operação será feita, são elas:
         * Adicionar: Adicionar insumos ao BD.
         * Remover: Remover produto ou insumo do BD.
         * RemoverTodos: Remover todos os produtos ou todos os insumos do BD.
         * Atualizar: Atualizar produto ou insumo no BD.
         * Buscar: Buscar produto ou insumo no BD.
         * BuscarTodos: Buscar todos os produtos ou insumos no BD.
         * Filtro: Buscar produto(s) ou insumo(s) específicos.
         * Relatorio1: Relatório de histórico de mercadorias/período.
         * Relatorio2: BUILDING...
         *
         * 
         */
        switch (operacao) {
            case "adicionar":
                    Insumo insumo = JSON.parse(request.getParameter("JSON"),
                            Insumo.class);
                    res = InsumoService.AdicionarInsumo(insumo);
                break;
            case "remover":
                if (tipo.equals("produto")) {
                    res = ProdutoService.deletarProduto(request.getParameter("id"));
                } else {
                    res = InsumoService.deletarInsumo(
                            InsumoService.getInsumoPorId(
                                    request.getParameter("id")));
                }
                break;
            case "removerTodos":
                if (tipo.equals("produto")) { 
                    res = ProdutoService.deletarProdutoTodos();
                } else {
                    res = InsumoService.deletarInsumoTodos();
                }
                break;
            case "removerDefinitivo":
                insumo = InsumoService.getInsumoPorId(request.getParameter("id"));
                res = InsumoService.deletarInsumoDefinitivo(insumo);
                break;
            case "atualizar":
                if (tipo.equals("produto")) {
                    Produto produto = JSON.parse(request.getParameter("JSON"),
                            Produto.class);
                    res = ProdutoService.atualizarProduto(produto);
                } else {
                    insumo = JSON.parse(request.getParameter("JSON"),
                            Insumo.class);
                    res = InsumoService.atualizarInsumo(insumo);
                }
                break;
            case "buscar":
                if (tipo.equals("produto")) {
                    Produto produto = ProdutoService.getProdutoPorId(request.getParameter("id"));
                    resposta = JSON.stringify(produto);
                } else {
                    insumo = InsumoService.getInsumoPorId(request.getParameter("id"));
                    resposta = JSON.stringify(insumo);
                }
                break;
            case "buscarTodos":
                if (tipo.equals("produto")) {
                    resposta = JSON.stringify(ProdutoService.get(""));
                } else {
                    resposta = JSON.stringify(InsumoService.get(""));
                }
                break;
            case "filtro":
                if (tipo.equals("produto")) {
                    switch(request.getParameter("id")){
                        case "todosP":
                            resposta = JSON.stringify(ProdutoService.get(""));
                            break;
                        case "estoqueBaixoP":
                            resposta = JSON.stringify(ProdutoService.get("ORDER BY `produto`.`quantEstoqueProduto` ASC"));
                            break;
                        case "foraEstoqueP":
                            resposta = JSON.stringify(ProdutoService.get("WHERE `quantEstoqueProduto` = 0"));
                            break;
                        case "maisVendidosP":
                            ArrayList<Transacao> transacaos = TransacaoService.get("WHERE `tipoTransacao` = \"PRODUTO\" ORDER BY `transacao`.`quantTransacao` DESC");
                            ArrayList<Produto> produtos = new ArrayList<Produto>();
                            for(int i = 0; i < transacaos.size(); i++){ 
                                produtos.add(ProdutoService.getProdutoPorId(String.valueOf(transacaos.get(i).getIdItemTransacao())));
                            }
                            resposta = JSON.stringify(produtos);
                            break;
                        default:
                    }
                } else {
                    switch(request.getParameter("id")){
                        case "estoqueBaixoI":
                            resposta = JSON.stringify(InsumoService.get("ORDER BY `insumo`.`quantEstoqueInsumo` ASC"));
                            break;
                        case "foraEstoqueI":
                            resposta = JSON.stringify(InsumoService.get("WHERE `quantEstoqueInsumo` = 0"));
                            break;
                        default:
                    }

                }
                break;
            case "relatorio1":
                resposta = RelatoriosControleProducaoService.relatorio1(request.getParameter("id"));
                break;
            case "relatorio2":
                resposta = JSON.stringify(RelatoriosControleProducaoService.relatorio2());
                break;
            default:
        }
        try (PrintWriter out = response.getWriter()) {
            if (resposta.equals("") && res == false) {
                resposta = " {\"resultado\":\"FALHA\"}";
            }
            if (resposta.equals("") && res == true){
                resposta = " {\"resultado\":\"SUCESSO\"}";
            }
            out.println(resposta);
        }
    }

}
