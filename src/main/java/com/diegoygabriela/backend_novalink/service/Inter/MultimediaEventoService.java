package com.diegoygabriela.backend_novalink.service.Inter;

import com.diegoygabriela.backend_novalink.entity.MultimediaEvento;

import java.util.List;

public interface MultimediaEventoService {
    void insert(MultimediaEvento multimediaEvento);
    List<MultimediaEvento> list();
    void delete(Long id);
    MultimediaEvento listId(Long id);
}
