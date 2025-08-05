package com.diegoygabriela.backend_novalink.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "mensajes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mensaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Pareja a la que pertenece el chat
    @ManyToOne
    @JoinColumn(name = "pareja_id", nullable = false)
    private Pareja pareja;

    // Remitente del mensaje (uno de los dos usuarios de la pareja)
    @ManyToOne
    @JoinColumn(name = "remitente_id", nullable = false)
    private Usuario remitente;

    // Contenido del mensaje
    @Column(length = 1000, nullable = false)
    private String contenido;

    // Fecha y hora de envío
    @Column(nullable = false)
    private LocalDateTime fechaEnvio;

    // Estado del mensaje (opcional): leído, no leído, eliminado, etc.
    @Column(length = 20)
    private String estado;
}
