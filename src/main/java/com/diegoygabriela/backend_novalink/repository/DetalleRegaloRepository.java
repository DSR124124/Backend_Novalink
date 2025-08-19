package com.diegoygabriela.backend_novalink.repository;

import com.diegoygabriela.backend_novalink.entity.DetalleRegalo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DetalleRegaloRepository extends JpaRepository<DetalleRegalo, Long> {
    
    // Buscar regalos por pareja
    List<DetalleRegalo> findByParejaIdOrderByFechaDesc(Long parejaId);
    
    // Buscar regalos dados por un usuario específico
    List<DetalleRegalo> findByRemitenteIdUsuarioOrderByFechaDesc(Integer remitenteId);
    
    // Buscar regalos recibidos por un usuario específico
    List<DetalleRegalo> findByReceptorIdUsuarioOrderByFechaDesc(Integer receptorId);
    
    // Buscar regalos entre dos usuarios específicos
    @Query("SELECT dr FROM DetalleRegalo dr WHERE " +
           "(dr.remitente.idUsuario = :usuario1Id AND dr.receptor.idUsuario = :usuario2Id) OR " +
           "(dr.remitente.idUsuario = :usuario2Id AND dr.receptor.idUsuario = :usuario1Id) " +
           "ORDER BY dr.fecha DESC")
    List<DetalleRegalo> findRegalosBetweenUsers(@Param("usuario1Id") Integer usuario1Id, 
                                               @Param("usuario2Id") Integer usuario2Id);
    
    // Buscar regalos por rango de fechas
    List<DetalleRegalo> findByFechaBetweenOrderByFechaDesc(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    // Buscar regalos asociados a una cita específica
    List<DetalleRegalo> findByCitaIdOrderByFechaDesc(Long citaId);
    
    // Buscar regalos asociados a un evento específico
    List<DetalleRegalo> findByEventoIdOrderByFechaDesc(Long eventoId);
    
    // Contar regalos por pareja
    Long countByParejaId(Long parejaId);
    
    // Buscar regalos recientes (últimos N días)
    @Query("SELECT dr FROM DetalleRegalo dr WHERE dr.fecha >= :fechaLimite ORDER BY dr.fecha DESC")
    List<DetalleRegalo> findRecentRegalos(@Param("fechaLimite") LocalDateTime fechaLimite);
}
