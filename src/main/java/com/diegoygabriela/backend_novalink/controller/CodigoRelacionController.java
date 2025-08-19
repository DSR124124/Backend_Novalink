package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.MensajeErrorDTO;
import com.diegoygabriela.backend_novalink.entity.Usuario;
import com.diegoygabriela.backend_novalink.service.Inter.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/codigos-relacion")
public class CodigoRelacionController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/generar/{username}")
    public MensajeErrorDTO generarCodigoRelacion(@PathVariable String username) {
        try {
            Usuario usuario = usuarioService.findByUsername(username);
            if (usuario == null) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Usuario no encontrado")
                    .p_mensavis("Verifica que el username sea correcto")
                    .p_exito(false)
                    .p_data(Map.of(
                        "username", username,
                        "error", "USER_NOT_FOUND"
                    ))
                    .build();
            }
            String codigoGenerado = usuarioService.generarCodigoRelacion(usuario.getIdUsuario());
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Código de relación generado exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "codigo", codigoGenerado,
                    "usuario", Map.of(
                        "id", usuario.getIdUsuario(),
                        "username", usuario.getUsername(),
                        "nombre", usuario.getNombre()
                    ),
                    "fechaGeneracion", java.time.LocalDateTime.now().toString()
                ))
                .build();
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al generar código: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of(
                    "username", username,
                    "error", e.getMessage()
                ))
                .build();
        }
    }

    @GetMapping("/usuario/{username}")
    public MensajeErrorDTO obtenerCodigoRelacion(@PathVariable String username) {
        try {
            Usuario usuario = usuarioService.findByUsername(username);
            if (usuario == null) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Usuario no encontrado")
                    .p_mensavis("Verifica que el username sea correcto")
                    .p_exito(false)
                    .p_data(Map.of(
                        "username", username,
                        "error", "USER_NOT_FOUND"
                    ))
                    .build();
            }
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Código de relación obtenido exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "codigo", usuario.getCodigoRelacion(),
                    "usuario", Map.of(
                        "id", usuario.getIdUsuario(),
                        "username", usuario.getUsername(),
                        "nombre", usuario.getNombre()
                    ),
                    "disponibleParaPareja", usuario.getDisponibleParaPareja()
                ))
                .build();
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al obtener código: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of(
                    "username", username,
                    "error", e.getMessage()
                ))
                .build();
        }
    }

    @GetMapping("/validar/{codigo}")
    public MensajeErrorDTO validarCodigoRelacion(@PathVariable String codigo) {
        try {
            Usuario usuario = usuarioService.findByCodigoRelacion(codigo);
            if (usuario == null) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Código de relación no válido")
                    .p_mensavis("El código ingresado no existe")
                    .p_exito(false)
                    .p_data(Map.of(
                        "codigo", codigo,
                        "error", "INVALID_CODE"
                    ))
                    .build();
            }
            
            if (!usuario.getDisponibleParaPareja()) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Usuario no disponible para pareja")
                    .p_mensavis("Este usuario ya tiene una pareja")
                    .p_exito(false)
                    .p_data(Map.of(
                        "codigo", codigo,
                        "usuario", Map.of(
                            "id", usuario.getIdUsuario(),
                            "username", usuario.getUsername(),
                            "nombre", usuario.getNombre()
                        ),
                        "error", "USER_NOT_AVAILABLE"
                    ))
                    .build();
            }
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Código de relación válido y usuario disponible")
                .p_exito(true)
                .p_data(Map.of(
                    "codigo", codigo,
                    "usuario", Map.of(
                        "id", usuario.getIdUsuario(),
                        "username", usuario.getUsername(),
                        "nombre", usuario.getNombre(),
                        "fotoPerfil", usuario.getFotoPerfil()
                    ),
                    "disponibleParaPareja", true
                ))
                .build();
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al validar código: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of(
                    "codigo", codigo,
                    "error", e.getMessage()
                ))
                .build();
        }
    }
}
