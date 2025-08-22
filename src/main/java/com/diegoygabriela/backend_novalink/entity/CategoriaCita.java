package com.diegoygabriela.backend_novalink.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "categorias_cita")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaCita implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre visible para el usuario
    @Column(length = 50, nullable = false, unique = true)
    private String nombre;

    // Descripción opcional
    @Column(length = 200)
    private String descripcion;

    // Color para la interfaz (hexadecimal)
    @Column(length = 7, nullable = false)
    @Builder.Default
    private String color = "#007bff";

    // Icono para la interfaz
    @Column(length = 50)
    private String icono;

    // Estado activo/inactivo
    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;

    // Orden de visualización
    @Column(nullable = false)
    @Builder.Default
    private Integer orden = 0;

    // Auditoría
    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column
    private LocalDateTime fechaModificacion;

    // Citas asociadas a esta categoría
    @OneToMany(mappedBy = "categoriaCita")
    @ToString.Exclude
    private List<Cita> citas;

    // Método para actualizar fecha de modificación
    @PreUpdate
    public void preUpdate() {
        this.fechaModificacion = LocalDateTime.now();
    }
}
