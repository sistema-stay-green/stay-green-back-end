/*
 *  Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Agrupa métodos estáticos para manipulação de dados no formato JSON.
 * 
 * @author Daniel
 * @version 1.0
 */
public final class JSON {
    
    private static final Gson GSON;
    
    static {
        GSON = new Gson();
    }
    
    private JSON() {}
    
    /**
     * Converte um objeto em uma string no formato JSON.
     * É semelhante ao método stringify do JavaScript.
     * 
     * @param object o objeto a ser convertido para uma string
     * @return       a string resultante da conversão
     */
    public static String stringify(Object object) {
        return GSON.toJson(object);
    }
    
    /**
     * Converte uma string contendo dados no formato JSON em um
     * objeto de uma classe específica.
     * 
     * @param <T>      o tipo do objeto resultante da conversão da string
     * @param json     a string a ser convertida em objeto
     * @param objClass a classe a qual o objeto resultante percence (T)
     * @return         o objeto resultante da conversão
     */
    public static <T> T parse(String json, Class<T> objClass) {
        try {
            System.out.println(json);
            return GSON.fromJson(json, objClass);
        } catch (JsonSyntaxException jsex) {
            System.out.println("Erro na conversão: " + jsex);
            return null;
        }
    }
    
    /**
     * Converte o conteúdo de um arquivo contendo dados no formato JSON em um
     * objeto de uma determinada classe.
     * 
     * @param <T>      o tipo do objeto resultante da conversão do conteúdo do arquivo
     * @param caminho  o caminho para o arquivo com os dados no formato JSON
     * @param objClass a classe a qual o objeto resultante percence (T)
     * @return         o objeto resultante da conversão
     */
    public static <T> T fromFile(String caminho, Class<T> objClass) {
        try (FileReader fr = new FileReader(IO.getCaminhoVerificado(caminho));
                BufferedReader br = new BufferedReader(fr)) {
            
            return GSON.fromJson(br, objClass);
            
        } catch (IOException ioex) {
            System.out.println(ioex);
            return null;
        }
    }
    
}   
