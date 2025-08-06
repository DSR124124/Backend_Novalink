package com.diegoygabriela.backend_novalink.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MultimediaEventoDTO {
    private Long id;
    private Long eventoId;
    private Long autorId;
    private String url;
    private String tipo;
    private String descripcion;
    private LocalDateTime fechaSubida;
}
