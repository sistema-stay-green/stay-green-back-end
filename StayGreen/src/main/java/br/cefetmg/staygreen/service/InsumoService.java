/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Insumo;
import br.cefetmg.staygreen.table.Transacao;
import br.cefetmg.staygreen.util.SQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author Arthur
 * @coop Mei
 */
public class InsumoService {
    // Objetos de manipulação interna

    private static ResultSet result;

    // Constantes que representam os nomes das colunas na tabela do DB SQL.
    private static final String ID_COLUMN;
    private static final String NOME_INSUMO_COLUMN;
    private static final String FINALIDADE_INSUMO_COLUMN;
    private static final String VALOR_COMPRA_INSUMO_COLUMN;
    private static final String QUANTIDADE_ESTOQUE_INSUMO_COLUMN;
    private static final String PONTO_AVISO_INSUMO_COLUMN;

    private static final String TABLE_NAME;

    // inicialização das constantes internas.
    static {
        ID_COLUMN = "idInsumo";
        NOME_INSUMO_COLUMN = "nomeInsumo";
        FINALIDADE_INSUMO_COLUMN = "finalidadeInsumo";
        VALOR_COMPRA_INSUMO_COLUMN = "valorCompraInsumo";
        QUANTIDADE_ESTOQUE_INSUMO_COLUMN = "quantEstoqueInsumo";
        PONTO_AVISO_INSUMO_COLUMN = "pontoAvisoInsumo";
        TABLE_NAME = SQL.getNomeTabela(Insumo.class);
    }

