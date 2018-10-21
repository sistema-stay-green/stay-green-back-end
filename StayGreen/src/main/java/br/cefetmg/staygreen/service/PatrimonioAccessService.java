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
 *
 * @author Mei
 * @version 10-10-18/18:29
 */
public class PatrimonioAccessService {
    
    // Atributos estáticos
    
    // Objetos de manipulação interna.
    private static ResultSet result;
    
    // Constantes que representam os nomes das colunas na tabela do DB SQL.
    private static final String ID_COLUMN;
    private static final String NOME_COLUMN;
    private static final String TIPO_COLUMN;
    private static final String DESCRICAO_COLUMN;
    private static final String STATUS_COLUMN;
    private static final String INDICE_DEPRECIACAO_COLUMN;
    private static final String VALOR_COMPRA_COLUMN;
    private static final String VALOR_ATUAL_COLUMN;
    private static final String DATA_COMPRA_COLUMN;
    private static final String DATA_SAIDA_COLUMN;
    private static final String DATA_BAIXA_COLUMN;
    
    private static final String TABLE_NAME;
    
    // inicialização das constantes internas.
    static {
        ID_COLUMN = "id";
        NOME_COLUMN = "nome";
        TIPO_COLUMN = "tipo";
        DESCRICAO_COLUMN = "descricao";
        STATUS_COLUMN = "status";
        INDICE_DEPRECIACAO_COLUMN = "indiceDepreciacao";
        VALOR_COMPRA_COLUMN = "valorCompra";
        VALOR_ATUAL_COLUMN = "valorAtual";
        DATA_COMPRA_COLUMN = "dataCompra";
        DATA_SAIDA_COLUMN = "dataSaida";
        DATA_BAIXA_COLUMN = "dataBaixa";
        
        TABLE_NAME = SQL.getNomeTabela(Patrimonio.class);
    }
    
    /**
     * Pesquisa na DB 'staygreen' usando o Id recebido.
     * @param id
     * @return Retorna um objeto Patrimonio que corresponde ao Id recebido.
     */
    public static Patrimonio getPatrimonioById(String id){
        
        ArrayList<Patrimonio> patrimonios = get("WHERE id=" + id);
        
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
     */
    public static ArrayList<Patrimonio> getPatrimoniosByNome(String nome){
        
        ArrayList<Patrimonio> patrimonios = get("WHERE nome='" + nome + "'");
        
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
     */
    public static ArrayList<Patrimonio> getPatrimoniosByTipo(String tipo){
        
        ArrayList<Patrimonio> patrimonios = get("WHERE tipo='" + tipo + "'");
        
        if (patrimonios == null){
            System.out.println("!!! AVISO !!! Nenhum patrimonio encontrado com o Tipo: " + tipo);
            return null;
        }
        
        return patrimonios;
    }
    
    /**
     * Pesquisa na DB 'staygreen' usando a queryCondition recebida.
     * @param queryCondition
     * @return Retorna objetos Patrimonio resultantes da queryCondition de pesquisa recebida.
     */
    public static ArrayList<Patrimonio> get(String queryCondition){
        
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
                        patrimonio.setDescricao(
                                result.getString(DESCRICAO_COLUMN));
                        patrimonio.setStatus(
                                result.getString(STATUS_COLUMN));
                        patrimonio.setIndiceDepreciacao(
                                result.getDouble(INDICE_DEPRECIACAO_COLUMN));
                        patrimonio.setValorCompra(
                                result.getDouble(VALOR_COMPRA_COLUMN));
                        patrimonio.setValorAtual(
                                result.getDouble(VALOR_ATUAL_COLUMN));
                        patrimonio.setDataCompra(
                                result.getDate(DATA_COMPRA_COLUMN));
                        patrimonio.setDataSaida(
                                result.getDate(DATA_SAIDA_COLUMN));
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
     * Insere um objeto Patrimonio na DB 'staygreen'
     * @param patrimonio
     */
    public static void insert(Patrimonio patrimonio){
        
            SQL.insert(patrimonio);
    }
    
    /**
     * Insere vários objetos Patrimonio na DB 'staygreen'
     * @param patrimonios
     */
    public static void insertAll(ArrayList<Patrimonio> patrimonios){
        
        for (Patrimonio patrimonio : patrimonios) {
            insert(patrimonio);
        }
    }
    
    /**
     * Atualiza o patrimonio na DB correspondente ao objeto Patrimonio recebido
     * @param patrimonio
     */
    public static void update(Patrimonio patrimonio){
        
            SQL.update(patrimonio);
    }
    
    /**
     * Atualiza os patrimonios na DB correspondentes aos objetos Patrimonio recebidos
     * @param patrimonios
     */
    public static void updateAll(ArrayList<Patrimonio> patrimonios){
        
        for (Patrimonio patrimonio : patrimonios) {
            SQL.update(patrimonio);
        }
    }
    
    /**
     * Deleta o Patrimonio do DB através de seu Id.
     * @param patrimonio
     */
    public static void delete(Patrimonio patrimonio){
        
        if (patrimonio.getId() != null) {
            SQL.delete(patrimonio.getId().intValue(), Patrimonio.class);
        } else{
            System.out.println("!!! ERRO !!! Não foi possível deletar pois"
                    + " o Patrimonio recebido não possui um Id definido.");
        }
    }
}
