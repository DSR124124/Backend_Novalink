package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.LugarDTO;
import com.diegoygabriela.backend_novalink.dtos.MensajeErrorDTO;
import com.diegoygabriela.backend_novalink.entity.Lugar;
import com.diegoygabriela.backend_novalink.service.Inter.LugarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lugares")
@CrossOrigin
public class LugarController {

    @Autowired
    private LugarService lugarService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public MensajeErrorDTO registrar(@Valid @RequestBody LugarDTO dto) {
        try {
            Lugar lugar = modelMapper.map(dto, Lugar.class);
            lugarService.insert(lugar);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Lugar registrado exitosamente")
                .p_exito(true)
                .p_data(createDataMap("lugar", modelMapper.map(lugar, LugarDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al registrar lugar: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(createDataMap("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar")
    public MensajeErrorDTO listar() {
        try {
            List<LugarDTO> lugares = lugarService.list().stream()
                .map(lugar -> modelMapper.map(lugar, LugarDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Lugares listados exitosamente")
                .p_exito(true)
                .p_data(createDataMap("lugares", lugares, "total", lugares.size()))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar lugares: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(createDataMap("error", e.getMessage()))
                .build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public MensajeErrorDTO eliminar(@PathVariable("id") Long id) {
        try {
            lugarService.delete(id);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Lugar eliminado exitosamente")
                .p_exito(true)
                .p_data(createDataMap("id", id))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al eliminar lugar: " + e.getMessage())
                .p_mensavis("Verifica que el lugar exista e intenta nuevamente")
                .p_exito(false)
                .p_data(createDataMap("id", id, "error", e.getMessage()))
                .build();
        }
    }

    @PutMapping("/modificar")
    public MensajeErrorDTO modificar(@Valid @RequestBody LugarDTO dto) {
        try {
            Lugar lugar = modelMapper.map(dto, Lugar.class);
            lugarService.insert(lugar);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Lugar modificado exitosamente")
                .p_exito(true)
                .p_data(createDataMap("lugar", modelMapper.map(lugar, LugarDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al modificar lugar: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(createDataMap("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar-por-id/{id}")
    public MensajeErrorDTO listarId(@PathVariable("id") Long id) {
        try {
            Lugar lugar = lugarService.listId(id);
            if (lugar == null) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Lugar no encontrado")
                    .p_mensavis("Verifica que el ID sea correcto")
                    .p_exito(false)
                    .p_data(createDataMap("id", id, "error", "NOT_FOUND"))
                    .build();
            }
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Lugar encontrado exitosamente")
                .p_exito(true)
                .p_data(createDataMap("lugar", modelMapper.map(lugar, LugarDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al buscar lugar: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(createDataMap("id", id, "error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/buscar/{nombre}")
    public MensajeErrorDTO buscarPorNombre(@PathVariable("nombre") String nombre) {
        try {
            List<LugarDTO> lugares = lugarService.findByNombreContainingIgnoreCase(nombre).stream()
                .map(lugar -> modelMapper.map(lugar, LugarDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Búsqueda por nombre realizada exitosamente")
                .p_exito(true)
                .p_data(createDataMap("lugares", lugares, "nombre", nombre, "total", lugares.size()))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al buscar por nombre: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(createDataMap("nombre", nombre, "error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/categoria/{categoria}")
    public MensajeErrorDTO listarPorCategoria(@PathVariable("categoria") String categoria) {
        try {
            // Convertir String a CategoriaLugar enum
            Lugar.CategoriaLugar categoriaEnum;
            try {
                categoriaEnum = Lugar.CategoriaLugar.valueOf(categoria.toUpperCase());
            } catch (IllegalArgumentException e) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Categoría no válida: " + categoria)
                    .p_mensavis("Categorías válidas: RESTAURANTE, BAR, CINE, PARQUE, OTRO")
                    .p_exito(false)
                    .p_data(createDataMap("categoria", categoria))
                    .build();
            }
            
            List<LugarDTO> lugares = lugarService.findByCategoria(categoriaEnum).stream()
                .map(lugar -> modelMapper.map(lugar, LugarDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Lugares por categoría listados exitosamente")
                .p_exito(true)
                .p_data(createDataMap("lugares", lugares, "categoria", categoria, "total", lugares.size()))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar por categoría: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(createDataMap("categoria", categoria, "error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/favoritos")
    public MensajeErrorDTO listarFavoritos() {
        try {
            List<LugarDTO> lugares = lugarService.findByEsFavorito(true).stream()
                .map(lugar -> modelMapper.map(lugar, LugarDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Lugares favoritos listados exitosamente")
                .p_exito(true)
                .p_data(createDataMap("lugares", lugares, "total", lugares.size()))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar favoritos: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(createDataMap("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/mas-visitados")
    public MensajeErrorDTO listarMasVisitados() {
        try {
            List<LugarDTO> lugares = lugarService.findMasVisitados().stream()
                .map(lugar -> modelMapper.map(lugar, LugarDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Lugares más visitados listados exitosamente")
                .p_exito(true)
                .p_data(createDataMap("lugares", lugares, "total", lugares.size()))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar más visitados: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(createDataMap("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/mejor-calificados")
    public MensajeErrorDTO listarMejorCalificados() {
        try {
            List<LugarDTO> lugares = lugarService.findMejorCalificados().stream()
                .map(lugar -> modelMapper.map(lugar, LugarDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Lugares mejor calificados listados exitosamente")
                .p_exito(true)
                .p_data(createDataMap("lugares", lugares, "total", lugares.size()))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar mejor calificados: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(createDataMap("error", e.getMessage()))
                .build();
        }
    }

    @PutMapping("/marcar-favorito/{id}")
    public MensajeErrorDTO marcarComoFavorito(@PathVariable("id") Long id) {
        try {
            lugarService.marcarComoFavorito(id);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Lugar marcado como favorito exitosamente")
                .p_exito(true)
                .p_data(createDataMap("id", id))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al marcar como favorito: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(createDataMap("id", id, "error", e.getMessage()))
                .build();
        }
    }

    @PutMapping("/desmarcar-favorito/{id}")
    public MensajeErrorDTO desmarcarComoFavorito(@PathVariable("id") Long id) {
        try {
            lugarService.desmarcarComoFavorito(id);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Lugar desmarcado como favorito exitosamente")
                .p_exito(true)
                .p_data(createDataMap("id", id))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al desmarcar como favorito: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(createDataMap("id", id, "error", e.getMessage()))
                .build();
        }
    }
    
    // Método helper para crear mapas de datos compatibles con Java 8
    private Map<String, Object> createDataMap(Object... keyValuePairs) {
        Map<String, Object> data = new HashMap<>();
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            if (i + 1 < keyValuePairs.length) {
                data.put(keyValuePairs[i].toString(), keyValuePairs[i + 1]);
            }
        }
        return data;
    }
}
