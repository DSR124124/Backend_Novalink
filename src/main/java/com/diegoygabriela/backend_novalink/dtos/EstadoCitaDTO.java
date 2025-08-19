package com.diegoygabriela.backend_novalink.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoCitaDTO {
    
    private String estado;
    private String descripcion;
    
    // Método estático para crear DTOs desde el enum
    public static EstadoCitaDTO fromEstadoCita(com.diegoygabriela.backend_novalink.entity.EstadoCita estadoCita) {
        return EstadoCitaDTO.builder()
                .estado(estadoCita.name())
                .descripcion(estadoCita.getDescripcion())
                .build();
    }
}
