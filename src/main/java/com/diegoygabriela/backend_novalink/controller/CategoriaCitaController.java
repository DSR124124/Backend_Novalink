package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.CategoriaCitaDTO;
import com.diegoygabriela.backend_novalink.entity.CategoriaCita;
import com.diegoygabriela.backend_novalink.service.Inter.CategoriaCitaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias-cita")
public class CategoriaCitaController {

    @Autowired
    private CategoriaCitaService service;

    @PostMapping("/registrar")
    public void registrar(@RequestBody CategoriaCitaDTO dto) {
        ModelMapper mapper = new ModelMapper();
        CategoriaCita entity = mapper.map(dto, CategoriaCita.class);
        service.insert(entity);
    }

    @GetMapping("/listar")
    public List<CategoriaCitaDTO> listar() {
        ModelMapper mapper = new ModelMapper();
        return service.list().stream()
                .map(entity -> mapper.map(entity, CategoriaCitaDTO.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody CategoriaCitaDTO dto) {
        ModelMapper mapper = new ModelMapper();
        CategoriaCita entity = mapper.map(dto, CategoriaCita.class);
        service.insert(entity); // insert también funciona como update
    }

    @GetMapping("/listar-por-id/{id}")
    public CategoriaCitaDTO listarPorId(@PathVariable("id") Long id) {
        ModelMapper mapper = new ModelMapper();
        CategoriaCita entity = service.listId(id);
        return mapper.map(entity, CategoriaCitaDTO.class);
    }
}
