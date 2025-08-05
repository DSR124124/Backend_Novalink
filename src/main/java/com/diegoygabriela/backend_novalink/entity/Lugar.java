package com.diegoygabriela.backend_novalink.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "lugares")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lugar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre del lugar (ej: "Parque Kennedy", "Café Central")
    @Column(length = 100, nullable = false)
    private String nombre;

    // Dirección detallada u opcional
    @Column(length = 200)
    private String direccion;

    // Coordenadas para mostrar en mapa
    @Column(nullable = false)
    private Double latitud;

    @Column(nullable = false)
    private Double longitud;

    // Descripción opcional
    @Column(length = 500)
    private String descripcion;

    // Citas que ocurrieron en este lugar (bidireccional opcional)
    @OneToMany(mappedBy = "lugar")
    private List<Cita> citas;
}
