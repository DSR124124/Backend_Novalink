package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.MultimediaEventoDTO;
import com.diegoygabriela.backend_novalink.dtos.MensajeErrorDTO;
import com.diegoygabriela.backend_novalink.entity.MultimediaEvento;
import com.diegoygabriela.backend_novalink.service.Inter.MultimediaEventoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/multimedia-eventos")
public class MultimediaEventoController {

    @Autowired
    private MultimediaEventoService multimediaEventoService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public MensajeErrorDTO registrar(@Valid @RequestBody MultimediaEventoDTO dto) {
        try {
            MultimediaEvento multimediaEvento = modelMapper.map(dto, MultimediaEvento.class);
            multimediaEventoService.insert(multimediaEvento);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Multimedia de evento registrado exitosamente")
                .p_exito(true)
                .p_data(Map.of("multimediaEvento", modelMapper.map(multimediaEvento, MultimediaEventoDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al registrar multimedia de evento: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar")
    public MensajeErrorDTO listar() {
        try {
            List<MultimediaEventoDTO> multimedias = multimediaEventoService.list().stream()
                .map(multimedia -> modelMapper.map(multimedia, MultimediaEventoDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Multimedia de eventos listado exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "multimedias", multimedias,
                    "total", multimedias.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar multimedia de eventos: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public MensajeErrorDTO eliminar(@PathVariable("id") Long id) {
        try {
            multimediaEventoService.delete(id);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Multimedia de evento eliminado exitosamente")
                .p_exito(true)
                .p_data(Map.of("id", id))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al eliminar multimedia de evento: " + e.getMessage())
                .p_mensavis("Verifica que el archivo exista e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("id", id, "error", e.getMessage()))
                .build();
        }
    }

    @PutMapping("/modificar")
    public MensajeErrorDTO modificar(@Valid @RequestBody MultimediaEventoDTO dto) {
        try {
            MultimediaEvento multimediaEvento = modelMapper.map(dto, MultimediaEvento.class);
            multimediaEventoService.insert(multimediaEvento);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Multimedia de evento modificado exitosamente")
                .p_exito(true)
                .p_data(Map.of("multimediaEvento", modelMapper.map(multimediaEvento, MultimediaEventoDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al modificar multimedia de evento: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar-por-id/{id}")
    public MensajeErrorDTO listarPorId(@PathVariable("id") Long id) {
        try {
            MultimediaEvento multimediaEvento = multimediaEventoService.listId(id);
            if (multimediaEvento == null) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Multimedia de evento no encontrado")
                    .p_mensavis("Verifica que el ID sea correcto")
                    .p_exito(false)
                    .p_data(Map.of("id", id, "error", "NOT_FOUND"))
                    .build();
            }
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Multimedia de evento encontrado exitosamente")
                .p_exito(true)
                .p_data(Map.of("multimediaEvento", modelMapper.map(multimediaEvento, MultimediaEventoDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al buscar multimedia de evento: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("id", id, "error", e.getMessage()))
                .build();
        }
    }
}
