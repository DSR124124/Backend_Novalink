package com.diegoygabriela.backend_novalink.controller;
import com.diegoygabriela.backend_novalink.dtos.RolDTO;
import com.diegoygabriela.backend_novalink.entity.Role;
import com.diegoygabriela.backend_novalink.service.Inter.RolService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @PostMapping("/registrar")
    public void registrar(@RequestBody RolDTO dto) {
        ModelMapper mapper = new ModelMapper();
        Role role = mapper.map(dto, Role.class);
        rolService.insert(role);
    }

    @GetMapping("/listar")
    public List<RolDTO> listar() {
        return rolService.list().stream().map(rol -> {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(rol, RolDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        rolService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody RolDTO dto) {
        ModelMapper mapper = new ModelMapper();
        Role role = mapper.map(dto, Role.class);
        rolService.insert(role);
    }

    @GetMapping("/listar-por-id/{id}")
    public RolDTO listarId(@PathVariable("id") Long id) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(rolService.listId(id), RolDTO.class);
    }
}
