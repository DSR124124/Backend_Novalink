package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.MensajeDTO;
import com.diegoygabriela.backend_novalink.entity.Mensaje;
import com.diegoygabriela.backend_novalink.service.Inter.MensajeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public void registrar(@Valid @RequestBody MensajeDTO dto) {
        
        Mensaje mensaje = modelMapper.map(dto, Mensaje.class);
        mensajeService.insert(mensaje);
    }

    @GetMapping("/listar")
    public List<MensajeDTO> listar() {
        return mensajeService.list().stream().map(mensaje -> {
            
            return modelMapper.map(mensaje, MensajeDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        mensajeService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@Valid @RequestBody MensajeDTO dto) {
        
        Mensaje mensaje = modelMapper.map(dto, Mensaje.class);
        mensajeService.insert(mensaje);
    }

    @GetMapping("/listar-por-id/{id}")
    public MensajeDTO listarId(@PathVariable("id") Long id) {
        
        return modelMapper.map(mensajeService.listId(id), MensajeDTO.class);
    }
}
