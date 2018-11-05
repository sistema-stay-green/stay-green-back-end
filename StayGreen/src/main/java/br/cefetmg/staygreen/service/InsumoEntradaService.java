/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Insumo;
import br.cefetmg.staygreen.util.SQL;
import java.util.ArrayList;

/**
 *
 * @author arthur
 */
public class InsumoEntradaService {

    public static void inserir(Insumo insumo) {
        SQL.insert(insumo);
    }

    public static void inserirTodos(ArrayList<Insumo> insumos) {

        for (Insumo insumo : insumos) {
            SQL.insert(insumo);
        }
    }

    public static void atualizar(Insumo insumo) {

        SQL.update(insumo);
    }

    public static void atulizarTodos(ArrayList<Insumo> insumos) {

        for (Insumo insumo : insumos) {
            SQL.update(insumo);
        }
    }

    public static void deletar(Insumo insumo) {

        if (insumo.getId() != null) {
            SQL.delete((int) insumo.getId().longValue(), Insumo.class);
        } else {
            System.out.println("!!! ERRO !!! Não foi possível deletar pois"
                    + " o Insumo recebido não possui um Id definido.");
        }
    }

    public static void AdicionarInsumo(Insumo insumo) {
        inserir(insumo);

    } 
    

    public static void RemoverInsumo(Insumo insumo) {

        deletar(insumo);

    }

    public static void EditarInsumo(Insumo insumo) {

        Insumo novoInsumo = new Insumo();
        atualizar(novoInsumo);

    }

}
