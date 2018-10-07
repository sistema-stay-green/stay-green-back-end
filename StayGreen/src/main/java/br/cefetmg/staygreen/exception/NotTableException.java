/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.exception;

/**
 * Exceção para operações com classes anotadas como
 * {@link br.cefetmg.staygreen.annotation.Tabela}. É lançada caso a classe
 * sendo analisada não contenha a anotação.
 * 
 * @author Daniel
 * @version 1.0
 */
public class NotTableException extends RuntimeException {

    public NotTableException() {
        super("A classe do objeto não é reconhecida como tabela. "
            + "Considere anotá-la com @Tabela.");
    }
    
}
