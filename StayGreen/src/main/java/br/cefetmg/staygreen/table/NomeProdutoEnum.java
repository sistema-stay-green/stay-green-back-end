/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.table;

/**
 *
 * @author Arthur
 */
public enum NomeProdutoEnum {
    LEITE,
    CAFE_BOURBON,
    CAFE_ROBUSTA,
    CAFE_ARABICA;
    
        
    public static NomeProdutoEnum converter(Object id) {
        for (NomeProdutoEnum tipo : values()) {
            if (tipo.toString().equals(id)) {
                return tipo;
            }
        }
        return null;
    }
            
}
