package com.diegoygabriela.backend_novalink.controller;
import com.diegoygabriela.backend_novalink.dtos.RolDTO;
import com.diegoygabriela.backend_novalink.entity.Role;
import com.diegoygabriela.backend_novalink.service.Inter.RolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolService rolService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public void registrar(@Valid @RequestBody RolDTO dto) {
        
        Role role = modelMapper.map(dto, Role.class);
        rolService.insert(role);
    }

    @GetMapping("/listar")
    public List<RolDTO> listar() {
        return rolService.list().stream()
                .map(rol -> modelMapper.map(rol, RolDTO.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        rolService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@Valid @RequestBody RolDTO dto) {
        
        Role role = modelMapper.map(dto, Role.class);
        rolService.insert(role);
    }

    @GetMapping("/listar-por-id/{id}")
    public RolDTO listarId(@PathVariable("id") Long id) {
        
        return modelMapper.map(rolService.listId(id), RolDTO.class);
    }
}
