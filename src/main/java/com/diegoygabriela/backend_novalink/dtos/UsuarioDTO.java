package com.diegoygabriela.backend_novalink.dtos;

import com.diegoygabriela.backend_novalink.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String correo;
    private String username;
    private String password;
    private Boolean enabled;
    private String fotoPerfil;
    private LocalDate fechaNacimiento;
    private String genero;
    private Role role;

}

