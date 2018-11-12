/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.table;

import br.cefetmg.staygreen.annotation.Id;
import br.cefetmg.staygreen.annotation.Tabela;
import static br.cefetmg.staygreen.util.Data.dateToCalendar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Gabriel Brandão
 * @version 1.3
 */
@Tabela("tarefa")
public class Tarefa {
    
    //Attributes
    @Id
    private Long idTarefa;
    private String nomeTarefa;
    private String descrTarefa;
    private TarefaTipoEnum tipoTarefa;
    private Calendar dataInicialTarefa;
    private Integer periodRepetTarefa;
    private Double gastoTarefa;
    private Integer quantProduzTarefa;
    private String insumosTarefa;
    private Integer quantInsumosTarefa;
    
    //Constructors
    
    /**
     * Cria uma tarefa com Id e Nome
     * @param idTarefa Id da tarefa
     * @param nomeTarefa Nome da tarefa
     */
    public Tarefa(Long idTarefa, String nomeTarefa){
        this.idTarefa = idTarefa;
        this.nomeTarefa = nomeTarefa;
    }
    
    /**
     * Cria uma tarefa com Nome
     * @param nomeTarefa Nome da tarefa
     */
    public Tarefa(String nomeTarefa){
        this(null, nomeTarefa);
    }
    
    /**
     * Cria uma tarefa com dados null
     */
    public Tarefa(){
        this(null, null);
    }

    /**
     * Cria uma tarefa com todos as colunas preenchidas.
     * @param idTarefa Id da tarefa
     * @param nomeTarefa Nome da tarefa
     * @param descrTarefa descrição da tarefa
     * @param tipoTarefa O tipo da tarefa
     * @param dataInicialTarefa Objeto Calendar com a data marcada para a tarefa
     * @param periodRepetTarefa Número de repetições da tarefa
     * @param gastoTarefa Gasto da tarefa
     * @param quantProduzTarefa
     * @param insumosTarefa
     * @param quantInsumosTarefa
     */
    public Tarefa(Long idTarefa, String nomeTarefa, String descrTarefa, 
            TarefaTipoEnum tipoTarefa, Calendar dataInicialTarefa, 
            Integer periodRepetTarefa, Double gastoTarefa, 
            Integer quantProduzTarefa, String insumosTarefa, 
            Integer quantInsumosTarefa) {
        this.idTarefa = idTarefa;
        this.nomeTarefa = nomeTarefa;
        this.descrTarefa = descrTarefa;
        this.tipoTarefa = tipoTarefa;
        this.dataInicialTarefa = dataInicialTarefa;
        this.periodRepetTarefa = periodRepetTarefa;
        this.gastoTarefa = gastoTarefa;
        this.quantProduzTarefa = quantProduzTarefa;
        this.insumosTarefa = insumosTarefa;
        this.quantInsumosTarefa = quantInsumosTarefa;
    }
    
    /**
     * Cria uma tarefa com dados de um ResultSet
     * @param tarefa ResultSet com os dados
     * @throws SQLException 
     */
    public Tarefa(ResultSet tarefa) throws SQLException {
        this(tarefa.getLong("idTarefa"), tarefa.getString("nomeTarefa"), 
                tarefa.getString("descrTarefa"), TarefaTipoEnum.valueOf(tarefa
                        .getString("tipoTarefa")),
                dateToCalendar(tarefa.getDate("dataInicialTarefa")), 
                tarefa.getInt("periodRepetTarefa"), 
                tarefa.getDouble("gastotarefa"), 
                tarefa.getInt("quantProduzTarefa"), 
                tarefa.getString("insumosTarefa"), 
                tarefa.getInt("quantInsumosTarefa"));
    }
    
    //Getter e Setter

    public Long getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(Long idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public String getDescrTarefa() {
        return descrTarefa;
    }

    public void setDescrTarefa(String descrTarefa) {
        this.descrTarefa = descrTarefa;
    }

    public TarefaTipoEnum getTipoTarefa() {
        return tipoTarefa;
    }

    public void setTipoTarefa(TarefaTipoEnum tipoTarefa) {
        this.tipoTarefa = tipoTarefa;
    }
    
    public void setTipoTarefa(String tipoTarefa) {
        try {
            this.tipoTarefa = TarefaTipoEnum.valueOf(tipoTarefa);
        } catch(IllegalArgumentException exception) {
            System.out.println(exception + " at Tarefa.setTipoTarefa()");
            System.out.println(" !!! ERRO !!! O valor inserido não corresponde "
                    + "a um estado possível de TarefaTipoEnum");
        }
    }

    public Calendar getDataInicialTarefa() {
        return dataInicialTarefa;
    }

    public void setDataInicialTarefa(Calendar dataInicialTarefa) {
        this.dataInicialTarefa = dataInicialTarefa;
    }

    public Integer getPeriodRepetTarefa() {
        return periodRepetTarefa;
    }

    public void setPeriodRepetTarefa(Integer periodRepetTarefa) {
        this.periodRepetTarefa = periodRepetTarefa;
    }

    public Double getGastoTarefa() {
        return gastoTarefa;
    }

    public void setGastoTarefa(Double gastoTarefa) {
        this.gastoTarefa = gastoTarefa;
    }

    public Integer getQuantProduzTarefa() {
        return quantProduzTarefa;
    }

    public void setQuantProduzTarefa(Integer quantProduzTarefa) {
        this.quantProduzTarefa = quantProduzTarefa;
    }

    public String getInsumosTarefa() {
        return insumosTarefa;
    }

    public void setInsumosTarefa(String insumosTarefa) {
        this.insumosTarefa = insumosTarefa;
    }

    public Integer getQuantInsumosTarefa() {
        return quantInsumosTarefa;
    }

    public void setQuantInsumosTarefa(Integer quantInsumosTarefa) {
        this.quantInsumosTarefa = quantInsumosTarefa;
    }

    
}
