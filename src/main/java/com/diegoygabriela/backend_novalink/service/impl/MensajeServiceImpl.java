package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.Mensaje;
import com.diegoygabriela.backend_novalink.repository.MensajeRepository;
import com.diegoygabriela.backend_novalink.service.Inter.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajeServiceImpl implements MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Override
    public void insert(Mensaje mensaje) {
        mensajeRepository.save(mensaje);
    }

    @Override
    public List<Mensaje> list() {
        return mensajeRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        mensajeRepository.deleteById(id);
    }

    @Override
    public Mensaje listId(Long id) {
        return mensajeRepository.findById(id).orElse(new Mensaje());
    }

}
