/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.util;

import br.cefetmg.staygreen.annotation.Id;
import br.cefetmg.staygreen.annotation.Tabela;
import br.cefetmg.staygreen.exception.InvalidIdException;
import br.cefetmg.staygreen.exception.NotTableException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Reúne ações básicas para a realização de requisições ao banco de dados.
 * 
 * @author Daniel
 * @version 1.0
 */
public final class SQL {
    
    private static final Connection CONNECTION;
    
    static {
        CONNECTION = new SQLConnectionFactory().getConnection();
    }
    
    private SQL() {}
    
    /**
     * Realiza uma simples requisição ao banco de dados.
     * 
     * @param query a string de requisição, que deve estar de acordo com a
     *              implementação sendo utilizada (MySQL)
     * @return      o conjunto de resutados da requisição
     */
    public static ResultSet query(String query) {
        
        try {
            Statement stmt = CONNECTION.createStatement();
            stmt.execute(query);
            return stmt.getResultSet();
        } catch (SQLException sqlex) {
            System.out.println(sqlex);
            return null;
        }
        
    }
    
    /**
     * Faz a inserção dos dados de um objeto para a respective tabela.
     * 
     * @param objeto o objeto com os dados a serem inseridos
     * @return       se a operação ocorreu com sucesso ou não
     * @throws       NotTableException se a classe não for anotada com
     *               {@link br.cefetmg.staygreen.annotation.Tabela}
     */
    public static boolean insert(Object objeto) throws NotTableException {
        
        Map<String, String> campos = getCampos(objeto, true);
        String[] nomesCampos = campos.keySet().stream().toArray(String[]::new);
        String[] valoresCampos = campos.values().stream().toArray(String[]::new);
        String sql = "INSERT INTO " + getNomeTabela(objeto.getClass())
                +" (" + String.join(", ", nomesCampos) + ")"
                +  "  VALUES (" + String.join(", ", valoresCampos) + ")";
        
        return query(sql) == null;
        
    }
    
    /**
     * Realiza a atualização dos dados de um objeto na respectiva tabela.
     * É importante saber que o objeto passado precisa ter um id não nulo. Os
     * dados que serão atualizados serão todos os campos não nulos do objeto.
     * 
     * Para exemplificar, considere uma clase Carro, que possui os campos id,
     * marca e ano. O seguinte código faz com que o registro com id igual a 2
     * tenha o seu nome atualizado, mas o ano permanece inalterado:
     * {@code SQL.update(new Carro(2, "Lamborghini", null)); }.
     * 
     * @param objeto o objeto com os dados a serem atualizados
     * @return       se a operação ocorreu com sucesso ou não
     * @throws       NotTableException se a classe não for anotada com
     *               {@link br.cefetmg.staygreen.annotation.Tabela} 
     */
    public static boolean update(Object objeto) throws NotTableException {
        
        Map<String, String> campos = getCampos(objeto, false);
        String nomeId = getNomeId(objeto.getClass());
        String id = campos.remove(String.format("`%s`", nomeId));
        
        /* Caso o id não esteja definido, a atualização não pode ser feita: */
        if (id == null || Integer.parseInt(id) < 0)
            return false;
        
        String[] camposUpdate = campos.keySet().stream()
                .map(nome -> String.format("%s = %s", nome, campos.get(nome)))
                .toArray(String[]::new);
        String sql = "UPDATE " + getNomeTabela(objeto.getClass())
                + " SET " + String.join(", ", camposUpdate)
                + " WHERE " + nomeId + " = " + id;
        
        return query(sql) == null;
        
    }
    
    /**
     * Deleta o registro de um uma tabela que contém o id especificado.
     * 
     * @param id     o id do registro a ser deletado
     * @param classe a classe que está associada à tabela em que se deseja deletar
     * @return       se a operação ocorreu com sucesso ou não
     * @throws       NotTableException se a classe não for anotada com
     *               {@link br.cefetmg.staygreen.annotation.Tabela}
     * @throws       InvalidIdException se o id recebido for inválido
     */
    public static boolean delete(int id, Class<?> classe)
            throws NotTableException, InvalidIdException {
        
        String sql = "DELETE FROM " + getNomeTabela(classe)
                + " WHERE " + getNomeId(classe) +" = " + id;
        
        return query(sql) == null;
        
    }
    
