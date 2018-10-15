package patrimonio_debug_temp;


import br.cefetmg.staygreen.service.PatrimonioAccessService;
import br.cefetmg.staygreen.table.Patrimonio;
import br.cefetmg.staygreen.table.PatrimonioStatusEnum;
import br.cefetmg.staygreen.util.JSON;
import br.cefetmg.staygreen.util.SQL;
import java.util.ArrayList;
import java.util.Calendar;

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
        
        //testGetFromNome();
        //testGetFromId();
        testInsert();

    }
    
    public static void testInsert(){
        
        Patrimonio patrimonio = new Patrimonio(new Long(5),"Trator");
        patrimonio.setTipo("Tipo");
        patrimonio.setDescricao("Descrição");
        // patrimonio.setStatus(PatrimonioStatusEnum.VENDIDO);
        patrimonio.setStatus("VENDIDO");
        patrimonio.setIndiceDepreciacao(1.5);
        patrimonio.setValorCompra(new Double(4000));
        patrimonio.setValorAtual(new Double(3800));
        
        Calendar c = Calendar.getInstance();
        
        patrimonio.setDataCompra(c);
        patrimonio.setDataSaida(c);
        patrimonio.setDataBaixa(c);
        
        PatrimonioAccessService.insert(patrimonio);
    }
    
    public static void testGetFromNome(){
        
        ArrayList<Patrimonio> p = PatrimonioAccessService.getPatrimoniosFromNome("Trator");
        
        System.out.println("Nome: " + p.get(0).getNome());
        System.out.println("Tipo: " + p.get(0).getTipo());
        System.out.println("Descrição: " + p.get(0).getDescricao());
        System.out.println("Status: " + p.get(0).getStatus());
        System.out.println("Índice de Depreciação: " + p.get(0).getIndiceDepreciacao());
        System.out.println("Valor da Compra: " + p.get(0).getValorCompra());
        System.out.println("Valor atual: " + p.get(0).getValorAtual());
        System.out.println("Data da Compra: " + p.get(0).getDataSaida().getTime());
        System.out.println("Data da Saída: " + p.get(0).getDataSaida().getTime());
        System.out.println("Data da Baixa: " + p.get(0).getDataBaixa().getTime());
    }
    
    public static void testGetFromId(){
        
        Patrimonio p = PatrimonioAccessService.getPatrimonioFromId("1");
        
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
