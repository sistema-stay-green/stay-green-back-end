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
public enum UnidadesMedidaProdutoEnum {
    KG, L;
    
    public static UnidadesMedidaProdutoEnum converter(Object id) {
        for (UnidadesMedidaProdutoEnum tipo : values()) {
            if (tipo.toString().equals(id)) {
                return tipo;
            }
        }
        return null;
    }
}

