package com.diegoygabriela.backend_novalink.repository;

import com.diegoygabriela.backend_novalink.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    
    // Buscar eventos por pareja
    List<Evento> findByParejaIdOrderByFechaDesc(Long parejaId);
    
    // Buscar eventos por tipo
    List<Evento> findByTipoIgnoreCaseOrderByFechaDesc(String tipo);
    
    // Buscar eventos por rango de fechas
    List<Evento> findByFechaBetweenOrderByFechaDesc(LocalDate fechaInicio, LocalDate fechaFin);
    
    // Buscar eventos próximos
    @Query("SELECT e FROM Evento e WHERE e.fecha >= :fechaActual ORDER BY e.fecha ASC")
    List<Evento> findEventosProximos(@Param("fechaActual") LocalDate fechaActual);
    
    // Buscar eventos pasados
    @Query("SELECT e FROM Evento e WHERE e.fecha < :fechaActual ORDER BY e.fecha DESC")
    List<Evento> findEventosPasados(@Param("fechaActual") LocalDate fechaActual);
    
    // Buscar eventos por lugar
    List<Evento> findByLugarIdOrderByFechaDesc(Long lugarId);
    
    // Buscar eventos por pareja y tipo
    List<Evento> findByParejaIdAndTipoIgnoreCaseOrderByFechaDesc(Long parejaId, String tipo);
    
    // Buscar eventos de un año específico
    @Query("SELECT e FROM Evento e WHERE YEAR(e.fecha) = :year ORDER BY e.fecha DESC")
    List<Evento> findEventosByYear(@Param("year") int year);
    
    // Contar eventos por pareja
    Long countByParejaId(Long parejaId);
    
    // Buscar eventos próximos de una pareja (próximos N días)
    @Query("SELECT e FROM Evento e WHERE e.pareja.id = :parejaId AND e.fecha BETWEEN :fechaActual AND :fechaLimite ORDER BY e.fecha ASC")
    List<Evento> findEventosProximosPorPareja(@Param("parejaId") Long parejaId, 
                                             @Param("fechaActual") LocalDate fechaActual, 
                                             @Param("fechaLimite") LocalDate fechaLimite);
}
