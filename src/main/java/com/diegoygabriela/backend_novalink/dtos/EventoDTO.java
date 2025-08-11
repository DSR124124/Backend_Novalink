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
public class EventoDTO {
    private Long id;
    
    @NotNull(message = "La pareja es obligatoria")
    private Long parejaId;
    
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 100, message = "El título no puede exceder 100 caracteres")
    private String titulo;
    
    @Size(max = 1000, message = "La descripción no puede exceder 1000 caracteres")
    private String descripcion;
    
    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;
    
    @Size(max = 50, message = "El tipo no puede exceder 50 caracteres")
    private String tipo;
    
    // Lugar asociado al evento (opcional)
    private Long lugarId;
    
    // Campos informativos (solo lectura)
    private String lugarNombre;
}