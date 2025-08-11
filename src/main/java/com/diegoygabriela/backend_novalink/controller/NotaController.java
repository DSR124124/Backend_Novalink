package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.NotaDTO;
import com.diegoygabriela.backend_novalink.entity.Nota;
import com.diegoygabriela.backend_novalink.service.Inter.NotaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public void registrar(@Valid @RequestBody NotaDTO dto) {
        
        Nota nota = modelMapper.map(dto, Nota.class);
        notaService.insert(nota);
    }

    @GetMapping("/listar")
    public List<NotaDTO> listar() {
        return notaService.list().stream().map(nota -> {
            
            return modelMapper.map(nota, NotaDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        notaService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@Valid @RequestBody NotaDTO dto) {
        
        Nota nota = modelMapper.map(dto, Nota.class);
        notaService.insert(nota);
    }

    @GetMapping("/listar-por-id/{id}")
    public NotaDTO listarId(@PathVariable("id") Long id) {
        
        return modelMapper.map(notaService.listId(id), NotaDTO.class);
    }
}