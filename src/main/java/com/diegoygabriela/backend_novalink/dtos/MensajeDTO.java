package com.diegoygabriela.backend_novalink.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MensajeDTO {

    private Long id;

    // IDs relacionados (en lugar de objetos completos para simplificar el DTO)
    private Long parejaId;
    private Long remitenteId;

    private String contenido;
    private LocalDateTime fechaEnvio;
    private String estado;
}
