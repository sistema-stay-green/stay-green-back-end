/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.table;

import br.cefetmg.staygreen.annotation.Id;
import br.cefetmg.staygreen.annotation.Tabela;
import java.util.Calendar;

/**
 *
 * @author gabri_000
 * @version 1.0
 */
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
     */
    public Tarefa(Long id, String nome){
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
