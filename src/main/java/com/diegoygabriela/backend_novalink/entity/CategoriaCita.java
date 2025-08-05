package com.diegoygabriela.backend_novalink.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
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

    // Citas asociadas a esta categoría
    @OneToMany(mappedBy = "categoriaCita")
    private List<Cita> citas;
}
