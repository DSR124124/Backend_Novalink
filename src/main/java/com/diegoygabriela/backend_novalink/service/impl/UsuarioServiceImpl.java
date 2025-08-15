package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.Usuario;
import com.diegoygabriela.backend_novalink.entity.Role;
import com.diegoygabriela.backend_novalink.repository.UsuarioRepository;
import com.diegoygabriela.backend_novalink.repository.RolRepository;
import com.diegoygabriela.backend_novalink.service.Inter.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolRepository rolRepository;

    @Override
    public void insert(Usuario usuario) {
        // Check if username already exists
        if (usuario.getUsername() != null && findByUsername(usuario.getUsername()) != null) {
            throw new RuntimeException("El username '" + usuario.getUsername() + "' ya está en uso. Por favor, elige otro username.");
        }
        
        // Check if email already exists
        if (usuario.getCorreo() != null && usuarioRepository.findByCorreo(usuario.getCorreo()) != null) {
            throw new RuntimeException("El correo '" + usuario.getCorreo() + "' ya está registrado. Por favor, usa otro correo.");
        }
        
        // For new user registration, ensure ID is null so JPA can auto-generate it
        if (usuario.getIdUsuario() != null) {
            usuario.setIdUsuario(null);
        }
        usuarioRepository.save(usuario);
    }

    /**
     * Method to register a new user with roleId
     * This method fetches the role from database and assigns it to the user
     */
    public void registrarUsuarioConRoleId(Usuario usuario, Integer roleId) {
        // Validate roleId
        if (roleId == null) {
            throw new RuntimeException("El ID del rol es obligatorio");
        }
        
        // Fetch the role from database
        Role role = rolRepository.findById(roleId.longValue())
            .orElseThrow(() -> new RuntimeException("No se encontró el rol con ID: " + roleId));
        
        // Assign the role to the user
        usuario.setRole(role);
        
        // Now call the regular insert method
        insert(usuario);
    }

    @Override
    public List<Usuario> list() {
        return usuarioRepository.findAll();
    }

    @Override
    public void delete(Integer idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    @Override
    public Usuario listId(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario).orElse(new Usuario());
    }
    
    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
