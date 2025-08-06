package com.diegoygabriela.backend_novalink.service.impl;
import com.diegoygabriela.backend_novalink.entity.Lugar;
import com.diegoygabriela.backend_novalink.repository.LugarRepository;
import com.diegoygabriela.backend_novalink.service.Inter.LugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LugarServiceImpl implements LugarService {

    @Autowired
    private LugarRepository lugarRepository;

    @Override
    public void insert(Lugar lugar) {
        lugarRepository.save(lugar);
    }

    @Override
    public List<Lugar> list() {
        return lugarRepository.findAll();
    }

    @Override
    public void delete(Long idLugar) {
        lugarRepository.deleteById(idLugar);
    }

    @Override
    public Lugar listId(Long idLugar) {
        return lugarRepository.findById(idLugar).orElse(new Lugar());
    }
}
