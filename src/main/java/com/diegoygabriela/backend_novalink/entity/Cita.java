package com.diegoygabriela.backend_novalink.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "citas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cita implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Pareja a la que pertenece la cita
    @ManyToOne
    @JoinColumn(name = "pareja_id", nullable = false)
    private Pareja pareja;

    // Lugar donde se llevó a cabo
    @ManyToOne
    @JoinColumn(name = "lugar_id", nullable = false)
    private Lugar lugar;

    // Categoría o tipo de la cita (ej: Cena, Viaje, Película)
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaCita categoriaCita;

    // Título de la cita
    @Column(length = 100, nullable = false)
    private String titulo;

    // Descripción opcional
    @Column(length = 1000)
    private String descripcion;

    // Fecha y hora de la cita
    @Column(nullable = false)
    private LocalDateTime fecha;

    // Recuerdos multimedia (fotos, videos, etc.)
    @OneToMany(mappedBy = "cita", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Multimedia> recuerdos;

    // Notas personales asociadas a la cita
    @OneToMany(mappedBy = "cita", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Nota> notas;
}
