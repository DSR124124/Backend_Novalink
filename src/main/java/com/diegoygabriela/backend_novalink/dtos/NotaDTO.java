package com.diegoygabriela.backend_novalink.dtos;

import com.diegoygabriela.backend_novalink.entity.Nota;
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
public class NotaDTO {
    private Long id;
    
    @NotNull(message = "El autor es obligatorio")
    private Long autorId;
    
    @NotNull(message = "La cita es obligatoria")
    private Long citaId;
    
    @NotBlank(message = "El contenido es obligatorio")
    @Size(max = 1000, message = "El contenido no puede exceder 1000 caracteres")
    private String contenido;
    
    private LocalDateTime fechaCreacion;
    
    // Privacidad de la nota
    @NotNull(message = "La privacidad es obligatoria")
    private Nota.TipoPrivacidad privacidad = Nota.TipoPrivacidad.COMPARTIDA;
    
    // Campos informativos (solo lectura)
    private String autorNombre;
    private String citaTitulo;
    private String privacidadDescripcion;
}