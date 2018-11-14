/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.table.Patrimonio;
import br.cefetmg.staygreen.table.PatrimonioStatusEnum;
import br.cefetmg.staygreen.table.PatrimonioTipoEnum;
import br.cefetmg.staygreen.util.JSON;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe com a lógica principal de manipulação dos patrimônios da empresa.
 *
 * @author Mei
 */
public class PatrimonioProcessService {

    private static final String FIELD_SEPARATOR;
    private static final String OBJECT_SEPARATOR;

    static {
        FIELD_SEPARATOR = "§";
        OBJECT_SEPARATOR = "¢";
    }

    @Deprecated
    public static String getPatrimonioByNome(String name) {

        ArrayList<Patrimonio> patrimonios = new ArrayList<>();

        patrimonios = PatrimonioAccessService.getPatrimoniosByNome(name);

        return stringfy(patrimonios);

    }

    /**
     * Concatenates values from objects Patrimonio into a new String
     *
     * @param patrimonios
     * @return String with all values from Patrimonio Objects.
     */
    @Deprecated
    public static String stringfy(ArrayList<Patrimonio> patrimonios) {

        String output = new String();

        for (Patrimonio patrimonio : patrimonios) {

            output += stringfy(patrimonio) + OBJECT_SEPARATOR;
        }

        return output;
    }

    /**
     * Concatenates values from a object Patrimonio into a new String
     *
     * @param patrimonio
     * @return String with all values from a Patrimonio Object.
     */
    @Deprecated
    public static String stringfy(Patrimonio patrimonio) {

        String output = new String();

        output += patrimonio.getId().toString() + FIELD_SEPARATOR;
        output += patrimonio.getNome() + FIELD_SEPARATOR;
        output += patrimonio.getTipo() + FIELD_SEPARATOR;
        output += patrimonio.getFinalidade() + FIELD_SEPARATOR;
        output += patrimonio.getStatus().toString() + FIELD_SEPARATOR;
        output += patrimonio.getIndiceDepreciacao().toString() + FIELD_SEPARATOR;
        output += patrimonio.getValorCompra().toString() + FIELD_SEPARATOR;
        output += patrimonio.getDataCompra().getTime().toString() + FIELD_SEPARATOR;
        output += patrimonio.getDataSaida().getTime().toString() + FIELD_SEPARATOR;
        output += patrimonio.getDataBaixa().getTime().toString();

        return output;
    }

    public static String compraPatrimonio(Patrimonio patrimonio) {
        String resposta = new String();
        if (patrimonio != null) {
            if (PatrimonioAccessService.insert(patrimonio)) {
                patrimonio = PatrimonioAccessService.
                        getLastInsertedPatrimonio();
                resposta = JSON.stringify(patrimonio);
            } else {
                resposta = "F";
            }
        } else {
            resposta = "F";
        }

        return resposta;
    }

    public static String pesquisaPatrimonio(String tipoPesquisa, String formaPesquisa) {
        String resposta = new String();
        ArrayList<Patrimonio> patrimonios = new ArrayList<>();
        switch (tipoPesquisa) {
            case "id":
                patrimonios.add(PatrimonioAccessService.
                        getPatrimonioById(formaPesquisa));

                if (patrimonios != null) {
                    resposta += JSON.stringify(patrimonios);
                } else {
                    resposta = "N";
                }
                break;

            case "nome":
                patrimonios = PatrimonioAccessService.
                        getPatrimoniosByNome(formaPesquisa);

                if (patrimonios != null) {
                    resposta += JSON.stringify(patrimonios);
                } else {
                    resposta = "N";
                }
                break;

            default:
                throw new IllegalArgumentException("Parametro 's' possui um valor inválido.");
        }
        return resposta;
    }

    public static String retornaTodosPatrimonio() {
        String resposta = new String();
        ArrayList<Patrimonio> patrimonios;
        patrimonios = PatrimonioAccessService.getAll();
        if (patrimonios != null) {
            resposta = JSON.stringify(patrimonios);
        }
        return resposta;
    }

    public static String atualizaPatrimonio(Patrimonio patrimonio) {
        String resposta = new String();
        if (patrimonio != null) {
            if (PatrimonioAccessService.update(patrimonio)) {
                resposta = "S";
            } else {
                resposta = "N";
            }
        } else {
            resposta = "F";
        }
        return resposta;
    }

    public static String deletaPatrimonio(String id) {
        String resposta = new String();
        Patrimonio patrimonio = PatrimonioAccessService.getPatrimonioById(id);
        if (patrimonio != null) {
            if (PatrimonioAccessService.delete(patrimonio)) {
                resposta = "S";
            } else {
                resposta = "N";
            }
        } else {
            resposta = "F";
        }
        return resposta;
    }

}
