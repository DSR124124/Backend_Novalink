package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.CitaDTO;
import com.diegoygabriela.backend_novalink.entity.Cita;
import com.diegoygabriela.backend_novalink.service.Inter.CitaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void registrar(@RequestBody CitaDTO dto) {
        
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
    public void eliminar(@PathVariable("id") int id) {
        citaService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody CitaDTO dto) {
        
        Cita cita = modelMapper.map(dto, Cita.class);
        citaService.insert(cita);
    }

    @GetMapping("/listar-por-id/{id}")
    public CitaDTO listarId(@PathVariable("id") int id) {
        
        return modelMapper.map(citaService.listId(id), CitaDTO.class);
    }
}