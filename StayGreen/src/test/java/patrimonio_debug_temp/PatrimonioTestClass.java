package patrimonio_debug_temp;

import br.cefetmg.staygreen.service.PatrimonioAccessService;
import br.cefetmg.staygreen.service.PatrimonioProcessService;
import br.cefetmg.staygreen.table.Patrimonio;
import br.cefetmg.staygreen.table.PatrimonioData;
import br.cefetmg.staygreen.table.PatrimonioTipoEnum;
import br.cefetmg.staygreen.util.Data;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */

/**
 *
 * @author Mei Fagundes
 * @version 10-10-18/21:45
 */
public class PatrimonioTestClass {
    
    public static void main(String[] args){
        
        //testGetFromNome("Trator");
        //testGetFromId(1);
        //testInsert("Nome");
        //testDelete(2);
        //testCompraPatrimonio("Machine", PatrimonioTipoEnum.MAQUINA, "Maquinar coisas", b, a, dataCompra);
        //testGetCalendarFromDateTimeString();
        //testEmbedPatrimonioData();
    }
    
    public static void testEmbedPatrimonioData(){
        
        Patrimonio p = new Patrimonio();
        PatrimonioData pD = new PatrimonioData();
        
        pD.setDataBaixa("1999-08-03-09-25");
        p.embedPatrimonioData(pD);
        
        System.out.println(p.getDataBaixa());
    }
    
    public static void testGetCalendarFromDateTimeString(){
        
        try {

            Calendar a = Data.getCalendarFromDateTimeString("1999-08-03-09-25");
            System.out.println(a);

            a = Data.getCalendarFromDateString("1999-08-03");
            System.out.println(a);
        } catch (ParseException e) {
        }
    }
    
    public static void testDelete(Integer id){
        
        Patrimonio patrimonio = new Patrimonio(id,"Trator");
        patrimonio.setTipo("Tipo");
        patrimonio.setFinalidade("Descrição");
        
        // Opção 1:
        //patrimonio.setStatus(PatrimonioStatusEnum.VENDIDO);
        // Opção 2:
        patrimonio.setStatus("VENDIDO");
        
        patrimonio.setIndiceDepreciacao(1.5);
        patrimonio.setValorCompra(new Double(4000));
        
        Calendar c = Calendar.getInstance();
        
        patrimonio.setDataCompra(c);
        patrimonio.setDataSaida(c);
        patrimonio.setDataBaixa(c);
        
        System.out.println(patrimonio.getId());
        
        PatrimonioAccessService.delete(patrimonio);
    }
    
    public static void testInsert(String nome){
        
        Patrimonio patrimonio = new Patrimonio(nome);
        patrimonio.setTipo("OUTROS");
        patrimonio.setFinalidade("Descrição");
        
        // Opção 1:
        //patrimonio.setStatus(PatrimonioStatusEnum.VENDIDO);
        // Opção 2:
        patrimonio.setStatus("VENDIDO");
        
        patrimonio.setIndiceDepreciacao(1.5);
        patrimonio.setValorCompra(new Double(4000));
        
        Calendar c = Calendar.getInstance();
        
        patrimonio.setDataCompra(c);
        patrimonio.setDataSaida(c);
        patrimonio.setDataBaixa(c);
        
        PatrimonioAccessService.insert(patrimonio);
        
    }
    
    public static void testGetFromNome(String nome){
        
        ArrayList<Patrimonio> p = PatrimonioAccessService.getPatrimoniosByNome(nome);
        
        System.out.println("Nome: " + p.get(0).getNome());
        System.out.println("Tipo: " + p.get(0).getTipo());
        System.out.println("Descrição: " + p.get(0).getFinalidade());
        System.out.println("Status: " + p.get(0).getStatus());
        System.out.println("Índice de Depreciação: " + p.get(0).getIndiceDepreciacao());
        System.out.println("Valor da Compra: " + p.get(0).getValorCompra());
        System.out.println("Data da Compra: " + p.get(0).getDataSaida().getTime());
        System.out.println("Data da Saída: " + p.get(0).getDataSaida().getTime());
        System.out.println("Data da Baixa: " + p.get(0).getDataBaixa().getTime());
    }
    
    public static void testGetFromId(Integer id){
        
        Patrimonio p = PatrimonioAccessService.getPatrimonioById(id.toString());
        
        System.out.println("Nome: " + p.getNome());
        System.out.println("Tipo: " + p.getTipo());
        System.out.println("Descrição: " + p.getFinalidade());
        System.out.println("Status: " + p.getStatus());
        System.out.println("Índice de Depreciação: " + p.getIndiceDepreciacao());
        System.out.println("Valor da Compra: " + p.getValorCompra());
        System.out.println("Data da Compra: " + p.getDataSaida().getTime());
        System.out.println("Data da Saída: " + p.getDataSaida().getTime());
        System.out.println("Data da Baixa: " + p.getDataBaixa().getTime());
    }
    
    public static void testCompraPatrimonio(String nome, PatrimonioTipoEnum tipo, String finalidade, Double indiceDeprec, Double valor, Calendar dataCompra){
        PatrimonioProcessService.compraPatrimonio(nome, tipo, finalidade, indiceDeprec, valor, dataCompra);
    }
}
