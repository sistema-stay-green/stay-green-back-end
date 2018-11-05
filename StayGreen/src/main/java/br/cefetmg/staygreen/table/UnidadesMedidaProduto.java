/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.table;

/**
 *
 * @author Paulo Vitor
 * @author Arthur
 */
public enum UnidadesMedidaProduto {
    KG, L;
    
    public static UnidadesMedidaProduto converter(Object id) {
        for (UnidadesMedidaProduto tipo : values()) {
            if (tipo.toString().equals(id)) {
                return tipo;
            }
        }
        return null;
    }
}

