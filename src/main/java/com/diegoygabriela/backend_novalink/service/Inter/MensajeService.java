package com.diegoygabriela.backend_novalink.service.Inter;

import com.diegoygabriela.backend_novalink.entity.Mensaje;

import java.util.List;

public interface MensajeService {
    void insert(Mensaje mensaje);
    List<Mensaje> list();
    void delete(Long id);
    Mensaje listId(Long id);

}
