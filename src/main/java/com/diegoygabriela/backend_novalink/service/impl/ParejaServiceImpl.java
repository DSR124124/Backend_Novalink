package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.Pareja;
import com.diegoygabriela.backend_novalink.entity.Usuario;
import com.diegoygabriela.backend_novalink.repository.ParejaRepository;
import com.diegoygabriela.backend_novalink.repository.UsuarioRepository;
import com.diegoygabriela.backend_novalink.service.Inter.ParejaService;
import com.diegoygabriela.backend_novalink.service.Inter.CodigoRelacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ParejaServiceImpl implements ParejaService {

    @Autowired
    private ParejaRepository parejaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private CodigoRelacionService codigoRelacionService;

    @Override
    public Optional<Pareja> buscarPorUsuario(Long usuarioId) {
        return parejaRepository.findByUsuario1IdOrUsuario2Id(usuarioId);
    }

    @Override
    public Pareja cambiarEstadoRelacion(Long parejaId, String nuevoEstado) {
        Optional<Pareja> parejaOpt = parejaRepository.findById(parejaId);
        if (parejaOpt.isEmpty()) {
            throw new RuntimeException("La pareja no existe");
        }
        
        Pareja pareja = parejaOpt.get();
        pareja.setEstadoRelacion(nuevoEstado);
        return parejaRepository.save(pareja);
    }

    @Override
    public boolean puedeCrearPareja(Long usuarioId) {
        Optional<Pareja> parejaExistente = buscarPorUsuario(usuarioId);
        return parejaExistente.isEmpty() || 
               !"activa".equals(parejaExistente.get().getEstadoRelacion());
    }
    
    @Override
    public Pareja crearParejaConCodigo(Integer usuarioId, String codigoRelacion, String estadoRelacion) {
        // Validar que el código de relación sea válido
        if (!validarCodigoRelacion(codigoRelacion)) {
            throw new RuntimeException("Código de relación inválido o no disponible");
        }
        
        // Obtener el usuario que se quiere unir
        Optional<Usuario> usuario1Opt = usuarioRepository.findById(usuarioId);
        if (usuario1Opt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        Usuario usuario1 = usuario1Opt.get();
        
        // Obtener el usuario por código de relación
        Usuario usuario2 = obtenerUsuarioPorCodigo(codigoRelacion);
        if (usuario2 == null) {
            throw new RuntimeException("Usuario con código de relación no encontrado");
        }
        
        // Validar que no sea el mismo usuario
        if (usuario1.getIdUsuario().equals(usuario2.getIdUsuario())) {
            throw new RuntimeException("No puedes formar pareja contigo mismo");
        }
        
        // Validar que ambos usuarios puedan crear pareja
        if (!puedeCrearPareja(usuario1.getIdUsuario().longValue()) ||
            !puedeCrearPareja(usuario2.getIdUsuario().longValue())) {
            throw new RuntimeException("Uno o ambos usuarios ya tienen una pareja activa");
        }
        
        // Crear la pareja
        Pareja pareja = new Pareja();
        pareja.setUsuario1(usuario1);
        pareja.setUsuario2(usuario2);
        pareja.setFechaCreacion(LocalDate.now());
        pareja.setEstadoRelacion(estadoRelacion != null ? estadoRelacion : "activa");
        
        // Invalidar el código de relación usado
        codigoRelacionService.invalidarCodigoRelacion(codigoRelacion);
        
        // Marcar ambos usuarios como no disponibles para formar pareja
        usuario1.setDisponibleParaPareja(false);
        usuario2.setDisponibleParaPareja(false);
        usuarioRepository.save(usuario1);
        usuarioRepository.save(usuario2);
        
        return parejaRepository.save(pareja);
    }

    @Override
    public boolean validarCodigoRelacion(String codigo) {
        return codigoRelacionService.validarCodigoRelacion(codigo);
    }

    @Override
    public Usuario obtenerUsuarioPorCodigo(String codigo) {
        Integer usuarioId = codigoRelacionService.obtenerUsuarioPorCodigo(codigo);
        if (usuarioId == null) {
            return null;
        }
        return usuarioRepository.findById(usuarioId).orElse(null);
    }
}

