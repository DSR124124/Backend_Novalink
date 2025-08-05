package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.Evento;
import com.diegoygabriela.backend_novalink.repository.EventoRepository;
import com.diegoygabriela.backend_novalink.service.Inter.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  EventoServiceImpl implements EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public void insert(Evento evento) {
        eventoRepository.save(evento);
    }

    @Override
    public List<Evento> list() {
        return eventoRepository.findAll();
    }

    @Override
    public void delete(int idEvento) {
        eventoRepository.deleteById((long) idEvento);
    }

    @Override
    public Evento listId(int idEvento) {
        return eventoRepository.findById((long) idEvento).orElse(new Evento());
    }
}