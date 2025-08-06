package com.diegoygabriela.backend_novalink.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaCitaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
}
