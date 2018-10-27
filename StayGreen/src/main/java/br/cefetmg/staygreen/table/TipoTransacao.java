/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.table;

/**
 * @author Gabriel Cruz
 * @version 24-10-18/11:07
 */
public enum TipoTransacao {
    
    MAQUINA,
    PATRIMONIO,
    INSUMO,
    PRODUTO;
    
    public int getTipoTransacao(){
        
        switch (this) {
            case MAQUINA:
                return 0;
            case PATRIMONIO:
                return 1;
            case INSUMO:
                return 2;
            case PRODUTO:
                return 3;
            default:
                return -1;
        }
    }
}