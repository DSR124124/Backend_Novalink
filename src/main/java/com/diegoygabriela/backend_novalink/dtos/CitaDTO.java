package com.diegoygabriela.backend_novalink.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CitaDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaHora;
    private String direccion;
    private BigDecimal latitud;
    private BigDecimal longitud;
    private Integer valoracion;
    private Long categoriaId;
    private String categoriaNombre;
    private Long usuarioId;
    private String usuarioNombre;
}