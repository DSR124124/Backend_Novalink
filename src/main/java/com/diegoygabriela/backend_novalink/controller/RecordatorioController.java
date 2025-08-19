package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.RecordatorioDTO;
import com.diegoygabriela.backend_novalink.dtos.MensajeErrorDTO;
import com.diegoygabriela.backend_novalink.entity.Recordatorio;
import com.diegoygabriela.backend_novalink.service.Inter.RecordatorioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recordatorios")
public class RecordatorioController {

    @Autowired
    private RecordatorioService recordatorioService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public MensajeErrorDTO registrar(@Valid @RequestBody RecordatorioDTO dto) {
        try {
            Recordatorio recordatorio = modelMapper.map(dto, Recordatorio.class);
            recordatorioService.insert(recordatorio);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Recordatorio registrado exitosamente")
                .p_exito(true)
                .p_data(createDataMap("recordatorio", modelMapper.map(recordatorio, RecordatorioDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al registrar recordatorio: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(createDataMap("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar")
    public MensajeErrorDTO listar() {
        try {
            List<RecordatorioDTO> recordatorios = recordatorioService.list().stream()
                .map(recordatorio -> modelMapper.map(recordatorio, RecordatorioDTO.class))
                .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Recordatorios listados exitosamente")
                .p_exito(true)
                .p_data(createDataMap("recordatorios", recordatorios, "total", recordatorios.size()))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar recordatorios: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(createDataMap("error", e.getMessage()))
                .build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public MensajeErrorDTO eliminar(@PathVariable("id") Long id) {
        try {
            recordatorioService.delete(id);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Recordatorio eliminado exitosamente")
                .p_exito(true)
                .p_data(createDataMap("id", id))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al eliminar recordatorio: " + e.getMessage())
                .p_mensavis("Verifica que el recordatorio exista e intenta nuevamente")
                .p_exito(false)
                .p_data(createDataMap("id", id, "error", e.getMessage()))
                .build();
        }
    }

    @PutMapping("/modificar")
    public MensajeErrorDTO modificar(@Valid @RequestBody RecordatorioDTO dto) {
        try {
            Recordatorio recordatorio = modelMapper.map(dto, Recordatorio.class);
            recordatorioService.insert(recordatorio);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Recordatorio modificado exitosamente")
                .p_exito(true)
                .p_data(createDataMap("recordatorio", modelMapper.map(recordatorio, RecordatorioDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al modificar recordatorio: " + e.getMessage())
                .p_mensavis("Verifica los datos e intenta nuevamente")
                .p_exito(false)
                .p_data(createDataMap("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/listar-por-id/{id}")
    public MensajeErrorDTO listarId(@PathVariable("id") Long id) {
        try {
            Recordatorio recordatorio = recordatorioService.listId(id);
            if (recordatorio == null) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Recordatorio no encontrado")
                    .p_mensavis("Verifica que el ID sea correcto")
                    .p_exito(false)
                    .p_data(createDataMap("id", id, "error", "NOT_FOUND"))
                    .build();
            }
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Recordatorio encontrado exitosamente")
                .p_exito(true)
                .p_data(createDataMap("recordatorio", modelMapper.map(recordatorio, RecordatorioDTO.class)))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al buscar recordatorio: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(createDataMap("id", id, "error", e.getMessage()))
                .build();
        }
    }
    
    // Endpoints específicos para la plataforma de parejas
    @GetMapping("/listar-por-pareja/{parejaId}")
    public MensajeErrorDTO listarPorPareja(@PathVariable("parejaId") Long parejaId) {
        try {
            List<RecordatorioDTO> recordatorios = recordatorioService.findByParejaId(parejaId).stream()
                    .map(recordatorio -> modelMapper.map(recordatorio, RecordatorioDTO.class))
                    .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Recordatorios por pareja listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "recordatorios", recordatorios,
                    "parejaId", parejaId,
                    "total", recordatorios.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar recordatorios por pareja: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(createDataMap("parejaId", parejaId, "error", e.getMessage()))
                .build();
        }
    }
    
    @GetMapping("/activos/{parejaId}")
    public MensajeErrorDTO listarActivos(@PathVariable("parejaId") Long parejaId) {
        try {
            List<RecordatorioDTO> recordatorios = recordatorioService.findActivosByParejaId(parejaId).stream()
                    .map(recordatorio -> modelMapper.map(recordatorio, RecordatorioDTO.class))
                    .collect(Collectors.toList());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Recordatorios activos listados exitosamente")
                .p_exito(true)
                .p_data(Map.of(
                    "recordatorios", recordatorios,
                    "parejaId", parejaId,
                    "total", recordatorios.size()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al listar recordatorios activos: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(createDataMap("parejaId", parejaId, "error", e.getMessage()))
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
