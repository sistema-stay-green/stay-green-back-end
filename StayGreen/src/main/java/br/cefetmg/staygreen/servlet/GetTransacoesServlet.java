/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.servlet;

import br.cefetmg.staygreen.service.UsuarioService;
import br.cefetmg.staygreen.table.Transacao;
import br.cefetmg.staygreen.table.Usuario;
import br.cefetmg.staygreen.util.IO;
import br.cefetmg.staygreen.util.JSON;
import br.cefetmg.staygreen.util.SQL;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet para cadastrar um novo usuário.
 * 
 * @author Daniel
 * @version 1.0
 * @see br.cefetmg.staygreen.service.UsuarioService
 */
@WebServlet("/GetTransacoesServlet")
public class GetTransacoesServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try (PrintWriter out = resp.getWriter()) {
            
            out.println(JSON.stringify(SQL.getRegistros(Transacao.class)));
            
        }
        
        
    }    
    
}
