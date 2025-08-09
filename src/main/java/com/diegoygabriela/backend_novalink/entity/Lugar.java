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

    // Categoría del lugar para iconos en mapa
    @Enumerated(EnumType.STRING)
    private CategoriaLugar categoria;

    // Rating promedio del lugar (calculado)
    @Column
    private Double ratingPromedio = 0.0;

    // Número de veces visitado
    @Column
    private Integer vecesVisitado = 0;

    // Si es un lugar favorito de la pareja
    @Column
    private Boolean esFavorito = false;

    // Citas que ocurrieron en este lugar (bidireccional opcional)
    @OneToMany(mappedBy = "lugar")
    private List<Cita> citas;

    public enum CategoriaLugar {
        RESTAURANTE("🍽️", "Restaurante"),
        CAFE("☕", "Café"),
        PARQUE("🌳", "Parque"),
        CINE("🎬", "Cine"),
        MUSEO("🏛️", "Museo"),
        PLAYA("🏖️", "Playa"),
        MONTAÑA("⛰️", "Montaña"),
        HOGAR("🏠", "Hogar"),
        VIAJE("✈️", "Viaje"),
        EVENTO("🎉", "Evento"),
        OTRO("📍", "Otro");

        private final String icono;
        private final String nombre;

        CategoriaLugar(String icono, String nombre) {
            this.icono = icono;
            this.nombre = nombre;
        }

        public String getIcono() { return icono; }
        public String getNombre() { return nombre; }
    }
}
