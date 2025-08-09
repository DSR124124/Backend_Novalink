package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.ParejaDTO;
import com.diegoygabriela.backend_novalink.entity.Pareja;
import com.diegoygabriela.backend_novalink.service.Inter.ParejaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/parejas")
public class ParejaController {

    @Autowired
    private ParejaService parejaService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public void registrar(@RequestBody ParejaDTO dto) {

        Pareja pareja = modelMapper.map(dto, Pareja.class);
        parejaService.insert(pareja);
    }

    @GetMapping("/listar")
    public List<ParejaDTO> listar() {
        return parejaService.list().stream()
                .map(pareja -> modelMapper.map(pareja, ParejaDTO.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        parejaService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody ParejaDTO dto) {

        Pareja pareja = modelMapper.map(dto, Pareja.class);
        parejaService.insert(pareja);
    }

    @GetMapping("/listar-por-id/{id}")
    public ParejaDTO listarId(@PathVariable("id") Long id) {
        return modelMapper.map(parejaService.listId(id), ParejaDTO.class);
    }
}
