/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.servlet;

import br.cefetmg.staygreen.table.Usuario;
import br.cefetmg.staygreen.util.SQL;
import java.io.IOException;
import java.util.Map;
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
@WebServlet("/cadastrarusuario")
public class CadastroUsuarioServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        Usuario usuario = new Usuario(
            null,
            req.getParameter("nome"),
            req.getParameter("email"),
            req.getParameter("senha"),
            req.getParameter("cnpj"),
            Double.parseDouble(req.getParameter("saldo"))
        );
        
        SQL.insert(usuario);
        resp.setStatus(200);
        
    }    
    
}
