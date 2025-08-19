package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.DetalleRegaloDTO;
import com.diegoygabriela.backend_novalink.dtos.MensajeErrorDTO;
import com.diegoygabriela.backend_novalink.entity.DetalleRegalo;
import com.diegoygabriela.backend_novalink.service.Inter.DetalleRegaloService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/detalles-regalo")
public class DetalleRegaloController {

    @Autowired
    private DetalleRegaloService detalleRegaloService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public MensajeErrorDTO registrar(@Valid @RequestBody DetalleRegaloDTO dto) {
        try {
            DetalleRegalo detalleRegalo = modelMapper.map(dto, DetalleRegalo.class);
            detalleRegaloService.insert(detalleRegalo);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Detalle de regalo registrado exitosamente")
                .p_exito(true)
                .p_data(Map.of("detalleRegalo", modelMapper.map(detalleRegalo, DetalleRegaloDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al registrar detalle de regalo: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar")
    public MensajeErrorDTO listar() {
        try {
            List<DetalleRegaloDTO> detalles = detalleRegaloService.list().stream()
                .map(detalle -> modelMapper.map(detalle, DetalleRegaloDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Detalles de regalo listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "detalles", detalles,
                    "total", detalles.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar detalles de regalo: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public MensajeErrorDTO eliminar(@PathVariable("id") Long id) {
        try {
            detalleRegaloService.delete(id);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Detalle de regalo eliminado exitosamente")
                .p_exito(true)
                .p_data(Map.of("id", id))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al eliminar detalle de regalo: " + e.getMessage())
                .p_mensavis("Verifica que el detalle exista e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("id", id, "error", e.getMessage()))
                .build();
        }
    }

    @PutMapping("/modificar")
    public MensajeErrorDTO modificar(@Valid @RequestBody DetalleRegaloDTO dto) {
        try {
            DetalleRegalo detalleRegalo = modelMapper.map(dto, DetalleRegalo.class);
            detalleRegaloService.insert(detalleRegalo);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Detalle de regalo modificado exitosamente")
                .p_exito(true)
                .p_data(Map.of("detalleRegalo", modelMapper.map(detalleRegalo, DetalleRegaloDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al modificar detalle de regalo: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar-por-id/{id}")
    public MensajeErrorDTO listarId(@PathVariable("id") Long id) {
        try {
            DetalleRegalo detalleRegalo = detalleRegaloService.listId(id);
            if (detalleRegalo == null) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Detalle de regalo no encontrado")
                    .p_mensavis("Verifica que el ID sea correcto")
                    .p_exito(false)
                    .p_data(Map.of("id", id, "error", "NOT_FOUND"))
                    .build();
            }
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Detalle de regalo encontrado exitosamente")
                .p_exito(true)
                .p_data(Map.of("detalleRegalo", modelMapper.map(detalleRegalo, DetalleRegaloDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al buscar detalle de regalo: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("id", id, "error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/pareja/{parejaId}")
    public MensajeErrorDTO listarPorPareja(@PathVariable("parejaId") Long parejaId) {
        try {
            List<DetalleRegaloDTO> detalles = detalleRegaloService.findByParejaId(parejaId).stream()
                .map(detalle -> modelMapper.map(detalle, DetalleRegaloDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Detalles de regalo por pareja listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "detalles", detalles,
                    "parejaId", parejaId,
                    "total", detalles.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar detalles por pareja: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("parejaId", parejaId, "error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/remitente/{remitenteId}")
    public MensajeErrorDTO listarPorRemitente(@PathVariable("remitenteId") Long remitenteId) {
        try {
            List<DetalleRegaloDTO> detalles = detalleRegaloService.findByRemitenteId(remitenteId).stream()
                .map(detalle -> modelMapper.map(detalle, DetalleRegaloDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Detalles de regalo por remitente listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "detalles", detalles,
                    "remitenteId", remitenteId,
                    "total", detalles.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar detalles por remitente: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("remitenteId", remitenteId, "error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/receptor/{receptorId}")
    public MensajeErrorDTO listarPorReceptor(@PathVariable("receptorId") Long receptorId) {
        try {
            List<DetalleRegaloDTO> detalles = detalleRegaloService.findByReceptorId(receptorId).stream()
                .map(detalle -> modelMapper.map(detalle, DetalleRegaloDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Detalles de regalo por receptor listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "detalles", detalles,
                    "receptorId", receptorId,
                    "total", detalles.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar detalles por receptor: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("receptorId", receptorId, "error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/entre-usuarios/{usuario1Id}/{usuario2Id}")
    public MensajeErrorDTO listarEntreUsuarios(@PathVariable("usuario1Id") Long usuario1Id, @PathVariable("usuario2Id") Long usuario2Id) {
        try {
            List<DetalleRegaloDTO> detalles = detalleRegaloService.findRegalosBetweenUsers(usuario1Id, usuario2Id).stream()
                .map(detalle -> modelMapper.map(detalle, DetalleRegaloDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Detalles de regalo entre usuarios listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "detalles", detalles,
                    "usuario1Id", usuario1Id,
                    "usuario2Id", usuario2Id,
                    "total", detalles.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar detalles entre usuarios: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("usuario1Id", usuario1Id, "usuario2Id", usuario2Id, "error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/cita/{citaId}")
    public MensajeErrorDTO listarPorCita(@PathVariable("citaId") Long citaId) {
        try {
            List<DetalleRegaloDTO> detalles = detalleRegaloService.findByCitaId(citaId).stream()
                .map(detalle -> modelMapper.map(detalle, DetalleRegaloDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Detalles de regalo por cita listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "detalles", detalles,
                    "citaId", citaId,
                    "total", detalles.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar detalles por cita: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("citaId", citaId, "error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/evento/{eventoId}")
    public MensajeErrorDTO listarPorEvento(@PathVariable("eventoId") Long eventoId) {
        try {
            List<DetalleRegaloDTO> detalles = detalleRegaloService.findByEventoId(eventoId).stream()
                .map(detalle -> modelMapper.map(detalle, DetalleRegaloDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Detalles de regalo por evento listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "detalles", detalles,
                    "eventoId", eventoId,
                    "total", detalles.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar detalles por evento: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("eventoId", eventoId, "error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/recientes/{dias}")
    public MensajeErrorDTO listarRecientes(@PathVariable("dias") Integer dias) {
        try {
            List<DetalleRegaloDTO> detalles = detalleRegaloService.findRecentRegalos(dias).stream()
                .map(detalle -> modelMapper.map(detalle, DetalleRegaloDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Detalles de regalo recientes listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "detalles", detalles,
                    "dias", dias,
                    "total", detalles.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar detalles recientes: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("dias", dias, "error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/contar-pareja/{parejaId}")
    public MensajeErrorDTO contarPorPareja(@PathVariable("parejaId") Long parejaId) {
        try {
            Long cantidad = detalleRegaloService.countByParejaId(parejaId);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Conteo de detalles de regalo por pareja realizado exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "parejaId", parejaId,
                    "cantidad", cantidad
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al contar detalles por pareja: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("parejaId", parejaId, "error", e.getMessage()))
                .build();
        }
    }
}
