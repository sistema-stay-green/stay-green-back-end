/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Produto;
import br.cefetmg.staygreen.util.SQL;
import java.util.ArrayList;

/**
 *
 * @author arthur
 */
public class ProdutoEntradaService {

    public static void inserir(Produto produto) {

        SQL.insert(produto);
    }

    public static void inserirTodos(ArrayList<Produto> produtos) {

        for (Produto produto : produtos) {
            SQL.insert(produto);
        }
    }

    public static void atualizar(Produto produto) {

        SQL.update(produto);
    }

    public static void atulizarTodos(ArrayList<Produto> produtos) {

        for (Produto produto : produtos) {
            SQL.update(produto);
        }
    }

    public static void deletAR(Produto produto) {

        if (produto.getIdProduto() != null) {
            SQL.delete((int) produto.getIdProduto().longValue(), Produto.class);
        } else {
            System.out.println("!!! ERRO !!! Não foi possível deletar pois"
                    + " o Produto recebido não possui um Id definido.");
        }
    }

    public static void AdicionarProduto(Produto produto) {
        inserir(produto);

    } 
    

    public static void RemoverProduto(Produto produto) {

        deletAR(produto);

    }

    public static void EditarProduto(Produto produto) {

        Produto novoProduto = new Produto();
        atualizar(novoProduto);

    }

}
