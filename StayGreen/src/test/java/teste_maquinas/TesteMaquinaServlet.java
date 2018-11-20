/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package teste_maquinas;

import br.cefetmg.staygreen.service.MaquinasService;
import br.cefetmg.staygreen.service.PatrimonioAccessService;
import br.cefetmg.staygreen.service.PatrimonioProcessService;
import br.cefetmg.staygreen.service.TransacaoEAluguelService;
import br.cefetmg.staygreen.table.Patrimonio;
import br.cefetmg.staygreen.table.PatrimonioData;
import br.cefetmg.staygreen.table.PatrimonioStatusEnum;
import br.cefetmg.staygreen.table.PatrimonioTipoEnum;
import br.cefetmg.staygreen.util.Data;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Gabriel
 */
public class TesteMaquinaServlet {
    private static Patrimonio maquina, maquina1, maquina2, maquina3;
    public static void main(String[] args){
        System.out.println("\n\n\n\n\n\tTESTES\n\n");
        declaraMaquinas();
        testeCadastrar();
        testeEditar();
        testeVenda();
        testeManutenir();
        testeDescartar();
        testeManutenir();
    }
    
    public static void declaraMaquinas(){
        maquina = new Patrimonio(null, "Colheitadeira");
        maquina.setDataBaixa((Calendar)null);
        maquina.setDataCompra((Calendar)null);
        maquina.setDataSaida((Calendar)null);
        maquina.setDataRetorno((Calendar)null);
        maquina.setFinalidade("Colheitar");
        maquina.setIndiceDepreciacao(2.0);
        maquina.setValorCompra(80000.0);
        maquina.setTipo(PatrimonioTipoEnum.MAQUINA);
        maquina.setStatus(PatrimonioStatusEnum.ALUGADO);
        
        maquina1 = new Patrimonio(null, "MAQUINA 1");
        maquina1.setDataBaixa((Calendar)null);
        maquina1.setDataCompra((Calendar)null);
        maquina1.setDataSaida((Calendar)null);
        maquina1.setDataRetorno((Calendar)null);
        maquina1.setFinalidade("Ajudar na colheita");
        maquina1.setIndiceDepreciacao(7.0);
        maquina1.setValorCompra(10000.0);
        maquina1.setTipo(PatrimonioTipoEnum.MAQUINA);
        maquina1.setStatus(PatrimonioStatusEnum.EM_POSSE);
        
        maquina2 = new Patrimonio(null, "MAQUINA 2");
        maquina2.setDataBaixa((Calendar)null);
        maquina2.setDataCompra((Calendar)null);
        maquina2.setDataSaida((Calendar)null);
        maquina2.setDataRetorno((Calendar)null);
        maquina2.setFinalidade("Ajudar na colheita 2");
        maquina2.setIndiceDepreciacao(2.0);
        maquina2.setValorCompra(5000.0);
        maquina2.setTipo(PatrimonioTipoEnum.MAQUINA);
        maquina2.setStatus(PatrimonioStatusEnum.EM_POSSE);
        
        maquina3 = new Patrimonio(null, "MAQUINA QUE NÃO ESTA FUNCIONANDO");
        maquina3.setDataBaixa((Calendar)null);
        maquina3.setDataCompra((Calendar)null);
        maquina3.setDataSaida((Calendar)null);
        maquina3.setDataRetorno((Calendar)null);
        maquina3.setFinalidade("Tinha uma função, mas deixou de exerce-la quand"
                + "o estragou");
        maquina3.setIndiceDepreciacao(0.0);
        maquina3.setValorCompra(100.0);
        maquina3.setTipo(PatrimonioTipoEnum.MAQUINA);
        maquina3.setStatus(PatrimonioStatusEnum.EM_POSSE);
    }
    
    public static void testeCadastrar(){
        
        System.out.println("\n\n\tTESTE CADASTRAR TODAS:");
        
        MaquinasService.Cadastrar(maquina, 1, TransacaoEAluguelService.
                converteStringToCalendar("12/11/2012"));
        MaquinasService.Cadastrar(maquina1, 1, Calendar.getInstance());
        MaquinasService.Cadastrar(maquina2, 1, TransacaoEAluguelService.
                converteStringToCalendar("20/10/2017"));
        MaquinasService.Cadastrar(maquina3, 1, TransacaoEAluguelService.
                converteStringToCalendar("02/04/1996"));
        
        System.out.println("\nMaquina 0:");
        imprimeMaquina(maquina);
        System.out.println("\nMaquina 1:");
        imprimeMaquina(maquina1);
        System.out.println("\nMaquina 2:");
        imprimeMaquina(maquina2);
        System.out.println("\nMaquina 3:");
        imprimeMaquina(maquina3);
        
    }
    
