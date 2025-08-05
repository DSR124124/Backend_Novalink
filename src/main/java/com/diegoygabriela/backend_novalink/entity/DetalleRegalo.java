package com.diegoygabriela.backend_novalink.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "detalles_regalo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetalleRegalo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Usuario que dio el regalo
    @ManyToOne
    @JoinColumn(name = "remitente_id", nullable = false)
    private Usuario remitente;

    // Usuario que recibió el regalo
    @ManyToOne
    @JoinColumn(name = "receptor_id", nullable = false)
    private Usuario receptor;

    // Pareja a la que pertenece el regalo (consistencia de relación)
    @ManyToOne
    @JoinColumn(name = "pareja_id", nullable = false)
    private Pareja pareja;

    // Título o nombre del regalo
    @Column(length = 100, nullable = false)
    private String titulo;

    // Descripción o dedicatoria
    @Column(length = 500)
    private String descripcion;

    // Fecha en que se dio el regalo
    @Column(nullable = false)
    private LocalDateTime fecha;

    // Cita en la que se dio (opcional)
    @ManyToOne
    @JoinColumn(name = "cita_id")
    private Cita cita;

    // Evento en el que se dio (opcional)
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    // Foto o evidencia (opcional)
    @Column(length = 255)
    private String urlMultimedia;

    // Tipo de archivo si se usa urlMultimedia: FOTO, VIDEO, ETC
    @Column(length = 20)
    private String tipoMultimedia;
}
