package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.Rol;
import com.diegoygabriela.backend_novalink.repository.RolRepository;
import com.diegoygabriela.backend_novalink.service.Inter.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public void insert(Rol rol) {
        rolRepository.save(rol);
    }

    @Override
    public List<Rol> list() {
        return rolRepository.findAll();
    }

    @Override
    public void delete(Long idRol) {
        rolRepository.deleteById(idRol);
    }

    @Override
    public Rol listId(Long idRol) {
        return rolRepository.findById(idRol).orElse(new Rol());
    }
}
