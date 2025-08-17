package com.diegoygabriela.backend_novalink.repository;

import com.diegoygabriela.backend_novalink.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Usuario findByUsername(String username);
    
    public Usuario findByCorreo(String correo);
    
    // Método para códigos de relación
    public Optional<Usuario> findByCodigoRelacion(String codigoRelacion);
    
    public boolean existsByCodigoRelacion(String codigoRelacion);
}
