package com.diegoygabriela.backend_novalink.repository;

import com.diegoygabriela.backend_novalink.entity.Cita;
import com.diegoygabriela.backend_novalink.entity.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    
    // Buscar citas por pareja
    List<Cita> findByParejaId(Long parejaId);
    
    // Buscar citas por estado
    List<Cita> findByEstado(EstadoCita estado);
    
    // Buscar citas por pareja y estado
    List<Cita> findByParejaIdAndEstado(Long parejaId, EstadoCita estado);
    
    // Buscar citas por lugar
    List<Cita> findByLugarId(Long lugarId);
    
    // Buscar citas por categoría
    List<Cita> findByCategoriaCitaId(Long categoriaId);
    
    // Buscar citas futuras por pareja
    @Query("SELECT c FROM Cita c WHERE c.pareja.id = :parejaId AND c.fecha > :ahora ORDER BY c.fecha ASC")
    List<Cita> findCitasFuturasByPareja(@Param("parejaId") Long parejaId, @Param("ahora") LocalDateTime ahora);
    
    // Buscar citas pasadas por pareja
    @Query("SELECT c FROM Cita c WHERE c.pareja.id = :parejaId AND c.fecha < :ahora ORDER BY c.fecha DESC")
    List<Cita> findCitasPasadasByPareja(@Param("parejaId") Long parejaId, @Param("ahora") LocalDateTime ahora);
    
    // Buscar citas por rango de fechas
    @Query("SELECT c FROM Cita c WHERE c.pareja.id = :parejaId AND c.fecha BETWEEN :fechaInicio AND :fechaFin ORDER BY c.fecha ASC")
    List<Cita> findByParejaIdAndFechaRange(@Param("parejaId") Long parejaId, 
                                          @Param("fechaInicio") LocalDateTime fechaInicio, 
                                          @Param("fechaFin") LocalDateTime fechaFin);
    
    // Contar citas por pareja y estado
    Long countByParejaIdAndEstado(Long parejaId, EstadoCita estado);
    
    // Buscar citas mejor calificadas por pareja
    @Query("SELECT c FROM Cita c WHERE c.pareja.id = :parejaId AND c.rating IS NOT NULL ORDER BY c.rating DESC")
    List<Cita> findMejorCalificadasByPareja(@Param("parejaId") Long parejaId);
}
