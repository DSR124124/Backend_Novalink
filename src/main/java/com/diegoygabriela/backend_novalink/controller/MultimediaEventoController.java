package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.MultimediaEventoDTO;
import com.diegoygabriela.backend_novalink.entity.MultimediaEvento;
import com.diegoygabriela.backend_novalink.service.Inter.MultimediaEventoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/multimedia-evento")
public class MultimediaEventoController {

    @Autowired
    private MultimediaEventoService multimediaEventoService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public void registrar(@Valid @RequestBody MultimediaEventoDTO dto) {
        
        MultimediaEvento multimediaEvento = modelMapper.map(dto, MultimediaEvento.class);
        multimediaEventoService.insert(multimediaEvento);
    }

    @GetMapping("/listar")
    public List<MultimediaEventoDTO> listar() {
        return multimediaEventoService.list().stream().map(multimediaEvento -> {
            
            return modelMapper.map(multimediaEvento, MultimediaEventoDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        multimediaEventoService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@Valid @RequestBody MultimediaEventoDTO dto) {
        
        MultimediaEvento multimediaEvento = modelMapper.map(dto, MultimediaEvento.class);
        multimediaEventoService.insert(multimediaEvento);
    }

    @GetMapping("/listar-por-id/{id}")
    public MultimediaEventoDTO listarPorId(@PathVariable("id") Long id) {
        
        return modelMapper.map(multimediaEventoService.listId(id), MultimediaEventoDTO.class);
    }
}
