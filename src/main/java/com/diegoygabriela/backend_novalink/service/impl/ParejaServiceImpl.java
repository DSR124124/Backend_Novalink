package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.Pareja;
import com.diegoygabriela.backend_novalink.entity.Usuario;
import com.diegoygabriela.backend_novalink.repository.ParejaRepository;
import com.diegoygabriela.backend_novalink.repository.UsuarioRepository;
import com.diegoygabriela.backend_novalink.service.Inter.ParejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ParejaServiceImpl implements ParejaService {

    @Autowired
    private ParejaRepository parejaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Pareja crearPareja(Integer usuario1Id, Integer usuario2Id) {
        // Validar que ambos usuarios existan
        Usuario usuario1 = usuarioRepository.findById(usuario1Id)
                .orElseThrow(() -> new RuntimeException("Usuario 1 no encontrado"));
        
        Usuario usuario2 = usuarioRepository.findById(usuario2Id)
                .orElseThrow(() -> new RuntimeException("Usuario 2 no encontrado"));
        
        // Validar que no sea el mismo usuario
        if (usuario1Id.equals(usuario2Id)) {
            throw new RuntimeException("No puedes formar pareja contigo mismo");
        }
        
        // Verificar que no tengan pareja existente
        if (parejaRepository.existsByUsuarioId(usuario1Id)) {
            throw new RuntimeException("Usuario 1 ya tiene una pareja");
        }
        
        if (parejaRepository.existsByUsuarioId(usuario2Id)) {
            throw new RuntimeException("Usuario 2 ya tiene una pareja");
        }
        
        // Crear la pareja
        Pareja pareja = new Pareja();
        pareja.setUsuario1(usuario1);
        pareja.setUsuario2(usuario2);
        pareja.setFechaCreacion(LocalDate.now());
        pareja.setEstadoRelacion("activa");
        
        return parejaRepository.save(pareja);
    }

    @Override
    public Optional<Pareja> buscarPorUsuario(Integer usuarioId) {
        return parejaRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Pareja> listarTodas() {
        return parejaRepository.findAll();
    }

    @Override
    public Optional<Pareja> buscarPorId(Integer parejaId) {
        return parejaRepository.findById(parejaId);
    }

    @Override
    public void eliminarPareja(Integer parejaId) {
        parejaRepository.deleteById(parejaId);
    }
}

