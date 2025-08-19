package com.diegoygabriela.backend_novalink.dtos;

import com.diegoygabriela.backend_novalink.entity.EstadoCita;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CitaDTO {
    private Long id;
    
    @NotNull(message = "La pareja es obligatoria")
    private Long parejaId;
    
    @NotNull(message = "El lugar es obligatorio")
    private Long lugarId;
    
    private Long categoriaId;
    
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 100, message = "El título no puede exceder 100 caracteres")
    private String titulo;
    
    @Size(max = 1000, message = "La descripción no puede exceder 1000 caracteres")
    private String descripcion;
    
    @NotNull(message = "La fecha y hora son obligatorias")
    private LocalDateTime fecha;
    
    // Estado de la cita
    private EstadoCita estado = EstadoCita.PLANIFICADA;
    
    // Rating de la cita (1-5 estrellas)
    @Min(value = 1, message = "El rating mínimo es 1")
    @Max(value = 5, message = "El rating máximo es 5")
    private Integer rating;
    
    // Recuerdos multimedia (fotos, videos, etc.)
    private java.util.List<Long> recuerdosIds;
    
    // Notas personales asociadas a la cita
    private java.util.List<Long> notasIds;
    
    // Campos informativos (solo lectura)
    private String lugarNombre;
    private String categoriaNombre;
    private String estadoDescripcion;
}
