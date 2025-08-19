package com.diegoygabriela.backend_novalink.service.Inter;

import com.diegoygabriela.backend_novalink.entity.Pareja;
import java.util.List;
import java.util.Optional;

public interface ParejaService {
    
    // ===== GESTIÓN BÁSICA DE PAREJAS =====
    
    // Crear nueva pareja
    Pareja crearPareja(Long usuario1Id, Long usuario2Id);
    
    // Buscar pareja por usuario
    Optional<Pareja> buscarPorUsuario(Long usuarioId);
    
    // Listar todas las parejas
    List<Pareja> listarTodas();
    
    // Obtener pareja por ID
    Optional<Pareja> buscarPorId(Long parejaId);
    
    // Eliminar pareja
    void eliminarPareja(Long parejaId);
}

