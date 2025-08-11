package com.diegoygabriela.backend_novalink.repository;

import com.diegoygabriela.backend_novalink.entity.Recordatorio;
import com.diegoygabriela.backend_novalink.entity.Recordatorio.EstadoRecordatorio;
import com.diegoygabriela.backend_novalink.entity.Recordatorio.TipoRecordatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RecordatorioRepository extends JpaRepository<Recordatorio, Long> {

    // Buscar recordatorios por pareja
    List<Recordatorio> findByParejaId(Long parejaId);

    // Buscar recordatorios activos por pareja
    List<Recordatorio> findByParejaIdAndEstado(Long parejaId, EstadoRecordatorio estado);

    // Buscar recordatorios por tipo
    List<Recordatorio> findByTipo(TipoRecordatorio tipo);

    // Buscar recordatorios por pareja y tipo
    List<Recordatorio> findByParejaIdAndTipo(Long parejaId, TipoRecordatorio tipo);

    // Buscar recordatorios próximos (útil para notificaciones)
    @Query("SELECT r FROM Recordatorio r WHERE r.pareja.id = :parejaId " +
           "AND r.estado = :estado " +
           "AND r.fechaHora BETWEEN :fechaInicio AND :fechaFin " +
           "ORDER BY r.fechaHora ASC")
    List<Recordatorio> findProximosRecordatorios(@Param("parejaId") Long parejaId,
                                                @Param("estado") EstadoRecordatorio estado,
                                                @Param("fechaInicio") LocalDateTime fechaInicio,
                                                @Param("fechaFin") LocalDateTime fechaFin);

    // Buscar recordatorios que necesitan notificación
    @Query("SELECT r FROM Recordatorio r WHERE r.estado = :estado " +
           "AND r.fechaHora <= :fechaNotificacion " +
           "ORDER BY r.fechaHora ASC")
    List<Recordatorio> findRecordatoriosParaNotificar(@Param("estado") EstadoRecordatorio estado,
                                                     @Param("fechaNotificacion") LocalDateTime fechaNotificacion);

    // Buscar recordatorios recurrentes
    List<Recordatorio> findByEsRecurrente(Boolean esRecurrente);

    // Buscar recordatorios por usuario creador
    List<Recordatorio> findByCreadoPorId(Long usuarioId);

    // Buscar recordatorios por pareja y rango de fechas
    @Query("SELECT r FROM Recordatorio r WHERE r.pareja.id = :parejaId " +
           "AND r.fechaHora BETWEEN :fechaInicio AND :fechaFin " +
           "ORDER BY r.fechaHora ASC")
    List<Recordatorio> findByParejaIdAndFechaRange(@Param("parejaId") Long parejaId,
                                                  @Param("fechaInicio") LocalDateTime fechaInicio,
                                                  @Param("fechaFin") LocalDateTime fechaFin);

    // Contar recordatorios activos por pareja
    Long countByParejaIdAndEstado(Long parejaId, EstadoRecordatorio estado);
}
