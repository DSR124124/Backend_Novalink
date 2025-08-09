package com.diegoygabriela.backend_novalink.dtos;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultimediaEventoDTO {
    private Long id;
    
    @NotNull(message = "El evento es obligatorio")
    private Long eventoId;
    
    @NotNull(message = "El autor es obligatorio")
    private Long autorId;
    
    @NotBlank(message = "La URL es obligatoria")
    @Size(max = 255, message = "La URL no puede exceder 255 caracteres")
    private String url;
    
    @NotBlank(message = "El tipo es obligatorio")
    @Pattern(regexp = "^(FOTO|VIDEO|AUDIO|DOCUMENTO)$", message = "Tipo debe ser: FOTO, VIDEO, AUDIO o DOCUMENTO")
    private String tipo;
    
    @Size(max = 300, message = "La descripción no puede exceder 300 caracteres")
    private String descripcion;
    
    private LocalDateTime fechaSubida;
    
    // Campos informativos (solo lectura)
    private String autorNombre;
    private String eventoTitulo;
}
