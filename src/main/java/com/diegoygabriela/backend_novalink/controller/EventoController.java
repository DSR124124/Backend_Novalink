package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.EventoDTO;
import com.diegoygabriela.backend_novalink.entity.Evento;
import com.diegoygabriela.backend_novalink.service.Inter.EventoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/registrar")
    public void registrar(@RequestBody EventoDTO dto) {
        ModelMapper mapper = new ModelMapper();
        Evento evento = mapper.map(dto, Evento.class);
        eventoService.insert(evento);
    }

    @GetMapping("/listar")
    public List<EventoDTO> listar() {
        return eventoService.list().stream().map(evento -> {
            ModelMapper mapper = new ModelMapper();
            return mapper.map(evento, EventoDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") int id) {
        eventoService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody EventoDTO dto) {
        ModelMapper mapper = new ModelMapper();
        Evento evento = mapper.map(dto, Evento.class);
        eventoService.insert(evento);
    }

    @GetMapping("/listar-por-id/{id}")
    public EventoDTO listarId(@PathVariable("id") int id) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(eventoService.listId(id), EventoDTO.class);
    }
}