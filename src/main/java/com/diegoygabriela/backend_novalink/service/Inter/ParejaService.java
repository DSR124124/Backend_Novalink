package com.diegoygabriela.backend_novalink.service.Inter;

import com.diegoygabriela.backend_novalink.entity.Pareja;
import com.diegoygabriela.backend_novalink.entity.Usuario;

import java.util.Optional;

public interface ParejaService {
    
    // Operaciones esenciales para relaciones
    Optional<Pareja> buscarPorUsuario(Long usuarioId);
    Pareja cambiarEstadoRelacion(Long parejaId, String nuevoEstado);
    
    // Operaciones de validación
    boolean puedeCrearPareja(Long usuarioId);
    
    // Métodos para códigos de relación
    Pareja crearParejaConCodigo(Integer usuarioId, String codigoRelacion, String estadoRelacion);
    boolean validarCodigoRelacion(String codigo);
    Usuario obtenerUsuarioPorCodigo(String codigo);
}