    /**
     * Método que busca um insumo no BD pelo NOME.
     *
     * @param nome
     * @return Obejeto do tipo Insumo com a primeira ocorrencia do NOME passado
     * por parâmetro.
     */
    public static Insumo getInsumoPorNome(String nome) {

        ArrayList<Insumo> insumos = get("WHERE `nomeInsumo`='" + nome + "'");
        if (insumos == null) {
            System.out.println("NENHUM INSUMO COM NOME " + nome
                    + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return insumos.get(0);

    }

    /**
     * Método que busca um insumo no BD pelo ID.
     *
     * @param id
     * @return Obejeto do tipo Insumo com a primeira ocorrencia da ID passada
     * por parâmetro.
     */
    public static Insumo getInsumoPorId(String id) {

        ArrayList<Insumo> insumos = get("WHERE `idInsumo`='" + id + "'");

        if (insumos == null) {
            System.out.println("NENHUM INSUMO COM ID " + id
                    + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return insumos.get(0);
    }

    /**
     * Método que busca vários insumos no BD pelo NOME.
     *
     * @param nome
     * @return ArrayList de objetos do tipo Insumo com todas as ocorrencias do
     * NOME passado por parâmetro.
     */
    public static ArrayList<Insumo> getInsumosPorNome(String nome) {

        ArrayList<Insumo> insumos = get("WHERE `nomeInsumo`='" + nome + "'");

        if (insumos == null) {
            System.out.println("NENHUM INSUMO COM NOME " + nome
                    + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return insumos;
    }

    /**
     * Método que busca vários insumos no BD pelo ID.
     *
     * @param id
     * @return ArrayList de objetos do tipo Insumo com todas as ocorrencias da
     * ID passada por parâmetro.
     */
    public static ArrayList<Insumo> getInsumosPorId(Long id) {

        ArrayList<Insumo> insumos = get("WHERE `idInsumo`='" + id + "'");

        if (insumos == null) {
            System.out.println("NENHUM INSUMO COM ID " + id
                    + " FOI ENCONTRADO NO BANCO DE DADOS");
            return null;
        }

        return insumos;
    }

    /**
     * Método que busca vários insumos no BD pelo CONDICAO.
     *
     * @param condicao
     * @return ArrayList de objetos do tipo Insumo com todas as ocorrencias da
     * CONDICAO passado por parâmetro.
     */
    public static ArrayList<Insumo> get(String condicao) {

        ArrayList<Insumo> insumos = new ArrayList<>();

        try {

            result = SQL.query("SELECT * FROM " + TABLE_NAME
                    + " " + condicao);

            if (result.next()) {
                do {
                    Insumo insumo = new Insumo();
                    insumo.setIdInsumo(Long.parseLong(
                            result.getString(ID_COLUMN)));
                    insumo.setNomeInsumo(
                            result.getString(NOME_INSUMO_COLUMN));
                    insumo.setFinalidadeInsumo(
                            result.getString(FINALIDADE_INSUMO_COLUMN));
                    insumo.setValorCompraInsumo(Double.parseDouble(
                            result.getString(VALOR_COMPRA_INSUMO_COLUMN)));
                    insumo.setQuantEstoqueInsumo(Integer.parseInt(
                            result.getString(QUANTIDADE_ESTOQUE_INSUMO_COLUMN)));
                    insumo.setPontoAvisoInsumo(Integer.parseInt(
                            result.getString(PONTO_AVISO_INSUMO_COLUMN)));
                    insumos.add(insumo);

                } while (result.next());
            } else {
                System.out.println("NENHUM INSUMO COM CONDIÇÃO: "
                        + condicao + " FOI ENCONTRADO NO BANCO DE DADOS");
                return null;
            }

        } catch (SQLException ex) {
            System.out.println(ex + " at getRowFromId");
            return null;
        }

        return insumos;
    }

    /**
     * Método para adicionar um insumo no BD.
     *
     * @param insumo
     * @return True ou False, dependendo do sucesso com a conexão com BD.
     */
    public static boolean AdicionarInsumo(Insumo insumo) {
        if (SQL.insert(insumo)) {
            Transacao transacao = new Transacao();
            if (SQL.getLastInsertId() != null) {
                transacao.setIdItemTransacao(SQL.getLastInsertId().longValue());
            } else {
                return false;
            }
            transacao.setDataTransacao(Calendar.getInstance());
            transacao.setQuantTransacao(insumo.getQuantEstoqueInsumo());
            transacao.setValorTransacao(insumo.getValorCompraInsumo()
                    * insumo.getQuantEstoqueInsumo() * -1);
            transacao.setTipoTransacao("INSUMO");
            return TransacaoService.AdicionarTransacao(transacao);
        } else {
            return false;
        }
    }

    /**
     * Método para atualizar um insumo no BD.
     *
     * @param insumo
     * @return True ou False, dependendo do sucesso com a conexão com BD.
     */
    public static boolean atualizarInsumo(Insumo insumo) {
        Insumo in = InsumoService.getInsumoPorId(
                String.valueOf(insumo.getIdInsumo()));
        if (!Objects.equals(in.getQuantEstoqueInsumo(),
                insumo.getQuantEstoqueInsumo())) {
            Transacao transacao = new Transacao();
            transacao.setDataTransacao(Calendar.getInstance());
            transacao.setIdItemTransacao(insumo.getIdInsumo());
            if(in.getQuantEstoqueInsumo().equals(-1)){
                in.setQuantEstoqueInsumo(0);
                in.setValorCompraInsumo(0.0);
                in.setPontoAvisoInsumo(0);
            }
            int aux = insumo.getQuantEstoqueInsumo()
                    - in.getQuantEstoqueInsumo();                    
            if(aux > 0){
                transacao.setValorTransacao(aux
                        * insumo.getValorCompraInsumo() * -1);
                transacao.setQuantTransacao(aux);
            }else{
                transacao.setValorTransacao(0);
                transacao.setQuantTransacao(aux);
            }
            transacao.setTipoTransacao("INSUMO");
            if (TransacaoService.AdicionarTransacao(transacao)) {
                return SQL.update(insumo);
            } else {
                return false;
            }
        } else {
            return SQL.update(insumo);
        }

    }

    /**
     * Método para remover um insumo no BD.
     *
     * @param insumo
     * @return True ou False, dependendo do sucesso com a conexão com BD.
     */
    public static boolean deletarInsumo(Insumo insumo) {
        if (insumo.getIdInsumo() != null) {
            Transacao transacao = new Transacao();
            transacao.setDataTransacao(Calendar.getInstance());
            transacao.setIdItemTransacao(insumo.getIdInsumo());
            transacao.setQuantTransacao(insumo.getQuantEstoqueInsumo() * -1);
            transacao.setValorTransacao(0);
            transacao.setTipoTransacao("INSUMO");
            insumo.setPontoAvisoInsumo(-1);
            insumo.setQuantEstoqueInsumo(-1);
            insumo.setValorCompraInsumo(-1.0);
            if (SQL.update(insumo)) {
                return TransacaoService.AdicionarTransacao(transacao);
            } else {
                return false;
            }
        } else {
            System.out.println("NÃO FOI POSSIVEL DELETAR O INSUMO,"
                    + "ID INVÁLIDO");
            return false;
        }

    }

    /**
     * Método para remover todos os insumos do BD.
     *
     * @return True ou False, dependendo do sucesso com a conexão com BD.
     */
    public static boolean deletarInsumoTodos() {
        ArrayList<Insumo> insumos = get("");
        int j = 0;
        if (insumos != null) {
            for (int i = 0; i < insumos.size(); i++) {
                if (deletarInsumo(insumos.get(i))) {
                    j++;
                }
            }
            return j == insumos.size();
        } else {
            System.out.println("Banco de dados já está vazio");
            return false;
        }

    }

    /**
     * Método para remover um insumo no BD.
     *
     * @param insumo
     * @return True ou False, dependendo do sucesso com a conexão com BD.
     */
    public static boolean deletarInsumoDefinitivo(Insumo insumo) {
        if (insumo.getIdInsumo() != null) {
            ArrayList<Transacao> transacoes = TransacaoService.get(
                    "WHERE `idItemTransacao`='" + insumo.getIdInsumo() + "'");
            int j = 0;
            for (int i = 0; i < transacoes.size(); i++) {
                if (TransacaoService.deletarTransacao(transacoes.get(i))) {
                    if (SQL.delete((int) insumo.getIdInsumo().longValue(),
                            Insumo.class)) {
                        j++;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return j == transacoes.size();
        } else {
            System.out.println("NÃO FOI POSSIVEL DELETAR O INSUMO,"
                    + "ID INVÁLIDO");
            return false;
        }
    }

    /**
     * Método para remover todos os insumos do BD.
     *
     * @return True ou False, dependendo do sucesso com a conexão com BD.
     */
    public static boolean deletarInsumoTodosDefinitivo() {
        ArrayList<Insumo> insumos = get("");
        int j = 0;
        if (insumos != null) {
            for (int i = 0; i < insumos.size(); i++) {
                if (deletarInsumoDefinitivo(insumos.get(i))) {
                    j++;
                }
            }
            return j == insumos.size();
        } else {
            System.out.println("Banco de dados já está vazio");
            return false;
        }

    }

}
