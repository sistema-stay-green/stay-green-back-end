/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package teste_vendas;

import br.cefetmg.staygreen.table.Produto;
import br.cefetmg.staygreen.util.Reflection;
import br.cefetmg.staygreen.util.SQL;
import java.util.List;
/**
 *
 * @author pvito
 */
public class TabelaProdutos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Produto> produtos = (List<Produto>) SQL.getRegistros(Produto.class);
        System.out.println(produtos);
    }
    
}
