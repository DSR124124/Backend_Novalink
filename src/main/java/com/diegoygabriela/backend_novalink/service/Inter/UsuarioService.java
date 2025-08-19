package com.diegoygabriela.backend_novalink.service.Inter;
import com.diegoygabriela.backend_novalink.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    
    // ===== GESTIÓN BÁSICA DE USUARIOS =====
    
    // CRUD básico
    void insert(Usuario usuario);
    void update(Usuario usuario);
    void delete(Long idUsuario);
    Usuario listId(Long idUsuario);
    List<Usuario> list();
    
    // ===== BÚSQUEDAS ESPECÍFICAS =====
    
    Usuario findByUsername(String username);
    Usuario findByCodigoRelacion(String codigoRelacion);
    
    // ===== GESTIÓN DE CONTRASEÑAS =====
    
    void cambiarPassword(Long idUsuario, String passwordActual, String passwordNueva);
    
    // ===== GESTIÓN DE CÓDIGOS DE RELACIÓN =====
    
    String generarCodigoRelacion(Long idUsuario);
}
