package com.diegoygabriela.backend_novalink.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MensajeErrorDTO {
    
    private String p_menserror;
    private String p_mensavis;
    private Boolean p_exito;
    private Object p_data;
} 
