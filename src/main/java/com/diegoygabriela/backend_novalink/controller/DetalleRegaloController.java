package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.DetalleRegaloDTO;
import com.diegoygabriela.backend_novalink.entity.DetalleRegalo;
import com.diegoygabriela.backend_novalink.service.Inter.DetalleRegaloService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/detalles-regalo")
public class DetalleRegaloController {

    @Autowired
    private DetalleRegaloService detalleRegaloService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public void registrar(@Valid @RequestBody DetalleRegaloDTO dto) {
        
        DetalleRegalo detalleRegalo = modelMapper.map(dto, DetalleRegalo.class);
        detalleRegaloService.insert(detalleRegalo);
    }

    @GetMapping("/listar")
    public List<DetalleRegaloDTO> listar() {
        return detalleRegaloService.list().stream().map(detalleRegalo -> {
            
            return modelMapper.map(detalleRegalo, DetalleRegaloDTO.class);
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        detalleRegaloService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@Valid @RequestBody DetalleRegaloDTO dto) {
        
        DetalleRegalo detalleRegalo = modelMapper.map(dto, DetalleRegalo.class);
        detalleRegaloService.insert(detalleRegalo);
    }

    @GetMapping("/listar-por-id/{id}")
    public DetalleRegaloDTO listarId(@PathVariable("id") Long id) {
        
        return modelMapper.map(detalleRegaloService.listId(id), DetalleRegaloDTO.class);
    }
    
    // Endpoints específicos para la funcionalidad de regalos
    @GetMapping("/pareja/{parejaId}")
    public List<DetalleRegaloDTO> listarPorPareja(@PathVariable("parejaId") Long parejaId) {
        return detalleRegaloService.findByParejaId(parejaId).stream()
                .map(regalo -> modelMapper.map(regalo, DetalleRegaloDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/remitente/{remitenteId}")
    public List<DetalleRegaloDTO> listarPorRemitente(@PathVariable("remitenteId") Integer remitenteId) {
        return detalleRegaloService.findByRemitenteId(remitenteId).stream()
                .map(regalo -> modelMapper.map(regalo, DetalleRegaloDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/receptor/{receptorId}")
    public List<DetalleRegaloDTO> listarPorReceptor(@PathVariable("receptorId") Integer receptorId) {
        return detalleRegaloService.findByReceptorId(receptorId).stream()
                .map(regalo -> modelMapper.map(regalo, DetalleRegaloDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/entre-usuarios/{usuario1Id}/{usuario2Id}")
    public List<DetalleRegaloDTO> listarEntreUsuarios(@PathVariable("usuario1Id") Integer usuario1Id,
                                                     @PathVariable("usuario2Id") Integer usuario2Id) {
        return detalleRegaloService.findRegalosBetweenUsers(usuario1Id, usuario2Id).stream()
                .map(regalo -> modelMapper.map(regalo, DetalleRegaloDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/cita/{citaId}")
    public List<DetalleRegaloDTO> listarPorCita(@PathVariable("citaId") Long citaId) {
        return detalleRegaloService.findByCitaId(citaId).stream()
                .map(regalo -> modelMapper.map(regalo, DetalleRegaloDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/evento/{eventoId}")
    public List<DetalleRegaloDTO> listarPorEvento(@PathVariable("eventoId") Long eventoId) {
        return detalleRegaloService.findByEventoId(eventoId).stream()
                .map(regalo -> modelMapper.map(regalo, DetalleRegaloDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/recientes/{dias}")
    public List<DetalleRegaloDTO> listarRecientes(@PathVariable("dias") int dias) {
        return detalleRegaloService.findRecentRegalos(dias).stream()
                .map(regalo -> modelMapper.map(regalo, DetalleRegaloDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/contar-pareja/{parejaId}")
    public Long contarPorPareja(@PathVariable("parejaId") Long parejaId) {
        return detalleRegaloService.countByParejaId(parejaId);
    }
}