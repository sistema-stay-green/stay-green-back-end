/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.util;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
/**
 * Contém métodos estáticos para manipulação de entrada e saída.
 * 
 * @author Daniel
 * @version 1.0
 */
public final class IO {
    
    private static MessageDigest digester;

    static {
        try {
            digester = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);
        }
    }
        
    private IO() {}
    
    /**
     * retorna um objeto {@link Properties} com os dados extraídos de um
     * arquivo que está no caminho especificado. Como a Resources API do
     * Maven está sendo utilizada, lembre-se de que os arquivos devem estar
     * dentro da pasta src/main/resources. Além disso, o caminho deve começar
     * com uma barra em grande parte dos casos.
     * 
     * @param caminho o caminho do arquivo de propriedades (.properties)
     * @return um objeto contendo as propriedades
     */
    public static Properties getProperties(String caminho) {
        
        Properties props = new Properties();
        
        try {
            props.load(IO.class.getResourceAsStream(caminho));
            return props;
        } catch (IOException ioex) {
            System.out.println("Erro ao tentar carregar as propriedades.");
            System.out.println(ioex);
            return null;
        }
        
    }
    
    /**
     * Retorna uma string com a separação de arquivos adequada ao sistema.
     * Ao realizar uma operação com arquivos, é importante utilizar os
     * separadores de arquivo do SO em que o programa é executado, e este
     * método realiza tal conversão, caso seja necessária.
     * 
     * @param caminho
     * @return 
     */
    public static String getCaminhoVerificado(String caminho) {
        
        String caminhoVerificado = caminho
            .replace('/', File.separator.charAt(0))
            .replace('\\', File.separator.charAt(0));
        
        return caminhoVerificado;
        
    }
    
    public static String criptografar(String s) {
        
        digester.update(s.getBytes());
        
        byte[] hash = digester.digest();
        StringBuilder hexString = new StringBuilder();
        
        for (int i = 0; i < hash.length; i++) {
            String criptografado = Integer.toHexString(0xFF & hash[i]);
            hexString.append(((0xff & hash[i]) < 0x10 ? "0" : "") + criptografado);
        }
        
        return hexString.toString();
        
    }
    
}
