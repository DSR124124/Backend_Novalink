package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.UsuarioDTO;
import com.diegoygabriela.backend_novalink.securities.JwtRequest;
import com.diegoygabriela.backend_novalink.securities.JwtTokenUtil;
import com.diegoygabriela.backend_novalink.dtos.MensajeErrorDTO;
import com.diegoygabriela.backend_novalink.entity.Usuario;
import com.diegoygabriela.backend_novalink.service.Inter.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/login")
    public MensajeErrorDTO createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            Usuario usuario = usuarioService.findByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails, usuario.getIdUsuario());
            
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("usuario", modelMapper.map(usuario, UsuarioDTO.class));
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Autenticación exitosa")
                .p_exito(true)
                .p_data(data)
                .build();
                
        } catch (Exception e) {
            Map<String, Object> errorData = new HashMap<>();
            errorData.put("error", e.getMessage());
            
            return MensajeErrorDTO.builder()
                .p_menserror("Error de autenticación: " + e.getMessage())
                .p_mensavis("Verifica tus credenciales e intenta nuevamente")
                .p_exito(false)
                .p_data(errorData)
                .build();
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("Usuario deshabilitado", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciales inválidas", e);
        }
    }
}
