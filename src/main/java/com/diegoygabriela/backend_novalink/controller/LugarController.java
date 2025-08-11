package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.LugarDTO;
import com.diegoygabriela.backend_novalink.entity.Lugar.CategoriaLugar;
import com.diegoygabriela.backend_novalink.entity.Lugar;
import com.diegoygabriela.backend_novalink.service.Inter.LugarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lugares")
public class LugarController {

    @Autowired
    private LugarService lugarService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public void registrar(@Valid @RequestBody LugarDTO dto) {
        
        Lugar lugar = modelMapper.map(dto, Lugar.class);
        lugarService.insert(lugar);
    }

    @GetMapping("/listar")
    public List<LugarDTO> listar() {
        
        return lugarService.list().stream()
                .map(lugar -> modelMapper.map(lugar, LugarDTO.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        lugarService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@Valid @RequestBody LugarDTO dto) {
        
        Lugar lugar = modelMapper.map(dto, Lugar.class);
        lugarService.insert(lugar); // insert también hace update
    }

    @GetMapping("/listar-por-id/{id}")
    public LugarDTO listarId(@PathVariable("id") Long id) {
        
        Lugar lugar = lugarService.listId(id);
        return modelMapper.map(lugar, LugarDTO.class);
    }
    
    // Endpoints específicos para la plataforma de parejas
    @GetMapping("/buscar/{nombre}")
    public List<LugarDTO> buscarPorNombre(@PathVariable("nombre") String nombre) {
        return lugarService.findByNombreContainingIgnoreCase(nombre).stream()
                .map(lugar -> modelMapper.map(lugar, LugarDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/categoria/{categoria}")
    public List<LugarDTO> listarPorCategoria(@PathVariable("categoria") CategoriaLugar categoria) {
        return lugarService.findByCategoria(categoria).stream()
                .map(lugar -> modelMapper.map(lugar, LugarDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/favoritos")
    public List<LugarDTO> listarFavoritos() {
        return lugarService.findByEsFavorito(true).stream()
                .map(lugar -> modelMapper.map(lugar, LugarDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/mas-visitados")
    public List<LugarDTO> masVisitados() {
        return lugarService.findMasVisitados().stream()
                .map(lugar -> modelMapper.map(lugar, LugarDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/mejor-calificados")
    public List<LugarDTO> mejorCalificados() {
        return lugarService.findMejorCalificados().stream()
                .map(lugar -> modelMapper.map(lugar, LugarDTO.class))
                .collect(Collectors.toList());
    }
    
    @PutMapping("/marcar-favorito/{id}")
    public void marcarComoFavorito(@PathVariable("id") Long id) {
        Lugar lugar = lugarService.listId(id);
        if (lugar != null) {
            lugar.setEsFavorito(true);
            lugarService.insert(lugar);
        }
    }
    
    @PutMapping("/desmarcar-favorito/{id}")
    public void desmarcarComoFavorito(@PathVariable("id") Long id) {
        Lugar lugar = lugarService.listId(id);
        if (lugar != null) {
            lugar.setEsFavorito(false);
            lugarService.insert(lugar);
        }
    }
}
