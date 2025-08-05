package com.diegoygabriela.backend_novalink.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    private List<Rol> roles;

    // Relación con Pareja como usuario1
    @OneToOne(mappedBy = "usuario1")
    private Pareja parejaComoUsuario1;

    // Relación con Pareja como usuario2
    @OneToOne(mappedBy = "usuario2")
    private Pareja parejaComoUsuario2;

    // Método útil para obtener la pareja sin importar la posición
    public Pareja getParejaActual() {
        return parejaComoUsuario1 != null ? parejaComoUsuario1 : parejaComoUsuario2;
    }
}
