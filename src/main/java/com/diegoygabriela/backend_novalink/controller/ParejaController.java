package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.*;
import com.diegoygabriela.backend_novalink.entity.Pareja;
import com.diegoygabriela.backend_novalink.entity.Usuario;
import com.diegoygabriela.backend_novalink.service.Inter.ParejaService;
import com.diegoygabriela.backend_novalink.service.Inter.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@RestController
@RequestMapping("/api/parejas")
@CrossOrigin(origins = "*")
public class ParejaController {

    @Autowired
    private ParejaService parejaService;
    
    @Autowired
    private UsuarioService usuarioService;

    // ========== ENDPOINTS PRINCIPALES ==========
    
    /**
     * Crear pareja usando código de relación
     * POST /api/parejas/unirse-con-codigo
     */
    @PostMapping("/unirse-con-codigo")
    public ResponseEntity<RespuestaParejaDTO> unirseConCodigo(@Valid @RequestBody UnirseConCodigoDTO request) {
        try {
            Pareja parejaCreada = parejaService.crearParejaConCodigo(
                request.getUsuarioId(), 
                request.getCodigoRelacion(), 
                request.getEstadoRelacion()
            );
            
            ParejaDTO parejaDTO = convertirAParejaDTO(parejaCreada);
            
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(RespuestaParejaDTO.exito("Pareja creada exitosamente", parejaDTO));
                
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(RespuestaParejaDTO.error("Error al crear pareja: " + e.getMessage()));
        }
    }

    /**
     * Obtener pareja por usuario
     * GET /api/parejas/usuario/{usuarioId}
     */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<ParejaDTO> obtenerPorUsuario(@PathVariable Long usuarioId) {
        Optional<Pareja> pareja = parejaService.buscarPorUsuario(usuarioId);
        
        if (pareja.isPresent()) {
            ParejaDTO parejaDTO = convertirAParejaDTO(pareja.get());
            return ResponseEntity.ok(parejaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Cambiar estado de relación
     * PATCH /api/parejas/{id}/estado
     */
    @PatchMapping("/{id}/estado")
    public ResponseEntity<RespuestaParejaDTO> cambiarEstadoRelacion(
            @PathVariable Long id, 
            @Valid @RequestBody CambiarEstadoParejaDTO request) {
        try {
            Pareja parejaActualizada = parejaService.cambiarEstadoRelacion(id, request.getNuevoEstado());
            ParejaDTO parejaDTO = convertirAParejaDTO(parejaActualizada);
            
            return ResponseEntity.ok(RespuestaParejaDTO.exito("Estado de relación actualizado", parejaDTO));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(RespuestaParejaDTO.error("Error al cambiar estado: " + e.getMessage()));
        }
    }

    /**
     * Verificar si un usuario puede crear pareja
     * GET /api/parejas/puede-crear/{usuarioId}
     */
    @GetMapping("/puede-crear/{usuarioId}")
    public ResponseEntity<Boolean> puedeCrearPareja(@PathVariable Long usuarioId) {
        boolean puedeCrear = parejaService.puedeCrearPareja(usuarioId);
        return ResponseEntity.ok(puedeCrear);
    }

    // ========== MÉTODOS AUXILIARES ==========
    
    private ParejaDTO convertirAParejaDTO(Pareja pareja) {
        ParejaDTO dto = new ParejaDTO();
        dto.setId(pareja.getId());
        dto.setUsuario1Id(pareja.getUsuario1().getIdUsuario().longValue());
        dto.setUsuario2Id(pareja.getUsuario2().getIdUsuario().longValue());
        dto.setFechaCreacion(pareja.getFechaCreacion());
        dto.setEstadoRelacion(pareja.getEstadoRelacion());
        
        // Información del usuario 1
        dto.setUsuario1Nombre(pareja.getUsuario1().getNombre());
        dto.setUsuario1Apellido(pareja.getUsuario1().getApellido());
        dto.setUsuario1FotoPerfil(pareja.getUsuario1().getFotoPerfil());
        
        // Información del usuario 2
        dto.setUsuario2Nombre(pareja.getUsuario2().getNombre());
        dto.setUsuario2Apellido(pareja.getUsuario2().getApellido());
        dto.setUsuario2FotoPerfil(pareja.getUsuario2().getFotoPerfil());
        
        // Calcular días de relación
        if (pareja.getFechaCreacion() != null) {
            long dias = ChronoUnit.DAYS.between(pareja.getFechaCreacion(), LocalDate.now());
            dto.setDiasRelacion(dias);
        }
        
        // Formatear estado de relación
        switch (pareja.getEstadoRelacion()) {
            case "activa":
                dto.setEstadoRelacionFormateado("💕 Relación Activa");
                break;
            case "pausada":
                dto.setEstadoRelacionFormateado("⏸️ Relación Pausada");
                break;
            case "terminada":
                dto.setEstadoRelacionFormateado("💔 Relación Terminada");
                break;
            default:
                dto.setEstadoRelacionFormateado(pareja.getEstadoRelacion());
        }
        
        return dto;
    }
}
