package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.MensajeDTO;
import com.diegoygabriela.backend_novalink.dtos.MensajeErrorDTO;
import com.diegoygabriela.backend_novalink.entity.Mensaje;
import com.diegoygabriela.backend_novalink.service.Inter.MensajeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public MensajeErrorDTO registrar(@Valid @RequestBody MensajeDTO dto) {
        try {
            Mensaje mensaje = modelMapper.map(dto, Mensaje.class);
            mensajeService.insert(mensaje);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Mensaje registrado exitosamente")
                .p_exito(true)
                .p_data(Map.of("mensaje", modelMapper.map(mensaje, MensajeDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al registrar mensaje: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar")
    public MensajeErrorDTO listar() {
        try {
            List<MensajeDTO> mensajes = mensajeService.list().stream()
                .map(mensaje -> modelMapper.map(mensaje, MensajeDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Mensajes listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "mensajes", mensajes,
                    "total", mensajes.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar mensajes: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public MensajeErrorDTO eliminar(@PathVariable("id") Long id) {
        try {
            mensajeService.delete(id);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Mensaje eliminado exitosamente")
                .p_exito(true)
                .p_data(Map.of("id", id))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al eliminar mensaje: " + e.getMessage())
                .p_mensavis("Verifica que el mensaje exista e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("id", id, "error", e.getMessage()))
                .build();
        }
    }

    @PutMapping("/modificar")
    public MensajeErrorDTO modificar(@Valid @RequestBody MensajeDTO dto) {
        try {
            Mensaje mensaje = modelMapper.map(dto, Mensaje.class);
            mensajeService.insert(mensaje);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Mensaje modificado exitosamente")
                .p_exito(true)
                .p_data(Map.of("mensaje", modelMapper.map(mensaje, MensajeDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al modificar mensaje: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar-por-id/{id}")
    public MensajeErrorDTO listarId(@PathVariable("id") Long id) {
        try {
            Mensaje mensaje = mensajeService.listId(id);
            if (mensaje == null) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Mensaje no encontrado")
                    .p_mensavis("Verifica que el ID sea correcto")
                    .p_exito(false)
                    .p_data(Map.of("id", id, "error", "NOT_FOUND"))
                    .build();
            }
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Mensaje encontrado exitosamente")
                .p_exito(true)
                .p_data(Map.of("mensaje", modelMapper.map(mensaje, MensajeDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al buscar mensaje: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("id", id, "error", e.getMessage()))
                .build();
        }
    }
}
