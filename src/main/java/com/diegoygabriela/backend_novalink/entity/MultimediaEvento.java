package com.diegoygabriela.backend_novalink.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "multimedia_eventos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultimediaEvento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Evento al que pertenece este archivo multimedia
    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    // Usuario que lo subió
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario autor;

    // URL o ruta del archivo multimedia
    @Column(length = 255, nullable = false)
    private String url;

    // Tipo de archivo: "FOTO", "VIDEO", "AUDIO", etc.
    @Column(length = 20, nullable = false)
    private String tipo;

    // Descripción del archivo (opcional)
    @Column(length = 300)
    private String descripcion;

    // Fecha en que fue subido
    @Column(nullable = false)
    private LocalDateTime fechaSubida;
}
