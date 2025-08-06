package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.MultimediaEventoDTO;
import com.diegoygabriela.backend_novalink.entity.MultimediaEvento;
import com.diegoygabriela.backend_novalink.service.Inter.MultimediaEventoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/multimedia-evento")
public class MultimediaEventoController {

    @Autowired
    private MultimediaEventoService multimediaEventoService;

    @PostMapping("/registrar")
    public void registrar(@RequestBody MultimediaEventoDTO dto) {
        ModelMapper mapper = new ModelMapper();
        MultimediaEvento multimediaEvento = mapper.map(dto, MultimediaEvento.class);
        multimediaEventoService.insert(multimediaEvento);
    }

    @GetMapping("/listar")
    public List<MultimediaEventoDTO> listar() {
        return multimediaEventoService.list().stream().map(multimediaEvento -> {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(multimediaEvento, MultimediaEventoDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        multimediaEventoService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody MultimediaEventoDTO dto) {
        ModelMapper mapper = new ModelMapper();
        MultimediaEvento multimediaEvento = mapper.map(dto, MultimediaEvento.class);
        multimediaEventoService.insert(multimediaEvento);
    }

    @GetMapping("/listar-por-id/{id}")
    public MultimediaEventoDTO listarPorId(@PathVariable("id") Long id) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(multimediaEventoService.listId(id), MultimediaEventoDTO.class);
    }
}
