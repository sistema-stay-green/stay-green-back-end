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
@WebServlet("/UpdateSaldoServlet")
public class UpdateSaldoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        UsuarioService service = new UsuarioService();
        Usuario usuario = service.getUsuarioByEmail(req.getParameter("login"));
        
        Transacao transacao = SQL.getRegistros(Transacao.class).stream()
                .reduce(new Transacao(), (t1, t2) -> { 
            
                    t1.setValorTransacao(t1.getValorTransacao() + t2.getValorTransacao());
                    return t1;
            
                });
        
        Double saldoFinal = usuario.getSaldoUsuario() + transacao.getValorTransacao();
        
        usuario.setSaldoUsuario(saldoFinal);
        SQL.update(usuario);
        
        try (PrintWriter out = resp.getWriter()) {
            
            out.println(String.format("%.2f", usuario.getSaldoUsuario()).replace(",", "."));
            resp.setStatus(200);
            
        }
        
    }    
    
}
