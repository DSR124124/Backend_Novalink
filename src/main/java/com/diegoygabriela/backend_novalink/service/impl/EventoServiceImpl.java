package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.Evento;
import com.diegoygabriela.backend_novalink.repository.EventoRepository;
import com.diegoygabriela.backend_novalink.service.Inter.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public void delete(Long idEvento) {
        eventoRepository.deleteById(idEvento);
    }

    @Override
    public Evento listId(Long idEvento) {
        return eventoRepository.findById(idEvento).orElse(new Evento());
    }

    @Override
    public List<Evento> findByParejaId(Long parejaId) {
        return eventoRepository.findByParejaIdOrderByFechaDesc(parejaId);
    }

    @Override
    public List<Evento> findByTipo(String tipo) {
        return eventoRepository.findByTipoIgnoreCaseOrderByFechaDesc(tipo);
    }

    @Override
    public List<Evento> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin) {
        return eventoRepository.findByFechaBetweenOrderByFechaDesc(fechaInicio, fechaFin);
    }

    @Override
    public List<Evento> findEventosProximos() {
        return eventoRepository.findEventosProximos(LocalDate.now());
    }

    @Override
    public List<Evento> findEventosPasados() {
        return eventoRepository.findEventosPasados(LocalDate.now());
    }

    @Override
    public List<Evento> findByLugarId(Long lugarId) {
        return eventoRepository.findByLugarIdOrderByFechaDesc(lugarId);
    }

    @Override
    public List<Evento> findByParejaIdAndTipo(Long parejaId, String tipo) {
        return eventoRepository.findByParejaIdAndTipoIgnoreCaseOrderByFechaDesc(parejaId, tipo);
    }

    @Override
    public List<Evento> findEventosByYear(int year) {
        return eventoRepository.findEventosByYear(year);
    }

    @Override
    public Long countByParejaId(Long parejaId) {
        return eventoRepository.countByParejaId(parejaId);
    }

    @Override
    public List<Evento> findEventosProximosPorPareja(Long parejaId, int dias) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaLimite = fechaActual.plusDays(dias);
        return eventoRepository.findEventosProximosPorPareja(parejaId, fechaActual, fechaLimite);
    }
}
