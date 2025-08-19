package com.diegoygabriela.backend_novalink.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "parejas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pareja implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con el primer usuario de la pareja
    @OneToOne
    @JoinColumn(name = "usuario1_id", nullable = false)
    private Usuario usuario1;

    // Relación con el segundo usuario de la pareja
    @OneToOne
    @JoinColumn(name = "usuario2_id", nullable = false)
    private Usuario usuario2;

    // Fecha en la que se creó la relación
    @Column(nullable = false)
    private LocalDate fechaCreacion;

    // Estado de la relación (opcional)
    @Column(length = 20)
    private String estadoRelacion; // Ej: "activa", "pausada", "terminada"

    // Método auxiliar para verificar si un usuario pertenece a esta pareja
    public boolean contieneUsuario(Usuario usuario) {
        return usuario != null && (usuario.equals(usuario1) || usuario.equals(usuario2));
    }
}

