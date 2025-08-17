package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.CodigoRelacionDTO;
import com.diegoygabriela.backend_novalink.entity.Usuario;
import com.diegoygabriela.backend_novalink.service.Inter.CodigoRelacionService;
import com.diegoygabriela.backend_novalink.service.Inter.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/codigos-relacion")
@CrossOrigin(origins = "*")
public class CodigoRelacionController {

    @Autowired
    private CodigoRelacionService codigoRelacionService;
    
    @Autowired
    private UsuarioService usuarioService;

    /**
     * Generar código de relación para un usuario
     * POST /api/codigos-relacion/generar/{usuarioId}
     */
    @PostMapping("/generar/{usuarioId}")
    public ResponseEntity<CodigoRelacionDTO> generarCodigoRelacion(@PathVariable Integer usuarioId) {
        try {
            Usuario usuario = usuarioService.listId(usuarioId);
            if (usuario == null) {
                return ResponseEntity.badRequest()
                    .body(CodigoRelacionDTO.error("Usuario no encontrado"));
            }
            String codigo = codigoRelacionService.generarCodigoRelacion();
            
            // Asignar el código al usuario
            usuario.setCodigoRelacion(codigo);
            usuario.setDisponibleParaPareja(true);
            usuarioService.insert(usuario);
            
            String nombreCompleto = usuario.getNombre() + " " + usuario.getApellido();
            return ResponseEntity.ok(CodigoRelacionDTO.exito(usuarioId, nombreCompleto, codigo));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(CodigoRelacionDTO.error("Error al generar código: " + e.getMessage()));
        }
    }

    /**
     * Obtener código de relación de un usuario
     * GET /api/codigos-relacion/usuario/{usuarioId}
     */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CodigoRelacionDTO> obtenerCodigoRelacion(@PathVariable Integer usuarioId) {
        try {
            Usuario usuario = usuarioService.listId(usuarioId);
            if (usuario == null) {
                return ResponseEntity.badRequest()
                    .body(CodigoRelacionDTO.error("Usuario no encontrado"));
            }
            if (usuario.getCodigoRelacion() == null) {
                return ResponseEntity.ok(CodigoRelacionDTO.error("El usuario no tiene código de relación"));
            }
            
            String nombreCompleto = usuario.getNombre() + " " + usuario.getApellido();
            return ResponseEntity.ok(CodigoRelacionDTO.exito(usuarioId, nombreCompleto, usuario.getCodigoRelacion()));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(CodigoRelacionDTO.error("Error al obtener código: " + e.getMessage()));
        }
    }

    /**
     * Validar un código de relación
     * GET /api/codigos-relacion/validar/{codigo}
     */
    @GetMapping("/validar/{codigo}")
    public ResponseEntity<Boolean> validarCodigoRelacion(@PathVariable String codigo) {
        boolean esValido = codigoRelacionService.validarCodigoRelacion(codigo);
        return ResponseEntity.ok(esValido);
    }
}
