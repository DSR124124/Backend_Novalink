package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.CitaDTO;
import com.diegoygabriela.backend_novalink.entity.Cita;
import com.diegoygabriela.backend_novalink.entity.EstadoCita;
import com.diegoygabriela.backend_novalink.service.Inter.CitaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public void registrar(@Valid @RequestBody CitaDTO dto) {
        
        Cita cita = modelMapper.map(dto, Cita.class);
        citaService.insert(cita);
    }

    @GetMapping("/listar")
    public List<CitaDTO> listar() {
        return citaService.list().stream().map(cita -> {
            
            return modelMapper.map(cita, CitaDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        citaService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@Valid @RequestBody CitaDTO dto) {
        
        Cita cita = modelMapper.map(dto, Cita.class);
        citaService.insert(cita);
    }

    @GetMapping("/listar-por-id/{id}")
    public CitaDTO listarId(@PathVariable("id") Long id) {
        
        return modelMapper.map(citaService.listId(id), CitaDTO.class);
    }
    
    // Endpoints específicos para la plataforma de parejas
    @GetMapping("/listar-por-pareja/{parejaId}")
    public List<CitaDTO> listarPorPareja(@PathVariable("parejaId") Long parejaId) {
        return citaService.findByParejaId(parejaId).stream()
                .map(cita -> modelMapper.map(cita, CitaDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/listar-por-estado/{estado}")
    public List<CitaDTO> listarPorEstado(@PathVariable("estado") EstadoCita estado) {
        return citaService.findByEstado(estado).stream()
                .map(cita -> modelMapper.map(cita, CitaDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/futuras/{parejaId}")
    public List<CitaDTO> citasFuturas(@PathVariable("parejaId") Long parejaId) {
        return citaService.findCitasFuturasByPareja(parejaId).stream()
                .map(cita -> modelMapper.map(cita, CitaDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/pasadas/{parejaId}")
    public List<CitaDTO> citasPasadas(@PathVariable("parejaId") Long parejaId) {
        return citaService.findCitasPasadasByPareja(parejaId).stream()
                .map(cita -> modelMapper.map(cita, CitaDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/mejor-calificadas/{parejaId}")
    public List<CitaDTO> mejorCalificadas(@PathVariable("parejaId") Long parejaId) {
        return citaService.findMejorCalificadasByPareja(parejaId).stream()
                .map(cita -> modelMapper.map(cita, CitaDTO.class))
                .collect(Collectors.toList());
    }
    
    @PutMapping("/completar/{id}")
    public void completarCita(@PathVariable("id") Long id) {
        citaService.marcarComoCompletada(id);
    }
    
    @PutMapping("/cancelar/{id}")
    public void cancelarCita(@PathVariable("id") Long id) {
        citaService.marcarComoCancelada(id);
    }
    
    @PutMapping("/calificar/{id}/{rating}")
    public void calificarCita(@PathVariable("id") Long id, @PathVariable("rating") Integer rating) {
        citaService.actualizarRating(id, rating);
    }
}