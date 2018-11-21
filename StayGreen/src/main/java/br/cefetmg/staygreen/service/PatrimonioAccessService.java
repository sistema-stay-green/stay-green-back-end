/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Patrimonio;
import br.cefetmg.staygreen.util.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe de acesso dos Patrimônios através do DB.
 * 
 * @author Mei Fagundes
 * @version 20-11-18/14:37
 */
public class PatrimonioAccessService {
    
    // Atributos estáticos
    
    // Constantes que representam os nomes das colunas na tabela do DB SQL.
    private static final String ID_COLUMN;
    private static final String NOME_COLUMN;
    private static final String TIPO_COLUMN;
    private static final String FINALIDADE_COLUMN;
    private static final String STATUS_COLUMN;
    private static final String INDICE_DEPRECIACAO_COLUMN;
    private static final String VALOR_COMPRA_COLUMN;
    private static final String DATA_COMPRA_COLUMN;
    private static final String DATA_SAIDA_COLUMN;
    private static final String DATA_RETORNO_COLUMN;
    private static final String DATA_BAIXA_COLUMN;
    
    private static final String TABLE_NAME;
    
    // inicialização das constantes internas.
    static {
        ID_COLUMN = "idPatrimonio";
        NOME_COLUMN = "nomePatrimonio";
        TIPO_COLUMN = "tipoPatrimonio";
        FINALIDADE_COLUMN = "finalidadePatrimonio";
        STATUS_COLUMN = "statusPatrimonio";
        INDICE_DEPRECIACAO_COLUMN = "indDeprecPatrimonio";
        VALOR_COMPRA_COLUMN = "valorCompraPatrimonio";
        DATA_COMPRA_COLUMN = "dataCompraPatrimonio";
        DATA_SAIDA_COLUMN = "dataSaidaPatrimonio";
        DATA_RETORNO_COLUMN = "dataRetornoPatrimonio";
        DATA_BAIXA_COLUMN = "dataBaixaPatrimonio";
        
        TABLE_NAME = SQL.getNomeTabela(Patrimonio.class);
    }
    
    /**
     * Pesquisa na DB 'staygreen' usando o Id recebido.
     * @param id
     * @return Retorna um objeto Patrimonio que corresponde ao Id recebido.
     * @author Mei Fagundes
     */
    public static Patrimonio getPatrimonioById(String id){
        
        ArrayList<Patrimonio> patrimonios = get("WHERE idPatrimonio=" + id);
        
        if (patrimonios == null){
            System.out.println("!!! AVISO !!! Nenhum patrimonio encontrado com o Id: " + id);
            return null;
        }
        
        return patrimonios.get(0);
    }
    
    /**
     * Pesquisa na DB 'staygreen' usando o Nome recebido.
     * @param nome
     * @return Retorna objetos Patrimonio que correspondam ao Nome recebido.
     * @author Mei Fagundes
     */
    public static ArrayList<Patrimonio> getPatrimoniosByNome(String nome){
        
        ArrayList<Patrimonio> patrimonios = get("WHERE nomePatrimonio='" + nome + "'");
        
        if (patrimonios == null){
            System.out.println("!!! AVISO !!! Nenhum patrimonio encontrado com o Nome: " + nome);
            return null;
        }
        return patrimonios;
    }
    
    /**
     * Pesquisa na DB 'staygreen' usando o Tipo recebido.
     * @param tipo
     * @return Retorna objetos Patrimonio que correspondam ao Tipo recebido. 
     * @author Mei Fagundes
     */
    public static ArrayList<Patrimonio> getPatrimoniosByTipo(String tipo){
        
        ArrayList<Patrimonio> patrimonios = get("WHERE tipoPatrimonio='" + tipo + "'");
        
        if (patrimonios == null){
            System.out.println("!!! AVISO !!! Nenhum patrimonio encontrado com o Tipo: " + tipo);
            return null;
        }
        return patrimonios;
    }
    
    /**
     *  Pesquisa todos os Patrimonios na DB 'staygreen'.
     * @return Retorna todos os objetos Patrimonio na DB 'staygreen'.
     * @author Mei Fagundes
     */
    public static ArrayList<Patrimonio> getAll(){
        return get("");
    }
    
