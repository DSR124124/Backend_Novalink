package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.DetalleRegaloDTO;
import com.diegoygabriela.backend_novalink.entity.DetalleRegalo;
import com.diegoygabriela.backend_novalink.service.Inter.DetalleRegaloService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/detalles-regalo")
public class DetalleRegaloController {

    @Autowired
    private DetalleRegaloService detalleRegaloService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public void registrar(@Valid @RequestBody DetalleRegaloDTO dto) {
        
        DetalleRegalo detalleRegalo = modelMapper.map(dto, DetalleRegalo.class);
        detalleRegaloService.insert(detalleRegalo);
    }

    @GetMapping("/listar")
    public List<DetalleRegaloDTO> listar() {
        return detalleRegaloService.list().stream().map(detalleRegalo -> {
            
            return modelMapper.map(detalleRegalo, DetalleRegaloDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") int id) {
        detalleRegaloService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@Valid @RequestBody DetalleRegaloDTO dto) {
        
        DetalleRegalo detalleRegalo = modelMapper.map(dto, DetalleRegalo.class);
        detalleRegaloService.insert(detalleRegalo);
    }

    @GetMapping("/listar-por-id/{id}")
    public DetalleRegaloDTO listarId(@PathVariable("id") int id) {
        
        return modelMapper.map(detalleRegaloService.listId(id), DetalleRegaloDTO.class);
    }
}