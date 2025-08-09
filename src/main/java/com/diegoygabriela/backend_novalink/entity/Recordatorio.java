package com.diegoygabriela.backend_novalink.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "recordatorios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recordatorio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Pareja a la que pertenece el recordatorio
    @ManyToOne
    @JoinColumn(name = "pareja_id", nullable = false)
    private Pareja pareja;

    // Usuario que creó el recordatorio
    @ManyToOne
    @JoinColumn(name = "creado_por", nullable = false)
    private Usuario creadoPor;

    // Título del recordatorio
    @Column(length = 100, nullable = false)
    private String titulo;

    // Descripción del recordatorio
    @Column(length = 500)
    private String descripcion;

    // Fecha y hora del recordatorio
    @Column(nullable = false)
    private LocalDateTime fechaHora;

    // Tipo de recordatorio
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoRecordatorio tipo;

    // Si es recurrente (aniversarios, etc.)
    @Column(nullable = false)
    private Boolean esRecurrente = false;

    // Frecuencia de recurrencia (ANUAL, MENSUAL, SEMANAL)
    @Enumerated(EnumType.STRING)
    private FrecuenciaRecordatorio frecuencia;

    // Estado del recordatorio
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoRecordatorio estado = EstadoRecordatorio.ACTIVO;

    // Minutos antes para notificar (15, 30, 60, etc.)
    @Column
    private Integer minutosAntes = 30;

    // Ubicación asociada (opcional)
    @ManyToOne
    @JoinColumn(name = "lugar_id")
    private Lugar lugar;

    public enum TipoRecordatorio {
        ANIVERSARIO,
        CUMPLEANOS,
        CITA_PLANIFICADA,
        EVENTO_ESPECIAL,
        RECORDATORIO_PERSONAL
    }

    public enum FrecuenciaRecordatorio {
        ANUAL,
        MENSUAL,
        SEMANAL,
        DIARIA
    }

    public enum EstadoRecordatorio {
        ACTIVO,
        COMPLETADO,
        CANCELADO,
        PAUSADO
    }
}
