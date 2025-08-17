package com.diegoygabriela.backend_novalink.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 50, nullable = false)
    private String apellido;

    @Column(length = 100, nullable = false, unique = true)
    private String correo;

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(length = 200, nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(length = 255)
    private String fotoPerfil;

    private LocalDate fechaNacimiento;

    private String genero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    
    // Código único para formar parejas (generado automáticamente)
    @Column(length = 10, unique = true)
    private String codigoRelacion;
    
    // Estado de disponibilidad para formar pareja
    @Column(nullable = false)
    private Boolean disponibleParaPareja = true;
} 
