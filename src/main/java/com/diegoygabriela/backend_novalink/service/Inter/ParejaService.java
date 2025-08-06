package com.diegoygabriela.backend_novalink.service.Inter;

import com.diegoygabriela.backend_novalink.entity.Pareja;

import java.util.List;

public interface ParejaService {
    void insert(Pareja pareja);
    void delete(Long id);
    List<Pareja> list();
    Pareja listId(Long id);
}

