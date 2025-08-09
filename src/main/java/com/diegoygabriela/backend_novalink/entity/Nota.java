package com.diegoygabriela.backend_novalink.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "notas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Nota implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Usuario que escribió la nota
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario autor;

    // Cita a la que pertenece la nota
    @ManyToOne
    @JoinColumn(name = "cita_id", nullable = false)
    private Cita cita;

    // Contenido de la nota
    @Column(length = 1000, nullable = false)
    private String contenido;

    // Fecha de creación de la nota
    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    // Privacidad de la nota
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPrivacidad privacidad = TipoPrivacidad.COMPARTIDA;

    public enum TipoPrivacidad {
        PRIVADA("Solo visible para el autor"),
        COMPARTIDA("Visible para ambos miembros de la pareja");

        private final String descripcion;

        TipoPrivacidad(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}