    public static <T> List<T> getRegistros(Class<T> classe)
            throws NotTableException {
        
        List<T> registros = new LinkedList<>();
        String[] nomesCampos = Reflection.getAtributos(classe);
        
        try (ResultSet rs = query("SELECT * FROM " + getNomeTabela(classe))) {
            while (rs.next()) {
                T objeto = classe.newInstance();
                for (String campo : nomesCampos) {
                    Object val = rs.getObject(campo);
                    if (val instanceof Date) {
                        Calendar data = Calendar.getInstance();
                        data.setTime((Date) val);
                        val = data;
                    } else if (classe.getDeclaredField(campo).getType()
                            .equals(Boolean.class) && val instanceof Integer) {
                        val = val.equals(1); // converte para Boolean
                    }
                    Reflection.setAtributo(objeto, campo, val);
                }
                registros.add(objeto);
            }
        } catch (SQLException sqlex) {
            System.out.println("Não foi possível carregar os registros – "
                    + sqlex);
        } catch (InstantiationException | IllegalAccessException ex) {
            System.out.println("Erro na instanciação dos objetos da"
                    + "classe especificada.");
        } finally {
            return registros;
        }
        
    }
    
    /**
     * Retorna o nome da tabela à qual a classe de um certo objeto está
     * associada. É necessário que a classe esteja anotada com 
     * {@link br.cefetmg.staygreen.annotation.Tabela}
     * 
     * @param classe a classe a ser analisada
     * @return       o nome da tabela referente à classe do objeto
     * @throws       NotTableException se a classe não for anotada com
     *               {@link br.cefetmg.staygreen.annotation.Tabela}
     */
    public static String getNomeTabela(Class<?> classe)
            throws NotTableException {
        
        return Optional // resgata o nome da tabela correspondente
                .ofNullable(classe.getAnnotation(Tabela.class))
                .map(a -> a.value())
                .orElseThrow(NotTableException::new);  
        
    }
    
    /**
     * Retorna o nome do atributo de uma classe que foi definido para
     * ser a chave primária da tabela. Qualquer nome de campo que esteja
     * anotado com {@link br.cefetmg.staygreen.annotation.Id} representa
     * uma chave primária. O primeiro atributo que for encontrado com essa
     * anotação terá o seu nome retornado. Caso não haja campos anotados,
     * uma exceção será lançada.
     * 
     * @param classe a classe a ser analisada
     * @return       o nome definido na classe para o id 
     * @throws       NotTableException se nenhum atributo estiver anotado
     *               com {@link br.cefetmg.staygreen.annotation.Id}
     */
    private static String getNomeId(Class<?> classe) throws NotTableException {
        
        Field[] atributos = classe.getDeclaredFields();
        
        for (Field atributo : atributos) {
            if (atributo.getAnnotation(Id.class) != null) {
                return atributo.getName();
            }
        }
        
        throw new NotTableException();

    }
    
    /**
     * Obtém os atributos do objeto e seus respectivos valores. O resultado
     * é armazenado em um mapa, cujas chaves são os nomes dos atributos e os
     * valores são o conteúdo de cada campo já convertidos para a notação SQL.
     * 
     * @param objeto o objeto a ser analisado
     * @return       o mapa com os atributos
     */
    private static Map<String, String> getCampos(Object objeto, boolean ignoreId) {
        
        Map<String, Object> campos = Reflection.getAtributos(objeto);
        
        if (ignoreId) { // não considera a chave primária caso necessário
            campos.remove(getNomeId(objeto.getClass()));
        }
        
        /* Converte para a notação em SQL: */
        String[] nomesCampos = campos.keySet().stream()
                .map(nome -> String.format("`%s`", nome))
                .toArray(String[]::new);
        String[] valoresCampos = campos.values().stream()
                .map(val -> {
                    Object resultado = val;
                    if (val instanceof String) { // strings entre aspas simples
                        resultado = String.format("'%s'", val);
                    } else if (val instanceof Calendar) { // data no formato SQL
                        resultado = String.format("'%s'",
                                new Date(((Calendar) val).getTimeInMillis()));
                    }
                    return resultado == null ? null : resultado.toString();
                }).toArray(String[]::new);
        
        /* Insere os dados dos campos em um mapa final: */
        Map<String, String> camposResultado = new HashMap<>();
        for (int i = 0; i < nomesCampos.length; i++) {
            if (valoresCampos[i] != null)
                camposResultado.put(nomesCampos[i], valoresCampos[i]);
        }
        
        return camposResultado;
        
    }
    
    /**
     * Retorna a conexão com o banco de dados. Ela é instanciada somente
     * uma vez, durante o carregamento da classe e deve, portanto, ser
     * utilizada estaticamente por todo o programa.
     * 
     * @return a conexão com o banco de dados
     */
    public static Connection getConnection() {
        return CONNECTION;
    }
    
}