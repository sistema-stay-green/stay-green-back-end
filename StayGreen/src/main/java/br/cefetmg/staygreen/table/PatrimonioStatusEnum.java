/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.table;

/**
 *
 * @author Mei
 * @version 10-10-18/18:23
 */
public enum PatrimonioStatusEnum {
    VENDIDO,
    ALUGADO,
    EM_POSSE,
    DESCARTADO,
    EM_MANUTENCAO;
    
    public int getStatusId(){
        
        switch (this) {
            case VENDIDO:
                return 0;
            case ALUGADO:
                return 1;
            case EM_POSSE:
                return 2;
            case DESCARTADO:
                return 3;
            case EM_MANUTENCAO:
                return 4;
            default:
                return -1;
        }
    }
}

