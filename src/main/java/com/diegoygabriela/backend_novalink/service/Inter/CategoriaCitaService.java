package com.diegoygabriela.backend_novalink.service.Inter;

import com.diegoygabriela.backend_novalink.entity.CategoriaCita;

import java.util.List;

public interface CategoriaCitaService {
    // Operaciones CRUD básicas
    CategoriaCita insert(CategoriaCita categoria);
    List<CategoriaCita> list();
    void delete(Long idCategoria);
    CategoriaCita listId(Long idCategoria);
    CategoriaCita update(CategoriaCita categoria);
    
    // Validación de integridad para eliminación
    boolean canDelete(Long idCategoria);
    
    // Contador de citas por categoría
    long countCitasByCategoria(Long idCategoria);
}
