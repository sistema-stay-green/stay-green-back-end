/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package testesVendas;

import br.cefetmg.staygreen.table.Venda;
import br.cefetmg.staygreen.util.SQL;
import br.cefetmg.staygreen.util.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
/**
 * 
 * Classe apenas para testar e exemplificar o uso de uma tabela
 * 
 * @author Paulo Vitor
 */
public class TesteTabelaVendas {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        //insere uma venda na tabela
        SQL.insert(new Venda("temporario", 12.15, Calendar.getInstance(), Double.valueOf(10)));
        
        //exibe todos os dados ta tabela
        ResultSet registros = SQL.query("SELECT * FROM " + SQL.getNomeTabela(Venda.class));
        while (registros.next()) {
            System.out.println("Id: " + registros.getInt("id"));
            System.out.println("Produto: " + registros.getString("produto"));
            System.out.println("Entrada/saída: " + registros.getString("entradaSaida"));
            System.out.println("Data da Transacao: " + Data.dateToCalendar(registros.getDate("dataTransacao")));
            System.out.println("Valor: " + registros.getDouble("valor"));
            System.out.println("");
        }
        
        // exclui o dado criado no inicio do codigo
        registros.previous();
        SQL.delete(registros.getInt(1), Venda.class);
        
    }
    
}
