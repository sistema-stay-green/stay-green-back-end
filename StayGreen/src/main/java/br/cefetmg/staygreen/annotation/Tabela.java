/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indica que uma classe representa uma tabela. Dessa forma, classes como
 * a {@link br.cefetmg.staygreen.util.SQL} poderão manipular os dados dos
 * objetos da classe anotada para realizar inserção e atualização de
 * registros na respectiva tabela.
 * 
 * @author Daniel
 * @version 1.0
 * @see br.cefetmg.staygreen.annotation.Id
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Tabela {
    
    /**
     * Retorna o nome da tabela a qual a classe anotada está relacionada.
     * 
     * @return o nome da tabela associada
     */
    public String value();
    
}
