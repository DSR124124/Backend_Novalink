package com.diegoygabriela.backend_novalink.dtos;

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
public class DetalleRegaloDTO {
    private Long id;
    
    @NotNull(message = "El remitente es obligatorio")
    private Long remitenteId;
    
    @NotNull(message = "El receptor es obligatorio")
    private Long receptorId;
    
    @NotNull(message = "La pareja es obligatoria")
    private Long parejaId;
    
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 100, message = "El título no puede exceder 100 caracteres")
    private String titulo;
    
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;
    
    @NotNull(message = "La fecha es obligatoria")
    private LocalDateTime fecha;
    
    // Relaciones opcionales
    private Long citaId;
    private Long eventoId;
    
    @Size(max = 255, message = "La URL multimedia no puede exceder 255 caracteres")
    private String urlMultimedia;
    
    @Pattern(regexp = "^(FOTO|VIDEO|AUDIO|DOCUMENTO)$", message = "Tipo debe ser: FOTO, VIDEO, AUDIO o DOCUMENTO")
    private String tipoMultimedia;
    
    // Campos informativos (solo lectura)
    private String remitenteNombre;
    private String receptorNombre;
    private String citaTitulo;
    private String eventoTitulo;
}