    public static void testeEditar(){
        
        System.out.println("\n\n\tTESTE EDITAR/ALUGAR 0:");
        
        Patrimonio maquinaAtualizada = new Patrimonio(null, null);
        maquinaAtualizada.setDataBaixa((Calendar)null);
        maquinaAtualizada.setDataCompra((Calendar)null);
        maquinaAtualizada.setDataSaida(TransacaoEAluguelService.converteStringToCalendar("12/11/2017"));
        maquinaAtualizada.setDataRetorno(TransacaoEAluguelService.converteStringToCalendar("12/11/2019"));
        maquinaAtualizada.setFinalidade(null);
        maquinaAtualizada.setIndiceDepreciacao(null);
        maquinaAtualizada.setValorCompra(null);
        maquinaAtualizada.setTipo((PatrimonioTipoEnum)null);
        maquinaAtualizada.setStatus(PatrimonioStatusEnum.ALUGADO);
        MaquinasService.Editar(maquina, maquinaAtualizada);
        
        System.out.println("\nMaquina 0:");
        imprimeMaquina(maquina);
        System.out.println("\n\tStatus da Maquina 0:"+maquina.getStatus());
        System.out.println("\nMaquina 1:");
        imprimeMaquina(maquina1);
        System.out.println("\nMaquina 2:");
        imprimeMaquina(maquina2);
        System.out.println("\nMaquina 3:");
        imprimeMaquina(maquina3);
        
    }
    
    public static void testeVenda(){
        
        System.out.println("\n\n\tTESTE VENDA 2:");
        
        MaquinasService.Venda(maquina2, TransacaoEAluguelService.converteStringToCalendar("25/11/2018"));
        
        System.out.println("\nMaquina 0:");
        imprimeMaquina(maquina);
        System.out.println("\nMaquina 1:");
        imprimeMaquina(maquina1);
        System.out.println("\nMaquina 2:");
        imprimeMaquina(maquina2);
        System.out.println("\n\tStatus da Maquina 2:"+maquina2.getStatus());
        System.out.println("\nMaquina 3:");
        imprimeMaquina(maquina3);
        
    }
    
    public static void testeManutenir(){
        
        System.out.println("\n\n\tTESTE MANUTENIR 1:");
        
        MaquinasService.Manuntenir(maquina1, TransacaoEAluguelService.converteStringToCalendar("01/08/2020"));
        
        System.out.println("\nMaquina 0:");
        imprimeMaquina(maquina);
        System.out.println("\nMaquina 1:");
        imprimeMaquina(maquina1);
        System.out.println("\n\tStatus da Maquina 1:"+maquina1.getStatus());
        System.out.println("\nMaquina 2:");
        imprimeMaquina(maquina2);
        System.out.println("\nMaquina 3:");
        imprimeMaquina(maquina3);
        
    }
    
    public static void testeDescartar(){
        
        System.out.println("\n\n\tTESTE DESCARTAR 3:");

        MaquinasService.Descarte(maquina3);
        
        System.out.println("\nMaquina 0:");
        imprimeMaquina(maquina);
        System.out.println("\nMaquina 1:");
        imprimeMaquina(maquina1);
        System.out.println("\nMaquina 2:");
        imprimeMaquina(maquina2);
        System.out.println("\nMaquina 3:");
        imprimeMaquina(maquina3);
        System.out.println("\n\tStatus da Maquina 3:"+maquina3.getStatus());
        
    }
    
    public static void imprimeMaquina(Patrimonio maquina){
                
        System.out.println("ID: " + maquina.getId());
        System.out.println("Nome: " + maquina.getNome());
        System.out.println("Tipo: " + maquina.getTipo());
        System.out.println("Descrição: " + maquina.getFinalidade());
        System.out.println("Status: " + maquina.getStatus());
        System.out.println("Índice de Depreciação: " + maquina.getIndiceDepreciacao());
        System.out.println("Valor da Compra: " + maquina.getValorCompra());
        System.out.println("Data de Compra: " + maquina.getDataSaida());
        System.out.println("Data de Saída: " + maquina.getDataSaida());
        System.out.println("Data de Baixa: " + maquina.getDataBaixa());
        System.out.println("Data de Retrono: " + maquina.getDataRetorno());
    }
}
