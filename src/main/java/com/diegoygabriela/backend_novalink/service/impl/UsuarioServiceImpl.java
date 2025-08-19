package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.Usuario;
import com.diegoygabriela.backend_novalink.repository.UsuarioRepository;
import com.diegoygabriela.backend_novalink.service.Inter.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        
        // Update only non-null fields, preserving existing values for null fields
        if (usuario.getNombre() != null) {
            existingUsuario.setNombre(usuario.getNombre());
        }
        if (usuario.getApellido() != null) {
            existingUsuario.setApellido(usuario.getApellido());
        }
        if (usuario.getCorreo() != null) {
            existingUsuario.setCorreo(usuario.getCorreo());
        }
        if (usuario.getUsername() != null) {
            existingUsuario.setUsername(usuario.getUsername());
        }
        // Note: Password is NOT updated here - use /cambiar-password endpoint for that
        if (usuario.getEnabled() != null) {
            existingUsuario.setEnabled(usuario.getEnabled());
        }
        if (usuario.getFotoPerfil() != null) {
            existingUsuario.setFotoPerfil(usuario.getFotoPerfil());
        }
        if (usuario.getFechaNacimiento() != null) {
            existingUsuario.setFechaNacimiento(usuario.getFechaNacimiento());
        }
        if (usuario.getGenero() != null) {
            existingUsuario.setGenero(usuario.getGenero());
        }
        if (usuario.getRole() != null) {
            existingUsuario.setRole(usuario.getRole());
        }
        
        // Save the updated user
        usuarioRepository.save(existingUsuario);
    }

    @Override
    public List<Usuario> list() {
        return usuarioRepository.findAll();
    }

    @Override
    public void delete(Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    @Override
    public Usuario listId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).orElse(new Usuario());
    }
    
    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
    
    @Override
    public Usuario findByCodigoRelacion(String codigoRelacion) {
        return usuarioRepository.findByCodigoRelacion(codigoRelacion);
    }

    @Override
    public void cambiarPassword(Long idUsuario, String passwordActual, String passwordNueva) {
        // Verificar que el usuario existe
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));
        
        // Verificar que la contraseña actual sea correcta
        if (!passwordEncoder.matches(passwordActual, usuario.getPassword())) {
            throw new RuntimeException("La contraseña actual es incorrecta.");
        }
        
        // Verificar que la nueva contraseña sea diferente a la actual
        if (passwordActual.equals(passwordNueva)) {
            throw new RuntimeException("La nueva contraseña debe ser diferente a la actual.");
        }
        
        // Encriptar la nueva contraseña
        String passwordEncriptada = passwordEncoder.encode(passwordNueva);
        
        // Actualizar la contraseña
        usuario.setPassword(passwordEncriptada);
        usuarioRepository.save(usuario);
    }

    @Override
    public String generarCodigoRelacion(Long idUsuario) {
        // Verificar que el usuario existe
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));
        
        // Generar código único de 6 caracteres
        String codigo;
        boolean codigoUnico = false;
        
        do {
            codigo = generarCodigoAleatorio();
            // Verificar que el código no exista
            Usuario usuarioExistente = usuarioRepository.findByCodigoRelacion(codigo);
            if (usuarioExistente == null) {
                codigoUnico = true;
            }
        } while (!codigoUnico);
        
        // Asignar el código al usuario
        usuario.setCodigoRelacion(codigo);
        usuarioRepository.save(usuario);
        
        return codigo;
    }
    
    private String generarCodigoAleatorio() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder codigo = new StringBuilder();
        
        for (int i = 0; i < 6; i++) {
            int indice = (int) (Math.random() * caracteres.length());
            codigo.append(caracteres.charAt(indice));
        }
        
        return codigo.toString();
    }
}
