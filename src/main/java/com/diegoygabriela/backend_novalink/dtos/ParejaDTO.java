package com.diegoygabriela.backend_novalink.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ParejaDTO {
    private Long id;
    private Long usuario1Id;
    private Long usuario2Id;
    private LocalDate fechaCreacion;
    private String estadoRelacion;
}
