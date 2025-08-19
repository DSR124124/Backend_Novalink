package com.diegoygabriela.backend_novalink.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodigoRelacionDTO {
    
    private Long idUsuario;
    private String codigoRelacion;
    private String mensaje;
    private Boolean exito;
}
