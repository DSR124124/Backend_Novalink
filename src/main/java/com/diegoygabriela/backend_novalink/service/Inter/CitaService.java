package com.diegoygabriela.backend_novalink.service.Inter;

import com.diegoygabriela.backend_novalink.entity.Cita;
import com.diegoygabriela.backend_novalink.entity.EstadoCita;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaService {

    // Métodos CRUD básicos
    void insert(Cita cita);
    List<Cita> list();
    void delete(Long id);
    Cita listId(Long id);
    
    // Métodos específicos para citas
    List<Cita> findByParejaId(Long parejaId);
    List<Cita> findByEstado(EstadoCita estado);
    List<Cita> findByParejaIdAndEstado(Long parejaId, EstadoCita estado);
    List<Cita> findByLugarId(Long lugarId);
    List<Cita> findByCategoriaCitaId(Long categoriaId);
    
    // Métodos para gestión temporal
    List<Cita> findCitasFuturasByPareja(Long parejaId);
    List<Cita> findCitasPasadasByPareja(Long parejaId);
    List<Cita> findByParejaIdAndFechaRange(Long parejaId, LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    // Métodos de estadísticas
    Long countByParejaIdAndEstado(Long parejaId, EstadoCita estado);
    List<Cita> findMejorCalificadasByPareja(Long parejaId);
    
    // Métodos de gestión de estado
    void marcarComoCompletada(Long citaId);
    void marcarComoCancelada(Long citaId);
    void actualizarRating(Long citaId, Integer rating);
}
