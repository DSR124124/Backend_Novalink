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
    public void delete(int idNota) {
        notaRepository.deleteById((long) idNota);
    }

    @Override
    public Nota listId(int idNota) {
        return notaRepository.findById((long) idNota).orElse(new Nota());
    }
}