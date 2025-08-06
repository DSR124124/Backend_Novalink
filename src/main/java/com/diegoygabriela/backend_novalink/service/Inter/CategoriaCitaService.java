package com.diegoygabriela.backend_novalink.service.Inter;

import com.diegoygabriela.backend_novalink.entity.CategoriaCita;

import java.util.List;

public interface CategoriaCitaService {
    void insert(CategoriaCita categoria);
    List<CategoriaCita> list();
    void delete(Long idCategoria);
    CategoriaCita listId(Long idCategoria);
}
