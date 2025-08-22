package com.diegoygabriela.backend_novalink.dtos;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaCitaDTO {
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
    private String nombre;
    
    @Size(max = 200, message = "La descripción no puede exceder 200 caracteres")
    private String descripcion;
    
    @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "El color debe ser un código hexadecimal válido (ej: #007bff)")
    @Builder.Default
    private String color = "#007bff";
    
    @Size(max = 50, message = "El icono no puede exceder 50 caracteres")
    private String icono;
    
    @Builder.Default
    private Boolean activo = true;
    
    @Min(value = 0, message = "El orden debe ser mayor o igual a 0")
    @Builder.Default
    private Integer orden = 0;
    
    // Campos de auditoría (solo lectura)
    private String fechaCreacion;
    private String fechaModificacion;
    
    // Contador de citas (solo lectura)
    private Long totalCitas;
}
