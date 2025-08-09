package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.LugarDTO;
import com.diegoygabriela.backend_novalink.entity.Lugar;
import com.diegoygabriela.backend_novalink.service.Inter.LugarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lugares")
public class LugarController {

    @Autowired
    private LugarService lugarService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public void registrar(@Valid @RequestBody LugarDTO dto) {
        
        Lugar lugar = modelMapper.map(dto, Lugar.class);
        lugarService.insert(lugar);
    }

    @GetMapping("/listar")
    public List<LugarDTO> listar() {
        
        return lugarService.list().stream()
                .map(lugar -> modelMapper.map(lugar, LugarDTO.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        lugarService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@Valid @RequestBody LugarDTO dto) {
        
        Lugar lugar = modelMapper.map(dto, Lugar.class);
        lugarService.insert(lugar); // insert también hace update
    }

    @GetMapping("/listar-por-id/{id}")
    public LugarDTO listarId(@PathVariable("id") Long id) {
        
        Lugar lugar = lugarService.listId(id);
        return modelMapper.map(lugar, LugarDTO.class);
    }
}
