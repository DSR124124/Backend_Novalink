package com.diegoygabriela.backend_novalink.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CambioPasswordDTO {
    
    private Long idUsuario;
    
    @NotBlank(message = "La contraseña actual es obligatoria")
    private String passwordActual;
    
    @NotBlank(message = "La nueva contraseña es obligatoria")
    @Size(min = 6, max = 200, message = "La nueva contraseña debe tener entre 6 y 200 caracteres")
    private String passwordNueva;
    
    @NotBlank(message = "La confirmación de la nueva contraseña es obligatoria")
    private String passwordConfirmacion;
}
