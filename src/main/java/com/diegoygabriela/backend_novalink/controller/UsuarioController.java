package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.UsuarioDTO;
import com.diegoygabriela.backend_novalink.dtos.CambioPasswordDTO;
import com.diegoygabriela.backend_novalink.dtos.MensajeErrorDTO;
import com.diegoygabriela.backend_novalink.entity.Usuario;
import com.diegoygabriela.backend_novalink.service.Inter.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public MensajeErrorDTO registrar(@Valid @RequestBody UsuarioDTO dto) {
        try {
            Usuario usuario = modelMapper.map(dto, Usuario.class);
            usuarioService.insert(usuario);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Usuario registrado exitosamente")
                .p_exito(true)
                .p_data(Map.of("usuario", modelMapper.map(usuario, UsuarioDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al registrar usuario: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar")
    public MensajeErrorDTO listar() {
        try {
            List<UsuarioDTO> usuarios = usuarioService.list().stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Usuarios listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "usuarios", usuarios,
                    "total", usuarios.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar usuarios: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar-por-id/{id}")
    public MensajeErrorDTO listarId(@PathVariable("id") Integer id) {
        try {
            Usuario usuario = usuarioService.listId(id);
            if (usuario == null) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Usuario no encontrado")
                    .p_mensavis("Verifica que el ID sea correcto")
                    .p_exito(false)
                    .p_data(Map.of("id", id, "error", "NOT_FOUND"))
                    .build();
            }
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Usuario encontrado exitosamente")
                .p_exito(true)
                .p_data(Map.of("usuario", modelMapper.map(usuario, UsuarioDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al buscar usuario: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("id", id, "error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar-por-username/{username}")
    public MensajeErrorDTO listarUsername(@PathVariable("username") String username) {
        try {
            Usuario usuario = usuarioService.findByUsername(username);
            if (usuario == null) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Usuario no encontrado")
                    .p_mensavis("Verifica que el username sea correcto")
                    .p_exito(false)
                    .p_data(Map.of("username", username, "error", "NOT_FOUND"))
                    .build();
            }
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Usuario encontrado exitosamente")
                .p_exito(true)
                .p_data(Map.of("usuario", modelMapper.map(usuario, UsuarioDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al buscar usuario: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("username", username, "error", e.getMessage()))
                .build();
        }
    }

    @PutMapping("/modificar")
    public MensajeErrorDTO modificar(@Valid @RequestBody UsuarioDTO dto) {
        try {
            Usuario usuario = modelMapper.map(dto, Usuario.class);
            usuarioService.update(usuario);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Usuario modificado exitosamente")
                .p_exito(true)
                .p_data(Map.of("usuario", modelMapper.map(usuario, UsuarioDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al modificar usuario: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @PostMapping("/cambiar-password")
    public MensajeErrorDTO cambiarPassword(@Valid @RequestBody CambioPasswordDTO dto) {
        try {
            usuarioService.cambiarPassword(dto.getIdUsuario(), dto.getPasswordActual(), dto.getPasswordNueva());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Contraseña cambiada exitosamente")
                .p_exito(true)
                .p_data(Map.of("idUsuario", dto.getIdUsuario()))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al cambiar contraseña: " + e.getMessage())
                .p_mensavis("Verifica la contraseña actual e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public MensajeErrorDTO eliminar(@PathVariable("id") Integer id) {
        try {
            usuarioService.delete(id);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Usuario eliminado exitosamente")
                .p_exito(true)
                .p_data(Map.of("id", id))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al eliminar usuario: " + e.getMessage())
                .p_mensavis("Verifica que el usuario exista e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("id", id, "error", e.getMessage()))
                .build();
        }
    }
}
