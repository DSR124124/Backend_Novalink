package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.CategoriaCitaDTO;
import com.diegoygabriela.backend_novalink.dtos.MensajeErrorDTO;
import com.diegoygabriela.backend_novalink.entity.CategoriaCita;
import com.diegoygabriela.backend_novalink.service.Inter.CategoriaCitaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias-cita")
public class CategoriaCitaController {

    @Autowired
    private CategoriaCitaService categoriaCitaService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public MensajeErrorDTO registrar(@Valid @RequestBody CategoriaCitaDTO dto) {
        try {
            CategoriaCita categoriaCita = modelMapper.map(dto, CategoriaCita.class);
            categoriaCitaService.insert(categoriaCita);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Categoría de cita registrada exitosamente")
                .p_exito(true)
                .p_data(Map.of("categoriaCita", modelMapper.map(categoriaCita, CategoriaCitaDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al registrar categoría de cita: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar")
    public MensajeErrorDTO listar() {
        try {
            List<CategoriaCitaDTO> categorias = categoriaCitaService.list().stream()
                .map(categoria -> modelMapper.map(categoria, CategoriaCitaDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Categorías de cita listadas exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "categorias", categorias,
                    "total", categorias.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar categorías de cita: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public MensajeErrorDTO eliminar(@PathVariable("id") Long id) {
        try {
            categoriaCitaService.delete(id);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Categoría de cita eliminada exitosamente")
                .p_exito(true)
                .p_data(Map.of("id", id))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al eliminar categoría de cita: " + e.getMessage())
                .p_mensavis("Verifica que la categoría exista e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("id", id, "error", e.getMessage()))
                .build();
        }
    }

    @PutMapping("/modificar")
    public MensajeErrorDTO modificar(@Valid @RequestBody CategoriaCitaDTO dto) {
        try {
            CategoriaCita categoriaCita = modelMapper.map(dto, CategoriaCita.class);
            categoriaCitaService.insert(categoriaCita);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Categoría de cita modificada exitosamente")
                .p_exito(true)
                .p_data(Map.of("categoriaCita", modelMapper.map(categoriaCita, CategoriaCitaDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al modificar categoría de cita: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar-por-id/{id}")
    public MensajeErrorDTO listarPorId(@PathVariable("id") Long id) {
        try {
            CategoriaCita categoriaCita = categoriaCitaService.listId(id);
            if (categoriaCita == null) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Categoría de cita no encontrada")
                    .p_mensavis("Verifica que el ID sea correcto")
                    .p_exito(false)
                    .p_data(Map.of("id", id, "error", "NOT_FOUND"))
                    .build();
            }
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Categoría de cita encontrada exitosamente")
                .p_exito(true)
                .p_data(Map.of("categoriaCita", modelMapper.map(categoriaCita, CategoriaCitaDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al buscar categoría de cita: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("id", id, "error", e.getMessage()))
                .build();
        }
    }
}
