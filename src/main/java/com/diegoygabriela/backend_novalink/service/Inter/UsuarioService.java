package com.diegoygabriela.backend_novalink.service.Inter;
import com.diegoygabriela.backend_novalink.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    void insert(Usuario usuario);
    void update(Usuario usuario);
    void cambiarPassword(Integer idUsuario, String passwordActual, String passwordNueva);
    List<Usuario> list();
    void delete(Integer idUsuario);
    Usuario listId(Integer idUsuario);
    Usuario findByUsername(String username);
}
