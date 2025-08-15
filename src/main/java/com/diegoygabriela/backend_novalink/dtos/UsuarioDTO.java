package com.diegoygabriela.backend_novalink.dtos;

import com.diegoygabriela.backend_novalink.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Integer idUsuario;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
    private String nombre;
    
    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50, message = "El apellido no puede exceder 50 caracteres")
    private String apellido;
    
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del correo no es válido")
    @Size(max = 100, message = "El correo no puede exceder 100 caracteres")
    private String correo;
    
    @NotBlank(message = "El username es obligatorio")
    @Size(max = 50, message = "El username no puede exceder 50 caracteres")
    private String username;
    
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 200, message = "La contraseña debe tener entre 6 y 200 caracteres")
    private String password;
    
    @NotNull(message = "El estado enabled es obligatorio")
    private Boolean enabled;
    
    @Size(max = 255, message = "La foto de perfil no puede exceder 255 caracteres")
    private String fotoPerfil;
    
    @Past(message = "La fecha de nacimiento debe ser anterior a hoy")
    private LocalDate fechaNacimiento;
    
    @Pattern(regexp = "^(M|F|O)$", message = "El género debe ser M (Masculino), F (Femenino) u O (Otro)")
    private String genero;
    
    @NotNull(message = "El rol es obligatorio")
    private Integer roleId; // Solo el ID del role, no el objeto completo

}

