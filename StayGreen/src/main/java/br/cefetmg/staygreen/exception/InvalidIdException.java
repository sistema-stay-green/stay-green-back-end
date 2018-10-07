/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.exception;

/**
 * Exceção para indicar valores de id inválidos. Isso pode ocorrer em
 * situações em que o valor é menor do que zero ou quando é igual a {@code null}
 * 
 * @author Daniel
 * @version 1.0
 */
public class InvalidIdException extends RuntimeException {

    public InvalidIdException() {
        super("O id passado é inválido. Verifique se é um número maior do que 0");
    }
    
}
