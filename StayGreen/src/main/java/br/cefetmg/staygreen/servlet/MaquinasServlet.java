/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.servlet;

import br.cefetmg.staygreen.service.MaquinasService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.cefetmg.staygreen.util.JSON;
import br.cefetmg.staygreen.table.Patrimonio;
import br.cefetmg.staygreen.service.PatrimonioAccessService;
import br.cefetmg.staygreen.service.ControleDeMaquinasUtilService;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Gabriel Cruz
 * @version 03-11-18/14:09
 */
@WebServlet(name = "MaquinasServlet", urlPatterns = {"/MaquinasServlet"})
public class MaquinasServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()){
            
            Patrimonio maquina = JSON.parse(request.getParameter("maquinasJSON"),
                    Patrimonio.class);
            String resposta = "",
                   stringDataCompra = request.getParameter("dataCompra"),
                   stringDataSaida = request.getParameter("dataSaida"),
                   stringDataRetorno = request.getParameter("dataRetorno"),
                   stringDataBaixa = request.getParameter("dataBaixa"),
                   stringQuantidade = request.getParameter("quantidade");
            int quantidade = Integer.parseInt(stringQuantidade);
                       
            switch(request.getParameter("acao")){
                /**
                * O Parametro "acao" será igual ao carcacter 'c' caso, a ação 
                * desejada pelo usuário a ser feita, em uma maquina, seja a de 
                * compra, candastrando-a
                */
                case "c": 
                    MaquinasService.Cadastrar(maquina, quantidade, 
                            ControleDeMaquinasUtilService.
                            converteStringToCalendar(stringDataCompra));
                    break;
                    
                /**
                * O Parametro "acao" será igual ao carcacter 'v' caso, a ação 
                * desejada pelo usuário a ser feita, em uma maquina, seja a de 
                * vende-la
                */
                case "v":
                    resposta = MaquinasService.Venda(maquina,ControleDeMaquinasUtilService.
                            converteStringToCalendar(stringDataBaixa));
                    break;
                    
                /**
                * O Parametro "acao" será igual ao carcacter 'a' caso, a ação 
                * desejada pelo usuário a ser feita, em uma maquina, seja a de 
                * aluga-la, por valor pré-determinado
                */
                case "a":
                    resposta = MaquinasService.Aluguel(maquina, request,
                            ControleDeMaquinasUtilService.
                            converteStringToCalendar(stringDataSaida),
                            ControleDeMaquinasUtilService.
                            converteStringToCalendar(stringDataRetorno));
                    break;
                    
                /**
                * O Parametro "acao" será igual ao carcacter 'd' caso, a ação 
                * desejada pelo usuário a ser feita, em uma maquina, seja a de 
                * descarta-la
                */
                case "d":
                    resposta = MaquinasService.Descarte(maquina, ControleDeMaquinasUtilService.
                            converteStringToCalendar(stringDataBaixa) );
                    break;
                    
                /**
                * O Parametro "acao" será igual ao carcacter 'm' caso, a ação 
                * desejada pelo usuário a ser feita, em uma maquina, seja a de 
                * manuiteni-la
                */
                case "m":
                    resposta = MaquinasService.Manuntenir(maquina,
                            ControleDeMaquinasUtilService.
                            converteStringToCalendar(stringDataRetorno));
                    break;
                    
                /**
                * O Parametro "acao" será igual ao carcacter 'r' caso, a ação 
                * desejada pelo usuário a ser feita seja retornar os dados de todas
                * as maquinas presentes no Data Base
                */
                case "r":      
                    ArrayList<Patrimonio> maquinas =PatrimonioAccessService.
                            get("WHERE tipoPatrimonio = 'MAQUINA'");
                    resposta = JSON.stringify(maquinas);              
                    break;
                    
                /**
                * O Parametro "acao" será igual ao carcacter 'e' caso, a ação 
                * desejada pelo usuário a ser feita seja editar qualquer um das
                * suas variáveis de classe
                */
                case "e":
                    maquina.setDataCompra
                            (ControleDeMaquinasUtilService.
                            converteStringToCalendar(stringDataCompra)); 
                    resposta = MaquinasService.Editar(maquina);
                    break;
                    
                /**
                * Caso o Parametro "acao" for diferente à todos os carcacteres 
                * anteriores, acusará que a opção selecionada está equivocada
                */
                default: 
                    throw new IllegalArgumentException("Opção Invalida");
            }
            out.println(resposta);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods.Click  on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>

}
