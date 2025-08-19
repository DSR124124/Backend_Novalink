package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.Nota;
import com.diegoygabriela.backend_novalink.repository.NotaRepository;
import com.diegoygabriela.backend_novalink.service.Inter.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaServiceImpl implements NotaService {
    @Autowired
    private NotaRepository notaRepository;

    @Override
    public void insert(Nota nota) {
        notaRepository.save(nota);
    }

    @Override
    public List<Nota> list() {
        return notaRepository.findAll();
    }

    @Override
    public void delete(Long idNota) {
        notaRepository.deleteById(idNota);
    }

    @Override
    public Nota listId(Long idNota) {
        return notaRepository.findById(idNota).orElse(new Nota());
    }
}
