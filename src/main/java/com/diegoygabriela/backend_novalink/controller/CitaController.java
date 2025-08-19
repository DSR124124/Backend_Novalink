package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.CitaDTO;
import com.diegoygabriela.backend_novalink.dtos.MensajeErrorDTO;
import com.diegoygabriela.backend_novalink.entity.Cita;
import com.diegoygabriela.backend_novalink.entity.EstadoCita;
import com.diegoygabriela.backend_novalink.service.Inter.CitaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public MensajeErrorDTO registrar(@Valid @RequestBody CitaDTO dto) {
        try {
            Cita cita = modelMapper.map(dto, Cita.class);
            citaService.insert(cita);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Cita registrada exitosamente")
                .p_exito(true)
                .p_data(Map.of("cita", modelMapper.map(cita, CitaDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al registrar cita: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar")
    public MensajeErrorDTO listar() {
        try {
            List<CitaDTO> citas = citaService.list().stream()
                .map(cita -> modelMapper.map(cita, CitaDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Citas listadas exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "citas", citas,
                    "total", citas.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar citas: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public MensajeErrorDTO eliminar(@PathVariable("id") Long id) {
        try {
            citaService.delete(id);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Cita eliminada exitosamente")
                .p_exito(true)
                .p_data(Map.of("id", id))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al eliminar cita: " + e.getMessage())
                .p_mensavis("Verifica que la cita exista e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("id", id, "error", e.getMessage()))
                .build();
        }
    }

    @PutMapping("/modificar")
    public MensajeErrorDTO modificar(@Valid @RequestBody CitaDTO dto) {
        try {
            Cita cita = modelMapper.map(dto, Cita.class);
            citaService.insert(cita);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Cita modificada exitosamente")
                .p_exito(true)
                .p_data(Map.of("cita", modelMapper.map(cita, CitaDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al modificar cita: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar-por-id/{id}")
    public MensajeErrorDTO listarId(@PathVariable("id") Long id) {
        try {
            Cita cita = citaService.listId(id);
            if (cita == null) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Cita no encontrada")
                    .p_mensavis("Verifica que el ID sea correcto")
                    .p_exito(false)
                    .p_data(Map.of("id", id, "error", "NOT_FOUND"))
                    .build();
            }
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Cita encontrada exitosamente")
                .p_exito(true)
                .p_data(Map.of("cita", modelMapper.map(cita, CitaDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al buscar cita: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("id", id, "error", e.getMessage()))
                .build();
        }
    }
    
    // Endpoints específicos para la plataforma de parejas
    @GetMapping("/listar-por-pareja/{parejaId}")
    public MensajeErrorDTO listarPorPareja(@PathVariable("parejaId") Long parejaId) {
        try {
            List<CitaDTO> citas = citaService.findByParejaId(parejaId).stream()
                    .map(cita -> modelMapper.map(cita, CitaDTO.class))
                    .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Citas por pareja listadas exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "citas", citas,
                    "parejaId", parejaId,
                    "total", citas.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar citas por pareja: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("parejaId", parejaId, "error", e.getMessage()))
                .build();
        }
    }
    
    @GetMapping("/listar-por-estado/{estado}")
    public MensajeErrorDTO listarPorEstado(@PathVariable("estado") EstadoCita estado) {
        try {
            List<CitaDTO> citas = citaService.findByEstado(estado).stream()
                    .map(cita -> modelMapper.map(cita, CitaDTO.class))
                    .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Citas por estado listadas exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "citas", citas,
                    "estado", estado.toString(),
                    "total", citas.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar citas por estado: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("estado", estado.toString(), "error", e.getMessage()))
                .build();
        }
    }
    
    @GetMapping("/futuras/{parejaId}")
    public MensajeErrorDTO citasFuturas(@PathVariable("parejaId") Long parejaId) {
        try {
            List<CitaDTO> citas = citaService.findCitasFuturasByPareja(parejaId).stream()
                    .map(cita -> modelMapper.map(cita, CitaDTO.class))
                    .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Citas futuras listadas exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "citas", citas,
                    "parejaId", parejaId,
                    "total", citas.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar citas futuras: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("parejaId", parejaId, "error", e.getMessage()))
                .build();
        }
    }
    
    @GetMapping("/pasadas/{parejaId}")
    public MensajeErrorDTO citasPasadas(@PathVariable("parejaId") Long parejaId) {
        try {
            List<CitaDTO> citas = citaService.findCitasPasadasByPareja(parejaId).stream()
                    .map(cita -> modelMapper.map(cita, CitaDTO.class))
                    .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Citas pasadas listadas exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "citas", citas,
                    "parejaId", parejaId,
                    "total", citas.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar citas pasadas: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("parejaId", parejaId, "error", e.getMessage()))
                .build();
        }
    }
    
    @GetMapping("/mejor-calificadas/{parejaId}")
    public MensajeErrorDTO mejorCalificadas(@PathVariable("parejaId") Long parejaId) {
        try {
            List<CitaDTO> citas = citaService.findMejorCalificadasByPareja(parejaId).stream()
                    .map(cita -> modelMapper.map(cita, CitaDTO.class))
                    .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Citas mejor calificadas listadas exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "citas", citas,
                    "parejaId", parejaId,
                    "total", citas.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar citas mejor calificadas: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("parejaId", parejaId, "error", e.getMessage()))
                .build();
        }
    }
    
    @PutMapping("/completar/{id}")
    public MensajeErrorDTO completarCita(@PathVariable("id") Long id) {
        try {
            citaService.marcarComoCompletada(id);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Cita marcada como completada exitosamente")
                .p_exito(true)
                .p_data(Map.of("id", id))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al completar cita: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("id", id, "error", e.getMessage()))
                .build();
        }
    }
    
    @PutMapping("/cancelar/{id}")
    public MensajeErrorDTO cancelarCita(@PathVariable("id") Long id) {
        try {
            citaService.marcarComoCancelada(id);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Cita marcada como cancelada exitosamente")
                .p_exito(true)
                .p_data(Map.of("id", id))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al cancelar cita: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("id", id, "error", e.getMessage()))
                .build();
        }
    }
    
    @PutMapping("/calificar/{id}/{rating}")
    public MensajeErrorDTO calificarCita(@PathVariable("id") Long id, @PathVariable("rating") Integer rating) {
        try {
            citaService.actualizarRating(id, rating);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Cita calificada exitosamente")
                .p_exito(true)
                .p_data(Map.of("id", id, "rating", rating))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al calificar cita: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("id", id, "rating", rating, "error", e.getMessage()))
                .build();
        }
    }
}
