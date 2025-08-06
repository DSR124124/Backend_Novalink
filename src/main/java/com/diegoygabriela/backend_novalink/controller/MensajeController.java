package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.MensajeDTO;
import com.diegoygabriela.backend_novalink.entity.Mensaje;
import com.diegoygabriela.backend_novalink.service.Inter.MensajeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @PostMapping("/registrar")
    public void registrar(@RequestBody MensajeDTO dto) {
        ModelMapper mapper = new ModelMapper();
        Mensaje mensaje = mapper.map(dto, Mensaje.class);
        mensajeService.insert(mensaje);
    }

    @GetMapping("/listar")
    public List<MensajeDTO> listar() {
        return mensajeService.list().stream().map(mensaje -> {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(mensaje, MensajeDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        mensajeService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody MensajeDTO dto) {
        ModelMapper mapper = new ModelMapper();
        Mensaje mensaje = mapper.map(dto, Mensaje.class);
        mensajeService.insert(mensaje);
    }

    @GetMapping("/listar-por-id/{id}")
    public MensajeDTO listarId(@PathVariable("id") Long id) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(mensajeService.listId(id), MensajeDTO.class);
    }
}
