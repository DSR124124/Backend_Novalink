package com.diegoygabriela.backend_novalink.service.impl;

import com.diegoygabriela.backend_novalink.entity.Cita;
import com.diegoygabriela.backend_novalink.entity.EstadoCita;
import com.diegoygabriela.backend_novalink.repository.CitaRepository;
import com.diegoygabriela.backend_novalink.service.Inter.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {
    
    @Autowired
    private CitaRepository citaRepository;

    @Override
    public void insert(Cita cita) {
        citaRepository.save(cita);
    }

    @Override
    public List<Cita> list() {
        return citaRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        citaRepository.deleteById(id);
    }

    @Override
    public Cita listId(Long id) {
        return citaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Cita> findByParejaId(Long parejaId) {
        return citaRepository.findByParejaId(parejaId);
    }

    @Override
    public List<Cita> findByEstado(EstadoCita estado) {
        return citaRepository.findByEstado(estado);
    }

    @Override
    public List<Cita> findByParejaIdAndEstado(Long parejaId, EstadoCita estado) {
        return citaRepository.findByParejaIdAndEstado(parejaId, estado);
    }

    @Override
    public List<Cita> findByLugarId(Long lugarId) {
        return citaRepository.findByLugarId(lugarId);
    }

    @Override
    public List<Cita> findByCategoriaCitaId(Long categoriaId) {
        return citaRepository.findByCategoriaCitaId(categoriaId);
    }

    @Override
    public List<Cita> findCitasFuturasByPareja(Long parejaId) {
        return citaRepository.findCitasFuturasByPareja(parejaId, LocalDateTime.now());
    }

    @Override
    public List<Cita> findCitasPasadasByPareja(Long parejaId) {
        return citaRepository.findCitasPasadasByPareja(parejaId, LocalDateTime.now());
    }

    @Override
    public List<Cita> findByParejaIdAndFechaRange(Long parejaId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return citaRepository.findByParejaIdAndFechaRange(parejaId, fechaInicio, fechaFin);
    }

    @Override
    public Long countByParejaIdAndEstado(Long parejaId, EstadoCita estado) {
        return citaRepository.countByParejaIdAndEstado(parejaId, estado);
    }

    @Override
    public List<Cita> findMejorCalificadasByPareja(Long parejaId) {
        return citaRepository.findMejorCalificadasByPareja(parejaId);
    }

    @Override
    public void marcarComoCompletada(Long citaId) {
        Optional<Cita> citaOpt = citaRepository.findById(citaId);
        if (citaOpt.isPresent()) {
            Cita cita = citaOpt.get();
            cita.setEstado(EstadoCita.COMPLETADA);
            citaRepository.save(cita);
        }
    }

    @Override
    public void marcarComoCancelada(Long citaId) {
        Optional<Cita> citaOpt = citaRepository.findById(citaId);
        if (citaOpt.isPresent()) {
            Cita cita = citaOpt.get();
            cita.setEstado(EstadoCita.CANCELADA);
            citaRepository.save(cita);
        }
    }

    @Override
    public void actualizarRating(Long citaId, Integer rating) {
        Optional<Cita> citaOpt = citaRepository.findById(citaId);
        if (citaOpt.isPresent()) {
            Cita cita = citaOpt.get();
            cita.setRating(rating);
            citaRepository.save(cita);
        }
    }
}
