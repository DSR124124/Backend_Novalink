package com.diegoygabriela.backend_novalink.controller;

import com.diegoygabriela.backend_novalink.dtos.MensajeErrorDTO;
import com.diegoygabriela.backend_novalink.dtos.UnirCodigosDTO;
import com.diegoygabriela.backend_novalink.entity.Pareja;
import com.diegoygabriela.backend_novalink.entity.Usuario;
import com.diegoygabriela.backend_novalink.service.Inter.ParejaService;
import com.diegoygabriela.backend_novalink.service.Inter.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/parejas")
public class ParejaController {

    @Autowired
    private ParejaService parejaService;
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/unir-codigos")
    public MensajeErrorDTO unirCodigos(@RequestBody UnirCodigosDTO unirCodigosDTO) {
        try {
            String codigo1 = unirCodigosDTO.getCodigo1();
            String codigo2 = unirCodigosDTO.getCodigo2();
            
            Usuario usuario1 = usuarioService.findByCodigoRelacion(codigo1);
            Usuario usuario2 = usuarioService.findByCodigoRelacion(codigo2);
            
            if (usuario1 == null || usuario2 == null) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Uno o ambos códigos no son válidos")
                    .p_mensavis("Verifica que los códigos sean correctos")
                    .p_exito(false)
                    .p_data(createDataMap("error", "INVALID_CODES"))
                    .build();
            }
            
            if (usuario1.getIdUsuario().equals(usuario2.getIdUsuario())) {
                return MensajeErrorDTO.builder()
                    .p_menserror("No puedes formar pareja contigo mismo")
                    .p_mensavis("Los códigos deben ser de usuarios diferentes")
                    .p_exito(false)
                    .p_data(createDataMap("error", "SELF_PAIRING"))
                    .build();
            }
            
            // Verificar si ya tienen pareja
            if (parejaService.buscarPorUsuario(usuario1.getIdUsuario()).isPresent()) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Usuario 1 ya tiene una pareja")
                    .p_mensavis("Este usuario ya está en una relación")
                    .p_exito(false)
                    .p_data(createDataMap("error", "USER1_ALREADY_PAIRED"))
                    .build();
            }
            
            if (parejaService.buscarPorUsuario(usuario2.getIdUsuario()).isPresent()) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Usuario 2 ya tiene una pareja")
                    .p_mensavis("Este usuario ya está en una relación")
                    .p_exito(false)
                    .p_data(createDataMap("error", "USER2_ALREADY_PAIRED"))
                    .build();
            }
            
            // Crear la pareja
            Pareja pareja = parejaService.crearPareja(usuario1.getIdUsuario(), usuario2.getIdUsuario());
            
            // Actualizar disponibilidad
            usuario1.setDisponibleParaPareja(false);
            usuario2.setDisponibleParaPareja(false);
            usuarioService.update(usuario1);
            usuarioService.update(usuario2);
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Pareja formada exitosamente")
                .p_exito(true)
                .p_data(createDataMap(
                    "pareja", createDataMap("id", pareja.getId()),
                    "usuario1", createDataMap("id", usuario1.getIdUsuario(), "username", usuario1.getUsername()),
                    "usuario2", createDataMap("id", usuario2.getIdUsuario(), "username", usuario2.getUsername())
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al formar pareja: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(createDataMap("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/puede-crear-pareja/{idUsuario}")
    public MensajeErrorDTO puedeCrearPareja(@PathVariable Long idUsuario) {
        try {
            Usuario usuario = usuarioService.listId(idUsuario);
            if (usuario == null) {
                return MensajeErrorDTO.builder()
                    .p_menserror("Usuario no encontrado")
                    .p_mensavis("Verifica que el ID sea correcto")
                    .p_exito(false)
                    .p_data(createDataMap("error", "USER_NOT_FOUND"))
                    .build();
            }
            
            boolean tienePareja = parejaService.buscarPorUsuario(idUsuario).isPresent();
            boolean disponible = !tienePareja; // Si no tiene pareja, está disponible
            
            // Sincronizar si hay inconsistencia
            if (usuario.getDisponibleParaPareja() != disponible) {
                usuario.setDisponibleParaPareja(disponible);
                usuarioService.update(usuario);
            }
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Estado verificado")
                .p_exito(true)
                .p_data(createDataMap(
                    "usuario", createDataMap("id", usuario.getIdUsuario(), "username", usuario.getUsername()),
                    "disponibleParaPareja", disponible,
                    "codigoRelacion", usuario.getCodigoRelacion()
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al verificar disponibilidad: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(createDataMap("error", e.getMessage()))
                .build();
        }
    }

    @GetMapping("/info-relacion/{idUsuario}")
    public MensajeErrorDTO obtenerInfoRelacion(@PathVariable Long idUsuario) {
        try {
            Optional<Pareja> parejaOpt = parejaService.buscarPorUsuario(idUsuario);
            
            if (!parejaOpt.isPresent()) {
                return MensajeErrorDTO.builder()
                    .p_menserror("No se encontró relación")
                    .p_mensavis("Este usuario no tiene pareja")
                    .p_exito(false)
                    .p_data(createDataMap("error", "NO_RELATIONSHIP"))
                    .build();
            }
            
            Pareja pareja = parejaOpt.get();
            Usuario usuario1 = usuarioService.listId(pareja.getUsuario1().getIdUsuario());
            Usuario usuario2 = usuarioService.listId(pareja.getUsuario2().getIdUsuario());
            
            return MensajeErrorDTO.builder()
                .p_menserror(null)
                .p_mensavis("Información de relación obtenida")
                .p_exito(true)
                .p_data(createDataMap(
                    "pareja", createDataMap("id", pareja.getId()),
                    "usuario1", createDataMap("id", usuario1.getIdUsuario(), "username", usuario1.getUsername()),
                    "usuario2", createDataMap("id", usuario2.getIdUsuario(), "username", usuario2.getUsername())
                ))
                .build();
                
        } catch (Exception e) {
            return MensajeErrorDTO.builder()
                .p_menserror("Error al obtener información: " + e.getMessage())
                .p_mensavis("Intenta nuevamente más tarde")
                .p_exito(false)
                .p_data(createDataMap("error", e.getMessage()))
                .build();
        }
    }
    
    // Método helper simplificado
    private Map<String, Object> createDataMap(Object... keyValuePairs) {
        Map<String, Object> data = new HashMap<>();
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            if (i + 1 < keyValuePairs.length) {
                data.put(keyValuePairs[i].toString(), keyValuePairs[i + 1]);
            }
        }
        return data;
    }
}
