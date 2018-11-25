/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.servlet;

import br.cefetmg.staygreen.service.ProdutoService;
import br.cefetmg.staygreen.service.InsumoService;
import br.cefetmg.staygreen.service.RelatoriosControleProducaoService;
import br.cefetmg.staygreen.service.TransacaoService;
import br.cefetmg.staygreen.table.Insumo;
import br.cefetmg.staygreen.table.Produto;
import br.cefetmg.staygreen.table.Transacao;
import br.cefetmg.staygreen.util.JSON;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
         * Switch que define qual operação será feita, são elas: Adicionar:
         * Adicionar insumos ao BD. Remover: Remover produto ou insumo do BD.
         * RemoverTodos: Remover todos os produtos ou todos os insumos do BD.
         * Atualizar: Atualizar produto ou insumo no BD. Buscar: Buscar produto
         * ou insumo no BD. BuscarTodos: Buscar todos os produtos ou insumos no
         * BD. Filtro: Buscar produto(s) ou insumo(s) específicos. Relatorio1:
         * Relatório de histórico de mercadorias/período. Relatorio2:
         * BUILDING...
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
                    if (produto.getIdProduto() != null) {
                        resposta = JSON.stringify(produto);
                        res = true;
                    }
                } else {
                    insumo = InsumoService.getInsumoPorId(request.getParameter("id"));
                    if (insumo.getIdInsumo() != null) {
                        resposta = JSON.stringify(insumo);
                        res = true;
                    }
                }
                break;
            case "buscarTodos":
                if (tipo.equals("produto")) {
                    resposta = JSON.stringify(ProdutoService.get(""));
                    if (resposta != null) {
                        res = true;
                    }
                } else {
                    resposta = JSON.stringify(InsumoService.get(""));
                    if (resposta != null) {
                        res = true;
                    }
                }
                break;
            case "filtro":               
                if (tipo.equals("produto")) {
                    switch (request.getParameter("id")) {
                        case "todosP": 
                            if (ProdutoService.get("") != null) {
                                res = true;
                                resposta = JSON.stringify(ProdutoService.get(""));
                            }
                            break;
                        case "estoqueBaixoP":
                            if (ProdutoService.get("ORDER BY `produto`.`quantEstoqueProduto` ASC") != null) {
                                res = true;
                                resposta = JSON.stringify(ProdutoService.get("ORDER BY `produto`.`quantEstoqueProduto` ASC"));
                            }
                            break;
                        case "foraEstoqueP":
                            if (ProdutoService.get("WHERE `quantEstoqueProduto` = 0") != null) {
                                res = true;
                                resposta = JSON.stringify(ProdutoService.get("WHERE `quantEstoqueProduto` = 0"));
                            }
                            break;
                        case "maisVendidosP":
                            ArrayList<Transacao> transacaos = TransacaoService.get("WHERE `tipoTransacao` = \"PRODUTO\" AND `valorTransacao` > 0 AND `quantTransacao` < 0 ORDER BY `transacao`.`valorTransacao` DESC");
                            if (transacaos != null) {
                                Map<Integer, Integer> quantidade = new HashMap<>();
                                ArrayList<Produto> produtos = new ArrayList();
                                quantidade.put(1, 0);
                                quantidade.put(2, 0);
                                quantidade.put(3, 0);
                                quantidade.put(4, 0);
                                for (int i = 0; i < transacaos.size(); i++) {
                                    quantidade.put(transacaos.get(i).getIdItemTransacao().intValue(),
                                            quantidade.get(transacaos.get(i).getIdItemTransacao().intValue()) + transacaos.get(i).getQuantTransacao());
                                } 
                                quantidade.entrySet().stream()
                                        .sorted((chave1, chave2) -> -chave2.getValue().compareTo(chave1.getValue()))
                                        .forEach(chave3 -> {System.out.println(chave3.getKey() + "" + chave3.getValue()); produtos.add(ProdutoService.getProdutoPorId(String.valueOf(chave3.getKey())));});
                                resposta = JSON.stringify(produtos);
                                System.out.println(quantidade);
                                res = true;
                            } else {
                                res = false;
                            }
                            break;
                        default:
                    }
                } else {
                    switch (request.getParameter("id")) {
                        case "todosI":
                            if (InsumoService.get("") != null) {
                                res = true;
                                resposta = JSON.stringify(InsumoService.get(""));
                            }
                            break;
                        case "estoqueBaixoI":
                            if (InsumoService.get("ORDER BY `insumo`.`quantEstoqueInsumo` ASC") != null) {
                                res = true;
                                resposta = JSON.stringify(InsumoService.get("ORDER BY `insumo`.`quantEstoqueInsumo` ASC"));
                            }
                            break;
                        case "foraEstoqueI":
                            if (InsumoService.get("WHERE `quantEstoqueInsumo` = 0") != null) {
                                res = true;
                                resposta = JSON.stringify(InsumoService.get("WHERE `quantEstoqueInsumo` = 0"));
                            }
                            break;
                        default:
                    }

                }
                break;
            case "relatorio1":
                resposta = RelatoriosControleProducaoService.relatorio1(request.getParameter("id"));
                if (resposta != null) {
                    res = true;
                }
                break;
            case "relatorio2":
                resposta = JSON.stringify(RelatoriosControleProducaoService.relatorio2());
                if (resposta != null) {
                    res = true;
                }
                System.out.println(resposta);
                break;
            default:
        }
        try (PrintWriter out = response.getWriter()) {
            if (res == false) {
                resposta = " {\"resultado\":\"FALHA\"}";
            }
            if (res == true && resposta.equals("")) {
                resposta = " {\"resultado\":\"SUCESSO\"}";
            }
            out.println(resposta);
        }
    }

}
