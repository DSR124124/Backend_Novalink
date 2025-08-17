package com.diegoygabriela.backend_novalink.service.Inter;

public interface CodigoRelacionService {
    
    /**
     * Genera un código de relación único para un usuario
     */
    String generarCodigoRelacion();
    
    /**
     * Valida si un código de relación existe y está disponible
     */
    boolean validarCodigoRelacion(String codigo);
    
    /**
     * Obtiene el usuario por código de relación
     */
    Integer obtenerUsuarioPorCodigo(String codigo);
    
    /**
     * Invalida un código de relación (cuando se forma la pareja)
     */
    void invalidarCodigoRelacion(String codigo);
}
