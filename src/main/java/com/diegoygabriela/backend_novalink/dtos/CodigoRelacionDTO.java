package com.diegoygabriela.backend_novalink.dtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodigoRelacionDTO {
    
    private Integer usuarioId;
    private String nombreCompleto;
    private String codigoRelacion;
    private Boolean disponibleParaPareja;
    private String mensaje;
    
    // Constructor para respuestas exitosas
    public static CodigoRelacionDTO exito(Integer usuarioId, String nombreCompleto, String codigoRelacion) {
        return CodigoRelacionDTO.builder()
                .usuarioId(usuarioId)
                .nombreCompleto(nombreCompleto)
                .codigoRelacion(codigoRelacion)
                .disponibleParaPareja(true)
                .mensaje("Código de relación generado exitosamente")
                .build();
    }
    
    // Constructor para respuestas de error
    public static CodigoRelacionDTO error(String mensaje) {
        return CodigoRelacionDTO.builder()
                .mensaje(mensaje)
                .disponibleParaPareja(false)
                .build();
    }
}
