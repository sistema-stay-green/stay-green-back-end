/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Agrupa algumas funcionalidades relativas a reflexão em Java, como obtenção
 * dos atributos de uma classe, valores dos campos de objetos em tempo de
 * execução, entre outros.
 * 
 * @author Daniel
 * @version 1.0
 */
public final class Reflection {
    
    private Reflection() {}
    
    /**
     * Retorna um array com os nomes dos atributos de uma classe.
     * 
     * @param classe a classe que conterá os atributos
     * @return       o conjunto de nomes dos atributos
     */
    public static String[] getAtributos(Class<?> classe) {
        return Arrays.stream(classe.getDeclaredFields())
                .map(campo -> campo.getName())
                .toArray(String[]::new);
    }
    
    /**
     * Obtém todos os atributos de um objeto. Os valores de cada campo também
     * são considerados e colocados em um mapa.
     * 
     * @param objeto o objeto do qual se quer objeter os atributos
     * @return       o mapa contendo os atributos do objeto
     */
    public static Map<String, Object> getAtributos(Object objeto) {
        
        Map<String, Object> campos = new HashMap<>();       
                
        Arrays.stream(objeto.getClass().getDeclaredFields()).forEach(campo -> {
            try {
                campo.setAccessible(true);
                campos.put(campo.getName(), campo.get(objeto));
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                System.out.println("Erro ao tentar obter os atributos – " + ex);
            }
        });
        
        return campos;
                
    }
    
    /**
     * Altera o valor de um atributo conforme o objeto e o nome especificados.
     * 
     * @param objeto       o objeto que possui o atributo a ser modificado
     * @param nomeAtributo o nome do campo a ser modificado
     * @param valor        o novo valor para o atributo daquele objeto
     */
    public static void setAtributo(Object objeto,
            String nomeAtributo, Object valor) {
        
        try {
            Field atributo = objeto.getClass().getDeclaredField(nomeAtributo);
            atributo.setAccessible(true);
            atributo.set(objeto, valor);
            atributo.setAccessible(false);
        } catch (NoSuchFieldException nsfex) {
            System.out.println("O atributo especificado não existe.");
        } catch (SecurityException | IllegalAccessException ex) {
            System.out.println("Ocorreu um erro por motivos de permissão.");
            System.out.println(ex);
        } catch (IllegalArgumentException iaex) {
            System.out.println(
                    "O argumento para o campo especificado é inválido");
            System.out.println(iaex);
        }
        
    }
    
}
