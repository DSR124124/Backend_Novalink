package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.LugarDTO;
import com.diegoygabriela.backend_novalink.entity.Lugar;
import com.diegoygabriela.backend_novalink.service.Inter.LugarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lugares")
public class LugarController {

    @Autowired
    private LugarService lugarService;

    @PostMapping("/registrar")
    public void registrar(@RequestBody LugarDTO dto) {
        ModelMapper mapper = new ModelMapper();
        Lugar lugar = mapper.map(dto, Lugar.class);
        lugarService.insert(lugar);
    }

    @GetMapping("/listar")
    public List<LugarDTO> listar() {
        ModelMapper mapper = new ModelMapper();
        return lugarService.list().stream()
                .map(lugar -> mapper.map(lugar, LugarDTO.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        lugarService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody LugarDTO dto) {
        ModelMapper mapper = new ModelMapper();
        Lugar lugar = mapper.map(dto, Lugar.class);
        lugarService.insert(lugar); // insert también hace update
    }

    @GetMapping("/listar-por-id/{id}")
    public LugarDTO listarId(@PathVariable("id") Long id) {
        ModelMapper mapper = new ModelMapper();
        Lugar lugar = lugarService.listId(id);
        return mapper.map(lugar, LugarDTO.class);
    }
}
