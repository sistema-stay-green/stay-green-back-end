/*
 * Sistema de Agronegocio :: Stay Green
 * CEFET-MG
 * INF-2A 2018
 */
package br.cefetmg.staygreen.service;

import br.cefetmg.staygreen.exception.InvalidIdException;
import br.cefetmg.staygreen.table.Usuario;
import br.cefetmg.staygreen.util.IO;
import br.cefetmg.staygreen.util.SQL;
import java.util.List;
import java.util.Objects;

/**
 * Serviço para manipular um usuário.
 * 
 * @author Daniel
 * @version 1.0
 */
public class UsuarioService {
    
    public boolean checkPassword(Usuario usuario) {
        
        Usuario usuarioOriginal = getUsuarioById(usuario.getIdUsuario());
        
        return IO.criptografar(usuario.getSenhaUsuario())
                .equals(usuarioOriginal.getSenhaUsuario());
            
        
    }
    
    public Usuario getUsuarioById(Long id) throws InvalidIdException {
        
        List<Usuario> usuarios = SQL.getRegistros(Usuario.class);
        
        try {
            Usuario[] resultado = usuarios.stream()
                    .filter(u -> u.getIdUsuario().equals(id))
                    .toArray(Usuario[]::new);
            if (resultado.length != 1)
                throw new InvalidIdException();
            else
                return resultado[0];
                
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
}
