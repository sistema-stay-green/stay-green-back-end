/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.servlet;

import br.cefetmg.staygreen.service.UsuarioService;
import br.cefetmg.staygreen.table.Usuario;
import br.cefetmg.staygreen.util.IO;
import br.cefetmg.staygreen.util.SQL;
import java.io.IOException;
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
@WebServlet("/UpdateUsuarioServlet")
public class UpdateUsuarioServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        UsuarioService service = new UsuarioService();
        Usuario usuario = service.getUsuarioByEmail(req.getParameter("loginAntigo"));
        
        usuario.setNomeUsuario(req.getParameter("nome"));
        usuario.setEmailUsuario(req.getParameter("login"));
        usuario.setCnpjUsuario(req.getParameter("cnpj"));
        usuario.setSaldoUsuario(Double.parseDouble(req.getParameter("saldo")));

        SQL.update(usuario);
        resp.setStatus(200);
        
    }    
    
}
