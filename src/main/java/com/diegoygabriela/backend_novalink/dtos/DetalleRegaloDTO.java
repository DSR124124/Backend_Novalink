package com.diegoygabriela.backend_novalink.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetalleRegaloDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String tienda;
    private String urlProducto;
    private Long citaId;
    private String citaTitulo;
}