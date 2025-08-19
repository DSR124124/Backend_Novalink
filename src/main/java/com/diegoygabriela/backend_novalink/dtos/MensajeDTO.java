package com.diegoygabriela.backend_novalink.dtos;

import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MensajeDTO {

    private Long id;

    @NotNull(message = "La pareja es obligatoria")
    private Long parejaId;
    
    @NotNull(message = "El remitente es obligatorio")
    private Long remitenteId;

    @NotBlank(message = "El contenido es obligatorio")
    @Size(max = 1000, message = "El contenido no puede exceder 1000 caracteres")
    private String contenido;
    
    private LocalDateTime fechaEnvio;
    
    @Pattern(regexp = "^(enviado|leido|eliminado)$", message = "Estado debe ser: enviado, leido o eliminado")
    @Builder.Default
    private String estado = "enviado";
    
    // Campos informativos (solo lectura)
    private String remitenteNombre;
}
