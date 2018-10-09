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
 * Indica que um atributo representa a chave primária (id) de uma tabela.
 * Dessa forma, classes como a {@link br.cefetmg.staygreen.util.SQL}
 * poderão manipular os dados dos objetos da classe anotada para realizar
 * inserção e atualização de registros na respectiva tabela.
 * 
 * @author Daniel
 * @version 1.0
 * @see br.cefetmg.staygreen.annotation.Tabela
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
    
}
