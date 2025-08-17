package com.diegoygabriela.backend_novalink.dtos;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaParejaDTO {
    
    private boolean exito;
    private String mensaje;
    private ParejaDTO pareja;
    private LocalDate fechaOperacion;
    
    public static RespuestaParejaDTO exito(String mensaje, ParejaDTO pareja) {
        return RespuestaParejaDTO.builder()
                .exito(true)
                .mensaje(mensaje)
                .pareja(pareja)
                .fechaOperacion(LocalDate.now())
                .build();
    }
    
    public static RespuestaParejaDTO exito(String mensaje) {
        return RespuestaParejaDTO.builder()
                .exito(true)
                .mensaje(mensaje)
                .fechaOperacion(LocalDate.now())
                .build();
    }
    
    public static RespuestaParejaDTO error(String mensaje) {
        return RespuestaParejaDTO.builder()
                .exito(false)
                .mensaje(mensaje)
                .fechaOperacion(LocalDate.now())
                .build();
    }
}
