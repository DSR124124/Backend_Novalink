package com.diegoygabriela.backend_novalink.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LugarDTO {

    private Long id;
    private String nombre;
    private String direccion;
    private Double latitud;
    private Double longitud;
    private String descripcion;
}
