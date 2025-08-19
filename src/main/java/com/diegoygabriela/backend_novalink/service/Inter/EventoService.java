package com.diegoygabriela.backend_novalink.service.Inter;

import com.diegoygabriela.backend_novalink.entity.Evento;

import java.time.LocalDate;
import java.util.List;

public interface EventoService {

    public void insert(Evento evento);
    public List<Evento> list();
    public void delete(Long idEvento);
    public Evento listId(Long idEvento);
    
    // Métodos adicionales para funcionalidad de eventos
    public List<Evento> findByParejaId(Long parejaId);
    public List<Evento> findByTipo(String tipo);
    public List<Evento> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
    public List<Evento> findEventosProximos();
    public List<Evento> findEventosPasados();
    public List<Evento> findByLugarId(Long lugarId);
    public List<Evento> findByParejaIdAndTipo(Long parejaId, String tipo);
    public List<Evento> findEventosByYear(int year);
    public Long countByParejaId(Long parejaId);
    public List<Evento> findEventosProximosPorPareja(Long parejaId, int dias);

}
