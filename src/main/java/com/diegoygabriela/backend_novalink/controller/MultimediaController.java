package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.MultimediaDTO;
import com.diegoygabriela.backend_novalink.dtos.MensajeErrorDTO;
import com.diegoygabriela.backend_novalink.entity.Multimedia;
import com.diegoygabriela.backend_novalink.service.Inter.MultimediaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/multimedia")
public class MultimediaController {

    @Autowired
    private MultimediaService multimediaService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public MensajeErrorDTO registrar(@Valid @RequestBody MultimediaDTO dto) {
        try {
            Multimedia multimedia = modelMapper.map(dto, Multimedia.class);
            multimediaService.insert(multimedia);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Multimedia registrado exitosamente")
                .p_exito(true)
                .p_data(Map.of("multimedia", modelMapper.map(multimedia, MultimediaDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al registrar multimedia: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar")
    public MensajeErrorDTO listar() {
        try {
            List<MultimediaDTO> multimedias = multimediaService.list().stream()
                .map(multimedia -> modelMapper.map(multimedia, MultimediaDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Multimedia listado exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "multimedias", multimedias,
                    "total", multimedias.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar multimedia: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public MensajeErrorDTO eliminar(@PathVariable("id") Long id) {
        try {
            multimediaService.delete(id);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Multimedia eliminado exitosamente")
                .p_exito(true)
                .p_data(Map.of("id", id))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al eliminar multimedia: " + e.getMessage())
                .p_mensavis("Verifica que el archivo exista e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("id", id, "error", e.getMessage()))
                .build();
        }
    }

    @PutMapping("/modificar")
    public MensajeErrorDTO modificar(@Valid @RequestBody MultimediaDTO dto) {
        try {
            Multimedia multimedia = modelMapper.map(dto, Multimedia.class);
            multimediaService.insert(multimedia);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Multimedia modificado exitosamente")
                .p_exito(true)
                .p_data(Map.of("multimedia", modelMapper.map(multimedia, MultimediaDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al modificar multimedia: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar-por-id/{id}")
    public MensajeErrorDTO listarId(@PathVariable("id") Long id) {
        try {
            Multimedia multimedia = multimediaService.listId(id);
            if (multimedia == null) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Multimedia no encontrado")
                    .p_mensavis("Verifica que el ID sea correcto")
                    .p_exito(false)
                    .p_data(Map.of("id", id, "error", "NOT_FOUND"))
                    .build();
            }
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Multimedia encontrado exitosamente")
                .p_exito(true)
                .p_data(Map.of("multimedia", modelMapper.map(multimedia, MultimediaDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al buscar multimedia: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("id", id, "error", e.getMessage()))
                .build();
        }
    }
}
