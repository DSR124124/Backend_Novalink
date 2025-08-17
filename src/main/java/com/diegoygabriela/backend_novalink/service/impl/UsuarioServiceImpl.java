package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.Usuario;
import com.diegoygabriela.backend_novalink.repository.UsuarioRepository;
import com.diegoygabriela.backend_novalink.service.Inter.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    @Override
    public void update(Usuario usuario) {
        // Verify the user exists
        if (usuario.getIdUsuario() == null) {
            throw new RuntimeException("El ID del usuario es requerido para la actualización.");
        }
        
        Usuario existingUsuario = usuarioRepository.findById(usuario.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuario.getIdUsuario()));
        
        // Check if username already exists (excluding current user)
        if (usuario.getUsername() != null && !usuario.getUsername().equals(existingUsuario.getUsername())) {
            Usuario usuarioWithUsername = findByUsername(usuario.getUsername());
            if (usuarioWithUsername != null && !usuarioWithUsername.getIdUsuario().equals(usuario.getIdUsuario())) {
                throw new RuntimeException("El username '" + usuario.getUsername() + "' ya está en uso. Por favor, elige otro username.");
            }
        }
        
        // Check if email already exists (excluding current user)
        if (usuario.getCorreo() != null && !usuario.getCorreo().equals(existingUsuario.getCorreo())) {
            Usuario usuarioWithEmail = usuarioRepository.findByCorreo(usuario.getCorreo());
            if (usuarioWithEmail != null && !usuarioWithEmail.getIdUsuario().equals(usuario.getIdUsuario())) {
                throw new RuntimeException("El correo '" + usuario.getCorreo() + "' ya está registrado. Por favor, usa otro correo.");
            }
        }
        
        // Preserve the original ID and update the user
        usuarioRepository.save(usuario);
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