    /**
     * Pesquisa na DB 'staygreen' usando a queryCondition recebida.
     * @param queryCondition
     * @return Retorna objetos Patrimonio resultantes da queryCondition de pesquisa recebida.
     * @author Mei Fagundes
     */
    public static ArrayList<Patrimonio> get(String queryCondition){
        
        ResultSet result;
        ArrayList<Patrimonio> patrimonios = new ArrayList<>();
        
        try {
            
            result = SQL.query("SELECT * FROM " + TABLE_NAME +  " " + queryCondition);
            
            if (result.next()) {
                    do {
                        
                        Patrimonio patrimonio = new Patrimonio(
                                result.getInt(ID_COLUMN), 
                                result.getString(NOME_COLUMN));
                        patrimonio.setTipo(
                                result.getString(TIPO_COLUMN));
                        patrimonio.setFinalidade(
                                result.getString(FINALIDADE_COLUMN));
                        patrimonio.setStatus(
                                result.getString(STATUS_COLUMN));
                        patrimonio.setIndiceDepreciacao(
                                result.getDouble(INDICE_DEPRECIACAO_COLUMN));
                        patrimonio.setValorCompra(
                                result.getDouble(VALOR_COMPRA_COLUMN));
                        patrimonio.setDataCompra(
                                result.getDate(DATA_COMPRA_COLUMN));
                        patrimonio.setDataSaida(
                                result.getDate(DATA_SAIDA_COLUMN));
                        patrimonio.setDataRetorno(
                                result.getDate(DATA_RETORNO_COLUMN));
                        patrimonio.setDataBaixa(
                                result.getDate(DATA_BAIXA_COLUMN));
                        
                        patrimonios.add(patrimonio);
                        
                } while (result.next());
            } else{
                System.out.println("!!! AVISO !!! Nenhum resultado encontrado"
                        + " usando a condição recebida.");
                return null;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex + " at getRowFromId");
            return null;
        }
        
        return patrimonios;
    }
    
    /**
     * Tenta Recupera o ultimo Patrimonio inserido na DB 'staygreen'.
     * @return Retorna o ultimo Patrimonio inserido na DB 'staygreen'.
     * @author Mei Fagundes
     */
    public static Patrimonio getLastInsertedPatrimonio(){
        
        try{
            ResultSet lastId = SQL.query("SELECT LAST_INSERT_ID()");
            
            if(lastId.next())
                return PatrimonioAccessService.getPatrimonioById(
                        Integer.toString(lastId.getInt("LAST_INSERT_ID()")));
            
        } catch(SQLException ex){
            System.out.println(ex + " at case Compra");
        }
        
        return null;
    }
    
    /**
     * Insere um objeto Patrimonio na DB 'staygreen'
     * @param patrimonio
     * @return Retorna o Patrimonio inserido com o Id atualizado.
     * @author Mei Fagundes
     */
    public static Boolean insert(Patrimonio patrimonio){
        
        return SQL.insert(patrimonio);
    }
    
    /**
     * Insere vários objetos Patrimonio na DB 'staygreen'
     * @param patrimonios
     * @return Retorna o êxito da operação.
     * @author Mei Fagundes
     */
    public static Boolean insertAll(ArrayList<Patrimonio> patrimonios){
        
        Boolean result = false;
        for (int i = 0; i < patrimonios.size(); i++) {
            result = insert(patrimonios.get(i));
        }
        return result;
    }
    
    /**
     * Atualiza o patrimonio na DB correspondente ao objeto Patrimonio recebido
     * @param patrimonio
     * @return Retorna o êxito da operação.
     * @author Mei Fagundes
     */
    public static Boolean update(Patrimonio patrimonio){
        
        return SQL.update(patrimonio);
    }
    
    /**
     * Atualiza os patrimonios na DB correspondentes aos objetos Patrimonio recebidos
     * @param patrimonios
     * @return Retorna o êxito da operação.
     * @author Mei Fagundes
     */
    public static Boolean updateAll(ArrayList<Patrimonio> patrimonios){
        
        Boolean result = false;
        for (Patrimonio patrimonio : patrimonios) {
            result = SQL.update(patrimonio);
        }
        return result;
    }
    
    /**
     * Deleta o Patrimonio do DB através de seu Id.
     * @param patrimonio
     * @return Retorna o êxito da operação.
     * @author Mei Fagundes
     */
    public static Boolean delete(Patrimonio patrimonio){
        
        if (patrimonio.getId() != null) {
            return SQL.delete(patrimonio.getId(), Patrimonio.class);
        } else{
            System.out.println("!!! ERRO !!! Não foi possível deletar pois"
                    + " o Patrimonio recebido não possui um Id definido.");
            return false;
        }
    }
}
