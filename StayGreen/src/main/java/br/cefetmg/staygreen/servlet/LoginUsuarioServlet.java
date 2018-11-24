/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.servlet;

import br.cefetmg.staygreen.service.UsuarioService;
import br.cefetmg.staygreen.table.Usuario;
import br.cefetmg.staygreen.util.IO;
import br.cefetmg.staygreen.util.JSON;
import br.cefetmg.staygreen.util.SQL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author Aluno
 */
@WebServlet("/LoginUsuarioServlet")
public class LoginUsuarioServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String emailUsuario = req.getParameter("login");
        String senhaUsuario = req.getParameter("senha");
        UsuarioService service = new UsuarioService();
        
        if (service.checkPassword(emailUsuario, senhaUsuario)) {
         
            Usuario usuario = service.getUsuarioByEmail(emailUsuario);
            
            try (PrintWriter out = resp.getWriter()){
                out.println(JSON.stringify(usuario));
            }
            
        }
        else{
            try (PrintWriter out = resp.getWriter()) {    
                out.println(JSON.stringify(null));
            }
        }
        
    }
    
}
