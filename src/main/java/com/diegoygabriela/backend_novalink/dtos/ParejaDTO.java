package com.diegoygabriela.backend_novalink.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    
    @NotNull(message = "La fecha de creación es obligatoria")
    private LocalDate fechaCreacion;
    
    @Size(max = 20, message = "El estado de la relación no puede exceder 20 caracteres")
    private String estadoRelacion;
    
    // Campos informativos (solo lectura)
    private String usuario1Nombre;
    private String usuario1Apellido;
    private String usuario2Nombre;
    private String usuario2Apellido;
    private String estadoRelacionDescripcion;
}
