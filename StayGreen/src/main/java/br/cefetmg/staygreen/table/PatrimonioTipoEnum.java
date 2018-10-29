/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.table;

/**
 *
 * @author Simoneti
 */
public enum PatrimonioTipoEnum {
    MAQUINA,
    OUTROS;
    
    public int getTipoId(){
        
        switch (this) {
            case MAQUINA:
                return 0;
            case OUTROS:
                return 1;
            default:
                return -1;
        }
    }
}

