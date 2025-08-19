package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.EventoDTO;
import com.diegoygabriela.backend_novalink.dtos.MensajeErrorDTO;
import com.diegoygabriela.backend_novalink.entity.Evento;
import com.diegoygabriela.backend_novalink.service.Inter.EventoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public MensajeErrorDTO registrar(@Valid @RequestBody EventoDTO dto) {
        try {
            Evento evento = modelMapper.map(dto, Evento.class);
            eventoService.insert(evento);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Evento registrado exitosamente")
                .p_exito(true)
                .p_data(Map.of("evento", modelMapper.map(evento, EventoDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al registrar evento: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar")
    public MensajeErrorDTO listar() {
        try {
            List<EventoDTO> eventos = eventoService.list().stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Eventos listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "eventos", eventos,
                    "total", eventos.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar eventos: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public MensajeErrorDTO eliminar(@PathVariable("id") Long id) {
        try {
            eventoService.delete(id);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Evento eliminado exitosamente")
                .p_exito(true)
                .p_data(Map.of("id", id))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al eliminar evento: " + e.getMessage())
                .p_mensavis("Verifica que el evento exista e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("id", id, "error", e.getMessage()))
                .build();
        }
    }

    @PutMapping("/modificar")
    public MensajeErrorDTO modificar(@Valid @RequestBody EventoDTO dto) {
        try {
            Evento evento = modelMapper.map(dto, Evento.class);
            eventoService.insert(evento);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Evento modificado exitosamente")
                .p_exito(true)
                .p_data(Map.of("evento", modelMapper.map(evento, EventoDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al modificar evento: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar-por-id/{id}")
    public MensajeErrorDTO listarId(@PathVariable("id") Long id) {
        try {
            Evento evento = eventoService.listId(id);
            if (evento == null) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Evento no encontrado")
                    .p_mensavis("Verifica que el ID sea correcto")
                    .p_exito(false)
                    .p_data(Map.of("id", id, "error", "NOT_FOUND"))
                    .build();
            }
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Evento encontrado exitosamente")
                .p_exito(true)
                .p_data(Map.of("evento", modelMapper.map(evento, EventoDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al buscar evento: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("id", id, "error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/pareja/{parejaId}")
    public MensajeErrorDTO listarPorPareja(@PathVariable("parejaId") Long parejaId) {
        try {
            List<EventoDTO> eventos = eventoService.findByParejaId(parejaId).stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Eventos por pareja listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "eventos", eventos,
                    "parejaId", parejaId,
                    "total", eventos.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar eventos por pareja: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("parejaId", parejaId, "error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/tipo/{tipo}")
    public MensajeErrorDTO listarPorTipo(@PathVariable("tipo") String tipo) {
        try {
            List<EventoDTO> eventos = eventoService.findByTipo(tipo).stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Eventos por tipo listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "eventos", eventos,
                    "tipo", tipo,
                    "total", eventos.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar eventos por tipo: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("tipo", tipo, "error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/lugar/{lugarId}")
    public MensajeErrorDTO listarPorLugar(@PathVariable("lugarId") Long lugarId) {
        try {
            List<EventoDTO> eventos = eventoService.findByLugarId(lugarId).stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Eventos por lugar listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "eventos", eventos,
                    "lugarId", lugarId,
                    "total", eventos.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar eventos por lugar: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("lugarId", lugarId, "error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/proximos")
    public MensajeErrorDTO listarProximos() {
        try {
            List<EventoDTO> eventos = eventoService.findEventosProximos().stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Eventos próximos listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "eventos", eventos,
                    "total", eventos.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar eventos próximos: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/pasados")
    public MensajeErrorDTO listarPasados() {
        try {
            List<EventoDTO> eventos = eventoService.findEventosPasados().stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Eventos pasados listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "eventos", eventos,
                    "total", eventos.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar eventos pasados: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/pareja/{parejaId}/tipo/{tipo}")
    public MensajeErrorDTO listarPorParejaYTipo(@PathVariable("parejaId") Long parejaId, @PathVariable("tipo") String tipo) {
        try {
            List<EventoDTO> eventos = eventoService.findByParejaIdAndTipo(parejaId, tipo).stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Eventos por pareja y tipo listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "eventos", eventos,
                    "parejaId", parejaId,
                    "tipo", tipo,
                    "total", eventos.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar eventos por pareja y tipo: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("parejaId", parejaId, "tipo", tipo, "error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/año/{year}")
    public MensajeErrorDTO listarPorAño(@PathVariable("year") Integer year) {
        try {
            List<EventoDTO> eventos = eventoService.findEventosByYear(year).stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Eventos por año listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "eventos", eventos,
                    "año", year,
                    "total", eventos.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar eventos por año: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("año", year, "error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/pareja/{parejaId}/proximos/{dias}")
    public MensajeErrorDTO listarProximosPorPareja(@PathVariable("parejaId") Long parejaId, @PathVariable("dias") Integer dias) {
        try {
            List<EventoDTO> eventos = eventoService.findEventosProximosPorPareja(parejaId, dias).stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Eventos próximos por pareja listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "eventos", eventos,
                    "parejaId", parejaId,
                    "dias", dias,
                    "total", eventos.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar eventos próximos por pareja: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("parejaId", parejaId, "dias", dias, "error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/contar-pareja/{parejaId}")
    public MensajeErrorDTO contarPorPareja(@PathVariable("parejaId") Long parejaId) {
        try {
            Long cantidad = eventoService.countByParejaId(parejaId);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Conteo de eventos por pareja realizado exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "parejaId", parejaId,
                    "cantidad", cantidad
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al contar eventos por pareja: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("parejaId", parejaId, "error", e.getMessage()))
                .build();
        }
    }
}
