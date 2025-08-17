package com.diegoygabriela.backend_novalink.dtos;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParejaDTO {
    private Long id;
    private Long usuario1Id;
    private Long usuario2Id;
    private LocalDate fechaCreacion;
    private String estadoRelacion;
    
    // Información básica de usuarios
    private String usuario1Nombre;
    private String usuario1Apellido;
    private String usuario1FotoPerfil;
    private String usuario2Nombre;
    private String usuario2Apellido;
    private String usuario2FotoPerfil;
    
    // Campo calculado
    private Long diasRelacion;
    private String estadoRelacionFormateado;
}
