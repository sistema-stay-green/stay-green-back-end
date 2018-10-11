package patrimonio_debug_temp;


import br.cefetmg.staygreen.service.PatrimonioAccessService;
import br.cefetmg.staygreen.table.Patrimonio;

/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */

/**
 *
 * @author Mei
 * @version 10-10-18/21:45
 */
public class PatrimonioTestClass {
    
    public static void main(String[] args){
        
        Patrimonio p = PatrimonioAccessService.getRowFromId("1");
        
        System.out.println("Nome: " + p.getNome());
        System.out.println("Tipo: " + p.getTipo());
        System.out.println("Descrição: " + p.getDescricao());
        System.out.println("Status: " + p.getStatus());
        System.out.println("Índice de Depreciação: " + p.getIndiceDepreciacao());
        System.out.println("Valor da Compra: " + p.getValorCompra());
        System.out.println("Valor atual: " + p.getValorAtual());
        System.out.println("Data da Compra: " + p.getDataSaida().getTime());
        System.out.println("Data da Saída: " + p.getDataSaida().getTime());
        System.out.println("Data da Baixa: " + p.getDataBaixa().getTime());
    }
}
