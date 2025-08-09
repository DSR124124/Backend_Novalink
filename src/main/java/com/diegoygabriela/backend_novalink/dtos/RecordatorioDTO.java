package com.diegoygabriela.backend_novalink.dtos;

import com.diegoygabriela.backend_novalink.entity.Recordatorio;
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
public class RecordatorioDTO {
    
    private Long id;
    
    @NotNull(message = "La pareja es obligatoria")
    private Long parejaId;
    
    @NotNull(message = "El creador es obligatorio")
    private Long creadoPorId;
    
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 100, message = "El título no puede exceder 100 caracteres")
    private String titulo;
    
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;
    
    @NotNull(message = "La fecha y hora son obligatorias")
    @Future(message = "La fecha debe ser futura")
    private LocalDateTime fechaHora;
    
    @NotNull(message = "El tipo es obligatorio")
    private Recordatorio.TipoRecordatorio tipo;
    
    private Boolean esRecurrente = false;
    
    private Recordatorio.FrecuenciaRecordatorio frecuencia;
    
    private Recordatorio.EstadoRecordatorio estado = Recordatorio.EstadoRecordatorio.ACTIVO;
    
    @Min(value = 5, message = "Mínimo 5 minutos de anticipación")
    @Max(value = 1440, message = "Máximo 24 horas de anticipación")
    private Integer minutosAntes = 30;
    
    // Ubicación asociada (opcional)
    private Long lugarId;
    
    // Campos informativos (solo lectura)
    private String creadoPorNombre;
    private String lugarNombre;
    private String tipoDescripcion;
    private String estadoDescripcion;
    private String frecuenciaDescripcion;
}
