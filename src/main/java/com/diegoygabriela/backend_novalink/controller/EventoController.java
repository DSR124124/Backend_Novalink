package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.EventoDTO;
import com.diegoygabriela.backend_novalink.entity.Evento;
import com.diegoygabriela.backend_novalink.service.Inter.EventoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public void registrar(@Valid @RequestBody EventoDTO dto) {
        
        Evento evento = modelMapper.map(dto, Evento.class);
        eventoService.insert(evento);
    }

    @GetMapping("/listar")
    public List<EventoDTO> listar() {
        return eventoService.list().stream().map(evento -> {
            
            return modelMapper.map(evento, EventoDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        eventoService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@Valid @RequestBody EventoDTO dto) {
        
        Evento evento = modelMapper.map(dto, Evento.class);
        eventoService.insert(evento);
    }

    @GetMapping("/listar-por-id/{id}")
    public EventoDTO listarId(@PathVariable("id") Long id) {
        
        return modelMapper.map(eventoService.listId(id), EventoDTO.class);
    }
    
    // Endpoints específicos para la funcionalidad de eventos
    @GetMapping("/pareja/{parejaId}")
    public List<EventoDTO> listarPorPareja(@PathVariable("parejaId") Long parejaId) {
        return eventoService.findByParejaId(parejaId).stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/tipo/{tipo}")
    public List<EventoDTO> listarPorTipo(@PathVariable("tipo") String tipo) {
        return eventoService.findByTipo(tipo).stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/lugar/{lugarId}")
    public List<EventoDTO> listarPorLugar(@PathVariable("lugarId") Long lugarId) {
        return eventoService.findByLugarId(lugarId).stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/proximos")
    public List<EventoDTO> listarProximos() {
        return eventoService.findEventosProximos().stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/pasados")
    public List<EventoDTO> listarPasados() {
        return eventoService.findEventosPasados().stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/pareja/{parejaId}/tipo/{tipo}")
    public List<EventoDTO> listarPorParejaYTipo(@PathVariable("parejaId") Long parejaId,
                                               @PathVariable("tipo") String tipo) {
        return eventoService.findByParejaIdAndTipo(parejaId, tipo).stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/año/{year}")
    public List<EventoDTO> listarPorAño(@PathVariable("year") int year) {
        return eventoService.findEventosByYear(year).stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/pareja/{parejaId}/proximos/{dias}")
    public List<EventoDTO> listarProximosPorPareja(@PathVariable("parejaId") Long parejaId,
                                                  @PathVariable("dias") int dias) {
        return eventoService.findEventosProximosPorPareja(parejaId, dias).stream()
                .map(evento -> modelMapper.map(evento, EventoDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/contar-pareja/{parejaId}")
    public Long contarPorPareja(@PathVariable("parejaId") Long parejaId) {
        return eventoService.countByParejaId(parejaId);
    }
}