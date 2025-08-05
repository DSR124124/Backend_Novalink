package com.diegoygabriela.backend_novalink.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultimediaDTO {
    private Long id;
    private String tipo;
    private String url;
    private String descripcion;
    private Long citaId;
    private String citaTitulo;
}