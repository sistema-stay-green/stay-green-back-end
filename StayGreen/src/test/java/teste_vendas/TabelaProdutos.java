/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package teste_vendas;

import br.cefetmg.staygreen.table.Produto;
import br.cefetmg.staygreen.table.VendaUsuario;
import br.cefetmg.staygreen.util.JSON;
import br.cefetmg.staygreen.util.SQL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
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
        System.out.println(JSON.stringify(produtos));
        Produto[] a = JSON.parse(JSON.stringify(produtos), Produto[].class);
        System.out.println(Arrays.toString(a));
        List<VendaUsuario> vendas = (List<VendaUsuario>) SQL.getRegistros(VendaUsuario.class);
        System.out.println(vendas);
    }
    
}
