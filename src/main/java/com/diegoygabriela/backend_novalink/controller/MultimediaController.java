package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.MultimediaDTO;
import com.diegoygabriela.backend_novalink.entity.Multimedia;
import com.diegoygabriela.backend_novalink.service.Inter.MultimediaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/multimedia")
public class MultimediaController {

    @Autowired
    private MultimediaService multimediaService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public void registrar(@RequestBody MultimediaDTO dto) {
        
        Multimedia multimedia = modelMapper.map(dto, Multimedia.class);
        multimediaService.insert(multimedia);
    }

    @GetMapping("/listar")
    public List<MultimediaDTO> listar() {
        return multimediaService.list().stream().map(multimedia -> {
            
            return modelMapper.map(multimedia, MultimediaDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") int id) {
        multimediaService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody MultimediaDTO dto) {
        
        Multimedia multimedia = modelMapper.map(dto, Multimedia.class);
        multimediaService.insert(multimedia);
    }

    @GetMapping("/listar-por-id/{id}")
    public MultimediaDTO listarId(@PathVariable("id") int id) {
        
        return modelMapper.map(multimediaService.listId(id), MultimediaDTO.class);
    }
}