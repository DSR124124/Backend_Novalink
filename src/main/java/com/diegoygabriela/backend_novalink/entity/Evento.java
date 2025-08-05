package com.diegoygabriela.backend_novalink.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "eventos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Evento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Pareja que creó el evento
    @ManyToOne
    @JoinColumn(name = "pareja_id", nullable = false)
    private Pareja pareja;

    // Título del evento
    @Column(length = 100, nullable = false)
    private String titulo;

    // Descripción del evento
    @Column(length = 1000)
    private String descripcion;

    // Fecha del evento
    @Column(nullable = false)
    private LocalDate fecha;

    // Tipo o categoría del evento: "Aniversario", "Cumpleaños", "Viaje", etc.
    @Column(length = 50)
    private String tipo;

    // Recuerdos asociados al evento (opcional)
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MultimediaEvento> recuerdos;

    // Lugar asociado al evento (opcional)
    @ManyToOne
    @JoinColumn(name = "lugar_id")
    private Lugar lugar;
}
