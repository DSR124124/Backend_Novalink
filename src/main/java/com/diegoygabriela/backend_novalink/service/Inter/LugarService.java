package com.diegoygabriela.backend_novalink.service.Inter;

import com.diegoygabriela.backend_novalink.entity.Lugar;

import java.util.List;

public interface LugarService {
    void insert(Lugar lugar);
    List<Lugar> list();
    void delete(Long idLugar);
    Lugar listId(Long idLugar);
}

