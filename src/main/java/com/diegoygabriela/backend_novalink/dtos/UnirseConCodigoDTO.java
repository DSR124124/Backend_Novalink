package com.diegoygabriela.backend_novalink.dtos;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnirseConCodigoDTO {
    
    @NotNull(message = "El ID del usuario que se quiere unir es obligatorio")
    @Min(value = 1, message = "El ID del usuario debe ser válido")
    private Integer usuarioId;
    
    @NotBlank(message = "El código de relación es obligatorio")
    @Size(min = 8, max = 8, message = "El código debe tener exactamente 8 caracteres")
    @Pattern(regexp = "^[A-Z0-9]{8}$", message = "El código debe contener solo letras mayúsculas y números")
    private String codigoRelacion;
    
    @NotBlank(message = "El estado de la relación es obligatorio")
    @Pattern(regexp = "^(activa|pausada|terminada)$", 
             message = "Estado debe ser: activa, pausada o terminada")
    private String estadoRelacion = "activa";
}
