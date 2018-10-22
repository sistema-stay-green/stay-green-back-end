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
 * @version 1.2
 */
@Tabela("tarefa")
public class Tarefa {
    
    //Attributes
    @Id
    private Long id;
    private String nome;
    private String tipo;
    private String caminhoImg;
    private Calendar dataMarcada;
    private Integer repeticao;
    private Double producaoPrevista;
    private Double valorGasto;
    
    //Constructors
    
    /**
     * Cria uma tarefa com Id e Nome
     * @param id Id da tarefa
     * @param nome Nome da tarefa
     */
    public Tarefa(Long id, String nome){
        this.id = id;
        this.nome = nome;
    }
    
    /**
     * Cria uma tarefa com Nome
     * @param nome Nome da tarefa
     */
    public Tarefa(String nome){
        this(null, nome);
    }
    
    /**
     * Cria uma tarefa com dados null
     */
    public Tarefa(){
        this(null, null);
    }

    /**
     * Cria uma tarefa com todos as colunas preenchidas.
     * @param id Id da tarefa
     * @param nome Nome da tarefa
     * @param tipo O tipo da tarefa
     * @param caminhoImg Caminho para a imagem da tarefa
     * @param dataMarcada Objeto Calendar com a data marcada para a tarefa
     * @param repeticao Número de repetições da tarefa
     * @param producaoPrevista Valor previsto para produção
     * @param valorGasto Valor gasto
     */
    public Tarefa(Long id, String nome, String tipo, String caminhoImg, 
            Calendar dataMarcada, Integer repeticao, Double producaoPrevista,
            Double valorGasto) {
        this.id = id;
        this.nome = nome;
        this.caminhoImg = caminhoImg;
        this.dataMarcada = dataMarcada;
        this.repeticao = repeticao;
        this.producaoPrevista = producaoPrevista;
        this.valorGasto = valorGasto;
    }
    
    /**
     * Cria uma tarefa com dados de um ResultSet
     * @param tarefa ResultSet com os dados
     * @throws SQLException 
     */
    public Tarefa(ResultSet tarefa) throws SQLException {
        this(tarefa.getLong("id"), tarefa.getString("nome"), 
                tarefa.getString("tipo"), tarefa.getString("caminhoImg"), 
                dateToCalendar(tarefa.getDate("dataMarcada")), 
                tarefa.getInt("repeticao"), tarefa.getDouble("producaoPrevista"),
                tarefa.getDouble("valorGasto"));
    }
    
    //Getter e Setter

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCaminhoImg() {
        return caminhoImg;
    }

    public Calendar getDataMarcada() {
        return dataMarcada;
    }

    public Integer getRepeticao() {
        return repeticao;
    }

    public Double getProducaoPrevista() {
        return producaoPrevista;
    }

    public Double getValorGasto() {
        return valorGasto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCaminhoImg(String caminhoImg) {
        this.caminhoImg = caminhoImg;
    }

    public void setDataMarcada(Calendar dataMarcada) {
        this.dataMarcada = dataMarcada;
    }

    public void setRepeticao(Integer repeticao) {
        this.repeticao = repeticao;
    }

    public void setProducaoPrevista(Double producaoPrevista) {
        this.producaoPrevista = producaoPrevista;
    }

    public void setValorGasto(Double valorGasto) {
        this.valorGasto = valorGasto;
    }
}
