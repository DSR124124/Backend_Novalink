package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.NotaDTO;
import com.diegoygabriela.backend_novalink.entity.Nota;
import com.diegoygabriela.backend_novalink.service.Inter.NotaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @PostMapping("/registrar")
    public void registrar(@RequestBody NotaDTO dto) {
        ModelMapper mapper = new ModelMapper();
        Nota nota = mapper.map(dto, Nota.class);
        notaService.insert(nota);
    }

    @GetMapping("/listar")
    public List<NotaDTO> listar() {
        return notaService.list().stream().map(nota -> {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(nota, NotaDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") int id) {
        notaService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody NotaDTO dto) {
        ModelMapper mapper = new ModelMapper();
        Nota nota = mapper.map(dto, Nota.class);
        notaService.insert(nota);
    }

    @GetMapping("/listar-por-id/{id}")
    public NotaDTO listarId(@PathVariable("id") int id) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(notaService.listId(id), NotaDTO.class);
    }
}