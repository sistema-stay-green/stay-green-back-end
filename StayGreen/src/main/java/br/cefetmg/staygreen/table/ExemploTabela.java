/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.table;

import br.cefetmg.staygreen.annotation.Id;
import br.cefetmg.staygreen.annotation.Tabela;

/**
 * Classe de tabela para exemplo, sem utilidade para o projeto.
 * 
 * @author Daniel
 */
@Tabela("exemplo_tabela")
public class ExemploTabela {
    
    @Id
    private Long id;
    private String campoExemplo;

    public ExemploTabela(Long id, String campoExemplo) {
        this.id = id;
        this.campoExemplo = campoExemplo;
    }
    
    public ExemploTabela(String campoExemplo) {
        this(null, campoExemplo);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCampoExemplo(String campoExemplo) {
        this.campoExemplo = campoExemplo;
    }

    public Long getId() {
        return id;
    }

    public String getCampoExemplo() {
        return campoExemplo;
    }    
    
}
