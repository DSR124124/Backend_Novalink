package com.diegoygabriela.backend_novalink.dtos;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CambiarEstadoParejaDTO {
    
    @NotNull(message = "El ID de la pareja es obligatorio")
    @Min(value = 1, message = "El ID de la pareja debe ser válido")
    private Long parejaId;
    
    @NotBlank(message = "El nuevo estado es obligatorio")
    @Pattern(regexp = "^(activa|pausada|terminada)$", 
             message = "Estado debe ser: activa, pausada o terminada")
    private String nuevoEstado;
}
