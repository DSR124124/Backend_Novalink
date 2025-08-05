package com.diegoygabriela.backend_novalink.controller;
import com.diegoygabriela.backend_novalink.dtos.UsuarioDTO;
import com.diegoygabriela.backend_novalink.entity.Usuario;
import com.diegoygabriela.backend_novalink.service.Inter.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public void registrar(@RequestBody UsuarioDTO dto) {
        ModelMapper mapper = new ModelMapper();
        Usuario usuario = mapper.map(dto, Usuario.class);
        usuarioService.insert(usuario);
    }

    @GetMapping("/listar")
    public List<UsuarioDTO> listar() {
        return usuarioService.list().stream().map(usuario -> {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(usuario, UsuarioDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        usuarioService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody UsuarioDTO dto) {
        ModelMapper mapper = new ModelMapper();
        Usuario usuario = mapper.map(dto, Usuario.class);
        usuarioService.insert(usuario); // Insert funciona también como update en JPA
    }

    @GetMapping("/listar-por-id/{id}")
    public UsuarioDTO listarId(@PathVariable("id") Long id) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(usuarioService.listId(id), UsuarioDTO.class);
    }
    @GetMapping("/listar-por-username/{username}")
    public UsuarioDTO listarUsername(@PathVariable("username") String username) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(usuarioService.findByUsername(username), UsuarioDTO.class);
    }
}
