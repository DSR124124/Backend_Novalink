package com.diegoygabriela.backend_novalink.dtos;

import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParejaDTO {
    private Long id;
    
    @NotNull(message = "El primer usuario es obligatorio")
    private Long usuario1Id;
    
    @NotNull(message = "El segundo usuario es obligatorio")
    private Long usuario2Id;
    
    private LocalDate fechaCreacion;
    
    @Pattern(regexp = "^(activa|pausada|terminada)$", message = "Estado debe ser: activa, pausada o terminada")
    private String estadoRelacion = "activa";
    
    // Campos informativos (solo lectura)
    private String usuario1Nombre;
    private String usuario2Nombre;
}
