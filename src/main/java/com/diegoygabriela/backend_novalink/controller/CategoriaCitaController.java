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
            CategoriaCita saved = categoriaCitaService.insert(categoriaCita);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Categoría registrada exitosamente")
                .p_exito(true)
                .p_data(Map.of("categoriaCita", modelMapper.map(saved, CategoriaCitaDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al registrar: " + e.getMessage())
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
                .map(categoria -> {
                    CategoriaCitaDTO dto = modelMapper.map(categoria, CategoriaCitaDTO.class);
                    dto.setTotalCitas(categoriaCitaService.countCitasByCategoria(categoria.getId()));
                    return dto;
                })
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Categorías listadas exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "categorias", categorias,
                    "total", categorias.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/buscar/{id}")
    public MensajeErrorDTO buscarPorId(@PathVariable Long id) {
        try {
            CategoriaCita categoriaCita = categoriaCitaService.listId(id);
            if (categoriaCita == null) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Categoría no encontrada")
                    .p_mensavis("Verifica que el ID sea correcto")
                    .p_exito(false)
                    .p_data(Map.of("error", "NOT_FOUND"))
                    .build();
            }
            
            CategoriaCitaDTO dto = modelMapper.map(categoriaCita, CategoriaCitaDTO.class);
            dto.setTotalCitas(categoriaCitaService.countCitasByCategoria(id));
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Categoría encontrada exitosamente")
                .p_exito(true)
                .p_data(Map.of("categoriaCita", dto))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al buscar: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @PutMapping("/modificar")
    public MensajeErrorDTO modificar(@Valid @RequestBody CategoriaCitaDTO dto) {
        try {
            if (dto.getId() == null) {
                return MensajeErrorDTO.builder()
                    .p_menserror("ID requerido")
                    .p_mensavis("El ID es obligatorio para modificar")
                    .p_exito(false)
                    .p_data(Map.of("error", "ID_REQUIRED"))
                    .build();
            }
            
            CategoriaCita categoriaCita = modelMapper.map(dto, CategoriaCita.class);
            CategoriaCita updated = categoriaCitaService.update(categoriaCita);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Categoría modificada exitosamente")
                .p_exito(true)
                .p_data(Map.of("categoriaCita", modelMapper.map(updated, CategoriaCitaDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al modificar: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public MensajeErrorDTO eliminar(@PathVariable Long id) {
        try {
            if (!categoriaCitaService.canDelete(id)) {
                return MensajeErrorDTO.builder()
                    .p_menserror("No se puede eliminar")
                    .p_mensavis("Esta categoría tiene citas asociadas")
                    .p_exito(false)
                    .p_data(Map.of("error", "HAS_RELATED_CITAS"))
                    .build();
            }
            
            categoriaCitaService.delete(id);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Categoría eliminada exitosamente")
                .p_exito(true)
                .p_data(Map.of("id", id))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al eliminar: " + e.getMessage())
                .p_mensavis("Verifica que la categoría exista")
                .p_exito(false)
                .p_data(Map.of("error", e.getMessage()))
                .build();
        }
    }
}
