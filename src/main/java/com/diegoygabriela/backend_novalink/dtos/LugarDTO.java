package com.diegoygabriela.backend_novalink.dtos;

import com.diegoygabriela.backend_novalink.entity.Lugar;
import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LugarDTO {

    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;
    
    @Size(max = 200, message = "La dirección no puede exceder 200 caracteres")
    private String direccion;
    
    @NotNull(message = "La latitud es obligatoria")
    @DecimalMin(value = "-90.0", message = "La latitud debe estar entre -90 y 90")
    @DecimalMax(value = "90.0", message = "La latitud debe estar entre -90 y 90")
    private Double latitud;
    
    @NotNull(message = "La longitud es obligatoria")
    @DecimalMin(value = "-180.0", message = "La longitud debe estar entre -180 y 180")
    @DecimalMax(value = "180.0", message = "La longitud debe estar entre -180 y 180")
    private Double longitud;
    
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;
    
    // Categoría del lugar para iconos en mapa
    private Lugar.CategoriaLugar categoria;
    
    // Rating promedio del lugar (calculado)
    @DecimalMin(value = "0.0", message = "El rating no puede ser negativo")
    @DecimalMax(value = "5.0", message = "El rating máximo es 5.0")
    private Double ratingPromedio = 0.0;
    
    // Número de veces visitado
    @Min(value = 0, message = "Las visitas no pueden ser negativas")
    private Integer vecesVisitado = 0;
    
    // Si es un lugar favorito de la pareja
    private Boolean esFavorito = false;
    
    // Citas que ocurrieron en este lugar (opcional)
    private java.util.List<Long> citasIds;
    
    // Campos informativos (solo lectura)
    private String categoriaIcono;
    private String categoriaNombre;
}
