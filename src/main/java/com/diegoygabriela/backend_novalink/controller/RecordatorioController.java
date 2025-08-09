package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.RecordatorioDTO;
import com.diegoygabriela.backend_novalink.entity.Recordatorio;
import com.diegoygabriela.backend_novalink.service.Inter.RecordatorioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recordatorios")
public class RecordatorioController {

    @Autowired
    private RecordatorioService recordatorioService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registrar")
    public void registrar(@Valid @RequestBody RecordatorioDTO dto) {
        Recordatorio recordatorio = modelMapper.map(dto, Recordatorio.class);
        recordatorioService.insert(recordatorio);
    }

    @GetMapping("/listar")
    public List<RecordatorioDTO> listar() {
        return recordatorioService.list().stream()
                .map(recordatorio -> modelMapper.map(recordatorio, RecordatorioDTO.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        recordatorioService.delete(id);
    }

    @PutMapping("/modificar")
    public void modificar(@Valid @RequestBody RecordatorioDTO dto) {
        Recordatorio recordatorio = modelMapper.map(dto, Recordatorio.class);
        recordatorioService.insert(recordatorio);
    }

    @GetMapping("/listar-por-id/{id}")
    public RecordatorioDTO listarId(@PathVariable("id") Long id) {
        return modelMapper.map(recordatorioService.listId(id), RecordatorioDTO.class);
    }
    
    @GetMapping("/listar-por-pareja/{parejaId}")
    public List<RecordatorioDTO> listarPorPareja(@PathVariable("parejaId") Long parejaId) {
        return recordatorioService.findByParejaId(parejaId).stream()
                .map(recordatorio -> modelMapper.map(recordatorio, RecordatorioDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/listar-activos/{parejaId}")
    public List<RecordatorioDTO> listarActivos(@PathVariable("parejaId") Long parejaId) {
        return recordatorioService.findActivosByParejaId(parejaId).stream()
                .map(recordatorio -> modelMapper.map(recordatorio, RecordatorioDTO.class))
                .collect(Collectors.toList());
    }
}
